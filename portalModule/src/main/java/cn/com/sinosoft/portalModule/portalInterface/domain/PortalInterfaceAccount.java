package cn.com.sinosoft.portalModule.portalInterface.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.UserStatus;

/**
 * POJO类AppSystem
 */
@Entity
@Table(name = "PORTAL_INTERFACE_ACCOUNT")
public class PortalInterfaceAccount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性登录名 */
	private String loginName;

	/** 属性登录密码 */
	private String password;

	/** 属性账户状态 */
	private Integer status;

	/** 属性IP地址 */
	private String ipAddress;

	/** 属性端口*/
	private String port;
	
	/** 属性备注 */
	private String remark;

	/** 属性操作员 */
	private String operatorID;
	
	/** 属性接口交互系统 */
	private PortalInterfaceSystem portalInterfaceSystem;
	
	/** 属性接口 */
	private PortalInterface portalInterface;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类PortalInterfaceSystem的默认构造方法
	 */
	public PortalInterfaceAccount() {
		
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	
	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * 属性接口状态的getter方法
	 */

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 属性接口状态枚举类的getter方法
	 */
	@Transient
	public UserStatus getEnumStatus() {
		if (getStatus() == null) {
			return null;
		}
		UserStatus status = (UserStatus) EnumDataUtils
				.getEnumDictionaryByValue(UserStatus.class, getStatus());
		return status;
	}

	/**
	 * 属性接口状态核心值的getter方法
	 */
	@Transient
	public String getStatusByCoreValue() {
		if (getStatus() == null) {
			return "";
		}
		UserStatus status = (UserStatus) EnumDataUtils
				.getEnumDictionaryByValue(UserStatus.class, getStatus());
		return status.getCoreValue();
	}

	/**
	 * 属性接口状态商家值的getter方法
	 */
	@Transient
	public String getStatusByMerchantValue() {
		if (getStatus() == null) {
			return "";
		}
		UserStatus status = (UserStatus) EnumDataUtils
				.getEnumDictionaryByValue(UserStatus.class, getStatus());
		return status.getMerchantValue();
	}

	/**
	 * 属性接口状态的setter方法
	 */
	public void setStatus(Integer Status) {
		this.status = Status;
	}

	/**
	 * 属性接口状态赋值
	 */
	public void setEnumStatus(UserStatus status) {
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * 属性核心接口状态赋值
	 */
	public void setStatusByCoreValue(String coreValue) {
		UserStatus status = (UserStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(UserStatus.class, coreValue);
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * 属性商家接口状态赋值
	 */
	public void setStatusByMerchantValue(String merchantValue) {
		UserStatus status = (UserStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(UserStatus.class,
						merchantValue);
		if (status != null) {
			this.status = status.getValue();
		}
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
	 * 属性端口的getter方法
	 */

	@Column(name = "PORT")
	public String getPort() {
		return this.port;
	}

	/**
	 * 属性端口的setter方法
	 */
	public void setPort(String port) {
		this.port = port;
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
	 * 属性操作员的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作员的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	
	/**
	 * 属性接口交互系统的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSTEMSERIALNO")
	public PortalInterfaceSystem getPortalInterfaceSystem() {
		return portalInterfaceSystem;
	}
	
	/**
	 * 属性接口交互系统的setter方法
	 */
	public void setPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		this.portalInterfaceSystem = portalInterfaceSystem;
		if (getPortalInterfaceSystem() != null && !getPortalInterfaceSystem().getPortalInterfaceAccounts().contains(this)) {
			getPortalInterfaceSystem().getPortalInterfaceAccounts().add(this);
		}
	}

	/**
	 * 属性接口的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERFACESERIALNO")
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}
	
	/**
	 * 属性接口的getter方法
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
		if (getPortalInterface() != null && !getPortalInterface().getPortalInterfaceAccounts().contains(this)) {
			getPortalInterface().getPortalInterfaceAccounts().add(this);
		}
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
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
