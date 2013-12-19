package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class MessageType extends EnumDictionary {

	/**
	 * 接口状态
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final MessageType REQUEST = new MessageType("REQUEST", "请求报文", "Req", 1); 
	
	public static final MessageType RESPONSE = new MessageType("RESPONSE ", "应答报文", "Res", 2);
	
	public static final MessageType FRONTREQUEST = new MessageType("FRONTREQUEST ", "外部系统请求报文", "FReq", 3);
	
	public static final MessageType FRONTRESPONSE = new MessageType("FRONTRESPONSE ", "应答外部系统报文", "FRes", 4);
	
	
	public MessageType(String enumName, String dataName, String dataENName,
			Integer value) {
		super(enumName, dataName, dataENName, value);
		// TODO Auto-generated constructor stub
	}
	
}
