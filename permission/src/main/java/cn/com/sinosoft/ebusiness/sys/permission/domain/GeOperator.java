package cn.com.sinosoft.ebusiness.sys.permission.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.TemporalType;

/**
 * POJO��GeOperator
 */
@Entity
@Table(name = "GE_OPERATOR")
public class GeOperator implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Բ����˱�� */
	private String operatorid;

	/** ���Ի������ */
	private String deptid;

	/** ���Ե�¼���� */
	private String pwd;

	/** ���Բ���Ա���� */
	private String operatorname;

	/** �����Ա� */
	private String sex;

	/** ������ϵ�绰 */
	private String phone;

	/** ����ҵ������ */
	private String businessarea;

	/** ���Ե������� */
	private String email;

	/** ����״̬ */
	private String state;

	/** ���Ա�ʶλ */
	private String flag;

	/** ����geGroups */
	private List<GeGroup> geGroups = new ArrayList<GeGroup>(0);
	
	private String groupid;
	
	private String deptname;
	
	private Date createtime;
	
	@Transient
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Transient
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	/**
	 * ��GeOperator��Ĭ�Ϲ��췽��
	 */
	public GeOperator() {
	}

	/**
	 * ���Բ����˱�ŵ�getter����
	 */
	@Id
	@Column(name = "OPERATORID")
	public String getOperatorid() {
		return this.operatorid;
	}

	/**
	 * ���Բ����˱�ŵ�setter����
	 */
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
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
	 * ���Ե�¼�����getter����
	 */

	@Column(name = "PWD")
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * ���Ե�¼�����setter����
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * ���Բ���Ա������getter����
	 */

	@Column(name = "OPERATORNAME")
	public String getOperatorname() {
		return this.operatorname;
	}

	/**
	 * ���Բ���Ա������setter����
	 */
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	/**
	 * �����Ա��getter����
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * �����Ա��setter����
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * ������ϵ�绰��getter����
	 */

	@Column(name = "PHONE")
	public String getPhone() {
		return this.phone;
	}

	/**
	 * ������ϵ�绰��setter����
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ����ҵ�������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessarea() {
		return this.businessarea;
	}

	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessarea(String businessarea) {
		this.businessarea = businessarea;
	}

	/**
	 * ���Ե��������getter����
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * ���Ե��������setter����
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ����״̬��getter����
	 */

	@Column(name = "STATE")
	public String getState() {
		return this.state;
	}

	/**
	 * ����״̬��setter����
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ����geGroups��getter����
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "geOperators")
	public List<GeGroup> getGeGroups() {
		return this.geGroups;
	}

	/**
	 * ����geGroups��setter����
	 */
	public void setGeGroups(List<GeGroup> geGroups) {
		this.geGroups = geGroups;
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
