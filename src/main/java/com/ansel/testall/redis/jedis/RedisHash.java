package com.ansel.testall.redis.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * RedisHash工具类
 */
public class RedisHash {

	/**
	 * 通过key给field设置指定的值,如果key不存在,则先创建
	 * 
	 * @param key
	 * @param field
	 *            字段
	 * @param value
	 * @return 如果存在返回0 异常返回null
	 */
	public Long set(String key, String field, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hset(key, field, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long setnx(String key, String field, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hsetnx(key, field, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key同时设置 hash的多个field
	 * 
	 * @param key
	 * @param hash
	 * @return 返回OK 异常返回null
	 */
	public String mset(String key, Map<String, String> hash) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hmset(key, hash);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key 和 field 获取指定的 value
	 * 
	 * @param key
	 * @param field
	 * @return 没有返回null
	 */
	public String get(String key, String field) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hget(key, field);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
	 * 
	 * @param key
	 * @param fields
	 *            可以使 一个String 也可以是 String数组
	 * @return
	 */
	public List<String> mget(String key, String... fields) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hmget(key, fields);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key给指定的field的value加上给定的值
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long incrby(String key, String field, long value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hincrBy(key, field, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key和field判断是否有指定的value存在
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Boolean exists(String key, String field) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hexists(key, field);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回field的数量
	 * 
	 * @param key
	 * @return
	 */
	public Long len(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hlen(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key 删除指定的 field
	 * 
	 * @param key
	 * @param fields
	 *            可以是 一个 field 也可以是 一个数组
	 * @return
	 */
	public Long del(String key, String... fields) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hdel(key, fields);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回所有的field
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> keys(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hkeys(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回所有和key有关的value
	 * 
	 * @param key
	 * @return
	 */
	public List<String> vals(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hvals(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取所有的field和value
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getall(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.hgetAll(key);
		} finally {
			jedis.close();
		}
	}
}