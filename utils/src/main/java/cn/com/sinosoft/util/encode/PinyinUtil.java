package cn.com.sinosoft.util.encode;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import cn.com.sinosoft.util.string.StringUtils;

public class PinyinUtil {

	public static String getPinYin(String str) {

		try {
			
			if (StringUtils.isBlank(str)) {
				return "";
			}
			
			char[] t1 = null;

			t1 = str.toCharArray();

			String[] t2 = new String[t1.length];

			// ���ú���ƴ������ĸ�ʽ
			HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);

			t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

			t3.setVCharType(HanyuPinyinVCharType.WITH_V);

			String t4 = "";

			int t0 = t1.length;

			for (int i = 0; i < t0; i++) {
				// �ж��Ƿ�Ϊ�����ַ�
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					// �����ֵļ���ȫƴ���浽t2������
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					// ȡ���ú���ȫƴ�ĵ�һ�ֶ��������ӵ��ַ���t4��
					t4 += t2[0];
				} else {
					// ������Ǻ����ַ���ֱ��ȡ���ַ������ӵ��ַ���t4��
					t4 += Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getPinYinHeadChar(String str) throws BadHanyuPinyinOutputFormatCombination {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// ��ȡ���ֵ�����ĸ
			HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
			outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word, outputFormat);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		// ���ַ���ת�����ֽ�����
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
}
