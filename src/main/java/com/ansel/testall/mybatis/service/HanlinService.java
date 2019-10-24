package com.ansel.testall.mybatis.service;

import java.util.List;
import java.util.Map;

import com.ansel.testall.mybatis.model.Hanlin;

public interface HanlinService {

	void insertSelective(Hanlin hanlin);

	List<Hanlin> selectByHanlin(Map<String, Object> param);

	void updateByPrimaryKeySelective(Hanlin hl);

	void deleteByPrimaryKey(String id);
}
