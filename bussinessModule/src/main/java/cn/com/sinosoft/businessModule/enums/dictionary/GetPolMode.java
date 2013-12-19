package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class GetPolMode extends EnumDictionary {


	/**
	 * 保单递送方式 枚举
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final GetPolMode	ANNUAL = new GetPolMode("ANNUAL", "年缴", 1, "12", "12");
	
	public static final GetPolMode	SEMI_ANNUAL = new GetPolMode("SEMI_ANNUAL", "半年缴", 2, "6", "6");
	
	public static final GetPolMode	QUARTERLY = new GetPolMode("QUARTERLY", "季缴", 3, "3", "3");
	
	public static final GetPolMode	MONTHLY = new GetPolMode("MONTHLY", "月缴", 4, "1", "1");
	
	public static final GetPolMode	DAILY = new GetPolMode("DAILY", "天缴", 6, "6", "6");
	
	public static final GetPolMode	AGE = new GetPolMode("AGE", "缴至岁", 7, "7", "7");
	
	public static final GetPolMode	SINGLE = new GetPolMode("SINGLE", "趸缴", 9, "0", "0");
	
	public static final GetPolMode	LIFE_TIME = new GetPolMode("LIFE_TIME", "终身", 10, "L", "L");
	
	public static final GetPolMode	RANDOM = new GetPolMode("RANDOM", "任意", 11, "-1", "-1");
	
	public static final GetPolMode	OTHER = new GetPolMode("OTHER", "其他", 12, "O", "O");

	
	public GetPolMode(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
