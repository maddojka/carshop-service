package com.soroko.carshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Aspect
public class LoggableAspect {
    @Pointcut("within(@com.soroko.carshop.annotations.Loggable *) && execution( * * (..))")
    public void annotatedByLoggable() {
    }

    @Around("annotatedByLoggable()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Logging method " + joinPoint.getSignature());
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime() - start;
        LOGGER.info("Execution of method " + joinPoint.getSignature() + " took " + end + " ms");
        return result;
    }
}
