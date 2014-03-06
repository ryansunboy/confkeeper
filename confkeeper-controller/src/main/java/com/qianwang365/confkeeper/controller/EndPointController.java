/**
 * AppController.java
 */
package com.qianwang365.confkeeper.controller;

import java.text.MessageFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.model.EndParamEntity;
import com.qianwang365.confkeeper.model.EndPointEntity;
import com.qianwang365.confkeeper.service.IAppService;
import com.qianwang365.confkeeper.service.IEndPointService;
import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/endpoint")
public class EndPointController extends BaseController {

	@Resource
	private IAppService appService;

	@Resource
	private IEndPointService service;

	@RequestMapping(method = { RequestMethod.GET })
	public String go(Model model) {
		Pagination<EndPointEntity> p = this.service.getEntities(1,
				Integer.MAX_VALUE, new EndPointEntity());
		model.addAttribute("datas", p.getRows());
		return "endpoint/list";
	}

	@RequestMapping(value = "/{rows}/{idx}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject list(@PathVariable int idx, @PathVariable int rows,
			@RequestParam(value = "app.id", required = false) Long appId,
			@RequestParam(value = "trunk", required = false) String trunk,
			@RequestParam(value = "state", required = false) Integer state) {
		EndPointEntity e = new EndPointEntity();
		if (appId != null) {
			AppEntity a = new AppEntity();
			a.setId(appId);
			e.setApp(a);
		}
		if (StringUtils.isNotEmpty(trunk))
			e.setTrunk(trunk);
		if (state != null)
			e.setState(state);

		Pagination<EndPointEntity> p = this.service.getEntities(idx, rows, e);
		final JSONObject json = new JSONObject();
		json.put("idx", p.getPageIndex());
		json.put("rows", p.getPageSize());
		json.put("count", p.getPageCount());
		json.put("total", p.getTotal());

		JSONArray arr = new JSONArray();
		json.put("data", arr);

		JSONObject arr_in_obj;
		for (EndPointEntity a : p.getRows()) {
			arr_in_obj = new JSONObject();
			arr_in_obj.put("id", a.getId());
			arr_in_obj.put("appName", a.getApp().getAppName());
			arr_in_obj.put("trunk", a.getTrunk());
			arr_in_obj.put("state", a.getState());
			arr.add(arr_in_obj);
		}
		return json;
	}

	// @RequestMapping(value = "/params/{id}", method = { RequestMethod.GET })
	// @ResponseBody
	// public JSONObject getParams(@PathVariable long id) {
	// JSONObject json = new JSONObject();
	// EndPointEntity epe = this.service.getEntity(id);
	// JSONArray ps = new JSONArray();
	// json.put("params", ps);
	// JSONObject p;
	// for (EndParamEntity e : epe.getParams()) {
	// p = new JSONObject();
	// p.put("type", e.getType());
	// p.put("key", e.getKey());
	// p.put("value", e.getValue());
	// ps.add(p);
	// }
	// return json;
	// }

	public static final String PARAM_FORMAT = "[{0}]{1}={2}\n";

	@RequestMapping(value = "/params/{id}", method = { RequestMethod.GET })
	public String params(@PathVariable long id, Model m) {
		EndPointEntity e = this.service.getEntity(id);
		StringBuilder sb = new StringBuilder(64 * e.getParams().size());
		for (EndParamEntity p : e.getParams()) {
			sb.append(MessageFormat.format(PARAM_FORMAT, p.getType(),
					p.getKey(), p.getValue()));
		}
		m.addAttribute("e", e);
		m.addAttribute("pv", sb.toString());
		return "endpoint/params";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(Model m) {
		Pagination<AppEntity> p = appService.getEntities(1, Integer.MAX_VALUE,
				new AppEntity());
		m.addAttribute("apps", p.getRows());
		return "endpoint/add";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public ModelAndView add(EndPointEntity e,
			@RequestParam(required = true, value = "appId") long appId) {
		e.getApp().setId(appId);
		this.service.add(e);
		return new ModelAndView("redirect:/endpoint");
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String modify(Model m, @PathVariable long id) {
		m.addAttribute("e", this.service.getEntity(id));
		return "endpoint/info";
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST })
	public ModelAndView modify(@PathVariable long id,
			@RequestParam(value = "trunk", required = true) String trunk,
			@RequestParam(value = "state", required = true) int state) {
		EndPointEntity e = this.service.getEntity(id);
		e.setTrunk(trunk);
		e.setState(state);
		this.service.update(e);
		return new ModelAndView("redirect:/endpoint");
	}

	@RequestMapping(value = "/remove/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable long id) {
		this.service.remove(id);
		return new ModelAndView("redirect:/endpoint");
	}

	@RequestMapping(value = "/removes", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject delete(HttpServletRequest req,
			@RequestParam(value = "ids", required = true) Long... ids) {
		this.service.batchRemove(ids);
		return JSONObject.parseObject("{\"msg\":\"succeed\"}");
	}
}
