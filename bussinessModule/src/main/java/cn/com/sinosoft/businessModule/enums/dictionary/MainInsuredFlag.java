package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class MainInsuredFlag extends EnumDictionary {

	/**
	 *�������˱�ʾ 
	 */
	private static final long serialVersionUID = -2850532640033013982L;
	
	public static final MainInsuredFlag MAININSURED = new MainInsuredFlag("MAININSURED", "��һ�����ˣ��������ˣ�", 1, "1", "1");
	
	public static final MainInsuredFlag SECONDINSURED = new MainInsuredFlag("SECONDINSURED", "�ڶ�������", 2, "2", "2");
	
	public static final MainInsuredFlag THIRDINSURED = new MainInsuredFlag("THIRDINSURED", "����������", 3, "3", "3");
	
	public MainInsuredFlag(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
		// TODO Auto-generated constructor stub
	}

}
