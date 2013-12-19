package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class BeneficiaryMode extends EnumDictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441033195355714290L;

	public static final BeneficiaryMode LEGAL = new BeneficiaryMode("LEGAL", "法定", 0, "0", "0");

	public static final BeneficiaryMode DESIGNATED = new BeneficiaryMode("DESIGNATED", "指定", 4, "1", "1");

	public BeneficiaryMode(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public BeneficiaryMode(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public BeneficiaryMode(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
}
