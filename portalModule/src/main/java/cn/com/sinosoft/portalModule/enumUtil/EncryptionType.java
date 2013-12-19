package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class EncryptionType extends EnumDictionary {

	/**
	 * ��������
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final EncryptionType NOT_ENCRYPTION = new EncryptionType("NOT_ENCRYPTION", "������", 0); 
	
	public static final EncryptionType ENCRYPTION = new EncryptionType("ENCRYPTION ", "����", 1);
	
	public EncryptionType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
