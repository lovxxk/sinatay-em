package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class BooleanStatus extends EnumDictionary {

	/**
	 * ÊÇ·ñ
	 */
	private static final long serialVersionUID = 594718822157693630L;

	public static final BooleanStatus TRUE = new BooleanStatus("TRUE", "ÊÇ", 1, "1", "1");// ÊÇ

	public static final BooleanStatus FALSE = new BooleanStatus("FALSE", "·ñ", 0, "0", "0");// ·ñ

	public BooleanStatus(String enumName, String dataName, Integer intValue) {
		super(enumName, dataName, intValue);
	}

	public BooleanStatus(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

}
