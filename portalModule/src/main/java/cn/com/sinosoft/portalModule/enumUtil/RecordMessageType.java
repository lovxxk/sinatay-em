package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class RecordMessageType extends EnumDictionary {

	/**
	 * 记录报文状态
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final RecordMessageType NOT_RECORD = new RecordMessageType("NOT_RECORD ", "不记录交易信息", 0);
	
	public static final RecordMessageType RECORD_TRANSACTION  = new RecordMessageType("RECORD_TRANSACTION", "记录交易信息", 1); 
	
	public static final RecordMessageType RECORD_TRANSMSG_FILE = new RecordMessageType("RECORD_TRANSMSG_FILE", "记录交易报文至文件", 2);
	
	public static final RecordMessageType RECORD_TRANSMSG_LOG = new RecordMessageType("RECORD_TRANSMSG_LOG", "记录交易报文至日志", 3);
	
	public static final RecordMessageType RECORD_TRANSMSG_DB = new RecordMessageType("RECORD_TRANSMSG_DB", "记录交易报文至数据库", 4);
	
	public RecordMessageType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
