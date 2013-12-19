package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PaymentMode extends EnumDictionary {


	/**
	 * 缴费方式枚举
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final PaymentMode	ANNUAL = new PaymentMode("ANNUAL", "年缴", 1, "12", "12");
	
	public static final PaymentMode	SEMI_ANNUAL = new PaymentMode("SEMI_ANNUAL", "半年缴", 2, "6", "6");
	
	public static final PaymentMode	QUARTERLY = new PaymentMode("QUARTERLY", "季缴", 3, "3", "3");
	
	public static final PaymentMode	MONTHLY = new PaymentMode("MONTHLY", "月缴", 4, "1", "1");
	
	public static final PaymentMode	DAILY = new PaymentMode("DAILY", "天缴", 6, "6", "6");
	
	public static final PaymentMode	AGE = new PaymentMode("AGE", "缴至岁", 7, "7", "7");
	
	public static final PaymentMode	SINGLE = new PaymentMode("SINGLE", "趸缴", 9, "0", "0");
	
	public static final PaymentMode	LIFE_TIME = new PaymentMode("LIFE_TIME", "终身", 10, "L", "L");
	
	public static final PaymentMode	RANDOM = new PaymentMode("RANDOM", "任意", 11, "-1", "-1");
	
	public static final PaymentMode	OTHER = new PaymentMode("OTHER", "其他", 12, "O", "O");

	
	public PaymentMode(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
