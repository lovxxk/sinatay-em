package cn.com.sinosoft.portalModule.interfacePortal.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GePortalInterfaceExtraId
 */
@Embeddable
public class GePortalInterfaceExtraId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性交易类型 */
	private String transType;

	/** 属性参数名称 */
	private String paramName;

	/**
	 * 类GePortalInterfaceExtraId的默认构造方法
	 */
	public GePortalInterfaceExtraId() {
	}

	/**
	 * 属性交易类型的getter方法
	 */

	@Column(name = "TRANSTYPE")
	public String getTransType() {
		return this.transType;
	}

	/**
	 * 属性交易类型的setter方法
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * 属性参数名称的getter方法
	 */

	@Column(name = "PARAMNAME")
	public String getParamName() {
		return this.paramName;
	}

	/**
	 * 属性参数名称的setter方法
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
