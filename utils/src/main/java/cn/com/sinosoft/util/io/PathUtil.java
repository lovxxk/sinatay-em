package cn.com.sinosoft.util.io;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.com.sinosoft.util.string.StringUtils;

public class PathUtil {
	
	/**
	 * ��ȡweb��Ŀ��Web Content Ŀ¼
	 * @return
	 */
	public static String webContentPath(){
		try {
			String path = PathUtil.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * ��ȡ��ľ���·��
	 * @param clazz
	 * @return
	 */
	public static String getClassAbsolutePath(Class<?> clazz) {
		return clazz.getResource("").getPath();
	}
	
	/**
	 * ��ȡ��ľ���·��
	 * @param clazz
	 * @return
	 */
	public static String getClassBuildPath() {
		return PathUtil.class.getClassLoader().getResource("").getPath();
	}
	/**
	 * ��ȡ����·��
	 * @return
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
}
