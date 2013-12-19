package cn.com.sinosoft.ebusiness.common.bizConfig.domain;

// default package
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeDeptMail
 */
@Entity
@Table(name = "GE_DEPT_MAIL")
public class GeDeptMail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	// 手机
	private String mobile;
	
	
	
	/** 属性机构邮箱配置ID */
	private String deptMailID;

//	/** 属性机构表-GE_Department */
//	private GeDepartment geDepartment;
	/** 属性机构编号 */
	private String deptID;

	/** 属性适用功能ID */
	private String functionID;

	/** 属性邮箱 */
	private String mail;

	/** 属性创建者 */
	private String creater;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性修改者 */
	private String updater;

	/** 属性修改时间 */
	private Date updateTime;

	/** 属性备注 */
	private String remark;

	/** 属性是否有效标志 */
	private String validInd;
	@Transient
	/** 属性所在省 */
	private String provinceCode;
	@Transient
	/** 属性所在市 */
	private String cityCode;
	@Transient
	/**机构名称*/
	private String departNmae;
	@Transient
	/**邮箱发送环节名称*/
	private String sendMailName;
	/**
	 * 类GeDeptMail的默认构造方法
	 */
	public GeDeptMail() {
	}
	
	/**
	 * 属性机构邮箱配置ID的getter方法
	 */
	@Id
	@Column(name = "DEPTMAILID", unique = true,nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getDeptMailID() {
		return this.deptMailID;
	}

	/**
	 * 属性机构邮箱配置ID的setter方法
	 */
	public void setDeptMailID(String deptMailID) {
		this.deptMailID = deptMailID;
	}

//	/**
//	 * 属性机构表-GE_Department的getter方法
//	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "DEPTID", nullable = false)
//	public GeDepartment getGeDepartment() {
//		return this.geDepartment;
//	}
//
//	/**
//	 * 属性机构表-GE_Department的setter方法
//	 */
//	public void setGeDepartment(GeDepartment geDepartment) {
//		this.geDepartment = geDepartment;
//	}
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
	 * 属性适用功能ID的getter方法
	 */

	@Column(name = "FUNCTIONID")
	public String getFunctionID() {
		return this.functionID;
	}

	/**
	 * 属性适用功能ID的setter方法
	 */
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	/**
	 * 属性邮箱的getter方法
	 */

	@Column(name = "MAIL")
	public String getMail() {
		return this.mail;
	}

	/**
	 * 属性邮箱的setter方法
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 属性创建者的getter方法
	 */

	@Column(name = "CREATER")
	public String getCreater() {
		return this.creater;
	}

	/**
	 * 属性创建者的setter方法
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性修改者的getter方法
	 */

	@Column(name = "UPDATER")
	public String getUpdater() {
		return this.updater;
	}

	/**
	 * 属性修改者的setter方法
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	/**
	 * 属性修改时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性修改时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性是否有效标志的getter方法
	 */

	@Column(name = "VALIDIND")
	public String getValidInd() {
		return this.validInd;
	}

	/**
	 * 属性是否有效标志的setter方法
	 */
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	@Transient
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	@Transient
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Transient
	public String getDepartNmae() {
		return departNmae;
	}

	public void setDepartNmae(String departNmae) {
		this.departNmae = departNmae;
	}
	@Transient
	public String getSendMailName() {
		return sendMailName;
	}

	public void setSendMailName(String sendMailName) {
		this.sendMailName = sendMailName;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
