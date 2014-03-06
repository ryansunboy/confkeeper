package com.qianwang365.confkeeper.server.session;

import java.io.Serializable;

import org.apache.mina.core.session.IoSession;

/**
 * @author Yate
 * @date Jan 15, 2014
 * @description TODO
 * @version 1.0
 */
public interface ISessionManager<K, S extends IoSession> {
	public void add(final K key, final S session);

	public void remove(final K key);

	public int sessionCount();

	public <O extends Serializable> void write(O data);
}
