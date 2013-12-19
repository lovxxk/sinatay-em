package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class MaritalStatus extends EnumDictionary {


	/**
	 * 婚姻状况枚举
	 */
	private static final long serialVersionUID = 4410319243038872599L;
	
	public static final MaritalStatus MARRIED = new MaritalStatus("MARRIED", "已婚", 1);
	
	public static final MaritalStatus DIVORCE = new MaritalStatus("DIVORCE", "离婚", 2);
    
	public static final MaritalStatus FACTOMARRIAGE = new MaritalStatus("FACTOMARRIAGE", "事实婚姻", 3);
	
	public static final MaritalStatus SEPARATION = new MaritalStatus("SEPARATION", "分居", 4);
	
	public static final MaritalStatus SINGLE = new MaritalStatus("SINGLE", "单身", 5);
	
	public static final MaritalStatus WIDOWED = new MaritalStatus("WIDOWED", "鳏寡", 6);
	
	public static final MaritalStatus UNKNOWN = new MaritalStatus("UNKNOWN", "未知", 7);
	
	public static final MaritalStatus OTHER = new MaritalStatus("OTHER", "其它", 8);
	
	public static final MaritalStatus TWOPLACE = new MaritalStatus("TWOPLACE", "两地", 9);
	
	public MaritalStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}
}
