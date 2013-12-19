package cn.com.sinosoft.ebusiness.sale.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
	
	/** 属性受益人类型 */
	private Integer beneficiaryType;

	/** 属性与被保险人关系 */
	private Integer relatedToInsured;
	
	/** 属性受益人顺序 */
	private Integer beneficiaryOrder;

	/** 属性受益份额 */
	private BigDecimal interestPercent;

	/** 属性保单 */
	private QuoteMain quoteMain;
	
	/**
	 * 类Beneficiary的默认构造方法
	 */
	public QuoteBeneficiary() {
	}

	/**
	 * 属性受益人类型的getter方法
	 */
	@Column(name = "BENEFICIARYTYPE")
	public Integer getBeneficiaryType() {
		return beneficiaryType;
	}
	
	/**
	 * 属性受益人类型的setter方法
	 */
	public void setBeneficiaryType(Integer beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}
	
	/**
	 * 属性与被保险人关系的getter方法
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * 属性与主被保险人关系的setter方法
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTENO")
	@JsonIgnore
	public QuoteMain getQuoteMain() {
		return this.quoteMain;
	}

	/**
	 * 属性保单的setter方法
	 */
	public void setQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
	}
	
	/**
	 * 属性添加保单时同时将受益人赋值给保单信息
	 */
	public void addQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
		if (getQuoteMain() != null && !getQuoteMain().getQuoteBeneficiaries().contains(this)) {
			getQuoteMain().getQuoteBeneficiaries().add(this);
		}
	}
	
}
