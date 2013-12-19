package cn.com.sinosoft.ebusiness.sale.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Beneficiary")
public class QuoteBeneficiary extends QuoteRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** �������������� */
	private Integer beneficiaryType;

	/** �����뱻�����˹�ϵ */
	private Integer relatedToInsured;
	
	/** ����������˳�� */
	private Integer beneficiaryOrder;

	/** ��������ݶ� */
	private BigDecimal interestPercent;

	/** ���Ա��� */
	private QuoteMain quoteMain;
	
	/**
	 * ��Beneficiary��Ĭ�Ϲ��췽��
	 */
	public QuoteBeneficiary() {
	}

	/**
	 * �������������͵�getter����
	 */
	@Column(name = "BENEFICIARYTYPE")
	public Integer getBeneficiaryType() {
		return beneficiaryType;
	}
	
	/**
	 * �������������͵�setter����
	 */
	public void setBeneficiaryType(Integer beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}
	
	/**
	 * �����뱻�����˹�ϵ��getter����
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * ���������������˹�ϵ��setter����
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTENO")
	@JsonIgnore
	public QuoteMain getQuoteMain() {
		return this.quoteMain;
	}

	/**
	 * ���Ա�����setter����
	 */
	public void setQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
	}
	
	/**
	 * ������ӱ���ʱͬʱ�������˸�ֵ��������Ϣ
	 */
	public void addQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
		if (getQuoteMain() != null && !getQuoteMain().getQuoteBeneficiaries().contains(this)) {
			getQuoteMain().getQuoteBeneficiaries().add(this);
		}
	}
	
}
