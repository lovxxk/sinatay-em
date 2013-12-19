package cn.com.sinosoft.ebusiness.sys.permission.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO类GeGroup
 */
@Entity
@Table(name = "GE_GROUP")
public class GeGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性用户组编号 */
	private String groupid;

	/** 属性组名称 */
	private String groupname;

	/** 属性组描述 */
	private String groupdesp;

	/** 属性机构编号 */
	private String deptid;

	/** 属性grouptypeid */
	private String grouptypeid;
	
	/** 属性grouptypename */
	private String grouptypename;
	
	/** 属性机构名称 */
	private String deptname;
	
	/** 属性创建时间*/
	private Date createtime;
	
	/** 属性更新时间*/
	private Date updatetime;
	
	@Transient
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/** 属性geOperators */
	private List<GeOperator> geOperators = new ArrayList<GeOperator>(0);

	/** 属性geDeptGroupRoles */
	private List<GeDeptGroupRole> geDeptGroupRoles = new ArrayList<GeDeptGroupRole>(
			0);

	/**
	 * 类GeGroup的默认构造方法
	 */
	public GeGroup() {
	}

	/**
	 * 属性用户组编号的getter方法
	 */
	@Id
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
	 * 属性组名称的getter方法
	 */

	@Column(name = "GROUPNAME")
	public String getGroupname() {
		return this.groupname;
	}

	/**
	 * 属性组名称的setter方法
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	/**
	 * 属性组描述的getter方法
	 */

	@Column(name = "GROUPDESP")
	public String getGroupdesp() {
		return this.groupdesp;
	}

	/**
	 * 属性组描述的setter方法
	 */
	public void setGroupdesp(String groupdesp) {
		this.groupdesp = groupdesp;
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

	/**
	 * 属性grouptypeid的getter方法
	 */

	@Column(name = "GROUPTYPEID")
	public String getGrouptypeid() {
		return this.grouptypeid;
	}

	/**
	 * 属性grouptypeid的setter方法
	 */
	public void setGrouptypeid(String grouptypeid) {
		this.grouptypeid = grouptypeid;
	}

	/**
	 * 属性geOperators的getter方法
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "GE_GROUP_USER", joinColumns = { @JoinColumn(name = "GROUPID", referencedColumnName = "GROUPID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "OPERATORID", referencedColumnName = "OPERATORID", nullable = false, updatable = false) })
	public List<GeOperator> getGeOperators() {
		return this.geOperators;
	}

	/**
	 * 属性geOperators的setter方法
	 */
	public void setGeOperators(List<GeOperator> geOperators) {
		this.geOperators = geOperators;
	}

	/**
	 * 属性geDeptGroupRoles的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geGroup")
	public List<GeDeptGroupRole> getGeDeptGroupRoles() {
		return this.geDeptGroupRoles;
	}

	/**
	 * 属性geDeptGroupRoles的setter方法
	 */
	public void setGeDeptGroupRoles(List<GeDeptGroupRole> geDeptGroupRoles) {
		this.geDeptGroupRoles = geDeptGroupRoles;
	}
	/**
	 * 属性grouptypename的getter方法
	 */

	@Column(name = "GROUPTYPENAME")
	public String getGrouptypename() {
		return this.grouptypename;
	}

	/**
	 * 属性grouptypename的setter方法
	 */
	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}
	
	/**
	 * 属性createtime的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 属性createtime的setter方法
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 属性updatetime的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * 属性updatetime的setter方法
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
