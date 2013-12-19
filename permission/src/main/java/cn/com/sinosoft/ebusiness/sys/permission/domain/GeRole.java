package cn.com.sinosoft.ebusiness.sys.permission.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO类GeRole
 */
@Entity
@Table(name = "GE_ROLE")
public class GeRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性角色编号 */
	private String roleID;

	/** 属性角色名称 */
	private String roleName;

	/** 属性角色描述 */
	private String roleDesp;

	/** 属性机构编号 */
	private String deptID;
	
	private String deptName;
	
	private String status;
	
	/** 创建时间*/
	private Date createtime;
	
	@Transient
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/** 属性geRoleAuthorities */
	private List<GeRoleAuthority> geRoleAuthorities = new ArrayList<GeRoleAuthority>(
			0);

	/** 属性geDeptGroupRoles */
	private List<GeDeptGroupRole> geDeptGroupRoles = new ArrayList<GeDeptGroupRole>(
			0);

	/**
	 * 类GeRole的默认构造方法
	 */
	public GeRole() {
	}

	/**
	 * 属性角色编号的getter方法
	 */
	@Id
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
	 * 属性角色名称的getter方法
	 */

	@Column(name = "ROLENAME")
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * 属性角色名称的setter方法
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 属性角色描述的getter方法
	 */

	@Column(name = "ROLEDESP")
	public String getRoleDesp() {
		return this.roleDesp;
	}

	/**
	 * 属性角色描述的setter方法
	 */
	public void setRoleDesp(String roleDesp) {
		this.roleDesp = roleDesp;
	}

	/**
	 * 属性机构编号的getter方法
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * 属性机构编号的setter方法
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * 属性geRoleAuthorities的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRole")
	public List<GeRoleAuthority> getGeRoleAuthorities() {
		return this.geRoleAuthorities;
	}

	/**
	 * 属性geRoleAuthorities的setter方法
	 */
	public void setGeRoleAuthorities(List<GeRoleAuthority> geRoleAuthorities) {
		this.geRoleAuthorities = geRoleAuthorities;
	}

	/**
	 * 属性geDeptGroupRoles的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRole")
	public List<GeDeptGroupRole> getGeDeptGroupRoles() {
		return this.geDeptGroupRoles;
	}

	/**
	 * 属性geDeptGroupRoles的setter方法
	 */
	public void setGeDeptGroupRoles(List<GeDeptGroupRole> geDeptGroupRoles) {
		this.geDeptGroupRoles = geDeptGroupRoles;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
    public Date getCreatetime() {
		return createtime;
	}
    
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
