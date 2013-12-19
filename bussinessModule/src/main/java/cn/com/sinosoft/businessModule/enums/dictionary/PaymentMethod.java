package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PaymentMethod extends EnumDictionary {

	/**
	 * 支付方式类型
	 */
	private static final long serialVersionUID = 8675273356114330183L;

	public static final PaymentMethod UNKNOWN = new PaymentMethod("UNKNOWN", "未知", 0);

	public static final PaymentMethod CASH = new PaymentMethod("CASH", "现金", 1);

	public static final PaymentMethod CREDITCARD = new PaymentMethod("CREDITCARD", "信用卡", 2);

	public static final PaymentMethod CHECK = new PaymentMethod("CHECK", "支票", 3);

	public static final PaymentMethod BANKPASSBOOK = new PaymentMethod("BANKPASSBOOK", "银行存折", 4);

	public static final PaymentMethod BANKTRANSFER = new PaymentMethod("BANKTRANSFER", "银行转账", 40, "4");
	
	public static final PaymentMethod BANK_INSURANCE_LINK = new PaymentMethod("BANK_INSURANCE_LINK", "银保通", 41, "B");

	public static final PaymentMethod POSMACHINE = new PaymentMethod("POSMACHINE", "pos机", 5);

	public static final PaymentMethod INTERNALACCOUNT = new PaymentMethod("INTERNALACCOUNT", "内部帐户", 6);

	public static final PaymentMethod DEDUCTION = new PaymentMethod("DEDUCTION", "DEDUCTION", 7);

	public static final PaymentMethod BANKCONSORTIUM = new PaymentMethod("BANKCONSORTIUM", "银联", 8);

	public static final PaymentMethod QUICKMONEY = new PaymentMethod("QUICKMONEY", "快钱", 9);
	
	public static final PaymentMethod ALIPAY = new PaymentMethod("ALIPAY", "支付宝", 10);
	
	public PaymentMethod(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public PaymentMethod(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
