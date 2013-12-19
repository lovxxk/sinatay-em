package cn.com.sinosoft.portalModule.interfacePortal.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO类GePortalInterfaceExtra
 */
@Entity
@Table(name = "GE_PORTAL_INTERFACE_EXTRA")
public class GePortalInterfaceExtra implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性黑名单编号 */
	private GePortalInterfaceExtraId id;

	/** 属性参数值 */
	private String paramValue;

	/**
	 * 类GePortalInterfaceExtra的默认构造方法
	 */
	public GePortalInterfaceExtra() {
	}

	/**
	 * 属性黑名单编号的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "transtype", column = @Column(name = "TRANSTYPE")),
			@AttributeOverride(name = "paramname", column = @Column(name = "PARAMNAME")) })
	public GePortalInterfaceExtraId getId() {
		return this.id;
	}

	/**
	 * 属性黑名单编号的setter方法
	 */
	public void setId(GePortalInterfaceExtraId id) {
		this.id = id;
	}

	/**
	 * 属性参数值的getter方法
	 */

	@Column(name = "PARAMVALUE")
	public String getParamValue() {
		return this.paramValue;
	}

	/**
	 * 属性参数值的setter方法
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
