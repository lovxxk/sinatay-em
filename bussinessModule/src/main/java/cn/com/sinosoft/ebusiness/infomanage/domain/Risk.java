package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;


public class Risk implements Comparable<Risk>,Serializable {
	/**
	 * @ProjectName: online
	 * @Package: cn.com.sinosoft.ebusiness.policy.domain
	 * @ClassName: Risk
	 * @Description: 险种信息
	 * @Author: jack_xiao
	 * @CreateDate: 2013-09-03
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = 443510694817792235L;
	//险种代码
	private String riskCode;
	//险种名称
	private String riskName;
	//险种标志（M 主险 S 附加险 A两者）
	private String subRiskFlag;
	//保额
	private String amnt;
	//保费
	private String prem;
	//保险年期年龄标志 目前核心只有：Y年  A岁
	private String insuYearFlag;
	//保险年期年龄
	private String insuYear;
	//险种生效日期
	private String cvaliDate;
	//保单险种状态
	private String state;
	//保单险种号码
	private String PolNo;
	//保单号
	private String policyNo;
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getSubRiskFlag() {
		return subRiskFlag;
	}
	public void setSubRiskFlag(String subRiskFlag) {
		this.subRiskFlag = subRiskFlag;
	}
	
	public String getAmnt() {
		return amnt;
	}
	public void setAmnt(String amnt) {
		this.amnt = amnt;
	}
	public String getPrem() {
		return prem;
	}
	public void setPrem(String prem) {
		this.prem = prem;
	}
	public String getInsuYearFlag() {
		return insuYearFlag;
	}
	public void setInsuYearFlag(String insuYearFlag) {
		this.insuYearFlag = insuYearFlag;
	}
	
	
	public String getInsuYear() {
		return insuYear;
	}
	public void setInsuYear(String insuYear) {
		this.insuYear = insuYear;
	}

	public String getCvaliDate() {
		return cvaliDate;
	}
	public void setCvaliDate(String cvaliDate) {
		this.cvaliDate = cvaliDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPolNo() {
		return PolNo;
	}
	public void setPolNo(String polNo) {
		PolNo = polNo;
	}
	
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	//实现按照主险排序
	@Override
	public int compareTo(Risk o) {
		if(!o.getCvaliDate().equals(this.getCvaliDate())){
			return o.getCvaliDate().compareTo(this.getCvaliDate());
		}else{
			//按照主附险
			return this.getSubRiskFlag().compareTo(o.getSubRiskFlag());
		}
	}
}
