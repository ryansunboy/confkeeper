/**
 * AppController.java
 */
package com.qianwang365.confkeeper.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.qianwang365.confkeeper.controller.amqp.JSONProducer;
import com.qianwang365.confkeeper.model.EndParamEntity;
import com.qianwang365.confkeeper.model.EndPointEntity;
import com.qianwang365.confkeeper.service.IEndParamService;
import com.qianwang365.confkeeper.service.IEndPointService;
import com.qianwang365.confkeeper.utils.Constants;
import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/endparam")
public class EndParamController extends BaseController {

	@Resource
	private IEndPointService epService;
	@Resource
	private IEndParamService service;
	@Resource(name = "amqpp")
	private JSONProducer amqpp;

	@RequestMapping(method = { RequestMethod.GET })
	public String list(Model model) {
		Pagination<EndParamEntity> p = this.service.getEntities(1,
				Integer.MAX_VALUE, new EndParamEntity());
		model.addAttribute("datas", p.getRows());
		return "endparam/list";
	}

	@RequestMapping(value = "/{rows}/{idx}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject list(@PathVariable int idx, @PathVariable int rows,
			@RequestParam(value = "epId", required = false) Long fkId,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "value", required = false) String value) {
		EndParamEntity e = new EndParamEntity();
		if (fkId != null) {
			EndPointEntity ep = new EndPointEntity();
			ep.setId(fkId);
			e.setEndpoint(ep);
		}
		if (key != null)
			e.setKey(key);
		if (value != null)
			e.setValue(value);

		Pagination<EndParamEntity> p = this.service.getEntities(idx, rows, e);
		final JSONObject json = new JSONObject();
		json.put("idx", p.getPageIndex());
		json.put("rows", p.getPageSize());
		json.put("count", p.getPageCount());
		json.put("total", p.getTotal());

		JSONArray arr = new JSONArray();
		json.put("data", arr);

		JSONObject arr_in_obj;
		for (EndParamEntity a : p.getRows()) {
			arr_in_obj = new JSONObject();
			arr_in_obj.put("appName", a.getEndpoint().getApp().getAppName());
			arr_in_obj.put("verId", a.getEndpoint().getId());
			arr_in_obj.put("key", a.getKey());
			arr_in_obj.put("value", a.getValue());
			arr.add(arr_in_obj);
		}
		return json;
	}

	public static final Pattern p = Pattern
			.compile("\\[(\\d+)\\](.+?)=(.+)\\r?");

	public static final String CONTENT_FORMAT = "'{'\"type\":{0},\"key\":\"{1}\",\"value\":\"{2}\"'}'";

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public ModelAndView add(
			@RequestParam(required = true, value = "ep_id") long epId,
			@RequestParam(required = true, value = "params") String params) {

		String[] pvs = StringUtils.split(params, '\n');
		Matcher m;

		EndPointEntity epe = epService.getEntity(epId);

		final List<EndParamEntity> lp = new ArrayList<EndParamEntity>();
		final JSONArray arr = new JSONArray();
		for (String s : pvs) {
			m = p.matcher(s);
			if (m.matches()) {
				lp.add(new EndParamEntity(Integer.parseInt(m.group(1)),
						StringUtils.trimToEmpty(m.group(2)), StringUtils
								.trimToEmpty(m.group(3)), epe));
				arr.add(JSONObject.parse(MessageFormat.format(CONTENT_FORMAT,
						Integer.parseInt(m.group(1)),
						StringUtils.trimToEmpty(m.group(2)),
						StringUtils.trimToEmpty(m.group(3)))));
			}
		}

		this.service.txUpdate(epId, lp);

		try {
			JSONObject json = new JSONObject();
			json.put(Constants.MSG_EVENT, "SET");
			json.put("appName", epe.getApp().getAppName());
			json.put("appVer", epe.getTrunk());

			json.put(Constants.MSG_CONTENT, arr);

			amqpp.sendMessage(json, "");
		} catch (IOException e) {
			this.log.error(e.getMessage(), e);
		}

		return new ModelAndView("redirect:/endparam");
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public JSONObject info(@PathVariable long id) {
		EndParamEntity a = this.service.getEntity(id);
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("appName", a.getEndpoint().getApp().getAppName());
		json.put("verId", a.getEndpoint().getId());
		json.put("key", a.getKey());
		json.put("state", a.getValue());
		return json;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject modify(@PathVariable long id,
			@RequestParam(value = "epId", required = true) Long fkId,
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "value", required = true) String value) {
		EndParamEntity e = this.service.getEntity(id);
		e.setKey(key);
		e.setValue(value);
		this.service.update(e);
		return JSONObject.parseObject("{\"msg\":\"succeed\"}");
	}

	@RequestMapping(value = "/remove/{id}", method = { RequestMethod.GET })
	public ModelAndView delete(@PathVariable long id) {
		this.service.remove(id);
		return new ModelAndView("redirect:/endparam");
	}

	@RequestMapping(value = "/removes", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject delete(HttpServletRequest req,
			@RequestParam(value = "ids", required = true) Long... ids) {
		this.service.batchRemove(ids);
		return JSONObject.parseObject("{\"msg\":\"succeed\"}");
	}
}
