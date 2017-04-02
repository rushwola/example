package com.example.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.example.controller.common.constant.ErrorConstant;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: leiyang
 * @Description: 接入层统一异常处理
 * @Date 15:04 2017/3/19
 */
@Service
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        ModelAndView mv = new ModelAndView();


        /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("code", "1000001");
        attributes.put("msg", e.getMessage());
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
