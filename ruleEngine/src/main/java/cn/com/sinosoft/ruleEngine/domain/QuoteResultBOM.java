package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class QuoteResultBOM implements Serializable {
	//����
	private double rate;
	//���ڷ���
	private double shortRate;
	//��׼����
	private double basePremium;
	//���ڱ���
	private double springPremium;
	//�ճ�����
	private double disCount;
	//�ճ�����
	private double premium;
	//��������
	private double salesRate;
	//��������
	private double salesPremium;
	//�Żݷ���
	private double privilegeRate;
	//�Żݱ���
	private double privilegePremium;
	//�����ۿ�
	private double salesCount;
	//�Ƿ�����
	private String chooseMessage;
	//���ۺ󱣷�
	private double discountPremium;
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getShortRate() {
		return shortRate;
	}
	public void setShortRate(double shortRate) {
		this.shortRate = shortRate;
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
	public double getSalesRate() {
		return salesRate;
	}
	public void setSalesRate(double salesRate) {
		this.salesRate = salesRate;
	}
	public double getSalesPremium() {
		return salesPremium;
	}
	public void setSalesPremium(double salesPremium) {
		this.salesPremium = salesPremium;
	}
	public double getPrivilegeRate() {
		return privilegeRate;
	}
	public void setPrivilegeRate(double privilegeRate) {
		this.privilegeRate = privilegeRate;
	}
	public double getPrivilegePremium() {
		return privilegePremium;
	}
	public void setPrivilegePremium(double privilegePremium) {
		this.privilegePremium = privilegePremium;
	}
	public double getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(double salesCount) {
		this.salesCount = salesCount;
	}
	public String getChooseMessage() {
		return chooseMessage;
	}
	public void setChooseMessage(String chooseMessage) {
		this.chooseMessage = chooseMessage;
	}
	public double getSpringPremium() {
		return springPremium;
	}
	public void setSpringPremium(double springPremium) {
		this.springPremium = springPremium;
	}
	public double getDiscountPremium() {
		return discountPremium;
	}
	public void setDiscountPremium(double discountPremium) {
		this.discountPremium = discountPremium;
	}
	
}
