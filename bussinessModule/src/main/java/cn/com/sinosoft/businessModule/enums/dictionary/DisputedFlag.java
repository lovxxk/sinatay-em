package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class DisputedFlag extends EnumDictionary {


	/**
	 * ��ͬ���鴦��ʽ ö��
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final DisputedFlag	ANNUAL = new DisputedFlag("ANNUAL", "���", 1, "12", "12");
	
	public static final DisputedFlag	SEMI_ANNUAL = new DisputedFlag("SEMI_ANNUAL", "�����", 2, "6", "6");
	
	public static final DisputedFlag	QUARTERLY = new DisputedFlag("QUARTERLY", "����", 3, "3", "3");
	
	public static final DisputedFlag	MONTHLY = new DisputedFlag("MONTHLY", "�½�", 4, "1", "1");
	
	public static final DisputedFlag	DAILY = new DisputedFlag("DAILY", "���", 6, "6", "6");
	
	public static final DisputedFlag	AGE = new DisputedFlag("AGE", "������", 7, "7", "7");
	
	public static final DisputedFlag	SINGLE = new DisputedFlag("SINGLE", "����", 9, "0", "0");
	
	public static final DisputedFlag	LIFE_TIME = new DisputedFlag("LIFE_TIME", "����", 10, "L", "L");
	
	public static final DisputedFlag	RANDOM = new DisputedFlag("RANDOM", "����", 11, "-1", "-1");
	
	public static final DisputedFlag	OTHER = new DisputedFlag("OTHER", "����", 12, "O", "O");

	
	public DisputedFlag(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
