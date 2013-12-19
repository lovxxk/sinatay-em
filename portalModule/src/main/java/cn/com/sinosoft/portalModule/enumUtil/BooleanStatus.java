package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class BooleanStatus extends EnumDictionary {

	/**
	 * ÊÇ·ñ
	 */
	private static final long serialVersionUID = 594718822157693630L;

	public static final BooleanStatus TRUE = new BooleanStatus("TRUE", "ÊÇ", 1);// ÊÇ

	public static final BooleanStatus FALSE = new BooleanStatus("FALSE", "·ñ", 0);// ·ñ

	public BooleanStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
