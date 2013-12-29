package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class AllowValuesType extends EnumDictionary {


	/**
	 * ��������ֵ����
	 */
	public static final AllowValuesType	NOTLIMITED = new AllowValuesType("NOTLIMITED", "���޶�", 0);
	
	public static final AllowValuesType	FIXEDVALUE = new AllowValuesType("FIXEDVALUE", "�̶�", 1);
	
	public static final AllowValuesType	ENUMERATION = new AllowValuesType("ENUMERATION", "ö��", 2);
	
	public static final AllowValuesType	RANGE = new AllowValuesType("RANGE", "��Χ", 3);
	
	public static final AllowValuesType	UPPERLIMIT = new AllowValuesType("UPPERLIMIT", "����", 4);
	
	public static final AllowValuesType	LOWERLIMIT = new AllowValuesType("LOWERLIMIT", "����", 5);


	public AllowValuesType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public AllowValuesType(String enumName, String dataName, String dataENName, Integer value) {
		super(enumName, dataName, dataENName, value);
	}
	
}
