/**
 * IoBufferFilter.java
 */
package com.qianwang365.confkeeper.server.filter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * @author Yate
 * @date Dec 30, 2013
 * @description TODO
 * @version 1.0
 */
public class IoByteFilter extends IoFilterAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mina.core.filterchain.IoFilterAdapter#init()
	 */
	@Override
	public void init() throws Exception {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mina.core.filterchain.IoFilterAdapter#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		super.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#onPreAdd(org.apache.
	 * mina.core.filterchain.IoFilterChain, java.lang.String,
	 * org.apache.mina.core.filterchain.IoFilter.NextFilter)
	 */
	@Override
	public void onPreAdd(IoFilterChain parent, String name,
			NextFilter nextFilter) throws Exception {
		super.onPreAdd(parent, name, nextFilter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#onPostAdd(org.apache
	 * .mina.core.filterchain.IoFilterChain, java.lang.String,
	 * org.apache.mina.core.filterchain.IoFilter.NextFilter)
	 */
	@Override
	public void onPostAdd(IoFilterChain parent, String name,
			NextFilter nextFilter) throws Exception {
		super.onPostAdd(parent, name, nextFilter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#onPreRemove(org.apache
	 * .mina.core.filterchain.IoFilterChain, java.lang.String,
	 * org.apache.mina.core.filterchain.IoFilter.NextFilter)
	 */
	@Override
	public void onPreRemove(IoFilterChain parent, String name,
			NextFilter nextFilter) throws Exception {
		super.onPreRemove(parent, name, nextFilter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#onPostRemove(org.apache
	 * .mina.core.filterchain.IoFilterChain, java.lang.String,
	 * org.apache.mina.core.filterchain.IoFilter.NextFilter)
	 */
	@Override
	public void onPostRemove(IoFilterChain parent, String name,
			NextFilter nextFilter) throws Exception {
		super.onPostRemove(parent, name, nextFilter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#sessionCreated(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionCreated(NextFilter nextFilter, IoSession session)
			throws Exception {
		super.sessionCreated(nextFilter, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#sessionOpened(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(NextFilter nextFilter, IoSession session)
			throws Exception {
		super.sessionOpened(nextFilter, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#sessionClosed(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(NextFilter nextFilter, IoSession session)
			throws Exception {
		super.sessionClosed(nextFilter, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#sessionIdle(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession,
	 * org.apache.mina.core.session.IdleStatus)
	 */
	@Override
	public void sessionIdle(NextFilter nextFilter, IoSession session,
			IdleStatus status) throws Exception {
		super.sessionIdle(nextFilter, session, status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#exceptionCaught(org.
	 * apache.mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(NextFilter nextFilter, IoSession session,
			Throwable cause) throws Exception {
		super.exceptionCaught(nextFilter, session, cause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#messageReceived(org.
	 * apache.mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		super.messageReceived(nextFilter, session, message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#messageSent(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession,
	 * org.apache.mina.core.write.WriteRequest)
	 */
	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		super.messageSent(nextFilter, session, writeRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#filterWrite(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession,
	 * org.apache.mina.core.write.WriteRequest)
	 */
	@Override
	public void filterWrite(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		super.filterWrite(nextFilter, session, writeRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.filterchain.IoFilterAdapter#filterClose(org.apache
	 * .mina.core.filterchain.IoFilter.NextFilter,
	 * org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void filterClose(NextFilter nextFilter, IoSession session)
			throws Exception {
		super.filterClose(nextFilter, session);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mina.core.filterchain.IoFilterAdapter#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
