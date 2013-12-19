package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class BenefitMode extends EnumDictionary {


	/**
	 * 生存金领取方式枚
	 */
	private static final long serialVersionUID = 8675273356114330183L;
	
	public static final BenefitMode ANNUAL = new BenefitMode("ANNUAL", "年缴", 1);
	
	public static final BenefitMode SEMI_ANNUA = new BenefitMode("SEMI_ANNUA", "半年缴", 2);//半年缴或半年缴
	
	public static final BenefitMode QUARTERLY = new BenefitMode("QUARTER", "季缴", 3);//季缴
	
	public static final BenefitMode MONTHLY = new BenefitMode("MONTHLY", "月缴", 4);
    
	public static final BenefitMode DAILY = new BenefitMode("DAILY", "天缴", 6);
	
	public static final BenefitMode AGE = new BenefitMode("AGE", "缴至岁", 7);

	public static final BenefitMode SINGLE = new BenefitMode("SINGLE", "趸缴",9);//趸缴
	
	public static final BenefitMode LIFE_TIME = new BenefitMode("LIFE_TIME", "终生", 10);
	
	public static final BenefitMode RANDOM = new BenefitMode("RANDOM", "任意", 11);
	
	public static final BenefitMode OTHER = new BenefitMode("OTHER", "其他", 12);
	
	public BenefitMode(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
	
}
