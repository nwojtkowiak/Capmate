package com.capgemini.jstk.capmates.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ReportTimeExecutionAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportTimeExecutionAspect.class);
    private long startTime;

    @Before("execution(* com.capgemini.jstk.capmates.*.*.*.*(..))")
    public void beforeExecution(JoinPoint joinPoint) {

        startTime = System.currentTimeMillis();

    }

    @After("execution(* com.capgemini.jstk.capmates.*.*.*.*(..))")
    public void afterExecution(JoinPoint joinPoint) {

        long timeTaken = System.currentTimeMillis() - startTime;
        LOGGER.info("Time execution is {}", timeTaken);

    }

}