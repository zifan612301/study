package com.hq.secondhand_book.util.base;

import com.hq.secondhand_book.util.resp.ResponseErr;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class ApiErrorController extends BasicErrorController {

    public ApiErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        Map<String, Object> resultResp = new HashMap<>();
        resultResp.put("err", ResponseErr.SERVER_ERROR.getErr());
        resultResp.put("msg", "http error");
        resultResp.put("data", body);

        return new ResponseEntity<>(resultResp, status);
    }
}
