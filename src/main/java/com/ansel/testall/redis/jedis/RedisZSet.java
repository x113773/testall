package com.ansel.testall.redis.jedis;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * RedisZSet通用工具类
 */
public class RedisZSet {

	/**
	 * 通过key向zset中添加value,score,其中score就是用来排序的 如果该value已经存在则根据score更新元素
	 * 
	 * @param key
	 * @param scoreMembers
	 * @return
	 */
	public Long add(String key, Map<String, Double> scoreMembers) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zadd(key, scoreMembers);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key向zset中添加value,score,其中score就是用来排序的 如果该value已经存在则根据score更新元素
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public Long add(String key, double score, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zadd(key, score, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key删除在zset中指定的value
	 * 
	 * @param key
	 * @param members
	 *            可以使一个string 也可以是一个string数组
	 * @return
	 */
	public Long rem(String key, String... members) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zrem(key, members);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key增加该zset中value的score的值
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public Double incrby(String key, double score, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zincrby(key, score, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回zset中value的排名 下标从小到大排序
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long rank(String key, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zrank(key, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回zset中value的排名 下标从大到小排序
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long revrank(String key, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zrevrank(key, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key将获取score从start到end中zset的value socre从大到小排序 当start为0 end为-1时返回全部
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> revRange(String key, long start, long end) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zrevrange(key, start, end);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回指定score内zset中的value
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 */
	public Set<String> rangeByScore(String key, String max, String min) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zrevrangeByScore(key, max, min);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回指定score内zset中的value
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 */
	public Set<String> rangeByScore(String key, double max, double min) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zrevrangeByScore(key, max, min);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 返回指定区间内zset中value的数量
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long count(String key, String min, String max) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zcount(key, min, max);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回zset中的value个数
	 * 
	 * @param key
	 * @return
	 */
	public Long card(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zcard(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取zset中value的score值
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Double score(String key, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zscore(key, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key删除给定区间内的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long remrangeByRank(String key, long start, long end) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zremrangeByRank(key, start, end);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key删除指定score内的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long remrangeByScore(String key, double start, double end) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.zremrangeByScore(key, start, end);
		} finally {
			jedis.close();
		}
	}
}