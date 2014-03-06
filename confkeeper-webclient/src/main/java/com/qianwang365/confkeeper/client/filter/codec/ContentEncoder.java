/**
 * ContentEncoder.java
 */
package com.qianwang365.confkeeper.client.filter.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author Yate
 * @date Dec 30, 2013
 * @description TODO
 * @version 1.0
 */
public class ContentEncoder implements ProtocolEncoder {

	protected static class EnConverter {
		public byte[] converter(Object m) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolEncoder#encode(org.apache.mina.core
	 * .session.IoSession, java.lang.Object,
	 * org.apache.mina.filter.codec.ProtocolEncoderOutput)
	 */
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buffer = IoBuffer.allocate(64, false);
		buffer.setAutoExpand(true);
		buffer.put(message.toString().getBytes());
		buffer.flip();
		out.write(buffer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolEncoder#dispose(org.apache.mina.
	 * core.session.IoSession)
	 */
	@Override
	public void dispose(IoSession session) throws Exception {
	}

}
