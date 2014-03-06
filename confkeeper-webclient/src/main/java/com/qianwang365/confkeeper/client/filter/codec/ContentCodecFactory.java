/**
 * ContentCodecFactory.java
 */
package com.qianwang365.confkeeper.client.filter.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import com.qianwang365.confkeeper.client.filter.codec.ContentDecoder;
import com.qianwang365.confkeeper.client.filter.codec.ContentEncoder;

/**
 * @author Yate
 * @date Dec 30, 2013
 * @description TODO
 * @version 1.0
 */
public class ContentCodecFactory implements ProtocolCodecFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolCodecFactory#getEncoder(org.apache
	 * .mina.core.session.IoSession)
	 */
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new ContentEncoder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolCodecFactory#getDecoder(org.apache
	 * .mina.core.session.IoSession)
	 */
	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new ContentDecoder();
	}

}
