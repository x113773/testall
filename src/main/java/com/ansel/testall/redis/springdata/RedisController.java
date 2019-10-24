package com.ansel.testall.redis.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansel.testall.helper.SystemUtil;
import com.ansel.testall.mybatis.model.User;
import com.ansel.testall.mybatis.service.UserService;

@RestController
public class RedisController {
	@Autowired
	UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping(value = "/redis/string", method = RequestMethod.GET)
	public void insertString() {
		stringRedisTemplate.opsForValue().set("stringKey", "stringValue");
	}

	@RequestMapping(value = "/redis/string/object", method = RequestMethod.GET)
	public void insertStringObject() {
		User user = new User();
		user.setUserId(1);
		user.setUsername("user1");
		user.setPassword("password1");
		redisTemplate.opsForValue().set("stringKeyObject", user);
	}

	@RequestMapping(value = "/redis/string/object/get", method = RequestMethod.GET)
	public User getStringObject() {
		User user = (User) redisTemplate.opsForValue().get("stringKeyObject");
		return user;
	}
	
	@CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
	@RequestMapping(value = "/redis/user", method = RequestMethod.POST)
	public User insertUser(@RequestBody User user) {
		user.setPassword(SystemUtil.MD5(user.getPassword()));
		userService.insertSelective(user);
		return user;
	}
	
	@Cacheable(value = "user")
	@RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer userId) {
		User user = userService.getUserById(userId);
		return user;
	}
	//#root.caches[0].name:当前被调用方法所使用的Cache, 即"user"
	@CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
	@RequestMapping(value = "/redis/user", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		user.setPassword(SystemUtil.MD5(user.getPassword()));
		userService.updateByPrimaryKeySelective(user);
		return user;
	}
	
//	@CacheEvict(value = "user", key = "#root.caches[0].name + ':' + #user.userId")
//	@RequestMapping(value = "/redis/user", method = RequestMethod.PUT)
//	public void updateUser(@RequestBody User user) {
//		userService.updateByPrimaryKeySelective(user);
//	}
	
	@CacheEvict(value = "user")
	@RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteByPrimaryKey(userId);
	}

}
