package com.ansel.testall.redis.jedis;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * RedisSet通用工具类
 */
public class RedisSet {

	/**
	 * 通过key向指定的set中添加value
	 * 
	 * @param key
	 * @param members
	 *            可以是一个String 也可以是一个String数组
	 * @return 添加成功的个数
	 */
	public Long add(String key, String... members) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sadd(key, members);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key删除set中对应的value值
	 * 
	 * @param key
	 * @param members
	 *            可以是一个String 也可以是一个String数组
	 * @return 删除的个数
	 */
	public Long rem(String key, String... members) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.srem(key, members);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key随机删除一个set中的value并返回该值
	 * 
	 * @param key
	 * @return
	 */
	public String pop(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.spop(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取set中的差集 以第一个set为标准
	 * 
	 * @param keys
	 *            可以使一个string 则返回set中所有的value 也可以是string数组
	 * @return
	 */
	public Set<String> diff(String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sdiff(keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取set中的差集并存入到另一个key中 以第一个set为标准
	 * 
	 * @param dstkey
	 *            差集存入的key
	 * @param keys
	 *            可以使一个string 则返回set中所有的value 也可以是string数组
	 * @return
	 */
	public Long diffStore(String dstkey, String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sdiffstore(dstkey, keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取指定set中的交集
	 * 
	 * @param keys
	 *            可以使一个string 也可以是一个string数组
	 * @return
	 */
	public Set<String> inter(String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sinter(keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取指定set中的交集 并将结果存入新的set中
	 * 
	 * @param dstkey
	 * @param keys
	 *            可以使一个string 也可以是一个string数组
	 * @return
	 */
	public Long interStore(String dstkey, String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sinterstore(dstkey, keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回所有set的并集
	 * 
	 * @param keys
	 *            可以使一个string 也可以是一个string数组
	 * @return
	 */
	public Set<String> union(String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sunion(keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key返回所有set的并集,并存入到新的set中
	 * 
	 * @param dstkey
	 * @param keys
	 *            可以使一个string 也可以是一个string数组
	 * @return
	 */
	public Long unionstore(String dstkey, String... keys) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sunionstore(dstkey, keys);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key将set中的value移除并添加到第二个set中
	 * 
	 * @param srckey
	 *            需要移除的
	 * @param dstkey
	 *            添加的
	 * @param member
	 *            set中的value
	 * @return
	 */
	public Long move(String srckey, String dstkey, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.smove(srckey, dstkey, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取set中value的个数
	 * 
	 * @param key
	 * @return
	 */
	public Long card(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.scard(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key判断value是否是set中的元素
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Boolean isMember(String key, String member) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.sismember(key, member);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取set中随机的value,不删除元素
	 * 
	 * @param key
	 * @return
	 */
	public String randMember(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.srandmember(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 通过key获取set中所有的value
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> members(String key) {
		Jedis jedis = RedisPoolUtil.getInstance();
		try {
			return jedis.smembers(key);
		} finally {
			jedis.close();
		}
	}

}