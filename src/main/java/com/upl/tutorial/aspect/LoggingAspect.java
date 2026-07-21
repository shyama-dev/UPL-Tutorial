package com.upl.tutorial.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Pointcut("execution(* com.upl.tutorial.service..*.*(..)) || execution(* com.upl.tutorial.controller..*.*(..))")
    public void applicationPackagePointcut() {

    }

    @Around("applicationPackagePointcut()")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Enter: {}.{}() with argument(s) = {}", className, methodName, Arrays.toString(args));

        try {

            Object result = joinPoint.proceed();        

            log.info("Exit: {}.{}() with result = {}", className, methodName,  result);
            
            return result;

        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(args), className, methodName);
            throw e;
        } catch (Throwable e) {
             log.error("Exception in {}.{}()", className, methodName, e);
            throw e;
        }
    }
    
}
