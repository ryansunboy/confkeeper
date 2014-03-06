/**
 * QueueConsumer.java
 */
package com.qianwang365.confkeeper.controller.amqp;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author Yate
 * @date 2014年1月13日
 * @description AMQP
 * @version 1.0
 */
public class JSONConsumer extends RabbitClient<JSONObject> implements Runnable {
	protected final static Logger log = LoggerFactory
			.getLogger(JSONConsumer.class);

	protected final String exchange;
	protected final IoSession session;

	public JSONConsumer(String queueName, String exchange, String host,
			int port, IoSession session) throws IOException {
		super(queueName,host,port);
		this.exchange = exchange;
		this.session = session;
	}

	public void run() {
		try {
			channel.queueDeclare(this.queueName, false, false, false, null);
			// channel.basicQos(2);
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(this.queueName, true, consumer);

			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				session.write(this.byte2Object(delivery.getBody()));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianwang365.confkeeper.server.amqp.EndPoint#object2Byte(java.io.
	 * Serializable)
	 */
	@Override
	public byte[] object2Byte(JSONObject o) {
		return SerializationUtils.serialize(o);
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
