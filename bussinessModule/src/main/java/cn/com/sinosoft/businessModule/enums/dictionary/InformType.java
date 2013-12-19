package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

/**
 * Ͷ����֪����
 */
public class InformType extends EnumDictionary {
	
	private static final long serialVersionUID = 3829658758358898290L;

	public static final InformType HEALTHINFORM = new InformType("HEALTHINFORM", "������˱��ս�����֪", 10, "A01", "A01");

	public static final InformType OTHERINFORM = new InformType("OTHERINFORM", "������˱��ղ���������֪", 11, "A02", "A02");

	public static final InformType BANKIINSUREINFORM = new InformType("BANKIINSUREINFORM", "�������б���Ͷ����֪", 20, "B01", "B01");
	
	public InformType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}


	public InformType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

}
