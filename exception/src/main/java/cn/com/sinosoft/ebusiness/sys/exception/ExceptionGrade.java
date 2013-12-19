package cn.com.sinosoft.ebusiness.sys.exception;

/**
 * 
 * 异常级别枚举类
 * 
 */
public enum ExceptionGrade {
	/**
	 * 异常不严重
	 */
	UNSERIOUS("0"),

	/**
	 * 异常严重
	 */
	SERIOUS("1"),

	/**
	 * 异常很严重
	 */
	MORESERIOUS("2"),

	/**
	 * 异常最严重
	 */
	MOSTSERIOUS("3");
	private String value;

	private ExceptionGrade(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}
}
