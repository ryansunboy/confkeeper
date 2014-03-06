/**
 * EndPoint.java
 */
package com.qianwang365.confkeeper.server.amqp;

import java.io.IOException;
import java.io.Serializable;

import com.qianwang365.confkeeper.server.utils.PropertyUtils;
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

	public RabbitClient(String queueName) throws IOException {
		this.queueName = queueName;

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(PropertyUtils.getInstance().getValue("amqp.server"));
		factory.setPort(Integer.parseInt(PropertyUtils.getInstance().getValue(
				"amqp.port")));

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
