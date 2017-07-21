package com.ansel.testall.redis.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	

	@RequestMapping(value = "/redis/user/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable() Integer userId) {
		User user = userService.getUserById(userId);
		return user;
	}

	@RequestMapping(value = "/redis/user", method = RequestMethod.POST)
	public String insertUser(@RequestBody User user) {
		userService.insertSelective(user);
		return "201";
	}
}
