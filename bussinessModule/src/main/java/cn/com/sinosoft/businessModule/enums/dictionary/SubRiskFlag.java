package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class SubRiskFlag extends EnumDictionary {

	/**
	 * ���� �����ձ�ʶ M-���� S-������
	 */
	private static final long serialVersionUID = 594718822157693630L;
	
	public static final SubRiskFlag MIANRISK = new SubRiskFlag("MIANRISK", "����", 1, "M", "1");//����
	
	public static final SubRiskFlag SUBRISK = new SubRiskFlag("SUBRISK", "������", 2, "S", "1");//������

	public SubRiskFlag(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

	public SubRiskFlag(String enumName, String dataName, Integer intValue) {
		super(enumName, dataName, intValue);
	}
	
}
