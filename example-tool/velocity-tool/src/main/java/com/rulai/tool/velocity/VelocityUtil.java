package com.rulai.tool.velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @ClassName VelocityUtil
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author ly
 * @Date 2017年6月8日 下午8:21:27
 * @version 1.0.0
 */
public class VelocityUtil {

	/**
	 * @Field @DEFAULT_ENCODING : TODO(Ĭ�ϱ���)
	 */
	private static final String DEFAULT_ENCODING = "utf-8";


	public static void generate(String inputVmFilePath, String outputFilePath, Map<String, Object> contextParmas)
			throws IOException {
		generate(inputVmFilePath, outputFilePath, contextParmas, DEFAULT_ENCODING);
	}

	/**
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param inputVmFilePath
	 * @param outputFilePath
	 * @param contextParmas
	 * @param encoding
	 * @throws IOException
	 */
	public static void generate(String inputVmFilePath, String outputFilePath, Map<String, Object> contextParmas,
			String encoding) throws IOException {
		FileWriterWithEncoding writer = null;
		try {
//			VelocityEngine ve = new VelocityEngine();
//			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//			ve.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
//			ve.init();
			Properties p = new Properties();
			p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			Velocity.init(p);
			Template template = Velocity.getTemplate(inputVmFilePath, encoding);
			File outputFile = new File(outputFilePath);
			writer = new FileWriterWithEncoding(outputFile, encoding);

			VelocityContext context = new VelocityContext();
			if (contextParmas != null) {
				for (Map.Entry<String, Object> entry : contextParmas.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					context.put(key, value);
				}
			}

			template.merge(context, writer);

			writer.close();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param inputVmFilePath
	 * @param contextParmas
	 * @return
	 * @throws IOException
	 */
	public static String generate(String inputVmFilePath, Map<String, Object> contextParmas) throws IOException {
		return generate(inputVmFilePath, contextParmas, DEFAULT_ENCODING);
	}

	public static String generate(String inputVmFilePath, Map<String, Object> contextParmas, String encoding)
			throws IOException {
		Properties p = new Properties();
		p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(p);
		Template template = Velocity.getTemplate(inputVmFilePath, encoding);
		StringWriter write = new StringWriter();

		VelocityContext context = new VelocityContext();
		if (contextParmas != null) {
			for (Map.Entry<String, Object> entry : contextParmas.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				context.put(key, value);
			}
		}

		template.merge(context, write);

		return write.toString();

	}

}
