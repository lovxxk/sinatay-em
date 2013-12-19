package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class PaymentDurationMode extends EnumDictionary {


	/**
	 * �ɷ���������ö��
	 */
	private static final long serialVersionUID = -7283649786232805507L;
	
	public static final PaymentDurationMode	AGE	= new PaymentDurationMode("AGE", "������", 0, "A", "A");
	
	public static final PaymentDurationMode	ANNUAL	= new PaymentDurationMode("ANNUAL", "��", 1, "Y", "12");
	
	public static final PaymentDurationMode	MONTHLY	= new PaymentDurationMode("MONTHLY", "��", 2, "M", "1");
	
	public static final PaymentDurationMode	DAILY	= new PaymentDurationMode("DAILY", "��", 3, "D", "D");
	
	public static final PaymentDurationMode	SINGLE	= new PaymentDurationMode("SINGLE", "����", 4, "S", "0");
	
	public static final PaymentDurationMode	LIFE_TIME	= new PaymentDurationMode("LIFE_TIME", "�����ɷ�",5 , "L", "L");
	 
	public static final PaymentDurationMode	RANDOM	= new PaymentDurationMode("RANDOM", "���ⷽʽ", 6, "R", "-1");
	
	public static final PaymentDurationMode	QUARTERLY	= new PaymentDurationMode("QUARTERLY", "����/����", 7, "Q", "3");
	
	public static final PaymentDurationMode	SEMI_ANNUAL	= new PaymentDurationMode("SEMI_ANNUAL", "�����/��", 8, "SA", "6");

	public PaymentDurationMode(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	
}
