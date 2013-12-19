package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class BankAccountType extends EnumDictionary {


	/**
	 * 缴费银行帐号类型
	 */
	private static final long serialVersionUID = -3854440970009058630L;
	
	public static final BankAccountType BANKBOOK = new BankAccountType("BANKBOOK", "存折", 1, "01");
	
	public static final BankAccountType CREDITCARD = new BankAccountType("CREDITCARD", "信用卡", 3, "02");
	
	public static final BankAccountType DEBITCARD = new BankAccountType("DEBITCARD", "借记卡", 4, "00");
	
	public BankAccountType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
	
	public BankAccountType(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public BankAccountType(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
	
}
