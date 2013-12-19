package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO类GeDeptGroupRoleId
 */
@Embeddable
public class GeDeptGroupRoleId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性角色编号 */
	private String roleid;

	/** 属性用户组编号 */
	private String groupid;

	/** 属性机构编号 */
	private String deptid;

	/**
	 * 类GeDeptGroupRoleId的默认构造方法
	 */
	public GeDeptGroupRoleId() {
	}

	/**
	 * 属性角色编号的getter方法
	 */

	@Column(name = "ROLEID")
	public String getRoleid() {
		return this.roleid;
	}

	/**
	 * 属性角色编号的setter方法
	 */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	/**
	 * 属性用户组编号的getter方法
	 */

	@Column(name = "GROUPID")
	public String getGroupid() {
		return this.groupid;
	}

	/**
	 * 属性用户组编号的setter方法
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	/**
	 * 属性机构编号的getter方法
	 */

	@Column(name = "DEPTID")
	public String getDeptid() {
		return this.deptid;
	}

	/**
	 * 属性机构编号的setter方法
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof GeDeptGroupRoleId)) {
			return false;
		}
		GeDeptGroupRoleId castOther = (GeDeptGroupRoleId) other;

		return ((this.getRoleid() == castOther.getRoleid()) || (this
				.getRoleid() != null && castOther.getRoleid() != null && this
				.getRoleid().equals(castOther.getRoleid())))
				&& ((this.getGroupid() == castOther.getGroupid()) || (this
						.getGroupid() != null && castOther.getGroupid() != null && this
						.getGroupid().equals(castOther.getGroupid())))
				&& ((this.getDeptid() == castOther.getDeptid()) || (this
						.getDeptid() != null && castOther.getDeptid() != null && this
						.getDeptid().equals(castOther.getDeptid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37 * result
				+ (getGroupid() == null ? 0 : this.getGroupid().hashCode());
		result = 37 * result
				+ (getDeptid() == null ? 0 : this.getDeptid().hashCode());
		return result;
	}

}
