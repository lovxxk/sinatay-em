package cn.com.sinosoft.ebusiness.sale.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

/**
 * 试算单
 * @author wcl
 *
 */
@Entity
@Table(name = "QUOTE_MAIN")
@JsonAutoDetect
public class QuoteMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性试算单号 */
	private String quoteNo;
	
	/** 属性产品Eid */
	private String eid;
	
	/** 属性核心试算单号 */
	private String hxssProposalNo;
	
	/** 属性投保单号*/
	private String proposalSID;
	
	/** 属性试算单状态 01:正常02:已转投保03:已转电销04:已取消05:已承保09:已删除 10:下架 */
	private Integer quoteStatus;

	/**试算单步骤 1试算 2填写投保单 3确认投保单*/
	private Integer step;

	/** 属性试算单状态名称 */
	private String quoteStatusName;

	/** 属性试算单状态描述 */
	private String quoteStatusDesc;
	
	/** 属性保额 */
	private BigDecimal insuredAmount;

	/** 属性保费 */
	private BigDecimal premium;
	
	/** 属性折扣保费 */
	private BigDecimal discountPremium;
	
	/** 属性总保费 */
	private BigDecimal grossPremium;
	
	/** 属性总保额 */
	private BigDecimal faceAmount;
	
	/** 属性产品代码 */
	private String productCode;

	/** 属性产品名称 */
	private String productName;
	
	/** 属性首期保费 */
	private BigDecimal firstPremium;

	/** 属性首次缴费金额 */
	private BigDecimal initialPremAmt;

	/** 属性保障年期 */
	private Integer benefitPeriod;

	/** 属性保障年期类型 */
	private Integer benefitPeriodType;

	/** 属性缴费方式 */
	private Integer paymentMode;

	/** 属性缴费年期 */
	private Integer paymentDuration;

	/** 属性缴费年期类型 */
	private Integer paymentDurationMode;

	/** 属性支付方式 */
	private Integer paymentMethod;

	/** 属性生存金领取方式 */
	private Integer benefitMode;

	/** 属性红利领取方式 */
	private Integer divType;

	/** 属性年金领取期限 */
	private String annuityPayoutDuration;

	/** 属性年金领取期限类型 */
	private Integer annuityPayoutDurationMode;

	/** 属性年金领取起始年龄 */
	private String payoutStart;

	/** 属性年金领取终止年龄 */
	private String payoutEnd;

	/** 属性附加保费/手续金额 */
	private BigDecimal excessPremAmt;

	/** 属性纸质试算单快递费 */
	private BigDecimal policyDeliveryFee;

	/** 属性自动核保警告 */
	private Integer policyStatusMessage;
	
	/** 属性签约日期 */
	private Date inputDate;
	
	/** 属性试算单申请日*/
	private Date applicationDate = new Date();
	
	/** 属性保险起期  */
	private Date insuranceStartPeriod;
	
	/** 属性保险止期 */
	private Date insuranceEndPeriod;
	
	/** 属性受益方式  */
	private Integer beneficiaryMode;
	
	/** 属性份数 */
	private Integer unitCount = 1;
	
	/** 属性特别声明 */
	private String specialStatement;
	
	/** 属性阅读并理解相关信息 */
	private String contractNames;
	
	/** 属性银行代码 */
	private String bankCode;
	
	/** 属性是否投递发票 */
	private Integer deliveryInvoice;

	/** 属性是否投递纸质试算单 */
	private Integer deliveryHardCopy;

	/** 属性是否发送电子试算单 */
	private Integer deliveryEPolicy;

	/** 属性活动代码 */
	private String campaignCode;

	/** 属性活动名称 */
	private String campaignName;

	/** 属性折扣代码 */
	private String discountTypeCode;

	/** 属性折扣系数 */
	private BigDecimal discountRate;

	/** 属性否申请一年期自动续保 */
	private Integer autoRenewable;

	/** 属性套餐代码 */
	private String comboCode;

	/** 属性套餐名称 */
	private String comboName;
	
	/** 属性用户号 */
	private String userId;
	
	/** 属性用户类型 */
	private String userType;

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
	
	/** 属性区域代码(省) */
	private String areaCodePro;

	/** 属性区域代码(市) */
	private String areaCode;

	/** 属性离开环节 */
	private String leaveSegment;

	/** 属性离开方式 */
	private String leaveWay;
	
	/** 属性续保标志 */
	private String renewalFlag;

	/** 属性原保单号 */
	private String oldlPolicyNo;

	/** 属性币别 */
	private String currency;
	
	/** 属性录入小时 */
	private String inputHour;
	
	/** 属性生效日期 */
	private Date inceptionDate;
	
	/** 属性截止时间 */
	private Date endDate;

	/** 属性查询码失效日期 */
	private Date invalidDate;
	
	/** 属性标识位 */
	private String flag;
	
	/** 属性业务来源（WEB） **/
	private String businessSource;

	/** 属性渠道代码（W——网销） **/
	private String groupChannel;
	
	/** 属性sellType（20） **/
	private String sellType;
	
	/** 属性推荐人类型 1：好友、2：代理人 */
	private String recommendType;
	
	/** 属性推荐人 */
	private String recommend;

	/** 属性 续期缴费形式 */
	private Integer exPayMode;

	/** 属性 试算单递送方式 */
	private Integer getPolMode;

	/** 属性 试算单密码 */
	private String password;

	/** 属性 特别约定 */
	private String specContent;

	/** 属性 发票印刷号码 */
	private String tempFeeNo;

	/** 属性 代理人组别 */
	private String agentGroup;
	
	/** 属性投保人 */
	private QuoteApplicant quoteApplicant;
	
	/** 属性被保人数 */
	private Integer quoteInsuredNumber;
	
	/** 属性被保险人 */
	private List<QuoteInsured> quoteInsureds = new ArrayList<QuoteInsured>(0);

	/** 属性受益人数 */
	private Integer quoteBeneficiaryNumber;
	
	/** 属性受益人 */
	private List<QuoteBeneficiary> quoteBeneficiaries = new ArrayList<QuoteBeneficiary>(0);
	
	/** 属性保险责任数 */
	private Integer quoteLiabilityNumber;
	
	/** 属性 保险责任 */
	private List<QuoteLiability> quoteLiabilities = new ArrayList<QuoteLiability>(0);
	
	/** 属性投保告知数 */
	private Integer quoteInsureInformBookNumber;
	
	/** 属性 投保告知 */
	private List<QuoteInsureInformBook> quoteInsureInformBooks = new ArrayList<QuoteInsureInformBook>(0);

	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**投保年龄区间*/
	private String ageInterval;
	
	/**套餐说明*/
	private String combDesc;
	
	/**保障方案*/
	private String benefitDesc;
	
//	/**投保单*/
//	private InsurancePolicy insurancePolicy;
//	
//	/**订单*/
//	private OrderForm orderForm;
	
	public QuoteMain() {
	}

	@Id
	@Column(name = "QUOTENO")
	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	@Column(name = "HXSSPROPOSALNO")
	public String getHxssProposalNo() {
		return hxssProposalNo;
	}

	public void setHxssProposalNo(String hxssProposalNo) {
		this.hxssProposalNo = hxssProposalNo;
	}

	@Column(name = "QUOTESTATUS")
	public Integer getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(Integer quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	@Column(name = "QUOTESTATUSNAME")
	public String getQuoteStatusName() {
		return quoteStatusName;
	}

	public void setQuoteStatusName(String quoteStatusName) {
		this.quoteStatusName = quoteStatusName;
	}

	@Column(name = "QUOTESTATUSDESC")
	public String getQuoteStatusDesc() {
		return quoteStatusDesc;
	}

	public void setQuoteStatusDesc(String quoteStatusDesc) {
		this.quoteStatusDesc = quoteStatusDesc;
	}

	@Column(name = "INSUREDAMOUNT")
	public BigDecimal getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(BigDecimal insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	@Column(name = "PREMIUM")
	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	@Column(name = "DISCOUNTPREMIUM")
	public BigDecimal getDiscountPremium() {
		return discountPremium;
	}

	public void setDiscountPremium(BigDecimal discountPremium) {
		this.discountPremium = discountPremium;
	}

	@Column(name = "GROSSPREMIUM")
	public BigDecimal getGrossPremium() {
		return grossPremium;
	}

	public void setGrossPremium(BigDecimal grossPremium) {
		this.grossPremium = grossPremium;
	}

	@Column(name = "FACEAMOUNT")
	public BigDecimal getFaceAmount() {
		return faceAmount;
	}

	public void setFaceAmount(BigDecimal faceAmount) {
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
	public BigDecimal getFirstPremium() {
		return firstPremium;
	}

	public void setFirstPremium(BigDecimal firstPremium) {
		this.firstPremium = firstPremium;
	}

	@Column(name = "INITIALPREMAMT")
	public BigDecimal getInitialPremAmt() {
		return initialPremAmt;
	}

	public void setInitialPremAmt(BigDecimal initialPremAmt) {
		this.initialPremAmt = initialPremAmt;
	}

	@Column(name = "BENEFITPERIOD")
	public Integer getBenefitPeriod() {
		return benefitPeriod;
	}

	public void setBenefitPeriod(Integer benefitPeriod) {
		this.benefitPeriod = benefitPeriod;
	}

	@Column(name = "BENEFITPERIODTYPE")
	public Integer getBenefitPeriodType() {
		return benefitPeriodType;
	}

	public void setBenefitPeriodType(Integer benefitPeriodType) {
		this.benefitPeriodType = benefitPeriodType;
	}

	@Column(name = "PAYMENTMODE")
	public Integer getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Column(name = "PAYMENTDURATION")
	public Integer getPaymentDuration() {
		return paymentDuration;
	}

	public void setPaymentDuration(Integer paymentDuration) {
		this.paymentDuration = paymentDuration;
	}

	@Column(name = "PAYMENTDURATIONMODE")
	public Integer getPaymentDurationMode() {
		return paymentDurationMode;
	}

	public void setPaymentDurationMode(Integer paymentDurationMode) {
		this.paymentDurationMode = paymentDurationMode;
	}

	@Column(name = "PAYMENTMETHOD")
	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "BENEFITMODE")
	public Integer getBenefitMode() {
		return benefitMode;
	}

	public void setBenefitMode(Integer benefitMode) {
		this.benefitMode = benefitMode;
	}

	@Column(name = "DIVTYPE")
	public Integer getDivType() {
		return divType;
	}

	public void setDivType(Integer divType) {
		this.divType = divType;
	}

	@Column(name = "ANNUITYPAYOUTDURATION")
	public String getAnnuityPayoutDuration() {
		return annuityPayoutDuration;
	}

	public void setAnnuityPayoutDuration(String annuityPayoutDuration) {
		this.annuityPayoutDuration = annuityPayoutDuration;
	}

	@Column(name = "ANNUITYPAYOUTDURATIONMODE")
	public Integer getAnnuityPayoutDurationMode() {
		return annuityPayoutDurationMode;
	}

	public void setAnnuityPayoutDurationMode(Integer annuityPayoutDurationMode) {
		this.annuityPayoutDurationMode = annuityPayoutDurationMode;
	}

	@Column(name = "PAYOUTSTART")
	public String getPayoutStart() {
		return payoutStart;
	}

	public void setPayoutStart(String payoutStart) {
		this.payoutStart = payoutStart;
	}

	@Column(name = "PAYOUTEND")
	public String getPayoutEnd() {
		return payoutEnd;
	}

	public void setPayoutEnd(String payoutEnd) {
		this.payoutEnd = payoutEnd;
	}

	@Column(name = "EXCESSPREMAMT")
	public BigDecimal getExcessPremAmt() {
		return excessPremAmt;
	}

	public void setExcessPremAmt(BigDecimal excessPremAmt) {
		this.excessPremAmt = excessPremAmt;
	}

	@Column(name = "POLICYDELIVERYFEE")
	public BigDecimal getPolicyDeliveryFee() {
		return policyDeliveryFee;
	}

	public void setPolicyDeliveryFee(BigDecimal policyDeliveryFee) {
		this.policyDeliveryFee = policyDeliveryFee;
	}

	@Column(name = "POLICYSTATUSMESSAGE")
	public Integer getPolicyStatusMessage() {
		return policyStatusMessage;
	}

	public void setPolicyStatusMessage(Integer policyStatusMessage) {
		this.policyStatusMessage = policyStatusMessage;
	}

	@Column(name = "INPUTDATE")
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Column(name = "APPLICATIONDATE")
	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Column(name = "INSURANCESTARTPERIOD")
	public Date getInsuranceStartPeriod() {
		return insuranceStartPeriod;
	}

	public void setInsuranceStartPeriod(Date insuranceStartPeriod) {
		this.insuranceStartPeriod = insuranceStartPeriod;
	}

	@Column(name = "INSURANCEENDPERIOD")
	public Date getInsuranceEndPeriod() {
		return insuranceEndPeriod;
	}

	public void setInsuranceEndPeriod(Date insuranceEndPeriod) {
		this.insuranceEndPeriod = insuranceEndPeriod;
	}

	@Column(name = "BENEFICIARYMODE")
	public Integer getBeneficiaryMode() {
		return beneficiaryMode;
	}

	public void setBeneficiaryMode(Integer beneficiaryMode) {
		this.beneficiaryMode = beneficiaryMode;
	}

	@Column(name = "UNITCOUNT")
	public Integer getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}

	@Column(name = "SPECIALSTATEMENT")
	public String getSpecialStatement() {
		return specialStatement;
	}

	public void setSpecialStatement(String specialStatement) {
		this.specialStatement = specialStatement;
	}

	@Column(name = "CONTRACTNAMES")
	public String getContractNames() {
		return contractNames;
	}

	public void setContractNames(String contractNames) {
		this.contractNames = contractNames;
	}

	@Column(name = "BANKCODE")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "DELIVERYINVOICE")
	public Integer getDeliveryInvoice() {
		return deliveryInvoice;
	}

	public void setDeliveryInvoice(Integer deliveryInvoice) {
		this.deliveryInvoice = deliveryInvoice;
	}


	@Column(name = "DELIVERYHARDCOPY")
	public Integer getDeliveryHardCopy() {
		return deliveryHardCopy;
	}

	public void setDeliveryHardCopy(Integer deliveryHardCopy) {
		this.deliveryHardCopy = deliveryHardCopy;
	}

	@Column(name = "DELIVERYEPOLICY")
	public Integer getDeliveryEPolicy() {
		return deliveryEPolicy;
	}

	public void setDeliveryEPolicy(Integer deliveryEPolicy) {
		this.deliveryEPolicy = deliveryEPolicy;
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
	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	@Column(name = "AUTORENEWABLE")
	public Integer getAutoRenewable() {
		return autoRenewable;
	}

	public void setAutoRenewable(Integer autoRenewable) {
		this.autoRenewable = autoRenewable;
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

	@Column(name = "USERID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USERTYPE")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	@Column(name = "AREACODEPRO")
	public String getAreaCodePro() {
		return areaCodePro;
	}

	public void setAreaCodePro(String areaCodePro) {
		this.areaCodePro = areaCodePro;
	}

	@Column(name = "AREACODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "LEAVESEGMENT")
	public String getLeaveSegment() {
		return leaveSegment;
	}

	public void setLeaveSegment(String leaveSegment) {
		this.leaveSegment = leaveSegment;
	}

	@Column(name = "LEAVEWAY")
	public String getLeaveWay() {
		return leaveWay;
	}

	public void setLeaveWay(String leaveWay) {
		this.leaveWay = leaveWay;
	}

	@Column(name = "RENEWALFLAG")
	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	@Column(name = "OLDLPOLICYNO")
	public String getOldlPolicyNo() {
		return oldlPolicyNo;
	}

	public void setOldlPolicyNo(String oldlPolicyNo) {
		this.oldlPolicyNo = oldlPolicyNo;
	}

	@Column(name = "CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "INPUTHOUR")
	public String getInputHour() {
		return inputHour;
	}

	public void setInputHour(String inputHour) {
		this.inputHour = inputHour;
	}

	@Column(name = "INCEPTIONDATE")
	public Date getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "INVALIDDATE")
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	@Column(name = "RECOMMENDTYPE")
	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	@Column(name = "RECOMMEND")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quoteMain")
	public QuoteApplicant getQuoteApplicant() {
		return quoteApplicant;
	}

	public void setQuoteApplicant(QuoteApplicant quoteApplicant) {
		this.quoteApplicant = quoteApplicant;
	}

	@Column(name = "QUOTEINSUREDNUMBER")
	public Integer getQuoteInsuredNumber() {
		return quoteInsuredNumber;
	}

	public void setQuoteInsuredNumber(Integer quoteInsuredNumber) {
		this.quoteInsuredNumber = quoteInsuredNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quoteMain")
	@Where(clause="ROLEKIND = 'Insured'")
	public List<QuoteInsured> getQuoteInsureds() {
		return quoteInsureds;
	}

	public void setQuoteInsureds(List<QuoteInsured> quoteInsureds) {
		this.quoteInsureds = quoteInsureds;
	}

	@Column(name = "QUOTEBENEFICIARYNUMBER")
	public Integer getQuoteBeneficiaryNumber() {
		return quoteBeneficiaryNumber;
	}

	public void setQuoteBeneficiaryNumber(Integer quoteBeneficiaryNumber) {
		this.quoteBeneficiaryNumber = quoteBeneficiaryNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quoteMain")
	@Where(clause="ROLEKIND = 'Beneficiary'")
	public List<QuoteBeneficiary> getQuoteBeneficiaries() {
		return quoteBeneficiaries;
	}

	public void setQuoteBeneficiaries(List<QuoteBeneficiary> quoteBeneficiaries) {
		this.quoteBeneficiaries = quoteBeneficiaries;
	}

	@Column(name = "QUOTELIABILITYNUMBER")
	public Integer getQuoteLiabilityNumber() {
		return quoteLiabilityNumber;
	}

	public void setQuoteLiabilityNumber(Integer quoteLiabilityNumber) {
		this.quoteLiabilityNumber = quoteLiabilityNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quoteMain")
	public List<QuoteLiability> getQuoteLiabilities() {
		return quoteLiabilities;
	}

	public void setQuoteLiabilities(List<QuoteLiability> quoteLiabilities) {
		this.quoteLiabilities = quoteLiabilities;
	}

	@Column(name = "EXPAYMODE")
	public Integer getExPayMode() {
		return exPayMode;
	}

	public void setExPayMode(Integer exPayMode) {
		this.exPayMode = exPayMode;
	}

	@Column(name = "GETPOLMODE")
	public Integer getGetPolMode() {
		return getPolMode;
	}

	public void setGetPolMode(Integer getPolMode) {
		this.getPolMode = getPolMode;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Column(name = "CREATETIME")
	@JsonIgnore
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATETIME")
	@JsonIgnore
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "AGEINTERVAL")
	public String getAgeInterval() {
		return ageInterval;
	}

	public void setAgeInterval(String ageInterval) {
		this.ageInterval = ageInterval;
	}
	@Column(name = "EID")
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	@Column(name = "COMBDESC")
	public String getCombDesc() {
		return combDesc;
	}

	public void setCombDesc(String combDesc) {
		this.combDesc = combDesc;
	}
	@Column(name = "BENEFITDESC")
	public String getBenefitDesc() {
		return benefitDesc;
	}

	public void setBenefitDesc(String benefitDesc) {
		this.benefitDesc = benefitDesc;
	}
	
	@Column(name = "QUOTEINSUREINFORMBOOKNUMBER")
	public Integer getQuoteInsureInformBookNumber() {
		return quoteInsureInformBookNumber;
	}

	public void setQuoteInsureInformBookNumber(Integer quoteInsureInformBookNumber) {
		this.quoteInsureInformBookNumber = quoteInsureInformBookNumber;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quoteMain")
	public List<QuoteInsureInformBook> getQuoteInsureInformBooks() {
		return quoteInsureInformBooks;
	}

	public void setQuoteInsureInformBooks(List<QuoteInsureInformBook> quoteInsureInformBooks) {
		this.quoteInsureInformBooks = quoteInsureInformBooks;
	}

	@Column(name = "POLICYSERIALNO")
	public String getProposalSID() {
		return proposalSID;
	}

	public void setProposalSID(String proposalSID) {
		this.proposalSID = proposalSID;
	}

	@Column(name = "STEP")
	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}


//	@OneToOne()
//	@JoinColumn(name = "insurancePolicyNo", unique = true, nullable = false)
//	public InsurancePolicy getInsurancePolicy() {
//		return insurancePolicy;
//	}
//
//	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
//		this.insurancePolicy = insurancePolicy;
//	}
//
//	@OneToOne()
//	@JoinColumn(name = "orderFormNo", unique = true, nullable = false)
//	public OrderForm getOrderForm() {
//		return orderForm;
//	}
//
//	public void setOrderForm(OrderForm orderForm) {
//		this.orderForm = orderForm;
//	}

}
