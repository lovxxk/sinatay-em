package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

public class RiskAccount implements Serializable{

	/**
	 * @ProjectName: online
	 * @Package:     cn.com.sinosoft.ebusiness.policy.domain
	 * @ClassName:   RiskAccount
	 * @Description: �����˻���Ϣ
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-09-03
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 //��������
	 private String riskName;
	 //�����ֽ��ֵ
	 private String cashValue; 
	 //�����˻����  Y-���˻��������� N-�����˻���������
	 private String insuaccFlag;
	 //������Դ
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
