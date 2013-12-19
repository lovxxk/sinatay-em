package cn.com.sinosoft.ebusiness.service.user.common.enums;

import cn.com.sinosoft.enums.EnumDictionary;

public class Gender extends EnumDictionary {

	/**
	 * �Ա�ö������
	 */
	private static final long serialVersionUID = 8624611319646255247L;

	public static final Gender MALE = new Gender("MALE", "����", 1, "0", "1");// ����

	public static final Gender FEMALE = new Gender("FEMALE", "Ů��", 2, "1", "2"); // Ů��

	public static final Gender UNKNOWN = new Gender("UNKNOWN", "����", 3, "2", "3");// ����

	public Gender(String enumName, String dataName, Integer intValue) {
		super(enumName, dataName, intValue);
	}

	public Gender(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public Gender(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

}
