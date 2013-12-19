package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeKindId
 */
@Embeddable
public class GeKindId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �������ִ��� */
	private String riskCode;

	/** �����ձ���� */
	private String kindCode;

	/**
	 * ��GeKindId��Ĭ�Ϲ��췽��
	 */
	public GeKindId() {
	}

	/**
	 * �������ִ����getter����
	 */

	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}

	/**
	 * �������ִ����setter����
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * �����ձ�����getter����
	 */

	@Column(name = "KINDCODE")
	public String getKindCode() {
		return this.kindCode;
	}

	/**
	 * �����ձ�����setter����
	 */
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeKindId)) {
			return false;
		}
		GeKindId castOther = (GeKindId) other;

		return ((this.getRiskCode() == castOther.getRiskCode()) || (this
				.getRiskCode() != null && castOther.getRiskCode() != null && this
				.getRiskCode().equals(castOther.getRiskCode())))
				&& ((this.getKindCode() == castOther.getKindCode()) || (this
						.getKindCode() != null
						&& castOther.getKindCode() != null && this
						.getKindCode().equals(castOther.getKindCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRiskCode() == null ? 0 : this.getRiskCode().hashCode());
		result = 37 * result
				+ (getKindCode() == null ? 0 : this.getKindCode().hashCode());
		return result;
	}

}
