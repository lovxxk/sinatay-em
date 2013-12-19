package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class RegionLevel extends EnumDictionary {

	/**
	 * 国家地区等级
	 */
	private static final long serialVersionUID = -8654439447148240455L;
	
	public static final RegionLevel PROVINCE = new RegionLevel("PROVINCE", "省", 10);
	
	public static final RegionLevel CITY = new RegionLevel("CITY", "市/区", 20);
    
	public static final RegionLevel COUNTY = new RegionLevel("COUNTY", "县", 30);
	
	public RegionLevel(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}

}
