package cn.com.sinosoft.ebusiness.sys.exception;

/**
 * 
 * 异常级别处理描述类
 * 
 */
public class ExceptionGradeHandle {
	private String exceptionGrade = "0";
	private String isSendSms = "0";
	private String isSendEmail = "0";
	private String exceptionGradeDesc = "";

	public ExceptionGradeHandle(String exceptionGrade, String isSendSms,
			String isSendEmail, String exceptionGradeDesc) {
		this.exceptionGrade = exceptionGrade;
		this.isSendEmail = isSendEmail;
		this.isSendSms = isSendSms;
		this.exceptionGradeDesc = exceptionGradeDesc;
	}

	public String getExceptionGrade() {
		return exceptionGrade;
	}

	public String getIsSendSms() {
		return isSendSms;
	}

	public String getIsSendEmail() {
		return isSendEmail;
	}

	public String getExceptionGradeDesc() {
		return exceptionGradeDesc;
	}

}
