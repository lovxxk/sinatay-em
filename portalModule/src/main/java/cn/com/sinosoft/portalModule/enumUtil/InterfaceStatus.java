package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class InterfaceStatus extends EnumDictionary {

	/**
	 * �ӿ�״̬
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final InterfaceStatus ENABLED = new InterfaceStatus("ENABLED", "����", 1); 
	
	public static final InterfaceStatus DISABLED = new InterfaceStatus("DISABLED ", "����", 0);
	
	public InterfaceStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
