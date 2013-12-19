package cn.com.sinosoft.businessModule.partyRoleInPolicy.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��InsuranceApplicant
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("InsuranceApplicant")
public class InsuranceApplicant extends PartyRoleInPolicy {
	private static final long serialVersionUID = 1L;

	/** ����Ͷ�����뱻�����˹�ϵ */
	private Integer relatedToInsured;

	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;
	
	/**
	 * ��InsuranceApplicant��Ĭ�Ϲ��췽��
	 */
	public InsuranceApplicant() {
	}
	

	/**
	 * ����Ͷ�����뱻�����˹�ϵ��getter����
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * ����Ͷ�����뱻�����˹�ϵ����ֵ��getter����
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
	 * ����Ͷ�����뱻�����˹�ϵ����ֵ��getter����
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
	 * ����Ͷ�����뱻�����˹�ϵ�̼�ֵ��getter����
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
	 * ����Ͷ���������������˹�ϵ��setter����
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}

	/**
	 * ����Ͷ�����뱻�����˹�ϵ��ֵ
	 */
	public void setEnumRelatedToInsured(RelatedToRoleCode  relatedToInsured) {
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * ���Ժ�����Ͷ���˱������˹�ϵ��ֵ
	 */
	public void setRelatedToInsuredByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * �����̼���Ͷ���˱������˹�ϵ��ֵ
	 */
	public void setRelatedToInsuredByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * ���Ա�����getter����
	 */
	@OneToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
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
	 * ������ӱ���ʱͬʱ����������Ϣ��ֵ����������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getInsuranceApplicant() == null) {
			getInsurancePolicy().setInsuranceApplicant(this);
		}
		
	}
}
