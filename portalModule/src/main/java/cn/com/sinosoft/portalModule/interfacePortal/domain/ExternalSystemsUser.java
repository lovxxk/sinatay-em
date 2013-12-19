package cn.com.sinosoft.portalModule.interfacePortal.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类ExternalSystemsUser
 */
@Entity
@Table(name = "GE_PORTAL_EXSYSUSER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LOGINNAME", "IPADDRESS"}))
public class ExternalSystemsUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	
	/** 属性esbauthbranchno */
	private String esbauthbranchno;

	/** 属性esbsignature */
	private String esbsignature;
	
	/** 属性逻辑主键 */
	private String id;

	/** 属性externalSysInfo */
	private ExternalSysInfo externalSysInfo;

	/** 属性登录名 */
	private String loginName;

	/** 属性登录密码 */
	private String password;

	/** 属性用户IP地址 */
	private String ipAddress;

	/** 属性账户状态 */
	private String status;

	/** 属性创建时间 */
	private Date createDate;

	/** 属性更新时间 */
	private Date updateDate;

	/** 属性备注 */
	private String remark;

	/** 属性externalSysUserInterfaceInfos */
	private List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfos = new ArrayList<ExternalSysUserInterfaceInfo>(
			0);

	/**
	 * 类ExternalSystemsUser的默认构造方法
	 */
	public ExternalSystemsUser() {
	}

	/**
	 * 属性逻辑主键的getter方法
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}
	/**
	 * 属性逻辑主键的setter方法
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 属性externalSysInfo的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXTERNALSYSID", nullable = false)
	public ExternalSysInfo getExternalSysInfo() {
		return this.externalSysInfo;
	}
	/**
	 * 属性externalSysInfo的setter方法
	 */
	public void setExternalSysInfo(ExternalSysInfo externalSysInfo) {
		this.externalSysInfo = externalSysInfo;
	}
	/**
	 * 属性登录名的getter方法
	 */

	@Column(name = "LOGINNAME")
	public String getLoginName() {
		return this.loginName;
	}
	/**
	 * 属性登录名的setter方法
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 属性登录密码的getter方法
	 */

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}
	/**
	 * 属性登录密码的setter方法
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 属性用户IP地址的getter方法
	 */

	@Column(name = "IPADDRESS")
	public String getIpAddress() {
		return this.ipAddress;
	}
	/**
	 * 属性用户IP地址的setter方法
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * 属性账户状态的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}
	/**
	 * 属性账户状态的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * 属性externalSysUserInterfaceInfos的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSystemsUser")
	public List<ExternalSysUserInterfaceInfo> getExternalSysUserInterfaceInfos() {
		return this.externalSysUserInterfaceInfos;
	}
	/**
	 * 属性externalSysUserInterfaceInfos的setter方法
	 */
	public void setExternalSysUserInterfaceInfos(
			List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfos) {
		this.externalSysUserInterfaceInfos = externalSysUserInterfaceInfos;
	}
	
	/**
	 * 属性esbauthbranchno的getter方法
	 */

	@Column(name = "ESBAUTHBRANCHNO")
	public String getEsbauthbranchno() {
		return this.esbauthbranchno;
	}

	/**
	 * 属性esbauthbranchno的setter方法
	 */
	public void setEsbauthbranchno(String esbauthbranchno) {
		this.esbauthbranchno = esbauthbranchno;
	}

	/**
	 * 属性esbsignature的getter方法
	 */

	@Column(name = "ESBSIGNATURE")
	public String getEsbsignature() {
		return this.esbsignature;
	}

	/**
	 * 属性esbsignature的setter方法
	 */
	public void setEsbsignature(String esbsignature) {
		this.esbsignature = esbsignature;
	}


}
