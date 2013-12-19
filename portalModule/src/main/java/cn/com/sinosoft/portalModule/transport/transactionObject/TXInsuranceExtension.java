package cn.com.sinosoft.portalModule.transport.transactionObject;

import java.io.Serializable;


/**
 * 报文扩展域
 *  
 *
 */
public class TXInsuranceExtension implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3791836647183228456L;
	
	/** 属性系统代码 */
	private String systemCode;

	/** 属性系统名称 */
	private String systemName;
	
	//用户名
	protected String operator;
	
	//请求密码
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
