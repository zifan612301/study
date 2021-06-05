package com.hq.secondhand_book.util.aop;

import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResponseErr;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * 验证RequestBody
 */
@Aspect
@Component
public class ValidateAspect {

    @Pointcut("execution(public * com.hq.secondhand_book.controller.*.*(..))")
    public void validate() {
    }

    @Around("validate()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        BindingResult bindingResult = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length != 0){
            for (Object object : args) {
                if (object instanceof BindingResult){
                    bindingResult = (BindingResult)object;
                    break;
                }
            }
        }
        if (bindingResult != null && bindingResult.hasErrors()){
            StringBuffer sb = new StringBuffer();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                sb.append(((FieldError) objectError).getField() + " : ").append(objectError.getDefaultMessage() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return Response.info(ResponseErr.DATA_ERROR.getErr(), sb.toString());
        }
        return joinPoint.proceed();
    }
}
