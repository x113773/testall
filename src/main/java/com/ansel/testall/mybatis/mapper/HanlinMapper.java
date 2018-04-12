package com.ansel.testall.mybatis.mapper;

import java.util.List;

import com.ansel.testall.mybatis.model.Hanlin;
import com.ansel.testall.mybatis.model.User;

public interface HanlinMapper {

	void insertSelective(Hanlin hanlin);

	List<Hanlin> selectByHanlin(Hanlin hanlin);

	void updateByPrimaryKeySelective(Hanlin hanlin);
}