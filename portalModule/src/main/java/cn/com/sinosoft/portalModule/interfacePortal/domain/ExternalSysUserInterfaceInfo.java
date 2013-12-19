package cn.com.sinosoft.portalModule.interfacePortal.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类ExternalSysUserInterfaceInfo
 */
@Entity
@Table(name = "GE_PORTAL_EXSYSUSER_INTERFACE")
public class ExternalSysUserInterfaceInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性逻辑主键 */
	private String id;

	/** 属性externalSystemsUser */
	private ExternalSystemsUser externalSystemsUser;

	/** 属性interfaceInfo */
	private InterfaceInfo interfaceInfo;

	/**
	 * 类ExternalSysUserInterfaceInfo的默认构造方法
	 */
	public ExternalSysUserInterfaceInfo() {
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
	 * 属性externalSystemsUser的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXSYSUSERID", nullable = false)
	public ExternalSystemsUser getExternalSystemsUser() {
		return this.externalSystemsUser;
	}
	/**
	 * 属性externalSystemsUser的setter方法
	 */
	public void setExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		this.externalSystemsUser = externalSystemsUser;
	}
	/**
	 * 属性interfaceInfo的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSCODE", nullable = false)
	public InterfaceInfo getInterfaceInfo() {
		return this.interfaceInfo;
	}
	/**
	 * 属性interfaceInfo的setter方法
	 */
	public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

}
