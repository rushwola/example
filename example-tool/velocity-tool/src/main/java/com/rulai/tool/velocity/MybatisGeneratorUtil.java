package com.rulai.tool.velocity;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.rulai.tool.db.JdbcUtil;
import com.rulai.tool.velocity.entry.MybatisConfigParam;

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
	 * @Description (TODOTODO根据模板生成 mybatis 的xml配置文件)
	 * @param param  参数
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String generatorConfig(MybatisConfigParam param) throws IOException, SQLException {
		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		String outFilepath = "generatorConfig.xml";
		Map<String, Object> contextParmas = new HashMap<String, Object>();

		List<Map<String, Object>> tables = new ArrayList<>();

		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + param.getDatabase()
				+ "' AND table_name LIKE '" + param.getTable_prefix() + "_%';";
		JdbcUtil jdbcUtil = new JdbcUtil(param.getJdbc_driver(), param.getJdbc_url(), param.getJdbc_username(), param.getJdbc_password());
		List<Map> result = jdbcUtil.selectByParams(sql, null);
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

		contextParmas.put("tables", tables);
		contextParmas.put("generator_javaModelGenerator_targetPackage", param.getPackage_name() + ".dao.model");
		contextParmas.put("generator_sqlMapGenerator_targetPackage", param.getPackage_name() + ".dao.mapper");
		contextParmas.put("generator_javaClientGenerator_targetPackage", param.getPackage_name() + ".dao.mapper");
		contextParmas.put("targetProject", param.getTargetProject());
		contextParmas.put("targetProject_sqlMap", param.getTargetProject_sqlMap());
		contextParmas.put("generator_jdbc_password", param.getJdbc_password());
		contextParmas.put("generator_jdbc_driver", param.getJdbc_driver());
		contextParmas.put("generator_jdbc_url", param.getJdbc_url().replace("&", "&amp;"));//应对xml文件中的转义
		contextParmas.put("generator_jdbc_username", param.getJdbc_username());
		contextParmas.put("last_insert_id_tables", param.getLast_insert_id_tables());

		VelocityUtil.generate(generatorConfig_vm, outFilepath, contextParmas);
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");
		return outFilepath;

	}
	
	
	/**
	 * @Description (TODO生成mybatis 模板代码)
	 * @param param
	 * @throws IOException
	 * @throws SQLException
	 * @throws XMLParserException
	 * @throws InvalidConfigurationException
	 * @throws InterruptedException
	 */
	public static void mybatisGenerator(MybatisConfigParam param) throws IOException, SQLException, XMLParserException, InvalidConfigurationException, InterruptedException {
		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
		String module_path=generatorConfig(param);
		File configFile = new File(module_path);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		
		if(configFile.exists()){
			configFile.delete();
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");
	}

}
