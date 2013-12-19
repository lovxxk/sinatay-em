package cn.com.sinosoft.ebusiness.infomanage.domain;

public class EmailInfo {
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: 保存电子函件信息
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-11
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	//保单号
	private String policyNo;
	//险种名称
	private String riskName;
	//邮箱
	private String email;
	//电子函件订阅类型 01 电子通知书 02 纸质通知书 03 二者皆是  04 都不寄送
	private String subType;
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	
	
}
