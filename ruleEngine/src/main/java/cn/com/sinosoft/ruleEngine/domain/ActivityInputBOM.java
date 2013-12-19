package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ActivityInputBOM implements Serializable {
	//�ʱ��
	private Date activityDate;
	//����
	private String deptID;
	//����/��Ʒ
	private String riskCode;
	//Ͷ������
	private String proposalNo;
	//Ͷ������
	private String proposalArea;
	//����
	private Double peremiumValue;
	
	//������ʶ
	private String connectFlag ;
	
	//�ձ��ʶ
	private List<String> kindCode ;  
	
	public List<String> getKindCode() {
		return kindCode;
	}

	public void setKindCode(List<String> kindCode) {
		this.kindCode = kindCode;
	}

	public String getConnectFlag() {
		return connectFlag;
	}

	public void setConnectFlag(String connectFlag) {
		this.connectFlag = connectFlag;
	}

	public Double getPeremiumValue() {
		return peremiumValue;
	}

	public void setPeremiumValue(Double peremiumValue) {
		this.peremiumValue = peremiumValue;
	}
	public String getProposalArea() {
		return proposalArea;
	}

	public void setProposalArea(String proposalArea) {
		this.proposalArea = proposalArea;
	}

	public Date getActivityDate() {
		return activityDate;
	}
	
	public void setActivityDate(Date activityDate) {
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
	
	public boolean listContainsString(String str){
	    boolean  flag = false ;
		String[] tempStr = str.split(",");
		for(int i=0;i<tempStr.length;i++){
			 if(this.kindCode.contains(tempStr[i])){
				 flag = true ;
			 }
		}
	   return flag ;
	}
	
}
