package cn.com.sinosoft.ruleEngine.domain;

import java.io.Serializable;

public class QuoteResultBOM implements Serializable {
	//费率
	private double rate;
	//短期费率
	private double shortRate;
	//标准保费
	private double basePremium;
	//春节保费
	private double springPremium;
	//日常费率
	private double disCount;
	//日常保费
	private double premium;
	//促销费率
	private double salesRate;
	//促销保费
	private double salesPremium;
	//优惠费率
	private double privilegeRate;
	//优惠保费
	private double privilegePremium;
	//促销折扣
	private double salesCount;
	//是否销售
	private String chooseMessage;
	//打折后保费
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
