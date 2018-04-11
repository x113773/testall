package com.ansel.testall.mybatis.service;

import java.util.List;

import com.ansel.testall.mybatis.model.Hanlin;

public interface HanlinService {

	void insertSelective(Hanlin hanlin);

	List<Hanlin> selectByHanlin(Hanlin hl);
}
