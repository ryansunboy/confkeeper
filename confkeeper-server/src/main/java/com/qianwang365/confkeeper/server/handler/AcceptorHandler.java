/**
 * ConfKeeperHandle.java
 */
package com.qianwang365.confkeeper.server.handler;

import java.io.IOException;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.amqp.JSONConsumer;
import com.qianwang365.confkeeper.server.content.handle.impl.IContentHandler;
import com.qianwang365.confkeeper.server.utils.PropertyUtils;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description confkeeper操作实现
 * @version 1.0
 */
public class AcceptorHandler extends IoHandlerAdapter {

	private static final Logger log = LoggerFactory
			.getLogger(AcceptorHandler.class);

	protected final IContentHandler<JSONObject, JSONObject> handler;
	protected JSONConsumer qc;

	public AcceptorHandler(IContentHandler<JSONObject, JSONObject> handler) {
		this.handler = handler;
		try {
			this.qc = new JSONConsumer(PropertyUtils.getInstance().getValue(
					"amqp.exchange"), PropertyUtils.getInstance().getValue(
					"amqp.exchange"));
			new Thread(qc,"AMQPConsumer").start();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			System.exit(1);
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		this.qc.add(session);

		// final JSONConsumer qc = new JSONConsumer(PropertyUtils
		// .getInstance().getValue("amqp.exchange"), PropertyUtils
		// .getInstance().getValue("amqp.exchange"), session);
		// new Thread(qc).start();
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		this.qc.remove(session);
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
		JSONObject response = this.handler.handleContent((JSONObject) message);
		session.write(response);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.debug(session.getRemoteAddress() + " sent msg:"
				+ message.toString());
	}
}
