package com.revature.nova.aspects;

import com.revature.nova.services.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * This class provides error catching and logging advice to all functional methods
 * Excludes: configs and filters
 *
 * @date 11/22/2021
 * @author Kollier Martin
 */
@Aspect
@Component
public class LoggerAspect {
    private final LoggerService loggerService;

    public LoggerAspect(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Pointcut("execution(* com.revature.nova.*..*(..)) && !within(com.revature.nova.filters..*) && !within(com.revature.nova.configs..*)")
    public void logAll() {

    }

    @Around("execution(* com.revature.nova.*..*(..)) && !within(com.revature.nova.filters..*) && !within(com.revature.nova.configs..*)")
    public Object logAroundAll(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Exception ignored){

        }

        return null;
    }

    @AfterThrowing(pointcut = "logAll()", throwing = "e")
    public void logMethodException(JoinPoint jp, Throwable e) {
        String methodSig = extractMethodSignature(jp);
        loggerService.writeLog(String.format("%s was thrown in method %s with message: %s", e.getClass().getSimpleName(), methodSig, e.getMessage()), 3);
    }

    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
    }
}
