package com.ansel.testall.redis.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * RedisString通用工具类
 */
public class RedisString {

	/**
	 * 通过key获取储存在redis中的value 并释放连接
	 * 
	 * @param key
	 * @return 成功返回value 失败返回null
	 */
	public String get(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.get(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 向redis存入key和value,并释放连接资源 如果key已经存在 则覆盖
	 * 
	 * @param key
	 * @param value
	 * @return 成功 返回OK 失败返回 0
	 */
	public String set(String key, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.set(key, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 删除指定的key,也可以传入一个包含key的数组
	 * 
	 * @param keys
	 *            一个key 也可以使 string 数组
	 * @return 返回删除成功的个数
	 */
	public Long del(String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.del(keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key向指定的value值追加值
	 * 
	 * @param key
	 * @param str
	 * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度 异常返回0L
	 */
	public Long append(String key, String str) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.append(key, str);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 设置key value,如果key已经存在则返回0,nx==> not exist
	 * 
	 * @param key
	 * @param value
	 * @return 成功返回1 如果存在 和 发生异常 返回 0
	 */
	public Long setnx(String key, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.setnx(key, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 设置key value并制定这个键值的有效期
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            单位:秒
	 * @return 成功返回OK 失败和异常返回null
	 */
	public String setex(String key, String value, int seconds) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.setex(key, seconds, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过批量的key获取批量的value
	 * 
	 * @param keys
	 *            string数组 也可以是一个key
	 * @return 成功返回value的集合, 失败返回null的集合 ,异常返回空
	 */
	public List<String> mget(String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.mget(keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 批量的设置key:value,可以一个 example: obj.mset(new
	 * String[]{"key2","value1","key2","value2"})
	 * 
	 * @param keysvalues
	 * @return 成功返回OK 失败 异常 返回 null
	 * 
	 */
	public String mset(String... keysvalues) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.mset(keysvalues);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚 example: obj.msetnx(new
	 * String[]{"key2","value1","key2","value2"})
	 * 
	 * @param keysvalues
	 * @return 成功返回1 失败返回0
	 */
	public Long msetnx(String... keysvalues) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.msetnx(keysvalues);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
	 * 
	 * @param key
	 * @return 加值后的结果
	 */
	public Long incr(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.incr(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key给指定的value加值,如果key不存在,则这是value为该值
	 * 
	 * @param key
	 * @param integer
	 * @return
	 */
	public Long incrBy(String key, Long integer) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.incrBy(key, integer);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 对key的值做减减操作,如果key不存在,则设置key为-1
	 * 
	 * @param key
	 * @return
	 */
	public Long decr(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.decr(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 减去指定的值
	 * 
	 * @param key
	 * @param integer
	 * @return
	 */
	public Long decrBy(String key, Long integer) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.decrBy(key, integer);
		} finally {
			jedis.close();
		}
	}
	
	/**
	 * 
	 * 设置缓存
	 * 
	 * @param key 缓存关键字
	 * @param seconds 过期时间
	 * @param value 缓存内容
	 * @return 
	 */
	public String setex(String key,int seconds, String value){
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.setex(key, seconds, value);
		} finally {
			jedis.close();
		}
	}
	
	
}