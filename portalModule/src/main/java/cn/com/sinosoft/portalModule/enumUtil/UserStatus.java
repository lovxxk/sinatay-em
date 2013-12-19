package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class UserStatus extends EnumDictionary {

	/**
	 * �û�״̬
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final UserStatus ENABLED = new UserStatus("ENABLED", "����", 1); 
	
	public static final UserStatus DISABLED = new UserStatus("DISABLED ", "����", 0);
	
	public UserStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
