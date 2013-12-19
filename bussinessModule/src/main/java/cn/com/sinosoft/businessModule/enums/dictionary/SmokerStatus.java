package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class SmokerStatus extends EnumDictionary {


	/**
	 * 吸烟状况
	 */
	private static final long serialVersionUID = 2782081010974374190L;
	
	public static final SmokerStatus NEVER = new SmokerStatus("NEVER", 1);//从未
	
	public static final SmokerStatus PRIOR = new SmokerStatus("PRIOR", 2);//曾经
	
	public static final SmokerStatus CURRENT = new SmokerStatus("CURRENT", 3);//现在
	
	protected SmokerStatus(String name, int value) {
		super(name, value);
	}
}
