package com.zll.demo.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	@Pointcut("execution(* com.zll.demo.aop.service.*.*(..))")
	public void pc1() {
		
	}
	
	@Before(value="pc1()")
	public void before(JoinPoint jp) {
		String name=jp.getSignature().getName();
		System.out.println(name+"方法开始执行     》》》");
	}
	
	@After(value="pc1()")
	public void after(JoinPoint jp) {
		String name=jp.getSignature().getName();
		System.out.println(name+"方法执行完成     》》》");
	}
	
	@AfterReturning(value="pc1()", returning="result")
	public void afterReturning(JoinPoint jp, Object result) {
		String name=jp.getSignature().getName();
		System.out.println(name+"....方法返回值为：   "+result);
	}
	
	@AfterThrowing(value="pc1()", throwing="e")
	public void afterThrowing(JoinPoint jp, Exception e) {
		String name=jp.getSignature().getName();
		System.out.println(name+"....方法抛异常了，异常是：   "+e.getMessage());
	}
	
	@Around("pc1()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}
}
