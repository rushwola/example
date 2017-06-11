package com.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import com.rulai.tool.velocity.MybatisGeneratorUtil;
import com.rulai.tool.velocity.entry.MybatisConfigParam;

/**
 * @ClassName MybatisGeneratorUtilTest
 * @Description TODO(MybatisGeneratorUtil 测试)
 * @author ly
 * @Date 2017年6月10日 下午1:48:11
 * @version 1.0.0
 */
public class MybatisGeneratorUtilTest {
	
	private static final String  DATABASE="zheng";
	
	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	
	private static final String JDBC_PASSWORD="qding";
	
	private static final String JDBC_URL="jdbc:mysql://120.24.181.119:3307/zheng?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";

	private static final String JDBC_USERNAME="qding";
	
	private static final String TABLE_PREFIX = "upms_";
	
	private static final String PACKAGE_NAME = "com.rulai.tool.velocity";
	
	private static final Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
	
	private static final String TARGET_PROJECT = "F:/example-git/example-tool/velocity-tool";//velocity-tool
	
	private static final String TARGET_PROJECT_SQLMAP = "F:/example-git/example-tool/velocity-tool";
	static {
		LAST_INSERT_ID_TABLES.put("upms_user", "user_id");
	}
	
	public static void main(String[] args) throws IOException, SQLException, XMLParserException, InvalidConfigurationException, InterruptedException {
		
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
		
//		MybatisGeneratorUtil.generatorConfig(param);
		
		MybatisGeneratorUtil.mybatisGenerator(param);
		
		
	}

}
