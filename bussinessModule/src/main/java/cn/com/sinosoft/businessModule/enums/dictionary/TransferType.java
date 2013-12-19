package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

/**
 * 资金划拨类型
 */
public class TransferType extends EnumDictionary {
	
	private static final long serialVersionUID = 3829658758358898290L;

	public static final TransferType SURRENDER = new TransferType("SURRENDER", "退保", 0, "1", "0");

	public static final TransferType ALL_RECIPIENTS = new TransferType("ALL_RECIPIENTS", "全部领取", 1, "1", "1");

	public static final TransferType SOME_RECIPIENTS = new TransferType("SOME_RECIPIENTS", "部分领取", 2, "0", "2");

	public TransferType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
		// TODO Auto-generated constructor stub
	}
	
}
