/**
 * ConfKeeperHandle.java
 */
package com.qianwang365.confkeeper.client.handle;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.content.handle.IContentHandler;
import com.qianwang365.confkeeper.client.exception.FailException;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description confkeeper操作实现
 * @version 1.0
 */
public class ConnectorHandler extends IoHandlerAdapter {
	protected static final Logger log = LoggerFactory
			.getLogger(ConnectorHandler.class);

	protected final IContentHandler<JSONObject> handler;

	public ConnectorHandler(IContentHandler<JSONObject> handler) {
		this.handler = handler;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.debug(session.getRemoteAddress() + " has created.");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.debug(session.getRemoteAddress() + " has opened.");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.debug(session.getRemoteAddress() + " has closed.");
		session.close(true);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.error(cause.getMessage(), cause);
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		JSONObject data = JSON.parseObject(message.toString());
//		if (data.getString("sid").equals(session.getAttribute("sid"))) {
//			session.setAttribute("sid", data.getString("sid"));
//		}
		try {
			this.handler.handleContent(data);
		} catch (FailException e) {
			// notice server failed event.
		}

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}
}
