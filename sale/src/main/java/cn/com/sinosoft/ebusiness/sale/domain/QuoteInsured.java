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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Insured")
public class QuoteInsured extends QuoteRole implements java.io.Serializable {
	
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
	
	/** 属性试算单 */
	private QuoteMain quoteMain;

	/**常用被保人Id存储*/
	private String topInsuredId;
	
	/**
	 * 类Insured的默认构造方法
	 */
	public QuoteInsured() {
	}

	/**
	 * 属性与投保人关系的getter方法
	 */

	@Column(name = "RELATEDTOAPPLICANT")
	public Integer getRelatedToApplicant() {
		return this.relatedToApplicant;
	}

	/**
	 * 属性与投保人关系的setter方法
	 */
	public void setRelatedToApplicant(Integer relatedToApplicant) {
		this.relatedToApplicant = relatedToApplicant;
	}
	
	/**
	 * 属性与主被保险人关系的getter方法
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
	 * 属性被保人标志的getter方法
	 */
	@Column(name = "MAININSUREDFLAG")
	public Integer getMainInsuredFlag() {
		return mainInsuredFlag;
	}
	
	/**
	 * 属性被保人标志的setter方法
	 */
	public void setMainInsuredFlag(Integer mainInsuredFlag) {
		this.mainInsuredFlag = mainInsuredFlag;
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
	 * 属性试算单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTENO", unique = true , nullable = false)
	@JsonIgnore
	public QuoteMain getQuoteMain() {
		return this.quoteMain;
	}

	/**
	 * 属性试算单的setter方法
	 */
	public void setQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
	}
	
	/**
	 * 添加试算单时同时将被保人赋值给试算单
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
