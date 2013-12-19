package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class ElementType extends EnumDictionary {

	/**
	 * Ԫ������
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final ElementType HEADER = new ElementType("HEADER", "ͷ���ڵ�", 0);
	
	public ElementType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
