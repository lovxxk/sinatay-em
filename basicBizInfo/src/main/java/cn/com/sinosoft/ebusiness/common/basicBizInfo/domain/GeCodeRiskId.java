package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeCodeRiskId
 */
@Embeddable
public class GeCodeRiskId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ի��� */
	private String companyCode;

	/** ���Դ������� */
	private String codeType;

	/** ���Դ��� */
	private String codeCode;

	/** �������ִ��� */
	private String riskCode;

	/**
	 * ��GeCodeRiskId��Ĭ�Ϲ��췽��
	 */
	public GeCodeRiskId() {
	}

	/**
	 * ���Ի�����getter����
	 */

	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * ���Ի�����setter����
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * ���Դ������͵�getter����
	 */

	@Column(name = "CODETYPE")
	public String getCodeType() {
		return this.codeType;
	}

	/**
	 * ���Դ������͵�setter����
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * ���Դ����getter����
	 */

	@Column(name = "CODECODE")
	public String getCodeCode() {
		return this.codeCode;
	}

	/**
	 * ���Դ����setter����
	 */
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
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

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeCodeRiskId)) {
			return false;
		}
		GeCodeRiskId castOther = (GeCodeRiskId) other;

		return ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
				.getCompanyCode() != null && castOther.getCompanyCode() != null && this
				.getCompanyCode().equals(castOther.getCompanyCode())))
				&& ((this.getCodeType() == castOther.getCodeType()) || (this
						.getCodeType() != null
						&& castOther.getCodeType() != null && this
						.getCodeType().equals(castOther.getCodeType())))
				&& ((this.getCodeCode() == castOther.getCodeCode()) || (this
						.getCodeCode() != null
						&& castOther.getCodeCode() != null && this
						.getCodeCode().equals(castOther.getCodeCode())))
				&& ((this.getRiskCode() == castOther.getRiskCode()) || (this
						.getRiskCode() != null
						&& castOther.getRiskCode() != null && this
						.getRiskCode().equals(castOther.getRiskCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode()
						.hashCode());
		result = 37 * result
				+ (getCodeType() == null ? 0 : this.getCodeType().hashCode());
		result = 37 * result
				+ (getCodeCode() == null ? 0 : this.getCodeCode().hashCode());
		result = 37 * result
				+ (getRiskCode() == null ? 0 : this.getRiskCode().hashCode());
		return result;
	}

}
