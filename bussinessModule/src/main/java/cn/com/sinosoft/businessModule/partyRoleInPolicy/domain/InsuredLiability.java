package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.enums.dictionary.BenefitPeriodType;
import cn.com.sinosoft.businessModule.enums.dictionary.GroupType;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentDurationMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMode;
import cn.com.sinosoft.businessModule.enums.dictionary.SubRiskFlag;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类InsuredLiability
 */
@Entity
@Table(name = "INSUREDLIABILITY")
public class InsuredLiability implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885224121908158768L;

	/** 属性序号 */
	private String serialNo;

	/** 属性保障/险种ID */
	private String liabilityId;
	
	/** 属性保障/险种代码 */
	private String liabilityCode;
	
	/** 属性核心保障/险种代码 */
	private String coreCode;

	/** 属性保障/险种名称 */
	private String liabilityName;
	
	/** 属性产品代码 */
	private String productCode;

	/** 属性产品名称 */
	private String productName;
	
	/** 属性商家产品代码 */
	private String merchantProductCode;
	
	/** 属性商家产品名称 */
	private String merchantProductName;
	
	/** 属性商家险种代码 */
	private String merchantLiabilityCode;
	
	/** 属性商家险种代码 */
	private String merchantLiabilityName;
	
	/** 属性主附险标志 */
	private Integer subRiskFlag;

	/** 属性适用群体 */
	private Integer groupType;

	/** 属性保障/险种顺序 */
	private Integer liabilityOrder;
	
	/** 属性保额 */
	private BigDecimal insuredAmount;

	/** 属性保费 */
	private BigDecimal premium;

	/** 属性折扣保费 */
	private BigDecimal discountPremium;
	
	/** 属性折扣代码 */
	private String discountTypeCode;

	/** 属性折扣系数 */
	private BigDecimal discountRate;
	
	/** 属性保障年期 */
	private Integer benefitPeriod;

	/** 属性保障年期类型 */
	private Integer benefitPeriodType;
	
	/** 属性保险起期  */
	private Date insuranceStartPeriod;
	
	/** 属性保险止期 */
	private Date insuranceEndPeriod;
	
	/** 属性缴费方式 */
	private Integer paymentMode;

	/** 属性缴费年期 */
	private Integer paymentDuration;

	/** 属性缴费年期类型 */
	private Integer paymentDurationMode;

	/** 属性免赔额 */
	private BigDecimal deductibles;

	/** 属性赔付比率 */
	private BigDecimal lossRatio;
	
	/** 属性被保险人 */
	private Insured insured;
	
	/** 属性保障/责任 */
	private InsuredLiability insuredLiability;
	
	/** 属性 保险责任 */
	private List<InsuredLiability> insuredLiabilities = new ArrayList<InsuredLiability>(0);
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	//add
	/** 属性 主险险种代码 */
	private String mainRiskCode;

	/** 属性 险种类型 */
	private Integer riskType;

	/** 属性 费率 */
	private BigDecimal Rate;

	/** 属性 生效日 */
	private Date inceptionDate;

	/** 属性 档次 */
	private String rank;

	/** 属性 份数 */
	private Integer unitCount;

	/** 属性 扣款间隔 */
	private String costIntv;

	/** 属性 扣款时间 */
	private Date costDate;

	/** 属性 特别约定 */
	private String specContent;

	/** 属性 缴费年期年龄标志 */
	private Integer payEndYearFlag;

	/** 属性 缴费年期年龄 */
	private Integer payEndYear;

	/** 属性 领取年龄年期标志 */
	private Integer getYearFlag;

	/** 属性 领取年龄 */
	private Integer getYear;

	/** 属性 保险年期年龄标志 */
	private Integer insuYearFlag;

	/** 属性 保险年期年龄 */
	private Integer insuYear;

	/** 属性 领取方式 */
	private Integer getIntv;

	/** 属性 领取银行编码 */
	private String getBankCode;

	/** 属性 领取银行账户 */
	private String getBankAccNo;

	/** 属性 领取银行户名 */
	private String getAccName;

	/** 属性 领取银行省编码 */
	private String getAccProvince;

	/** 属性 领取银行市编码 */
	private String getAccCity;

	/** 属性 领取银行卡折类型 */
	private Integer getAccType;

	/** 属性 垫交标志 */
	private Integer autoPayFlag;

	/** 属性 红利分配标记 */
	private Integer bonusPayMode;

	/** 属性 减额交清标志 */
	private Integer subFlag;

	/** 属性 红利领取方式 */
	private Integer bonusGetMode;

	/** 属性 自动续交标志 */
	private Integer autoRNewFlag;

	/** 属性 健康告之标志 */
	private Integer healthFlag;

	/** 属性 满期领取金领取方式 */
	private Integer fullBonusGetMode;

	/** 属性 初始费用率 */
	private BigDecimal firstRate;

	/** 属性 保证利率 */
	private BigDecimal sureRate;

	/**
	 * 类InsuranceLiability的默认构造方法
	 */
	public InsuredLiability() {
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
	 * 属性保障/险种ID的getter方法
	 */
	@Column(name = "LIABILITYID")
	public String getLiabilityId() {
		return liabilityId;
	}
	
	/**
	 * 属性保障/险种ID的setter方法
	 */
	public void setLiabilityId(String liabilityId) {
		this.liabilityId = liabilityId;
	}
	
	/**
	 * 属性保障/险种代码的getter方法
	 */
	@Column(name = "LIABILITYCODE")
	public String getLiabilityCode() {
		return liabilityCode;
	}
	
	/**
	 * 属性保障/险种代码的setter方法
	 */
	public void setLiabilityCode(String liabilityCode) {
		this.liabilityCode = liabilityCode;
	}

	/**
	 * 属性核心保障/险种代码的getter方法
	 */

	@Column(name = "CORECODE")
	public String getCoreCode() {
		return this.coreCode;
	}

	/**
	 * 属性核心保障/险种代码的setter方法
	 */
	public void setCoreCode(String coreCode) {
		this.coreCode = coreCode;
	}

	/**
	 * 属性保障/险种名称的getter方法
	 */

	@Column(name = "LIABILITYNAME")
	public String getLiabilityName() {
		return this.liabilityName;
	}

	/**
	 * 属性保障/险种名称的setter方法
	 */
	public void setLiabilityName(String liabilityName) {
		this.liabilityName = liabilityName;
	}

	/**
	 * 属性产品代码的getter方法
	 */

	@Column(name = "PRODUCTCODE")
	public String getProductCode() {
		return this.productCode;
	}

	/**
	 * 属性产品代码的setter方法
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 属性产品名称的getter方法
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
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
	 * 属性商家险种代码的getter方法
	 */
	@Column(name = "MERCHANTLIABILITYCODE")
	public String getMerchantLiabilityCode() {
		return merchantLiabilityCode;
	}

	/**
	 * 属性商家险种代码的setter方法
	 */
	public void setMerchantLiabilityCode(String merchantLiabilityCode) {
		this.merchantLiabilityCode = merchantLiabilityCode;
	}
	
	/**
	 * 属性商家险种名称的getter方法
	 */
	@Column(name = "MERCHANTLIABILITYNAME")
	public String getMerchantLiabilityName() {
		return merchantLiabilityName;
	}

	/**
	 * 属性商家险种名称的setter方法
	 */
	public void setMerchantLiabilityName(String merchantLiabilityName) {
		this.merchantLiabilityName = merchantLiabilityName;
	}
	
	/**
	 * 属性主附险标志的getter方法
	 */

	@Column(name = "SUBRISKFLAG")
	public Integer getSubRiskFlag() {
		return this.subRiskFlag;
	}
	
	/**
	 * 属性主附险标志枚举类的getter方法
	 */
	@Transient
	public SubRiskFlag getEnumSubRiskFlag() {
		if (getSubRiskFlag() == null) {
			return null;
		}
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByValue(SubRiskFlag.class, getSubRiskFlag());
		return subRiskFlag;
	}
	
	/**
	 * 属性主附险标志核心值的getter方法
	 */
	@Transient
	public String getSubRiskFlagByCoreValue() {
		if (getSubRiskFlag() == null) {
			return "";
		}
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByValue(SubRiskFlag.class, getSubRiskFlag());
		return subRiskFlag.getCoreValue();
	}
	
	/**
	 * 属性主附险标志商家值的getter方法
	 */
	@Transient
	public String getSubRiskFlagByMerchantValue() {
		if (getSubRiskFlag() == null) {
			return "";
		}
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByValue(SubRiskFlag.class, getSubRiskFlag());
		return subRiskFlag.getMerchantValue();
	}
	
	
	/**
	 * 属性主附险标志的setter方法
	 */
	public void setSubRiskFlag(Integer subRiskFlag) {
		this.subRiskFlag = subRiskFlag;
	}
	
	/**
	 * 属性主附险标志赋值
	 */
	public void setEnumSubRiskFlag(SubRiskFlag  subRiskFlag) {
		if (subRiskFlag != null) {
			this.subRiskFlag = subRiskFlag.getValue();
		}
	}
	
	/**
	 * 属性核心主附险标志赋值
	 */
	public void setSubRiskFlagByCoreValue(String coreValue) {
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByCoreValue(SubRiskFlag.class, coreValue);
		if (subRiskFlag != null) {
			this.subRiskFlag = subRiskFlag.getValue();
		}
	}
	
	/**
	 * 属性商家主附险标志赋值
	 */
	public void setSubRiskFlagByMerchantValue(String merchantValue) {
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(SubRiskFlag.class, merchantValue);
		if (subRiskFlag != null) {
			this.subRiskFlag = subRiskFlag.getValue();
		}
	}
	
	
	/**
	 * 属性群体类型的getter方法
	 */

	@Column(name = "GROUPTYPE")
	public Integer getGroupType() {
		return groupType;
	}
	
	/**
	 * 属性适用群体枚举类的getter方法
	 */
	@Transient
	public GroupType getEnumGroupType() {
		if (getGroupType() == null) {
			return null;
		}
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByValue(GroupType.class, getGroupType());
		return groupType;
	}
	
	/**
	 * 属性适用群体核心值的getter方法
	 */
	@Transient
	public String getGroupTypeByCoreValue() {
		if (getGroupType() == null) {
			return "";
		}
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByValue(GroupType.class, getGroupType());
		return groupType.getCoreValue();
	}
	
	/**
	 * 属性适用群体商家值的getter方法
	 */
	@Transient
	public String getGroupTypeByMerchantValue() {
		if (getGroupType() == null) {
			return "";
		}
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByValue(GroupType.class, getGroupType());
		return groupType.getMerchantValue();
	}
	
	/**
	 * 属性群体类型的getter方法
	 */

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	
	/**
	 * 属性适用群体赋值
	 */
	public void setEnumGroupType(GroupType  groupType) {
		if (groupType != null) {
			this.groupType = groupType.getValue();
		}
	}
	
	/**
	 * 属性核心适用群体赋值
	 */
	public void setGroupTypeByCoreValue(String coreValue) {
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByCoreValue(GroupType.class, coreValue);
		if (groupType != null) {
			this.groupType = groupType.getValue();
		}
	}
	
	/**
	 * 属性商家适用群体赋值
	 */
	public void setGroupTypeByMerchantValue(String merchantValue) {
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByMerchantValue(GroupType.class, merchantValue);
		if (groupType != null) {
			this.groupType = groupType.getValue();
		}
	}
	
	/**
	 * 属性保障/险种顺序的getter方法
	 */
	@Column(name = "LIABILITYORDER")
	public Integer getLiabilityOrder() {
		return liabilityOrder;
	}
	
	/**
	 * 属性保障/险种顺序的setter方法
	 */
	public void setLiabilityOrder(Integer liabilityOrder) {
		this.liabilityOrder = liabilityOrder;
	}
	
	
	/**
	 * 属性保额的getter方法
	 */

	@Column(name = "INSUREDAMOUNT")
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
	 * 属性折扣代码的getter方法
	 */

	@Column(name = "DISCOUNTTYPECODE")
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
	 * 属性保障年期的getter方法
	 */

	@Column(name = "BENEFITPERIOD")
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
	public Integer getBenefitPeriodType() {
		return this.benefitPeriodType;
	}
	
	/**
	 * 属性保障年期类型枚举类的getter方法
	 */
	@Transient
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
	public String getBenefitPeriodTypeByCoreValue() {
		if (getBenefitPeriodType() == null) {
			return "";
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType.getCoreValue();
	}
	
	/**
	 * 属性保障年期类型商家值的getter方法
	 */
	@Transient
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
	public Integer getPaymentDurationMode() {
		return this.paymentDurationMode;
	}
	
	/**
	 * 属性缴费年期类型枚举类的getter方法
	 */
	@Transient
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
	 * 属性免赔额的getter方法
	 */

	@Column(name = "DEDUCTIBLES")
	public BigDecimal getDeductibles() {
		return this.deductibles;
	}

	/**
	 * 属性免赔额的setter方法
	 */
	public void setDeductibles(BigDecimal deductibles) {
		this.deductibles = deductibles;
	}

	/**
	 * 属性赔付比率的getter方法
	 */

	@Column(name = "LOSSRATIO")
	public BigDecimal getLossRatio() {
		return this.lossRatio;
	}

	/**
	 * 属性赔付比率的setter方法
	 */
	public void setLossRatio(BigDecimal lossRatio) {
		this.lossRatio = lossRatio;
	}
	
	/**
	 * 属性保单的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "INSUREDSERIALNO")
	public Insured getInsured() {
		return insured;
	}
	
	/**
	 * 属性保单的setter方法
	 */
	public void setInsured(Insured insured) {
		this.insured = insured;
	}
	
	/**
	 * 属性添加被保人时同时将被保人险种信息赋值给被保人
	 */
	public void addInsured(Insured insured) {
		this.insured = insured;
		if (getInsured()!= null && !getInsured().getInsuredLiabilities().contains(this)) {
			getInsured().getInsuredLiabilities().add(this);
		}
	}
	
	/**
	 * 属性保障/责任的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "PARENTSERIALNO")
	public InsuredLiability getInsuredLiability() {
		return insuredLiability;
	}

	public void setInsuredLiability(InsuredLiability insuredLiability) {
		this.insuredLiability = insuredLiability;
	}
	
	public void addInsuredLiability(InsuredLiability insuredLiability) {
		this.insuredLiability = insuredLiability;
		if (getInsuredLiability()!= null && !getInsuredLiability().getInsuredLiabilities().contains(this)) {
			getInsuredLiability().getInsuredLiabilities().add(this);
		}
	}
	/**
	 * 属性保险责任的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insuredLiability")
	public List<InsuredLiability> getInsuredLiabilities() {
		return insuredLiabilities;
	}
	
	/**
	 * 属性保险责任的setter方法
	 */
	public void setInsuredLiabilities(List<InsuredLiability> insuredLiabilities) {
		this.insuredLiabilities = insuredLiabilities;
	}

	/**
	 * 属性添加所有保险责任
	 */
	public void addAllInsuredLiabilities(List<InsuredLiability> insuredLiabilities) {
		
		for (InsuredLiability insuredLiability:insuredLiabilities) {
			if (!getInsuredLiabilities().contains(insuredLiability)) {
				getInsuredLiabilities().add(insuredLiability);
			}
		}
		
		for (InsuredLiability insuredLiability:getInsuredLiabilities()) {
			if (insuredLiability.getInsuredLiability() == null) {
				insuredLiability.setInsuredLiability(this);
			}
			
		}
		
	}
	
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
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
	 * 属性 主险险种代码 的getter方法
	 * @return the mainRiskCode
	 */
	@Column(name = "MAINRISKCODE")
	public String getMainRiskCode() {
		return mainRiskCode;
	}

	/**
	 * 属性 主险险种代码 的setter方法
	 * @param mainRiskCode the mainRiskCode to set
	 */
	public void setMainRiskCode(String mainRiskCode) {
		this.mainRiskCode = mainRiskCode;
	}

	/**
	 * 属性 险种类型 的getter方法
	 * @return the riskType
	 */
	@Column(name = "RISKTYPE")
	public Integer getRiskType() {
		return riskType;
	}

	/**
	 * 属性 险种类型 的setter方法
	 * @param riskType the riskType to set
	 */
	public void setRiskType(Integer riskType) {
		this.riskType = riskType;
	}

	/**
	 * 属性 费率 的getter方法
	 * @return the rate
	 */
	@Column(name = "RATE")
	public BigDecimal getRate() {
		return Rate;
	}

	/**
	 * 属性 费率 的setter方法
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		Rate = rate;
	}

	/**
	 * 属性 生效日 的getter方法
	 * @return the inceptionDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INCEPTIONDATE")
	public Date getInceptionDate() {
		return inceptionDate;
	}

	/**
	 * 属性 生效日 的setter方法
	 * @param inceptionDate the inceptionDate to set
	 */
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	/**
	 * 属性 档次 的getter方法
	 * @return the rank
	 */
	@Column(name = "RANK")
	public String getRank() {
		return rank;
	}

	/**
	 * 属性 档次 的setter方法
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * 属性 份数 的getter方法
	 * @return the unitCount
	 */
	@Column(name = "UNITCOUNT")
	public Integer getUnitCount() {
		return unitCount;
	}

	/**
	 * 属性 份数 的setter方法
	 * @param unitCount the unitCount to set
	 */
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}

	/**
	 * 属性 扣款间隔 的getter方法
	 * @return the costIntv
	 */
	@Column(name = "COSTINTV")
	public String getCostIntv() {
		return costIntv;
	}

	/**
	 * 属性 扣款间隔 的setter方法
	 * @param costIntv the costIntv to set
	 */
	public void setCostIntv(String costIntv) {
		this.costIntv = costIntv;
	}

	/**
	 * 属性 扣款时间 的getter方法
	 * @return the costDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COSTDATE")
	public Date getCostDate() {
		return costDate;
	}

	/**
	 * 属性 扣款时间 的setter方法
	 * @param costDate the costDate to set
	 */
	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	/**
	 * 属性 特别约定 的getter方法
	 * @return the specContent
	 */
	@Column(name = "SPECCONTENT")
	public String getSpecContent() {
		return specContent;
	}

	/**
	 * 属性 特别约定 的setter方法
	 * @param specContent the specContent to set
	 */
	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	/**
	 * 属性 缴费年期年龄标志 的getter方法
	 * @return the payEndYearFlag
	 */
	@Column(name = "PAYENDYEARFLAG")
	public Integer getPayEndYearFlag() {
		return payEndYearFlag;
	}

	/**
	 * 属性 缴费年期年龄标志 的setter方法
	 * @param payEndYearFlag the payEndYearFlag to set
	 */
	public void setPayEndYearFlag(Integer payEndYearFlag) {
		this.payEndYearFlag = payEndYearFlag;
	}

	/**
	 * 属性 缴费年期年龄 的getter方法
	 * @return the payEndYear
	 */
	@Column(name = "PAYENDYEAR")
	public Integer getPayEndYear() {
		return payEndYear;
	}

	/**
	 * 属性 缴费年期年龄 的setter方法
	 * @param payEndYear the payEndYear to set
	 */
	public void setPayEndYear(Integer payEndYear) {
		this.payEndYear = payEndYear;
	}

	/**
	 * 属性 领取年龄年期标志 的getter方法
	 * @return the getYearFlag
	 */
	@Column(name = "GETYEARFLAG")
	public Integer getGetYearFlag() {
		return getYearFlag;
	}

	/**
	 * 属性 领取年龄年期标志 的setter方法
	 * @param getYearFlag the getYearFlag to set
	 */
	public void setGetYearFlag(Integer getYearFlag) {
		this.getYearFlag = getYearFlag;
	}

	/**
	 * 属性 领取年龄 的getter方法
	 * @return the getYear
	 */
	@Column(name = "GETYEAR")
	public Integer getGetYear() {
		return getYear;
	}

	/**
	 * 属性 领取年龄 的setter方法
	 * @param getYear the getYear to set
	 */
	public void setGetYear(Integer getYear) {
		this.getYear = getYear;
	}

	/**
	 * 属性 保险年期年龄标志 的getter方法
	 * @return the insuYearFlag
	 */
	@Column(name = "INSUYEARFLAG")
	public Integer getInsuYearFlag() {
		return insuYearFlag;
	}

	/**
	 * 属性 保险年期年龄标志 的setter方法
	 * @param insuYearFlag the insuYearFlag to set
	 */
	public void setInsuYearFlag(Integer insuYearFlag) {
		this.insuYearFlag = insuYearFlag;
	}

	/**
	 * 属性 保险年期年龄 的getter方法
	 * @return the insuYear
	 */
	@Column(name = "INSUYEAR")
	public Integer getInsuYear() {
		return insuYear;
	}

	/**
	 * 属性 保险年期年龄 的setter方法
	 * @param insuYear the insuYear to set
	 */
	public void setInsuYear(Integer insuYear) {
		this.insuYear = insuYear;
	}

	/**
	 * 属性 领取方式 的getter方法
	 * @return the getIntv
	 */
	@Column(name = "GETINTV")
	public Integer getGetIntv() {
		return getIntv;
	}

	/**
	 * 属性 领取方式 的setter方法
	 * @param getIntv the getIntv to set
	 */
	public void setGetIntv(Integer getIntv) {
		this.getIntv = getIntv;
	}

	/**
	 * 属性 领取银行编码 的getter方法
	 * @return the getBankCode
	 */
	@Column(name = "GETBANKCODE")
	public String getGetBankCode() {
		return getBankCode;
	}

	/**
	 * 属性 领取银行编码 的setter方法
	 * @param getBankCode the getBankCode to set
	 */
	public void setGetBankCode(String getBankCode) {
		this.getBankCode = getBankCode;
	}

	/**
	 * 属性 领取银行账户 的getter方法
	 * @return the getBankAccNo
	 */
	@Column(name = "GETBANKACCNO")
	public String getGetBankAccNo() {
		return getBankAccNo;
	}

	/**
	 * 属性 领取银行账户 的setter方法
	 * @param getBankAccNo the getBankAccNo to set
	 */
	public void setGetBankAccNo(String getBankAccNo) {
		this.getBankAccNo = getBankAccNo;
	}

	/**
	 * 属性 领取银行户名 的getter方法
	 * @return the getAccName
	 */
	@Column(name = "GETACCNAME")
	public String getGetAccName() {
		return getAccName;
	}

	/**
	 * 属性 领取银行户名 的setter方法
	 * @param getAccName the getAccName to set
	 */
	public void setGetAccName(String getAccName) {
		this.getAccName = getAccName;
	}

	/**
	 * 属性 领取银行省编码 的getter方法
	 * @return the getAccProvince
	 */
	@Column(name = "GETACCPROVINCE")
	public String getGetAccProvince() {
		return getAccProvince;
	}

	/**
	 * 属性 领取银行省编码 的setter方法
	 * @param getAccProvince the getAccProvince to set
	 */
	public void setGetAccProvince(String getAccProvince) {
		this.getAccProvince = getAccProvince;
	}

	/**
	 * 属性 领取银行市编码 的getter方法
	 * @return the getAccCity
	 */
	@Column(name = "GETACCCITY")
	public String getGetAccCity() {
		return getAccCity;
	}

	/**
	 * 属性 领取银行市编码 的setter方法
	 * @param getAccCity the getAccCity to set
	 */
	public void setGetAccCity(String getAccCity) {
		this.getAccCity = getAccCity;
	}

	/**
	 * 属性 领取银行卡折类型 的getter方法
	 * @return the getAccType
	 */
	@Column(name = "GETACCTYPE")
	public Integer getGetAccType() {
		return getAccType;
	}

	/**
	 * 属性 领取银行卡折类型 的setter方法
	 * @param getAccType the getAccType to set
	 */
	public void setGetAccType(Integer getAccType) {
		this.getAccType = getAccType;
	}

	/**
	 * 属性 垫交标志 的getter方法
	 * @return the autoPayFlag
	 */
	@Column(name = "AUTOPAYFLAG")
	public Integer getAutoPayFlag() {
		return autoPayFlag;
	}

	/**
	 * 属性 垫交标志 的setter方法
	 * @param autoPayFlag the autoPayFlag to set
	 */
	public void setAutoPayFlag(Integer autoPayFlag) {
		this.autoPayFlag = autoPayFlag;
	}

	/**
	 * 属性 红利分配标记 的getter方法
	 * @return the bonusPayMode
	 */
	@Column(name = "BONUSPAYMODE")
	public Integer getBonusPayMode() {
		return bonusPayMode;
	}

	/**
	 * 属性 红利分配标记 的setter方法
	 * @param bonusPayMode the bonusPayMode to set
	 */
	public void setBonusPayMode(Integer bonusPayMode) {
		this.bonusPayMode = bonusPayMode;
	}

	/**
	 * 属性 减额交清标志 的getter方法
	 * @return the subFlag
	 */
	@Column(name = "SUBFLAG")
	public Integer getSubFlag() {
		return subFlag;
	}

	/**
	 * 属性 减额交清标志 的setter方法
	 * @param subFlag the subFlag to set
	 */
	public void setSubFlag(Integer subFlag) {
		this.subFlag = subFlag;
	}

	/**
	 * 属性 红利领取方式 的getter方法
	 * @return the bonusGetMode
	 */
	@Column(name = "BONUSGETMODE")
	public Integer getBonusGetMode() {
		return bonusGetMode;
	}

	/**
	 * 属性 红利领取方式 的setter方法
	 * @param bonusGetMode the bonusGetMode to set
	 */
	public void setBonusGetMode(Integer bonusGetMode) {
		this.bonusGetMode = bonusGetMode;
	}

	/**
	 * 属性 自动续交标志 的getter方法
	 * @return the autoRNewFlag
	 */
	@Column(name = "AUTORNEWFLAG")
	public Integer getAutoRNewFlag() {
		return autoRNewFlag;
	}

	/**
	 * 属性 自动续交标志 的setter方法
	 * @param autoRNewFlag the autoRNewFlag to set
	 */
	public void setAutoRNewFlag(Integer autoRNewFlag) {
		this.autoRNewFlag = autoRNewFlag;
	}

	/**
	 * 属性 健康告之标志 的getter方法
	 * @return the healthFlag
	 */
	@Column(name = "HEALTHFLAG")
	public Integer getHealthFlag() {
		return healthFlag;
	}

	/**
	 * 属性 健康告之标志 的setter方法
	 * @param healthFlag the healthFlag to set
	 */
	public void setHealthFlag(Integer healthFlag) {
		this.healthFlag = healthFlag;
	}

	/**
	 * 属性 满期领取金领取方式 的getter方法
	 * @return the fullBonusGetMode
	 */
	@Column(name = "FULLBONUSGETMODE")
	public Integer getFullBonusGetMode() {
		return fullBonusGetMode;
	}

	/**
	 * 属性 满期领取金领取方式 的setter方法
	 * @param fullBonusGetMode the fullBonusGetMode to set
	 */
	public void setFullBonusGetMode(Integer fullBonusGetMode) {
		this.fullBonusGetMode = fullBonusGetMode;
	}

	/**
	 * 属性 初始费用率 的getter方法
	 * @return the firstRate
	 */
	@Column(name = "FIRSTRATE")
	public BigDecimal getFirstRate() {
		return firstRate;
	}

	/**
	 * 属性 初始费用率 的setter方法
	 * @param firstRate the firstRate to set
	 */
	public void setFirstRate(BigDecimal firstRate) {
		this.firstRate = firstRate;
	}

	/**
	 * 属性 保证利率 的getter方法
	 * @return the sureRate
	 */
	@Column(name = "SURERATE")
	public BigDecimal getSureRate() {
		return sureRate;
	}

	/**
	 * 属性 保证利率 的setter方法
	 * @param sureRate the sureRate to set
	 */
	public void setSureRate(BigDecimal sureRate) {
		this.sureRate = sureRate;
	}
	
}
