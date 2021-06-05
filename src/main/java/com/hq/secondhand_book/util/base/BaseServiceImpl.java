package com.hq.secondhand_book.util.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public abstract class BaseServiceImpl {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public WebApplicationContext webApplicationContext;
}
