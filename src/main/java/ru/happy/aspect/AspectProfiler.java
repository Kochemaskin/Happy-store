package ru.happy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy

public class AspectProfiler {

    private final Map<String, Integer> methodMap = new HashMap<>();
    private final Map<String, Long> methodTimeMap = new HashMap<>();

    @Pointcut("execution(public * ru.happy..*.*(..))")
    private void allMethodsProfiler() {
    }


    @Pointcut("execution(public * ru.happy.controllers.*.*(..))")
    private void controllersProfiler() {
    }


    @Before("allMethodsProfiler()")
    public void beforeMethods2(JoinPoint joinPoint) {
        int count = 1;
        String methodName = joinPoint.getSignature().toString();
        if (methodMap.containsKey(methodName)) {
            count = methodMap.get(methodName);
            count++;
        }
        methodMap.put(methodName, count);
        Map.Entry<String, Integer> maxEntry = methodMap.entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get();
        log.info("Max use method: " + maxEntry.getKey() + "; Use count: " + maxEntry.getValue());
    }

    @Around("controllersProfiler()")
    public Object controllerTimeProfiler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - start;
        methodTimeMap.put(proceedingJoinPoint.getSignature().toString(), duration);

        Map.Entry<String, Long> maxEntry = methodTimeMap.entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get();
        log.info("Method longer time: " + maxEntry.getKey() + "; Time: " + maxEntry.getValue() + " ms");
        return result;
    }
}
