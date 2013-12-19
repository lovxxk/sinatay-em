package cn.com.sinosoft.ebusiness.sale.domain;

import java.util.List;

/**
 *
 */
public class InsureFlowDto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/**���㵥����*/
	private QuoteMain quoteMain;
	/**�����б�*/
	private List<QuoteLiability> quoteLiability;
	/**��Ʒ����*/
	private String productCode;
	/**��Ʒ����*/
	private String productName;
	/**Ͷ�������к�*/
	private String proposalSID;
	/**�������Ƿ񷨶� 0-������1-�Ƿ���*/
	private String isExsitBnf;
	/**0-��Ͷ����1-����Ͷ��*/
	private String insuranceType;
	
	/**����չʾ*/
	/**�ײ�˵��*/
	private String combDesc;
	/**Ͷ����������*/
	private String ageInterval;
	/**������Сֵ*/
	private String ageMid;
	/**�������ֵ*/
	private String ageMax;
	/**�ɷ�����˵��*/
	private String paymentDurationDesc;
	/**�������˵��*/
	private String unitCountDesc;
	
	/**��ס�أ�ʡ��*/
	private String areaCodeProDesc;
	/**��ס�أ��У�*/
	private String areaCodeDesc;
	/**�����ڼ�*/
	private String insuYearFlagDesc;
	
	/**�ж��Ƿ����*/
	private String existDiscountFlag;
	/**�ۿ۱���**/
	private String discountID;
	/**�ж��Ƿ�ɳ齱*/
	private String existLuckDrawFlag;
	
	/**1-�ӹ���0-�Ǽӹ�*/
	private String moreBuyFlag;
	/**�ӹ�Ͷ�������к�*/
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
