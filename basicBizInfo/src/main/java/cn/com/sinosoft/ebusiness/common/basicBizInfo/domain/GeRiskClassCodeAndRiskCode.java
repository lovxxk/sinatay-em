package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;
/**
 * 存放险类与险别信息的载体
 * 数据的值是从kind.xml文件里取的
 *
 */
public class GeRiskClassCodeAndRiskCode {
	
	/**险类代码**/
	private String riskClassCode;
	
	/**险种代码**/
	private String riskCode;
	
	//get and set method
	public String getRiskClassCode() {
		return riskClassCode;
	}
	public void setRiskClassCode(String riskClassCode) {
		this.riskClassCode = riskClassCode;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
}
