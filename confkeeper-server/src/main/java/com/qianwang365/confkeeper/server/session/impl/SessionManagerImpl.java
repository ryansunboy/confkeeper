package com.qianwang365.confkeeper.server.session.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qianwang365.confkeeper.server.session.ISessionManager;

/**
 * @author Yate
 * @date Jan 15, 2014
 * @description TODO
 * @version 1.0
 */
public class SessionManagerImpl implements ISessionManager<String, IoSession> {
	private static final Logger log = LoggerFactory
			.getLogger(SessionManagerImpl.class);

	protected final Map<String, IoSession> clientSessions = new ConcurrentHashMap<String, IoSession>();

	public SessionManagerImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.server.session.ISessionManager#add(java.lang
	 * .Object, org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void add(String key, IoSession session) {
		this.clientSessions.put(key, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.server.session.ISessionManager#remove(java
	 * .lang.Object)
	 */
	@Override
	public void remove(String key) {
		this.clientSessions.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.server.session.ISessionManager#sessionCount()
	 */
	@Override
	public int sessionCount() {
		return this.clientSessions.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.server.session.ISessionManager#SessionWriter
	 * (java.io.Serializable)
	 */
	@Override
	public <O extends Serializable> void write(O data) {
		for (Map.Entry<String, IoSession> e : this.clientSessions.entrySet()) {
			e.getValue().write(data);
		}
	}

}
