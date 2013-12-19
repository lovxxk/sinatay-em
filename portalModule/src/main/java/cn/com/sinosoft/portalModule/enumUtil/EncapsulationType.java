package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class EncapsulationType extends EnumDictionary {

	/**
	 * 接口类型
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final EncapsulationType NOT_ENCAPSULATION = new EncapsulationType("NOT_ENCAPSULATION", "不封装", 0); 
	
	public static final EncapsulationType ALL_ENCAPSULATION = new EncapsulationType("ALL_ENCAPSULATION ", "封装所有信息", 1);
	
	public static final EncapsulationType HEADER_ENCAPSULATION = new EncapsulationType("HEADER_ENCAPSULATION ", "封装报文头部信息", 2);
	
	public static final EncapsulationType EXTENSION_ENCAPSULATION = new EncapsulationType("EXTENSION_ENCAPSULATION ", "封装报文扩展信息", 3);
	
	public static final EncapsulationType SYSTEM_ENCAPSULATION = new EncapsulationType("SYSTEM_ENCAPSULATION ", "封装报文系统扩展信息", 4);
	
	public static final EncapsulationType ACCOUNT_ENCAPSULATION = new EncapsulationType("ACCOUNT_ENCAPSULATION ", "封装报文接口账号信息", 5);
	
	public EncapsulationType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
