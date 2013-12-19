package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

/**
 * 报文扩展域
 *
 */
public class TXInsuranceExtension {

	//用户名
	private String operator;
	
	//请求密码
	private String operatorKey;
	
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
