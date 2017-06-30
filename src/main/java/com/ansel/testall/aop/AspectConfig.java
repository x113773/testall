package com.ansel.testall.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class AspectConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 定义切点：com.ansel.testall.aop包下，带@RestController注解的类。
	 */
	@Pointcut("execution(* com.ansel.testall.aop..*(..)) and @annotation(org.springframework.web.bind.annotation.RestController)")
	public void myPointcut() {
	}

	@Before("myPointcut()")
	public void beforeAdvice() {
		System.out.println("Before--通知方法会在目标方法调用之前执行");
	}

	@After("myPointcut()")
	public void afterAdvice() {
		System.out.println("After--通知方法会在目标方法返回或抛出异常后调用");
	}

	@AfterReturning(returning = "retVal", pointcut = "myPointcut()")
	public void afterReturningAdvice(Object retVal) {
		retVal = retVal + " (@AfterReturning can read the return value, but it can't change the value!)";
		System.out.println("AfterReturning--通知方法会在目标方法返回后调用; retVal = " + retVal);
	}

	@AfterThrowing("myPointcut()")
	public void afterThrowingAdvice() {
		System.out.println("AfterThrowing--通知方法会在目标方法抛出异常后调用");
	}

	/**
	 * 环绕通知
	 * 
	 * @param pjp
	 */
	@Around("myPointcut()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		Object retVal = null;
		try {
			System.out.println("Around--目标方法调用之前执行");

			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();

			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Method method = signature.getMethod(); // 获取被拦截的方法
			String methodName = method.getName(); // 获取被拦截的方法名

			logger.info("requset method name is: " + methodName);
			logger.info("request URL is: " + request.getRequestURL().toString());
			logger.info("request http method: " + request.getMethod());
			logger.info("request arguments are: " + Arrays.toString(pjp.getArgs()));

			retVal = pjp.proceed();
			retVal = retVal + " (@Around can change the return value!)";
			
			System.out.println("Around--目标方法返回后调用");
		} catch (Throwable e) {
			System.out.println("Around--目标方法抛出异常后调用");
		}
		return retVal;
	}
}
