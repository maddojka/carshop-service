package com.soroko.carshop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * This class consist aspect which evaluate time of execution of methods
 * @author yuriy.soroko
 * @version 1.0
 */
@Aspect
@Component
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
