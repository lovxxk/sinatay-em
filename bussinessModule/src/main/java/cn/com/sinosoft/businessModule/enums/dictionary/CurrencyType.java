package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class CurrencyType extends EnumDictionary {


	/**
	 * �ɷ������˺ű�������
	 */
	private static final long serialVersionUID = -4597647754756125385L;
	
	public static final CurrencyType RMB =new CurrencyType("RMB", "�����",156);
	
	public static final CurrencyType USD =new CurrencyType("USD", "��Ԫ",840);
	
	public static final CurrencyType EURO =new CurrencyType("EURO", "ŷԪ",954);

	public CurrencyType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}
}
