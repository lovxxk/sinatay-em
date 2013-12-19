package cn.com.sinosoft.ebusiness.ali.config;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import cn.com.sinosoft.ebusiness.ali.ldc.LDCode;
import cn.com.sinosoft.ebusiness.ali.ldc.LDCodeKey;
import cn.com.sinosoft.ebusiness.ali.ldc.LDCodeMapper;
import cn.com.sinosoft.util.spring.SpringContextUtil;

public class ConfigUtil {
	
	

	private LDCodeMapper ldCodeMapper; 
	
	public static String BUNDLE_NAME = "application";

	private static final ResourceBundle res = ResourceBundle
			.getBundle(BUNDLE_NAME);
	
	public ConfigUtil() {
		super();
		ldCodeMapper = (LDCodeMapper) SpringContextUtil.getBean("ldcodeMapper");
	}

	public static String getString(String key) {
		return res.getString(key).trim();
	}
	public static String getWithPlaceHolder(String key,String ph){
		 return MessageFormat.format(res.getString(key),ph);
	}
	
	public  String getConfig(String codeType,String code){
		LDCodeKey key = new LDCodeKey();
		key.setCodeType(codeType);
		key.setCode(code);
		LDCode ldCode = ldCodeMapper.selectByPrimaryKey(key);
		return ldCode.getCodeName();
	}
	
}

