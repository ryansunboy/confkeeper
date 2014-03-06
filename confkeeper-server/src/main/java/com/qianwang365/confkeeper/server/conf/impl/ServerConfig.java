/**
 * ClientConfig.java
 */
package com.qianwang365.confkeeper.server.conf.impl;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.conf.IServerConfig;
import com.qianwang365.confkeeper.server.conf.IServerConfig.Formatter;
import com.qianwang365.confkeeper.server.content.event.ContentEventType;
import com.qianwang365.confkeeper.server.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class ServerConfig implements IServerConfig<Formatter> {

	protected final ContentEventType event;
	protected final JSONObject content;
	protected final IServerConfig.Formatter f = new Formatter() {

		@Override
		public String format() {
			JSONObject json = new JSONObject();
			json.put(Constants.MSG_EVENT, event);
			json.put(Constants.MSG_CONTENT, content);
			return json.toJSONString();
		}
	};

	/**
	 * 
	 */
	public ServerConfig(ContentEventType event, JSONObject content) {
		this.event = event;
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianwang365.confkeeper.client.base.IClientConfig#getFormatter()
	 */
	@Override
	public Formatter getFormatter() {
		return f;
	}

}
