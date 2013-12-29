package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class FactorType extends EnumDictionary {

	private static final long serialVersionUID = -1068865207905452306L;

	/**
	 * �ӿڹ�������
	 */

	public static final FactorType OTHER = new FactorType("OTHER", "����", 0);
	
	public static final FactorType SYSTEM_INTERFACE = new FactorType("SYSTEM_INTERFACE", "�ⲿ�Խ�ϵͳ�ӿ�", 1);
	
	public static final FactorType INTERFACE_VERIFICATION = new FactorType("INTERFACE_VERIFICATION", "�ӿ���֤", 2);

	public FactorType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public FactorType(String enumName, String dataName, String dataENName, Integer value) {
		super(enumName, dataName, dataENName, value);
	}
	
}
