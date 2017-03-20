package com.example.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: leiyang
 * @Description: 基类
 * @Date 14:27 2017/3/19
 */
public abstract class BaseController {

    protected static final Logger _log = LoggerFactory.getLogger(BaseController.class);


    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpServletRequest request;
}
