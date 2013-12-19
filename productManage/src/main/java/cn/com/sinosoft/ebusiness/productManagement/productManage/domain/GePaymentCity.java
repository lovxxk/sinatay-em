package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO��GePaymentCity
 */
@Entity
@Table(name = "GE_PAYMENT_CITY")
public class GePaymentCity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����id */
	private GePaymentCityId id;

	/** ����gePayment */
	private GePayment gePayment;

	/**
	 * ��GePaymentCity��Ĭ�Ϲ��췽��
	 */
	public GePaymentCity() {
	}

	/**
	 * ����id��getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cityid", column = @Column(name = "CITYID")),
			@AttributeOverride(name = "paymentid", column = @Column(name = "PAYMENTID")) })
	public GePaymentCityId getId() {
		return this.id;
	}

	/**
	 * ����id��setter����
	 */
	public void setId(GePaymentCityId id) {
		this.id = id;
	}

	/**
	 * ����gePayment��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENTID", nullable = false, insertable = false, updatable = false)
	public GePayment getGePayment() {
		return this.gePayment;
	}

	/**
	 * ����gePayment��setter����
	 */
	public void setGePayment(GePayment gePayment) {
		this.gePayment = gePayment;
	}

}
