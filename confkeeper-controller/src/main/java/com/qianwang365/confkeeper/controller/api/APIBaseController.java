/**
 * APIBaseController.java
 */
package com.qianwang365.confkeeper.controller.api;

import java.util.NoSuchElementException;

import javax.annotation.Resource;

import redis.client.RedisClient;

import com.qianwang365.confkeeper.controller.BaseController;
import com.qianwang365.confkeeper.controller.cache.IoRedisPool;

/**
 * @author Yate
 * @date Jan 13, 2014
 * @description TODO
 * @version 1.0
 */
public abstract class APIBaseController extends BaseController {

	@Resource(name = "redisPool")
	protected IoRedisPool redisPool;

	public final RedisClient getRedis() throws NoSuchElementException,
			IllegalStateException, Exception {
		return this.redisPool.borrowObject();
	}
}
