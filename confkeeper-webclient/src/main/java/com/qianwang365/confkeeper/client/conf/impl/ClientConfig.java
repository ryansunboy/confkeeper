/**
 * ClientConfig.java
 */
package com.qianwang365.confkeeper.client.conf.impl;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.conf.IClientConfig;
import com.qianwang365.confkeeper.client.conf.IClientConfig.Formatter;
import com.qianwang365.confkeeper.client.content.event.ContentEventType;
import com.qianwang365.confkeeper.client.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class ClientConfig implements IClientConfig<Formatter> {

	protected final ContentEventType event;
	protected final JSONObject content;
	protected final IClientConfig.Formatter f = new Formatter() {
		@Override
		public String format() {
			final JSONObject json = new JSONObject();
			json.put(Constants.MSG_EVENT, event);
			json.put(Constants.MSG_CONTENT, content);
			return json.toString();
		}
	};

	/**
	 * 
	 */
	public ClientConfig(ContentEventType event, JSONObject content) {
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
