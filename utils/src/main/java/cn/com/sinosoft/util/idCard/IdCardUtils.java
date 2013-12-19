package cn.com.sinosoft.util.idCard;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * <p>
 * ��˵��:���֤�Ϸ���У��
 * </p>
 * <p>
 * --15λ���֤���룺��7��8λΪ�������(��λ��)����9��10λΪ�����·ݣ���11��12λ����������ڣ���15λ�����Ա�����Ϊ�У�ż��ΪŮ��
 * --18λ���֤����
 * ����7��8��9��10λΪ�������(��λ��)����11����12λΪ�����·ݣ���13��14λ����������ڣ���17λ�����Ա�����Ϊ�У�ż��ΪŮ��
 * </p>
 */
@SuppressWarnings({ "unchecked", "unused", "all" })
public class IdCardUtils {

	/**
	 * ʡ��ֱϽ�д���� { 11:"����",12:"���",13:"�ӱ�",14:"ɽ��",15:"���ɹ�",
	 * 21:"����",22:"����",23:"������",31:"�Ϻ�",32:"����",
	 * 33:"�㽭",34:"����",35:"����",36:"����",37:"ɽ��",41:"����",
	 * 42:"����",43:"����",44:"�㶫",45:"����",46:"����",50:"����",
	 * 51:"�Ĵ�",52:"����",53:"����",54:"����",61:"����",62:"����",
	 * 63:"�ຣ",64:"����",65:"�½�",71:"̨��",81:"���",82:"����",91:"����"}
	 */
	protected String codeAndCity[][] = { { "11", "����" }, { "12", "���" },
			{ "13", "�ӱ�" }, { "14", "ɽ��" }, { "15", "���ɹ�" }, { "21", "����" },
			{ "22", "����" }, { "23", "������" }, { "31", "�Ϻ�" }, { "32", "����" },
			{ "33", "�㽭" }, { "34", "����" }, { "35", "����" }, { "36", "����" },
			{ "37", "ɽ��" }, { "41", "����" }, { "42", "����" }, { "43", "����" },
			{ "44", "�㶫" }, { "45", "����" }, { "46", "����" }, { "50", "����" },
			{ "51", "�Ĵ�" }, { "52", "����" }, { "53", "����" }, { "54", "����" },
			{ "61", "����" }, { "62", "����" }, { "63", "�ຣ" }, { "64", "����" },
			{ "65", "�½�" }, { "71", "̨��" }, { "81", "���" }, { "82", "����" },
			{ "91", "����" } };

	private String cityCode[] = { "11", "12", "13", "14", "15", "21", "22",
			"23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
			"44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
			"64", "65", "71", "81", "82", "91" };

	// ÿλ��Ȩ����
	private int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// ��18λУ����
	private String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5",
			"4", "3", "2" };

	// ʡ��
	private String province;

	// ����
	private String city;

	// ����
	private String region;

	// ���
	private int year;

	// �·�
	private int month;

	// ����
	private int day;

	// �Ա�
	private String gender;

	// ��������
	private Date birthday;

	private Map cityCodeMap = new HashMap() {
		{
			this.put("11", "����");
			this.put("12", "���");
			this.put("13", "�ӱ�");
			this.put("14", "ɽ��");
			this.put("15", "���ɹ�");
			this.put("21", "����");
			this.put("22", "����");
			this.put("23", "������");
			this.put("31", "�Ϻ�");
			this.put("32", "����");
			this.put("33", "�㽭");
			this.put("34", "����");
			this.put("35", "����");
			this.put("36", "����");
			this.put("37", "ɽ��");
			this.put("41", "����");
			this.put("42", "����");
			this.put("43", "����");
			this.put("44", "�㶫");
			this.put("45", "����");
			this.put("46", "����");
			this.put("50", "����");
			this.put("51", "�Ĵ�");
			this.put("52", "����");
			this.put("53", "����");
			this.put("54", "����");
			this.put("61", "����");
			this.put("62", "����");
			this.put("63", "�ຣ");
			this.put("64", "����");
			this.put("65", "�½�");
			this.put("71", "̨��");
			this.put("81", "���");
			this.put("82", "����");
			this.put("91", "����");
		}
	};

	private IdCardUtils idCardUtils = null;

	/**
	 * ��֤���е����֤�ĺϷ���
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean isValidatedAllIdcard(String idcard) {
		if (idcard.length() == 15) {
			idcard = this.convertIdcarBy15bit(idcard);
		}
		return this.isValidate18Idcard(idcard);
	}

	/**
	 * <p>
	 * �ж�18λ���֤�ĺϷ���
	 * </p>
	 * ���ݡ��л����񹲺͹����ұ�׼GB11643-1999�����йع�����ݺ���Ĺ涨��������ݺ�������������룬��ʮ��λ���ֱ������һλ����У������ɡ�
	 * ����˳�������������Ϊ����λ���ֵ�ַ�룬��λ���ֳ��������룬��λ����˳�����һλ����У���롣
	 * <p>
	 * ˳����: ��ʾ��ͬһ��ַ������ʶ������Χ�ڣ���ͬ�ꡢͬ�¡�ͬ �ճ������˱ඨ��˳��ţ�˳�����������������ԣ�ż������ ��Ů�ԡ�
	 * </p>
	 * <p>
	 * 1.ǰ1��2λ���ֱ�ʾ������ʡ�ݵĴ��룻 2.��3��4λ���ֱ�ʾ�����ڳ��еĴ��룻 3.��5��6λ���ֱ�ʾ���������صĴ��룻
	 * 4.��7~14λ���ֱ�ʾ�������ꡢ�¡��գ� 5.��15��16λ���ֱ�ʾ�����ڵص��ɳ����Ĵ��룻
	 * 6.��17λ���ֱ�ʾ�Ա�������ʾ���ԣ�ż����ʾŮ�ԣ�
	 * 7.��18λ������У���룺Ҳ�е�˵�Ǹ�����Ϣ�룬һ��������������������������������֤����ȷ�ԡ�У���������0~9�����֣���ʱҲ��x��ʾ��
	 * </p>
	 * <p>
	 * ��ʮ��λ����(У����)�ļ��㷽��Ϊ�� 1.��ǰ������֤����17λ���ֱ���Բ�ͬ��ϵ�����ӵ�һλ����ʮ��λ��ϵ���ֱ�Ϊ��7 9 10 5 8 4
	 * 2 1 6 3 7 9 10 5 8 4 2
	 * </p>
	 * <p>
	 * 2.����17λ���ֺ�ϵ����˵Ľ����ӡ�
	 * </p>
	 * <p>
	 * 3.�üӳ����ͳ���11���������Ƕ��٣�
	 * </p>
	 * 4.����ֻ������0 1 2 3 4 5 6 7 8 9 10��11�����֡���ֱ��Ӧ�����һλ���֤�ĺ���Ϊ1 0 X 9 8 7 6 5 4 3
	 * 2��
	 * <p>
	 * 5.ͨ�������֪���������2���ͻ������֤�ĵ�18λ�����ϳ����������ֵĢ������������10�����֤�����һλ�������2��
	 * </p>
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean isValidate18Idcard(String idcard) {
		// ��18λΪ��
		if (idcard.length() != 18) {
			return false;
		}
		// ��ȡǰ17λ
		String idcard17 = idcard.substring(0, 17);
		// ��ȡ��18λ
		String idcard18Code = idcard.substring(17, 18);
		char c[] = null;
		String checkCode = "";
		// �Ƿ�Ϊ����
		if (isDigital(idcard17)) {
			c = idcard17.toCharArray();
		} else {
			return false;
		}

		if (null != c) {
			int bit[] = new int[idcard17.length()];

			bit = converCharToInt(c);

			int sum17 = 0;

			sum17 = getPowerSum(bit);

			// ����ֵ��11ȡģ�õ���������У�����ж�
			checkCode = getCheckCodeBySum(sum17);
			if (null == checkCode) {
				return false;
			}
			// �����֤�ĵ�18λ���������У�����ƥ�䣬����Ⱦ�Ϊ��
			if (!idcard18Code.equalsIgnoreCase(checkCode)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ��֤15λ���֤�ĺϷ���,�÷�����֤��׼ȷ������ǽ�15תΪ18λ�����жϣ����������ṩ��
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean isValidate15Idcard(String idcard) {
		// ��15λΪ��
		if (idcard.length() != 15) {
			return false;
		}

		// �Ƿ�ȫ��Ϊ����
		if (isDigital(idcard)) {
			String provinceid = idcard.substring(0, 2);
			String birthday = idcard.substring(6, 12);
			int year = Integer.parseInt(idcard.substring(6, 8));
			int month = Integer.parseInt(idcard.substring(8, 10));
			int day = Integer.parseInt(idcard.substring(10, 12));

			// �ж��Ƿ�Ϊ�Ϸ���ʡ��
			boolean flag = false;
			for (String id : cityCode) {
				if (id.equals(provinceid)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				return false;
			}
			// �����֤���������ڵ�ǰ����֮��ʱΪ��
			Date birthdate = null;
			try {
				birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (birthdate == null || new Date().before(birthdate)) {
				return false;
			}

			// �ж��Ƿ�Ϊ�Ϸ������
			GregorianCalendar curDay = new GregorianCalendar();
			int curYear = curDay.get(Calendar.YEAR);
			int year2bit = Integer.parseInt(String.valueOf(curYear)
					.substring(2));

			// �жϸ���ݵ���λ��ʾ����С��50�ĺʹ��ڵ�ǰ��ݵģ�Ϊ��
			if ((year < 50 && year > year2bit)) {
				return false;
			}

			// �ж��Ƿ�Ϊ�Ϸ����·�
			if (month < 1 || month > 12) {
				return false;
			}

			// �ж��Ƿ�Ϊ�Ϸ�������
			boolean mflag = false;
			curDay.setTime(birthdate); // �������֤�ĳ������ڸ��ڶ���curDay
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				mflag = (day >= 1 && day <= 31);
				break;
			case 2: // ������2�·�������28��,�����2����29�졣
				if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
					mflag = (day >= 1 && day <= 29);
				} else {
					mflag = (day >= 1 && day <= 28);
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				mflag = (day >= 1 && day <= 30);
				break;
			}
			if (!mflag) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * ��15λ�����֤ת��18λ���֤
	 * 
	 * @param idcard
	 * @return
	 */
	public String convertIdcarBy15bit(String idcard) {
		String idcard17 = null;
		// ��15λ���֤
		if (idcard.length() != 15) {
			return null;
		}

		if (isDigital(idcard)) {
			// ��ȡ����������
			String birthday = idcard.substring(6, 12);
			Date birthdate = null;
			try {
				birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cday = Calendar.getInstance();
			cday.setTime(birthdate);
			String year = String.valueOf(cday.get(Calendar.YEAR));

			idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

			char c[] = idcard17.toCharArray();
			String checkCode = "";

			if (null != c) {
				int bit[] = new int[idcard17.length()];

				// ���ַ�����תΪ��������
				bit = converCharToInt(c);
				int sum17 = 0;
				sum17 = getPowerSum(bit);

				// ��ȡ��ֵ��11ȡģ�õ���������У����
				checkCode = getCheckCodeBySum(sum17);
				// ��ȡ����У��λ
				if (null == checkCode) {
					return null;
				}

				// ��ǰ17λ���18λУ����ƴ��
				idcard17 += checkCode;
			}
		} else { // ���֤��������
			return null;
		}
		return idcard17;
	}

	/**
	 * 15λ��18λ���֤����Ļ������ֺ�λ����У
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean isIdcard(String idcard) {
		return idcard == null || "".equals(idcard) ? false : Pattern.matches(
				"(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
	}

	/**
	 * 15λ���֤����Ļ������ֺ�λ����У
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean is15Idcard(String idcard) {
		return idcard == null || "".equals(idcard) ? false : Pattern.matches(
				"^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
				idcard);
	}

	/**
	 * 18λ���֤����Ļ������ֺ�λ����У
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean is18Idcard(String idcard) {
		return Pattern
				.matches(
						"^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
						idcard);
	}

	/**
	 * ������֤
	 * 
	 * @param str
	 * @return
	 */
	public boolean isDigital(String str) {
		return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
	}

	/**
	 * �����֤��ÿλ�Ͷ�Ӧλ�ļ�Ȩ�������֮���ٵõ���ֵ
	 * 
	 * @param bit
	 * @return
	 */
	public int getPowerSum(int[] bit) {

		int sum = 0;

		if (power.length != bit.length) {
			return sum;
		}

		for (int i = 0; i < bit.length; i++) {
			for (int j = 0; j < power.length; j++) {
				if (i == j) {
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return sum;
	}

	/**
	 * ����ֵ��11ȡģ�õ���������У�����ж�
	 * 
	 * @param checkCode
	 * @param sum17
	 * @return У��λ
	 */
	public String getCheckCodeBySum(int sum17) {
		String checkCode = null;
		switch (sum17 % 11) {
		case 10:
			checkCode = "2";
			break;
		case 9:
			checkCode = "3";
			break;
		case 8:
			checkCode = "4";
			break;
		case 7:
			checkCode = "5";
			break;
		case 6:
			checkCode = "6";
			break;
		case 5:
			checkCode = "7";
			break;
		case 4:
			checkCode = "8";
			break;
		case 3:
			checkCode = "9";
			break;
		case 2:
			checkCode = "x";
			break;
		case 1:
			checkCode = "0";
			break;
		case 0:
			checkCode = "1";
			break;
		}
		return checkCode;
	}

	/**
	 * ���ַ�����תΪ��������
	 * 
	 * @param c
	 * @return
	 * @throws NumberFormatException
	 */
	public int[] converCharToInt(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		for (char temp : c) {
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}
		return a;
	}

	/**
	 * ͨ�����췽����ʼ��������Ա����
	 */
	public IdCardUtils(String idcard) {
		try {
			idCardUtils = new IdCardUtils(idcard);
			if (idCardUtils.isValidatedAllIdcard(idcard)) {
				if (idcard.length() == 15) {
					idcard = idCardUtils.convertIdcarBy15bit(idcard);
				}
				// ��ȡʡ��
				String provinceId = idcard.substring(0, 2);
				Set<String> key = this.cityCodeMap.keySet();
				for (String id : key) {
					if (id.equals(provinceId)) {
						this.province = (String) this.cityCodeMap.get(id);
						break;
					}
				}

				// ��ȡ�Ա�
				String id17 = idcard.substring(16, 17);
				if (Integer.parseInt(id17) % 2 != 0) {
					this.gender = "��";
				} else {
					this.gender = "Ů";
				}

				// ��ȡ��������
				String birthday = idcard.substring(6, 14);
				Date birthdate = new SimpleDateFormat("yyyyMMdd")
						.parse(birthday);
				this.birthday = birthdate;
				GregorianCalendar currentDay = new GregorianCalendar();
				currentDay.setTime(birthdate);
				this.year = currentDay.get(Calendar.YEAR);
				this.month = currentDay.get(Calendar.MONTH) + 1;
				this.day = currentDay.get(Calendar.DAY_OF_MONTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public int getAge() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		int age = Integer.parseInt(formatter.format(date))
				- Integer.parseInt(formatter.format(birthday));
		return age;
	}
	
	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public static int getAge(Date birthday) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		int age = Integer.parseInt(formatter.format(date))
				- Integer.parseInt(formatter.format(birthday));
		return age;
	}
	
	
	/**
	 * 15λ���֤��ת18λ
	 * 
	 * @param cardId
	 * @return
	 * @create by soft at 2009-7-9
	 */
	public static String toEighteenId(String cardId) {
		if (cardId.length() != 15)
			return cardId;
		cardId = cardId.substring(0, 6) + "19" + cardId.substring(6, 15);
		cardId = cardId + getVerify(cardId);
		return cardId;
	}

	/**
	 * ����17λ���֤�Ż�ȡ��֤��
	 * 
	 * @param cardId
	 *            17λ���֤��
	 * @return ��֤��
	 * @create by szq at 2009-7-9
	 */
	public static String getVerify(String cardId) {
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(cardId.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];

		return strVerifyCode;
	}

	/**
	 * ����������֤��
	 * 
	 * @param num ����
	 * @param filePath
	 *            �ļ�·��
	 * @param year
	 *            ��1920�꿪ʼ�ڼ������
	 * @create by szq at 2009-8-19
	 */
	public static String genCardId(int num, String filePath, int yearLen) {
		String ret = "";
		FileWriter fw = null;
		try {
			// ����һ��properties�ļ�������
			fw = new FileWriter(filePath);

			String propFile = "areacode.properties";
			// ����һ��properties����
			Properties properties = new Properties();
			// ��ȡproperties
			InputStream file = IdCardUtils.class.getClassLoader()
					.getResourceAsStream(propFile);
			properties.load(file);
			Object[] code = properties.keySet().toArray();
			int size = code.length;
			Random random = new Random();
			for (int i = 0; i < num; i++) {
				String areaCode = (String) code[random.nextInt(size)];
				int year = 1920 + random.nextInt(yearLen);
				int month = random.nextInt(11);
				if (month == 0)
					month = 12;
				int day = 0;
				while (true) {
					day = random.nextInt(31);
					if (!((day == 0 || (month == 4 || month == 6 || month == 9 || month == 11)
							&& day > 30) || (month == 2 && (((year) % 4 > 0 && day > 28) || day > 29)))) {
						break;
					}
				}
				String birthday = String.valueOf(year * 10000 + month * 100
						+ day);
				String randomCode = String.valueOf(1000 + random.nextInt(999))
						.substring(1);
				String verify = getVerify(areaCode + birthday + randomCode);
				ret = areaCode + birthday + randomCode + verify;
				fw.write(ret);
				fw.write("\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return "ʡ�ݣ�" + this.province + ",�Ա�" + this.gender + ",�������ڣ�"
				+ this.birthday;
	}

}