package cn.com.sinosoft.businessModule.enums.dictionary;

import cn.com.sinosoft.enums.EnumDictionary;

public final class RelatedToRoleCode extends EnumDictionary {

	private static final long serialVersionUID = -6185567139762056720L;

	public static final RelatedToRoleCode Spouse = new RelatedToRoleCode("Spouse", "配偶关系", 1, "33", "9");

	public static final RelatedToRoleCode Child = new RelatedToRoleCode("Child", "子女关系", 2, "32", "11");

	public static final RelatedToRoleCode Parent = new RelatedToRoleCode("Parent", "父母关系", 3, "31", "10");

	public static final RelatedToRoleCode Sibling = new RelatedToRoleCode("Sibling", "兄弟姐妹关系", 4, "", "26");

	public static final RelatedToRoleCode Family = new RelatedToRoleCode("Family", "亲属关系", 5, "25", "12");

	public static final RelatedToRoleCode Employee = new RelatedToRoleCode("Employee", "雇佣关系", 6, "29", "13");

	public static final RelatedToRoleCode Friend = new RelatedToRoleCode("Friend", "朋友关系", 14, "27", "");

	public static final RelatedToRoleCode Trustee = new RelatedToRoleCode("Trustee", "托管人", 18, "", "");

	public static final RelatedToRoleCode Executor = new RelatedToRoleCode("Executor", "执行人", 19, "", "");

	public static final RelatedToRoleCode FatherMotherInLaw = new RelatedToRoleCode("FatherMotherInLaw", "公婆关系", 21, "", "");

	public static final RelatedToRoleCode DaughterInLaw = new RelatedToRoleCode("DaughterInLaw", "儿媳关系", 22, "23", "");

	public static final RelatedToRoleCode Suocer = new RelatedToRoleCode("Suocer", "岳父母关系", 23, "", "");

	public static final RelatedToRoleCode Genero = new RelatedToRoleCode("Genero", "女婿关系", 24, "24", "");

	public static final RelatedToRoleCode Husband = new RelatedToRoleCode("Husband", "丈夫", 25, "01", "2");

	public static final RelatedToRoleCode Wife = new RelatedToRoleCode("Wife", "妻子", 26, "02", "3");

	public static final RelatedToRoleCode Father = new RelatedToRoleCode("Father", "父亲", 27, "03", "4");

	public static final RelatedToRoleCode Mother = new RelatedToRoleCode("Mother", "母亲", 28, "04", "5");

	public static final RelatedToRoleCode Son = new RelatedToRoleCode("Son", "儿子", 29, "05", "6");

	public static final RelatedToRoleCode Daughter = new RelatedToRoleCode("Daughter", "女儿", 30, "06", "7");

	public static final RelatedToRoleCode Brothers = new RelatedToRoleCode("Brothers", "兄弟", 31, "", "24");

	public static final RelatedToRoleCode Sisters = new RelatedToRoleCode("Sisters", "姊妹", 32, "", "25");

	public static final RelatedToRoleCode Siblings = new RelatedToRoleCode("Siblings", "姐弟", 33, "", "27");

	public static final RelatedToRoleCode Grandfather = new RelatedToRoleCode("Grandfather", "祖父", 34, "07", "");
	public static final RelatedToRoleCode Grandmother = new RelatedToRoleCode("Grandmother", "祖母", 35, "08", "");
	public static final RelatedToRoleCode Grandson = new RelatedToRoleCode("Grandson", "孙子", 36, "09", "");
	public static final RelatedToRoleCode Granddaughter = new RelatedToRoleCode("Granddaughter", "孙女", 37, "10", "");
	public static final RelatedToRoleCode Maternal_Grandfather = new RelatedToRoleCode("Maternal_Grandfather", "外祖父", 38, "11", "");
	public static final RelatedToRoleCode Maternal_Grandmother = new RelatedToRoleCode("Maternal_Grandmother", "外祖母", 39, "12", "");
	public static final RelatedToRoleCode OGrandson = new RelatedToRoleCode("OGrandson", "外孙", 40, "13", "");
	public static final RelatedToRoleCode OGranddaughter = new RelatedToRoleCode("OGranddaughter", "外孙女", 41, "14", "");
	public static final RelatedToRoleCode Brother = new RelatedToRoleCode("Brother", "哥哥", 42, "15", "");
	public static final RelatedToRoleCode Sister = new RelatedToRoleCode("Sister", "姐姐", 43, "16", "");
	public static final RelatedToRoleCode Brothery = new RelatedToRoleCode("Brothery", "弟弟", 44, "17", "");
	public static final RelatedToRoleCode Sistery = new RelatedToRoleCode("Sistery", "妹妹", 45, "18", "");
	public static final RelatedToRoleCode OFather = new RelatedToRoleCode("OFather", "公公", 46, "19", "");
	public static final RelatedToRoleCode OMother = new RelatedToRoleCode("OMother", "婆婆", 47, "20", "");
	public static final RelatedToRoleCode YFather = new RelatedToRoleCode("Yather", "岳父", 48, "21", "");
	public static final RelatedToRoleCode YMother = new RelatedToRoleCode("YMother", "岳母", 49, "22", "");
	public static final RelatedToRoleCode Employer = new RelatedToRoleCode("Employer", "雇主", 50, "28", "");

	public static final RelatedToRoleCode GrandParent = new RelatedToRoleCode("GrandParent", "祖父或祖母关系", 92, "", "");

	public static final RelatedToRoleCode GrandChild = new RelatedToRoleCode("GrandChild", "孙子或孙女关系", 93, "", "");

	public static final RelatedToRoleCode Self = new RelatedToRoleCode("Self", "本人关系", 168, "00", "1");

	public static final RelatedToRoleCode GreatGrandChild = new RelatedToRoleCode("GreatGrandChild", "曾孙子或曾孙女关系", 1111, "", "");

	public static final RelatedToRoleCode LegalInherit = new RelatedToRoleCode("LegalInherit", "法定继承关系", 8888, "", "");

	public static final RelatedToRoleCode GreatGrandParent = new RelatedToRoleCode("GreatGrandParent", "曾祖父或曾祖母关系", 9999, "", "");

	public static final RelatedToRoleCode FLAN = new RelatedToRoleCode("FLAN", "父子关系", 10001, "", "20");

	public static final RelatedToRoleCode FADA = new RelatedToRoleCode("FADA", "父女关系", 10002, "", "21");

	public static final RelatedToRoleCode MOSO = new RelatedToRoleCode("MOSO", "母子关系", 10003, "", "22");

	public static final RelatedToRoleCode MODA = new RelatedToRoleCode("MODA", "母女关系", 10004, "", "23");

	public static final RelatedToRoleCode GPGC = new RelatedToRoleCode("GPGC", "祖孙关系", 10005, "", "28");

	public static final RelatedToRoleCode Workmate = new RelatedToRoleCode("Workmate", "同事关系", 10016, "26", "");

	public static final RelatedToRoleCode LegalGuard = new RelatedToRoleCode("LegalGuard", "法定关系", 10018, "", "");

	public static final RelatedToRoleCode Other = new RelatedToRoleCode("Other", "其他", 2147483647, "30", "14");

	public RelatedToRoleCode(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}

	public RelatedToRoleCode(String enumName, String dataName, Integer value, String coreValue) {
		super(enumName, dataName, value, coreValue);
	}

	public RelatedToRoleCode(String enumName, String dataName, Integer value, String coreValue, String merchantValue) {
		super(enumName, dataName, value, coreValue, merchantValue);
	}
}