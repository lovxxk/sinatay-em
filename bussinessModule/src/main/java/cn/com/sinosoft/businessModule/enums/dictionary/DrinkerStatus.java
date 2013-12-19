package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class DrinkerStatus extends EnumDictionary {


	/**
	 * 饮酒状况
	 */
	private static final long serialVersionUID = 2782081010974374190L;
	
	public static final DrinkerStatus NEVER = new DrinkerStatus("NEVER", 1);//从未
	
	public static final DrinkerStatus PRIOR = new DrinkerStatus("PRIOR", 2);//曾经
	
	public static final DrinkerStatus CURRENT = new DrinkerStatus("CURRENT", 3);//现在
	
	protected DrinkerStatus(String name, int value) {
		super(name, value);
	}
}
