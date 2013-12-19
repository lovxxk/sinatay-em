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
			System.out.println(propertyFileName + "�����ļ�û���ҵ���");
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
	 * �õ�����������Ϣ��Properties����
	 * 
	 * @return Properties����
	 */
	public Properties getConfigProperty() {
		return props;
	}

	/**
	 * �������ýڵ����õ�����ֵ
	 * 
	 * @param key
	 *            ���ýڵ���
	 * @param defaul
	 *            Ĭ��ֵ
	 * @param islog
	 *            �Ƿ�д��־�����Ϊtrue�ҷ��ص�����ֵΪnull����д��־
	 * @return ���������ֵ���򷵻�����ֵ�����򷵻�Ĭ��ֵ
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
	 * �������ýڵ����õ�����ֵ
	 * 
	 * @param key
	 *            ���ýڵ���
	 * @param defaul
	 *            Ĭ��ֵ
	 * @return ���������ֵ���򷵻�����ֵ�����򷵻�Ĭ��ֵ
	 */
	public static String getConfig(String key, String defaul) {
		return getConfig(key, defaul, false);
	}

	/**
	 * �������ýڵ����õ�����ֵ
	 * 
	 * @param key
	 *            ���ýڵ���
	 * @return ����ֵ
	 */
	public static String getConfig(String key) {
		return getConfig(key, null, false);
	}

	/**
	 * �������ýڵ����õ�����ֵ
	 * 
	 * @param key
	 *            ���ýڵ���
	 * @param islog
	 *            �Ƿ�д��־�����Ϊtrue�ҷ��ص�����ֵΪnull����д��־
	 * @return ����ֵ
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
