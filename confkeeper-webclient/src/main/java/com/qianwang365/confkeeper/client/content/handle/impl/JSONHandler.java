/**
 * ConfKeeperContent.java
 */
package com.qianwang365.confkeeper.client.content.handle.impl;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.content.event.ContentEvent;
import com.qianwang365.confkeeper.client.content.event.ContentEventType;
import com.qianwang365.confkeeper.client.content.event.ContentListener;
import com.qianwang365.confkeeper.client.content.handle.IContentHandler;
import com.qianwang365.confkeeper.client.exception.FailException;
import com.qianwang365.confkeeper.client.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class JSONHandler<C extends JSONObject> implements IContentHandler<C> {

	protected Map<String, ContentListener<C>> listeners;

	/**
	 * @return the listeners
	 */
	public final Map<String, ContentListener<C>> getListeners() {
		return listeners;
	}

	/**
	 * @param listeners
	 *            the listeners to set
	 */
	public final void setListeners(Map<String, ContentListener<C>> listeners) {
		this.listeners = listeners;
	}

	/**
	 * @param c
	 *            the c to set
	 * @throws Exception 
	 */
	public final void handleContent(C c) throws FailException {
		ContentEventType type = ContentEventType
				.valueOf(c.getString(Constants.MSG_EVENT));
		final ContentEvent<C> event = new ContentEvent<C>(type, c);
		for (Map.Entry<String, ContentListener<C>> e : listeners.entrySet()) {
				e.getValue().headleEvent(event);
		}
	}
}
