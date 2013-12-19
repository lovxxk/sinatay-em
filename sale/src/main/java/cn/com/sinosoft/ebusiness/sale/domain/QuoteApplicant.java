package cn.com.sinosoft.ebusiness.sale.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Applicant")
public class QuoteApplicant extends QuoteRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性投保人与被保险人关系 */
	private Integer relatedToInsured;

	/** 属性试算单 */
	private QuoteMain quoteMain;
	
	/**
	 * 类InsuranceApplicant的默认构造方法
	 */
	public QuoteApplicant() {
	}
	

	/**
	 * 属性投保人与被保险人关系的getter方法
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * 属性投保人与主被保险人关系的setter方法
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}

	/**
	 * 属性保单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTENO", unique = true, nullable = false)
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
	 * 属性添加保单时同时将被保人信息赋值给保单对象
	 */
	public void addQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
		if (getQuoteMain() != null && getQuoteMain().getQuoteApplicant() == null) {
			getQuoteMain().setQuoteApplicant(this);
		}
		
	}
}
