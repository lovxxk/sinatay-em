package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class FeeCalResultBOM implements Serializable{
	//ҵ�񵥺�
	private String certiNo = "";
	//��׼����
	private double basePremium;
	//�����ۿ�
	private double disCount;
	//��������
	private double premium;
	//�����ۿ�
	private double proDiscount;
	//�ػ��ۿ�
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
