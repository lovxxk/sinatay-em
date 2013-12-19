package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class FundsTransferStatus extends EnumDictionary {

	private static final long serialVersionUID = 2768087728634705993L;

	public static final FundsTransferStatus WAITING_AUDIT = new FundsTransferStatus("WAITING_AUDIT", "待审核", 0, "0" ,"0");

	public static final FundsTransferStatus AUDIT = new FundsTransferStatus("AUDIT", "审核中", 1, "1" ,"1");
	
	public static final FundsTransferStatus AUDIT_SUCCESS = new FundsTransferStatus("AUDIT_SUCCESS", "审核成功", 2, "2" ,"2");

	public static final FundsTransferStatus AUDIT_FAILURE = new FundsTransferStatus("AUDIT_FAILURE", "审核失败", 3, "3" ,"3");
	
	public static final FundsTransferStatus WAITING_TRANSFER = new FundsTransferStatus("WAITING_TRANSFER", "资金待划拨", 4, "4" ,"4");
	
	public static final FundsTransferStatus REQUEST_TRANSFER = new FundsTransferStatus("REQUEST_TRANSFER", "资金划拨请求中", 5, "5" ,"5");
	
	public static final FundsTransferStatus HANLDER_TRANSFER = new FundsTransferStatus("HANLDER_TRANSFER", "资金划拨请求处理中", 6, "6" ,"6");
	
	public static final FundsTransferStatus HANLDER_TRANSFER_SUCCESS = new FundsTransferStatus("HANLDER_TRANSFER_SUCCESS", "资金划拨请求处理成功", 60, "60" ,"60");
	
	public static final FundsTransferStatus HANLDER_TRANSFER_FAILURE = new FundsTransferStatus("HANLDER_TRANSFER_FAILURE", "资金划拨请求处理失败", 61, "61" ,"61");
	
	public static final FundsTransferStatus TRANSFER_AUDIT_SUCCESS = new FundsTransferStatus("TRANSFER_AUDIT_SUCCESS", "资金划拨审核成功", 7, "7" , "7");
	
	public static final FundsTransferStatus TRANSFER_AUDIT_FAILURE = new FundsTransferStatus("TRANSFER_AUDIT_FAILURE", "资金划拨审核失败", 71, "71" ,"71");
	
	public static final FundsTransferStatus TRANSFER_SUCCESS = new FundsTransferStatus("TRANSFER_SUCCESS", "划拨成功", 8, "8" ,"8");

	public static final FundsTransferStatus TRANSFER_NOTIFY_CORE_SUCCESS = new FundsTransferStatus("TRANSFER_NOTIFY_CORE_SUCCESS", "划拨通知核心成功", 81, "81" ,"81");

	public static final FundsTransferStatus TRANSFER_FAILURE = new FundsTransferStatus("TRANSFER_FAILURE", " 划拨失败", 9, "9" ,"9");
	
	public static final FundsTransferStatus TRANSFER_NOTIFY_CORE_FAILURE = new FundsTransferStatus("TRANSFER_NOTIFY_CORE_FAILURE", " 划拨通知核心失败", 91, "91" ,"91");
	
	public static final FundsTransferStatus TRANSFER = new FundsTransferStatus("TRANSFER", " 已划拨", 10, "10" ,"10");
	
	public static final FundsTransferStatus TRANSFER_CALLBACK = new FundsTransferStatus("TRANSFER_CALLBACK", "划拨回调中", 11, "11" ,"11");
	
	public static final FundsTransferStatus TRANSFER_CALLBACK_SUCCESS = new FundsTransferStatus("TRANSFER_CALLBACK_SUCCESS", "划拨回调成功", 12, "12" ,"12");
	
	public static final FundsTransferStatus TRANSFER_CALLBACK_FAILURE = new FundsTransferStatus("TRANSFER_CALLBACK_FAILURE", " 划拨回调失败", 13, "13" ,"13");

	public FundsTransferStatus(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
}
