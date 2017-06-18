package com.ruali.upsso.dao;

import java.util.HashMap;
import java.util.Map;

import com.rulai.tool.velocity.MybatisGeneratorUtil;
import com.rulai.tool.velocity.entry.MybatisConfigParam;

/**
 * @ClassName Generator
 * @Description TODO(生成代码类)
 * @author ly
 * @Date 2017年6月12日 下午11:03:31
 * @version 1.0.0
 */
public class Generator {
	
private static final String  DATABASE="rulai";
	
	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	
	private static final String JDBC_PASSWORD="qding";
	
	private static final String JDBC_URL="jdbc:mysql://120.24.181.119:3307/rulai?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";

	private static final String JDBC_USERNAME="qding";
	
	private static final String TABLE_PREFIX = "upsso_";
	
	private static final String PACKAGE_NAME = "com.rulai.tool.velocity";
	
	private static final Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
	
	private static final String TARGET_PROJECT = "F:/example-git/rulai-example-simple/rulai-upsso/rulai-upsso-dao";//velocity-tool
	
	private static final String TARGET_PROJECT_SQLMAP = "F:/example-git/rulai-example-simple/rulai-upsso/rulai-upsso-dao";
	static {
		LAST_INSERT_ID_TABLES.put("upsso_user", "user_id");
	}

	/**
	 * 自动代码生成
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisConfigParam param=new MybatisConfigParam();
		param.setDatabase(DATABASE);
		param.setJdbc_driver(JDBC_DRIVER);
		param.setJdbc_password(JDBC_PASSWORD);
		param.setJdbc_url(JDBC_URL);
		param.setJdbc_username(JDBC_USERNAME);
		param.setLast_insert_id_tables(LAST_INSERT_ID_TABLES);
		param.setPackage_name(PACKAGE_NAME);
		param.setTargetProject(TARGET_PROJECT);
		param.setTable_prefix(TABLE_PREFIX);
		param.setTargetProject_sqlMap(TARGET_PROJECT_SQLMAP);
		
		
		MybatisGeneratorUtil.mybatisGenerator(param);
	}

}
