package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class MainInsuredFlag extends EnumDictionary {

	/**
	 *主被保人标示 
	 */
	private static final long serialVersionUID = -2850532640033013982L;
	
	public static final MainInsuredFlag MAININSURED = new MainInsuredFlag("MAININSURED", "第一被保人（主被保人）", 1, "1", "1");
	
	public static final MainInsuredFlag SECONDINSURED = new MainInsuredFlag("SECONDINSURED", "第二被保人", 2, "2", "2");
	
	public static final MainInsuredFlag THIRDINSURED = new MainInsuredFlag("THIRDINSURED", "第三被保人", 3, "3", "3");
	
	public MainInsuredFlag(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
		// TODO Auto-generated constructor stub
	}

}
