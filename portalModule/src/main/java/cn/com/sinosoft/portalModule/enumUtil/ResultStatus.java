package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;

public class ResultStatus extends EnumDictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2584924526540439346L;

	public static final ResultStatus FAILURE = new ResultStatus("FAILURE ", "失败", -1, "-1");
	
	public static final ResultStatus SUCCESS = new ResultStatus("SUCCESS", "成功", 0, "0"); 
	
	public static final ResultStatus PARTIAL_SUCCESS = new ResultStatus("PARTIAL_SUCCESS ", "部分成功", 1, "1");
	
	public static final ResultStatus HAS_BEEN_SUCCESS = new ResultStatus("HAS_BEEN_SUCCESS ", "已经成功", 2, "2");
	
	public ResultStatus(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}
	
}
