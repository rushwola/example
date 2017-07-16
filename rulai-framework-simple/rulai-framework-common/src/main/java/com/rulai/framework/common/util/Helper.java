package com.rulai.framework.common.util;

import java.io.UnsupportedEncodingException;

import org.omg.IOP.Encoding;

import com.rulai.framework.common.plugin.EncryptPropertyPlaceholderConfigurer;
import com.rulai.tool.core.lang.Base64;
import com.rulai.tool.crypto.SecureUtil;

import sun.misc.BASE64Encoder;

public class Helper {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String pw = "qding";
		String str = SecureUtil.aes(EncryptPropertyPlaceholderConfigurer.ENCODE_RULES.getBytes()).encryptBase64(pw);

		System.out.println("加密后字符串:" + str);

		String str1 = SecureUtil.aes(EncryptPropertyPlaceholderConfigurer.ENCODE_RULES.getBytes()).decryptBase64(str);

		System.out.println("解密字符串:" + str1);
	}

}
