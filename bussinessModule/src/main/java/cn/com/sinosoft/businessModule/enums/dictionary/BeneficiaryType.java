package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public class BeneficiaryType extends EnumDictionary {

	/**
	 * 受益人类型枚举
	 */
	private static final long serialVersionUID = -7055264074433343372L;

	public static final BeneficiaryType BD = new BeneficiaryType("BD", "默认", 0);

	public static final BeneficiaryType B1 = new BeneficiaryType("B1", "第一顺位", 1);

	public static final BeneficiaryType B2 = new BeneficiaryType("B2", "第二顺位", 2);

	public static final BeneficiaryType B3 = new BeneficiaryType("B3", "第三顺位", 3);

	public static final BeneficiaryType BC = new BeneficiaryType("BC", "指定受益人", 4);

	public static final BeneficiaryType DB = new BeneficiaryType("DB", "死亡受益人（第一顺位）", 3401, "1");

	public static final BeneficiaryType LB = new BeneficiaryType("LB", "生存受益人", 3402, "0");

	public static final BeneficiaryType DE = new BeneficiaryType("DE", "满期受益人", 3403);

	public static final BeneficiaryType D1 = new BeneficiaryType("D1", "死亡受益人（第二顺位）", 3404);

	public BeneficiaryType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryType(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
		// TODO Auto-generated constructor stub
	}

}
