package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public final class IdType extends EnumDictionary {
	private static final long serialVersionUID = 3051899814977847880L;

	public static final IdType UNKOWN = new IdType("UNKOWN", "δ֪", 0, "9", "");

	public static final IdType SOCIALSECURITYCARD = new IdType("SOCIALSECURITYCARD", "���֤", 1, "0", "1");

	public static final IdType UNEMPLOYMENTCARD = new IdType("UNEMPLOYMENTCARD", "ʧҵ֤", 2);

	public static final IdType RETIREMENTCARD = new IdType("RETIREMENTCARD", "����֤", 3);

	public static final IdType PASSPORT = new IdType("PASSPORT", "����", 4, "1", "3");

	public static final IdType VISA = new IdType("VISA", "ǩ֤", 5);

	public static final IdType STUDENTCARD = new IdType("STUDENTCARD", "ѧ��֤", 6);

	public static final IdType RESIDENCECARD = new IdType("RESIDENCECARD", "��ס֤", 7);

	public static final IdType OFFICERCARD = new IdType("OFFICERCARD", "����֤", 8, "2", "4");

	public static final IdType OFFICERRETIREMENTCARD = new IdType("OFFICERRETIREMENTCARD", "��������֤", 9);

	public static final IdType DRIVERSLICENCE = new IdType("DRIVERSLICENCE", "��ʻ֤", 10, "3", "13");

	public static final IdType BIRTHCARD = new IdType("BIRTHCARD", "����֤", 11);

	public static final IdType SOLDIERCARD = new IdType("SOLDIERCARD", "ʿ��֤", 13, "A", "12");

	public static final IdType RETURNCARD = new IdType("RETURNCARD", "����֤", 14, "B", "14");

	public static final IdType TEMPORARYSOCIALSECURITYCARD = new IdType("TEMPORARYSOCIALSECURITYCARD", "��ʱ���֤", 15, "C", "");

	public static final IdType HOUSECARD = new IdType("HOUSECARD", "���ڱ�", 16, "4", "11");

	public static final IdType SERGEANTCARD = new IdType("SERGEANTCARD", "����֤", 17, "D", "");

	public static final IdType CIVILIANPOSTCARD = new IdType("CIVILIANPOSTCARD", "��ž���ְ�ɲ�֤", 18);

	public static final IdType TRAVELPASSPORTCARD = new IdType("TRAVELPASSPORTCARD", "����֤", 19);

	public static final IdType FOREIGNPASSPORTCARD = new IdType("FOREIGNPASSPORTCARD", "�������", 20);

	public static final IdType ARMEDCIVILIANCADRES = new IdType("ARMEDCIVILIANCADRES", "�侯��ְ�ɲ�֤", 21);

	public static final IdType ARMEDSOLDIERCARD = new IdType("ARMEDSOLDIERCARD", "�侯ʿ��֤", 22);

	public static final IdType TAIWANESECARD = new IdType("TAIWANESECARD", "̨��֤", 23, "E", "5");

	public static final IdType ORGANIZATIONCODE = new IdType("ORGANIZATIONCODE", "��֯��������", 24, "", "15");

	public static final IdType HONGKONGANDMACAOPASSCARD = new IdType("HONGKONGANDMACAOPASSCARD", "�۰�ͨ��֤", 25, "", "16");

	public static final IdType TAIWANPASSCARD = new IdType("TAIWANPASSCARD", "̨��ͨ��֤", 26, "", "17");

	public static final IdType OTHER = new IdType("OTHER", "����", 2147483647, "8", "10");

	public IdType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public IdType(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}

}