package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class ResultStatus extends EnumDictionary {
	private static final long serialVersionUID = 3829658758358898290L;

	public static final ResultStatus FAILURE = new ResultStatus("FAILURE", "Ê§°Ü", 0, "false");

	public static final ResultStatus SUCCESS = new ResultStatus("SUCCESS", "³É¹¦", 1, "true");

	public ResultStatus(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public ResultStatus(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
