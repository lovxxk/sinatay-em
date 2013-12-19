package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��Insured
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Insured")
public class Insured extends PartyRoleInPolicy {
	
	private static final long serialVersionUID = 1L;

	/** ������Ͷ���˹�ϵ */
	private Integer relatedToApplicant;

	/** ���������������˹�ϵ */
	private Integer relatedToInsured;
	
	/** �����������˱�־ */
	private Integer mainInsuredFlag;
	
	/** ���Ա���˳�� */
	private Integer insuredOrder;

	/** ������ռ����ٷֱ� */
	private BigDecimal insuredAmountPercent;
	
	/** ����Ͷ���˸�֪��Ϣ */
	private String indicatorMessage;

	/** ����ͬҵ���� */
	private String sameIndustryInsuredAmount;
	
	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;
	
	/** ���Ա������˱���/���� */
	private List<InsuredLiability> insuredLiabilities = new ArrayList<InsuredLiability>(0);
	
	/** ���������� */
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>(0);
	
	/**
	 * ��Insured��Ĭ�Ϲ��췽��
	 */
	public Insured() {
	}

	/**
	 * ������Ͷ���˹�ϵ��getter����
	 */

	@Column(name = "RELATEDTOAPPLICANT")
	public Integer getRelatedToApplicant() {
		return this.relatedToApplicant;
	}


	/**
	 * ������Ͷ���˹�ϵö�����getter����
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
	 * ������Ͷ���˹�ϵ����ֵ��getter����
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
	 * ������Ͷ���˹�ϵ�̼�ֵ��getter����
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
	 * ������Ͷ���˹�ϵ��setter����
	 */
	public void setRelatedToApplicant(Integer relatedToApplicant) {
		this.relatedToApplicant = relatedToApplicant;
	}
	
	/**
	 * ������Ͷ���˹�ϵ��ֵ
	 */
	public void setEnumRelatedToApplicant(RelatedToRoleCode  relatedToApplicant) {
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	/**
	 * ���Ժ�����Ͷ���˹�ϵ��ֵ
	 */
	public void setRelatedToApplicantByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	/**
	 * �����̼���Ͷ���˹�ϵ��ֵ
	 */
	public void setRelatedToApplicantByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	
	/**
	 * ���������������˹�ϵ��getter����
	 */

	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * ���������������˹�ϵö�����getter����
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
	 * ���������������˹�ϵ����ֵ��getter����
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
	 * ���������������˹�ϵ�̼�ֵ��getter����
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
	 * ���Ա����˱�־��getter����
	 */
	@Column(name = "MAININSUREDFLAG")
	public Integer getMainInsuredFlag() {
		return mainInsuredFlag;
	}
	
	/**
	 * ���Ա����˱�־ö�����getter����
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
	 * ���Ա����˱�־����ֵ��getter����
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
	 * ���Ա����˱�־�̼�ֵ��getter����
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
	 * ���Ա����˱�־��setter����
	 */
	public void setMainInsuredFlag(Integer mainInsuredFlag) {
		this.mainInsuredFlag = mainInsuredFlag;
	}
	
	/**
	 * ���Ա����˱�־��ֵ
	 */
	public void setEnumMainInsuredFlag(MainInsuredFlag  mainInsuredFlag) {
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * ���Ժ��ı����˱�־��ֵ
	 */
	public void setMainInsuredFlagByCoreValue(String coreValue) {
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByCoreValue(MainInsuredFlag.class, coreValue);
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * �����̼ұ����˱�־��ֵ
	 */
	public void setMainInsuredFlagByMerchantValue(String merchantValue) {
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(MainInsuredFlag.class, merchantValue);
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * ���Ա���˳���getter����
	 */

	@Column(name = "ROLEORDER")
	public Integer getInsuredOrder() {
		return this.insuredOrder;
	}

	/**
	 * ���Ա���˳���setter����
	 */
	public void setInsuredOrder(Integer insuredOrder) {
		this.insuredOrder = insuredOrder;
	}

	/**
	 * ������ռ����ٷֱȵ�getter����
	 */

	@Column(name = "INSUREDAMOUNTPERCENT")
	public BigDecimal getInsuredAmountPercent() {
		return this.insuredAmountPercent;
	}

	/**
	 * ������ռ����ٷֱȵ�setter����
	 */
	public void setInsuredAmountPercent(BigDecimal insuredAmountPercent) {
		this.insuredAmountPercent = insuredAmountPercent;
	}

	/**
	 * ����Ͷ���˸�֪��Ϣ��getter����
	 */

	@Column(name = "INDICATORMESSAGE")
	public String getIndicatorMessage() {
		return this.indicatorMessage;
	}

	/**
	 * ����Ͷ���˸�֪��Ϣ��setter����
	 */
	public void setIndicatorMessage(String indicatorMessage) {
		this.indicatorMessage = indicatorMessage;
	}

	/**
	 * ����ͬҵ�����getter����
	 */

	@Column(name = "SAMEINDUSTRYINSUREDAMOUNT")
	public String getSameIndustryInsuredAmount() {
		return this.sameIndustryInsuredAmount;
	}

	/**
	 * ����ͬҵ�����setter����
	 */
	public void setSameIndustryInsuredAmount(String sameIndustryInsuredAmount) {
		this.sameIndustryInsuredAmount = sameIndustryInsuredAmount;
	}

	/**
	 * ���������˵�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insured")
	@Where(clause="ROLEKIND = 'Beneficiary'")
	@Fetch(FetchMode.SUBSELECT)
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	
	/**
	 * ������������˵�setter����
	 */
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	/**
	 * �����������������
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
	 * ���Ա�����getter����
	 */
	@OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO", unique = true , nullable = false)
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
	 * ��ӱ���ʱͬʱ�������˸�ֵ������
	 * @param insurancePolicy
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && !getInsurancePolicy().getInsureds().contains(this)) {
			getInsurancePolicy().getInsureds().add(this);
		}
	}
	/**
	 * ���Ա������˱���/���ֵ�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insured")
	@Fetch(FetchMode.SUBSELECT)
	public List<InsuredLiability> getInsuredLiabilities() {
		return insuredLiabilities;
	}
	
	/**
	 * ���Ա������˱���/���ֵ�setter����
	 */
	public void setInsuredLiabilities(List<InsuredLiability> insuredLiabilities) {
		this.insuredLiabilities = insuredLiabilities;
	}

	/**
	 * ����������б������˱���/����
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
