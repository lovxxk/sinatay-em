package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


public class StaticVariable implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 门户通调用地址 
	 * 0---18
	 * 1---200
	 */
    public static String interfaceInd = "1";
	public static String getInterfaceInd() {
		return interfaceInd;
	}
}
