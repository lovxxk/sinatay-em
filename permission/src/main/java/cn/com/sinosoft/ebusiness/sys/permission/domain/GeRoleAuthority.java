package cn.com.sinosoft.ebusiness.sys.permission.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO��GeRoleAuthority
 */
@Entity
@Table(name = "GE_ROLE_AUTHORITY")
public class GeRoleAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ժ�������� */
	private GeRoleAuthorityId id;

	/** ���Ժ�̨��ɫ��-GE_Role */
	private GeRole geRole;

	/**
	 * ��GeRoleAuthority��Ĭ�Ϲ��췽��
	 */
	public GeRoleAuthority() {
	}

	/**
	 * ���Ժ�������ŵ�getter����
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleID", column = @Column(name = "ROLEID")),
			@AttributeOverride(name = "authorityID", column = @Column(name = "AUTHORITYID")) })
	public GeRoleAuthorityId getId() {
		return this.id;
	}

	/**
	 * ���Ժ�������ŵ�setter����
	 */
	public void setId(GeRoleAuthorityId id) {
		this.id = id;
	}

	/**
	 * ���Ժ�̨��ɫ��-GE_Role��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLEID", nullable = false, insertable = false, updatable = false)
	public GeRole getGeRole() {
		return this.geRole;
	}

	/**
	 * ���Ժ�̨��ɫ��-GE_Role��setter����
	 */
	public void setGeRole(GeRole geRole) {
		this.geRole = geRole;
	}

}
