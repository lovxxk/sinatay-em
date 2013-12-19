package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class FundsTransferStatus extends EnumDictionary {

	private static final long serialVersionUID = 2768087728634705993L;

	public static final FundsTransferStatus WAITING_AUDIT = new FundsTransferStatus("WAITING_AUDIT", "�����", 0, "0" ,"0");

	public static final FundsTransferStatus AUDIT = new FundsTransferStatus("AUDIT", "�����", 1, "1" ,"1");
	
	public static final FundsTransferStatus AUDIT_SUCCESS = new FundsTransferStatus("AUDIT_SUCCESS", "��˳ɹ�", 2, "2" ,"2");

	public static final FundsTransferStatus AUDIT_FAILURE = new FundsTransferStatus("AUDIT_FAILURE", "���ʧ��", 3, "3" ,"3");
	
	public static final FundsTransferStatus WAITING_TRANSFER = new FundsTransferStatus("WAITING_TRANSFER", "�ʽ������", 4, "4" ,"4");
	
	public static final FundsTransferStatus REQUEST_TRANSFER = new FundsTransferStatus("REQUEST_TRANSFER", "�ʽ𻮲�������", 5, "5" ,"5");
	
	public static final FundsTransferStatus HANLDER_TRANSFER = new FundsTransferStatus("HANLDER_TRANSFER", "�ʽ𻮲���������", 6, "6" ,"6");
	
	public static final FundsTransferStatus HANLDER_TRANSFER_SUCCESS = new FundsTransferStatus("HANLDER_TRANSFER_SUCCESS", "�ʽ𻮲�������ɹ�", 60, "60" ,"60");
	
	public static final FundsTransferStatus HANLDER_TRANSFER_FAILURE = new FundsTransferStatus("HANLDER_TRANSFER_FAILURE", "�ʽ𻮲�������ʧ��", 61, "61" ,"61");
	
	public static final FundsTransferStatus TRANSFER_AUDIT_SUCCESS = new FundsTransferStatus("TRANSFER_AUDIT_SUCCESS", "�ʽ𻮲���˳ɹ�", 7, "7" , "7");
	
	public static final FundsTransferStatus TRANSFER_AUDIT_FAILURE = new FundsTransferStatus("TRANSFER_AUDIT_FAILURE", "�ʽ𻮲����ʧ��", 71, "71" ,"71");
	
	public static final FundsTransferStatus TRANSFER_SUCCESS = new FundsTransferStatus("TRANSFER_SUCCESS", "�����ɹ�", 8, "8" ,"8");

	public static final FundsTransferStatus TRANSFER_NOTIFY_CORE_SUCCESS = new FundsTransferStatus("TRANSFER_NOTIFY_CORE_SUCCESS", "����֪ͨ���ĳɹ�", 81, "81" ,"81");

	public static final FundsTransferStatus TRANSFER_FAILURE = new FundsTransferStatus("TRANSFER_FAILURE", " ����ʧ��", 9, "9" ,"9");
	
	public static final FundsTransferStatus TRANSFER_NOTIFY_CORE_FAILURE = new FundsTransferStatus("TRANSFER_NOTIFY_CORE_FAILURE", " ����֪ͨ����ʧ��", 91, "91" ,"91");
	
	public static final FundsTransferStatus TRANSFER = new FundsTransferStatus("TRANSFER", " �ѻ���", 10, "10" ,"10");
	
	public static final FundsTransferStatus TRANSFER_CALLBACK = new FundsTransferStatus("TRANSFER_CALLBACK", "�����ص���", 11, "11" ,"11");
	
	public static final FundsTransferStatus TRANSFER_CALLBACK_SUCCESS = new FundsTransferStatus("TRANSFER_CALLBACK_SUCCESS", "�����ص��ɹ�", 12, "12" ,"12");
	
	public static final FundsTransferStatus TRANSFER_CALLBACK_FAILURE = new FundsTransferStatus("TRANSFER_CALLBACK_FAILURE", " �����ص�ʧ��", 13, "13" ,"13");

	public FundsTransferStatus(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
}
