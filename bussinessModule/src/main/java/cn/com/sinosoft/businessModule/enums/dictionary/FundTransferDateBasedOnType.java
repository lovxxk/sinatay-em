package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class FundTransferDateBasedOnType extends EnumDictionary{

	/**
	 * 基于日期类型的基金转让
	 */
	private static final long serialVersionUID = 1134689789826255896L;
	
	public static final FundTransferDateBasedOnType UNKNOWN = new FundTransferDateBasedOnType("UNKNOWN", "未知", 0);
	
	public static final FundTransferDateBasedOnType APPSIGNED = new FundTransferDateBasedOnType("APPSIGNED", "签署日期", 1);
	
	public static final FundTransferDateBasedOnType SUBMIT = new FundTransferDateBasedOnType("SUBMIT", "提交日期", 2);
	
	public static final FundTransferDateBasedOnType ISSUED = new FundTransferDateBasedOnType("ISSUED", "签发日期", 3);
	
	public static final FundTransferDateBasedOnType SUSPENDED_END = new FundTransferDateBasedOnType("SUSPENDED_END", "挂起停止", 4);
	
	public static final FundTransferDateBasedOnType OTHER = new FundTransferDateBasedOnType("OTHER", "其他", 2147483647);

	public FundTransferDateBasedOnType(String enumName, String dataName,
			Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}

}
