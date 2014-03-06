/**
 * ConfKeeperConnector.java
 */
package com.qianwang365.confkeeper.client.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.conf.IClientConfig;
import com.qianwang365.confkeeper.client.conf.IClientConfig.Formatter;
import com.qianwang365.confkeeper.client.content.event.ContentListener;
import com.qianwang365.confkeeper.client.content.handle.impl.JSONHandler;
import com.qianwang365.confkeeper.client.core.IMinaConnection;
import com.qianwang365.confkeeper.client.core.IMinaConnection.MinaConnParam;
import com.qianwang365.confkeeper.client.exception.KeyClashException;
import com.qianwang365.confkeeper.client.filter.IoByteFilter;
import com.qianwang365.confkeeper.client.filter.codec.ContentCodecFactory;
import com.qianwang365.confkeeper.client.handle.ConnectorHandler;
import com.qianwang365.confkeeper.client.utils.Constants;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class MinaConnector<P extends MinaConnParam> implements
		IMinaConnection<JSONObject> {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected final Map<String, ContentListener<JSONObject>> listeners = new HashMap<String, ContentListener<JSONObject>>();
	protected P p;
	protected IoConnector connector;

	public MinaConnector(P p) {
		this.p = p;
	}

	public void init() {
		connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(p.getTimeout());
		connector.getFilterChain().addFirst(Constants.BYTES_FILTER,
				new IoByteFilter());
		connector.getFilterChain().addLast(Constants.LOG_FILTER,
				new LoggingFilter());
		connector.getFilterChain().addLast(Constants.CODEC_FILTER,
				new ProtocolCodecFilter(new ContentCodecFactory()));

		JSONHandler<JSONObject> h = new JSONHandler<JSONObject>();
		h.setListeners(listeners);

		connector.setHandler(new ConnectorHandler(h));
	}

	public <F extends Formatter> void connection(IClientConfig<F> conf) {
		ConnectFuture future = connector.connect(p.getServerAddress());
		future.awaitUninterruptibly();
		IoSession session = future.getSession();
		session.write(conf.getFormatter().format());
		session.getCloseFuture().awaitUninterruptibly();
		while (!session.isConnected()) {
			this.log.error("服务器会话连接已段开,尝试重连...");
			future = connector.connect(p.getServerAddress());
			future.awaitUninterruptibly();
			session = future.getSession();
			this.log.error("服务器连接完成");
			session.getCloseFuture().awaitUninterruptibly();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.client.base.IClientConnector#disconnection()
	 */
	@Override
	public void disconnection() {
		connector.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.client.base.IClientConnector#getConnParam()
	 */
	@Override
	public ConnectionParam getConnParam() {
		return this.p;
	}

	public void regeditListener(String key, ContentListener<JSONObject> l)
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
