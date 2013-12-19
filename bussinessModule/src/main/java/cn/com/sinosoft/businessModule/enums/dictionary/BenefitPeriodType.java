package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class BenefitPeriodType extends EnumDictionary {

	/**
	 * 保障年期类型
	 */
	private static final long serialVersionUID = 8675273356114330183L;

	public static final BenefitPeriodType OTHER = new BenefitPeriodType("OTHER", "其他", 9, "9", "9");

	public static final BenefitPeriodType ANNUAL = new BenefitPeriodType("ANNUAL", "年", 42, "Y", "Y");

	public static final BenefitPeriodType MONTHLY = new BenefitPeriodType("MONTHLY", "月", 41, "M", "M");

	public static final BenefitPeriodType DAILY = new BenefitPeriodType("DAILY", "天", 40, "D", "D");

	public static final BenefitPeriodType WEEK = new BenefitPeriodType("WEEK", "周", 30, "W", "W");

	public static final BenefitPeriodType AGE = new BenefitPeriodType("AGE", "岁", 20, "A", "");

	public static final BenefitPeriodType LIFE_TIME = new BenefitPeriodType("LIFE_TIME", "终身", 18, "A", "A");

	public BenefitPeriodType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public BenefitPeriodType(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

}
