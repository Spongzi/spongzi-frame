package com.spongzi.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author spong
 * @date 2023/11/08
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "#{log.aspect.enable}", havingValue = "true", matchIfMissing = true)
public class LogAspect {

    @Pointcut("execution(* com.spongzi.*.controller.*Controller.*(..)) " +
            "||" +
            "execution(* com.spongzi.*.service.*Service.*(..))")
    private void pointcut() {

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] reqArgs = pjp.getArgs();
        String req = new Gson().toJson(reqArgs);
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String messageName = methodSignature.getDeclaringType().getName() + "." + methodSignature.getName();
        log.info("{}, req: {}", messageName, req);
        long startTime = System.currentTimeMillis();
        Object responseObj = pjp.proceed();// 一定要直接抛出，不用try-catch
        String resp = new Gson().toJson(responseObj);
        long endTime = System.currentTimeMillis();
        log.info("{}, response: {}, costTime: {}", messageName, resp, endTime - startTime);
    }
}
