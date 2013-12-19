package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class MessageType extends EnumDictionary {

	/**
	 * �ӿ�״̬
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final MessageType REQUEST = new MessageType("REQUEST", "������", "Req", 1); 
	
	public static final MessageType RESPONSE = new MessageType("RESPONSE ", "Ӧ����", "Res", 2);
	
	public static final MessageType FRONTREQUEST = new MessageType("FRONTREQUEST ", "�ⲿϵͳ������", "FReq", 3);
	
	public static final MessageType FRONTRESPONSE = new MessageType("FRONTRESPONSE ", "Ӧ���ⲿϵͳ����", "FRes", 4);
	
	
	public MessageType(String enumName, String dataName, String dataENName,
			Integer value) {
		super(enumName, dataName, dataENName, value);
		// TODO Auto-generated constructor stub
	}
	
}
