package com.ansel.testall.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ansel.testall.mybatis.mapper.UserMapper;
import com.ansel.testall.mybatis.model.User;
import com.ansel.testall.mybatis.service.UserService;

@Transactional(rollbackFor = RuntimeException.class)
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public User getUserById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<User> getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteByPrimaryKey(Integer userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

}
