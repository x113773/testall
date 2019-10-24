package com.ansel.testall.redis.jedis;

import java.util.List;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

/**
 * RedisList通用工具类
 */
public class RedisList {

	/**
	 * 通过key向list头部添加字符串
	 * 
	 * @param key
	 * @param strs
	 *            可以使一个string 也可以使string数组
	 * @return 返回list的value个数
	 */
	public Long lpush(String key, String... strs) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.lpush(key, strs);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key向list尾部添加字符串
	 * 
	 * @param key
	 * @param strs
	 *            可以使一个string 也可以使string数组
	 * @return 返回list的value个数
	 */
	public Long rpush(String key, String... strs) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.rpush(key, strs);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key在list指定的位置之前或者之后 添加字符串元素
	 * 
	 * @param key
	 * @param where
	 *            LIST_POSITION枚举类型
	 * @param pivot
	 *            list里面的value
	 * @param value
	 *            添加的value
	 * @return
	 */
	public Long insert(String key, LIST_POSITION where, String pivot, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.linsert(key, where, pivot, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key设置list指定下标位置的value 如果下标超过list里面value的个数则报错
	 * 
	 * @param key
	 * @param index
	 *            从0开始
	 * @param value
	 * @return 成功返回OK
	 */
	public String set(String key, Long index, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.lset(key, index, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key从对应的list中删除指定的count个 和 value相同的元素
	 * 
	 * @param key
	 * @param count
	 *            当count为0时删除全部
	 * @param value
	 * @return 返回被删除的个数
	 */
	public Long rem(String key, long count, String value) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.lrem(key, count, value);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key保留list中从strat下标开始到end下标结束的value值
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 成功返回OK
	 */
	public String trim(String key, long start, long end) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.ltrim(key, start, end);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key从list的头部删除一个value,并返回该value
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.lpop(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key从list尾部删除一个value,并返回该元素
	 * 
	 * @param key
	 * @return
	 */
	public String rpop(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.rpop(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value 如果第一个list为空或者不存在则返回null
	 * 
	 * @param srckey
	 * @param dstkey
	 * @return
	 */
	public String rpoplpush(String srckey, String dstkey) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.rpoplpush(srckey, dstkey);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取list中指定下标位置的value
	 * 
	 * @param key
	 * @param index
	 * @return 如果没有返回null
	 */
	public String index(String key, long index) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.lindex(key, index);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回list的长度
	 * 
	 * @param key
	 * @return
	 */
	public Long len(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.llen(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取list指定下标位置的value 如果start 为 0 end 为 -1 则返回全部的list中的value
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> range(String key, long start, long end) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.lrange(key, start, end);
		} finally {
			jedis.close();
		}
	}
}