package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;


// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeCodeRiskId
 */
@Embeddable
public class GeCodeRiskId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性机构 */
	private String companyCode;

	/** 属性代码类型 */
	private String codeType;

	/** 属性代码 */
	private String codeCode;

	/** 属性险种代码 */
	private String riskCode;

	/**
	 * 类GeCodeRiskId的默认构造方法
	 */
	public GeCodeRiskId() {
	}

	/**
	 * 属性机构的getter方法
	 */

	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * 属性机构的setter方法
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * 属性代码类型的getter方法
	 */

	@Column(name = "CODETYPE")
	public String getCodeType() {
		return this.codeType;
	}

	/**
	 * 属性代码类型的setter方法
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * 属性代码的getter方法
	 */

	@Column(name = "CODECODE")
	public String getCodeCode() {
		return this.codeCode;
	}

	/**
	 * 属性代码的setter方法
	 */
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}

	/**
	 * 属性险种代码的getter方法
	 */

	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return this.riskCode;
	}

	/**
	 * 属性险种代码的setter方法
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
