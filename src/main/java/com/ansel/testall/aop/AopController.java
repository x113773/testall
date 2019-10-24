package com.ansel.testall.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

	/**
	 * 只注入AopService
	 */
	@Autowired
	AopService aopService;

	/**
	 * 测试AOP通知(Advice，也译作 增强)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aop", method = RequestMethod.GET)
	public String testAop() {
		return "this is a AOP Advice test.";
	}

	/**
	 * 测试AOP引入(Introduction)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aop/introdution", method = RequestMethod.GET)
	public String testAopIntroduction() {
		System.out.println(aopService.myOwnMethod());
		//接口类型转换
		IntroductionService introductionService = (IntroductionService) aopService;
		System.out.println(introductionService.IntroductionMethod());
		return "this is a AOP Introduction test.";
	}
}
