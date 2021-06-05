package com.hq.secondhand_book.util.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

/**
 * 记录接口信息
 */
@Aspect
@Component
public class HttpAspect {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.hq.secondhand_book.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURL());
        logger.info("method={}", request.getMethod());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());

        StringBuilder sb = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            sb.append(" ");
            sb.append(name);
            sb.append("=");
            sb.append(value);
        }

        logger.info("param={} ", sb.toString());

        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Parameter[] parameters = method.getParameters();
            for (int j = 0; j < parameters.length; j++) {
                Parameter parameter = parameters[j];
                Annotation[] annotations = parameter.getDeclaredAnnotations();
                for (int k = 0; k < annotations.length; k++) {
                    Annotation annotation = annotations[k];
                    if (annotation instanceof RequestBody) {
                        logger.info("body={}", JSON.toJSON(joinPoint.getArgs()[j]));
                    }
                }
            }
        }
    }

    @After("log()")
    public void doAfter() {
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        if (object != null) {
            logger.info("response: {}", JSON.toJSON(object));
        } else {
            logger.info("response={}", "object is null");
        }
    }
}
