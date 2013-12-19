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
 * POJO类GeOperator
 */
@Entity
@Table(name = "GE_OPERATOR")
public class GeOperator implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性操作人编号 */
	private String operatorid;

	/** 属性机构编号 */
	private String deptid;

	/** 属性登录密码 */
	private String pwd;

	/** 属性操作员姓名 */
	private String operatorname;

	/** 属性性别 */
	private String sex;

	/** 属性联系电话 */
	private String phone;

	/** 属性业务领域 */
	private String businessarea;

	/** 属性电子邮箱 */
	private String email;

	/** 属性状态 */
	private String state;

	/** 属性标识位 */
	private String flag;

	/** 属性geGroups */
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
	 * 类GeOperator的默认构造方法
	 */
	public GeOperator() {
	}

	/**
	 * 属性操作人编号的getter方法
	 */
	@Id
	@Column(name = "OPERATORID")
	public String getOperatorid() {
		return this.operatorid;
	}

	/**
	 * 属性操作人编号的setter方法
	 */
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
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
	 * 属性登录密码的getter方法
	 */

	@Column(name = "PWD")
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * 属性登录密码的setter方法
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 属性操作员姓名的getter方法
	 */

	@Column(name = "OPERATORNAME")
	public String getOperatorname() {
		return this.operatorname;
	}

	/**
	 * 属性操作员姓名的setter方法
	 */
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	/**
	 * 属性性别的getter方法
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * 属性性别的setter方法
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 属性联系电话的getter方法
	 */

	@Column(name = "PHONE")
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 属性联系电话的setter方法
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 属性业务领域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessarea() {
		return this.businessarea;
	}

	/**
	 * 属性业务领域的setter方法
	 */
	public void setBusinessarea(String businessarea) {
		this.businessarea = businessarea;
	}

	/**
	 * 属性电子邮箱的getter方法
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * 属性电子邮箱的setter方法
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 属性状态的getter方法
	 */

	@Column(name = "STATE")
	public String getState() {
		return this.state;
	}

	/**
	 * 属性状态的setter方法
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性geGroups的getter方法
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "geOperators")
	public List<GeGroup> getGeGroups() {
		return this.geGroups;
	}

	/**
	 * 属性geGroups的setter方法
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
