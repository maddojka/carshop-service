package com.soroko.auditstarter.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * This class consist aspect which evaluate time of execution of methods
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@Aspect
public class LoggableAspect {
    @Pointcut("within(@com.soroko.auditstarter.annotations.EnableLoggable *) && execution( * * (..))")
    public void annotatedByLoggable() {
    }

    @Around("annotatedByLoggable()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Logging method " + joinPoint.getSignature());
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime() - start;
        log.info("Execution of method " + joinPoint.getSignature() + " took " + end + " ms");
        return result;
    }
}
