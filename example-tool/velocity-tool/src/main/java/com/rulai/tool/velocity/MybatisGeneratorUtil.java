package com.rulai.tool.velocity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.rulai.tool.db.JdbcUtil;

import static com.rulai.tool.core.util.StrUtil.*;

/**
 * @ClassName MybatisGeneratorUtil
 * @Description TODO(Mybatis 工具类)
 * @author ly
 * @Date 2017年6月8日 下午8:17:36
 * @version 1.0.0
 */
public class MybatisGeneratorUtil {

	// generatorConfig模板路径
	private static String generatorConfig_vm = "/template/generatorConfig.vm";

	/**
	 * @Description (TODO根据模板生成 mybatis 的xml配置文件)
	 * @param jdbc_driver 
	 * @param jdbc_url
	 * @param jdbc_username
	 * @param jdbc_password
	 * @param database  数据库名
	 * @param table_prefix 表前缀
	 * @return
	 * @throws IOException
	 */
	public String generatorConfig(String jdbc_driver, String jdbc_url, String jdbc_username, String jdbc_password,
			String database,String table_prefix) throws IOException {

		String outFilepath = "generatorConfig.xml";
		Map<String, Object> contextParmas = new HashMap<String, Object>();
		
		List<Map<String, Object>> tables = new ArrayList<>();

		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database
				+ "' AND table_name LIKE '" + table_prefix + "_%';";
		JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
		List<Map> result = jdbcUtil.selectByParams(sql, null);
		Map<String, Object> table;
		for (Map map : result) {
			System.out.println(map.get("TABLE_NAME"));
			table=null;
			table = new HashMap<>();
			table.put("table_name", map.get("TABLE_NAME"));
			table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
			tables.add(table);
		}
		jdbcUtil.release();

		contextParmas.put("tables", tables);
		contextParmas.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.model");
		contextParmas.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
		contextParmas.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
		contextParmas.put("targetProject", targetProject);
		contextParmas.put("targetProject_sqlMap", targetProject_sqlMap);
		contextParmas.put("generator_jdbc_password", AESUtil.AESDecode(jdbc_password));
		contextParmas.put("last_insert_id_tables", last_insert_id_tables);

		VelocityUtil.generate(generatorConfig_vm, outFilepath, contextParmas);
		return outFilepath;

	}

}
