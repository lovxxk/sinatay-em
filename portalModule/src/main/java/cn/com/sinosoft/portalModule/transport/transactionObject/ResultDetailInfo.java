package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;

public class ResultDetailInfo implements Serializable {
	/**
	 * ��ϸ�����Ϣ
	 */
	private static final long serialVersionUID = 8134740710274885356L;
	
	/**
	 * ��Ϣ���� 
	 */
	private String infoCode;

	/**
	 * ��Ϣ����
	 */
	private String infoMessage;
	
	public String getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	public String getInfoMessage() {
		return infoMessage;
	}
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	
	public String toString() {
		return infoCode + ":" +  infoMessage;
	}
	
	
}
