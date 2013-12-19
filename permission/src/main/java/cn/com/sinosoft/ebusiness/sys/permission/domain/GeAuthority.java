package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO类GeAuthority
 */
@Entity
@Table(name = "GE_AUTHORITY")
public class GeAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性权限编号 */
	private String authorityID;

	/** 属性权限名称 */
	private String authorityName;

	/** 属性权限类型 */
	private String authorityType;

	/** 属性权限描述 */
	private String authorityDesp;

	/** 属性权限链接地址 */
	private String authorityLink;

	/** 属性权限层级 */
	private String authorityLevel;

	/** 属性父权限编号 */
	private String parentID;

	/** 属性authorityorder */
	private String authorityorder;

	/** 属性ismenu */
	private String isMenu;

	/** 属性opentype */
	private String opentype;

	/** 属性systype */
	private String systype;

	/**
	 * 类GeAuthority的默认构造方法
	 */
	public GeAuthority() {
	}

	/**
	 * 属性权限编号的getter方法
	 */
	@Id
	@Column(name = "AUTHORITYID")
	public String getAuthorityID() {
		return this.authorityID;
	}

	/**
	 * 属性权限编号的setter方法
	 */
	public void setAuthorityID(String authorityID) {
		this.authorityID = authorityID;
	}

	/**
	 * 属性权限名称的getter方法
	 */

	@Column(name = "AUTHORITYNAME")
	public String getAuthorityName() {
		return this.authorityName;
	}

	/**
	 * 属性权限名称的setter方法
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	/**
	 * 属性权限类型的getter方法
	 */

	@Column(name = "AUTHORITYTYPE")
	public String getAuthorityType() {
		return this.authorityType;
	}

	/**
	 * 属性权限类型的setter方法
	 */
	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	/**
	 * 属性权限描述的getter方法
	 */

	@Column(name = "AUTHORITYDESP")
	public String getAuthorityDesp() {
		return this.authorityDesp;
	}

	/**
	 * 属性权限描述的setter方法
	 */
	public void setAuthorityDesp(String authorityDesp) {
		this.authorityDesp = authorityDesp;
	}

	/**
	 * 属性权限链接地址的getter方法
	 */

	@Column(name = "AUTHORITYLINK")
	public String getAuthorityLink() {
		return this.authorityLink;
	}

	/**
	 * 属性权限链接地址的setter方法
	 */
	public void setAuthorityLink(String authorityLink) {
		this.authorityLink = authorityLink;
	}

	/**
	 * 属性权限层级的getter方法
	 */

	@Column(name = "AUTHORITYLEVEL")
	public String getAuthorityLevel() {
		return this.authorityLevel;
	}

	/**
	 * 属性权限层级的setter方法
	 */
	public void setAuthorityLevel(String authorityLevel) {
		this.authorityLevel = authorityLevel;
	}

	/**
	 * 属性父权限编号的getter方法
	 */

	@Column(name = "PARENTID")
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
	 * 属性authorityorder的getter方法
	 */

	@Column(name = "AUTHORITYORDER")
	public String getAuthorityorder() {
		return this.authorityorder;
	}

	/**
	 * 属性authorityorder的setter方法
	 */
	public void setAuthorityorder(String authorityorder) {
		this.authorityorder = authorityorder;
	}

	/**
	 * 属性ismenu的getter方法
	 */

	@Column(name = "ISMENU")
	public String getIsMenu() {
		return this.isMenu;
	}

	/**
	 * 属性ismenu的setter方法
	 */
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	/**
	 * 属性opentype的getter方法
	 */

	@Column(name = "OPENTYPE")
	public String getOpentype() {
		return this.opentype;
	}

	/**
	 * 属性opentype的setter方法
	 */
	public void setOpentype(String opentype) {
		this.opentype = opentype;
	}

	/**
	 * 属性systype的getter方法
	 */

	@Column(name = "SYSTYPE")
	public String getSystype() {
		return this.systype;
	}

	/**
	 * 属性systype的setter方法
	 */
	public void setSystype(String systype) {
		this.systype = systype;
	}

}
