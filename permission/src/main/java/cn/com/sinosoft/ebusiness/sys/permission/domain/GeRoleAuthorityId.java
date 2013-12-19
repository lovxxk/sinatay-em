package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * POJO��GeRoleAuthorityId
 */
@Embeddable
public class GeRoleAuthorityId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Խ�ɫ��� */
	private String roleID;

	/** ����Ȩ�ޱ�� */
	private String authorityID;

	/**
	 * ��GeRoleAuthorityId��Ĭ�Ϲ��췽��
	 */
	public GeRoleAuthorityId() {
	}

	/**
	 * ���Խ�ɫ��ŵ�getter����
	 */

	@Column(name = "ROLEID")
	public String getRoleID() {
		return this.roleID;
	}

	/**
	 * ���Խ�ɫ��ŵ�setter����
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	/**
	 * ����Ȩ�ޱ�ŵ�getter����
	 */

	@Column(name = "AUTHORITYID")
	public String getAuthorityID() {
		return this.authorityID;
	}

	/**
	 * ����Ȩ�ޱ�ŵ�setter����
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
