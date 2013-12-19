package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;

public class TransResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6528523681767334370L;
	
	/**
	 * �������
	 */
	private String resultCode;
	
	/***
	 * ���״̬
	 */
	private String resultStatus;
	
	/***
	 * �������
	 */
	private String resultDesc;
	
	/***
	 * 
	 */
	private String resultCodePrivate;
	
	/***
	 * �����Ϣ
	 */
	private ResultDetailInfo resultInfo;
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getResultCodePrivate() {
		return resultCodePrivate;
	}

	public void setResultCodePrivate(String resultCodePrivate) {
		this.resultCodePrivate = resultCodePrivate;
	}

	public ResultDetailInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultDetailInfo resultInfo) {
		this.resultInfo = resultInfo;
	}
	
}
