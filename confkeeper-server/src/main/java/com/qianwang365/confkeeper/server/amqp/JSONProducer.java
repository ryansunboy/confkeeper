/**
 * Producer.java
 */
package com.qianwang365.confkeeper.server.amqp;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Yate
 * @date 2014年1月13日
 * @description AMQP
 * @version 1.0
 */
public class JSONProducer extends RabbitClient<JSONObject> {
	protected final String exchange;

	public JSONProducer(String endPointName, String exchange)
			throws IOException {
		super(endPointName);
		this.exchange = exchange;
	}

	public void sendMessage(JSONObject object, String routeKey)
			throws IOException {
		channel.queueDeclare(this.queueName, false, false, false, null);
//		channel.basicQos(8);
		channel.basicPublish("", this.queueName, null, this.object2Byte(object));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianwang365.confkeeper.server.amqp.EndPoint#object2Byte(java.io.
	 * Serializable)
	 */
	@Override
	public byte[] object2Byte(JSONObject o) {
		return o.toJSONString().getBytes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianwang365.confkeeper.server.amqp.EndPoint#byte2Object(byte[])
	 */
	@Override
	public JSONObject byte2Object(byte[] bs) {
		return JSONObject.parseObject(new String(bs));
	}

}