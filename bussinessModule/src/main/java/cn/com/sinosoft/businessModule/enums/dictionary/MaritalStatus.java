package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class MaritalStatus extends EnumDictionary {


	/**
	 * ����״��ö��
	 */
	private static final long serialVersionUID = 4410319243038872599L;
	
	public static final MaritalStatus MARRIED = new MaritalStatus("MARRIED", "�ѻ�", 1);
	
	public static final MaritalStatus DIVORCE = new MaritalStatus("DIVORCE", "���", 2);
    
	public static final MaritalStatus FACTOMARRIAGE = new MaritalStatus("FACTOMARRIAGE", "��ʵ����", 3);
	
	public static final MaritalStatus SEPARATION = new MaritalStatus("SEPARATION", "�־�", 4);
	
	public static final MaritalStatus SINGLE = new MaritalStatus("SINGLE", "����", 5);
	
	public static final MaritalStatus WIDOWED = new MaritalStatus("WIDOWED", "����", 6);
	
	public static final MaritalStatus UNKNOWN = new MaritalStatus("UNKNOWN", "δ֪", 7);
	
	public static final MaritalStatus OTHER = new MaritalStatus("OTHER", "����", 8);
	
	public static final MaritalStatus TWOPLACE = new MaritalStatus("TWOPLACE", "����", 9);
	
	public MaritalStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}
}
