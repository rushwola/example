package com.rulai.framework.common.plugin;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.rulai.tool.crypto.SecureUtil;

/**
 * 支持加密配置文件插件 Created by ZhangShuzheng on 2017/2/4.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	public static final String ENCODE_RULES = "12345!@#$%rulai#";

	private String[] propertyNames = { "master.jdbc.password", "slave.jdbc.password", "generator.jdbc.password",
			"master.redis.password" };

	/**
	 * 解密指定propertyName的加密属性值
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		for (String p : propertyNames) {
			if (p.equalsIgnoreCase(propertyName)) {
				// return AESUtil.AESDecode(propertyValue);
				String str = "";
				str = SecureUtil.aes(EncryptPropertyPlaceholderConfigurer.ENCODE_RULES.getBytes()).decryptBase64(propertyValue);
				return str;

			}
		}
		return super.convertProperty(propertyName, propertyValue);
	}

}
