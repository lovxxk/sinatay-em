package cn.com.sinosoft.businessModule.policy.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
import javax.persistence.OneToOne;
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
 * POJO��InsuredLiability
 */
@Entity
@Table(name = "INSURANCEPOLICYLIABILITY")
public class InsurancePolicyLiability implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885224121908158768L;

	/** ������� */
	private String serialNo;

	/** ���Ա���/����ID */
	private String liabilityId;
	
	/** ���Ա���/���ִ��� */
	private String liabilityCode;
	
	/** ���Ժ��ı���/���ִ��� */
	private String coreCode;

	/** ���Ա���/�������� */
	private String liabilityName;
	
	/** ���Բ�Ʒ���� */
	private String productCode;

	/** ���Բ�Ʒ���� */
	private String productName;
	
	/** �����̼Ҳ�Ʒ���� */
	private String merchantProductCode;
	
	/** �����̼Ҳ�Ʒ���� */
	private String merchantProductName;
	
	/** �����̼����ִ��� */
	private String merchantLiabilityCode;
	
	/** �����̼����ִ��� */
	private String merchantLiabilityName;
	
	/** ���������ձ�־ */
	private Integer subRiskFlag;

	/** ��������Ⱥ�� */
	private Integer groupType;

	/** ���Ա���/����˳�� */
	private Integer liabilityOrder;
	
	/** �����ܱ��� */
	private BigDecimal grossInsuredAmount = new BigDecimal(0);

	/** �����ܱ��� */
	private BigDecimal grossPremium = new BigDecimal(0);
	
	/** ���Ա��� */
	private BigDecimal insuredAmount = new BigDecimal(0);

	/** ���Ա��� */
	private BigDecimal premium = new BigDecimal(0);
	
	/** �����ۿ۱��� */
	private BigDecimal discountPremium;
	
	/** �����ۿ۴��� */
	private String discountTypeCode;

	/** �����ۿ�ϵ�� */
	private BigDecimal discountRate;
	
	/** ���Ա������� */
	private Integer benefitPeriod;

	/** ���Ա����������� */
	private Integer benefitPeriodType;
	
	/** ���Ա�������  */
	private Date insuranceStartPeriod;
	
	/** ���Ա���ֹ�� */
	private Date insuranceEndPeriod;
	
	/** ���Խɷѷ�ʽ */
	private Integer paymentMode;

	/** ���Խɷ����� */
	private Integer paymentDuration;

	/** ���Խɷ��������� */
	private Integer paymentDurationMode;

	/** ��������� */
	private BigDecimal deductibles;

	/** �����⸶���� */
	private BigDecimal lossRatio;
	
	/** ���Ա���/���� */
	private InsurancePolicy insurancePolicy;
	
	/** ���Ա���/���� */
	private InsurancePolicyLiability insurancePolicyLiability;
	
	/** ���� �������� */
	private List<InsurancePolicyLiability> insurancePolicyLiabilities = new ArrayList<InsurancePolicyLiability>(0);
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/** ���� �������ִ��� */
	private String mainRiskCode;

	/** ���� �������� */
	private Integer riskType;

	/** ���� ���� */
	private BigDecimal Rate;

	/** ���� ��Ч�� */
	private Date inceptionDate;

	/** ���� ���� */
	private String rank;

	/** ���� ���� */
	private Integer unitCount = 1;

	/** ���� �ۿ��� */
	private String costIntv;

	/** ���� �ۿ�ʱ�� */
	private Date costDate;

	/** ���� �ر�Լ�� */
	private String specContent;

	/** ���� �ɷ����������־ */
	private Integer payEndYearFlag;

	/** ���� �ɷ��������� */
	private Integer payEndYear;

	/** ���� ��ȡ�������ڱ�־ */
	private Integer getYearFlag;

	/** ���� ��ȡ���� */
	private Integer getYear;

	/** ���� �������������־ */
	private Integer insuYearFlag;

	/** ���� ������������ */
	private Integer insuYear;

	/** ���� ��ȡ��ʽ */
	private Integer getIntv;

	/** ���� ��ȡ���б��� */
	private String getBankCode;

	/** ���� ��ȡ�����˻� */
	private String getBankAccNo;

	/** ���� ��ȡ���л��� */
	private String getAccName;

	/** ���� ��ȡ����ʡ���� */
	private String getAccProvince;

	/** ���� ��ȡ�����б��� */
	private String getAccCity;

	/** ���� ��ȡ���п������� */
	private Integer getAccType;

	/** ���� �潻��־ */
	private Integer autoPayFlag;

	/** ���� ���������� */
	private Integer bonusPayMode;

	/** ���� ������־ */
	private Integer subFlag;

	/** ���� ������ȡ��ʽ */
	private Integer bonusGetMode;

	/** ���� �Զ�������־ */
	private Integer autoRNewFlag;

	/** ���� ������֮��־ */
	private Integer healthFlag;

	/** ���� ������ȡ����ȡ��ʽ */
	private Integer fullBonusGetMode;

	/** ���� ��ʼ������ */
	private BigDecimal firstRate;

	/** ���� ��֤���� */
	private BigDecimal sureRate;
	
	/**
	 * ��InsuranceLiability��Ĭ�Ϲ��췽��
	 */
	public InsurancePolicyLiability() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ���Ա���/����ID��getter����
	 */
	@Column(name = "LIABILITYID")
	public String getLiabilityId() {
		return liabilityId;
	}
	
	/**
	 * ���Ա���/����ID��setter����
	 */
	public void setLiabilityId(String liabilityId) {
		this.liabilityId = liabilityId;
	}
	
	/**
	 * ���Ա���/���ִ����getter����
	 */
	@Column(name = "LIABILITYCODE")
	public String getLiabilityCode() {
		return liabilityCode;
	}
	
	/**
	 * ���Ա���/���ִ����setter����
	 */
	public void setLiabilityCode(String liabilityCode) {
		this.liabilityCode = liabilityCode;
	}

	/**
	 * ���Ժ��ı���/���ִ����getter����
	 */

	@Column(name = "CORECODE")
	public String getCoreCode() {
		return this.coreCode;
	}

	/**
	 * ���Ժ��ı���/���ִ����setter����
	 */
	public void setCoreCode(String coreCode) {
		this.coreCode = coreCode;
	}

	/**
	 * ���Ա���/�������Ƶ�getter����
	 */

	@Column(name = "LIABILITYNAME")
	public String getLiabilityName() {
		return this.liabilityName;
	}

	/**
	 * ���Ա���/�������Ƶ�setter����
	 */
	public void setLiabilityName(String liabilityName) {
		this.liabilityName = liabilityName;
	}

	/**
	 * ���Բ�Ʒ�����getter����
	 */

	@Column(name = "PRODUCTCODE")
	public String getProductCode() {
		return this.productCode;
	}

	/**
	 * ���Բ�Ʒ�����setter����
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * ���Բ�Ʒ���Ƶ�getter����
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}

	/**
	 * ���Բ�Ʒ���Ƶ�setter����
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * �����̼Ҳ�Ʒ�����getter����
	 */
	@Column(name = "MERCHANTPRODUCTCODE")
	public String getMerchantProductCode() {
		return merchantProductCode;
	}

	/**
	 * �����̼Ҳ�Ʒ�����setter����
	 */
	public void setMerchantProductCode(String merchantProductCode) {
		this.merchantProductCode = merchantProductCode;
	}

	/**
	 * �����̼Ҳ�Ʒ���Ƶ�setter����
	 */
	@Column(name = "MERCHANTPRODUCTNAME")
	public String getMerchantProductName() {
		return merchantProductName;
	}

	/**
	 * �����̼Ҳ�Ʒ���Ƶ�setter����
	 */
	public void setMerchantProductName(String merchantProductName) {
		this.merchantProductName = merchantProductName;
	}
	
	
	/**
	 * �����̼����ִ����getter����
	 */
	@Column(name = "MERCHANTLIABILITYCODE")
	public String getMerchantLiabilityCode() {
		return merchantLiabilityCode;
	}

	/**
	 * �����̼����ִ����setter����
	 */
	public void setMerchantLiabilityCode(String merchantLiabilityCode) {
		this.merchantLiabilityCode = merchantLiabilityCode;
	}
	
	/**
	 * �����̼��������Ƶ�getter����
	 */
	@Column(name = "MERCHANTLIABILITYNAME")
	public String getMerchantLiabilityName() {
		return merchantLiabilityName;
	}

	/**
	 * �����̼��������Ƶ�setter����
	 */
	public void setMerchantLiabilityName(String merchantLiabilityName) {
		this.merchantLiabilityName = merchantLiabilityName;
	}

	/**
	 * ���������ձ�־��getter����
	 */

	@Column(name = "SUBRISKFLAG")
	public Integer getSubRiskFlag() {
		return this.subRiskFlag;
	}
	
	/**
	 * ���������ձ�־ö�����getter����
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
	 * ���������ձ�־����ֵ��getter����
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
	 * ���������ձ�־�̼�ֵ��getter����
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
	 * ���������ձ�־��setter����
	 */
	public void setSubRiskFlag(Integer subRiskFlag) {
		this.subRiskFlag = subRiskFlag;
	}
	
	/**
	 * ���������ձ�־��ֵ
	 */
	public void setEnumSubRiskFlag(SubRiskFlag  subRiskFlag) {
		if (subRiskFlag != null) {
			this.subRiskFlag = subRiskFlag.getValue();
		}
	}
	
	/**
	 * ���Ժ��������ձ�־��ֵ
	 */
	public void setSubRiskFlagByCoreValue(String coreValue) {
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByCoreValue(SubRiskFlag.class, coreValue);
		if (subRiskFlag != null) {
			this.subRiskFlag = subRiskFlag.getValue();
		}
	}
	
	/**
	 * �����̼������ձ�־��ֵ
	 */
	public void setSubRiskFlagByMerchantValue(String merchantValue) {
		SubRiskFlag  subRiskFlag = (SubRiskFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(SubRiskFlag.class, merchantValue);
		if (subRiskFlag != null) {
			this.subRiskFlag = subRiskFlag.getValue();
		}
	}
	
	
	/**
	 * ����Ⱥ�����͵�getter����
	 */

	@Column(name = "GROUPTYPE")
	public Integer getGroupType() {
		return groupType;
	}
	
	/**
	 * ��������Ⱥ��ö�����getter����
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
	 * ��������Ⱥ�����ֵ��getter����
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
	 * ��������Ⱥ���̼�ֵ��getter����
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
	 * ����Ⱥ�����͵�getter����
	 */

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	
	/**
	 * ��������Ⱥ�帳ֵ
	 */
	public void setEnumGroupType(GroupType  groupType) {
		if (groupType != null) {
			this.groupType = groupType.getValue();
		}
	}
	
	/**
	 * ���Ժ�������Ⱥ�帳ֵ
	 */
	public void setGroupTypeByCoreValue(String coreValue) {
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByCoreValue(GroupType.class, coreValue);
		if (groupType != null) {
			this.groupType = groupType.getValue();
		}
	}
	
	/**
	 * �����̼�����Ⱥ�帳ֵ
	 */
	public void setGroupTypeByMerchantValue(String merchantValue) {
		GroupType  groupType = (GroupType) EnumDataUtils.getEnumDictionaryByMerchantValue(GroupType.class, merchantValue);
		if (groupType != null) {
			this.groupType = groupType.getValue();
		}
	}
	
	/**
	 * ���Ա���/����˳���getter����
	 */
	@Column(name = "LIABILITYORDER")
	public Integer getLiabilityOrder() {
		return liabilityOrder;
	}
	
	/**
	 * ���Ա���/����˳���setter����
	 */
	public void setLiabilityOrder(Integer liabilityOrder) {
		this.liabilityOrder = liabilityOrder;
	}

	/**
	 * ���Ա����getter����
	 */

	@Column(name = "INSUREDAMOUNT")
	public BigDecimal getInsuredAmount() {
		return this.insuredAmount;
	}

	/**
	 * ���Ա����setter����
	 */
	public void setInsuredAmount(BigDecimal insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	/**
	 * ���Ա��ѵ�getter����
	 */

	@Column(name = "PREMIUM")
	public BigDecimal getPremium() {
		return this.premium;
	}

	/**
	 * ���Ա��ѵ�setter����
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	/**
	 * �����ۿ۱��ѵ�getter����
	 */
	@Column(name = "DISCOUNTPREMIUM")
	public BigDecimal getDiscountPremium() {
		return discountPremium;
	}
	
	/**
	 * �����ۿ۱��ѵ�setter����
	 */
	public void setDiscountPremium(BigDecimal discountPremium) {
		this.discountPremium = discountPremium;
	}

	/**
	 * �����ۿ۴����getter����
	 */

	@Column(name = "DISCOUNTTYPECODE")
	public String getDiscountTypeCode() {
		return this.discountTypeCode;
	}

	/**
	 * �����ۿ۴����setter����
	 */
	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	/**
	 * �����ۿ�ϵ����getter����
	 */

	@Column(name = "DISCOUNTRATE")
	public BigDecimal getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * �����ۿ�ϵ����setter����
	 */
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	
	/**
	 * ���Ա������ڵ�getter����
	 */

	@Column(name = "BENEFITPERIOD")
	public Integer getBenefitPeriod() {
		return this.benefitPeriod;
	}

	/**
	 * ���Ա������ڵ�setter����
	 */
	public void setBenefitPeriod(Integer benefitPeriod) {
		this.benefitPeriod = benefitPeriod;
	}

	/**
	 * ���Ա����������͵�getter����
	 */

	@Column(name = "BENEFITPERIODTYPE")
	public Integer getBenefitPeriodType() {
		return this.benefitPeriodType;
	}
	
	/**
	 * ���Ա�����������ö�����getter����
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
	 * ���Ա����������ͺ���ֵ��getter����
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
	 * ���Ա������������̼�ֵ��getter����
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
	 * ���Ա����������͵�setter����
	 */
	public void setBenefitPeriodType(Integer benefitPeriodType) {
		this.benefitPeriodType = benefitPeriodType;
	}
	
	/**
	 * ���Ա����������͸�ֵ
	 */
	public void setEnumBenefitPeriodType(BenefitPeriodType  benefitPeriodType) {
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * ���Ժ��ı����������͸�ֵ
	 */
	public void setBenefitPeriodTypeByCoreValue(String coreValue) {
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByCoreValue(BenefitPeriodType.class, coreValue);
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * �����̼ұ����������͸�ֵ
	 */
	public void setBenefitPeriodTypeByMerchantValue(String merchantValue) {
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByMerchantValue(BenefitPeriodType.class, merchantValue);
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	
	/**
	 * ���Ա������ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCESTARTPERIOD")
	public Date getInsuranceStartPeriod() {
		return insuranceStartPeriod;
	}
	
	/**
	 * ���Ա������ڵ�setter����
	 */
	public void setInsuranceStartPeriod(Date insuranceStartPeriod) {
		this.insuranceStartPeriod = insuranceStartPeriod;
	}
	
	/**
	 * ���Ա���ֹ�ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCEENDPERIOD")
	public Date getInsuranceEndPeriod() {
		return insuranceEndPeriod;
	}
	
	/**
	 * ���Ա���ֹ�ڵ�setter����
	 */
	public void setInsuranceEndPeriod(Date insuranceEndPeriod) {
		this.insuranceEndPeriod = insuranceEndPeriod;
	}
	
	/**
	 * ���Խɷѷ�ʽ��getter����
	 */
	@Column(name = "PAYMENTMODE")
	public Integer getPaymentMode() {
		return this.paymentMode;
	}
	
	/**
	 * ���Խɷѷ�ʽö�����getter����
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
	 * ���Խɷѷ�ʽ����ֵ��getter����
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
	 * ���Խɷѷ�ʽ�̼�ֵ��getter����
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
	 * ���Խɷѷ�ʽ��setter����
	 */
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * ���Խɷѷ�ʽ��ֵ
	 */
	public void setEnumPaymentMode(PaymentMode  paymentMode) {
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	
	/**
	 * ���Ժ��Ľɷѷ�ʽ��ֵ
	 */
	public void setPaymentModeByCoreValue(String coreValue) {
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentMode.class, coreValue);
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	
	/**
	 * �����̼ҽɷѷ�ʽ��ֵ
	 */
	public void setPaymentModeByMerchantValue(String merchantValue) {
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentMode.class, merchantValue);
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	
	
	/**
	 * ���Խɷ����ڵ�getter����
	 */

	@Column(name = "PAYMENTDURATION")
	public Integer getPaymentDuration() {
		return this.paymentDuration;
	}

	/**
	 * ���Խɷ����ڵ�setter����
	 */
	public void setPaymentDuration(Integer paymentDuration) {
		this.paymentDuration = paymentDuration;
	}

	/**
	 * ���Խɷ��������͵�getter����
	 */

	@Column(name = "PAYMENTDURATIONMODE")
	public Integer getPaymentDurationMode() {
		return this.paymentDurationMode;
	}
	
	/**
	 * ���Խɷ���������ö�����getter����
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
	 * ���Խɷ��������ͺ���ֵ��getter����
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
	 * ���Խɷ����������̼�ֵ��getter����
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
	 * ���Խɷ��������͵�setter����
	 */
	public void setPaymentDurationMode(Integer paymentDurationMode) {
		this.paymentDurationMode = paymentDurationMode;
	}

	/**
	 * ���Խɷ��������͸�ֵ
	 */
	public void setEnumPaymentDurationMode(PaymentDurationMode  paymentDurationMode) {
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * ���Ժ��Ľɷ��������͸�ֵ
	 */
	public void setPaymentDurationModeByCoreValue(String coreValue) {
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentDurationMode.class, coreValue);
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * �����̼ҽɷ��������͸�ֵ
	 */
	public void setPaymentDurationModeByMerchantValue(String merchantValue) {
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentDurationMode.class, merchantValue);
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * ����������getter����
	 */

	@Column(name = "DEDUCTIBLES")
	public BigDecimal getDeductibles() {
		return this.deductibles;
	}

	/**
	 * ����������setter����
	 */
	public void setDeductibles(BigDecimal deductibles) {
		this.deductibles = deductibles;
	}

	/**
	 * �����⸶���ʵ�getter����
	 */

	@Column(name = "LOSSRATIO")
	public BigDecimal getLossRatio() {
		return this.lossRatio;
	}

	/**
	 * �����⸶���ʵ�setter����
	 */
	public void setLossRatio(BigDecimal lossRatio) {
		this.lossRatio = lossRatio;
	}

	/**
	 * ���Ա�����getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POLICYSERIALNO")
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * ���Ա�����setter����
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	/**
	 * �������ӱ���ʱͬʱ������/���ָ�ֵ������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getInsurancePolicyLiabilities().contains(this)) {
			getInsurancePolicy().getInsurancePolicyLiabilities().add(this);
		}
	}
	/**
	 * ���Ա���/���ε�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "PARENTSERIALNO")
	public InsurancePolicyLiability getInsurancePolicyLiability() {
		return insurancePolicyLiability;
	}

	public void setInsurancePolicyLiability(
			InsurancePolicyLiability insurancePolicyLiability) {
		this.insurancePolicyLiability = insurancePolicyLiability;
	}
	
	public void addInsurancePolicyLiability(
			InsurancePolicyLiability insurancePolicyLiability) {
		this.insurancePolicyLiability = insurancePolicyLiability;
		if (getInsurancePolicyLiability() != null && !getInsurancePolicyLiability().getInsurancePolicyLiabilities().contains(this)) {
			getInsurancePolicyLiability().getInsurancePolicyLiabilities().add(this);
		}
	}
	
	/**
	 * ���Ա������ε�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicyLiability")
	public List<InsurancePolicyLiability> getInsurancePolicyLiabilities() {
		return insurancePolicyLiabilities;
	}
	
	/**
	 * ���Ա������ε�setter����
	 */
	public void setInsurancePolicyLiabilities(
			List<InsurancePolicyLiability> insurancePolicyLiabilities) {
		this.insurancePolicyLiabilities = insurancePolicyLiabilities;
	}
	
	/**
	 * �����������б�������
	 */
	public void addAllInsurancePolicyLiabilities(
			List<InsurancePolicyLiability> insurancePolicyLiabilities) {
		for (InsurancePolicyLiability insurancePolicyLiability:insurancePolicyLiabilities) {
			if (!getInsurancePolicyLiabilities().contains(insurancePolicyLiability)) {
				getInsurancePolicyLiabilities().add(insurancePolicyLiability);
			}
		}
		
		for (InsurancePolicyLiability insurancePolicyLiability:getInsurancePolicyLiabilities()) {
			if (insurancePolicyLiability.getInsurancePolicyLiability() == null) {
				insurancePolicyLiability.setInsurancePolicyLiability(this);
			}
			
		}
		
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * ���� �������ִ��� ��getter����
	 * @return the mainRiskCode
	 */
	@Column(name = "MAINRISKCODE")
	public String getMainRiskCode() {
		return mainRiskCode;
	}

	/**
	 * ���� �������ִ��� ��setter����
	 * @param mainRiskCode the mainRiskCode to set
	 */
	public void setMainRiskCode(String mainRiskCode) {
		this.mainRiskCode = mainRiskCode;
	}

	/**
	 * ���� �������� ��getter����
	 * @return the riskType
	 */
	@Column(name = "RISKTYPE")
	public Integer getRiskType() {
		return riskType;
	}

	/**
	 * ���� �������� ��setter����
	 * @param riskType the riskType to set
	 */
	public void setRiskType(Integer riskType) {
		this.riskType = riskType;
	}

	/**
	 * ���� ���� ��getter����
	 * @return the rate
	 */
	@Column(name = "RATE")
	public BigDecimal getRate() {
		return Rate;
	}

	/**
	 * ���� ���� ��setter����
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		Rate = rate;
	}

	/**
	 * ���� ��Ч�� ��getter����
	 * @return the inceptionDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INCEPTIONDATE")
	public Date getInceptionDate() {
		return inceptionDate;
	}

	/**
	 * ���� ��Ч�� ��setter����
	 * @param inceptionDate the inceptionDate to set
	 */
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	/**
	 * ���� ���� ��getter����
	 * @return the rank
	 */
	@Column(name = "RANK")
	public String getRank() {
		return rank;
	}

	/**
	 * ���� ���� ��setter����
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * ���� ���� ��getter����
	 * @return the unitCount
	 */
	@Column(name = "UNITCOUNT")
	public Integer getUnitCount() {
		return unitCount;
	}

	/**
	 * ���� ���� ��setter����
	 * @param unitCount the unitCount to set
	 */
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}

	/**
	 * ���� �ۿ��� ��getter����
	 * @return the costIntv
	 */
	@Column(name = "COSTINTV")
	public String getCostIntv() {
		return costIntv;
	}

	/**
	 * ���� �ۿ��� ��setter����
	 * @param costIntv the costIntv to set
	 */
	public void setCostIntv(String costIntv) {
		this.costIntv = costIntv;
	}

	/**
	 * ���� �ۿ�ʱ�� ��getter����
	 * @return the costDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COSTDATE")
	public Date getCostDate() {
		return costDate;
	}

	/**
	 * ���� �ۿ�ʱ�� ��setter����
	 * @param costDate the costDate to set
	 */
	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	/**
	 * ���� �ر�Լ�� ��getter����
	 * @return the specContent
	 */
	@Column(name = "SPECCONTENT")
	public String getSpecContent() {
		return specContent;
	}

	/**
	 * ���� �ر�Լ�� ��setter����
	 * @param specContent the specContent to set
	 */
	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	/**
	 * ���� �ɷ����������־ ��getter����
	 * @return the payEndYearFlag
	 */
	@Column(name = "PAYENDYEARFLAG")
	public Integer getPayEndYearFlag() {
		return payEndYearFlag;
	}

	/**
	 * ���� �ɷ����������־ ��setter����
	 * @param payEndYearFlag the payEndYearFlag to set
	 */
	public void setPayEndYearFlag(Integer payEndYearFlag) {
		this.payEndYearFlag = payEndYearFlag;
	}

	/**
	 * ���� �ɷ��������� ��getter����
	 * @return the payEndYear
	 */
	@Column(name = "PAYENDYEAR")
	public Integer getPayEndYear() {
		return payEndYear;
	}

	/**
	 * ���� �ɷ��������� ��setter����
	 * @param payEndYear the payEndYear to set
	 */
	public void setPayEndYear(Integer payEndYear) {
		this.payEndYear = payEndYear;
	}

	/**
	 * ���� ��ȡ�������ڱ�־ ��getter����
	 * @return the getYearFlag
	 */
	@Column(name = "GETYEARFLAG")
	public Integer getGetYearFlag() {
		return getYearFlag;
	}

	/**
	 * ���� ��ȡ�������ڱ�־ ��setter����
	 * @param getYearFlag the getYearFlag to set
	 */
	public void setGetYearFlag(Integer getYearFlag) {
		this.getYearFlag = getYearFlag;
	}

	/**
	 * ���� ��ȡ���� ��getter����
	 * @return the getYear
	 */
	@Column(name = "GETYEAR")
	public Integer getGetYear() {
		return getYear;
	}

	/**
	 * ���� ��ȡ���� ��setter����
	 * @param getYear the getYear to set
	 */
	public void setGetYear(Integer getYear) {
		this.getYear = getYear;
	}

	/**
	 * ���� �������������־ ��getter����
	 * @return the insuYearFlag
	 */
	@Column(name = "INSUYEARFLAG")
	public Integer getInsuYearFlag() {
		return insuYearFlag;
	}

	/**
	 * ���� �������������־ ��setter����
	 * @param insuYearFlag the insuYearFlag to set
	 */
	public void setInsuYearFlag(Integer insuYearFlag) {
		this.insuYearFlag = insuYearFlag;
	}

	/**
	 * ���� ������������ ��getter����
	 * @return the insuYear
	 */
	@Column(name = "INSUYEAR")
	public Integer getInsuYear() {
		return insuYear;
	}

	/**
	 * ���� ������������ ��setter����
	 * @param insuYear the insuYear to set
	 */
	public void setInsuYear(Integer insuYear) {
		this.insuYear = insuYear;
	}

	/**
	 * ���� ��ȡ��ʽ ��getter����
	 * @return the getIntv
	 */
	@Column(name = "GETINTV")
	public Integer getGetIntv() {
		return getIntv;
	}

	/**
	 * ���� ��ȡ��ʽ ��setter����
	 * @param getIntv the getIntv to set
	 */
	public void setGetIntv(Integer getIntv) {
		this.getIntv = getIntv;
	}

	/**
	 * ���� ��ȡ���б��� ��getter����
	 * @return the getBankCode
	 */
	@Column(name = "GETBANKCODE")
	public String getGetBankCode() {
		return getBankCode;
	}

	/**
	 * ���� ��ȡ���б��� ��setter����
	 * @param getBankCode the getBankCode to set
	 */
	public void setGetBankCode(String getBankCode) {
		this.getBankCode = getBankCode;
	}

	/**
	 * ���� ��ȡ�����˻� ��getter����
	 * @return the getBankAccNo
	 */
	@Column(name = "GETBANKACCNO")
	public String getGetBankAccNo() {
		return getBankAccNo;
	}

	/**
	 * ���� ��ȡ�����˻� ��setter����
	 * @param getBankAccNo the getBankAccNo to set
	 */
	public void setGetBankAccNo(String getBankAccNo) {
		this.getBankAccNo = getBankAccNo;
	}

	/**
	 * ���� ��ȡ���л��� ��getter����
	 * @return the getAccName
	 */
	@Column(name = "GETACCNAME")
	public String getGetAccName() {
		return getAccName;
	}

	/**
	 * ���� ��ȡ���л��� ��setter����
	 * @param getAccName the getAccName to set
	 */
	public void setGetAccName(String getAccName) {
		this.getAccName = getAccName;
	}

	/**
	 * ���� ��ȡ����ʡ���� ��getter����
	 * @return the getAccProvince
	 */
	@Column(name = "GETACCPROVINCE")
	public String getGetAccProvince() {
		return getAccProvince;
	}

	/**
	 * ���� ��ȡ����ʡ���� ��setter����
	 * @param getAccProvince the getAccProvince to set
	 */
	public void setGetAccProvince(String getAccProvince) {
		this.getAccProvince = getAccProvince;
	}

	/**
	 * ���� ��ȡ�����б��� ��getter����
	 * @return the getAccCity
	 */
	@Column(name = "GETACCCITY")
	public String getGetAccCity() {
		return getAccCity;
	}

	/**
	 * ���� ��ȡ�����б��� ��setter����
	 * @param getAccCity the getAccCity to set
	 */
	public void setGetAccCity(String getAccCity) {
		this.getAccCity = getAccCity;
	}

	/**
	 * ���� ��ȡ���п������� ��getter����
	 * @return the getAccType
	 */
	@Column(name = "GETACCTYPE")
	public Integer getGetAccType() {
		return getAccType;
	}

	/**
	 * ���� ��ȡ���п������� ��setter����
	 * @param getAccType the getAccType to set
	 */
	public void setGetAccType(Integer getAccType) {
		this.getAccType = getAccType;
	}

	/**
	 * ���� �潻��־ ��getter����
	 * @return the autoPayFlag
	 */
	@Column(name = "AUTOPAYFLAG")
	public Integer getAutoPayFlag() {
		return autoPayFlag;
	}

	/**
	 * ���� �潻��־ ��setter����
	 * @param autoPayFlag the autoPayFlag to set
	 */
	public void setAutoPayFlag(Integer autoPayFlag) {
		this.autoPayFlag = autoPayFlag;
	}

	/**
	 * ���� ���������� ��getter����
	 * @return the bonusPayMode
	 */
	@Column(name = "BONUSPAYMODE")
	public Integer getBonusPayMode() {
		return bonusPayMode;
	}

	/**
	 * ���� ���������� ��setter����
	 * @param bonusPayMode the bonusPayMode to set
	 */
	public void setBonusPayMode(Integer bonusPayMode) {
		this.bonusPayMode = bonusPayMode;
	}

	/**
	 * ���� ������־ ��getter����
	 * @return the subFlag
	 */
	@Column(name = "SUBFLAG")
	public Integer getSubFlag() {
		return subFlag;
	}

	/**
	 * ���� ������־ ��setter����
	 * @param subFlag the subFlag to set
	 */
	public void setSubFlag(Integer subFlag) {
		this.subFlag = subFlag;
	}

	/**
	 * ���� ������ȡ��ʽ ��getter����
	 * @return the bonusGetMode
	 */
	@Column(name = "BONUSGETMODE")
	public Integer getBonusGetMode() {
		return bonusGetMode;
	}

	/**
	 * ���� ������ȡ��ʽ ��setter����
	 * @param bonusGetMode the bonusGetMode to set
	 */
	public void setBonusGetMode(Integer bonusGetMode) {
		this.bonusGetMode = bonusGetMode;
	}

	/**
	 * ���� �Զ�������־ ��getter����
	 * @return the autoRNewFlag
	 */
	@Column(name = "AUTORNEWFLAG")
	public Integer getAutoRNewFlag() {
		return autoRNewFlag;
	}

	/**
	 * ���� �Զ�������־ ��setter����
	 * @param autoRNewFlag the autoRNewFlag to set
	 */
	public void setAutoRNewFlag(Integer autoRNewFlag) {
		this.autoRNewFlag = autoRNewFlag;
	}

	/**
	 * ���� ������֮��־ ��getter����
	 * @return the healthFlag
	 */
	@Column(name = "HEALTHFLAG")
	public Integer getHealthFlag() {
		return healthFlag;
	}

	/**
	 * ���� ������֮��־ ��setter����
	 * @param healthFlag the healthFlag to set
	 */
	public void setHealthFlag(Integer healthFlag) {
		this.healthFlag = healthFlag;
	}

	/**
	 * ���� ������ȡ����ȡ��ʽ ��getter����
	 * @return the fullBonusGetMode
	 */
	@Column(name = "FULLBONUSGETMODE")
	public Integer getFullBonusGetMode() {
		return fullBonusGetMode;
	}

	/**
	 * ���� ������ȡ����ȡ��ʽ ��setter����
	 * @param fullBonusGetMode the fullBonusGetMode to set
	 */
	public void setFullBonusGetMode(Integer fullBonusGetMode) {
		this.fullBonusGetMode = fullBonusGetMode;
	}

	/**
	 * ���� ��ʼ������ ��getter����
	 * @return the firstRate
	 */
	@Column(name = "FIRSTRATE")
	public BigDecimal getFirstRate() {
		return firstRate;
	}

	/**
	 * ���� ��ʼ������ ��setter����
	 * @param firstRate the firstRate to set
	 */
	public void setFirstRate(BigDecimal firstRate) {
		this.firstRate = firstRate;
	}

	/**
	 * ���� ��֤���� ��getter����
	 * @return the sureRate
	 */
	@Column(name = "SURERATE")
	public BigDecimal getSureRate() {
		return sureRate;
	}

	/**
	 * ���� ��֤���� ��setter����
	 * @param sureRate the sureRate to set
	 */
	public void setSureRate(BigDecimal sureRate) {
		this.sureRate = sureRate;
	}

	@Column(name = "GROSSINSUREDAMOUNT")
	public BigDecimal getGrossInsuredAmount() {
		return grossInsuredAmount;
	}

	public void setGrossInsuredAmount(BigDecimal grossInsuredAmount) {
		this.grossInsuredAmount = grossInsuredAmount;
	}

	@Column(name = "GROSSPREMIUM")
	public BigDecimal getGrossPremium() {
		return grossPremium;
	}

	public void setGrossPremium(BigDecimal grossPremium) {
		this.grossPremium = grossPremium;
	}
	
	
}