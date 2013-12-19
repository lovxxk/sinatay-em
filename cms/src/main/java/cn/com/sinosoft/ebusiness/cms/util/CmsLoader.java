package cn.com.sinosoft.ebusiness.cms.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.util.string.StringUtils;


public class CmsLoader {
	private static CmsLoader instance;
	//ģ������ö��
	public static final String[] templetTypeArray = {"�����б�", "������ϸ", "��ƪ����", "���ⶨ��", "���˵�", "��ת����Ŀ", "����"};
	
	private static Logger loger = LoggerFactory.getLogger(CmsLoader.class);
			
	private CmsLoader() {
	}

	public static CmsLoader getInstance() {
		if(instance == null) {
			return new CmsLoader();
		}
		return instance;
	}

	
	public static String getCmsProperties(String key) {
		return CmsLoader.getProperties(key, "cms.properties");
	}
	
	/**
	 * ��ȡĳ�����ļ�
	 * @param key
	 * @param propName
	 * @return
	 */
	public static String getProperties(String key, String propName) {
		Properties prop=new Properties();
		String var = "";
		try {
			java.io.InputStream inputStream = CmsLoader.class.getClassLoader().getResourceAsStream("config/"+propName); 
			prop.load(inputStream);
			var = prop.getProperty(key);
			return new String(var);
		} catch (Exception e) {
			loger.info("CMS�������ʧ��:"+StringUtils.exception2String(e));
		}
		return var;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(CmsLoader.getCmsProperties("tplType"));
	}
}
