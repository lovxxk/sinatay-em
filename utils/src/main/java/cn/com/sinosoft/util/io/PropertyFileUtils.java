package cn.com.sinosoft.util.io;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;

import cn.com.sinosoft.util.string.StringUtils;

public class PropertyFileUtils {

	private static Properties props = null;
	
	public static void init(String propertyFileName) {
		init(propertyFileName, props);
	}
	
	public static void init(URL url) {
		init(url, props);
	}

	public static void init(String propertyFileName, Properties defaultProperties) {
		File propertyFile = new File(propertyFileName);
		if (propertyFile.exists()) {
			try {
				props = new Properties(defaultProperties);
				props.load(FileUtils.openInputStream(propertyFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(propertyFileName + "配置文件没有找到！");
		}
	}
	
	public static void init(URL fileUrl, Properties defaultProperties) {
		try {
			props = new Properties(defaultProperties);
			props.load(fileUrl.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 得到包含配置信息的Properties对象
	 * 
	 * @return Properties对象
	 */
	public Properties getConfigProperty() {
		return props;
	}

	/**
	 * 根据配置节点名得到配置值
	 * 
	 * @param key
	 *            配置节点名
	 * @param defaul
	 *            默认值
	 * @param islog
	 *            是否写日志。如果为true且返回的配置值为null，则写日志
	 * @return 如果有配置值，则返回配置值，否则返回默认值
	 */
	public static String getConfig(String key, String defaul, boolean isLog) {
		String keyVal = props.getProperty(key);
		if (keyVal == null && isLog) {
		}
		if (keyVal == null) {
			keyVal = defaul;
		}
		if (keyVal != null && keyVal.endsWith("\""))
			keyVal = keyVal.replaceAll("\"", "");
		return keyVal;
	}

	/**
	 * 根据配置节点名得到配置值
	 * 
	 * @param key
	 *            配置节点名
	 * @param defaul
	 *            默认值
	 * @return 如果有配置值，则返回配置值，否则返回默认值
	 */
	public static String getConfig(String key, String defaul) {
		return getConfig(key, defaul, false);
	}

	/**
	 * 根据配置节点名得到配置值
	 * 
	 * @param key
	 *            配置节点名
	 * @return 配置值
	 */
	public static String getConfig(String key) {
		return getConfig(key, null, false);
	}

	/**
	 * 根据配置节点名得到配置值
	 * 
	 * @param key
	 *            配置节点名
	 * @param islog
	 *            是否写日志。如果为true且返回的配置值为null，则写日志
	 * @return 配置值
	 */
	public static  String getConfig(String key, boolean islog) {
		return getConfig(key, null, islog);
	}
	
	public static Properties getProperties() {
		return props;
	}
	
	public static String getConfig(String key, String defaultEncoding, String encoding) {
		String value = "";
		try {
			if(StringUtils.isBlank(defaultEncoding)){
				defaultEncoding = "ISO8859-1";
			}
			value = new String(getConfig(key, null, false).getBytes(defaultEncoding), encoding);
		} catch (UnsupportedEncodingException e) {
			//
		}
		return value;
	}
}
