package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeStationProvinceCico
 */
@Entity
@Table(name = "GE_STATION_PROVINCE_CICO")
public class GeStationProvinceCico implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性obid */
	private String obid;

	/** 属性名称 */
	private String name;

	/** 属性父权限编号 */
	private String parentID;

	/** 属性Column_4 */
	private String jhdls;

	/**
	 * 类GeStationProvinceCico的默认构造方法
	 */
	public GeStationProvinceCico() {
	}

	/**
	 * 属性obid的getter方法
	 */
	@Id
	@Column(name = "OBID")
	public String getObid() {
		return this.obid;
	}

	/**
	 * 属性obid的setter方法
	 */
	public void setObid(String obid) {
		this.obid = obid;
	}

	/**
	 * 属性名称的getter方法
	 */

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	/**
	 * 属性名称的setter方法
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 属性父权限编号的getter方法
	 */

	@Column(name = "PARENT_ID")
	public String getParentID() {
		return this.parentID;
	}

	/**
	 * 属性父权限编号的setter方法
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * 属性Column_4的getter方法
	 */

	@Column(name = "JHDLS")
	public String getJhdls() {
		return this.jhdls;
	}

	/**
	 * 属性Column_4的setter方法
	 */
	public void setJhdls(String jhdls) {
		this.jhdls = jhdls;
	}

}
