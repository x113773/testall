package com.ansel.testall.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.ansel.testall.mybatis.model.Hanlin;
import com.ansel.testall.mybatis.model.User;

public interface HanlinMapper {

	void insertSelective(Hanlin hanlin);

	List<Hanlin> selectByHanlin(Map<String, Object> param);

	void updateByPrimaryKeySelective(Hanlin hanlin);

	void deleteByPrimaryKey(String id);
}