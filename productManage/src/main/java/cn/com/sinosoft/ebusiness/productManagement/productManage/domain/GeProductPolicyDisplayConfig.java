package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeProductApplicantConfig
 */
@Entity
@Table(name = "GE_PRODUCT_POLICYDISPLAYCONFIG")
public class GeProductPolicyDisplayConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;
	
	/** 属性交易流水号 */
	private String transSerialNumber;
	
	/** 属性保单号 */
	private String policySerialNumber;

	/** 属性投保单号 */
	private String applicationNumber;

	/** 属性投保单印刷号 */
	private String applicationSerialNumber;
	
	/** 属性保单状态 */
	private String policyStatus;
	
	/** 属性保额 */
	private String insuredAmount;

	/** 属性保费 */
	private String premium;
	
	/** 属性折扣保费 */
	private String discountPremium;
	
	/** 属性总保费 */
	private String grossPremium;
	
	/** 属性总保额 */
	private String faceAmount;
	
	/** 属性产品代码 */
	private String productCode;

	/** 属性产品名称 */
	private String productName;
	
	/** 属性首期保费 */
	private String firstPremium;

	/** 属性首次缴费金额 */
	private String initialPremAmt;

	/** 属性保障年期 */
	private String benefitPeriod;

	/** 属性保障年期类型 */
	private String benefitPeriodType;

	/** 属性缴费年期 */
	private String paymentDuration;

	/** 属性缴费年期类型 */
	private String paymentDurationMode;

	/** 属性支付方式 */
	private String paymentMethod;

	/** 属性生存金领取方式 */
	private String benefitMode;

	/** 属性红利领取方式 */
	private String divType;

	/** 属性附加保费/手续金额 */
	private String excessPremAmt;

	/** 属性纸质保单快递费 */
	private String policyDeliveryFee;

	/** 属性签约日期 */
	private String signedDate;

	/** 属性保单生效日 */
	private String inceptionDate;
	
	/** 属性保单申请日*/
	private String applicationDate;
	
	/** 属性保险起期  */
	private String insuranceStartPeriod;
	
	/** 属性保险止期 */
	private String insuranceEndPeriod;
	
	/** 属性受益方式  */
	private String beneficiaryMode;
	
	/** 属性投保受理日期 */
	private String submissionDate;

	/** 属性份数 */
	private String unitCount;
	
	/** 属性特别声明 */
	private String specialStatement;

	/** 属性活动代码 */
	private String campaignCode;

	/** 属性活动名称 */
	private String campaignName;

	/** 属性折扣代码 */
	private String discountTypeCode;

	/** 属性折扣系数 */
	private String discountRate;

	/** 属性套餐代码 */
	private String comboCode;

	/** 属性套餐名称 */
	private String comboName;
	
	/** 属性个人客户 */
	private String personalUserSerialNo;

	/** 属性代理人代码 */
	private String agentCode;

	/** 属性代理人协议号 */
	private String agreementNo;

	/** 属性代理人姓名 */
	private String agentName;

	/** 属性代理人所在机构编码 */
	private String departmentNo;

	/** 属性代理人所在机构名称 */
	private String departmentName;
	
	/** 属性渠道代码 */
	private String intermediaryCode;
	
	/** 属性渠道名称 */
	private String intermediaryName;
	
	/** 属性网点代码 */
	private String branchCode;
	
	/** 属性网点代码 */
	private String branchName;
	
	/** 属性机构代码 */
	private String organizationCode;

	/** 属性机构名称 */
	private String organizationName;
	
	/** 属性合作机构代码 */
	private String institutionCode;

	/** 属性合作机构名称 */
	private String institutionName;
	
	/** 属性现金价值 */
	private String cashValue;

	/** 属性保全受理时间*/
	private String preserveAcceptTime;
	
	/** 属性保全生效时间*/
	private String preserveEffectiveTime;
	
	/** 属性撤单时间*/
	private String reFundPolicyTime;
	
	/** 属性撤单成功时间*/
	private String reFundPolicySuccessTime;

	/** 属性 续期缴费形式 */
	private String exPayMode;

	/** 属性 保单递送方式 */
	private String getPolMode;

	/** 属性 特别约定 */
	private String specContent;

	/** 属性 发票印刷号码 */
	private String tempFeeNo;

	/** 属性 代理人组别 */
	private String agentGroup;

	/** 属性 合同争议处理方式 */
	private String disputedFlag;

	/** 属性 仲裁委员会名称 */
	private String acName;

	/** 属性 是否回访 */
	private String isVisit;

	/** 属性 绑定标志 */
	private String isBind;
	
	/** 属性续保标志 */
	private String renewalFlag;
	
	/** 属性币别 */
	private String currency;
	
	/** 属性业务领域 */
	private String businessArea;
	
	/** 属性原因 */
	private String reason;
	
	/** 核保时间 */
	private String precheckDate;
	
	/**属性集团业务来源（WEB）**/
	private String businessSource;
	
	/**属性集团渠道代码（W——网销）**/
	private String groupChannel;
	
	/** 属性sellType（20） **/
	private String sellType;

	/**
	 * 类GeProductApplicantConfig的默认构造方法
	 */
	public GeProductPolicyDisplayConfig() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}

	@Column(name = "TRANSSERIALNUMBER")
	public String getTransSerialNumber() {
		return transSerialNumber;
	}

	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}

	@Column(name = "POLICYSERIALNUMBER")
	public String getPolicySerialNumber() {
		return policySerialNumber;
	}

	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	@Column(name = "APPLICATIONNUMBER")
	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@Column(name = "APPLICATIONSERIALNUMBER")
	public String getApplicationSerialNumber() {
		return applicationSerialNumber;
	}

	public void setApplicationSerialNumber(String applicationSerialNumber) {
		this.applicationSerialNumber = applicationSerialNumber;
	}

	@Column(name = "POLICYSTATUS")
	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	@Column(name = "INSUREDAMOUNT")
	public String getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(String insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	@Column(name = "PREMIUM")
	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	@Column(name = "DISCOUNTPREMIUM")
	public String getDiscountPremium() {
		return discountPremium;
	}

	public void setDiscountPremium(String discountPremium) {
		this.discountPremium = discountPremium;
	}

	@Column(name = "GROSSPREMIUM")
	public String getGrossPremium() {
		return grossPremium;
	}

	public void setGrossPremium(String grossPremium) {
		this.grossPremium = grossPremium;
	}

	@Column(name = "FACEAMOUNT")
	public String getFaceAmount() {
		return faceAmount;
	}

	public void setFaceAmount(String faceAmount) {
		this.faceAmount = faceAmount;
	}

	@Column(name = "PRODUCTCODE")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "FIRSTPREMIUM")
	public String getFirstPremium() {
		return firstPremium;
	}

	public void setFirstPremium(String firstPremium) {
		this.firstPremium = firstPremium;
	}

	@Column(name = "INITIALPREMAMT")
	public String getInitialPremAmt() {
		return initialPremAmt;
	}

	public void setInitialPremAmt(String initialPremAmt) {
		this.initialPremAmt = initialPremAmt;
	}

	@Column(name = "BENEFITPERIOD")
	public String getBenefitPeriod() {
		return benefitPeriod;
	}

	public void setBenefitPeriod(String benefitPeriod) {
		this.benefitPeriod = benefitPeriod;
	}

	@Column(name = "BENEFITPERIODTYPE")
	public String getBenefitPeriodType() {
		return benefitPeriodType;
	}

	public void setBenefitPeriodType(String benefitPeriodType) {
		this.benefitPeriodType = benefitPeriodType;
	}

	@Column(name = "PAYMENTDURATION")
	public String getPaymentDuration() {
		return paymentDuration;
	}

	public void setPaymentDuration(String paymentDuration) {
		this.paymentDuration = paymentDuration;
	}

	@Column(name = "PAYMENTDURATIONMODE")
	public String getPaymentDurationMode() {
		return paymentDurationMode;
	}

	public void setPaymentDurationMode(String paymentDurationMode) {
		this.paymentDurationMode = paymentDurationMode;
	}

	@Column(name = "PAYMENTMETHOD")
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "BENEFITMODE")
	public String getBenefitMode() {
		return benefitMode;
	}

	public void setBenefitMode(String benefitMode) {
		this.benefitMode = benefitMode;
	}

	@Column(name = "DIVTYPE")
	public String getDivType() {
		return divType;
	}

	public void setDivType(String divType) {
		this.divType = divType;
	}

	@Column(name = "EXCESSPREMAMT")
	public String getExcessPremAmt() {
		return excessPremAmt;
	}

	public void setExcessPremAmt(String excessPremAmt) {
		this.excessPremAmt = excessPremAmt;
	}

	@Column(name = "POLICYDELIVERYFEE")
	public String getPolicyDeliveryFee() {
		return policyDeliveryFee;
	}

	public void setPolicyDeliveryFee(String policyDeliveryFee) {
		this.policyDeliveryFee = policyDeliveryFee;
	}

	@Column(name = "SIGNEDDATE")
	public String getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}

	@Column(name = "INCEPTIONDATE")
	public String getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	@Column(name = "APPLICATIONDATE")
	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Column(name = "INSURANCESTARTPERIOD")
	public String getInsuranceStartPeriod() {
		return insuranceStartPeriod;
	}

	public void setInsuranceStartPeriod(String insuranceStartPeriod) {
		this.insuranceStartPeriod = insuranceStartPeriod;
	}

	@Column(name = "INSURANCEENDPERIOD")
	public String getInsuranceEndPeriod() {
		return insuranceEndPeriod;
	}

	public void setInsuranceEndPeriod(String insuranceEndPeriod) {
		this.insuranceEndPeriod = insuranceEndPeriod;
	}

	@Column(name = "BENEFICIARYMODE")
	public String getBeneficiaryMode() {
		return beneficiaryMode;
	}

	public void setBeneficiaryMode(String beneficiaryMode) {
		this.beneficiaryMode = beneficiaryMode;
	}

	@Column(name = "SUBMISSIONDATE")
	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Column(name = "UNITCOUNT")
	public String getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}

	@Column(name = "SPECIALSTATEMENT")
	public String getSpecialStatement() {
		return specialStatement;
	}

	public void setSpecialStatement(String specialStatement) {
		this.specialStatement = specialStatement;
	}

	@Column(name = "CAMPAIGNCODE")
	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	@Column(name = "CAMPAIGNNAME")
	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	@Column(name = "DISCOUNTTYPECODE")
	public String getDiscountTypeCode() {
		return discountTypeCode;
	}

	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	@Column(name = "DISCOUNTRATE")
	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	@Column(name = "COMBOCODE")
	public String getComboCode() {
		return comboCode;
	}

	public void setComboCode(String comboCode) {
		this.comboCode = comboCode;
	}

	@Column(name = "COMBONAME")
	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	@Column(name = "PERSONALUSERSERIALNO")
	public String getPersonalUserSerialNo() {
		return personalUserSerialNo;
	}

	public void setPersonalUserSerialNo(String personalUserSerialNo) {
		this.personalUserSerialNo = personalUserSerialNo;
	}

	@Column(name = "AGENTCODE")
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	@Column(name = "AGREEMENTNO")
	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	@Column(name = "AGENTNAME")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Column(name = "DEPARTMENTNO")
	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	@Column(name = "DEPARTMENTNAME")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "INTERMEDIARYCODE")
	public String getIntermediaryCode() {
		return intermediaryCode;
	}

	public void setIntermediaryCode(String intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}

	@Column(name = "INTERMEDIARYNAME")
	public String getIntermediaryName() {
		return intermediaryName;
	}

	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}

	@Column(name = "BRANCHCODE")
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Column(name = "BRANCHNAME")
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "ORGANIZATIONCODE")
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	@Column(name = "ORGANIZATIONNAME")
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Column(name = "INSTITUTIONCODE")
	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	@Column(name = "INSTITUTIONNAME")
	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	@Column(name = "CASHVALUE")
	public String getCashValue() {
		return cashValue;
	}

	public void setCashValue(String cashValue) {
		this.cashValue = cashValue;
	}

	@Column(name = "PRESERVEACCEPTTIME")
	public String getPreserveAcceptTime() {
		return preserveAcceptTime;
	}

	public void setPreserveAcceptTime(String preserveAcceptTime) {
		this.preserveAcceptTime = preserveAcceptTime;
	}

	@Column(name = "PRESERVEEFFECTIVETIME")
	public String getPreserveEffectiveTime() {
		return preserveEffectiveTime;
	}

	public void setPreserveEffectiveTime(String preserveEffectiveTime) {
		this.preserveEffectiveTime = preserveEffectiveTime;
	}

	@Column(name = "REFUNDPOLICYTIME")
	public String getReFundPolicyTime() {
		return reFundPolicyTime;
	}

	public void setReFundPolicyTime(String reFundPolicyTime) {
		this.reFundPolicyTime = reFundPolicyTime;
	}

	@Column(name = "REFUNDPOLICYSUCCESSTIME")
	public String getReFundPolicySuccessTime() {
		return reFundPolicySuccessTime;
	}

	public void setReFundPolicySuccessTime(String reFundPolicySuccessTime) {
		this.reFundPolicySuccessTime = reFundPolicySuccessTime;
	}

	@Column(name = "EXPAYMODE")
	public String getExPayMode() {
		return exPayMode;
	}

	public void setExPayMode(String exPayMode) {
		this.exPayMode = exPayMode;
	}

	@Column(name = "GETPOLMODE")
	public String getGetPolMode() {
		return getPolMode;
	}

	public void setGetPolMode(String getPolMode) {
		this.getPolMode = getPolMode;
	}

	@Column(name = "SPECCONTENT")
	public String getSpecContent() {
		return specContent;
	}

	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	@Column(name = "TEMPFEENO")
	public String getTempFeeNo() {
		return tempFeeNo;
	}

	public void setTempFeeNo(String tempFeeNo) {
		this.tempFeeNo = tempFeeNo;
	}

	@Column(name = "AGENTGROUP")
	public String getAgentGroup() {
		return agentGroup;
	}

	public void setAgentGroup(String agentGroup) {
		this.agentGroup = agentGroup;
	}

	@Column(name = "DISPUTEDFLAG")
	public String getDisputedFlag() {
		return disputedFlag;
	}

	public void setDisputedFlag(String disputedFlag) {
		this.disputedFlag = disputedFlag;
	}

	@Column(name = "ACNAME")
	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	@Column(name = "ISVISIT")
	public String getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(String isVisit) {
		this.isVisit = isVisit;
	}

	@Column(name = "ISBIND")
	public String getIsBind() {
		return isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	@Column(name = "RENEWALFLAG")
	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	@Column(name = "CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "PRECHECKDATE")
	public String getPrecheckDate() {
		return precheckDate;
	}

	public void setPrecheckDate(String precheckDate) {
		this.precheckDate = precheckDate;
	}

	@Column(name = "BUSINESSSOURCE")
	public String getBusinessSource() {
		return businessSource;
	}

	public void setBusinessSource(String businessSource) {
		this.businessSource = businessSource;
	}

	@Column(name = "GROUPCHANNEL")
	public String getGroupChannel() {
		return groupChannel;
	}

	public void setGroupChannel(String groupChannel) {
		this.groupChannel = groupChannel;
	}

	@Column(name = "SELLTYPE")
	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	
}
