package cn.com.sinosoft.lang.math;

public class IntegerToChinese {
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:数组转换为中文
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-6
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// 数字转换为中文
	public static String ConvertToChinese(int x) {
		String retStr = "";
		if (x < 10 && x > 0) {
			switch (x) {
			case 1:
				retStr = "一";
				break;
			case 2:
				retStr = "二";
				break;

			case 3:
				retStr = "三";
				break;

			case 4:
				retStr = "四";
				break;

			case 5:
				retStr = "五";
				break;
			case 6:
				retStr = "六";
				break;
			case 7:
				retStr = "七";
				break;
			case 8:
				retStr = "八";
				break;
			case 9:
				retStr = "九";
				break;
			}
		} else if (x >= 10 && x < 100) {
			switch (Integer.parseInt((x + "").substring(0, 1))) {
			case 1:
				retStr = "十";
				break;
			case 2:
				retStr = "二十";
				break;
			case 3:
				retStr = "三十";
				break;
			case 4:
				retStr = "四十";
				break;
			case 5:
				retStr = "五十";
				break;
			case 6:
				retStr = "六十";
				break;
			case 7:
				retStr = "七十";
				break;
			case 8:
				retStr = "八十";
				break;
			case 9:
				retStr = "九十";
				break;
			}
			switch (Integer.parseInt((x + "").substring(1, 2))) {
			case 1:
				retStr += "一";
				break;
			case 2:
				retStr += "二";
				break;
			case 3:
				retStr += "三";
				break;
			case 4:
				retStr += "四";
				break;
			case 5:
				retStr += "五";
				break;
			case 6:
				retStr += "六";
				break;
			case 7:
				retStr += "七";
				break;
			case 8:
				retStr += "八";
				break;
			case 9:
				retStr += "九";
				break;
			}
		}
		return retStr;
	}
}
