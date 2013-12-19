package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class DivType extends EnumDictionary {


	/**
	 * 红利领取方式枚举表
	 */
	private static final long serialVersionUID = -4340067204833298224L;
	
	//直接领取
	public static final DivType CSWD = new DivType("CSWD", "领取现金", 2, "2", "2");//DRAWCASH
	
	//抵交保费
	public static final DivType PMST = new DivType("PMST", "抵交保费", 4, "1", "1");//OFFSETPAYPREMIUM
	
	//现金领取
	public static final DivType DVAC = new DivType("DVAC", "累积生息", 5, "3", "3");//ACCUMULATELIVE
	
	//现金领取
	public static final DivType PUAD = new DivType("PUAD", "增额交清", 8, "", "");//INCREMENTSETTLE
	
	//现金领取
	public static final DivType OTHER = new DivType("OTHER", "其他", 2147483647, "", "");//OTHER

	public DivType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
}
