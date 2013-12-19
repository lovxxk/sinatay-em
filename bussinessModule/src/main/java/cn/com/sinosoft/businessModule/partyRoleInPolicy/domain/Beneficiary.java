package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;



import cn.com.sinosoft.businessModule.enums.dictionary.BeneficiaryType;
import cn.com.sinosoft.businessModule.enums.dictionary.RelatedToRoleCode;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类Beneficiary
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Beneficiary")
public class Beneficiary extends PartyRoleInPolicy {
	private static final long serialVersionUID = 1L;

	
	/** 属性受益人类型 */
	private Integer beneficiaryType;

	/** 属性与被保险人关系 */
	private Integer relatedToInsured;
	
	/** 属性受益人顺序 */
	private Integer beneficiaryOrder;

	/** 属性受益份额 */
	private BigDecimal interestPercent;

	/** 属性被保险人 */
	private Insured insured;

	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/**
	 * 类Beneficiary的默认构造方法
	 */
	public Beneficiary() {
	}

	/**
	 * 属性受益人类型的getter方法
	 */
	@Column(name = "BENEFICIARYTYPE")
	public Integer getBeneficiaryType() {
		return beneficiaryType;
	}

	/**
	 * 属性受益人类型核心值的getter方法
	 */
	@Transient
	public BeneficiaryType getEnumBeneficiaryType() {
		if (getBeneficiaryType() == null) {
			return null;
		}
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryType.class, getBeneficiaryType());
		return beneficiaryType;
	}
	
	/**
	 * 属性受益人类型核心值的getter方法
	 */
	@Transient
	public String getBeneficiaryTypeByCoreValue() {
		if (getBeneficiaryType() == null) {
			return "";
		}
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryType.class, getBeneficiaryType());
		return beneficiaryType.getCoreValue();
	}
	
	/**
	 * 属性受益人类型商家值的getter方法
	 */
	@Transient
	public String getBeneficiaryTypeByMerchantValue() {
		if (getBeneficiaryType() == null) {
			return "";
		}
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryType.class, getBeneficiaryType());
		return beneficiaryType.getMerchantValue();
	}
	
	/**
	 * 属性受益人类型的setter方法
	 */
	public void setBeneficiaryType(Integer beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}
	
	/**
	 * 属性受益人类型赋值
	 */
	public void setEnumBeneficiaryType(BeneficiaryType  beneficiaryType) {
		if (beneficiaryType != null) {
			this.beneficiaryType = beneficiaryType.getValue();
		}
	}
	
	/**
	 * 属性核心受益人类型赋值
	 */
	public void setBeneficiaryTypeByCoreValue(String coreValue) {
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByCoreValue(BeneficiaryType.class, coreValue);
		if (beneficiaryType != null) {
			this.beneficiaryType = beneficiaryType.getValue();
		}
	}
	
	/**
	 * 属性商家受益人类型赋值
	 */
	public void setBeneficiaryTypeByMerchantValue(String merchantValue) {
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByMerchantValue(BeneficiaryType.class, merchantValue);
		if (beneficiaryType != null) {
			this.beneficiaryType = beneficiaryType.getValue();
		}
	}
	
	/**
	 * 属性与被保险人关系的getter方法
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * 属性与被保险人关系核心值的getter方法
	 */
	@Transient
	public RelatedToRoleCode getEnumRelatedToInsured() {
		if (getRelatedToInsured() == null) {
			return null;
		}
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToInsured());
		return relatedToInsured;
	}
	
	/**
	 * 属性与被保险人关系核心值的getter方法
	 */
	@Transient
	public String getRelatedToInsuredByCoreValue() {
		if (getRelatedToInsured() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToInsured());
		return relatedToInsured.getCoreValue();
	}
	
	/**
	 * 属性与被保险人关系商家值的getter方法
	 */
	@Transient
	public String getRelatedToInsuredByMerchantValue() {
		if (getRelatedToInsured() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToInsured());
		return relatedToInsured.getMerchantValue();
	}
	
	
	/**
	 * 属性与主被保险人关系的setter方法
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}

	/**
	 * 属性与被保险人关系赋值
	 */
	public void setEnumRelatedToInsured(RelatedToRoleCode  relatedToInsured) {
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * 属性核心与被保险人关系赋值
	 */
	public void setRelatedToInsuredByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
		
	}
	
	/**
	 * 属性商家与被保险人关系赋值
	 */
	public void setRelatedToInsuredByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * 属性受益人顺序的getter方法
	 */
	@Column(name = "ROLEORDER")
	public Integer getBeneficiaryOrder() {
		return beneficiaryOrder;
	}

	/**
	 * 属性受益人顺序的setter方法
	 */
	public void setBeneficiaryOrder(Integer beneficiaryOrder) {
		this.beneficiaryOrder = beneficiaryOrder;
	}

	/**
	 * 属性受益份额的getter方法
	 */

	@Column(name = "INTERESTPERCENT")
	public BigDecimal getInterestPercent() {
		return this.interestPercent;
	}

	/**
	 * 属性受益份额的setter方法
	 */
	public void setInterestPercent(BigDecimal interestPercent) {
		this.interestPercent = interestPercent;
	}

	/**
	 * 属性保单的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "PARENTSERIALNO")
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
	 * 属性添加投保人的时候同时将受益人信息赋值给投保人
	 */
	public void addInsured(Insured insured) {
		this.insured = insured;
		if (getInsured() != null && !getInsured().getBeneficiaries().contains(this)) {
			getInsured().getBeneficiaries().add(this);
		}
	}
	
	/**
	 * 属性保单的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO")
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * 属性保单的setter方法
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	
	/**
	 * 属性添加保单时同时将受益人赋值给保单信息
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getBeneficiaries().contains(this)) {
			getInsurancePolicy().getBeneficiaries().add(this);
		}
	}
	
}
