package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GePaymentCityId
 */
@Embeddable
public class GePaymentCityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����֧�ֵ����������� */
	private String cityId;

	/** ����֧����ʽId */
	private String paymentId;

	/**
	 * ��GePaymentCityId��Ĭ�Ϲ��췽��
	 */
	public GePaymentCityId() {
	}

	/**
	 * ����֧�ֵ������������getter����
	 */

	@Column(name = "CITYID")
	public String getCityId() {
		return this.cityId;
	}

	/**
	 * ����֧�ֵ������������setter����
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * ����֧����ʽId��getter����
	 */

	@Column(name = "PAYMENTID")
	public String getPaymentId() {
		return this.paymentId;
	}

	/**
	 * ����֧����ʽId��setter����
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
