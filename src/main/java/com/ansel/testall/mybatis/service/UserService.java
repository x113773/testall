package com.ansel.testall.mybatis.service;

import java.util.List;

import com.ansel.testall.mybatis.model.User;

public interface UserService {
	int insert(User user);

	int insertSelective(User user);
	
	int updateByPrimaryKeySelective(User user);

	User getUserById(Integer userId);

	List<User> getUserByUsername(String username);

	void deleteByPrimaryKey(Integer userId);
}
