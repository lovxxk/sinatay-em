package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class BeneficiaryType extends EnumDictionary {

	/**
	 * ����������ö��
	 */
	private static final long serialVersionUID = -7055264074433343372L;

	public static final BeneficiaryType BD = new BeneficiaryType("BD", "Ĭ��", 0);

	public static final BeneficiaryType B1 = new BeneficiaryType("B1", "��һ˳λ", 1);

	public static final BeneficiaryType B2 = new BeneficiaryType("B2", "�ڶ�˳λ", 2);

	public static final BeneficiaryType B3 = new BeneficiaryType("B3", "����˳λ", 3);

	public static final BeneficiaryType BC = new BeneficiaryType("BC", "ָ��������", 4);

	public static final BeneficiaryType DB = new BeneficiaryType("DB", "���������ˣ���һ˳λ��", 3401, "1");

	public static final BeneficiaryType LB = new BeneficiaryType("LB", "����������", 3402, "0");

	public static final BeneficiaryType DE = new BeneficiaryType("DE", "����������", 3403);

	public static final BeneficiaryType D1 = new BeneficiaryType("D1", "���������ˣ��ڶ�˳λ��", 3404);

	public BeneficiaryType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryType(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
		// TODO Auto-generated constructor stub
	}

}
