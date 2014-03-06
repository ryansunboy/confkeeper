/**
 * ContentDecoder.java
 */
package com.qianwang365.confkeeper.server.filter.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.utils.Constants;

/**
 * @author Yate
 * @date Dec 30, 2013
 * @description TODO
 * @version 1.0
 */
public class ContentDecoder implements ProtocolDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolDecoder#decode(org.apache.mina.core
	 * .session.IoSession, org.apache.mina.core.buffer.IoBuffer,
	 * org.apache.mina.filter.codec.ProtocolDecoderOutput)
	 */
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		JSONObject request = JSON.parseObject(in
				.getString(Constants.CHARSET_UTF8.newDecoder()));
		out.write(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolDecoder#finishDecode(org.apache.
	 * mina.core.session.IoSession,
	 * org.apache.mina.filter.codec.ProtocolDecoderOutput)
	 */
	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolDecoder#dispose(org.apache.mina.
	 * core.session.IoSession)
	 */
	@Override
	public void dispose(IoSession session) throws Exception {
	}

}
