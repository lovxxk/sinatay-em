package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class DisputedFlag extends EnumDictionary {


	/**
	 * 合同争议处理方式 枚举
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final DisputedFlag	ANNUAL = new DisputedFlag("ANNUAL", "年缴", 1, "12", "12");
	
	public static final DisputedFlag	SEMI_ANNUAL = new DisputedFlag("SEMI_ANNUAL", "半年缴", 2, "6", "6");
	
	public static final DisputedFlag	QUARTERLY = new DisputedFlag("QUARTERLY", "季缴", 3, "3", "3");
	
	public static final DisputedFlag	MONTHLY = new DisputedFlag("MONTHLY", "月缴", 4, "1", "1");
	
	public static final DisputedFlag	DAILY = new DisputedFlag("DAILY", "天缴", 6, "6", "6");
	
	public static final DisputedFlag	AGE = new DisputedFlag("AGE", "缴至岁", 7, "7", "7");
	
	public static final DisputedFlag	SINGLE = new DisputedFlag("SINGLE", "趸缴", 9, "0", "0");
	
	public static final DisputedFlag	LIFE_TIME = new DisputedFlag("LIFE_TIME", "终身", 10, "L", "L");
	
	public static final DisputedFlag	RANDOM = new DisputedFlag("RANDOM", "任意", 11, "-1", "-1");
	
	public static final DisputedFlag	OTHER = new DisputedFlag("OTHER", "其他", 12, "O", "O");

	
	public DisputedFlag(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
