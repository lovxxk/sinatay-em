package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class EncapsulationType extends EnumDictionary {

	/**
	 * �ӿ�����
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final EncapsulationType NOT_ENCAPSULATION = new EncapsulationType("NOT_ENCAPSULATION", "����װ", 0); 
	
	public static final EncapsulationType ALL_ENCAPSULATION = new EncapsulationType("ALL_ENCAPSULATION ", "��װ������Ϣ", 1);
	
	public static final EncapsulationType HEADER_ENCAPSULATION = new EncapsulationType("HEADER_ENCAPSULATION ", "��װ����ͷ����Ϣ", 2);
	
	public static final EncapsulationType EXTENSION_ENCAPSULATION = new EncapsulationType("EXTENSION_ENCAPSULATION ", "��װ������չ��Ϣ", 3);
	
	public static final EncapsulationType SYSTEM_ENCAPSULATION = new EncapsulationType("SYSTEM_ENCAPSULATION ", "��װ����ϵͳ��չ��Ϣ", 4);
	
	public static final EncapsulationType ACCOUNT_ENCAPSULATION = new EncapsulationType("ACCOUNT_ENCAPSULATION ", "��װ���Ľӿ��˺���Ϣ", 5);
	
	public EncapsulationType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
