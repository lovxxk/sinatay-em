package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class ActivityResultBOM implements Serializable {
	//供应商公司代码
	private String thirdParterID;
	//商品代码
	private String itemID;
	//活动方式
	private String activityPattern;
	//活动方式N的值
	private String nValue;
	//起始时间
	private String startDate;
	//结束时间
	private String endDate;
	//活动时间段
	private String intervalDate;
	//活动时间
	private String activityDate;
	//地区
	private String deptID;
	//险种/产品
	private String riskCode;
	//投保单号
	private String proposalNo;
	//投保地区
	private String proposalArea;
	public String getProposalArea() {
		return proposalArea;
	}

	public void setProposalArea(String proposalArea) {
		this.proposalArea = proposalArea;
	}

	public String getThirdParterID() {
		return thirdParterID;
	}
	
	public void setThirdParterID(String thirdParterID) {
		this.thirdParterID = thirdParterID;
	}
	
	public String getItemID() {
		return itemID;
	}
	
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getActivityPattern() {
		return activityPattern;
	}
	
	public void setActivityPattern(String activityPattern) {
		this.activityPattern = activityPattern;
	}
	
	public String getnValue() {
		return nValue;
	}
	
	public void setnValue(String nValue) {
		this.nValue = nValue;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getIntervalDate() {
		return intervalDate;
	}
	
	public void setIntervalDate(String intervalDate) {
		this.intervalDate = intervalDate;
	}
	
	public String getActivityDate() {
		return activityDate;
	}
	
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	
	public String getDeptID() {
		return deptID;
	}
	
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
	public String getRiskCode() {
		return riskCode;
	}
	
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	
	public String getProposalNo() {
		return proposalNo;
	}
	
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	
}
