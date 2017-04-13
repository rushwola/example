package com.example.controller.common.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/8.
 * 此Filter 处理了js的跨域访问的CORS技术
 */
public class SimpleCORSFilter implements Filter {
    protected static final Logger _log = LoggerFactory.getLogger(SimpleCORSFilter.class);

    private static final String DOMAIN_CORS="domain";

    private String domain="*";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String url= filterConfig.getInitParameter(DOMAIN_CORS);
        if(!StringUtils.isBlank(url)){
            domain=url;
        }
        _log.info("SimpleCORSFilter init!!!!!");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        _log.info("SimpleCORSFilter  过滤了!!!!!");
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", domain);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
        response.setHeader("Access-Control-Expose-Headers", "token");
//		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(req, res);
        _log.info("SimpleCORSFilter end!!!!!");
    }

    @Override
    public void destroy() {
        _log.info("SimpleCORSFilter destroy!!!!!");
    }
}
