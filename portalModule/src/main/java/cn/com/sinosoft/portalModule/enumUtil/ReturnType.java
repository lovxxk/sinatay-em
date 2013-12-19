package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class ReturnType extends EnumDictionary {

	/**
	 * 接口类型
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final ReturnType RETURN_NONE = new ReturnType("RETURN_NONE ", "无返回交易对象", 0);
	
	public static final ReturnType RETURN_BUSINESSDATUM = new ReturnType("RETURN_BUSINESSDATUM", "返回业务对象", 1); 
	
	public static final ReturnType RETURN_TRANSOBJ = new ReturnType("RETURN_TRANSOBJ ", "返回交易对象", 2);
	
	public static final ReturnType RETURN_RESPONSE_XML = new ReturnType("RETURN_RESPONSE_XML ", "返回应答报文", 3);
	
	public ReturnType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
