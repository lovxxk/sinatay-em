package cn.com.sinosoft.ebusiness.sys.permission.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeDeptGroupRoleId
 */
@Embeddable
public class GeDeptGroupRoleId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Խ�ɫ��� */
	private String roleid;

	/** �����û����� */
	private String groupid;

	/** ���Ի������ */
	private String deptid;

	/**
	 * ��GeDeptGroupRoleId��Ĭ�Ϲ��췽��
	 */
	public GeDeptGroupRoleId() {
	}

	/**
	 * ���Խ�ɫ��ŵ�getter����
	 */

	@Column(name = "ROLEID")
	public String getRoleid() {
		return this.roleid;
	}

	/**
	 * ���Խ�ɫ��ŵ�setter����
	 */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	/**
	 * �����û����ŵ�getter����
	 */

	@Column(name = "GROUPID")
	public String getGroupid() {
		return this.groupid;
	}

	/**
	 * �����û����ŵ�setter����
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	/**
	 * ���Ի�����ŵ�getter����
	 */

	@Column(name = "DEPTID")
	public String getDeptid() {
		return this.deptid;
	}

	/**
	 * ���Ի�����ŵ�setter����
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
