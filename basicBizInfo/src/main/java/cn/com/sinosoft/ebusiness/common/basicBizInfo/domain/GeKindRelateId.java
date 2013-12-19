package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeKindRelateId
 */
@Embeddable
public class GeKindRelateId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �������ִ��� */
	private String riskCode;

	/** �����ձ���� */
	private String kindCode;

	/** ���Թ����ձ���� */
	private String relateKindCode;

	/**
	 * ��GeKindRelateId��Ĭ�Ϲ��췽��
	 */
	public GeKindRelateId() {
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

	/**
	 * ���Թ����ձ�����getter����
	 */

	@Column(name = "RELATEKINDCODE")
	public String getRelateKindCode() {
		return this.relateKindCode;
	}

	/**
	 * ���Թ����ձ�����setter����
	 */
	public void setRelateKindCode(String relateKindCode) {
		this.relateKindCode = relateKindCode;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeKindRelateId)) {
			return false;
		}
		GeKindRelateId castOther = (GeKindRelateId) other;

		return ((this.getRiskCode() == castOther.getRiskCode()) || (this
				.getRiskCode() != null && castOther.getRiskCode() != null && this
				.getRiskCode().equals(castOther.getRiskCode())))
				&& ((this.getKindCode() == castOther.getKindCode()) || (this
						.getKindCode() != null
						&& castOther.getKindCode() != null && this
						.getKindCode().equals(castOther.getKindCode())))
				&& ((this.getRelateKindCode() == castOther.getRelateKindCode()) || (this
						.getRelateKindCode() != null
						&& castOther.getRelateKindCode() != null && this
						.getRelateKindCode().equals(
								castOther.getRelateKindCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRiskCode() == null ? 0 : this.getRiskCode().hashCode());
		result = 37 * result
				+ (getKindCode() == null ? 0 : this.getKindCode().hashCode());
		result = 37
				* result
				+ (getRelateKindCode() == null ? 0 : this.getRelateKindCode()
						.hashCode());
		return result;
	}

}
