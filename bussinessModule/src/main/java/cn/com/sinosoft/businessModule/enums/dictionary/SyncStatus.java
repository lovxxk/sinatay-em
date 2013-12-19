package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class SyncStatus extends EnumDictionary {


	/**
	 * 数据同步状态
	 */
	private static final long serialVersionUID = 2782081010974374190L;
	
	public static final SyncStatus NOTYET_SYNCHRONIZED = new SyncStatus("NOTYETSYNCHRONIZED", "尚未同步", 0);
	
	public static final SyncStatus SYNCHRONIZATION = new SyncStatus("SYNCHRONIZATION", "同步中", 1);
	
	public static final SyncStatus SYNCHRONIZED_SUCCESS = new SyncStatus("SYNCHRONIZED_SUCCESS", "同步成功", 2);
	
	public static final SyncStatus SYNCHRONIZED_FAILED   = new SyncStatus("SYNCHRONIZED_FAILED", "同步失败", 3);
	
	public SyncStatus(String enumName, String dataName,
			Integer value) {
		super(enumName, dataName, value);
	}
}
