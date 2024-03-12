package com.example.demo;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingConfig {

    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.example.demo..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) {
        final Logger logger = LoggerFactory.getLogger(getClass().getName());
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final String className = methodSignature.getDeclaringType().getSimpleName();
        final String methodName = methodSignature.getName();
        final StopWatch stopWatch = new StopWatch();
        Object result;

        //Measure method execution time
        logger.info(String.format("Attempting to invoke `%s.%s`...", className, methodName));
        stopWatch.start();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            result = t;
        } finally {
            stopWatch.stop();
        }
        final long elapsedTime = stopWatch.getTotalTimeMillis();
        logger.info(String.format("`%s.%s` resulted in `%s` :: executed in %s ms", className, methodName, result, elapsedTime));
        return result;
    }
}