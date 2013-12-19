package cn.com.sinosoft.lang.math;

public class IntegerToChinese {
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:����ת��Ϊ����
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-6
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// ����ת��Ϊ����
	public static String ConvertToChinese(int x) {
		String retStr = "";
		if (x < 10 && x > 0) {
			switch (x) {
			case 1:
				retStr = "һ";
				break;
			case 2:
				retStr = "��";
				break;

			case 3:
				retStr = "��";
				break;

			case 4:
				retStr = "��";
				break;

			case 5:
				retStr = "��";
				break;
			case 6:
				retStr = "��";
				break;
			case 7:
				retStr = "��";
				break;
			case 8:
				retStr = "��";
				break;
			case 9:
				retStr = "��";
				break;
			}
		} else if (x >= 10 && x < 100) {
			switch (Integer.parseInt((x + "").substring(0, 1))) {
			case 1:
				retStr = "ʮ";
				break;
			case 2:
				retStr = "��ʮ";
				break;
			case 3:
				retStr = "��ʮ";
				break;
			case 4:
				retStr = "��ʮ";
				break;
			case 5:
				retStr = "��ʮ";
				break;
			case 6:
				retStr = "��ʮ";
				break;
			case 7:
				retStr = "��ʮ";
				break;
			case 8:
				retStr = "��ʮ";
				break;
			case 9:
				retStr = "��ʮ";
				break;
			}
			switch (Integer.parseInt((x + "").substring(1, 2))) {
			case 1:
				retStr += "һ";
				break;
			case 2:
				retStr += "��";
				break;
			case 3:
				retStr += "��";
				break;
			case 4:
				retStr += "��";
				break;
			case 5:
				retStr += "��";
				break;
			case 6:
				retStr += "��";
				break;
			case 7:
				retStr += "��";
				break;
			case 8:
				retStr += "��";
				break;
			case 9:
				retStr += "��";
				break;
			}
		}
		return retStr;
	}
}
