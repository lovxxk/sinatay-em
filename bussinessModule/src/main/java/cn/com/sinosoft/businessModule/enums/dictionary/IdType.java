package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public final class IdType extends EnumDictionary {
	private static final long serialVersionUID = 3051899814977847880L;

	public static final IdType UNKOWN = new IdType("UNKOWN", "未知", 0, "9", "");

	public static final IdType SOCIALSECURITYCARD = new IdType("SOCIALSECURITYCARD", "身份证", 1, "0", "1");

	public static final IdType UNEMPLOYMENTCARD = new IdType("UNEMPLOYMENTCARD", "失业证", 2);

	public static final IdType RETIREMENTCARD = new IdType("RETIREMENTCARD", "退休证", 3);

	public static final IdType PASSPORT = new IdType("PASSPORT", "护照", 4, "1", "3");

	public static final IdType VISA = new IdType("VISA", "签证", 5);

	public static final IdType STUDENTCARD = new IdType("STUDENTCARD", "学生证", 6);

	public static final IdType RESIDENCECARD = new IdType("RESIDENCECARD", "居住证", 7);

	public static final IdType OFFICERCARD = new IdType("OFFICERCARD", "军官证", 8, "2", "4");

	public static final IdType OFFICERRETIREMENTCARD = new IdType("OFFICERRETIREMENTCARD", "军官退休证", 9);

	public static final IdType DRIVERSLICENCE = new IdType("DRIVERSLICENCE", "驾驶证", 10, "3", "13");

	public static final IdType BIRTHCARD = new IdType("BIRTHCARD", "出生证", 11);

	public static final IdType SOLDIERCARD = new IdType("SOLDIERCARD", "士兵证", 13, "A", "12");

	public static final IdType RETURNCARD = new IdType("RETURNCARD", "回乡证", 14, "B", "14");

	public static final IdType TEMPORARYSOCIALSECURITYCARD = new IdType("TEMPORARYSOCIALSECURITYCARD", "临时身份证", 15, "C", "");

	public static final IdType HOUSECARD = new IdType("HOUSECARD", "户口本", 16, "4", "11");

	public static final IdType SERGEANTCARD = new IdType("SERGEANTCARD", "警官证", 17, "D", "");

	public static final IdType CIVILIANPOSTCARD = new IdType("CIVILIANPOSTCARD", "解放军文职干部证", 18);

	public static final IdType TRAVELPASSPORTCARD = new IdType("TRAVELPASSPORTCARD", "旅行证", 19);

	public static final IdType FOREIGNPASSPORTCARD = new IdType("FOREIGNPASSPORTCARD", "外国护照", 20);

	public static final IdType ARMEDCIVILIANCADRES = new IdType("ARMEDCIVILIANCADRES", "武警文职干部证", 21);

	public static final IdType ARMEDSOLDIERCARD = new IdType("ARMEDSOLDIERCARD", "武警士兵证", 22);

	public static final IdType TAIWANESECARD = new IdType("TAIWANESECARD", "台胞证", 23, "E", "5");

	public static final IdType ORGANIZATIONCODE = new IdType("ORGANIZATIONCODE", "组织机构代码", 24, "", "15");

	public static final IdType HONGKONGANDMACAOPASSCARD = new IdType("HONGKONGANDMACAOPASSCARD", "港澳通行证", 25, "", "16");

	public static final IdType TAIWANPASSCARD = new IdType("TAIWANPASSCARD", "台湾通行证", 26, "", "17");

	public static final IdType OTHER = new IdType("OTHER", "其他", 2147483647, "8", "10");

	public IdType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public IdType(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

}