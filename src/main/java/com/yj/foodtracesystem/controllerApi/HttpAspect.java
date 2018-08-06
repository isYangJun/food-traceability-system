package com.yj.foodtracesystem.controllerApi;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 6:41 2018/8/1
 */

@Component
@Aspect
public class HttpAspect {
    /*public final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut(value = "execution(public * com.yj.foodtracesystem.controllerApi.ReturnRes.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        //url  method ip 类方法 参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURL());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());
        System.out.println("before");
    }


    @After("log()")
    public void doAfter() {
        System.out.println("after");
    }

    @AfterReturning(returning = "", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response=", object.toString());
    }

*/
}
