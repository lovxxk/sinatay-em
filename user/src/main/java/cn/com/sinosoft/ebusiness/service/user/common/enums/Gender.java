package cn.com.sinosoft.ebusiness.service.user.common.enums;

import cn.com.sinosoft.enums.EnumDictionary;

public class Gender extends EnumDictionary {

	/**
	 * 性别枚举类型
	 */
	private static final long serialVersionUID = 8624611319646255247L;

	public static final Gender MALE = new Gender("MALE", "男性", 1, "0", "1");// 男性

	public static final Gender FEMALE = new Gender("FEMALE", "女性", 2, "1", "2"); // 女性

	public static final Gender UNKNOWN = new Gender("UNKNOWN", "不祥", 3, "2", "3");// 不祥

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
