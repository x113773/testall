package com.ansel.testall.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * RedisConfig 初始化连接池
 */
public abstract class RedisPoolUtil {
	private static JedisPool pool = null;
	static {
		pool = new JedisPool(new JedisPoolConfig(), "192.168.10.128", Protocol.DEFAULT_PORT, 60000, "123qwe");
	}

	/**
	 * 从非切片池中获取Jedis实例。
	 */
	public static Jedis getInstance() {
		return pool.getResource();
	}
}