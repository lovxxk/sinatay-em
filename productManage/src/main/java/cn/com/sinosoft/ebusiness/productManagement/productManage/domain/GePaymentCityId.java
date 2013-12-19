package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GePaymentCityId
 */
@Embeddable
public class GePaymentCityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性支持地区机构编码 */
	private String cityId;

	/** 属性支付方式Id */
	private String paymentId;

	/**
	 * 类GePaymentCityId的默认构造方法
	 */
	public GePaymentCityId() {
	}

	/**
	 * 属性支持地区机构编码的getter方法
	 */

	@Column(name = "CITYID")
	public String getCityId() {
		return this.cityId;
	}

	/**
	 * 属性支持地区机构编码的setter方法
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * 属性支付方式Id的getter方法
	 */

	@Column(name = "PAYMENTID")
	public String getPaymentId() {
		return this.paymentId;
	}

	/**
	 * 属性支付方式Id的setter方法
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GePaymentCityId)) {
			return false;
		}
		GePaymentCityId castOther = (GePaymentCityId) other;

		return ((this.getCityId() == castOther.getCityId()) || (this
				.getCityId() != null && castOther.getCityId() != null && this
				.getCityId().equals(castOther.getCityId())))
				&& ((this.getPaymentId() == castOther.getPaymentId()) || (this
						.getPaymentId() != null
						&& castOther.getPaymentId() != null && this
						.getPaymentId().equals(castOther.getPaymentId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCityId() == null ? 0 : this.getCityId().hashCode());
		result = 37 * result
				+ (getPaymentId() == null ? 0 : this.getPaymentId().hashCode());
		return result;
	}

}
