package cn.com.sinosoft.util.io;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import cn.com.sinosoft.util.string.StringUtils;
@SuppressWarnings({"rawtypes"})
public class FilePathUtil {
	
	/**
	 * ��ȡweb��Ŀ��Web Content Ŀ¼
	 * @return
	 */
	public static String webContentPath(){
		try {
			String path = FilePathUtil.class.getResource("").getFile();
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
	
	public static String getWebInfClassesPath(){
		try {
			String path = FilePathUtil.class.getResource("").getFile();
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
				return path + "/WEB-INF/classes";
			}
			return null;
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
	public static String getClassAbsolutePath(Class clazz) {
		return clazz.getResource("").getPath();
	}
	
	
	/**
	 * ��ȡ��ľ���·��
	 * @param clazz
	 * @return
	 */
	public static String getClassBuildPath() {
		return FilePathUtil.class.getClassLoader().getResource("").getPath();
	}
	/**
	 * ��ȡ����·��
	 * @return
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * �������ȡ�ļ�
	 * @param clazz ��
	 * @param file �ļ��м��ļ���
	 * @return �ļ���URL��ַ
	 */
	public static URL getResourceFilePath(Class clazz, String file) {
		return clazz.getResource(file);
	}
	
	
	/***
	 * �������ȡ�ļ�
	 * @param clazz ��
	 * @param fileDirectory �ļ���
	 * @param fileName �ļ���
	 * @return �ļ���URL��ַ
	 */
	public static URL getResourceFilePath(Class clazz, String fileDirectory, String fileName) {
		return clazz.getResource(fileDirectory + fileName);
	}
	
	/**
	 * ��ȡ��Ŀ����·��
	 * @param clzss ������class
	 * @param dir ·��
	 * @param tagDir ��ǩ·��
	 * @return
	 */
	public static String getProjectLocalPath(Class clzss, final String dir, String tagDir) {
		try {
			String path = clzss.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			
			Properties props=System.getProperties(); //���ϵͳ���Լ�  
			String osName = props.getProperty("os.name"); //����ϵͳ����  
			if (osName.startsWith("Windows")) {
				path = props.getProperty("user.dir").replace("\\", "/") + tagDir;//"/src/main/webapp";
			} else {
				path += dir;
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
