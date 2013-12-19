package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeRoleAuthorityId
 */
@Embeddable
public class GeRoleAuthorityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性角色编号 */
	private String roleID;

	/** 属性权限编号 */
	private String authorityID;

	/**
	 * 类GeRoleAuthorityId的默认构造方法
	 */
	public GeRoleAuthorityId() {
	}

	/**
	 * 属性角色编号的getter方法
	 */

	@Column(name = "ROLEID")
	public String getRoleID() {
		return this.roleID;
	}

	/**
	 * 属性角色编号的setter方法
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	/**
	 * 属性权限编号的getter方法
	 */

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

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeRoleAuthorityId)) {
			return false;
		}
		GeRoleAuthorityId castOther = (GeRoleAuthorityId) other;

		return ((this.getRoleID() == castOther.getRoleID()) || (this
				.getRoleID() != null && castOther.getRoleID() != null && this
				.getRoleID().equals(castOther.getRoleID())))
				&& ((this.getAuthorityID() == castOther.getAuthorityID()) || (this
						.getAuthorityID() != null
						&& castOther.getAuthorityID() != null && this
						.getAuthorityID().equals(castOther.getAuthorityID())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleID() == null ? 0 : this.getRoleID().hashCode());
		result = 37
				* result
				+ (getAuthorityID() == null ? 0 : this.getAuthorityID()
						.hashCode());
		return result;
	}

}
