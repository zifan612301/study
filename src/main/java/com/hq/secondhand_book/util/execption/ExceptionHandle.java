package com.hq.secondhand_book.util.execption;

import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
public class ExceptionHandle {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 统一异常处理
     */

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResultResp handle(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        e.printStackTrace(writer);
        logger.error(sw.getBuffer().toString());
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            if (apiException.getResponseEnum() == null) {
                return Response.info(apiException.getErr(), apiException.getMessage());
            }
            return Response.info(((ApiException) e).getResponseEnum());
        }
        return Response.serverErr(e.getMessage());
    }

}
