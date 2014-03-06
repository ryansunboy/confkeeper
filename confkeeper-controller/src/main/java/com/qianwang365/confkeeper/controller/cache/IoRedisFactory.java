/**
 * IoRedis.java
 */
package com.qianwang365.confkeeper.controller.cache;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.client.RedisClient;

/**
 * @author Yate
 * @date 2013年9月26日
 * @description TODO
 * @version 1.0
 */
public class IoRedisFactory implements PoolableObjectFactory<RedisClient> {
	protected static final Logger log = LoggerFactory
			.getLogger(IoRedisFactory.class);

	protected String host;
	protected int port;

	public IoRedisFactory(String host, int port) throws Exception {
		if (port < 0 || port > 65535 || StringUtils.isEmpty(host))
			throw new Exception("Redis host or port error.");
		this.host = host;
		this.port = port;
	}

	@Override
	public RedisClient makeObject() throws Exception {
		log.debug("IoRedisFactory create new RedisClient object.");
		return new RedisClient(host, port);
	}

	@Override
	public void destroyObject(RedisClient obj) throws Exception {
		log.debug("Destroy Object " + obj);
		obj = null;
	}

	@Override
	public boolean validateObject(RedisClient obj) {
		log.debug("Validate Object " + obj);
		return obj != null ? true : false;
	}

	@Override
	public void activateObject(RedisClient obj) throws Exception {
		log.debug("Activate Object " + obj);
	}

	@Override
	public void passivateObject(RedisClient obj) throws Exception {
		log.debug("Passivating Object " + obj);
	}
}
