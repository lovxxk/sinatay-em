package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class GroupType  extends EnumDictionary {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7305637388192450134L;
	
	public static final GroupType ALL = new GroupType("ALL", "������", 0);
	
	public static final GroupType ADULTS = new GroupType("ADULTS", "������", 1);
	
	public static final GroupType MINOR = new GroupType("MINOR", "δ������", 2);
	
	public static final GroupType OLD = new GroupType("OLD", "������", 3);
	
	public GroupType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
	
}
