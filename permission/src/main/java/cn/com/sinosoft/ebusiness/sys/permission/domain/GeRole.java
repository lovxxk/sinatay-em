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
 * POJO��GeRole
 */
@Entity
@Table(name = "GE_ROLE")
public class GeRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Խ�ɫ��� */
	private String roleID;

	/** ���Խ�ɫ���� */
	private String roleName;

	/** ���Խ�ɫ���� */
	private String roleDesp;

	/** ���Ի������ */
	private String deptID;
	
	private String deptName;
	
	private String status;
	
	/** ����ʱ��*/
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

	/** ����geRoleAuthorities */
	private List<GeRoleAuthority> geRoleAuthorities = new ArrayList<GeRoleAuthority>(
			0);

	/** ����geDeptGroupRoles */
	private List<GeDeptGroupRole> geDeptGroupRoles = new ArrayList<GeDeptGroupRole>(
			0);

	/**
	 * ��GeRole��Ĭ�Ϲ��췽��
	 */
	public GeRole() {
	}

	/**
	 * ���Խ�ɫ��ŵ�getter����
	 */
	@Id
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
	 * ���Խ�ɫ���Ƶ�getter����
	 */

	@Column(name = "ROLENAME")
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * ���Խ�ɫ���Ƶ�setter����
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * ���Խ�ɫ������getter����
	 */

	@Column(name = "ROLEDESP")
	public String getRoleDesp() {
		return this.roleDesp;
	}

	/**
	 * ���Խ�ɫ������setter����
	 */
	public void setRoleDesp(String roleDesp) {
		this.roleDesp = roleDesp;
	}

	/**
	 * ���Ի�����ŵ�getter����
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}

	/**
	 * ���Ի�����ŵ�setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * ����geRoleAuthorities��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRole")
	public List<GeRoleAuthority> getGeRoleAuthorities() {
		return this.geRoleAuthorities;
	}

	/**
	 * ����geRoleAuthorities��setter����
	 */
	public void setGeRoleAuthorities(List<GeRoleAuthority> geRoleAuthorities) {
		this.geRoleAuthorities = geRoleAuthorities;
	}

	/**
	 * ����geDeptGroupRoles��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geRole")
	public List<GeDeptGroupRole> getGeDeptGroupRoles() {
		return this.geDeptGroupRoles;
	}

	/**
	 * ����geDeptGroupRoles��setter����
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
