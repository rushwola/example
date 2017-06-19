package com.rulai.framework.generator;

import static com.rulai.tool.core.util.StrUtil.lineToHump;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;

import com.rulai.tool.core.util.PropertiesFileUtil;
import com.rulai.tool.core.util.StrUtil;
import com.rulai.tool.db.JdbcUtil;
import com.zheng.common.util.StringUtil;
import com.zheng.common.util.VelocityUtil;

/**
 * @ClassName GeneratorUtil
 * @Description TODO(框架代码生成器)
 * @author ly
 * @Date 2017年6月18日 下午4:40:41
 * @version 1.0.0
 */
public class FrameworkGenerator {
	
	private static final String   DEFAULT_PRO_FILENAME="generator.properties";
	
	/**
	 * @Field @DEFAULT_TABLE_NAME_PREFIX_VALUE : table_name_prefix 默认值
	 */
	private static final String   DEFAULT_TABLE_NAME_PREFIX_VALUE="_";
	
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
	
	
	
	private FrameworkGenerator(String propertieName){
		this.propertieName=propertieName;
	}
	
	private FrameworkGenerator(){
		this.propertieName=DEFAULT_PRO_FILENAME;
	}
	
	private  String  createSql(){
		String  tablePrefix=  PropertiesFileUtil.getInstance(DEFAULT_PRO_FILENAME).get(TABLE_PREFIX);
		
		String tableNamePrefix= PropertiesFileUtil.getInstance(TABLE_NAME_PREFIX).get(TABLE_NAME_PREFIX,DEFAULT_TABLE_NAME_PREFIX_VALUE);
		String tablename="%";
		if(!StrUtil.isBlank(tablePrefix)){
			tablename=tablePrefix+tableNamePrefix+"%";
		}
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + param.getDatabase()
		+ "' AND table_name LIKE '" + tablename;
	}

	public  List<Map> createMode() {

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
		return result;
	}

	/**
	 * @Description (TODO生成service层代码吗)
	 */
	public  void serviceGenerator() {
		
		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = module + "/" + module + "-rpc-api" + "/src/main/java/"
				+ package_name.replaceAll("\\.", "/") + "/rpc/api";
		String serviceImplPath = module + "/" + module + "-rpc-service" + "/src/main/java/"
				+ package_name.replaceAll("\\.", "/") + "/rpc/service/impl";
		for (int i = 0; i < tables.size(); i++) {
			String model = StringUtil.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String service = servicePath + "/" + model + "Service.java";
			String serviceMock = servicePath + "/" + model + "ServiceMock.java";
			String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm, service, context);
				System.out.println(service);
			}
			// 生成serviceMock
			File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceMock_vm, serviceMock, context);
				System.out.println(serviceMock);
			}
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
				System.out.println(serviceImpl);
			}
		}
		System.out.println("========== 结束生成Service ==========");
	}

}
