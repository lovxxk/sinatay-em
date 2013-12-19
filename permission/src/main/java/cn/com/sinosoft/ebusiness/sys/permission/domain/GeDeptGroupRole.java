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
 * POJO类GeDeptGroupRole
 */
@Entity
@Table(name = "GE_DEPT_GROUP_ROLE")
public class GeDeptGroupRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性黑名单编号 */
	private GeDeptGroupRoleId id;

	/** 属性用户组表-GE_Group */
	private GeGroup geGroup;

	/** 属性后台角色表-GE_Role */
	private GeRole geRole;
	
	private String flag;

	/**
	 * 类GeDeptGroupRole的默认构造方法
	 */
	public GeDeptGroupRole() {
	}

	/**
	 * 属性黑名单编号的getter方法
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleID", column = @Column(name = "ROLEID")),
			@AttributeOverride(name = "groupID", column = @Column(name = "GROUPID")),
			@AttributeOverride(name = "deptID", column = @Column(name = "DEPTID")) })
	public GeDeptGroupRoleId getId() {
		return this.id;
	}

	/**
	 * 属性黑名单编号的setter方法
	 */
	public void setId(GeDeptGroupRoleId id) {
		this.id = id;
	}

	/**
	 * 属性用户组表-GE_Group的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUPID", nullable = false, insertable = false, updatable = false)
	public GeGroup getGeGroup() {
		return this.geGroup;
	}

	/**
	 * 属性用户组表-GE_Group的setter方法
	 */
	public void setGeGroup(GeGroup geGroup) {
		this.geGroup = geGroup;
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
	
	/**
	 * 属性子机构是否被全选的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性子机构是否被全选的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
