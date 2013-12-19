package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import cn.com.sinosoft.businessModule.enums.dictionary.RelatedToRoleCode;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类InsuranceApplicant
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("InsuranceApplicant")
public class InsuranceApplicant extends PartyRoleInPolicy {
	private static final long serialVersionUID = 1L;

	/** 属性投保人与被保险人关系 */
	private Integer relatedToInsured;

	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/**
	 * 类InsuranceApplicant的默认构造方法
	 */
	public InsuranceApplicant() {
	}
	

	/**
	 * 属性投保人与被保险人关系的getter方法
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * 属性投保人与被保险人关系核心值的getter方法
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
	 * 属性投保人与被保险人关系核心值的getter方法
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
	 * 属性投保人与被保险人关系商家值的getter方法
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
	 * 属性投保人与主被保险人关系的setter方法
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}

	/**
	 * 属性投保人与被保险人关系赋值
	 */
	public void setEnumRelatedToInsured(RelatedToRoleCode  relatedToInsured) {
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * 属性核心与投保人被保险人关系赋值
	 */
	public void setRelatedToInsuredByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * 属性商家与投保人被保险人关系赋值
	 */
	public void setRelatedToInsuredByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * 属性保单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
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
	 * 属性添加保单时同时将被保人信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getInsuranceApplicant() == null) {
			getInsurancePolicy().setInsuranceApplicant(this);
		}
		
	}
}
