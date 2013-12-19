package cn.com.sinosoft.businessModule.policy.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import cn.com.sinosoft.businessModule.bankAccount.domain.FundsTransfer;
import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.AnnuityPayoutDurationMode;
import cn.com.sinosoft.businessModule.enums.dictionary.BeneficiaryMode;
import cn.com.sinosoft.businessModule.enums.dictionary.BenefitMode;
import cn.com.sinosoft.businessModule.enums.dictionary.BenefitPeriodType;
import cn.com.sinosoft.businessModule.enums.dictionary.BooleanStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.DisputedFlag;
import cn.com.sinosoft.businessModule.enums.dictionary.DivType;
import cn.com.sinosoft.businessModule.enums.dictionary.ElectronicPolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.ExPayMode;
import cn.com.sinosoft.businessModule.enums.dictionary.FundTransferDateBasedOnType;
import cn.com.sinosoft.businessModule.enums.dictionary.GetPolMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentDurationMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMethod;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.SyncStatus;
import cn.com.sinosoft.businessModule.insureInformBook.domain.InsureInformBook;
import cn.com.sinosoft.businessModule.logisticDistribution.domain.Addressee;
import cn.com.sinosoft.businessModule.logisticDistribution.domain.Invoice;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Beneficiary;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类InsurancePolicy
 */
@Entity
@Table(name = "INSURANCEPOLICY")
public class InsurancePolicy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	/** 属性交易标示 */
	private String transIdentify;
	
	/** 属性交易流水号 */
	private String transSerialNumber;
	
	/** 属性商家交易流水号 */
	private String merchantTransSerialNumber;
	
	/** 属性订单号 */
	private String orderSerialNumber;
	
	/** 属性合作机构订单号（淘宝订单号） */
	private String merchantOrderNumber;

	/** 属性保单号 */
	private String policySerialNumber;

	/** 属性投保单号 */
	private String applicationNumber;

	/** 属性投保单印刷号 */
	private String applicationSerialNumber;

	/** 属性电子保单号 */
	private String electronicPolicyNumber;

	/** 属性电子投保单号 */
	private String electronicApplicantNumber;

	/** 属性保单类型 */
	private Integer policyType;
	
	/** 属性保险单证类型 */
	private Integer documentType;
	
	/** 属性逾期缴付保费选项(保费过期未付选择) */
	private Integer overduePremiumPaymentOption;
	
	/** 属性保单状态 */
	private Integer policyStatus;

	/** 属性保单状态名称 */
	private String policyStatusName;

	/** 属性保单状态描述 */
	private String policyStatusDesc;
	
	/** 属性生成保单状态*/
	private Integer generateElectronicPolicyStatus = ElectronicPolicyStatus.NOT_GENERATED.getValue();
	
	/** 属性保额 */
	private BigDecimal insuredAmount;

	/** 属性保费 */
	private BigDecimal premium;
	
	/** 属性折扣保费 */
	private BigDecimal discountPremium;
	
	/** 属性总保费 */
	private BigDecimal grossPremium = new BigDecimal(0);
	
	/** 属性总保额 */
	private BigDecimal faceAmount;
	
	/** 属性产品代码 */
	private String productCode;

	/** 属性产品名称 */
	private String productName;
	
	/** 属性合作机构产品代码 */
	private String merchantProductCode;
	
	/** 属性合作机构产品名称 */
	private String merchantProductName;
	
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

	/** 属性纸质保单快递费 */
	private BigDecimal policyDeliveryFee;

	/** 属性自动核保警告 */
	private Integer policyStatusMessage;
	
	/** 属性签约日期 */
	private Date signedDate;

	/** 属性保单生效日 */
	private Date inceptionDate;
	
	/** 属性保单申请日*/
	private Date applicationDate;
	
	/** 属性保单回溯日*/
	private Date backtrackDate;
	
	/** 属性保险起期  */
	private Date insuranceStartPeriod;
	
	/** 属性保险止期 */
	private Date insuranceEndPeriod;
	
	/** 属性受益方式  */
	private Integer beneficiaryMode;
	
	/** 属性旅游目的  */
	private String travelDestination;
	
	/** 属性投保受理日期 */
	private Date submissionDate;

	/** 属性份数 */
	private Integer unitCount = 1;

	/** 属性投保书类型 */
	private String formID;
	
	/** 属性特别声明 */
	private String specialStatement;
	
	/** 属性阅读并理解相关信息 */
	private String contractNames;
	
	/** 属性保费进入投资账户日期选项 */
	private Integer fundTransferDateBasedOn;
	
	/** 属性银行代码 */
	private String bankCode;
	
	/** 属性是否需签证 */
	private Integer requireVisa;
	
	/** 属性是否需要英文证明 */
	private Integer certificationRequired;

	/** 属性是否投递发票 */
	private Integer deliveryInvoice;

	/** 属性是否投递纸质保单 */
	private Integer deliveryHardCopy;

	/** 属性是否发送电子保单 */
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
	
	/** 属性个人客户 */
	private String personalUserSerialNo;

	/** 属性用户号 */
	private String customerID;
	
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
	
	/** 属性同步状态 */
	private Integer syncStatus = SyncStatus.NOTYET_SYNCHRONIZED.getValue();
	
	/** 属性同步状态描述 */
	private String syncStatusDesc;
	
	/** 属性同步开始时间 */
	private Date syncStartTime;
	
	/** 属性同步结束时间 */
	private Date syncEndTime;
	
	/** 属性现金价值 */
	private BigDecimal cashValue;
	
	/** 属性是否可冲正 */
	private Integer reversalFlag;
	
	/** 属性全额情况下是否必须退保 */
	private Integer mustCancelFlag;
	
	/** 属性是否允许部分支取 */
	private Integer allowPartyWithdrowFlag;
	
	/** 属性保全受理时间*/
	private Date preserveAcceptTime;
	
	/** 属性保全生效时间*/
	private Date preserveEffectiveTime;
	
	/** 属性撤单时间*/
	private Date reFundPolicyTime;
	
	/** 属性撤单成功时间*/
	private Date reFundPolicySuccessTime;
	
	/** 属性付款账户 */
	private PaymentAccount paymentAccount;

	/** 属性发票 */
	private Invoice invoice;

	/** 属性收件人 */
	private Addressee addressee;

	/** 属性订单 */
	private OrderForm orderForm;

	/** 属性投保人 */
	private InsuranceApplicant insuranceApplicant;
	
	/** 属性被保人数 */
	private Integer insuredNumber;
	
	/** 属性被保险人 */
	private List<Insured> insureds = new ArrayList<Insured>(0);

	/** 属性受益人数 */
	private Integer beneficiaryNumber;
	
	/** 属性受益人 */
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>(0);
	
	/** 属性保险责任数 */
	private Integer insurancePolicyLiabilityNumber;
	
	/** 属性 保险责任 */
	private List<InsurancePolicyLiability> insurancePolicyLiabilities = new ArrayList<InsurancePolicyLiability>(0);
	
	/** 属性投保告知 */
	private List<InsureInformBook> insureInformBooks = new ArrayList<InsureInformBook>(0);
	
	/** 属性资金划拨 */
	private List<FundsTransfer> fundsTransfers = new ArrayList<FundsTransfer>(0);
	
	/** 属性投保告知数 */
	private Integer insureInformBookNumber;
	
	/** 属性 续期缴费形式 */
	private Integer exPayMode;

	/** 属性 保单递送方式 */
	private Integer getPolMode;

	/** 属性 保单密码 */
	private String password;

	/** 属性 特别约定 */
	private String specContent;

	/** 属性 发票印刷号码 */
	private String tempFeeNo;

	/** 属性 代理人组别 */
	private String agentGroup;

	/** 属性 合同争议处理方式 */
	private Integer disputedFlag;

	/** 属性 仲裁委员会名称 */
	private String acName;

	/** 属性 是否回访 */
	private Integer isVisit;

	/** 属性 绑定标志 */
	private Integer isBind;
	
	/** 属性操作员 */
	private String operatorID;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/** 属性试算单号 */
	private String quoteNo;
	
	/** 属性续保标志 */
	private String renewalFlag;
	
	/** 属性原保单号 */
	private String oldlPolicyNo;
	
	/** 属性币别 */
	private String currency;
	
	/** 属性录入小时 */
	private String inputHour;
	
	/** 属性业务领域（1集团、2寿险 3财险 4养老险  9其他） */
	private String businessArea;
	
	/** 属性原因 */
	private String reason;

	/** 属性标识位 */
	private String flag;
	
	/** 核保时间 */
	private String precheckDate;
	
	/** 加购关联号 */
	private String moreBuyNo;
	
	/**属性集团业务来源（WEB）**/
	private String businessSource;
	
	/**属性集团渠道代码（W——网销）**/
	private String groupChannel;
	
	/** 属性sellType（20） **/
	private String sellType;
	
	/**提醒次数*/
	private Integer remindCount = 0;
	
	
	/**
	 * 类InsurancePolicy的默认构造方法
	 */
	public InsurancePolicy() {
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
	 * 属性交易标识码的getter方法
	 */
	@Column(name = "TRANSIDENTIFY")
	@JSON(serialize=false)  
	public String getTransIdentify() {
		return transIdentify;
	}

	/**
	 * 属性交易标识码的getter方法
	 */
	public void setTransIdentify(String transIdentify) {
		this.transIdentify = transIdentify;
	}

	/**
	 * 属性交易流水号的getter方法
	 */
	@Column(name = "TRANSSERIALNUMBER")
	@JSON(serialize=false)  
	public String getTransSerialNumber() {
		return this.transSerialNumber;
	}

	/**
	 * 属性交易流水号的setter方法
	 */
	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}
	
	/**
	 * 属性商家交易流水号的getter方法
	 */
	@Column(name = "MERCHANTTRANSSERIALNUMBER")
	@JSON(serialize=false)  
	public String getMerchantTransSerialNumber() {
		return merchantTransSerialNumber;
	}
	
	/**
	 * 属性商家交易流水号的setter方法
	 */
	public void setMerchantTransSerialNumber(String merchantTransSerialNumber) {
		this.merchantTransSerialNumber = merchantTransSerialNumber;
	}

	/**
	 * 属性订单号的getter方法
	 */

	@Column(name = "ORDERSERIALNUMBER")
	@JSON(serialize=false)  
	public String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	/**
	 * 属性订单号的setter方法
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}
	
	/**
	 * 属性合作机构订单号的getter方法
	 */
	@Column(name = "MERCHANTORDERNUMBER")
	@JSON(serialize=false)  
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * 属性合作机构订单号的setter方法
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}
	
	/**
	 * 属性保单号的getter方法
	 */
	@Column(name = "POLICYSERIALNUMBER")
	@JSON(serialize=false)  
	public String getPolicySerialNumber() {
		return this.policySerialNumber;
	}

	/**
	 * 属性保单号的setter方法
	 */
	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	/**
	 * 属性投保单号的getter方法
	 */
	@Column(name = "APPLICATIONNUMBER")
	public String getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * 属性投保单号的setter方法
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * 属性投保单印刷号的getter方法
	 */

	@Column(name = "APPLICATIONSERIALNUMBER")
	@JSON(serialize=false)  
	public String getApplicationSerialNumber() {
		return this.applicationSerialNumber;
	}

	/**
	 * 属性投保单印刷号的setter方法
	 */
	public void setApplicationSerialNumber(String applicationSerialNumber) {
		this.applicationSerialNumber = applicationSerialNumber;
	}

	/**
	 * 属性电子保单号的getter方法
	 */

	@Column(name = "ELECTRONICPOLICYNUMBER")
	@JSON(serialize=false)  
	public String getElectronicPolicyNumber() {
		return this.electronicPolicyNumber;
	}

	/**
	 * 属性电子保单号的setter方法
	 */
	public void setElectronicPolicyNumber(String electronicPolicyNumber) {
		this.electronicPolicyNumber = electronicPolicyNumber;
	}

	/**
	 * 属性电子投保单号的getter方法
	 */

	@Column(name = "ELECTRONICAPPLICANTNUMBER")
	@JSON(serialize=false)  
	public String getElectronicApplicantNumber() {
		return this.electronicApplicantNumber;
	}

	/**
	 * 属性电子投保单号的setter方法
	 */
	public void setElectronicApplicantNumber(String electronicApplicantNumber) {
		this.electronicApplicantNumber = electronicApplicantNumber;
	}

	/**
	 * 属性保单类型的getter方法
	 */
	@Column(name = "POLICYTYPE")
	@JSON(serialize=false)  
	public Integer getPolicyType() {
		return this.policyType;
	}

	/**
	 * 属性保单类型的setter方法
	 */
	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}
	
	/**
	 * 属性保险单证类型的getter方法
	 */
	@Column(name = "DOCUMENTTYPE")
	@JSON(serialize=false)  
	public Integer getDocumentType() {
		return documentType;
	}
	
	/**
	 * 属性保险单证类型的getter方法
	 */
	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}
	
	/**
	 * 属性逾期缴付保费选项(保费过期未付选择)的getter方法
	 */
	@Column(name = "OVERDUEPREMIUMPAYMENTOPTION")
	@JSON(serialize=false)  
	public Integer getOverduePremiumPaymentOption() {
		return overduePremiumPaymentOption;
	}
	
	/**
	 * 属性逾期缴付保费选项(保费过期未付选择)的setter方法
	 */
	public void setOverduePremiumPaymentOption(Integer overduePremiumPaymentOption) {
		this.overduePremiumPaymentOption = overduePremiumPaymentOption;
	}

	/**
	 * 属性保单状态的getter方法
	 */

	@Column(name = "POLICYSTATUS")
	public Integer getPolicyStatus() {
		return this.policyStatus;
	}

	/**
	 * 属性保单状态枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public PolicyStatus getEnumPolicyStatus() {
		if (getPolicyStatus() == null) {
			return null;
		}
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByValue(PolicyStatus.class, getPolicyStatus());
		return policyStatus;
	}
	
	/**
	 * 属性保单状态核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusByCoreValue() {
		if (getPolicyStatus() == null) {
			return "";
		}
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByValue(PolicyStatus.class, getPolicyStatus());
		return policyStatus.getCoreValue();
	}
	
	/**
	 * 属性保单状态商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusByMerchantValue() {
		if (getPolicyStatus() == null) {
			return "";
		}
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByValue(PolicyStatus.class, getPolicyStatus());
		return policyStatus.getMerchantValue();
	}
	
	/**
	 * 属性保单状态的setter方法
	 */
	public void setPolicyStatus(Integer policyStatus) {
		this.policyStatus = policyStatus;
	}
	
	/**
	 * 属性保单状态赋值
	 */
	public void setEnumPolicyStatus(PolicyStatus  policyStatus) {
		if (policyStatus != null) {
			this.policyStatus = policyStatus.getValue();
		}
	}
	
	/**
	 * 属性核心保单状态赋值
	 */
	public void setPolicyStatusByCoreValue(String coreValue) {
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByCoreValue(PolicyStatus.class, coreValue);
		if (policyStatus != null) {
			this.policyStatus = policyStatus.getValue();
		}
	}
	
	/**
	 * 属性商家保单状态赋值
	 */
	public void setPolicyStatusByMerchantValue(String merchantValue) {
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(PolicyStatus.class, merchantValue);
		if (policyStatus != null) {
			this.policyStatus = policyStatus.getValue();
		}
	}
	
	/**
	 * 属性保单状态名称的getter方法
	 */
	@Column(name = "POLICYSTATUSNAME")
	@JSON(serialize=false)  
	public String getPolicyStatusName() {
		return policyStatusName;
	}
	/**
	 * 属性保单状态名称的setter方法
	 */
	public void setPolicyStatusName(String policyStatusName) {
		this.policyStatusName = policyStatusName;
	}
	
	/**
	 * 属性保单状态描述的getter方法
	 */
	@Column(name = "POLICYSTATUSDESC")
	@JSON(serialize=false)  
	public String getPolicyStatusDesc() {
		return policyStatusDesc;
	}
	
	/**
	 * 属性保单状态描述的setter方法
	 */
	public void setPolicyStatusDesc(String policyStatusDesc) {
		this.policyStatusDesc = policyStatusDesc;
	}
	

	/**
	 * 属性生成电子保单状态的getter方法
	 */

	@Column(name = "GENERATEELECTRONICPOLICYSTATUS")
	@JSON(serialize=false)  
	public Integer getGenerateElectronicPolicyStatus() {
		return this.generateElectronicPolicyStatus;
	}
	
	/**
	 * 属性生成电子保单状态枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public ElectronicPolicyStatus getEnumGenerateElectronicPolicyStatus() {
		if (getGenerateElectronicPolicyStatus() == null) {
			return null;
		}
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByValue(ElectronicPolicyStatus.class, getGenerateElectronicPolicyStatus());
		return electronicPolicyStatus;
	}
	
	/**
	 * 属性生成电子保单状态核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGenerateElectronicPolicyStatusByCoreValue() {
		if (getGenerateElectronicPolicyStatus() == null) {
			return "";
		}
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByValue(ElectronicPolicyStatus.class, getGenerateElectronicPolicyStatus());
		return electronicPolicyStatus.getCoreValue();
	}
	
	/**
	 * 属性生成电子保单状态商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGenerateElectronicPolicyStatusByMerchantValue() {
		if (getGenerateElectronicPolicyStatus() == null) {
			return "";
		}
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByValue(ElectronicPolicyStatus.class, getGenerateElectronicPolicyStatus());
		return electronicPolicyStatus.getMerchantValue();
	}
	
	/**
	 * 属性生成电子保单状态的setter方法
	 */
	public void setGenerateElectronicPolicyStatus(Integer generateElectronicPolicyStatus) {
		this.generateElectronicPolicyStatus = generateElectronicPolicyStatus;
	}
	
	/**
	 * 属性生成电子保单状态赋值
	 */
	public void setEnumGenerateElectronicPolicyStatus(ElectronicPolicyStatus  electronicPolicyStatus) {
		if (electronicPolicyStatus != null) {
			this.generateElectronicPolicyStatus = electronicPolicyStatus.getValue();
		}
	}
	
	/**
	 * 属性核心生成电子保单状态赋值
	 */
	public void setGenerateElectronicPolicyStatusByCoreValue(String coreValue) {
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByCoreValue(ElectronicPolicyStatus.class, coreValue);
		if (electronicPolicyStatus != null) {
			this.generateElectronicPolicyStatus = electronicPolicyStatus.getValue();
		}
	}
	
	/**
	 * 属性商家生成电子保单状态赋值
	 */
	public void setGenerateElectronicPolicyStatusByMerchantValue(String merchantValue) {
		ElectronicPolicyStatus electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(ElectronicPolicyStatus.class, merchantValue);
		if (electronicPolicyStatus != null) {
			this.generateElectronicPolicyStatus = electronicPolicyStatus.getValue();
		}
	}
	
	
	/**
	 * 属性保额的getter方法
	 */

	@Column(name = "INSUREDAMOUNT")
	@JSON(serialize=false)  
	public BigDecimal getInsuredAmount() {
		return this.insuredAmount;
	}

	/**
	 * 属性保额的setter方法
	 */
	public void setInsuredAmount(BigDecimal insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	/**
	 * 属性保费的getter方法
	 */

	@Column(name = "PREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getPremium() {
		return this.premium;
	}

	/**
	 * 属性保费的setter方法
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	
	/**
	 * 属性折扣保费的getter方法
	 */
	@Column(name = "DISCOUNTPREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getDiscountPremium() {
		return discountPremium;
	}
	
	/**
	 * 属性折扣保费的setter方法
	 */
	public void setDiscountPremium(BigDecimal discountPremium) {
		this.discountPremium = discountPremium;
	}
	
	/**
	 * 属性总保费的getter方法
	 */

	@Column(name = "GROSSPREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getGrossPremium() {
		return grossPremium;
	}
	
	/**
	 * 属性总保费的setter方法
	 */
	public void setGrossPremium(BigDecimal grossPremium) {
		this.grossPremium = grossPremium;
	}

	/**
	 * 属性总保额的getter方法
	 */

	@Column(name = "FACEAMOUNT")
	@JSON(serialize=false)  
	public BigDecimal getFaceAmount() {
		return this.faceAmount;
	}

	/**
	 * 属性总保额的setter方法
	 */
	public void setFaceAmount(BigDecimal faceAmount) {
		this.faceAmount = faceAmount;
	}

	/**
	 * 属性首期保费的getter方法
	 */

	@Column(name = "FIRSTPREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getFirstPremium() {
		return this.firstPremium;
	}

	/**
	 * 属性首期保费的setter方法
	 */
	public void setFirstPremium(BigDecimal firstPremium) {
		this.firstPremium = firstPremium;
	}

	/**
	 * 属性首次缴费金额的getter方法
	 */

	@Column(name = "INITIALPREMAMT")
	@JSON(serialize=false)  
	public BigDecimal getInitialPremAmt() {
		return this.initialPremAmt;
	}

	/**
	 * 属性首次缴费金额的setter方法
	 */
	public void setInitialPremAmt(BigDecimal initialPremAmt) {
		this.initialPremAmt = initialPremAmt;
	}

	/**
	 * 属性保障年期的getter方法
	 */

	@Column(name = "BENEFITPERIOD")
	@JSON(serialize=false)  
	public Integer getBenefitPeriod() {
		return this.benefitPeriod;
	}

	/**
	 * 属性保障年期的setter方法
	 */
	public void setBenefitPeriod(Integer benefitPeriod) {
		this.benefitPeriod = benefitPeriod;
	}

	/**
	 * 属性保障年期类型的getter方法
	 */
	@Column(name = "BENEFITPERIODTYPE")
	@JSON(serialize=false)  
	public Integer getBenefitPeriodType() {
		return this.benefitPeriodType;
	}
	
	/**
	 * 属性保障年期类型枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BenefitPeriodType getEnumBenefitPeriodType() {
		if (getBenefitPeriodType() == null) {
			return null;
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType;
	}
	
	/**
	 * 属性保障年期类型核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitPeriodTypeByCoreValue() {
		if (getBenefitPeriodType() == null) {
			return null;
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType.getCoreValue();
	}
	
	/**
	 * 属性保障年期类型商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitPeriodTypeByMerchantValue() {
		if (getBenefitPeriodType() == null) {
			return "";
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType.getMerchantValue();
	}
	
	
	/**
	 * 属性保障年期类型的setter方法
	 */
	public void setBenefitPeriodType(Integer benefitPeriodType) {
		this.benefitPeriodType = benefitPeriodType;
	}
	
	/**
	 * 属性保障年期类型赋值
	 */
	public void setEnumBenefitPeriodType(BenefitPeriodType  benefitPeriodType) {
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * 属性核心保障年期类型赋值
	 */
	public void setBenefitPeriodTypeByCoreValue(String coreValue) {
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByCoreValue(BenefitPeriodType.class, coreValue);
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * 属性商家保障年期类型赋值
	 */
	public void setBenefitPeriodTypeByMerchantValue(String merchantValue) {
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByMerchantValue(BenefitPeriodType.class, merchantValue);
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * 属性缴费方式的getter方法
	 */

	@Column(name = "PAYMENTMODE")
	public Integer getPaymentMode() {
		return this.paymentMode;
	}

	/**
	 * 属性缴费方式枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public PaymentMode getEnumPaymentMode() {
		if (getPaymentMode() == null) {
			return null;
		}
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByValue(PaymentMode.class, getPaymentMode());
		return paymentMode;
	}
	
	/**
	 * 属性缴费方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentModeByCoreValue() {
		if (getPaymentMode() == null) {
			return "";
		}
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByValue(PaymentMode.class, getPaymentMode());
		return paymentMode.getCoreValue();
	}
	
	/**
	 * 属性缴费方式商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentModeByMerchantValue() {
		if (getPaymentMode() == null) {
			return "";
		}
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByValue(PaymentMode.class, getPaymentMode());
		return paymentMode.getMerchantValue();
	}
	
	
	/**
	 * 属性缴费方式的setter方法
	 */
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * 属性缴费方式赋值
	 */
	public void setEnumPaymentMode(PaymentMode  paymentMode) {
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
		
	}
	
	/**
	 * 属性核心缴费方式赋值
	 */
	public void setPaymentModeByCoreValue(String coreValue) {
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentMode.class, coreValue);
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	
	/**
	 * 属性商家缴费方式赋值
	 */
	public void setPaymentModeByMerchantValue(String merchantValue) {
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentMode.class, merchantValue);
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	

	/**
	 * 属性缴费年期的getter方法
	 */

	@Column(name = "PAYMENTDURATION")
	@JSON(serialize=false)  
	public Integer getPaymentDuration() {
		return this.paymentDuration;
	}

	/**
	 * 属性缴费年期的setter方法
	 */
	public void setPaymentDuration(Integer paymentDuration) {
		this.paymentDuration = paymentDuration;
	}

	/**
	 * 属性缴费年期类型的getter方法
	 */

	@Column(name = "PAYMENTDURATIONMODE")
	@JSON(serialize=false)  
	public Integer getPaymentDurationMode() {
		return this.paymentDurationMode;
	}


	/**
	 * 属性缴费年期类型枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public PaymentDurationMode getEnumPaymentDurationMode() {
		if (getPaymentDurationMode() == null) {
			return null;
		}
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByValue(PaymentDurationMode.class, getPaymentDurationMode());
		return paymentDurationMode;
	}
	
	/**
	 * 属性缴费年期类型核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentDurationModeByCoreValue() {
		if (getPaymentDurationMode() == null) {
			return "";
		}
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByValue(PaymentDurationMode.class, getPaymentDurationMode());
		return paymentDurationMode.getCoreValue();
	}
	
	/**
	 * 属性缴费年期类型商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentDurationModeByMerchantValue() {
		if (getPaymentDurationMode() == null) {
			return "";
		}
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByValue(PaymentDurationMode.class, getPaymentDurationMode());
		return paymentDurationMode.getMerchantValue();
	}
	
	/**
	 * 属性缴费年期类型的setter方法
	 */
	public void setPaymentDurationMode(Integer paymentDurationMode) {
		this.paymentDurationMode = paymentDurationMode;
	}

	/**
	 * 属性缴费年期类型赋值
	 */
	public void setEnumPaymentDurationMode(PaymentDurationMode  paymentDurationMode) {
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * 属性核心缴费年期类型赋值
	 */
	public void setPaymentDurationModeByCoreValue(String coreValue) {
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentDurationMode.class, coreValue);
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * 属性商家缴费年期类型赋值
	 */
	public void setPaymentDurationModeByMerchantValue(String merchantValue) {
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentDurationMode.class, merchantValue);
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * 属性支付方式的getter方法
	 */

	@Column(name = "PAYMENTMETHOD")
	@JSON(serialize=false)  
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}
	
	/**
	 * 属性支付方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public PaymentMethod getEnumPaymentMethod() {
		if (getPaymentMethod() == null) {
			return null;
		}
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByValue(PaymentMethod.class, getPaymentMethod());
		return paymentMethod;
	}
	
	/**
	 * 属性支付方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentMethodByCoreValue() {
		if (getPaymentMethod() == null) {
			return "";
		}
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByValue(PaymentMethod.class, getPaymentMethod());
		return paymentMethod.getCoreValue();
	}
	
	/**
	 * 属性支付方式商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentMethodByMerchantValue() {
		if (getPaymentMethod() == null) {
			return "";
		}
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByValue(PaymentMethod.class, getPaymentMethod());
		return paymentMethod.getMerchantValue();
	}
	
	/**
	 * 属性支付方式的setter方法
	 */
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * 属性支付方式赋值
	 */
	public void setEnumPaymentMethod(PaymentMethod  paymentMethod) {
		if (paymentMethod != null) {
			this.paymentMethod = paymentMethod.getValue();
		}
	}
	
	/**
	 * 属性核心支付方式赋值
	 */
	public void setPaymentMethodByCoreValue(String coreValue) {
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentMethod.class, coreValue);
		if (paymentMethod != null) {
			this.paymentMethod = paymentMethod.getValue();
		}
	}
	
	/**
	 * 属性商家支付方式赋值
	 */
	public void setPaymentMethodByMerchantValue(String merchantValue) {
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentMethod.class, merchantValue);
		if (paymentMethod != null) {
			this.paymentMethod = paymentMethod.getValue();
		}
	}
	
	
	/**
	 * 属性生存金领取方式的getter方法
	 */

	@Column(name = "BENEFITMODE")
	@JSON(serialize=false)  
	public Integer getBenefitMode() {
		return this.benefitMode;
	}
	
	/**
	 * 属性生存金领取方式枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BenefitMode getEnumBenefitMode() {
		if (getBenefitMode() == null) {
			return null;
		}
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByValue(BenefitMode.class, getBenefitMode());
		return benefitMode;
	}
	
	/**
	 * 属性生存金领取方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitModeByCoreValue() {
		if (getBenefitMode() == null) {
			return "";
		}
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByValue(BenefitMode.class, getBenefitMode());
		return benefitMode.getCoreValue();
	}
	
	/**
	 * 属性生存金领取方式商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitModeByMerchantValue() {
		if (getBenefitMode() == null) {
			return "";
		}
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByValue(BenefitMode.class, getBenefitMode());
		return benefitMode.getMerchantValue();
	}
	
	/**
	 * 属性生存金领取方式的setter方法
	 */
	public void setBenefitMode(Integer benefitMode) {
		this.benefitMode = benefitMode;
	}
	
	/**
	 * 属性生存金领取方式赋值
	 */
	public void setEnumBenefitMode(BenefitMode  benefitMode) {
		if (benefitMode != null) {
			this.benefitMode = benefitMode.getValue();
		}
	}
	
	/**
	 * 属性核心生存金领取方式赋值
	 */
	public void setBenefitModeByCoreValue(String coreValue) {
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByCoreValue(BenefitMode.class, coreValue);
		if (benefitMode != null) {
			this.benefitMode = benefitMode.getValue();
		}
	}
	
	/**
	 * 属性商家生存金领取方式赋值
	 */
	public void setBenefitModeByMerchantValue(String merchantValue) {
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByMerchantValue(BenefitMode.class, merchantValue);
		if (benefitMode != null) {
			this.benefitMode = benefitMode.getValue();
		}
	}
	
	
	/**
	 * 属性红利领取方式的getter方法
	 */

	@Column(name = "DIVTYPE")
	@JSON(serialize=false)  
	public Integer getDivType() {
		return this.divType;
	}
	
	/**
	 * 属性红利领取方式枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public DivType getEnumDivType() {
		if (getDivType() == null) {
			return null;
		}
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByValue(DivType.class, getDivType());
		return divType;
	}
	
	/**
	 * 属性红利领取方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDivTypeByCoreValue() {
		if (getDivType() == null) {
			return "";
		}
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByValue(DivType.class, getDivType());
		return divType.getCoreValue();
	}
	
	/**
	 * 属性红利领取方式商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDivTypeByMerchantValue() {
		if (getDivType() == null) {
			return "";
		}
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByValue(DivType.class, getDivType());
		return divType.getMerchantValue();
	}
	
	
	/**
	 * 属性红利领取方式的setter方法
	 */
	public void setDivType(Integer divType) {
		this.divType = divType;
	}

	/**
	 * 属性红利领取方式取方式赋值
	 */
	public void setEnumDivType(DivType  divType) {
		if (divType != null) {
			this.divType = divType.getValue();
		}
	}
	
	/**
	 * 属性核心红利领取方式取方式赋值
	 */
	public void setDivTypeByCoreValue(String coreValue) {
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByCoreValue(DivType.class, coreValue);
		if (divType != null) {
			this.divType = divType.getValue();
		}
	}
	
	/**
	 * 属性商家红利领取方式取方式赋值
	 */
	public void setDivTypeByMerchantValue(String merchantValue) {
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByMerchantValue(DivType.class, merchantValue);
		if (divType != null) {
			this.divType = divType.getValue();
		}
	}
	
	
	/**
	 * 属性年金领取期限的getter方法
	 */

	@Column(name = "ANNUITYPAYOUTDURATION")
	@JSON(serialize=false)  
	public String getAnnuityPayoutDuration() {
		return this.annuityPayoutDuration;
	}

	/**
	 * 属性年金领取期限的setter方法
	 */
	public void setAnnuityPayoutDuration(String annuityPayoutDuration) {
		this.annuityPayoutDuration = annuityPayoutDuration;
	}

	/**
	 * 属性年金领取期限类型的getter方法
	 */

	@Column(name = "ANNUITYPAYOUTDURATIONMODE")
	@JSON(serialize=false)  
	public Integer getAnnuityPayoutDurationMode() {
		return this.annuityPayoutDurationMode;
	}
	/**
	 * 属性年金领取期限类型枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public AnnuityPayoutDurationMode getEnumAnnuityPayoutDurationMode() {
		if (getAnnuityPayoutDurationMode() == null) {
			return null;
		}
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByValue(AnnuityPayoutDurationMode.class, getAnnuityPayoutDurationMode());
		return annuityPayoutDurationMode;
	}
	
	/**
	 * 属性年金领取期限类型核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAnnuityPayoutDurationModeByCoreValue() {
		if (getAnnuityPayoutDurationMode() == null) {
			return "";
		}
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByValue(AnnuityPayoutDurationMode.class, getAnnuityPayoutDurationMode());
		return annuityPayoutDurationMode.getCoreValue();
	}
	
	/**
	 * 属性年金领取期限类型商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAnnuityPayoutDurationModeByMerchantValue() {
		if (getAnnuityPayoutDurationMode() == null) {
			return "";
		}
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByValue(AnnuityPayoutDurationMode.class, getAnnuityPayoutDurationMode());
		return annuityPayoutDurationMode.getMerchantValue();
	}
	
	/**
	 * 属性年金领取期限类型的setter方法
	 */
	public void setAnnuityPayoutDurationMode(Integer annuityPayoutDurationMode) {
		this.annuityPayoutDurationMode = annuityPayoutDurationMode;
	}
	
	/**
	 * 属性年金领取期限类型取方式赋值
	 */
	public void setEnumAnnuityPayoutDurationMode(AnnuityPayoutDurationMode  annuityPayoutDurationMode) {
		if (annuityPayoutDurationMode != null) {
			this.annuityPayoutDurationMode = annuityPayoutDurationMode.getValue();
		}
	}
	
	/**
	 * 属性核心年金领取期限类型取方式赋值
	 */
	public void setAnnuityPayoutDurationModeByCoreValue(String coreValue) {
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByCoreValue(AnnuityPayoutDurationMode.class, coreValue);
		if (annuityPayoutDurationMode != null) {
			this.annuityPayoutDurationMode = annuityPayoutDurationMode.getValue();
		}
	}
	
	/**
	 * 属性商家年金领取期限类型取方式赋值
	 */
	public void setAnnuityPayoutDurationModeByMerchantValue(String merchantValue) {
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByMerchantValue(AnnuityPayoutDurationMode.class, merchantValue);
		if (annuityPayoutDurationMode != null) {
			this.annuityPayoutDurationMode = annuityPayoutDurationMode.getValue();
		}
	}
	
	/**
	 * 属性年金领取起始年龄的getter方法
	 */

	@Column(name = "PAYOUTSTART")
	@JSON(serialize=false)  
	public String getPayoutStart() {
		return this.payoutStart;
	}

	/**
	 * 属性年金领取起始年龄的setter方法
	 */
	public void setPayoutStart(String payoutStart) {
		this.payoutStart = payoutStart;
	}

	/**
	 * 属性年金领取终止年龄的getter方法
	 */

	@Column(name = "PAYOUTEND")
	@JSON(serialize=false)  
	public String getPayoutEnd() {
		return this.payoutEnd;
	}

	/**
	 * 属性年金领取终止年龄的setter方法
	 */
	public void setPayoutEnd(String payoutEnd) {
		this.payoutEnd = payoutEnd;
	}

	/**
	 * 属性附加保费/手续金额的getter方法
	 */

	@Column(name = "EXCESSPREMAMT")
	@JSON(serialize=false)  
	public BigDecimal getExcessPremAmt() {
		return this.excessPremAmt;
	}

	/**
	 * 属性附加保费/手续金额的setter方法
	 */
	public void setExcessPremAmt(BigDecimal excessPremAmt) {
		this.excessPremAmt = excessPremAmt;
	}

	/**
	 * 属性纸质保单快递费的getter方法
	 */

	@Column(name = "POLICYDELIVERYFEE")
	@JSON(serialize=false)  
	public BigDecimal getPolicyDeliveryFee() {
		return this.policyDeliveryFee;
	}

	/**
	 * 属性纸质保单快递费的setter方法
	 */
	public void setPolicyDeliveryFee(BigDecimal policyDeliveryFee) {
		this.policyDeliveryFee = policyDeliveryFee;
	}

	/**
	 * 属性自动核保警告的getter方法
	 */

	@Column(name = "POLICYSTATUSMESSAGE")
	@JSON(serialize=false)  
	public Integer getPolicyStatusMessage() {
		return this.policyStatusMessage;
	}
	
	/**
	 * 属性自动核保警告枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumPolicyStatusMessage() {
		if (getPolicyStatusMessage() == null) {
			return null;
		}
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getPolicyStatusMessage());
		return policyStatusMessage;
	}
	
	/**
	 * 属性自动核保警告核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusMessageByCoreValue() {
		if (getPolicyStatusMessage() == null) {
			return "";
		}
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getPolicyStatusMessage());
		return policyStatusMessage.getCoreValue();
	}
	
	/**
	 * 属性自动核保警告商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusMessageByMerchantValue() {
		if (getPolicyStatusMessage() == null) {
			return "";
		}
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getPolicyStatusMessage());
		return policyStatusMessage.getMerchantValue();
	}
	
	/**
	 * 属性自动核保警告的setter方法
	 */
	public void setPolicyStatusMessage(Integer policyStatusMessage) {
		this.policyStatusMessage = policyStatusMessage;
	}
	
	/**
	 * 属性自动核保警告赋值
	 */
	public void setEnumPolicyStatusMessage(BooleanStatus  policyStatusMessage) {
		if (policyStatusMessage != null) {
			this.policyStatusMessage = policyStatusMessage.getValue();
		}
	}
	
	/**
	 * 属性核心自动核保警告赋值
	 */
	public void setPolicyStatusMessageByCoreValue(String coreValue) {
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (policyStatusMessage != null) {
			this.policyStatusMessage = policyStatusMessage.getValue();
		}
	}
	
	/**
	 * 属性商家自动核保警告赋值
	 */
	public void setPolicyStatusMessageByMerchantValue(String merchantValue) {
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (policyStatusMessage != null) {
			this.policyStatusMessage = policyStatusMessage.getValue();
		}
	}
	
	/**
	 * 属性签约日期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SIGNEDDATE")
	@JSON(serialize=false)  
	public Date getSignedDate() {
		return this.signedDate;
	}

	/**
	 * 属性签约日期的setter方法
	 */
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	/**
	 * 属性保单生效日的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INCEPTIONDATE")
	public Date getInceptionDate() {
		return this.inceptionDate;
	}

	/**
	 * 属性保单生效日的setter方法
	 */
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	
	/**
	 * 属性保单申请日的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLICATIONDATE")
	@JSON(serialize=false)  
	public Date getApplicationDate() {
		return applicationDate;
	}
	
	/**
	 * 属性保单申请日的setter方法
	 */
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	/**
	 * 属性保单回溯日的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BACKTRACKDATE")
	@JSON(serialize=false)  
	public Date getBacktrackDate() {
		return backtrackDate;
	}
	
	/**
	 * 属性保单回溯日的setter方法
	 */
	public void setBacktrackDate(Date backtrackDate) {
		this.backtrackDate = backtrackDate;
	}

	/**
	 * 属性保单起期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCESTARTPERIOD")
	public Date getInsuranceStartPeriod() {
		return insuranceStartPeriod;
	}
	
	/**
	 * 属性保单起期的setter方法
	 */
	public void setInsuranceStartPeriod(Date insuranceStartPeriod) {
		this.insuranceStartPeriod = insuranceStartPeriod;
	}
	
	/**
	 * 属性保单止期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCEENDPERIOD")
	@JSON(serialize=false)  
	public Date getInsuranceEndPeriod() {
		return insuranceEndPeriod;
	}
	
	/**
	 * 属性保单止期的setter方法
	 */
	public void setInsuranceEndPeriod(Date insuranceEndPeriod) {
		this.insuranceEndPeriod = insuranceEndPeriod;
	}
	
	/**
	 * 属性受益方式的getter方法
	 */
	@Column(name = "BENEFICIARYMODE")
	@JSON(serialize=false)  
	public Integer getBeneficiaryMode() {
		return beneficiaryMode;
	}
	
	/**
	 * 属性受益方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BeneficiaryMode getEnumBeneficiaryMode() {
		if (getBeneficiaryMode() == null) {
			return null;
		}
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryMode.class, getBeneficiaryMode());
		return beneficiaryMode;
	}
	
	/**
	 * 属性受益方式核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBeneficiaryModeByCoreValue() {
		if (getBeneficiaryMode() == null) {
			return "";
		}
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryMode.class, getBeneficiaryMode());
		return beneficiaryMode.getCoreValue();
	}
	
	/**
	 * 属性受益方式商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBeneficiaryModeByMerchantValue() {
		if (getBeneficiaryMode() == null) {
			return "";
		}
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryMode.class, getBeneficiaryMode());
		return beneficiaryMode.getMerchantValue();
	}
	
	/**
	 * 属性受益方式的setter方法
	 */
	public void setBeneficiaryMode(Integer beneficiaryMode) {
		this.beneficiaryMode = beneficiaryMode;
	}
	
	/**
	 * 属性受益方式赋值
	 */
	public void setEnumBeneficiaryMode(BeneficiaryMode  beneficiaryMode) {
		if (beneficiaryMode != null) {
			this.beneficiaryMode = beneficiaryMode.getValue();
		}
	}
	
	/**
	 * 属性核心受益方式赋值
	 */
	public void setBeneficiaryModeByCoreValue(String coreValue) {
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByCoreValue(BeneficiaryMode.class, coreValue);
		if (beneficiaryMode != null) {
			this.beneficiaryMode = beneficiaryMode.getValue();
		}
	}
	
	/**
	 * 属性商家受益方式赋值
	 */
	public void setBeneficiaryModeByMerchantValue(String merchantValue) {
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByMerchantValue(BeneficiaryMode.class, merchantValue);
		if (beneficiaryMode != null) {
			this.beneficiaryMode = beneficiaryMode.getValue();
		}
	}
	
	/**
	 * 属性旅游目的地的getter方法
	 */
	@Column(name = "TRAVELDESTINATION")
	@JSON(serialize=false)  
	public String getTravelDestination() {
		return travelDestination;
	}
	
	/**
	 * 属性旅游目的地的setter方法
	 */
	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
	}

	/**
	 * 属性投保受理日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMISSIONDATE")
	@JSON(serialize=false)  
	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	/**
	 * 属性投保受理日期的setter方法
	 */
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	/**
	 * 属性份数的getter方法
	 */

	@Column(name = "UNITCOUNT")
	@JSON(serialize=false)  
	public Integer getUnitCount() {
		return this.unitCount;
	}

	/**
	 * 属性份数的setter方法
	 */
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}

	/**
	 * 属性投保书类型的getter方法
	 */

	@Column(name = "FORMID")
	@JSON(serialize=false)  
	public String getFormID() {
		return this.formID;
	}

	/**
	 * 属性投保书类型的setter方法
	 */
	public void setFormID(String formID) {
		this.formID = formID;
	}
	
	/**
	 * 属性阅读并理解相关信息的setter方法
	 */
	@Column(name = "CONTRACTNAMES")
	@JSON(serialize=false)  
	public String getContractNames() {
		return contractNames;
	}
	
	/**
	 * 属性阅读并理解相关信息的setter方法
	 */
	public void setContractNames(String contractNames) {
		this.contractNames = contractNames;
	}
	
	/**
	 * 属性特别声明的setter方法
	 */
	@Column(name = "SPECIALSTATEMENT")
	@JSON(serialize=false)  
	public String getSpecialStatement() {
		return specialStatement;
	}
	
	/**
	 * 属性特别声明的setter方法
	 */
	public void setSpecialStatement(String specialStatement) {
		this.specialStatement = specialStatement;
	}

	/**
	 * 属性保费进入投资账户日期选项的getter方法
	 */

	@Column(name = "FUNDTRANSFERDATEBASEDON")
	@JSON(serialize=false)  
	public Integer getFundTransferDateBasedOn() {
		return this.fundTransferDateBasedOn;
	}
	
	/**
	 * 属性年保费进入投资账户日期选项枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public FundTransferDateBasedOnType getEnumFundTransferDateBasedOn() {
		if (getFundTransferDateBasedOn() == null) {
			return null;
		}
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByValue(FundTransferDateBasedOnType.class, getFundTransferDateBasedOn());
		return fundTransferDateBasedOn;
	}
	
	/**
	 * 属性年保费进入投资账户日期选项核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getFundTransferDateBasedOnByCoreValue() {
		if (getFundTransferDateBasedOn() == null) {
			return "";
		}
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByValue(FundTransferDateBasedOnType.class, getFundTransferDateBasedOn());
		return fundTransferDateBasedOn.getCoreValue();
	}
	
	/**
	 * 属性年保费进入投资账户日期选项商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getFundTransferDateBasedOnByMerchantValue() {
		if (getFundTransferDateBasedOn() == null) {
			return "";
		}
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByValue(FundTransferDateBasedOnType.class, getFundTransferDateBasedOn());
		return fundTransferDateBasedOn.getMerchantValue();
	}
	
	
	/**
	 * 属性保费进入投资账户日期选项的setter方法
	 */
	public void setFundTransferDateBasedOn(Integer fundTransferDateBasedOn) {
		this.fundTransferDateBasedOn = fundTransferDateBasedOn;
	}
	
	/**
	 * 属性保费进入投资账户日期选项取方式赋值
	 */
	public void setEnumFundTransferDateBasedOn(FundTransferDateBasedOnType  fundTransferDateBasedOn) {
		if (fundTransferDateBasedOn != null) {
			this.fundTransferDateBasedOn = fundTransferDateBasedOn.getValue();
		}
	}
	
	/**
	 * 属性核心保费进入投资账户日期选项取方式赋值
	 */
	public void setFundTransferDateBasedOnByCoreValue(String coreValue) {
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByCoreValue(FundTransferDateBasedOnType.class, coreValue);
		if (fundTransferDateBasedOn != null) {
			this.fundTransferDateBasedOn = fundTransferDateBasedOn.getValue();
		}
	}
	
	/**
	 * 属性商家保费进入投资账户日期选项取方式赋值
	 */
	public void setFundTransferDateBasedOnByMerchantValue(String merchantValue) {
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByMerchantValue(FundTransferDateBasedOnType.class, merchantValue);
		if (fundTransferDateBasedOn != null) {
			this.fundTransferDateBasedOn = fundTransferDateBasedOn.getValue();
		}
	}
	
	/**
	 * 属性银行代码的getter方法
	 */

	@Column(name = "BANKCODE")
	@JSON(serialize=false)  
	public String getBankCode() {
		return this.bankCode;
	}

	/**
	 * 属性银行代码的setter方法
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * 属性是否需要英文证明的getter方法
	 */

	@Column(name = "CERTIFICATIONREQUIRED")
	@JSON(serialize=false)  
	public Integer getCertificationRequired() {
		return this.certificationRequired;
	}

	/**
	 * 属性否需要英文证明枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumCertificationRequired() {
		if (getCertificationRequired() == null) {
			return null;
		}
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCertificationRequired());
		return certificationRequired;
	}
	
	/**
	 * 属性否需要英文证明核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getCertificationRequiredByCoreValue() {
		if (getCertificationRequired() == null) {
			return "";
		}
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCertificationRequired());
		return certificationRequired.getCoreValue();
	}
	
	/**
	 * 属性否需要英文证明商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getCertificationRequiredByMerchantValue() {
		if (getCertificationRequired() == null) {
			return "";
		}
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCertificationRequired());
		return certificationRequired.getMerchantValue();
	}
	
	/**
	 * 属性是否需要英文证明的setter方法
	 */
	public void setCertificationRequired(Integer certificationRequired) {
		this.certificationRequired = certificationRequired;
	}
	/**
	 * 属性否需要英文证明赋值
	 */
	public void setEnumCertificationRequired(BooleanStatus  certificationRequired) {
		if (certificationRequired != null) {
			this.certificationRequired = certificationRequired.getValue();
		}
	}
	
	/**
	 * 属性核心否需要英文证明赋值
	 */
	public void setCertificationRequiredByCoreValue(String coreValue) {
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (certificationRequired != null) {
			this.certificationRequired = certificationRequired.getValue();
		}
	}
	
	/**
	 * 属性商家否需要英文证明赋值
	 */
	public void setCertificationRequiredByMerchantValue(String merchantValue) {
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (certificationRequired != null) {
			this.certificationRequired = certificationRequired.getValue();
		}
	}
	
	/**
	 * 属性是否需要签证的getter方法
	 */

	@Column(name = "REQUIREVISA")
	@JSON(serialize=false)  
	public Integer getRequireVisa() {
		return requireVisa;
	}
	
	/**
	 * 属性否需要签证枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumRequireVisa() {
		if (getRequireVisa() == null) {
			return null;
		}
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getRequireVisa());
		return requireVisa;
	}
	
	/**
	 * 属性否需要签证核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getRequireVisaByCoreValue() {
		if (getRequireVisa() == null) {
			return "";
		}
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getRequireVisa());
		return requireVisa.getCoreValue();
	}
	
	/**
	 * 属性否需要签证商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getRequireVisaByMerchantValue() {
		if (getRequireVisa() == null) {
			return "";
		}
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getRequireVisa());
		return requireVisa.getMerchantValue();
	}
	
	/**
	 * 属性是否需要签证的setter方法
	 */
	public void setRequireVisa(Integer requireVisa) {
		this.requireVisa = requireVisa;
	}
	/**
	 * 属性否需要签证赋值
	 */
	public void setEnumRequireVisa(BooleanStatus  requireVisa) {
		if (requireVisa != null) {
			this.requireVisa = requireVisa.getValue();
		}
	}
	
	/**
	 * 属性核心否需要签证赋值
	 */
	public void setRequireVisaByCoreValue(String coreValue) {
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (requireVisa != null) {
			this.requireVisa = requireVisa.getValue();
		}
	}
	
	/**
	 * 属性商家否需要签证赋值
	 */
	public void setRequireVisaByMerchantValue(String merchantValue) {
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (requireVisa != null) {
			this.requireVisa = requireVisa.getValue();
		}
	}
	
	/**
	 * 属性是否投递发票的getter方法
	 */

	@Column(name = "DELIVERYINVOICE")
	@JSON(serialize=false)  
	public Integer getDeliveryInvoice() {
		return this.deliveryInvoice;
	}
	
	/**
	 * 属性是否投递发票枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumDeliveryInvoice() {
		if (getDeliveryInvoice() == null) {
			return null;
		}
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryInvoice());
		return deliveryInvoice;
	}
	
	/**
	 * 属性是否投递发票核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryInvoiceByCoreValue() {
		if (getDeliveryInvoice() == null) {
			return "";
		}
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryInvoice());
		return deliveryInvoice.getCoreValue();
	}
	
	/**
	 * 属性是否投递发票商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryInvoiceByMerchantValue() {
		if (getDeliveryInvoice() == null) {
			return "";
		}
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryInvoice());
		return deliveryInvoice.getMerchantValue();
	}
	
	
	/**
	 * 属性是否投递发票的setter方法
	 */
	public void setDeliveryInvoice(Integer deliveryInvoice) {
		this.deliveryInvoice = deliveryInvoice;
	}
	
	/**
	 * 属性是否投递发票赋值
	 */
	public void setEnumDeliveryInvoice(BooleanStatus  deliveryInvoice) {
		if (deliveryInvoice != null) {
			this.deliveryInvoice = deliveryInvoice.getValue();
		}
	}
	
	/**
	 * 属性核心是否投递发票赋值
	 */
	public void setDeliveryInvoiceByCoreValue(String coreValue) {
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (deliveryInvoice != null) {
			this.deliveryInvoice = deliveryInvoice.getValue();
		}
	}
	
	/**
	 * 属性商家是否投递发票赋值
	 */
	public void setDeliveryInvoiceByMerchantValue(String merchantValue) {
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (deliveryInvoice != null) {
			this.deliveryInvoice = deliveryInvoice.getValue();
		}
	}
	
	
	/**
	 * 属性是否投递纸质保单的getter方法
	 */

	@Column(name = "DELIVERYHARDCOPY")
	@JSON(serialize=false)  
	public Integer getDeliveryHardCopy() {
		return this.deliveryHardCopy;
	}
	
	/**
	 * 属性是否投递纸质保单枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumDeliveryHardCopy() {
		if (getDeliveryHardCopy() == null) {
			return null;
		}
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryHardCopy());
		return deliveryHardCopy;
	}
	
	/**
	 * 属性是否投递纸质保单核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryHardCopyByCoreValue() {
		if (getDeliveryHardCopy() == null) {
			return "";
		}
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryHardCopy());
		return deliveryHardCopy.getCoreValue();
	}
	
	/**
	 * 属性是否投递纸质保单商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryHardCopyByMerchantValue() {
		if (getDeliveryHardCopy() == null) {
			return "";
		}
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryHardCopy());
		return deliveryHardCopy.getMerchantValue();
	}
	
	
	/**
	 * 属性是否投递纸质保单的setter方法
	 */
	public void setDeliveryHardCopy(Integer deliveryHardCopy) {
		this.deliveryHardCopy = deliveryHardCopy;
	}
	/**
	 * 属性是否投递纸质保单赋值
	 */
	public void setEnumDeliveryHardCopy(BooleanStatus  deliveryHardCopy) {
		if (deliveryHardCopy != null) {
			this.deliveryHardCopy = deliveryHardCopy.getValue();
		}
	}
	
	/**
	 * 属性核心是否投递纸质保单赋值
	 */
	public void setDeliveryHardCopyByCoreValue(String coreValue) {
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (deliveryHardCopy != null) {
			this.deliveryHardCopy = deliveryHardCopy.getValue();
		}
	}
	
	/**
	 * 属性商家是否投递纸质保单赋值
	 */
	public void setDeliveryHardCopyByMerchantValue(String merchantValue) {
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (deliveryHardCopy != null) {
			this.deliveryHardCopy = deliveryHardCopy.getValue();
		}
	}
	
	/**
	 * 属性是否发送电子保单的getter方法
	 */

	@Column(name = "DELIVERYEPOLICY")
	@JSON(serialize=false)  
	public Integer getDeliveryEPolicy() {
		return this.deliveryEPolicy;
	}
	
	/**
	 * 属性是否发送电子保单枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumDeliveryEPolicy() {
		if (getDeliveryEPolicy() == null) {
			return null;
		}
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryEPolicy());
		return deliveryEPolicy;
	}
	
	/**
	 * 属性是否发送电子保单核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryEPolicyByCoreValue() {
		if (getDeliveryEPolicy() == null) {
			return "";
		}
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryEPolicy());
		return deliveryEPolicy.getCoreValue();
	}
	
	/**
	 * 属性是否发送电子保单商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryEPolicyByMerchantValue() {
		if (getDeliveryEPolicy() == null) {
			return "";
		}
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryEPolicy());
		return deliveryEPolicy.getMerchantValue();
	}
	
	
	/**
	 * 属性是否发送电子保单的setter方法
	 */
	public void setDeliveryEPolicy(Integer deliveryEPolicy) {
		this.deliveryEPolicy = deliveryEPolicy;
	}
	
	/**
	 * 属性是否发送电子保单赋值
	 */
	public void setEnumDeliveryEPolicy(BooleanStatus  deliveryEPolicy) {
		if (deliveryEPolicy != null) {
			this.deliveryEPolicy = deliveryEPolicy.getValue();
		}
	}
	
	/**
	 * 属性核心是否发送电子保单赋值
	 */
	public void setDeliveryEPolicyByCoreValue(String coreValue) {
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (deliveryEPolicy != null) {
			this.deliveryEPolicy = deliveryEPolicy.getValue();
		}
	}
	
	/**
	 * 属性商家是否发送电子保单赋值
	 */
	public void setDeliveryEPolicyByMerchantValue(String merchantValue) {
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (deliveryEPolicy != null) {
			this.deliveryEPolicy = deliveryEPolicy.getValue();
		}
	}
	
	/**
	 * 属性活动代码的getter方法
	 */

	@Column(name = "CAMPAIGNCODE")
	@JSON(serialize=false)  
	public String getCampaignCode() {
		return this.campaignCode;
	}

	/**
	 * 属性活动代码的setter方法
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	/**
	 * 属性活动名称的getter方法
	 */

	@Column(name = "CAMPAIGNNAME")
	@JSON(serialize=false)  
	public String getCampaignName() {
		return this.campaignName;
	}

	/**
	 * 属性活动名称的setter方法
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * 属性折扣代码的getter方法
	 */

	@Column(name = "DISCOUNTTYPECODE")
	@JSON(serialize=false)  
	public String getDiscountTypeCode() {
		return this.discountTypeCode;
	}

	/**
	 * 属性折扣代码的setter方法
	 */
	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	/**
	 * 属性折扣系数的getter方法
	 */

	@Column(name = "DISCOUNTRATE")
	@JSON(serialize=false)  
	public BigDecimal getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * 属性折扣系数的setter方法
	 */
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * 属性是否申请一年期自动续保的getter方法
	 */

	@Column(name = "AUTORENEWABLE")
	@JSON(serialize=false)  
	public Integer getAutoRenewable() {
		return this.autoRenewable;
	}
	
	/**
	 * 属性是否申请一年期自动续保枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumAutoRenewable() {
		if (getAutoRenewable() == null) {
			return null;
		}
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAutoRenewable());
		return autoRenewable;
	}
	
	/**
	 * 属性是否申请一年期自动续保核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAutoRenewableByCoreValue() {
		if (getAutoRenewable() == null) {
			return "";
		}
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAutoRenewable());
		return autoRenewable.getCoreValue();
	}
	
	/**
	 * 属性是否申请一年期自动续保商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAutoRenewableByMerchantValue() {
		if (getAutoRenewable() == null) {
			return "";
		}
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAutoRenewable());
		return autoRenewable.getMerchantValue();
	}
	
	/**
	 * 属性是否申请一年期自动续保的setter方法
	 */
	public void setAutoRenewable(Integer autoRenewable) {
		this.autoRenewable = autoRenewable;
	}
	
	/**
	 * 属性是否申请一年期自动续保赋值
	 */
	public void setEnumAutoRenewable(BooleanStatus  autoRenewable) {
		if (autoRenewable != null) {
			this.autoRenewable = autoRenewable.getValue();
		}
	}
	
	/**
	 * 属性核心是否申请一年期自动续保赋值
	 */
	public void setAutoRenewableByCoreValue(String coreValue) {
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (autoRenewable != null) {
			this.autoRenewable = autoRenewable.getValue();
		}
	}
	
	/**
	 * 属性商家是否申请一年期自动续保赋值
	 */
	public void setAutoRenewableByMerchantValue(String merchantValue) {
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (autoRenewable != null) {
			this.autoRenewable = autoRenewable.getValue();
		}
	}
	
	
	/**
	 * 属性套餐代码的getter方法
	 */

	@Column(name = "COMBOCODE")
	@JSON(serialize=false)  
	public String getComboCode() {
		return this.comboCode;
	}

	/**
	 * 属性套餐代码的setter方法
	 */
	public void setComboCode(String comboCode) {
		this.comboCode = comboCode;
	}
	
	/**
	 * 属性套餐类型的getter方法
	 */
	@Column(name = "COMBONAME")
	@JSON(serialize=false)  
	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	
	/**
	 * 属性个人客户的getter方法
	 */

	@Column(name = "PERSONALUSERSERIALNO")
	@JSON(serialize=false)  
	public String getPersonalUserSerialNo() {
		return this.personalUserSerialNo;
	}

	/**
	 * 属性个人客户的setter方法
	 */
	public void setPersonalUserSerialNo(String personalUserSerialNo) {
		this.personalUserSerialNo = personalUserSerialNo;
	}

	/**
	 * 属性用户号的getter方法
	 */
	@Column(name = "CUSTOMERID")
	@JSON(serialize=false)  
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	/**
	 * 属性产品代码的getter方法
	 */

	@Column(name = "PRODUCTCODE")
	public String getProductCode() {
		return productCode;
	}
	
	/**
	 * 属性产品代码的getter方法
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 属性产品名称的getter方法
	 */
	@Column(name = "PRODUCTNAME")
	@JSON(serialize=false)  
	public String getProductName() {
		return productName;
	}
	
	/**
	 * 属性产品名称的setter方法
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 属性商家产品代码的getter方法
	 */
	@Column(name = "MERCHANTPRODUCTCODE")
	@JSON(serialize=false)  
	public String getMerchantProductCode() {
		return merchantProductCode;
	}

	/**
	 * 属性商家产品代码的setter方法
	 */
	public void setMerchantProductCode(String merchantProductCode) {
		this.merchantProductCode = merchantProductCode;
	}

	/**
	 * 属性商家产品名称的setter方法
	 */
	@Column(name = "MERCHANTPRODUCTNAME")
	@JSON(serialize=false)  
	public String getMerchantProductName() {
		return merchantProductName;
	}

	/**
	 * 属性商家产品名称的setter方法
	 */
	public void setMerchantProductName(String merchantProductName) {
		this.merchantProductName = merchantProductName;
	}

	/**
	 * 属性代理人代码的getter方法
	 */
	@Column(name = "AGENTCODE")
	@JSON(serialize=false)  
	public String getAgentCode() {
		return agentCode;
	}

	/**
	 * 属性代理人代码的setter方法
	 */
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	/** 
	 * 属性代理人协议号的getter方法 
	 */
	@Column(name = "AGREEMENTNO")
	@JSON(serialize=false)  
	public String getAgreementNo() {
		return agreementNo;
	}
	
	/** 
	 * 属性代理人协议号的setter方法 
	 */
	public void setAgreementNo(String agreementNo){
		this.agreementNo = agreementNo;
	}

	/** 
	 * 属性代理人协议号的getter方法 
	 */
	@Column(name = "AGENTNAME")
	@JSON(serialize=false)  
	public String getAgentName() {
		return agentName;
	}
	
	/** 
	 * 属性代理人协议号的setter方法 
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	/** 
	 * 属性代理人所属部门的getter方法 
	 */
	@Column(name = "DEPARTMENTNO")
	@JSON(serialize=false)  
	public String getDepartmentNo() {
		return departmentNo;
	}
	
	/** 
	 * 属性代理人所属部门的setter方法 
	 */
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}
	
	/** 
	 * 属性代理人所属部门名称的getter方法 
	 */
	@Column(name = "DEPARTMENTNAME")
	@JSON(serialize=false)  
	public String getDepartmentName() {
		return departmentName;
	}
	
	/** 
	 * 属性代理人所属部门名称的setter方法 
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	/** 
	 * 属性渠道代码的getter方法 
	 */
	@Column(name = "INTERMEDIARYCODE")
	@JSON(serialize=false)  
	public String getIntermediaryCode() {
		return intermediaryCode;
	}
	
	/** 
	 * 属性渠道代码的setter方法 
	 */
	public void setIntermediaryCode(String intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}
	
	/** 
	 * 属性渠道名称的getter方法 
	 */
	@Column(name = "INTERMEDIARYNAME")
	@JSON(serialize=false)  
	public String getIntermediaryName() {
		return intermediaryName;
	}
	
	/** 
	 * 属性渠道名称的setter方法 
	 */
	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}
	/**
	 * 属性网点代码的getter方法
	 */

	@Column(name = "BRANCHCODE")
	@JSON(serialize=false)  
	public String getBranchCode() {
		return this.branchCode;
	}

	/**
	 * 属性网点代码的setter方法
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	/**
	 * 属性网点名称的getter方法
	 */
	@Column(name = "BRANCHNAME")
	@JSON(serialize=false)  
	public String getBranchName() {
		return branchName;
	}
	
	/**
	 * 属性网点名称的setter方法
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	/** 
	 * 属性机构代码的getter方法 
	 */
	@Column(name = "ORGANIZATIONCODE")
	@JSON(serialize=false)  
	public String getOrganizationCode() {
		return organizationCode;
	}
	
	/** 
	 * 属性机构代码的setter方法 
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	/** 
	 * 属性机构名称的getter方法 
	 */
	@Column(name = "ORGANIZATIONNAME")
	@JSON(serialize=false)  
	public String getOrganizationName() {
		return organizationName;
	}
	
	/** 
	 * 属性机构名称的setter方法 
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	
	/** 
	 * 属性合作机构代码的getter方法 
	 */
	@Column(name = "INSTITUTIONCODE")
	@JSON(serialize=false)  
	public String getInstitutionCode() {
		return institutionCode;
	}
	
	/** 
	 * 属性合作机构代码的setter方法 
	 */
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	/** 
	 * 属性合作机构名称的getter方法 
	 */
	@Column(name = "INSTITUTIONNAME")
	@JSON(serialize=false)  
	public String getInstitutionName() {
		return institutionName;
	}
	
	/** 
	 * 属性合作机构名称的setter方法 
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	
	/** 
	 * 属性同步状态的getter方法 
	 */
	@Column(name = "SYNCSTATUS")
	@JSON(serialize=false)  
	public Integer getSyncStatus() {
		return syncStatus;
	}
	
	/**
	 * 属性同步状态枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public SyncStatus getEnumSyncStatus() {
		if (getSyncStatus() == null) {
			return null;
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus;
	}
	
	/**
	 * 属性同步状态核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getSyncStatusByCoreValue() {
		if (getSyncStatus() == null) {
			return "";
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus.getCoreValue();
	}
	
	/**
	 * 属性同步状态商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getSyncStatusByMerchantValue() {
		if (getSyncStatus() == null) {
			return "";
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus.getMerchantValue();
	}
	
	/** 
	 * 属性同步状态的setter方法 
	 */
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	/**
	 * 属性同步状态赋值
	 */
	public void setEnumSyncStatus(SyncStatus  syncStatus) {
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * 属性核心同步状态赋值
	 */
	public void setSyncStatusByCoreValue(String coreValue) {
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByCoreValue(SyncStatus.class, coreValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * 属性商家同步状态赋值
	 */
	public void setSyncStatusByMerchantValue(String merchantValue) {
		SyncStatus syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(SyncStatus.class, merchantValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/** 
	 * 属性同步状态描述的getter方法 
	 */
	@Column(name = "SYNCSTATUSDESC")
	@JSON(serialize=false)  
	public String getSyncStatusDesc() {
		return syncStatusDesc;
	}
	
	/** 
	 * 属性同步状态描述的setter方法 
	 */
	public void setSyncStatusDesc(String syncStatusDesc) {
		this.syncStatusDesc = syncStatusDesc;
	}

	/** 
	 * 属性同步开始时间的getter方法 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCSTARTTIME")
	@JSON(serialize=false)  
	public Date getSyncStartTime() {
		return syncStartTime;
	}
	
	/** 
	 * 属性同步开始时间的setter方法 
	 */
	public void setSyncStartTime(Date syncStartTime) {
		this.syncStartTime = syncStartTime;
	}
	
	/** 
	 * 属性同步结束时间的getter方法 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCENDTIME")
	@JSON(serialize=false)  
	public Date getSyncEndTime() {
		return syncEndTime;
	}
	
	/** 
	 * 属性同步结束时间的setter方法 
	 */
	public void setSyncEndTime(Date syncEndTime) {
		this.syncEndTime = syncEndTime;
	}
	
	/** 
	 * 属性现金价值的getter方法 
	 */
	@Column(name = "CASHVALUE")
	@JSON(serialize=false)  
	public BigDecimal getCashValue() {
		return cashValue;
	}

	/** 
	 * 属性现金价值的setter方法 
	 */
	public void setCashValue(BigDecimal cashValue) {
		this.cashValue = cashValue;
	}
	
	/** 
	 * 属性是否可冲正的getter方法 
	 */
	@Column(name = "REVERSALFLAG")
	@JSON(serialize=false)  
	public Integer getReversalFlag() {
		return reversalFlag;
	}

	/**
	 * 属性是否可冲正枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumReversalFlag() {
		if (getReversalFlag() == null) {
			return null;
		}
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getReversalFlag());
		return reversalFlag;
	}
	
	/**
	 * 属性是否可冲正核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getReversalFlagByCoreValue() {
		if (getReversalFlag() == null) {
			return "";
		}
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getReversalFlag());
		return reversalFlag.getCoreValue();
	}
	
	/**
	 * 属性是否可冲正商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getReversalFlagByMerchantValue() {
		if (getReversalFlag() == null) {
			return "";
		}
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getReversalFlag());
		return reversalFlag.getMerchantValue();
	}
	
	
	/** 
	 * 属性是否可冲正的setter方法 
	 */
	public void setReversalFlag(Integer reversalFlag) {
		this.reversalFlag = reversalFlag;
	}
	
	/**
	 * 属性是否可冲正赋值
	 */
	public void setEnumReversalFlag(BooleanStatus  reversalFlag) {
		if (reversalFlag != null) {
			this.reversalFlag = reversalFlag.getValue();
		}
	}
	
	/**
	 * 属性核心是否可冲正赋值
	 */
	public void setReversalFlagByCoreValue(String coreValue) {
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (reversalFlag != null) {
			this.reversalFlag = reversalFlag.getValue();
		}
	}
	
	/**
	 * 属性商家是否可冲正赋值
	 */
	public void setReversalFlagByMerchantValue(String merchantValue) {
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (reversalFlag != null) {
			this.reversalFlag = reversalFlag.getValue();
		}
	}
	
	/** 
	 * 属性全额领取情况下是否必须退保的getter方法 
	 */
	@Column(name = "MUSTCANCELFLAG")
	@JSON(serialize=false)  
	public Integer getMustCancelFlag() {
		return mustCancelFlag;
	}
	
	/**
	 * 属性全额领取情况下是否必须退保枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumMustCancelFlag() {
		if (getMustCancelFlag() == null) {
			return null;
		}
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getMustCancelFlag());
		return mustCancelFlag;
	}
	
	/**
	 * 属性全额领取情况下是否必须退保核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getMustCancelFlagByCoreValue() {
		if (getMustCancelFlag() == null) {
			return "";
		}
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getMustCancelFlag());
		return mustCancelFlag.getCoreValue();
	}
	
	/**
	 * 属性全额领取情况下是否必须退保商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getMustCancelFlagByMerchantValue() {
		if (getMustCancelFlag() == null) {
			return "";
		}
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getMustCancelFlag());
		return mustCancelFlag.getMerchantValue();
	}
	
	/** 
	 * 属性全额领取情况下是否必须退保的setter方法 
	 */
	public void setMustCancelFlag(Integer mustCancelFlag) {
		this.mustCancelFlag = mustCancelFlag;
	}
	
	/**
	 * 属性额领取情况下是否必须退保赋值
	 */
	public void setEnumMustCancelFlag(BooleanStatus  mustCancelFlag) {
		if (mustCancelFlag != null) {
			this.mustCancelFlag = mustCancelFlag.getValue();
		}
	}
	
	/**
	 * 属性核心额领取情况下是否必须退保赋值
	 */
	public void setMustCancelFlagByCoreValue(String coreValue) {
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (mustCancelFlag != null) {
			this.mustCancelFlag = mustCancelFlag.getValue();
		}
	}
	
	/**
	 * 属性商家额领取情况下是否必须退保赋值
	 */
	public void setMustCancelFlagByMerchantValue(String merchantValue) {
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (mustCancelFlag != null) {
			this.mustCancelFlag = mustCancelFlag.getValue();
		}
	}
	
	/** 
	 * 属性是否允许部分支取的getter方法 
	 */
	@Column(name = "ALLOWPARTYWITHDROWFLAG")
	@JSON(serialize=false)  
	public Integer getAllowPartyWithdrowFlag() {
		return allowPartyWithdrowFlag;
	}
	
	/**
	 * 属性是否允许部分支取枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumAllowPartyWithdrowFlag() {
		if (getAllowPartyWithdrowFlag() == null) {
			return null;
		}
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAllowPartyWithdrowFlag());
		return allowPartyWithdrowFlag;
	}
	
	/**
	 * 属性是否允许部分支取核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAllowPartyWithdrowFlagByCoreValue() {
		if (getAllowPartyWithdrowFlag() == null) {
			return "";
		}
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAllowPartyWithdrowFlag());
		return allowPartyWithdrowFlag.getCoreValue();
	}
	
	/**
	 * 属性是否允许部分支取商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAllowPartyWithdrowFlagByMerchantValue() {
		if (getAllowPartyWithdrowFlag() == null) {
			return "";
		}
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAllowPartyWithdrowFlag());
		return allowPartyWithdrowFlag.getMerchantValue();
	}
	
	
	/** 
	 * 属性是否允许部分支取的setter方法 
	 */
	public void setAllowPartyWithdrowFlag(Integer allowPartyWithdrowFlag) {
		this.allowPartyWithdrowFlag = allowPartyWithdrowFlag;
	}
	
	/**
	 * 属性是否允许部分支取赋值
	 */
	public void setEnumAllowPartyWithdrowFlag(BooleanStatus  allowPartyWithdrowFlag) {
		if (allowPartyWithdrowFlag != null) {
			this.allowPartyWithdrowFlag = allowPartyWithdrowFlag.getValue();
		}
	}
	
	/**
	 * 属性核心是否允许部分支取赋值
	 */
	public void setAllowPartyWithdrowFlagByCoreValue(String coreValue) {
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (allowPartyWithdrowFlag != null) {
			this.allowPartyWithdrowFlag = allowPartyWithdrowFlag.getValue();
		}
	}
	
	/**
	 * 属性商家是否允许部分支取赋值
	 */
	public void setAllowPartyWithdrowFlagByMerchantValue(String merchantValue) {
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (allowPartyWithdrowFlag != null) {
			this.allowPartyWithdrowFlag = allowPartyWithdrowFlag.getValue();
		}
	}
	
	/**
	 * 属性保全受理时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRESERVEACCEPTTIME")
	@JSON(serialize=false)  
	public Date getPreserveAcceptTime() {
		return preserveAcceptTime;
	}
	
	/**
	 * 属性保全受理时间的setter方法
	 */
	public void setPreserveAcceptTime(Date preserveAcceptTime) {
		this.preserveAcceptTime = preserveAcceptTime;
	}
	
	/**
	 * 属性保全生效时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRESERVEEFFECTIVETIME")
	@JSON(serialize=false)  
	public Date getPreserveEffectiveTime() {
		return preserveEffectiveTime;
	}
	
	/**
	 * 属性保全生效时间的setter方法
	 */
	public void setPreserveEffectiveTime(Date preserveEffectiveTime) {
		this.preserveEffectiveTime = preserveEffectiveTime;
	}
	
	/**
	 * 属性撤单时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REFUNDPOLICYTIME")
	@JSON(serialize=false)  
	public Date getReFundPolicyTime() {
		return reFundPolicyTime;
	}
	
	/**
	 * 属性撤单时间的setter方法
	 */
	public void setReFundPolicyTime(Date reFundPolicyTime) {
		this.reFundPolicyTime = reFundPolicyTime;
	}

	/**
	 * 属性撤单成功时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REFUNDPOLICYSUCCESSTIME")
	@JSON(serialize=false)  
	public Date getReFundPolicySuccessTime() {
		return reFundPolicySuccessTime;
	}
	
	/**
	 * 属性撤单成功时间的setter方法
	 */
	public void setReFundPolicySuccessTime(Date reFundPolicySuccessTime) {
		this.reFundPolicySuccessTime = reFundPolicySuccessTime;
	}

	
	/**
	 * 属性操作员的getter方法
	 */

	@Column(name = "OPERATORID")
	@JSON(serialize=false)  
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作员的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * 属性发票的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public Invoice getInvoice() {
		return this.invoice;
	}

	/**
	 * 属性发票的setter方法
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
		if (getInvoice() != null && getInvoice().getInsurancePolicy() == null) {
			getInvoice().setInsurancePolicy(this);
		}
	}

	/**
	 * 属性付款账户的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public PaymentAccount getPaymentAccount() {
		return this.paymentAccount;
	}

	/**
	 * 属性付款账户的setter方法
	 */
	public void setPaymentAccount(PaymentAccount paymentAccount) {
		this.paymentAccount = paymentAccount;
		if (getPaymentAccount() != null && getPaymentAccount().getInsurancePolicy() == null) {
			getPaymentAccount().setInsurancePolicy(this);
		}
	}

	/**
	 * 属性收件人的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public Addressee getAddressee() {
		return this.addressee;
	}

	/**
	 * 属性收件人的setter方法
	 */
	public void setAddressee(Addressee addressee) {
		this.addressee = addressee;
		if (getAddressee() != null && getAddressee().getInsurancePolicy() == null) {
			getAddressee().setInsurancePolicy(this);
		}
	}

	/**
	 * 属性订单的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public OrderForm getOrderForm() {
		return orderForm;
	}

	/**
	 * 属性订单的setter方法
	 */
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
		if (getOrderForm() != null && getOrderForm().getInsurancePolicy() == null) {
			getOrderForm().setInsurancePolicy(this);
		}
	}

	/**
	 * 属性投保人的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public InsuranceApplicant getInsuranceApplicant() {
		return this.insuranceApplicant;
	}

	/**
	 * 属性投保人的setter方法
	 */
	public void setInsuranceApplicant(InsuranceApplicant insuranceApplicant) {
		this.insuranceApplicant = insuranceApplicant;
		if (getInsuranceApplicant()!= null && getInsuranceApplicant().getInsurancePolicy() == null) {
			getInsuranceApplicant().setInsurancePolicy(this);
		}
	}
	
	/**
	 * 属性被保险人的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "insurancePolicy")
	@Where(clause="ROLEKIND = 'Insured'")
	@JSON(serialize=false)  
	public List<Insured> getInsureds() {
		return this.insureds;
	}
	
	/**
	 * 属性被保险人的setter方法
	 */
	public void setInsureds(List<Insured> insureds) {
		this.insureds = insureds;
	}

	/**
	 * 属性添加所有被保险人信息
	 */
	public void addAllInsureds(List<Insured> insureds) {
		
		for (Insured insured:insureds) {
			if (!getInsureds().contains(insured)) {
				getInsureds().add(insured);
			}
		}
		
		for (Insured insured:getInsureds()) {
			if (insured.getInsurancePolicy() == null) {
				insured.setInsurancePolicy(this);
			}
			
		}
	}
	
	/**
	 * 属性被保险人数量的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public Integer getInsuredNumber() {
		insuredNumber = getInsureds().size();
		return insuredNumber;
	}
	
	/**
	 * 属性被保险人数量的setter方法
	 */
	public void setInsuredNumber(Integer insuredNumber) {
		this.insuredNumber = insuredNumber;
	}

	/**
	 * 属性受益人的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@Where(clause="ROLEKIND = 'Beneficiary'")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)  
	public List<Beneficiary> getBeneficiaries() {
		return this.beneficiaries;
	}
	
	/**
	 * 属性受益人的setter方法
	 */
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	/**
	 * 属性添加所有受益人
	 */
	public void addAllBeneficiaries(List<Beneficiary> beneficiaries) {
		for (Beneficiary beneficiary:beneficiaries) {
			if (!getBeneficiaries().contains(beneficiary)) {
				getBeneficiaries().add(beneficiary);
			}
		}
		for (Beneficiary beneficiary:getBeneficiaries()) {
			if (beneficiary.getInsurancePolicy() == null) {
				beneficiary.setInsurancePolicy(this);
			}
		}
	}
	
	/**
	 * 属性受益人数量的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public Integer getBeneficiaryNumber() {
		beneficiaryNumber = getBeneficiaries().size();
		return beneficiaryNumber;
	}

	/**
	 * 属性受益人数量的setter方法
	 */
	public void setBeneficiaryNumber(Integer beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}

	/**
	 * 属性保险责任的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public List<InsurancePolicyLiability> getInsurancePolicyLiabilities() {
		return insurancePolicyLiabilities;
	}
	
	/**
	 * 属性保险责任的setter方法
	 */
	public void setInsurancePolicyLiabilities(
			List<InsurancePolicyLiability> insurancePolicyLiabilities) {
		this.insurancePolicyLiabilities = insurancePolicyLiabilities;
	}

	/**
	 * 属性添加所有保险责任
	 */
	public void addAllInsurancePolicyLiabilities(List<InsurancePolicyLiability> insurancePolicyLiabilities) {
		for (InsurancePolicyLiability insurancePolicyLiability:insurancePolicyLiabilities) {
			if (!getInsurancePolicyLiabilities().contains(insurancePolicyLiability)) {
				getInsurancePolicyLiabilities().add(insurancePolicyLiability);
			}
		}
		
		for (InsurancePolicyLiability insurancePolicyLiability:getInsurancePolicyLiabilities()) {
			if (insurancePolicyLiability.getInsurancePolicy() == null) {
				insurancePolicyLiability.setInsurancePolicy(this);
			}
		}
		
	}
	
	/**
	 * 属性保险责任数量的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public Integer getInsurancePolicyLiabilityNumber() {
		insurancePolicyLiabilityNumber = getInsurancePolicyLiabilities().size();
		return insurancePolicyLiabilityNumber;
	}
	
	/**
	 * 属性保险责任数量的setter方法
	 */
	public void setInsurancePolicyLiabilityNumber(
			Integer insurancePolicyLiabilityNumber) {
		this.insurancePolicyLiabilityNumber = insurancePolicyLiabilityNumber;
	}

	/**
	 * 属性投保告知的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)  
	public List<InsureInformBook> getInsureInformBooks() {
		return insureInformBooks;
	}
	
	/**
	 * 属性投保告知的setter方法
	 */
	public void setInsureInformBooks(List<InsureInformBook> insureInformBooks) {
		this.insureInformBooks = insureInformBooks;
	}

	/**
	 * 属性添加所有投保告知信息
	 */
	public void addAllInsureInformBooks(List<InsureInformBook> insureInformBooks) {
		
		for (InsureInformBook insureInformBook:insureInformBooks) {
			if (!getInsureInformBooks().contains(insureInformBook)) {
				getInsureInformBooks().add(insureInformBook);
			}
		}
		
		for (InsureInformBook insureInformBook:getInsureInformBooks()) {
			if (insureInformBook.getInsurancePolicy() == null) {
				insureInformBook.setInsurancePolicy(this);
			}
		}
		
	}
	
	/**
	 * 属性资金划拨的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)  
	public List<FundsTransfer> getFundsTransfers() {
		return fundsTransfers;
	}
	
	/**
	 * 属性资金划拨的setter方法
	 */
	public void setFundsTransfers(List<FundsTransfer> fundsTransfers) {
		this.fundsTransfers = fundsTransfers;
	}

	/**
	 * 属性添加所有资金划拨信息
	 */
	public void addAllFundsTransfers(List<FundsTransfer> fundsTransfers) {
		for (FundsTransfer fundsTransfer:fundsTransfers) {
			if (!getFundsTransfers().contains(fundsTransfer)) {
				getFundsTransfers().add(fundsTransfer);
			}
		}
		for (FundsTransfer fundsTransfer:getFundsTransfers()) {
			if (fundsTransfer.getInsurancePolicy() == null) {
				fundsTransfer.setInsurancePolicy(this);
			}
		}
	}
	
	
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JSON(serialize=false)  
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	@JSON(serialize=false)  
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性 续期缴费形式 的getter方法
	 */
	@Column(name = "EXPAYMODE")
	@JSON(serialize=false)  
	public Integer getExPayMode() {
		return exPayMode;
	}

	/**
	 * 属性 续期缴费形式 的setter方法
	 */
	public void setExPayMode(Integer exPayMode) {
		this.exPayMode = exPayMode;
	}

	/**
	 * 属性 保单递送方式 的getter方法
	 */
	@Column(name = "GETPOLMODE")
	@JSON(serialize=false)  
	public Integer getGetPolMode() {
		return getPolMode;
	}

	/**
	 * 属性 保单递送方式 的setter方法
	 */
	public void setGetPolMode(Integer getPolMode) {
		this.getPolMode = getPolMode;
	}

	/**
	 * 属性 保单密码 的getter方法
	 */
	@Column(name = "PASSWORD")
	@JSON(serialize=false)  
	public String getPassword() {
		return password;
	}

	/**
	 * 属性 保单密码 的setter方法
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 属性 特别约定 的getter方法
	 */
	@Column(name = "SPECCONTENT")
	@JSON(serialize=false)  
	public String getSpecContent() {
		return specContent;
	}

	/**
	 * 属性 特别约定 的setter方法
	 */
	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	/**
	 * 属性 发票印刷号码 的getter方法
	 */
	@Column(name = "TEMPFEENO")
	@JSON(serialize=false)  
	public String getTempFeeNo() {
		return tempFeeNo;
	}

	/**
	 * 属性 发票印刷号码 的setter方法
	 */
	public void setTempFeeNo(String tempFeeNo) {
		this.tempFeeNo = tempFeeNo;
	}

	/**
	 * 属性 代理人组别 的getter方法
	 */
	@Column(name = "AGENTGROUP")
	@JSON(serialize=false)  
	public String getAgentGroup() {
		return agentGroup;
	}

	/**
	 * 属性 代理人组别 的setter方法
	 */
	public void setAgentGroup(String agentGroup) {
		this.agentGroup = agentGroup;
	}

	/**
	 * 属性 合同争议处理方式 的getter方法
	 */
	@Column(name = "DISPUTEDFLAG")
	@JSON(serialize=false)  
	public Integer getDisputedFlag() {
		return disputedFlag;
	}

	/**
	 * 属性 合同争议处理方式 的setter方法
	 */
	public void setDisputedFlag(Integer disputedFlag) {
		this.disputedFlag = disputedFlag;
	}

	/**
	 * 属性 仲裁委员会名称 的getter方法
	 */
	@Column(name = "ACNAME")
	@JSON(serialize=false)  
	public String getAcName() {
		return acName;
	}

	/**
	 * 属性 仲裁委员会名称 的setter方法
	 */
	public void setAcName(String acName) {
		this.acName = acName;
	}

	/**
	 * 属性 回访标志 的getter方法
	 */
	@Column(name = "ISVISIT")
	@JSON(serialize=false)  
	public Integer getIsVisit() {
		return isVisit;
	}

	/**
	 * 属性 回访标志 的setter方法
	 */
	public void setIsVisit(Integer isVisit) {
		this.isVisit = isVisit;
	}

	/**
	 * 属性 绑定标志 的getter方法
	 */
	@Column(name = "ISBIND")
	@JSON(serialize=false)  
	public Integer getIsBind() {
		return isBind;
	}

	/**
	 * 属性 绑定标志 的setter方法
	 */
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	/**
	 * 属性 绑定标志 的getter方法
	 */
	@Column(name = "INSUREINFORMBOOKNUMBER")
	@JSON(serialize=false)  
	public Integer getInsureInformBookNumber() {
		return insureInformBookNumber;
	}

	/**
	 * 属性 绑定标志 的setter方法
	 */
	public void setInsureInformBookNumber(Integer insureInformBookNumber) {
		this.insureInformBookNumber = insureInformBookNumber;
	}
	
	/**
	 * 属性 续期缴费方式 枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public ExPayMode getEnumExPayMode() {
		if (getExPayMode() == null) {
			return null;
		}
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByValue(ExPayMode.class, getExPayMode());
		return exPayMode;
	}
	
	/**
	 * 属性 续期缴费方式 核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getExPayModeByCoreValue() {
		if (getExPayMode() == null) {
			return "";
		}
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByValue(ExPayMode.class, getExPayMode());
		return exPayMode.getCoreValue();
	}
	
	/**
	 * 属性 续期缴费方式 商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getExPayModeByMerchantValue() {
		if (getExPayMode() == null) {
			return "";
		}
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByValue(ExPayMode.class, getExPayMode());
		return exPayMode.getMerchantValue();
	}
	
	
	
	
	/**
	 * 属性 续期缴费方式 赋值
	 */
	public void setEnumExPayMode(ExPayMode  exPayMode) {
		if (exPayMode != null) {
			this.exPayMode = exPayMode.getValue();
		}
		
	}
	
	/**
	 * 属性核心 续期缴费方式 赋值
	 */
	public void setExPayModeByCoreValue(String coreValue) {
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByCoreValue(ExPayMode.class, coreValue);
		if (exPayMode != null) {
			this.exPayMode = exPayMode.getValue();
		}
	}
	
	/**
	 * 属性商家 续期缴费方式 赋值
	 */
	public void setExPayModeByMerchantValue(String merchantValue) {
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByMerchantValue(ExPayMode.class, merchantValue);
		if (exPayMode != null) {
			this.exPayMode = exPayMode.getValue();
		}
	}
	
	/**
	 * 属性 保单递送方式 枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public GetPolMode getEnumGetPolMode() {
		if (getGetPolMode() == null) {
			return null;
		}
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByValue(GetPolMode.class, getGetPolMode());
		return getPolMode;
	}
	
	/**
	 * 属性 保单递送方式 核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGetPolModeByCoreValue() {
		if (getGetPolMode() == null) {
			return "";
		}
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByValue(GetPolMode.class, getGetPolMode());
		return getPolMode.getCoreValue();
	}
	
	/**
	 * 属性 保单递送方式 商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGetPolModeByMerchantValue() {
		if (getGetPolMode() == null) {
			return "";
		}
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByValue(GetPolMode.class, getGetPolMode());
		return getPolMode.getMerchantValue();
	}
	
	
	
	
	/**
	 * 属性 保单递送方式 赋值
	 */
	public void setEnumGetPolMode(GetPolMode  getPolMode) {
		if (getPolMode != null) {
			this.getPolMode = getPolMode.getValue();
		}
		
	}
	
	/**
	 * 属性核心 保单递送方式 赋值
	 */
	public void setGetPolModeByCoreValue(String coreValue) {
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByCoreValue(GetPolMode.class, coreValue);
		if (getPolMode != null) {
			this.getPolMode = getPolMode.getValue();
		}
	}
	
	/**
	 * 属性商家 保单递送方式 赋值
	 */
	public void setGetPolModeByMerchantValue(String merchantValue) {
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByMerchantValue(GetPolMode.class, merchantValue);
		if (getPolMode != null) {
			this.getPolMode = getPolMode.getValue();
		}
	}
	
	/**
	 * 属性 合同争议处理方式 枚举类的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public DisputedFlag getEnumDisputedFlag() {
		if (getDisputedFlag() == null) {
			return null;
		}
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByValue(DisputedFlag.class, getDisputedFlag());
		return disputedFlag;
	}
	
	/**
	 * 属性 合同争议处理方式 核心值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDisputedFlagByCoreValue() {
		if (getDisputedFlag() == null) {
			return "";
		}
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByValue(DisputedFlag.class, getDisputedFlag());
		return disputedFlag.getCoreValue();
	}
	
	/**
	 * 属性 合同争议处理方式 商家值的getter方法
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDisputedFlagByMerchantValue() {
		if (getDisputedFlag() == null) {
			return "";
		}
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByValue(DisputedFlag.class, getDisputedFlag());
		return disputedFlag.getMerchantValue();
	}
	
	
	
	
	/**
	 * 属性 合同争议处理方式 赋值
	 */
	public void setEnumDisputedFlag(DisputedFlag  disputedFlag) {
		if (disputedFlag != null) {
			this.disputedFlag = disputedFlag.getValue();
		}
		
	}
	
	/**
	 * 属性核心 合同争议处理方式 赋值
	 */
	public void setDisputedFlagByCoreValue(String coreValue) {
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByCoreValue(DisputedFlag.class, coreValue);
		if (disputedFlag != null) {
			this.disputedFlag = disputedFlag.getValue();
		}
	}
	
	/**
	 * 属性商家 合同争议处理方式 赋值
	 */
	public void setDisputedFlagByMerchantValue(String merchantValue) {
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(DisputedFlag.class, merchantValue);
		if (disputedFlag != null) {
			this.disputedFlag = disputedFlag.getValue();
		}
	}

	@Column(name = "QUOTENO")
	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	@Column(name = "RENEWALFLAG")
	@JSON(serialize=false)  
	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	@Column(name = "OLDLPOLICYNO")
	@JSON(serialize=false)  
	public String getOldlPolicyNo() {
		return oldlPolicyNo;
	}

	public void setOldlPolicyNo(String oldlPolicyNo) {
		this.oldlPolicyNo = oldlPolicyNo;
	}

	@Column(name = "CURRENCY")
	@JSON(serialize=false)  
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "INPUTHOUR")
	@JSON(serialize=false)  
	public String getInputHour() {
		return inputHour;
	}

	public void setInputHour(String inputHour) {
		this.inputHour = inputHour;
	}

	@Column(name = "BUSINESSAREA")
	@JSON(serialize=false)  
	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	@Column(name = "REASON")
	@JSON(serialize=false)  
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "FLAG")
	@JSON(serialize=false)  
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "PRECHECKDATE")
	@JSON(serialize=false)  
	public String getPrecheckDate() {
		return precheckDate;
	}

	public void setPrecheckDate(String precheckDate) {
		this.precheckDate = precheckDate;
	}

	@Column(name = "MOREBUYNO")
	@JSON(serialize=false)  
	public String getMoreBuyNo() {
		return moreBuyNo;
	}

	public void setMoreBuyNo(String moreBuyNo) {
		this.moreBuyNo = moreBuyNo;
	}
	
	@Column(name = "BUSINESSSOURCE")
	@JSON(serialize=false)  
	public String getBusinessSource() {
		return businessSource;
	}

	public void setBusinessSource(String businessSource) {
		this.businessSource = businessSource;
	}

	@Column(name = "GROUPCHANNEL")
	@JSON(serialize=false)  
	public String getGroupChannel() {
		return groupChannel;
	}

	public void setGroupChannel(String groupChannel) {
		this.groupChannel = groupChannel;
	}

	@Column(name = "SELLTYPE")
	@JSON(serialize=false)  
	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	@JSON(serialize=false)  
	@Column(name = "REMINDCOUNT")
	public Integer getRemindCount() {
		return remindCount;
	}

	public void setRemindCount(Integer remindCount) {
		this.remindCount = remindCount;
	}
	
}
