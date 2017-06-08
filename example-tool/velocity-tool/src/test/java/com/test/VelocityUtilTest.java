package com.test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rulai.tool.velocity.VelocityUtil;

public class VelocityUtilTest {
	
	private static final Logger _log=LoggerFactory.getLogger(VelocityUtilTest.class);

	public static void main(String[] args) throws IOException {
		Map<String,Object> contextParmas =new HashMap<String,Object>();
		contextParmas.put("name", "leiyang");
		contextParmas.put("date", new Date());
		String  resutl=VelocityUtil.generate("template/Hellovelocity.vm", contextParmas);
		
		System.out.println(resutl);
		
		VelocityUtil.generate("template/Hellovelocity.vm", "outputFilePath", contextParmas);

	}

}
