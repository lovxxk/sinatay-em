package cn.com.sinosoft.portalModule.interfacePortal.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GePortalInterfaceExtraId
 */
@Embeddable
public class GePortalInterfaceExtraId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Խ������� */
	private String transType;

	/** ���Բ������� */
	private String paramName;

	/**
	 * ��GePortalInterfaceExtraId��Ĭ�Ϲ��췽��
	 */
	public GePortalInterfaceExtraId() {
	}

	/**
	 * ���Խ������͵�getter����
	 */

	@Column(name = "TRANSTYPE")
	public String getTransType() {
		return this.transType;
	}

	/**
	 * ���Խ������͵�setter����
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * ���Բ������Ƶ�getter����
	 */

	@Column(name = "PARAMNAME")
	public String getParamName() {
		return this.paramName;
	}

	/**
	 * ���Բ������Ƶ�setter����
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GePortalInterfaceExtraId)) {
			return false;
		}
		GePortalInterfaceExtraId castOther = (GePortalInterfaceExtraId) other;

		return ((this.getTransType() == castOther.getTransType()) || (this
				.getTransType() != null && castOther.getTransType() != null && this
				.getTransType().equals(castOther.getTransType())))
				&& ((this.getParamName() == castOther.getParamName()) || (this
						.getParamName() != null
						&& castOther.getParamName() != null && this
						.getParamName().equals(castOther.getParamName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTransType() == null ? 0 : this.getTransType().hashCode());
		result = 37 * result
				+ (getParamName() == null ? 0 : this.getParamName().hashCode());
		return result;
	}

}
