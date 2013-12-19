package cn.com.sinosoft.ebusiness.tools;

import org.dom4j.Document;

public class BT {
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param s
	 * @return
	 */
	public static boolean isBlank(String s){
		if(s == null || "".equals(s)){
			return true;
		}
		return false;
	}
	
	/**
	 * �ж�/TranData/TranResponse/Flag��ֵ��1����true,����false
	 * @param doc����Ӧ����
	 * @return
	 */
	public static boolean isSuccess(Document doc){
		String flag = doc.selectSingleNode("/TranData/TranResponse/Flag").getText();
		if("1".equals(flag)){
			return true;
		}
		return false;
	}
	
	/**
	 * ��ȡ��Ӧ����Desc�������
	 * @param doc����Ӧ����
	 * @return
	 */
	public static String getDesc(Document doc){
		String desc = doc.selectSingleNode("/TranData/TranResponse/Desc").getText();
		return desc;
	}
	
	public static String trunc(String str) {
		if (null == str)
			return "";
		return str;
	}
	
	
}
