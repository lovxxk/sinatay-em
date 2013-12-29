package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class FactorValueType extends EnumDictionary {

	private static final long serialVersionUID = -1068865207905452306L;

	/**
	 * 接口规则因子值类型
	 */

	public static final FactorValueType OTHER = new FactorValueType("OTHER", "其他", 0);
	
	public static final FactorValueType INTERFACE_PROCESS = new FactorValueType("INTERFACE_PROCESS", "接口处理", 1);
	
	public static final FactorValueType INTERFACE_VERIFICATION_PROCESS = new FactorValueType("INTERFACE_VERIFICATION_PROCESS", "接口验证处理", 2);

	public FactorValueType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public FactorValueType(String enumName, String dataName, String dataENName, Integer value) {
		super(enumName, dataName, dataENName, value);
	}
	
}
