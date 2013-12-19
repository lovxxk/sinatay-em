package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PayMethod extends EnumDictionary {

	/**
	 * 支付方式类型
	 */
	private static final long serialVersionUID = 8675273356114330183L;

	public static final PayMethod UNKNOWN = new PayMethod("UNKNOWN", "未知", 0);

	public static final PayMethod WITHHOLDING = new PayMethod("WITHHOLDING", "实时代扣", 1, "B");
	
	public static final PayMethod BANK_WITHHOLDING = new PayMethod("BANK_WITHHOLDING", "银行代扣(制返盘)", 2, "7");
	
	public PayMethod(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public PayMethod(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
