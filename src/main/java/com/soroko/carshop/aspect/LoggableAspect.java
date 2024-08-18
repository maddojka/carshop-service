package com.soroko.carshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author yuriy.soroko
 */
@Aspect
public class LoggableAspect {
    @Pointcut("within(@com.soroko.carshop.annotations.Loggable *) && execution( * * (..))")
    public void annotatedByLoggable() {}

    @Around("annotatedByLoggable()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logging method " + joinPoint.getSignature());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis() - start;
        System.out.println("Execution of method " + joinPoint.getSignature() + " took " + end + " ms");
        return result;
    }
}
