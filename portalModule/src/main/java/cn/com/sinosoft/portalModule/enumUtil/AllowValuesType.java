package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class AllowValuesType extends EnumDictionary {


	/**
	 * 属性允许值类型
	 */
	public static final AllowValuesType	NOTLIMITED = new AllowValuesType("NOTLIMITED", "不限定", 0);
	
	public static final AllowValuesType	FIXEDVALUE = new AllowValuesType("FIXEDVALUE", "固定", 1);
	
	public static final AllowValuesType	ENUMERATION = new AllowValuesType("ENUMERATION", "枚举", 2);
	
	public static final AllowValuesType	RANGE = new AllowValuesType("RANGE", "范围", 3);
	
	public static final AllowValuesType	UPPERLIMIT = new AllowValuesType("UPPERLIMIT", "上限", 4);
	
	public static final AllowValuesType	LOWERLIMIT = new AllowValuesType("LOWERLIMIT", "下限", 5);


	public AllowValuesType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public AllowValuesType(String enumName, String dataName, String dataENName, Integer value) {
		super(enumName, dataName, dataENName, value);
	}
	
}
