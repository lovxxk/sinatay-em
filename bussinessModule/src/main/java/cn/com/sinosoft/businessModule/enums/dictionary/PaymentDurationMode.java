package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class PaymentDurationMode extends EnumDictionary {


	/**
	 * 缴费年期类型枚举
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final PaymentDurationMode	AGE	= new PaymentDurationMode("AGE", "缴至岁", 0, "A", "A");
	
	public static final PaymentDurationMode	ANNUAL	= new PaymentDurationMode("ANNUAL", "年", 1, "Y", "12");
	
	public static final PaymentDurationMode	MONTHLY	= new PaymentDurationMode("MONTHLY", "月", 2, "M", "1");
	
	public static final PaymentDurationMode	DAILY	= new PaymentDurationMode("DAILY", "日", 3, "D", "D");
	
	public static final PaymentDurationMode	SINGLE	= new PaymentDurationMode("SINGLE", "趸缴", 4, "S", "0");
	
	public static final PaymentDurationMode	LIFE_TIME	= new PaymentDurationMode("LIFE_TIME", "终生缴费",5 , "L", "L");
	 
	public static final PaymentDurationMode	RANDOM	= new PaymentDurationMode("RANDOM", "任意方式", 6, "R", "-1");
	
	public static final PaymentDurationMode	QUARTERLY	= new PaymentDurationMode("QUARTERLY", "季缴/季领", 7, "Q", "3");
	
	public static final PaymentDurationMode	SEMI_ANNUAL	= new PaymentDurationMode("SEMI_ANNUAL", "半年缴/领", 8, "SA", "6");

	public PaymentDurationMode(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
