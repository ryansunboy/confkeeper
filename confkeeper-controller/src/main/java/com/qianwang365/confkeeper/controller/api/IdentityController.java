/**
 * RegeditController.java
 */
package com.qianwang365.confkeeper.controller.api;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.client.RedisClient;
import redis.reply.IntegerReply;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/api/id")
public class IdentityController extends APIBaseController {

	public static final String API_SUCCEED = "'{'\"code\":200'}'";
	public static final String API_FAILED = "'{'\"code\":400'}'";

	public static final String REG_SERVER_FORMAT = "'{'\"code\":{0},\"serverId\":{1}'}'";
	public static final String REG_CLIENT_FORMAT = "'{'\"code\":{0},\"clientId\":{1}'}'";
	public static final String SERVER_STORE = "server:{0}";
	public static final String CLIENT_STORE = "client:{0}:{1}";

	@SuppressWarnings("unused")
	private static class ClientData {
		String address;
		String appName;
		String appVer;

		public final void setAddress(String address) {
			this.address = address;
		}

		public final void setAppName(String appName) {
			this.appName = appName;
		}

		public final void setAppVer(String appVer) {
			this.appVer = appVer;
		}
	}

	/**
	 * @description 服务器注册
	 * @return
	 */
	@RequestMapping(value = "/registry", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject registry(
			@RequestParam(required = true, value = "server") String address) {
		RedisClient rc = null;
		try {
			rc = this.redisPool.borrowObject();
			IntegerReply ir = rc.incr("servers");
			rc.sadd(MessageFormat.format(SERVER_STORE, ir.data()), ir.data());
			return JSONObject.parseObject(MessageFormat.format(
					REG_SERVER_FORMAT, 200, ir.data()));
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			return JSONObject.parseObject(API_FAILED);
		} finally {
			try {
				if (rc != null)
					this.redisPool.returnObject(rc);
			} catch (Exception e) {
				this.log.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @description 客户端注册
	 * @return
	 */
	@RequestMapping(value = "/registry/{server}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject registry(@PathVariable String server,
			@RequestParam(required = true, value = "client") ClientData cd) {
		RedisClient rc = null;
		try {
			rc = this.redisPool.borrowObject();
			IntegerReply ir = rc.incr("clients");
			rc.sadd(MessageFormat.format(CLIENT_STORE, server, ir.data()),
					ir.data());
			return JSONObject.parseObject(MessageFormat.format(
					REG_CLIENT_FORMAT, 200, ir.data()));
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			return JSONObject.parseObject(API_FAILED);
		} finally {
			try {
				if (rc != null)
					this.redisPool.returnObject(rc);
			} catch (Exception e) {
				this.log.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @description 客户注销
	 * @return
	 */
	@RequestMapping(value = "/unregistry/{server}/{client}", method = { RequestMethod.GET })
	@ResponseBody
	public JSONObject unregistry(@PathVariable String server,
			@PathVariable String client) {
		RedisClient rc = null;
		try {
			rc = this.redisPool.borrowObject();
			rc.srem(MessageFormat.format(CLIENT_STORE, server, client), client);
			return JSONObject.parseObject("{\"code\":200}");
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			return JSONObject.parseObject(API_FAILED);
		} finally {
			try {
				if (rc != null)
					this.redisPool.returnObject(rc);
			} catch (Exception e) {
				this.log.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @description 服务器心跳
	 * @return
	 */
	@RequestMapping(value = "/live/{server}", method = { RequestMethod.GET })
	@ResponseBody
	public JSONObject liveNotice(String server) {
		return JSONObject.parseObject("{\"code\":200}");
	}
}
