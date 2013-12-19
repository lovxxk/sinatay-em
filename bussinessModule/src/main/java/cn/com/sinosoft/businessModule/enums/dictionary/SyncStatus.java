package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class SyncStatus extends EnumDictionary {


	/**
	 * ����ͬ��״̬
	 */
	private static final long serialVersionUID = 2782081010974374190L;
	
	public static final SyncStatus NOTYET_SYNCHRONIZED = new SyncStatus("NOTYETSYNCHRONIZED", "��δͬ��", 0);
	
	public static final SyncStatus SYNCHRONIZATION = new SyncStatus("SYNCHRONIZATION", "ͬ����", 1);
	
	public static final SyncStatus SYNCHRONIZED_SUCCESS = new SyncStatus("SYNCHRONIZED_SUCCESS", "ͬ���ɹ�", 2);
	
	public static final SyncStatus SYNCHRONIZED_FAILED   = new SyncStatus("SYNCHRONIZED_FAILED", "ͬ��ʧ��", 3);
	
	public SyncStatus(String enumName, String dataName,
			Integer value) {
		super(enumName, dataName, value);
	}
}
