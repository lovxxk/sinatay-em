package cn.com.sinosoft.ebusiness.sale.domain;

import java.util.List;

/**
 *
 */
public class InsureFlowDto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/**试算单主表*/
	private QuoteMain quoteMain;
	/**责任列表*/
	private List<QuoteLiability> quoteLiability;
	/**产品编码*/
	private String productCode;
	/**产品名称*/
	private String productName;
	/**投保单序列号*/
	private String proposalSID;
	/**受益人是否法定 0-法定，1-非法定*/
	private String isExsitBnf;
	/**0-新投保，1-继续投保*/
	private String insuranceType;
	
	/**用于展示*/
	/**套餐说明*/
	private String combDesc;
	/**投保年龄区间*/
	private String ageInterval;
	/**年龄最小值*/
	private String ageMid;
	/**年龄最大值*/
	private String ageMax;
	/**缴费年期说明*/
	private String paymentDurationDesc;
	/**购买份数说明*/
	private String unitCountDesc;
	
	/**常住地（省）*/
	private String areaCodeProDesc;
	/**常住地（市）*/
	private String areaCodeDesc;
	/**保险期间*/
	private String insuYearFlagDesc;
	
	/**判断是否打折*/
	private String existDiscountFlag;
	/**折扣编码**/
	private String discountID;
	/**判断是否可抽奖*/
	private String existLuckDrawFlag;
	
	/**1-加购，0-非加购*/
	private String moreBuyFlag;
	/**加购投保单序列号*/
	private String moreBuyProposalSID;
	private String processValue;
	public QuoteMain getQuoteMain() {
		return quoteMain;
	}
	public void setQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
	}
	public List<QuoteLiability> getQuoteLiability() {
		return quoteLiability;
	}
	public void setQuoteLiability(List<QuoteLiability> quoteLiability) {
		this.quoteLiability = quoteLiability;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProposalSID() {
		return proposalSID;
	}
	public void setProposalSID(String proposalSID) {
		this.proposalSID = proposalSID;
	}
	public String getIsExsitBnf() {
		return isExsitBnf;
	}
	public void setIsExsitBnf(String isExsitBnf) {
		this.isExsitBnf = isExsitBnf;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getCombDesc() {
		return combDesc;
	}
	public void setCombDesc(String combDesc) {
		this.combDesc = combDesc;
	}
	public String getAgeInterval() {
		return ageInterval;
	}
	public void setAgeInterval(String ageInterval) {
		this.ageInterval = ageInterval;
	}
	public String getAgeMid() {
		return ageMid;
	}
	public void setAgeMid(String ageMid) {
		this.ageMid = ageMid;
	}
	public String getAgeMax() {
		return ageMax;
	}
	public void setAgeMax(String ageMax) {
		this.ageMax = ageMax;
	}
	public String getPaymentDurationDesc() {
		return paymentDurationDesc;
	}
	public void setPaymentDurationDesc(String paymentDurationDesc) {
		this.paymentDurationDesc = paymentDurationDesc;
	}
	public String getUnitCountDesc() {
		return unitCountDesc;
	}
	public void setUnitCountDesc(String unitCountDesc) {
		this.unitCountDesc = unitCountDesc;
	}
	public String getAreaCodeProDesc() {
		return areaCodeProDesc;
	}
	public void setAreaCodeProDesc(String areaCodeProDesc) {
		this.areaCodeProDesc = areaCodeProDesc;
	}
	public String getAreaCodeDesc() {
		return areaCodeDesc;
	}
	public void setAreaCodeDesc(String areaCodeDesc) {
		this.areaCodeDesc = areaCodeDesc;
	}
	public String getInsuYearFlagDesc() {
		return insuYearFlagDesc;
	}
	public void setInsuYearFlagDesc(String insuYearFlagDesc) {
		this.insuYearFlagDesc = insuYearFlagDesc;
	}
	public String getExistDiscountFlag() {
		return existDiscountFlag;
	}
	public void setExistDiscountFlag(String existDiscountFlag) {
		this.existDiscountFlag = existDiscountFlag;
	}
	public String getDiscountID() {
		return discountID;
	}
	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}
	public String getExistLuckDrawFlag() {
		return existLuckDrawFlag;
	}
	public void setExistLuckDrawFlag(String existLuckDrawFlag) {
		this.existLuckDrawFlag = existLuckDrawFlag;
	}
	public String getMoreBuyFlag() {
		return moreBuyFlag;
	}
	public void setMoreBuyFlag(String moreBuyFlag) {
		this.moreBuyFlag = moreBuyFlag;
	}
	public String getMoreBuyProposalSID() {
		return moreBuyProposalSID;
	}
	public void setMoreBuyProposalSID(String moreBuyProposalSID) {
		this.moreBuyProposalSID = moreBuyProposalSID;
	}
	public String getProcessValue() {
		return processValue;
	}
	public void setProcessValue(String processValue) {
		this.processValue = processValue;
	}
	
}
