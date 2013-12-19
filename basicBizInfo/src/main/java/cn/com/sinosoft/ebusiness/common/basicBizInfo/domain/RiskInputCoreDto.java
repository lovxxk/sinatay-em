package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;
/**
 * 该DTO用于产品定义接口，调用核心的入参
 *
 */
public class RiskInputCoreDto extends RequestBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**险种代码**/
	private String riskCode;
	
	/**险类代码**/
	
	private String riskClassCode;
	
	//set and get method

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskClassCode() {
		return riskClassCode;
	}

	public void setRiskClassCode(String riskClassCode) {
		this.riskClassCode = riskClassCode;
	}
}
