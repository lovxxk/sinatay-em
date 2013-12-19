package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class ExPayMode extends EnumDictionary {


	/**
	 * 续期缴费方式枚举
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final ExPayMode	ANNUAL = new ExPayMode("ANNUAL", "年缴", 1, "12", "12");
	
	public static final ExPayMode	SEMI_ANNUAL = new ExPayMode("SEMI_ANNUAL", "半年缴", 2, "6", "6");
	
	public static final ExPayMode	QUARTERLY = new ExPayMode("QUARTERLY", "季缴", 3, "3", "3");
	
	public static final ExPayMode	MONTHLY = new ExPayMode("MONTHLY", "月缴", 4, "1", "1");
	
	public static final ExPayMode	DAILY = new ExPayMode("DAILY", "天缴", 6, "6", "6");
	
	public static final ExPayMode	AGE = new ExPayMode("AGE", "缴至岁", 7, "7", "7");
	
	public static final ExPayMode	SINGLE = new ExPayMode("SINGLE", "趸缴", 9, "0", "0");
	
	public static final ExPayMode	LIFE_TIME = new ExPayMode("LIFE_TIME", "终身", 10, "L", "L");
	
	public static final ExPayMode	RANDOM = new ExPayMode("RANDOM", "任意", 11, "-1", "-1");
	
	public static final ExPayMode	OTHER = new ExPayMode("OTHER", "其他", 12, "O", "O");

	
	public ExPayMode(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
