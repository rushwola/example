package com.rulai.framework.generator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;

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
	
	
	/**
	 * @Description (TODO生成service层代码吗)
	 */
	public static void serviceGenerator(){
		
		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = module + "/" + module + "-rpc-api" + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/rpc/api";
		String serviceImplPath = module + "/" + module + "-rpc-service" + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/rpc/service/impl";
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
