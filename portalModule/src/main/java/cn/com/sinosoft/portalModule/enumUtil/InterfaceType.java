package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class InterfaceType extends EnumDictionary {

	/**
	 * 接口类型
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final InterfaceType SERVICE_INTERFACE = new InterfaceType("SERVICE_INTERFACE", "服务接口", 1); 
	
	public static final InterfaceType REQUEST_INTERFACE = new InterfaceType("REQUEST_INTERFACE ", "请求接口", 2);
	
	public InterfaceType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
