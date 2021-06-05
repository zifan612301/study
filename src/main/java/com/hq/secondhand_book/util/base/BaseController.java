package com.hq.secondhand_book.util.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Autowired
    protected HttpServletResponse httpServletResponse;

    public HttpSession getHttpSession() {
        return getHttpSession(true);
    }

    public HttpSession getHttpSession(boolean request) {
        return httpServletRequest.getSession(request);
    }

}
