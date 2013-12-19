package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class SystemType extends EnumDictionary {

	/**
	 * 系统类型
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final SystemType INNER_SYSTEM = new SystemType("INNER_SYSTEM", "内部系统", 1); 
	
	public static final SystemType EXTERNAL_SYSTEM = new SystemType("EXTERNAL_SYSTEM ", "外部系统", 2);
	
	public SystemType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
