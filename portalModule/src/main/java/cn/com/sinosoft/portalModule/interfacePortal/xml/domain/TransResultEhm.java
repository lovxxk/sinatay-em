package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

public class TransResultEhm {
	
	private String resultCode;//�������
	private String errorCode;//�쳣����
	
	private String resultInfoDesc;//�����Ϣ
	
	private String errorType;//�쳣����
	
	private String stackTrace;//��ջ��Ϣ

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
