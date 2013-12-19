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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Insured")
public class QuoteInsured extends QuoteRole implements java.io.Serializable {
	
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
	
	/** �������㵥 */
	private QuoteMain quoteMain;

	/**���ñ�����Id�洢*/
	private String topInsuredId;
	
	/**
	 * ��Insured��Ĭ�Ϲ��췽��
	 */
	public QuoteInsured() {
	}

	/**
	 * ������Ͷ���˹�ϵ��getter����
	 */

	@Column(name = "RELATEDTOAPPLICANT")
	public Integer getRelatedToApplicant() {
		return this.relatedToApplicant;
	}

	/**
	 * ������Ͷ���˹�ϵ��setter����
	 */
	public void setRelatedToApplicant(Integer relatedToApplicant) {
		this.relatedToApplicant = relatedToApplicant;
	}
	
	/**
	 * ���������������˹�ϵ��getter����
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
	 * ���Ա����˱�־��getter����
	 */
	@Column(name = "MAININSUREDFLAG")
	public Integer getMainInsuredFlag() {
		return mainInsuredFlag;
	}
	
	/**
	 * ���Ա����˱�־��setter����
	 */
	public void setMainInsuredFlag(Integer mainInsuredFlag) {
		this.mainInsuredFlag = mainInsuredFlag;
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
	 * �������㵥��getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTENO", unique = true , nullable = false)
	@JsonIgnore
	public QuoteMain getQuoteMain() {
		return this.quoteMain;
	}

	/**
	 * �������㵥��setter����
	 */
	public void setQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
	}
	
	/**
	 * ������㵥ʱͬʱ�������˸�ֵ�����㵥
	 * @param insurancePolicy
	 */
	public void addQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
		if (getQuoteMain() != null && !getQuoteMain().getQuoteInsureds().contains(this)) {
			getQuoteMain().getQuoteInsureds().add(this);
		}
	}
	
	@Column(name = "TOPINSUREDID")
	public String getTopInsuredId() {
		return topInsuredId;
	}

	public void setTopInsuredId(String topInsuredId) {
		this.topInsuredId = topInsuredId;
	}
	
}
