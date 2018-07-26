package com.capgemini.jstk.capmates.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ReportParameterAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportParameterAspect.class);

    @Before("execution(* com.capgemini.jstk.capmates.*.*.*.*(..))")
    public void beforeWriteParameter(JoinPoint joinPoint) {

        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg : signatureArgs) {
            LOGGER.info("LOGGER# "+joinPoint.getSignature().getName()+": "+signatureArg.toString());
        }
    }



}
