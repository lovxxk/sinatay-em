package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class InterfaceType extends EnumDictionary {

	/**
	 * �ӿ�����
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final InterfaceType SERVICE_INTERFACE = new InterfaceType("SERVICE_INTERFACE", "����ӿ�", 1); 
	
	public static final InterfaceType REQUEST_INTERFACE = new InterfaceType("REQUEST_INTERFACE ", "����ӿ�", 2);
	
	public InterfaceType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
