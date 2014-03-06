/**
 * ConfKeeperConnector.java
 */
package com.qianwang365.confkeeper.server.core.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.content.event.MessageListener;
import com.qianwang365.confkeeper.server.content.impl.JSONHandler;
import com.qianwang365.confkeeper.server.core.IMinaAcceptor;
import com.qianwang365.confkeeper.server.core.IMinaAcceptor.MinaStarterParam;
import com.qianwang365.confkeeper.server.exception.KeyClashException;
import com.qianwang365.confkeeper.server.filter.IoByteFilter;
import com.qianwang365.confkeeper.server.filter.codec.ContentCodecFactory;
import com.qianwang365.confkeeper.server.handler.AcceptorHandler;
import com.qianwang365.confkeeper.server.manager.factory.APIConnectorFactory;
import com.qianwang365.confkeeper.server.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class MinaAcceptor<P extends MinaStarterParam> implements
		IMinaAcceptor<JSONObject> {

	protected final Map<String, MessageListener<JSONObject>> listeners = new HashMap<String, MessageListener<JSONObject>>();
	protected P p;
	protected IoAcceptor acceptor;
	
	public MinaAcceptor(P p) {
		this.p = p;
		this.init();
		this.initSessionConfig();
	}

	protected void init() {
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addFirst(Constants.BYTES_FILTER,
				new IoByteFilter());
		acceptor.getFilterChain().addLast(Constants.LOG_FILTER,
				new LoggingFilter());
		acceptor.getFilterChain().addLast(Constants.CODEC_FILTER,
				new ProtocolCodecFilter(new ContentCodecFactory()));
		JSONHandler<JSONObject> h = new JSONHandler<JSONObject>();
		h.setFactory(new APIConnectorFactory());
		h.setListeners(listeners);
		acceptor.setHandler(new AcceptorHandler(h));
	}

	protected void initSessionConfig() {
		acceptor.getSessionConfig().setReadBufferSize(p.getBufferSize());
		acceptor.getSessionConfig().setWriteTimeout((int) p.getTimeout());
		acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE,
				p.getReadIdle());
		acceptor.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE,
				p.getWriteIdle());
	}

	public void start() throws IOException {
		acceptor.bind(p.getServerAddress());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.client.base.IClientConnector#disconnection()
	 */
	@Override
	public void shutdown() {
		acceptor.dispose();
	}

	@Override
	public void reset() throws IOException {
		this.shutdown();
		this.init();
		this.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.client.base.IClientConnector#getConnParam()
	 */
	@Override
	public StarterParam getStarterParam() {
		return this.p;
	}

	public void regeditListener(String key, MessageListener<JSONObject> l)
			throws KeyClashException {
		if (!listeners.containsKey(key)) {
			this.listeners.put(key, l);
		} else {
			throw new KeyClashException();
		}
	}

	public void unregeditListener(String key) {
		if (this.listeners.containsKey(key)) {
			this.listeners.remove(key);
		}
	}

}
