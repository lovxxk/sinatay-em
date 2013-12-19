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

			// 设置汉字拼音输出的格式
			HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

			t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);

			t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

			t3.setVCharType(HanyuPinyinVCharType.WITH_V);

			String t4 = "";

			int t0 = t1.length;

			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					// 将汉字的几种全拼都存到t2数组中
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					// 取出该汉字全拼的第一种读音并连接到字符串t4后
					t4 += t2[0];
				} else {
					// 如果不是汉字字符，直接取出字符并连接到字符串t4后
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
			// 提取汉字的首字母
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
		// 将字符串转换成字节序列
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
}
