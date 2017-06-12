package com.xieziming.tap.testcase.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Suny on 6/11/17.
 */
@Aspect
@Configuration
@Slf4j
public class VisitorAnalytics {
    @Pointcut("execution(* com.xieziming.tap.testcase.controller.*Controller.*(..))")
    public void visit() {
    }

    @Around("visit()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String uri = request.getRequestURI();
        String method = request.getMethod();

        Object result = pjp.proceed();
        log.info("request: {}, method: {}, return: {}", uri, method, result.getClass().getSimpleName());
        return result;
    }
}
