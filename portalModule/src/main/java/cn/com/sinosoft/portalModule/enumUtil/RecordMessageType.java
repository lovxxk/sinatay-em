package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class RecordMessageType extends EnumDictionary {

	/**
	 * ��¼����״̬
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final RecordMessageType NOT_RECORD = new RecordMessageType("NOT_RECORD ", "����¼������Ϣ", 0);
	
	public static final RecordMessageType RECORD_TRANSACTION  = new RecordMessageType("RECORD_TRANSACTION", "��¼������Ϣ", 1); 
	
	public static final RecordMessageType RECORD_TRANSMSG_FILE = new RecordMessageType("RECORD_TRANSMSG_FILE", "��¼���ױ������ļ�", 2);
	
	public static final RecordMessageType RECORD_TRANSMSG_LOG = new RecordMessageType("RECORD_TRANSMSG_LOG", "��¼���ױ�������־", 3);
	
	public static final RecordMessageType RECORD_TRANSMSG_DB = new RecordMessageType("RECORD_TRANSMSG_DB", "��¼���ױ��������ݿ�", 4);
	
	public RecordMessageType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
