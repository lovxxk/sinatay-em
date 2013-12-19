package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��Beneficiary
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Beneficiary")
public class Beneficiary extends PartyRoleInPolicy {
	private static final long serialVersionUID = 1L;

	
	/** �������������� */
	private Integer beneficiaryType;

	/** �����뱻�����˹�ϵ */
	private Integer relatedToInsured;
	
	/** ����������˳�� */
	private Integer beneficiaryOrder;

	/** ��������ݶ� */
	private BigDecimal interestPercent;

	/** ���Ա������� */
	private Insured insured;

	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;
	
	/**
	 * ��Beneficiary��Ĭ�Ϲ��췽��
	 */
	public Beneficiary() {
	}

	/**
	 * �������������͵�getter����
	 */
	@Column(name = "BENEFICIARYTYPE")
	public Integer getBeneficiaryType() {
		return beneficiaryType;
	}

	/**
	 * �������������ͺ���ֵ��getter����
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
	 * �������������ͺ���ֵ��getter����
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
	 * ���������������̼�ֵ��getter����
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
	 * �������������͵�setter����
	 */
	public void setBeneficiaryType(Integer beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}
	
	/**
	 * �������������͸�ֵ
	 */
	public void setEnumBeneficiaryType(BeneficiaryType  beneficiaryType) {
		if (beneficiaryType != null) {
			this.beneficiaryType = beneficiaryType.getValue();
		}
	}
	
	/**
	 * ���Ժ������������͸�ֵ
	 */
	public void setBeneficiaryTypeByCoreValue(String coreValue) {
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByCoreValue(BeneficiaryType.class, coreValue);
		if (beneficiaryType != null) {
			this.beneficiaryType = beneficiaryType.getValue();
		}
	}
	
	/**
	 * �����̼����������͸�ֵ
	 */
	public void setBeneficiaryTypeByMerchantValue(String merchantValue) {
		BeneficiaryType  beneficiaryType = (BeneficiaryType) EnumDataUtils.getEnumDictionaryByMerchantValue(BeneficiaryType.class, merchantValue);
		if (beneficiaryType != null) {
			this.beneficiaryType = beneficiaryType.getValue();
		}
	}
	
	/**
	 * �����뱻�����˹�ϵ��getter����
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * �����뱻�����˹�ϵ����ֵ��getter����
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
	 * �����뱻�����˹�ϵ����ֵ��getter����
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
	 * �����뱻�����˹�ϵ�̼�ֵ��getter����
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
	 * ���������������˹�ϵ��setter����
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}

	/**
	 * �����뱻�����˹�ϵ��ֵ
	 */
	public void setEnumRelatedToInsured(RelatedToRoleCode  relatedToInsured) {
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * ���Ժ����뱻�����˹�ϵ��ֵ
	 */
	public void setRelatedToInsuredByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
		
	}
	
	/**
	 * �����̼��뱻�����˹�ϵ��ֵ
	 */
	public void setRelatedToInsuredByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * ����������˳���getter����
	 */
	@Column(name = "ROLEORDER")
	public Integer getBeneficiaryOrder() {
		return beneficiaryOrder;
	}

	/**
	 * ����������˳���setter����
	 */
	public void setBeneficiaryOrder(Integer beneficiaryOrder) {
		this.beneficiaryOrder = beneficiaryOrder;
	}

	/**
	 * ��������ݶ��getter����
	 */

	@Column(name = "INTERESTPERCENT")
	public BigDecimal getInterestPercent() {
		return this.interestPercent;
	}

	/**
	 * ��������ݶ��setter����
	 */
	public void setInterestPercent(BigDecimal interestPercent) {
		this.interestPercent = interestPercent;
	}

	/**
	 * ���Ա�����getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "PARENTSERIALNO")
	public Insured getInsured() {
		return insured;
	}
	
	/**
	 * ���Ա�����setter����
	 */
	public void setInsured(Insured insured) {
		this.insured = insured;
	}
	
	/**
	 * �������Ͷ���˵�ʱ��ͬʱ����������Ϣ��ֵ��Ͷ����
	 */
	public void addInsured(Insured insured) {
		this.insured = insured;
		if (getInsured() != null && !getInsured().getBeneficiaries().contains(this)) {
			getInsured().getBeneficiaries().add(this);
		}
	}
	
	/**
	 * ���Ա�����getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
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
	 * ������ӱ���ʱͬʱ�������˸�ֵ��������Ϣ
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getBeneficiaries().contains(this)) {
			getInsurancePolicy().getBeneficiaries().add(this);
		}
	}
	
}
