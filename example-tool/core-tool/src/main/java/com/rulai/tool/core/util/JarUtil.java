package com.rulai.tool.core.util;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by shuzheng on 2016/12/18.
 */
public class JarUtil {

	/**
	 * 解压jar文件到指定目录
	 * 
	 * @param fileName
	 * @param outputPath
	 */
	public static synchronized void decompress(String fileName, String outputPath) {
		// 保证输出路径为目录
		if (!outputPath.endsWith(File.separator)) {
			outputPath += File.separator;
		}
		// 如果不存在输出目录，则创建
		File dir = new File(outputPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 解压到输出目录
		JarFile jf = null;
		try {
			jf = new JarFile(fileName);
			for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements();) {
				JarEntry je = e.nextElement();
				String outFileName = outputPath + je.getName();
				File f = new File(outFileName);
				if (je.isDirectory()) {
					if (!f.exists()) {
						f.mkdirs();
					}
				} else {
					File pf = f.getParentFile();
					if (!pf.exists()) {
						pf.mkdirs();
					}
					InputStream in = jf.getInputStream(je);
					OutputStream out = new BufferedOutputStream(new FileOutputStream(f));
					byte[] buffer = new byte[2048];
					int nBytes;
					while ((nBytes = in.read(buffer)) > 0) {
						out.write(buffer, 0, nBytes);
					}
					out.flush();
					out.close();
					in.close();
				}
			}
		} catch (Exception e) {
			System.out.println("解压" + fileName + "出错！" + e.getMessage());
		} finally {
			if (jf != null) {
				try {
					jf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getJarPath4ClassPath(String fileName) {
		ClassLoader classLoader = JarUtil.class.getClassLoader();
		/**
		 * getResource()方法会去classpath下找这个文件，获取到url resource,
		 * 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
		 */
		URL url = classLoader.getResource(fileName);
		/**
		 * url.getFile() 得到这个文件的绝对路径
		 */
		System.out.println(url.getFile());
		File file = new File(url.getFile());
		System.out.println(file.exists());
		
		return url.getFile();
	}

}
