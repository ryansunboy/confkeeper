/**
 * DataController.java
 */
package com.qianwang365.confkeeper.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.utils.Constants;
import com.qianwang365.confkeeper.model.EndParamEntity;
import com.qianwang365.confkeeper.service.imp.EndParamService;

/**
 * @author Yate
 * @date Jan 13, 2014
 * @description TODO
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/api/data")
public class DataController extends APIBaseController {

	@Resource
	private EndParamService endParamService;

	@RequestMapping(value = "/{app}/{version}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject get(@PathVariable String app, @PathVariable String version) {
		List<EndParamEntity> epps = this.endParamService.getAppVersionById(app,
				version);

		JSONObject json = new JSONObject();
		JSONArray jepps = new JSONArray();
		json.put(Constants.MSG_EVENT, "GET");
		json.put("appName", app);
		json.put("appVer", version);
		json.put(Constants.MSG_CONTENT, jepps);

		JSONObject temp;
		for (EndParamEntity e : epps) {
			temp = new JSONObject();
			temp.put("type", e.getType());
			temp.put("key", e.getKey());
			temp.put("value", e.getValue());
			jepps.add(temp);
		}
		return json;
	}
}
