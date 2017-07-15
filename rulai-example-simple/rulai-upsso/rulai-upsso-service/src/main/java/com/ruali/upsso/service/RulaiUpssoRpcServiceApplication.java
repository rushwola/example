package com.ruali.upsso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by ZhangShuzheng on 2017/2/3.
 */
public class RulaiUpssoRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(RulaiUpssoRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> rulai-upsso-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> rulai-upsso-rpc-service 启动完成 <<<<<");
	}

}
