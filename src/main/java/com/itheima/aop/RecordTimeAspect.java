package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Aspect
@Component
public class RecordTimeAspect {

    @Around("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {

        //记录方法执行开始时间
        Long beginTime = Instant.now().toEpochMilli();

        //执行原始方法
        Object result = pjp.proceed();

        //记录方法执行结束时间
        Long endTime = Instant.now().toEpochMilli();
        log.info("方法运行耗时：{}ms",(endTime-beginTime));
        return result;
    }
}
