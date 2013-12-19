package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import cn.com.sinosoft.businessModule.enums.dictionary.MainInsuredFlag;
import cn.com.sinosoft.businessModule.enums.dictionary.RelatedToRoleCode;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类Insured
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Insured")
public class Insured extends PartyRoleInPolicy {
	
	private static final long serialVersionUID = 1L;

	/** 属性与投保人关系 */
	private Integer relatedToApplicant;

	/** 属性与主被保险人关系 */
	private Integer relatedToInsured;
	
	/** 属性主被保人标志 */
	private Integer mainInsuredFlag;
	
	/** 属性被保顺序 */
	private Integer insuredOrder;

	/** 属性所占保额百分比 */
	private BigDecimal insuredAmountPercent;
	
	/** 属性投保人告知信息 */
	private String indicatorMessage;

	/** 属性同业保额 */
	private String sameIndustryInsuredAmount;
	
	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/** 属性被保险人保障/险种 */
	private List<InsuredLiability> insuredLiabilities = new ArrayList<InsuredLiability>(0);
	
	/** 属性受益人 */
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>(0);
	
	/**
	 * 类Insured的默认构造方法
	 */
	public Insured() {
	}

	/**
	 * 属性与投保人关系的getter方法
	 */

	@Column(name = "RELATEDTOAPPLICANT")
	public Integer getRelatedToApplicant() {
		return this.relatedToApplicant;
	}


	/**
	 * 属性与投保人关系枚举类的getter方法
	 */
	@Transient
	public RelatedToRoleCode getEnumRelatedToApplicant() {
		if (getRelatedToApplicant() == null) {
			return null;
		}
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToApplicant());
		return relatedToApplicant;
	}
	
	/**
	 * 属性与投保人关系核心值的getter方法
	 */
	@Transient
	public String getRelatedToApplicantByCoreValue() {
		if (getRelatedToApplicant() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToApplicant());
		return relatedToApplicant.getCoreValue();
	}
	
	/**
	 * 属性与投保人关系商家值的getter方法
	 */
	@Transient
	public String getRelatedToApplicantByMerchantValue() {
		if (getRelatedToApplicant() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToApplicant());
		return relatedToApplicant.getMerchantValue();
	}
	
	/**
	 * 属性与投保人关系的setter方法
	 */
	public void setRelatedToApplicant(Integer relatedToApplicant) {
		this.relatedToApplicant = relatedToApplicant;
	}
	
	/**
	 * 属性与投保人关系赋值
	 */
	public void setEnumRelatedToApplicant(RelatedToRoleCode  relatedToApplicant) {
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	/**
	 * 属性核心与投保人关系赋值
	 */
	public void setRelatedToApplicantByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	/**
	 * 属性商家与投保人关系赋值
	 */
	public void setRelatedToApplicantByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	
	/**
	 * 属性与主被保险人关系的getter方法
	 */

	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * 属性与主被保险人关系枚举类的getter方法
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
	 * 属性与主被保险人关系核心值的getter方法
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
	 * 属性与主被保险人关系商家值的getter方法
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
	 * 属性被保人标志的getter方法
	 */
	@Column(name = "MAININSUREDFLAG")
	public Integer getMainInsuredFlag() {
		return mainInsuredFlag;
	}
	
	/**
	 * 属性被保人标志枚举类的getter方法
	 */
	@Transient
	public MainInsuredFlag getEnumMainInsuredFlag() {
		if (getMainInsuredFlag() == null) {
			return null;
		}
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByValue(MainInsuredFlag.class, getMainInsuredFlag());
		return mainInsuredFlag;
	}
	
	/**
	 * 属性被保人标志核心值的getter方法
	 */
	@Transient
	public String getMainInsuredFlagByCoreValue() {
		if (getMainInsuredFlag() == null) {
			return "";
		}
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByValue(MainInsuredFlag.class, getMainInsuredFlag());
		return mainInsuredFlag.getCoreValue();
	}
	
	/**
	 * 属性被保人标志商家值的getter方法
	 */
	@Transient
	public String getMainInsuredFlagByMerchantValue() {
		if (getMainInsuredFlag() == null) {
			return "";
		}
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByValue(MainInsuredFlag.class, getMainInsuredFlag());
		return mainInsuredFlag.getMerchantValue();
	}
	
	
	/**
	 * 属性被保人标志的setter方法
	 */
	public void setMainInsuredFlag(Integer mainInsuredFlag) {
		this.mainInsuredFlag = mainInsuredFlag;
	}
	
	/**
	 * 属性被保人标志赋值
	 */
	public void setEnumMainInsuredFlag(MainInsuredFlag  mainInsuredFlag) {
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * 属性核心被保人标志赋值
	 */
	public void setMainInsuredFlagByCoreValue(String coreValue) {
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByCoreValue(MainInsuredFlag.class, coreValue);
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * 属性商家被保人标志赋值
	 */
	public void setMainInsuredFlagByMerchantValue(String merchantValue) {
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(MainInsuredFlag.class, merchantValue);
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * 属性被保顺序的getter方法
	 */

	@Column(name = "ROLEORDER")
	public Integer getInsuredOrder() {
		return this.insuredOrder;
	}

	/**
	 * 属性被保顺序的setter方法
	 */
	public void setInsuredOrder(Integer insuredOrder) {
		this.insuredOrder = insuredOrder;
	}

	/**
	 * 属性所占保额百分比的getter方法
	 */

	@Column(name = "INSUREDAMOUNTPERCENT")
	public BigDecimal getInsuredAmountPercent() {
		return this.insuredAmountPercent;
	}

	/**
	 * 属性所占保额百分比的setter方法
	 */
	public void setInsuredAmountPercent(BigDecimal insuredAmountPercent) {
		this.insuredAmountPercent = insuredAmountPercent;
	}

	/**
	 * 属性投保人告知信息的getter方法
	 */

	@Column(name = "INDICATORMESSAGE")
	public String getIndicatorMessage() {
		return this.indicatorMessage;
	}

	/**
	 * 属性投保人告知信息的setter方法
	 */
	public void setIndicatorMessage(String indicatorMessage) {
		this.indicatorMessage = indicatorMessage;
	}

	/**
	 * 属性同业保额的getter方法
	 */

	@Column(name = "SAMEINDUSTRYINSUREDAMOUNT")
	public String getSameIndustryInsuredAmount() {
		return this.sameIndustryInsuredAmount;
	}

	/**
	 * 属性同业保额的setter方法
	 */
	public void setSameIndustryInsuredAmount(String sameIndustryInsuredAmount) {
		this.sameIndustryInsuredAmount = sameIndustryInsuredAmount;
	}

	/**
	 * 属性受益人的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insured")
	@Where(clause="ROLEKIND = 'Beneficiary'")
	@Fetch(FetchMode.SUBSELECT)
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	
	/**
	 * 属性添加受益人的setter方法
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
			if (beneficiary.getInsured() == null) {
				beneficiary.setInsured(this);
			}
			
		}
	}
	
	/**
	 * 属性保单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO", unique = true , nullable = false)
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
	 * 添加保单时同时将被保人赋值给保单
	 * @param insurancePolicy
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getInsureds().contains(this)) {
			getInsurancePolicy().getInsureds().add(this);
		}
	}
	/**
	 * 属性被保险人保障/险种的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insured")
	@Fetch(FetchMode.SUBSELECT)
	public List<InsuredLiability> getInsuredLiabilities() {
		return insuredLiabilities;
	}
	
	/**
	 * 属性被保险人保障/险种的setter方法
	 */
	public void setInsuredLiabilities(List<InsuredLiability> insuredLiabilities) {
		this.insuredLiabilities = insuredLiabilities;
	}

	/**
	 * 属性添加所有被保险人保障/险种
	 */
	public void addAllInsuredLiabilities(List<InsuredLiability> insuredLiabilities) {

		for (InsuredLiability insuredLiability:insuredLiabilities) {
			if (!getInsuredLiabilities().contains(insuredLiability)) {
				getInsuredLiabilities().add(insuredLiability);
			}
		}
		
		for (InsuredLiability insuredLiability:getInsuredLiabilities()) {
			if (insuredLiability.getInsured() == null) {
				insuredLiability.setInsured(this);
			}
			
		}
		
	}
	
}
