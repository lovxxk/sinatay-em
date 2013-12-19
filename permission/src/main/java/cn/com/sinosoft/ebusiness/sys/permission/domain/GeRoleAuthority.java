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
 * POJO类GeRoleAuthority
 */
@Entity
@Table(name = "GE_ROLE_AUTHORITY")
public class GeRoleAuthority implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性黑名单编号 */
	private GeRoleAuthorityId id;

	/** 属性后台角色表-GE_Role */
	private GeRole geRole;

	/**
	 * 类GeRoleAuthority的默认构造方法
	 */
	public GeRoleAuthority() {
	}

	/**
	 * 属性黑名单编号的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleID", column = @Column(name = "ROLEID")),
			@AttributeOverride(name = "authorityID", column = @Column(name = "AUTHORITYID")) })
	public GeRoleAuthorityId getId() {
		return this.id;
	}

	/**
	 * 属性黑名单编号的setter方法
	 */
	public void setId(GeRoleAuthorityId id) {
		this.id = id;
	}

	/**
	 * 属性后台角色表-GE_Role的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLEID", nullable = false, insertable = false, updatable = false)
	public GeRole getGeRole() {
		return this.geRole;
	}

	/**
	 * 属性后台角色表-GE_Role的setter方法
	 */
	public void setGeRole(GeRole geRole) {
		this.geRole = geRole;
	}

}
