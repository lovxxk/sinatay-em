package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class ProcessStatus extends EnumDictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3855154924768955518L;

	public static final ProcessStatus CREATED = new ProcessStatus("CREATED", "�Ѵ���", 1);
	
	public static final ProcessStatus SUCCEEDED = new ProcessStatus("SUCCEEDED", "�ɹ�", 2);
    
	public static final ProcessStatus FAILED = new ProcessStatus("FAILED", "ʧ��", 3);
	
	public static final ProcessStatus PERFORMING = new ProcessStatus("PERFORMING", "ִ����", 8);
	
	public ProcessStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
