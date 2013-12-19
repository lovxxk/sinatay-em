package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class CurrencyType extends EnumDictionary {


	/**
	 * 缴费银行账号币种类型
	 */
	private static final long serialVersionUID = -4597647754756125385L;
	
	public static final CurrencyType RMB =new CurrencyType("RMB", "人民币",156);
	
	public static final CurrencyType USD =new CurrencyType("USD", "美元",840);
	
	public static final CurrencyType EURO =new CurrencyType("EURO", "欧元",954);

	public CurrencyType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}
}
