package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

public class RiskAccount implements Serializable{

	/**
	 * @ProjectName: online
	 * @Package:     cn.com.sinosoft.ebusiness.policy.domain
	 * @ClassName:   RiskAccount
	 * @Description: 险种账户信息
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-09-03
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 //险种名称
	 private String riskName;
	 //险种现金价值
	 private String cashValue; 
	 //险种账户标记  Y-是账户类型险种 N-不是账户类型险种
	 private String insuaccFlag;
	 //险种来源
	 private String riskScource;
	public String getRiskScource() {
		return riskScource;
	}
	public void setRiskScource(String riskScource) {
		this.riskScource = riskScource;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getCashValue() {
		return cashValue;
	}
	public void setCashValue(String cashValue) {
		this.cashValue = cashValue;
	}
	public String getInsuaccFlag() {
		return insuaccFlag;
	}
	public void setInsuaccFlag(String insuaccFlag) {
		this.insuaccFlag = insuaccFlag;
	}
	
	 
}
