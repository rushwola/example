package com.rulai.framework.common.util;

import java.io.UnsupportedEncodingException;

import org.omg.IOP.Encoding;

import com.rulai.framework.common.plugin.EncryptPropertyPlaceholderConfigurer;
import com.rulai.tool.crypto.SecureUtil;

import sun.misc.BASE64Encoder;

public class Helper {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String pw="qding";
		byte[]  bytes=SecureUtil.aes(EncryptPropertyPlaceholderConfigurer.ENCODE_RULES.getBytes()).encrypt(pw);
		
		String str=new String(new BASE64Encoder().encode(bytes));
	
		System.out.println(str);
	
	}

}
