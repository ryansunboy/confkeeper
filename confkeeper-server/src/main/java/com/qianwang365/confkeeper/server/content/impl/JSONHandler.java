/**
 * ConfKeeperContent.java
 */
package com.qianwang365.confkeeper.server.content.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.content.event.ContentEventType;
import com.qianwang365.confkeeper.server.content.event.MessageEvent;
import com.qianwang365.confkeeper.server.content.event.MessageListener;
import com.qianwang365.confkeeper.server.content.handle.impl.IContentHandler;
import com.qianwang365.confkeeper.server.manager.APIConnType;
import com.qianwang365.confkeeper.server.manager.factory.APIConnectorFactory;
import com.qianwang365.confkeeper.server.utils.Constants;
import com.qianwang365.confkeeper.server.utils.PropertyUtils;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class JSONHandler<C extends JSONObject> implements
		IContentHandler<C, JSONObject> {

	private static final Logger log = LoggerFactory
			.getLogger(JSONHandler.class);

	protected APIConnectorFactory factory;
	protected Map<String, MessageListener<C>> listeners;
	protected final String targetUrl = PropertyUtils.getInstance().getValue(
			"ckmgr.server");

	/**
	 * @return the factory
	 */
	public final APIConnectorFactory getFactory() {
		return factory;
	}

	/**
	 * @param factory
	 *            the factory to set
	 */
	public final void setFactory(APIConnectorFactory factory) {
		this.factory = factory;
	}

	/**
	 * @return the listeners
	 */
	public final Map<String, MessageListener<C>> getListeners() {
		return listeners;
	}

	/**
	 * @param listeners
	 *            the listeners to set
	 */
	public final void setListeners(Map<String, MessageListener<C>> listeners) {
		this.listeners = listeners;
	}

	/**
	 * @param c
	 *            the c to set
	 * @throws IOException
	 * @throws ParseException
	 */
	public final JSONObject handleContent(C c) {
		ContentEventType event = ContentEventType.valueOf(c
				.getString(Constants.MSG_EVENT));
		JSONObject content = c.getJSONObject(Constants.MSG_CONTENT);
		String appName = content.getString("appName");
		String appVer = content.getString("appVer");
		for (Map.Entry<String, MessageListener<C>> e : listeners.entrySet()) {
			e.getValue().headleEvent(new MessageEvent<C>(event, c));
		}
		try {
			StringBuilder sb = new StringBuilder(64);
			sb.append(this.targetUrl);
			sb.append("/data/" + appName);
			sb.append("/" + appVer);
			
			return this.factory.getConnector().invoke(APIConnType.GET,
					sb.toString(), null, null, null, Constants.CHARACTOR_CODE);
		} catch (ParseException e1) {
			log.error(e1.getMessage(), e1);
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		}
		return JSONObject.parseObject("{\"code\":502}");
	}
}
