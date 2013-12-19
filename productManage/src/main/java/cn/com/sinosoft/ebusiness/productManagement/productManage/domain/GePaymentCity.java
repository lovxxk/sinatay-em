package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GePaymentCity
 */
@Entity
@Table(name = "GE_PAYMENT_CITY")
public class GePaymentCity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private GePaymentCityId id;

	/** 属性gePayment */
	private GePayment gePayment;

	/**
	 * 类GePaymentCity的默认构造方法
	 */
	public GePaymentCity() {
	}

	/**
	 * 属性id的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cityid", column = @Column(name = "CITYID")),
			@AttributeOverride(name = "paymentid", column = @Column(name = "PAYMENTID")) })
	public GePaymentCityId getId() {
		return this.id;
	}

	/**
	 * 属性id的setter方法
	 */
	public void setId(GePaymentCityId id) {
		this.id = id;
	}

	/**
	 * 属性gePayment的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENTID", nullable = false, insertable = false, updatable = false)
	public GePayment getGePayment() {
		return this.gePayment;
	}

	/**
	 * 属性gePayment的setter方法
	 */
	public void setGePayment(GePayment gePayment) {
		this.gePayment = gePayment;
	}

}
