package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO��GeStationProvinceCico
 */
@Entity
@Table(name = "GE_STATION_PROVINCE_CICO")
public class GeStationProvinceCico implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����obid */
	private String obid;

	/** �������� */
	private String name;

	/** ���Ը�Ȩ�ޱ�� */
	private String parentID;

	/** ����Column_4 */
	private String jhdls;

	/**
	 * ��GeStationProvinceCico��Ĭ�Ϲ��췽��
	 */
	public GeStationProvinceCico() {
	}

	/**
	 * ����obid��getter����
	 */
	@Id
	@Column(name = "OBID")
	public String getObid() {
		return this.obid;
	}

	/**
	 * ����obid��setter����
	 */
	public void setObid(String obid) {
		this.obid = obid;
	}

	/**
	 * �������Ƶ�getter����
	 */

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	/**
	 * �������Ƶ�setter����
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���Ը�Ȩ�ޱ�ŵ�getter����
	 */

	@Column(name = "PARENT_ID")
	public String getParentID() {
		return this.parentID;
	}

	/**
	 * ���Ը�Ȩ�ޱ�ŵ�setter����
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * ����Column_4��getter����
	 */

	@Column(name = "JHDLS")
	public String getJhdls() {
		return this.jhdls;
	}

	/**
	 * ����Column_4��setter����
	 */
	public void setJhdls(String jhdls) {
		this.jhdls = jhdls;
	}

}
