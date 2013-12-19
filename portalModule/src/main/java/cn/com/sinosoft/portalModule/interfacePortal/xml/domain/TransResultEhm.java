package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

public class TransResultEhm {
	
	private String resultCode;//结果代码
	private String errorCode;//异常编码
	
	private String resultInfoDesc;//结果信息
	
	private String errorType;//异常类型
	
	private String stackTrace;//堆栈信息

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getResultInfoDesc() {
		return resultInfoDesc;
	}

	public void setResultInfoDesc(String resultInfoDesc) {
		this.resultInfoDesc = resultInfoDesc;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	

	
	
}
