package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class SubRiskFlag extends EnumDictionary {

	/**
	 * 主险 附加险标识 M-主险 S-附加险
	 */
	private static final long serialVersionUID = 594718822157693630L;
	
	public static final SubRiskFlag MIANRISK = new SubRiskFlag("MIANRISK", "主险", 1, "M", "1");//主险
	
	public static final SubRiskFlag SUBRISK = new SubRiskFlag("SUBRISK", "附加险", 2, "S", "1");//附加险

	public SubRiskFlag(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	public SubRiskFlag(String enumName, String dataName, Integer intValue) {
		super(enumName, dataName, intValue);
	}
	
}
