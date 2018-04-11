package com.ansel.testall.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ansel.testall.mybatis.mapper.HanlinMapper;
import com.ansel.testall.mybatis.model.Hanlin;
import com.ansel.testall.mybatis.service.HanlinService;

@Transactional(rollbackFor = RuntimeException.class)
@Service
public class HanlinServiceImpl implements HanlinService {

	@Autowired
	HanlinMapper hanlinMapper;

	@Override
	public void insertSelective(Hanlin hanlin) {
		hanlinMapper.insertSelective(hanlin);
	}

	@Override
	public List<Hanlin> selectByHanlin(Hanlin hanlin) {
		return hanlinMapper.selectByHanlin(hanlin);
	}

}
