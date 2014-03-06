/**
 * EndPoint.java
 */
package com.qianwang365.confkeeper.controller.amqp;

import java.io.IOException;
import java.io.Serializable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Yate
 * @date 2014年1月13日
 * @description AMQP
 * @version 1.0
 */
public abstract class RabbitClient<O extends Serializable> {

	protected Channel channel;
	protected Connection connection;
	protected String queueName;

	public RabbitClient(String queueName, String host, int port)
			throws IOException {
		this.queueName = queueName;

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);

		connection = factory.newConnection();
		channel = connection.createChannel();
	}

	/**
	 * Turn off the channel and connection.
	 * 
	 * @throws IOException
	 */
	public final void close() throws IOException {
		this.channel.close();
		this.connection.close();
	}

	public abstract byte[] object2Byte(final O o);

	public abstract O byte2Object(final byte[] bs);

}
