package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PaymentMethod extends EnumDictionary {

	/**
	 * ֧����ʽ����
	 */
	private static final long serialVersionUID = 8675273356114330183L;

	public static final PaymentMethod UNKNOWN = new PaymentMethod("UNKNOWN", "δ֪", 0);

	public static final PaymentMethod CASH = new PaymentMethod("CASH", "�ֽ�", 1);

	public static final PaymentMethod CREDITCARD = new PaymentMethod("CREDITCARD", "���ÿ�", 2);

	public static final PaymentMethod CHECK = new PaymentMethod("CHECK", "֧Ʊ", 3);

	public static final PaymentMethod BANKPASSBOOK = new PaymentMethod("BANKPASSBOOK", "���д���", 4);

	public static final PaymentMethod BANKTRANSFER = new PaymentMethod("BANKTRANSFER", "����ת��", 40, "4");
	
	public static final PaymentMethod BANK_INSURANCE_LINK = new PaymentMethod("BANK_INSURANCE_LINK", "����ͨ", 41, "B");

	public static final PaymentMethod POSMACHINE = new PaymentMethod("POSMACHINE", "pos��", 5);

	public static final PaymentMethod INTERNALACCOUNT = new PaymentMethod("INTERNALACCOUNT", "�ڲ��ʻ�", 6);

	public static final PaymentMethod DEDUCTION = new PaymentMethod("DEDUCTION", "DEDUCTION", 7);

	public static final PaymentMethod BANKCONSORTIUM = new PaymentMethod("BANKCONSORTIUM", "����", 8);

	public static final PaymentMethod QUICKMONEY = new PaymentMethod("QUICKMONEY", "��Ǯ", 9);
	
	public static final PaymentMethod ALIPAY = new PaymentMethod("ALIPAY", "֧����", 10);
	
	public PaymentMethod(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public PaymentMethod(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
