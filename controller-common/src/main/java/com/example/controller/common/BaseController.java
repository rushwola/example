package com.example.controller.common;

import com.example.controller.common.util.ParamValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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


    /*
     * Bind出错，这里是最高优先级的处理
     */
    @ExceptionHandler({BindException.class})
    public ModelAndView handleBindException(final BindException e) {

        return ParamValidateUtils.getParamErrors(e);
    }
}
