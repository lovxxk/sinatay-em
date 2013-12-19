package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

/**
 * 投保告知类型
 */
public class InformType extends EnumDictionary {
	
	private static final long serialVersionUID = 3829658758358898290L;

	public static final InformType HEALTHINFORM = new InformType("HEALTHINFORM", "东吴个人保险健康告知", 10, "A01", "A01");

	public static final InformType OTHERINFORM = new InformType("OTHERINFORM", "东吴个人保险财务及其它告知", 11, "A02", "A02");

	public static final InformType BANKIINSUREINFORM = new InformType("BANKIINSUREINFORM", "东吴银行保险投保告知", 20, "B01", "B01");
	
	public InformType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}


	public InformType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
