package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;


public class DivType extends EnumDictionary {


	/**
	 * ������ȡ��ʽö�ٱ�
	 */
	private static final long serialVersionUID = -4340067204833298224L;
	
	//ֱ����ȡ
	public static final DivType CSWD = new DivType("CSWD", "��ȡ�ֽ�", 2, "2", "2");//DRAWCASH
	
	//�ֽ�����
	public static final DivType PMST = new DivType("PMST", "�ֽ�����", 4, "1", "1");//OFFSETPAYPREMIUM
	
	//�ֽ���ȡ
	public static final DivType DVAC = new DivType("DVAC", "�ۻ���Ϣ", 5, "3", "3");//ACCUMULATELIVE
	
	//�ֽ���ȡ
	public static final DivType PUAD = new DivType("PUAD", "�����", 8, "", "");//INCREMENTSETTLE
	
	//�ֽ���ȡ
	public static final DivType OTHER = new DivType("OTHER", "����", 2147483647, "", "");//OTHER

	public DivType(String enumName, String dataName, Integer value,
			String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
}
