package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class FundTransferDateBasedOnType extends EnumDictionary{

	/**
	 * �����������͵Ļ���ת��
	 */
	private static final long serialVersionUID = 1134689789826255896L;
	
	public static final FundTransferDateBasedOnType UNKNOWN = new FundTransferDateBasedOnType("UNKNOWN", "δ֪", 0);
	
	public static final FundTransferDateBasedOnType APPSIGNED = new FundTransferDateBasedOnType("APPSIGNED", "ǩ������", 1);
	
	public static final FundTransferDateBasedOnType SUBMIT = new FundTransferDateBasedOnType("SUBMIT", "�ύ����", 2);
	
	public static final FundTransferDateBasedOnType ISSUED = new FundTransferDateBasedOnType("ISSUED", "ǩ������", 3);
	
	public static final FundTransferDateBasedOnType SUSPENDED_END = new FundTransferDateBasedOnType("SUSPENDED_END", "����ֹͣ", 4);
	
	public static final FundTransferDateBasedOnType OTHER = new FundTransferDateBasedOnType("OTHER", "����", 2147483647);

	public FundTransferDateBasedOnType(String enumName, String dataName,
			Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}

}
