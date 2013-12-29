package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class FactorValueType extends EnumDictionary {

	private static final long serialVersionUID = -1068865207905452306L;

	/**
	 * �ӿڹ�������ֵ����
	 */

	public static final FactorValueType OTHER = new FactorValueType("OTHER", "����", 0);
	
	public static final FactorValueType INTERFACE_PROCESS = new FactorValueType("INTERFACE_PROCESS", "�ӿڴ���", 1);
	
	public static final FactorValueType INTERFACE_VERIFICATION_PROCESS = new FactorValueType("INTERFACE_VERIFICATION_PROCESS", "�ӿ���֤����", 2);

	public FactorValueType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public FactorValueType(String enumName, String dataName, String dataENName, Integer value) {
		super(enumName, dataName, dataENName, value);
	}
	
}
