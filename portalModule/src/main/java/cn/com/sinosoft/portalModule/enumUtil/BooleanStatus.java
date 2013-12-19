package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class BooleanStatus extends EnumDictionary {

	/**
	 * �Ƿ�
	 */
	private static final long serialVersionUID = 594718822157693630L;

	public static final BooleanStatus TRUE = new BooleanStatus("TRUE", "��", 1);// ��

	public static final BooleanStatus FALSE = new BooleanStatus("FALSE", "��", 0);// ��

	public BooleanStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
