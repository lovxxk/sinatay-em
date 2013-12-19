package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class PayMethod extends EnumDictionary {

	/**
	 * ֧����ʽ����
	 */
	private static final long serialVersionUID = 8675273356114330183L;

	public static final PayMethod UNKNOWN = new PayMethod("UNKNOWN", "δ֪", 0);

	public static final PayMethod WITHHOLDING = new PayMethod("WITHHOLDING", "ʵʱ����", 1, "B");
	
	public static final PayMethod BANK_WITHHOLDING = new PayMethod("BANK_WITHHOLDING", "���д���(�Ʒ���)", 2, "7");
	
	public PayMethod(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public PayMethod(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
