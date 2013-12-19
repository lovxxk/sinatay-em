package cn.com.sinosoft.portalModule.portalInterface.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.SystemType;

/**
 * POJO类AppSystem
 */
@Entity
@Table(name = "PORTAL_INTERFACE_SYSTEM")
public class PortalInterfaceSystem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性系统代码 */
	private String systemCode;

	/** 属性系统名称 */
	private String systemName;

	/** 属性系统简称 */
	private String systemSimpleName;

	/** 属性系统类型 */
	private Integer systemType;

	/** 属性系统描述 */
	private String systemDesc;

	/** 属性系统简介 */
	private String systemSummary;

	/** 属性操作员 */
	private String operatorID;

	/** 属性接口账号信息 */
	private List<PortalInterfaceAccount> portalInterfaceAccounts = new ArrayList<PortalInterfaceAccount>(0);
	
	/** 属性接口账号信息 */
	private PortalInterface portalInterface;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类PortalInterfaceSystem的默认构造方法
	 */
	public PortalInterfaceSystem() {
		
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
	 * 属性系统代码的getter方法
	 */

	@Column(name = "SYSTEMCODE")
	public String getSystemCode() {
		return this.systemCode;
	}

	/**
	 * 属性系统代码的setter方法
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * 属性系统名称的getter方法
	 */

	@Column(name = "SYSTEMNAME")
	public String getSystemName() {
		return this.systemName;
	}

	/**
	 * 属性系统名称的setter方法
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * 属性系统简称的getter方法
	 */

	@Column(name = "SYSTEMSIMPLENAME")
	public String getSystemSimpleName() {
		return this.systemSimpleName;
	}

	/**
	 * 属性系统简称的setter方法
	 */
	public void setSystemSimpleName(String systemSimpleName) {
		this.systemSimpleName = systemSimpleName;
	}

	/**
	 * 属性系统类型的getter方法
	 */

	@Column(name = "SYSTEMTYPE")
	public Integer getSystemType() {
		return this.systemType;
	}

	/**
	 * 属性系统类型枚举类的getter方法
	 */
	@Transient
	public SystemType getEnumSystemType() {
		if (getSystemType() == null) {
			return null;
		}
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByValue(SystemType.class, getSystemType());
		return systemType;
	}

	/**
	 * 属性系统类型核心值的getter方法
	 */
	@Transient
	public String getSystemTypeByCoreValue() {
		if (getSystemType() == null) {
			return "";
		}
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByValue(SystemType.class, getSystemType());
		return systemType.getCoreValue();
	}

	/**
	 * 属性系统类型商家值的getter方法
	 */
	@Transient
	public String getSystemTypeByMerchantValue() {
		if (getSystemType() == null) {
			return "";
		}
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByValue(SystemType.class, getSystemType());
		return systemType.getMerchantValue();
	}

	/**
	 * 属性系统类型的setter方法
	 */
	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	/**
	 * 属性系统类型赋值
	 */
	public void setEnumSystemType(SystemType systemType) {
		if (systemType != null) {
			this.systemType = systemType.getValue();
		}
	}

	/**
	 * 属性加密类型赋值
	 */
	public void setSystemTypeByCoreValue(String coreValue) {
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByCoreValue(SystemType.class, coreValue);
		if (systemType != null) {
			this.systemType = systemType.getValue();
		}
	}

	/**
	 * 属性商家系统类型赋值
	 */
	public void setSystemTypeByMerchantValue(String merchantValue) {
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByMerchantValue(SystemType.class, merchantValue);
		if (systemType != null) {
			this.systemType = systemType.getValue();
		}
	}

	/**
	 * 属性系统描述的getter方法
	 */

	@Column(name = "SYSTEMDESC")
	public String getSystemDesc() {
		return this.systemDesc;
	}

	/**
	 * 属性系统描述的setter方法
	 */
	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	/**
	 * 属性系统简介的getter方法
	 */

	@Column(name = "SYSTEMSUMMARY")
	public String getSystemSummary() {
		return this.systemSummary;
	}

	/**
	 * 属性系统简介的setter方法
	 */
	public void setSystemSummary(String systemSummary) {
		this.systemSummary = systemSummary;
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
	 * 属性接口账号信息的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceSystem")
	public List<PortalInterfaceAccount> getPortalInterfaceAccounts() {
		return portalInterfaceAccounts;
	}
	
	/**
	 * 属性接口账号信息的setter方法
	 */
	public void setPortalInterfaceAccounts(
			List<PortalInterfaceAccount> portalInterfaceAccounts) {
		this.portalInterfaceAccounts = portalInterfaceAccounts;
	}
	/**
	 * 属性添加所有接口账号信息
	 */
	public void addPortalInterfaceAccounts(List<PortalInterfaceAccount> portalInterfaceAccounts) {
		
		for (PortalInterfaceAccount portalInterfaceAccount:portalInterfaceAccounts) {
			if (!getPortalInterfaceAccounts().contains(portalInterfaceAccount)) {
				getPortalInterfaceAccounts().add(portalInterfaceAccount);
			}
		}
		
		for (PortalInterfaceAccount portalInterfaceAccount:getPortalInterfaceAccounts()) {
			if (portalInterfaceAccount.getPortalInterfaceSystem() == null) {
				portalInterfaceAccount.setPortalInterfaceSystem(this);
			}
			
		}
	}
	
	/**
	 * 属性接口的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceSystem")
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}
	
	/**
	 * 属性接口的getter方法
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
		if (getPortalInterface() != null && getPortalInterface().getPortalInterfaceSystem() == null) {
			getPortalInterface().setPortalInterfaceSystem(this);
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
