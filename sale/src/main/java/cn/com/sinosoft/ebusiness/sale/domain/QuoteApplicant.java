package cn.com.sinosoft.ebusiness.sale.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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

	/** ����Ͷ�����뱻�����˹�ϵ */
	private Integer relatedToInsured;

	/** �������㵥 */
	private QuoteMain quoteMain;
	
	/**
	 * ��InsuranceApplicant��Ĭ�Ϲ��췽��
	 */
	public QuoteApplicant() {
	}
	

	/**
	 * ����Ͷ�����뱻�����˹�ϵ��getter����
	 */
	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * ����Ͷ���������������˹�ϵ��setter����
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}

	/**
	 * ���Ա�����getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUOTENO", unique = true, nullable = false)
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
	 * ������ӱ���ʱͬʱ����������Ϣ��ֵ����������
	 */
	public void addQuoteMain(QuoteMain quoteMain) {
		this.quoteMain = quoteMain;
		if (getQuoteMain() != null && getQuoteMain().getQuoteApplicant() == null) {
			getQuoteMain().setQuoteApplicant(this);
		}
		
	}
}
