package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeCodeId
 */
@Embeddable
public class GeCodeId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Դ������� */
	private String codeType;

	/** ���Դ��� */
	private String codeCode;

	/**
	 * ��GeCodeId��Ĭ�Ϲ��췽��
	 */
	public GeCodeId() {
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

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeCodeId)) {
			return false;
		}
		GeCodeId castOther = (GeCodeId) other;

		return ((this.getCodeType() == castOther.getCodeType()) || (this
				.getCodeType() != null && castOther.getCodeType() != null && this
				.getCodeType().equals(castOther.getCodeType())))
				&& ((this.getCodeCode() == castOther.getCodeCode()) || (this
						.getCodeCode() != null
						&& castOther.getCodeCode() != null && this
						.getCodeCode().equals(castOther.getCodeCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCodeType() == null ? 0 : this.getCodeType().hashCode());
		result = 37 * result
				+ (getCodeCode() == null ? 0 : this.getCodeCode().hashCode());
		return result;
	}
	
	
	
	

}
