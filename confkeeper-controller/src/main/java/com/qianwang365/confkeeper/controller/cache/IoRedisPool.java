/**
 * IoRedis.java
 */
package com.qianwang365.confkeeper.controller.cache;

import java.util.NoSuchElementException;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import redis.client.RedisClient;

/**
 * @author Yate
 * @date 2013年9月25日
 * @description 通过池管理对象
 * @version 1.0
 */
public class IoRedisPool implements ObjectPool<RedisClient> {
	protected PoolableObjectFactory<RedisClient> factory;
	protected ObjectPool<RedisClient> pool;

	public IoRedisPool(String host, int port) throws Exception {
		this.factory = new IoRedisFactory(host, port);
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		config.maxActive = 10;
		config.maxIdle = 15;
		config.minIdle = 5;
		this.pool = new GenericObjectPool<RedisClient>(factory, config);
	}

	public IoRedisPool(String host, int port, int maxActive, int maxIdle,
			int minIdle) throws Exception {
		this.factory = new IoRedisFactory(host, port);
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		config.maxActive = maxActive;
		config.maxIdle = maxIdle;
		config.minIdle = minIdle;
		this.pool = new GenericObjectPool<RedisClient>(factory, config);
	}

	@Override
	public RedisClient borrowObject() throws Exception, NoSuchElementException,
			IllegalStateException {
		return pool.borrowObject();
	}

	@Override
	public void returnObject(RedisClient obj) throws Exception {
		pool.returnObject(obj);
	}

	@Override
	public void invalidateObject(RedisClient obj) throws Exception {
		pool.invalidateObject(obj);
	}

	@Override
	public void addObject() throws Exception, IllegalStateException,
			UnsupportedOperationException {
		pool.addObject();
	}

	@Override
	public int getNumIdle() throws UnsupportedOperationException {
		return pool.getNumIdle();
	}

	@Override
	public int getNumActive() throws UnsupportedOperationException {
		return pool.getNumActive();
	}

	@Override
	public void clear() throws Exception, UnsupportedOperationException {
		pool.clear();
	}

	@Override
	public void close() throws Exception {
		pool.close();
	}

	@Override
	public void setFactory(PoolableObjectFactory<RedisClient> factory)
			throws IllegalStateException, UnsupportedOperationException {
		throw new UnsupportedOperationException(
				"IoRedisPool setFactory method not unsupported!");
	}
}
