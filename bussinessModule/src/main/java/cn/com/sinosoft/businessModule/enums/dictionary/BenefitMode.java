package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class BenefitMode extends EnumDictionary {


	/**
	 * �������ȡ��ʽö
	 */
	private static final long serialVersionUID = 8675273356114330183L;
	
	public static final BenefitMode ANNUAL = new BenefitMode("ANNUAL", "���", 1);
	
	public static final BenefitMode SEMI_ANNUA = new BenefitMode("SEMI_ANNUA", "�����", 2);//����ɻ�����
	
	public static final BenefitMode QUARTERLY = new BenefitMode("QUARTER", "����", 3);//����
	
	public static final BenefitMode MONTHLY = new BenefitMode("MONTHLY", "�½�", 4);
    
	public static final BenefitMode DAILY = new BenefitMode("DAILY", "���", 6);
	
	public static final BenefitMode AGE = new BenefitMode("AGE", "������", 7);

	public static final BenefitMode SINGLE = new BenefitMode("SINGLE", "����",9);//����
	
	public static final BenefitMode LIFE_TIME = new BenefitMode("LIFE_TIME", "����", 10);
	
	public static final BenefitMode RANDOM = new BenefitMode("RANDOM", "����", 11);
	
	public static final BenefitMode OTHER = new BenefitMode("OTHER", "����", 12);
	
	public BenefitMode(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
	
}
