package com.rulai.framework.generator;

import static com.rulai.tool.core.util.StrUtil.lineToHump;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;

import com.rulai.tool.core.io.FileUtil;
import com.rulai.tool.core.util.PropertiesFileUtil;
import com.rulai.tool.core.util.StrUtil;
import com.rulai.tool.db.JdbcUtil;
import com.rulai.tool.system.SystemUtil;
import com.rulai.tool.velocity.VelocityUtil;
import com.rulai.tool.velocity.simple.VelocitySimpleUtil;

/**
 * @ClassName GeneratorUtil
 * @Description TODO(框架代码生成器)
 * @author ly
 * @Date 2017年6月18日 下午4:40:41
 * @version 1.0.0
 */
public class FrameworkGenerator {
	
	//generator.properties
	private static final String   DEFAULT_PRO_FILENAME="generator";
	
	/**
	 * @Field @DEFAULT_TABLE_NAME_PREFIX_VALUE : table_name_prefix 默认值
	 */
	private static final String   DEFAULT_TABLE_NAME_PREFIX_VALUE="_";
	
	private static String   DEFAULT_SERVICE_VM_VALUE = "/template/Service.vm";
	// ServiceMock模板路径
	private static String DEFAULT_SERVICE_MOCK_VALUE = "/template/ServiceMock.vm";
	// ServiceImpl模板路径
	private static String DEFAULT_SERVICE_IMPL_VM = "/template/ServiceImpl.vm";
	
	private String propertieName;
	
	/************属性文件中的  key************************/
	/**
	 * @Field @TABLE_PREFIX : 表名前缀参数名
	 */
	private static  final String TABLE_PREFIX="table_prefix";
	
	/**
	 * @Field @TABLE_NAME_PREFIX : 表名分隔符
	 */
	private static  final String TABLE_NAME_PREFIX="table_name_prefix";
	
	/**
	 * @Field @JDBC_DRIVER : jdbc drive 字符串
	 */
	private static  final String JDBC_DRIVER="jdbc_driver";
	
	private static  final String JDBC_URL="jdbc_url";
	
	private static final String   JDBC_USERNAME="jdbc_username";
	
	private static final String  JDBC_PASSWORD ="jdbc_password";
	
	private static final String  DATABASE ="database";
	
	private static final String  MODULE ="module";
	private static final String  PACKAGE_NAME ="package_name";
	private static final String  SERVICE_VM="service_vm";
	private static final String  SERVICE_MOCK_VM="serviceMock_vm";
	private static final String  SERVICE_IMPL_VM="serviceImpl_vm";
	private static final String  BASE_DIR="baseDir";
	
	
	/************属性文件中的  key************************/
	
	
	
	
	public FrameworkGenerator(String propertieName){
		this.propertieName=propertieName;
	}
	
	public FrameworkGenerator(){
		this.propertieName=DEFAULT_PRO_FILENAME;
	}
	
	private  String  createSql(){
		String  tablePrefix=  PropertiesFileUtil.getInstance(propertieName).get(TABLE_PREFIX);
		
		String tableNamePrefix= PropertiesFileUtil.getInstance(propertieName).get(TABLE_NAME_PREFIX,DEFAULT_TABLE_NAME_PREFIX_VALUE);
		String database= PropertiesFileUtil.getInstance(propertieName).get(DATABASE);
		String tablename="%";
		if(!StrUtil.isBlank(tablePrefix)){
			tablename=tablePrefix+tableNamePrefix+"%";
		}
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database
		+ "' AND table_name LIKE '" + tablename+"'";
		
		return sql;
	}

	public  List<Map<String, Object>> createMode() throws SQLException {

		List<Map<String, Object>> tables = new ArrayList<>();

		String sqlStr=createSql();
		String jdbcDriver=PropertiesFileUtil.getInstance(propertieName).get(JDBC_DRIVER);
		String jdbcUrl=PropertiesFileUtil.getInstance(propertieName).get(JDBC_URL);
		String jdbcUsername=PropertiesFileUtil.getInstance(propertieName).get(JDBC_USERNAME);
		String jdbcPassword=PropertiesFileUtil.getInstance(propertieName).get(JDBC_PASSWORD);
		
		JdbcUtil jdbcUtil = new JdbcUtil(jdbcDriver, jdbcUrl, jdbcUsername,jdbcPassword);
		List<Map> result = jdbcUtil.selectByParams(sqlStr, null);
		Map<String, Object> table;
		for (Map map : result) {
			System.out.println(map.get("TABLE_NAME"));
			table = null;
			table = new HashMap<>();
			table.put("table_name", map.get("TABLE_NAME"));
			table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
			tables.add(table);
		}
		jdbcUtil.release();
		return tables;
	}

	/**
	 * @throws Exception 
	 * @Description (TODO生成service层代码吗)
	 */
	public  void serviceGenerator() throws Exception {
		
		String  module=PropertiesFileUtil.getInstance(propertieName).get(MODULE);
		String  package_name=PropertiesFileUtil.getInstance(propertieName).get(PACKAGE_NAME);
		
		List<Map<String, Object>>  tables=this.createMode();
		
		
		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = module + "/" + module + "-facade" + "/src/main/java/"
				+ package_name.replaceAll("\\.", "/") + "/facade";
		String serviceImplPath = module + "/" + module + "-service" + "/src/main/java/"
				+ package_name.replaceAll("\\.", "/") + "/service/impl";
		
		String  service_vm=PropertiesFileUtil.getInstance(propertieName).get(SERVICE_VM);
		String  serviceMock_vm=PropertiesFileUtil.getInstance(propertieName).get(SERVICE_MOCK_VM);
		String  serviceImpl_vm=PropertiesFileUtil.getInstance(propertieName).get(SERVICE_IMPL_VM);
		String baseDir=PropertiesFileUtil.getInstance(propertieName).get(BASE_DIR);
		
		for (int i = 0; i < tables.size(); i++) {
			String model = StrUtil.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String service = servicePath + "/" + model + "Service.java";
			service=baseDir+SystemUtil.get(SystemUtil.FILE_SEPRATOR)  +service;
			String serviceMock = servicePath + "/" + model + "ServiceMock.java";
			serviceMock=baseDir+SystemUtil.get(SystemUtil.FILE_SEPRATOR)+serviceMock;
			
			String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
			serviceImpl=baseDir+SystemUtil.get(SystemUtil.FILE_SEPRATOR)+serviceImpl;
			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				Map<String, Object> context = new HashMap<String,Object>();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm, service, context);
				System.out.println(service);
			}
			// 生成serviceMock
			File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				Map<String, Object> context = new HashMap<String,Object>();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceMock_vm, serviceMock, context);
				System.out.println(serviceMock);
			}
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				Map<String, Object> context = new HashMap<String,Object>();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("mapper", StrUtil.lowerFirst(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
				System.out.println(serviceImpl);
			}
		}
		System.out.println("========== 结束生成Service ==========");
	}

}
