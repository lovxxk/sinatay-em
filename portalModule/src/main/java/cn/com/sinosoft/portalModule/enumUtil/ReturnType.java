package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class ReturnType extends EnumDictionary {

	/**
	 * �ӿ�����
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final ReturnType RETURN_NONE = new ReturnType("RETURN_NONE ", "�޷��ؽ��׶���", 0);
	
	public static final ReturnType RETURN_BUSINESSDATUM = new ReturnType("RETURN_BUSINESSDATUM", "����ҵ�����", 1); 
	
	public static final ReturnType RETURN_TRANSOBJ = new ReturnType("RETURN_TRANSOBJ ", "���ؽ��׶���", 2);
	
	public static final ReturnType RETURN_RESPONSE_XML = new ReturnType("RETURN_RESPONSE_XML ", "����Ӧ����", 3);
	
	public ReturnType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
