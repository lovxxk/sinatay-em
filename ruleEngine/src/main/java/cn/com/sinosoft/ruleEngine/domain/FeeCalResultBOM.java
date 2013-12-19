package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class FeeCalResultBOM implements Serializable{
	//业务单号
	private String certiNo = "";
	//标准保费
	private double basePremium;
	//正常折扣
	private double disCount;
	//正常保费
	private double premium;
	//促销折扣
	private double proDiscount;
	//特惠折扣
	private double specialDiscount;
	
	public String getCertiNo() {
		return certiNo;
	}
	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}
	public double getBasePremium() {
		return basePremium;
	}
	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}
	public double getDisCount() {
		return disCount;
	}
	public void setDisCount(double disCount) {
		this.disCount = disCount;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public double getProDiscount() {
		return proDiscount;
	}
	public void setProDiscount(double proDiscount) {
		this.proDiscount = proDiscount;
	}
	public double getSpecialDiscount() {
		return specialDiscount;
	}
	public void setSpecialDiscount(double specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
}
