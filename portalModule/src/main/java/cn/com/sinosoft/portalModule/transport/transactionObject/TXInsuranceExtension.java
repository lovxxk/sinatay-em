package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;


/**
 * ������չ��
 *  
 *
 */
public class TXInsuranceExtension implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3791836647183228456L;
	
	/** ����ϵͳ���� */
	private String systemCode;

	/** ����ϵͳ���� */
	private String systemName;
	
	//�û���
	protected String operator;
	
	//��������
	protected String operatorKey;
	
	
	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorKey() {
		return operatorKey;
	}

	public void setOperatorKey(String operatorKey) {
		this.operatorKey = operatorKey;
	}
}
