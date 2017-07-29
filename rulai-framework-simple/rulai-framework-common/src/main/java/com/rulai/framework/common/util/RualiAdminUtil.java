package com.rulai.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.rulai.tool.core.io.resource.ClassPathResource;
import com.rulai.tool.core.util.JarUtil;
import com.rulai.tool.core.util.PropertiesFileUtil;

import javax.servlet.ServletContext;

/**
 * 启动解压zhengAdmin-x.x.x.jar到resources目录
 * Created by shuzheng on 2016/12/18.
 */
public class RualiAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(RualiAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压ruali-admin-ui =====");
        String version = PropertiesFileUtil.getInstance("rulai-admin-ui-client").get("rulai.admin.ui.version");
        _log.info("rulai-admin-ui.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/rulai-framework-admin-ui-" + version + ".jar");
        if(jarPath==null){
        	//从classpath路径中获取
        	jarPath=JarUtil.getJarPath4ClassPath("rulai-framework-admin-ui-" + version + ".jar");
        }
       _log.info("servletContext.getRealPath:{}",servletContext.getRealPath("/"));
        _log.info("rulai-admin-ui.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/rulai-admin-ui";
        _log.info("rulai-admin-ui.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压rulai-admin-ui完成 =====");
    }

}
