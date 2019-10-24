package com.ansel.testall.aop;

import org.springframework.stereotype.Service;

@Service
public class IntroductionServiceImpl implements IntroductionService {

	@Override
	public String IntroductionMethod() {
		return "this method from Introduction.";
	}

}
