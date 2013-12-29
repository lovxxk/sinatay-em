package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class FactorType extends EnumDictionary {

	private static final long serialVersionUID = -1068865207905452306L;

	/**
	 * 接口规则类型
	 */

	public static final FactorType OTHER = new FactorType("OTHER", "其他", 0);
	
	public static final FactorType SYSTEM_INTERFACE = new FactorType("SYSTEM_INTERFACE", "外部对接系统接口", 1);
	
	public static final FactorType INTERFACE_VERIFICATION = new FactorType("INTERFACE_VERIFICATION", "接口验证", 2);

	public FactorType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public FactorType(String enumName, String dataName, String dataENName, Integer value) {
		super(enumName, dataName, dataENName, value);
	}
	
}
