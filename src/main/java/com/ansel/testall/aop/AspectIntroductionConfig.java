package com.ansel.testall.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectIntroductionConfig {

	/**
	 * DeclareParents注解所标注的静态属性指明了要引入了接;
	 * value属性指定了哪种类型的bean要引入该接口;
	 * defaultImpl属性指定了为引入功能提供实现的类。
	 */
	@DeclareParents(value = "com.ansel.testall.aop.AopService+", defaultImpl = IntroductionServiceImpl.class)
	public static IntroductionService introductionService;
}
