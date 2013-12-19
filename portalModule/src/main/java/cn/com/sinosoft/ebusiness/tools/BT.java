package cn.com.sinosoft.ebusiness.tools;

import org.dom4j.Document;

public class BT {
	
	/**
	 * 判断字符串是否为空
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
	 * 判断/TranData/TranResponse/Flag的值，1返回true,否则false
	 * @param doc：响应报文
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
	 * 获取响应报文Desc项的内容
	 * @param doc：响应报文
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
