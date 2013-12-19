package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class GroupType  extends EnumDictionary {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7305637388192450134L;
	
	public static final GroupType ALL = new GroupType("ALL", "所有人", 0);
	
	public static final GroupType ADULTS = new GroupType("ADULTS", "成年人", 1);
	
	public static final GroupType MINOR = new GroupType("MINOR", "未成年人", 2);
	
	public static final GroupType OLD = new GroupType("OLD", "老年人", 3);
	
	public GroupType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
	
}
