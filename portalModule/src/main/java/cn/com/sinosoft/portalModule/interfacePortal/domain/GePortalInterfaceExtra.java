package cn.com.sinosoft.portalModule.interfacePortal.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * POJO��GePortalInterfaceExtra
 */
@Entity
@Table(name = "GE_PORTAL_INTERFACE_EXTRA")
public class GePortalInterfaceExtra implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ժ�������� */
	private GePortalInterfaceExtraId id;

	/** ���Բ���ֵ */
	private String paramValue;

	/**
	 * ��GePortalInterfaceExtra��Ĭ�Ϲ��췽��
	 */
	public GePortalInterfaceExtra() {
	}

	/**
	 * ���Ժ�������ŵ�getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "transtype", column = @Column(name = "TRANSTYPE")),
			@AttributeOverride(name = "paramname", column = @Column(name = "PARAMNAME")) })
	public GePortalInterfaceExtraId getId() {
		return this.id;
	}

	/**
	 * ���Ժ�������ŵ�setter����
	 */
	public void setId(GePortalInterfaceExtraId id) {
		this.id = id;
	}

	/**
	 * ���Բ���ֵ��getter����
	 */

	@Column(name = "PARAMVALUE")
	public String getParamValue() {
		return this.paramValue;
	}

	/**
	 * ���Բ���ֵ��setter����
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
