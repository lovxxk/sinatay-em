package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;
import java.util.Date;

public class FeeCalInputBOM implements Serializable{
	//ҵ�񵥺�
	private String certiNo = "";
	//��Ʒ����
	private String productCode = "";
	//���ִ���
	private String riskCode = "";
	//���α���
	private String kindCode = "";
	//��������
	private String insuredTime = "";
	//���ս��
	private String insuredAmount = "";
	//��������
	private String travelType = "";
	//�¹����
	private String accidentCategory = "";
	//������ʽ
	private String ensureForm = "";
	//����
	private int age = 0;
	//�Ա�
	private String sex = "";
	//ǩ������
	private Date operateDate;
	//�Ƿ��Ա
	private String isMember = "";
	public String getCertiNo() {
		return certiNo;
	}
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getKindCode() {
		return kindCode;
	}
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public String getInsuredTime() {
		return insuredTime;
	}
	public void setInsuredTime(String insuredTime) {
		this.insuredTime = insuredTime;
	}
	public String getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(String insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getAccidentCategory() {
		return accidentCategory;
	}
	public void setAccidentCategory(String accidentCategory) {
		this.accidentCategory = accidentCategory;
	}
	public String getEnsureForm() {
		return ensureForm;
	}
	public void setEnsureForm(String ensureForm) {
		this.ensureForm = ensureForm;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getIsMember() {
		return isMember;
	}
	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}
}
