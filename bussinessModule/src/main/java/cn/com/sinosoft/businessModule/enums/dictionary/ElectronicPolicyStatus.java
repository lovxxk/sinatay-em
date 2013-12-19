package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class ElectronicPolicyStatus extends EnumDictionary {

	private static final long serialVersionUID = 2768087728634705993L;

	public static final ElectronicPolicyStatus NOT_GENERATED = new ElectronicPolicyStatus("NOT_GENERATED", "δ����", 0, "0" ,"0");

	public static final ElectronicPolicyStatus GENERATION = new ElectronicPolicyStatus("GENERATION", "������", 1, "1" ,"1");
	
	public static final ElectronicPolicyStatus GENERATED_SUCCESS = new ElectronicPolicyStatus("GENERATED_SUCCESS", "���ɳɹ�", 11, "11" ,"11");
	
	public static final ElectronicPolicyStatus GENERATED_FAILURE = new ElectronicPolicyStatus("GENERATED_FAILURE", "����ʧ��", 12, "12" ,"12");

	public static final ElectronicPolicyStatus SIGNED = new ElectronicPolicyStatus("SIGNED", "��ǩ��", 3, "3" ,"3");
	
	public static final ElectronicPolicyStatus SIGNED_FAILURE = new ElectronicPolicyStatus("SIGNED_FAILURE", "ǩ��ʧ��", 31, "31" ,"31");
	
	public static final ElectronicPolicyStatus EMAIL_SEND = new ElectronicPolicyStatus("EMAIL_SEND", "�ʼ�����", 4, "4" ,"4");
	
	public static final ElectronicPolicyStatus EMAIL_SEND_SUCCESS = new ElectronicPolicyStatus("EMAIL_SEND_SUCCESS", "�ʼ����ͳɹ�", 41, "41" ,"41");
	
	public static final ElectronicPolicyStatus EMAIL_SEND_FAILURE = new ElectronicPolicyStatus("EMAIL_SEND_FAILURE", "�ʼ�����ʧ��", 42, "42" ,"42");
	
	public ElectronicPolicyStatus(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}


	public ElectronicPolicyStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
