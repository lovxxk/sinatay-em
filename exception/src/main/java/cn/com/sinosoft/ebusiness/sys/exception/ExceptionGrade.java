package cn.com.sinosoft.ebusiness.sys.exception;

/**
 * 
 * �쳣����ö����
 * 
 */
public enum ExceptionGrade {
	/**
	 * �쳣������
	 */
	UNSERIOUS("0"),

	/**
	 * �쳣����
	 */
	SERIOUS("1"),

	/**
	 * �쳣������
	 */
	MORESERIOUS("2"),

	/**
	 * �쳣������
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
