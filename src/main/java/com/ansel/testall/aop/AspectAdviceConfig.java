package com.ansel.testall.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AspectAdviceConfig {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 定义切点指示器：com.ansel.testall.aop包下，带@RestController注解的类。
	 */
	@Pointcut("execution(* com.ansel.testall.aop..*(..)) and @annotation(org.springframework.web.bind.annotation.RestController)")
	public void myPointcut() {
	}

	/**
	 * 前置通知，在目标方法完成之后调用通知，此时不会关 心方法的输出是什么
	 */
	@Before("myPointcut()")
	public void beforeAdvice() {
		System.out.println("Before--通知方法会在目标方法调用之前执行");
	}

	/**
	 * 后置通知，在目标方法完成之后调用通知，此时不会关 心方法的输出是什么
	 */
	@After("myPointcut()")
	public void afterAdvice() {
		System.out.println("After--通知方法会在目标方法返回或抛出异常后调用");
	}

	/**
	 * 返回通知，在目标方法成功执行之后调用，可以获得目标方法的返回值，但不能修改（修改也不影响方法的返回值）
	 * 
	 * @param jp
	 *            JoinPoint接口，可以获得连接点的一些信息
	 * 
	 * @param retVal
	 *            目标方法返回值，和jp一样会由spring自动传入
	 */
	@AfterReturning(returning = "retVal", pointcut = "myPointcut()")
	public void afterReturningAdvice(JoinPoint jp, Object retVal) {
		retVal = retVal + " (@AfterReturning can read the return value, but it can't change the value!)";
		System.out.println("AfterReturning--通知方法会在目标方法返回后调用; retVal = " + retVal);
		System.out.println(jp.toLongString());
	}

	/**
	 * 异常通知，在目标方法抛出异常后调用通知
	 */

	@AfterThrowing("myPointcut()")
	public void afterThrowingAdvice() {
		System.out.println("AfterThrowing--通知方法会在目标方法抛出异常后调用");
	}

	/**
	 * 环绕通知，可以在目标方法调用前后，自定义执行内容。可以修改目标方法的返回值
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
