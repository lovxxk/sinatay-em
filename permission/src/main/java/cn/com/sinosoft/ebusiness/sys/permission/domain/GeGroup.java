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
 * POJO��GeGroup
 */
@Entity
@Table(name = "GE_GROUP")
public class GeGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����û����� */
	private String groupid;

	/** ���������� */
	private String groupname;

	/** ���������� */
	private String groupdesp;

	/** ���Ի������ */
	private String deptid;

	/** ����grouptypeid */
	private String grouptypeid;
	
	/** ����grouptypename */
	private String grouptypename;
	
	/** ���Ի������� */
	private String deptname;
	
	/** ���Դ���ʱ��*/
	private Date createtime;
	
	/** ���Ը���ʱ��*/
	private Date updatetime;
	
	@Transient
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/** ����geOperators */
	private List<GeOperator> geOperators = new ArrayList<GeOperator>(0);

	/** ����geDeptGroupRoles */
	private List<GeDeptGroupRole> geDeptGroupRoles = new ArrayList<GeDeptGroupRole>(
			0);

	/**
	 * ��GeGroup��Ĭ�Ϲ��췽��
	 */
	public GeGroup() {
	}

	/**
	 * �����û����ŵ�getter����
	 */
	@Id
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
	 * ���������Ƶ�getter����
	 */

	@Column(name = "GROUPNAME")
	public String getGroupname() {
		return this.groupname;
	}

	/**
	 * ���������Ƶ�setter����
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	/**
	 * ������������getter����
	 */

	@Column(name = "GROUPDESP")
	public String getGroupdesp() {
		return this.groupdesp;
	}

	/**
	 * ������������setter����
	 */
	public void setGroupdesp(String groupdesp) {
		this.groupdesp = groupdesp;
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

	/**
	 * ����grouptypeid��getter����
	 */

	@Column(name = "GROUPTYPEID")
	public String getGrouptypeid() {
		return this.grouptypeid;
	}

	/**
	 * ����grouptypeid��setter����
	 */
	public void setGrouptypeid(String grouptypeid) {
		this.grouptypeid = grouptypeid;
	}

	/**
	 * ����geOperators��getter����
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "GE_GROUP_USER", joinColumns = { @JoinColumn(name = "GROUPID", referencedColumnName = "GROUPID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "OPERATORID", referencedColumnName = "OPERATORID", nullable = false, updatable = false) })
	public List<GeOperator> getGeOperators() {
		return this.geOperators;
	}

	/**
	 * ����geOperators��setter����
	 */
	public void setGeOperators(List<GeOperator> geOperators) {
		this.geOperators = geOperators;
	}

	/**
	 * ����geDeptGroupRoles��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geGroup")
	public List<GeDeptGroupRole> getGeDeptGroupRoles() {
		return this.geDeptGroupRoles;
	}

	/**
	 * ����geDeptGroupRoles��setter����
	 */
	public void setGeDeptGroupRoles(List<GeDeptGroupRole> geDeptGroupRoles) {
		this.geDeptGroupRoles = geDeptGroupRoles;
	}
	/**
	 * ����grouptypename��getter����
	 */

	@Column(name = "GROUPTYPENAME")
	public String getGrouptypename() {
		return this.grouptypename;
	}

	/**
	 * ����grouptypename��setter����
	 */
	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}
	
	/**
	 * ����createtime��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * ����createtime��setter����
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * ����updatetime��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * ����updatetime��setter����
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
