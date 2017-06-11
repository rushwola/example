package com.rulai.tool.velocity.entry;

import java.util.Map;

/**
 * @ClassName MybatisConfigParam
 * @Description TODO(mybatis config 文件参数)
 * @author ly
 * @Date 2017年6月10日 下午1:52:34
 * @version 1.0.0
 */
public class MybatisConfigParam {
	
	/**
	 * @Field @jdbc_driver : TODO(jdbc 驱动)
	 */
	private String jdbc_driver;
	
	/**
	 * @Field @jdbc_url : TODO(jdbc url)
	 */
	private String jdbc_url;
	
	/**
	 * @Field @jdbc_username : TODO(jdbc  用户名)
	 */
	private String jdbc_username;
	
	/**
	 * @Field @jdbc_password : TODO(jdbc  密码)
	 */
	private String jdbc_password;
	
	/**
	 * @Field @database : TODO( 数据库名)
	 */
	private String database;
	
	/**
	 * @Field @table_prefix : TODO(表前缀)
	 */
	private String table_prefix;
	
	/**
	 * @Field @package_name : TODO(包名)
	 */
	private  String package_name;
	
	/**
	 * @Field @targetProject : TODO(mybatis  接口类放的项目)
	 */
	private  String  targetProject;
	
	
	/**
	 * @Field @targetProject_sqlMap : TODO(mybatis sqlmap 放的项目名)
	 */
	private  String  targetProject_sqlMap;
	
	/**
	 * @Field @last_insert_id_tables : TODO(表名与主键的对应关系)
	 */
	private Map<String, String>  last_insert_id_tables;

	public String getJdbc_driver() {
		return jdbc_driver;
	}

	public void setJdbc_driver(String jdbc_driver) {
		this.jdbc_driver = jdbc_driver;
	}

	public String getJdbc_url() {
		return jdbc_url;
	}

	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	public String getJdbc_username() {
		return jdbc_username;
	}

	public void setJdbc_username(String jdbc_username) {
		this.jdbc_username = jdbc_username;
	}

	public String getJdbc_password() {
		return jdbc_password;
	}

	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTable_prefix() {
		return table_prefix;
	}

	public void setTable_prefix(String table_prefix) {
		this.table_prefix = table_prefix;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public String getTargetProject() {
		return targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public String getTargetProject_sqlMap() {
		return targetProject_sqlMap;
	}

	public void setTargetProject_sqlMap(String targetProject_sqlMap) {
		this.targetProject_sqlMap = targetProject_sqlMap;
	}

	public Map<String, String> getLast_insert_id_tables() {
		return last_insert_id_tables;
	}

	public void setLast_insert_id_tables(Map<String, String> last_insert_id_tables) {
		this.last_insert_id_tables = last_insert_id_tables;
	}

	
	
}
