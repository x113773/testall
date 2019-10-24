package com.ansel.testall.aop;

import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {

	@Override
	public String myOwnMethod() {
		return "this method is from AopService";
	}

}
