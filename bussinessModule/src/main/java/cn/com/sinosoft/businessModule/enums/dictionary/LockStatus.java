package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class LockStatus extends EnumDictionary {
	
	/**
	 * ¼ÓËø×´Ì¬
	 */
	
	private static final long serialVersionUID = -6376294998911913886L;
	
	public static final LockStatus LOCKED = new LockStatus("LOCKED", "¼ÓËø", 0);
	
	public static final LockStatus UNLOCK = new LockStatus("UNLOCK", "½âËø", 1);
	
	public LockStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
