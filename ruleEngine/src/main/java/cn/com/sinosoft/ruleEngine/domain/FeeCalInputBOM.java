package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;
import java.util.Date;

public class FeeCalInputBOM implements Serializable{
	//业务单号
	private String certiNo = "";
	//产品代码
	private String productCode = "";
	//险种代码
	private String riskCode = "";
	//责任编码
	private String kindCode = "";
	//保险期限
	private String insuredTime = "";
	//保险金额
	private String insuredAmount = "";
	//旅游类型
	private String travelType = "";
	//事故类别
	private String accidentCategory = "";
	//保障形式
	private String ensureForm = "";
	//年龄
	private int age = 0;
	//性别
	private String sex = "";
	//签单日期
	private Date operateDate;
	//是否会员
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
