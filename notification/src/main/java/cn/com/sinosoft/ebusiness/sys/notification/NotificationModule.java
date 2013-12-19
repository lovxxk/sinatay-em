/**
 * Copyright (c) 2005-2012 sinosoft.com.cn
 *
 */
package cn.com.sinosoft.ebusiness.sys.notification;

/**
 * ֪ͨ��Դ��ö����
 * 
 *  
 */
public enum NotificationModule {
	/**
	 * ����ģ��
	 */
	ALL("00"),
	/**
	 * �쳣ģ��
	 */
	EXCEPTION("01"),
	/**
	 * �쳣ģ��
	 */
	OSAGENT("02"),

	/**
	 * ������Ӧģ��
	 */
	RESPONSE("03"),
	/**
	 * ����ģ��
	 */
	OTHER("04");

	private String value;

	private NotificationModule(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}
}
