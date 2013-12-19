package cn.com.sinosoft.ebusiness.sys.exception;

public class XmlConcreteException {
	private ExceptionGrade grade = ExceptionGrade.UNSERIOUS;
	private String exceptionCode = "";
	private String exceptionDesc = "";
	private boolean showExceptionCode = false;
	private boolean showExceptionDesc = false;

	public XmlConcreteException(ExceptionGrade grade, String exceptionCode,
			String exceptionDesc,boolean isShowExceptionCode,boolean isShowExceptionDesc) {
		this.grade = grade;
		this.exceptionCode = exceptionCode;
		this.exceptionDesc = exceptionDesc;
		this.showExceptionCode = isShowExceptionCode;
		this.showExceptionDesc = isShowExceptionDesc;
	}

	public ExceptionGrade getGrade() {
		return grade;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}
	public boolean isShowExceptionCode() {
		return showExceptionCode;
	}

	public boolean isShowExceptionDesc() {
		return showExceptionDesc;
	}
	

}
