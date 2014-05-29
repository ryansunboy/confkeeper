/**
 * AppController.java
 */
package com.qianwang365.confkeeper.controller;

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
import com.qianwang365.confkeeper.service.IAppService;
import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/app")
public class AppController extends BaseController {

	@Resource
	private IAppService service;

	@RequestMapping(method = { RequestMethod.GET })
	public String list(Model model) {
		Pagination<AppEntity> p = this.service.getExEntities(1,
				Integer.MAX_VALUE, new AppEntity());
		model.addAttribute("datas", p.getRows());
		return "app/list";
	}

	@RequestMapping(value = "/{rows}/{idx}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject list(@PathVariable int idx, @PathVariable int rows,
			@RequestParam(value = "appName", required = false) String appName,
			@RequestParam(value = "appNote", required = false) String appNote) {
		AppEntity e = new AppEntity();
		if (StringUtils.isNotEmpty(appName))
			e.setAppName(appName);
		if (StringUtils.isNotEmpty(appNote))
			e.setAppNote(appNote);

		Pagination<AppEntity> p = this.service.getEntities(idx, rows, e);
		final JSONObject json = new JSONObject();
		json.put("idx", p.getPageIndex());
		json.put("rows", p.getPageSize());
		json.put("count", p.getPageCount());
		json.put("total", p.getTotal());

		JSONArray arr = new JSONArray();
		json.put("data", arr);

		JSONObject arr_in_obj;
		for (AppEntity a : p.getRows()) {
			arr_in_obj = new JSONObject();
			arr_in_obj.put("id", a.getId());
			arr_in_obj.put("appName", a.getAppName());
			arr_in_obj.put("appNote", a.getAppNote());
			arr.add(arr_in_obj);
		}
		return json;
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add() {
		return "app/add";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public ModelAndView add(
			@RequestParam(required = true, value = "appName") String appName,
			@RequestParam(required = false, value = "appNote", defaultValue = "") String appNote) {
		this.service.add(new AppEntity().setAppName(appName)
				.setAppNote(appNote));
		return new ModelAndView("redirect:/app");
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String modify(Model model, @PathVariable long id) {
//		model.addAttribute("e", this.service.getEntity(id));
		return "app/info";
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST })
	public ModelAndView modify(@PathVariable long id,
			@RequestParam(value = "appName", required = true) String appName,
			@RequestParam(value = "appNote", required = true) String appNote) {
		AppEntity a = this.service.getEntity(id);
		a.setAppName(appName);
		a.setAppNote(appNote);
		this.service.update(a);
		return new ModelAndView("redirect:/app");
	}

	@RequestMapping(value = "/remove/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable long id) {
		this.service.remove(id);
		return new ModelAndView("redirect:/app");
	}

	@RequestMapping(value = "/removes", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject delete(HttpServletRequest req,
			@RequestParam(value = "ids", required = true) Long... ids) {
		this.service.batchRemove(ids);
		return JSONObject.parseObject("{\"msg\":\"succeed\"}");
	}
}
