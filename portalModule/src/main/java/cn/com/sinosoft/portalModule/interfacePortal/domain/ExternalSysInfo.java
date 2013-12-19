package cn.com.sinosoft.portalModule.interfacePortal.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransactionMessage;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO类ExternalSysInfo
 */
@Entity
@Table(name = "GE_PORTAL_EXSYSINFO")
public class ExternalSysInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性外部系统ID */
	private String externalSysId;

	/** 属性外部系统名称 */
	private String externalSysName;

	/** 属性外部系统类型 */
	private String externalSysType;

	/** 属性备注 */
	private String remark;

	/** 属性externalSystemsUsers */
	private List<ExternalSystemsUser> externalSystemsUsers = new ArrayList<ExternalSystemsUser>(
			0);

	/** 属性transactionMessages */
	private List<TransactionMessage> transactionMessages = new ArrayList<TransactionMessage>(
			0);

	/** 属性clientUsers */
	private List<ClientUser> clientUsers = new ArrayList<ClientUser>(0);

	/**
	 * 类ExternalSysInfo的默认构造方法
	 */
	public ExternalSysInfo() {
	}

	/**
	 * 属性外部系统ID的getter方法
	 */
	@Id
	@Column(name = "EXTERNALSYSID")
	public String getExternalSysId() {
		return this.externalSysId;
	}
	/**
	 * 属性外部系统ID的setter方法
	 */
	public void setExternalSysId(String externalSysId) {
		this.externalSysId = externalSysId;
	}
	/**
	 * 属性外部系统名称的getter方法
	 */

	@Column(name = "EXTERNALSYSNAME")
	public String getExternalSysName() {
		return this.externalSysName;
	}
	/**
	 * 属性外部系统名称的setter方法
	 */
	public void setExternalSysName(String externalSysName) {
		this.externalSysName = externalSysName;
	}
	/**
	 * 属性外部系统类型的getter方法
	 */

	@Column(name = "EXTERNALSYSTYPE")
	public String getExternalSysType() {
		return this.externalSysType;
	}
	/**
	 * 属性外部系统类型的setter方法
	 */
	public void setExternalSysType(String externalSysType) {
		this.externalSysType = externalSysType;
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
	 * 属性externalSystemsUsers的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSysInfo")
	public List<ExternalSystemsUser> getExternalSystemsUsers() {
		return this.externalSystemsUsers;
	}
	/**
	 * 属性externalSystemsUsers的setter方法
	 */
	public void setExternalSystemsUsers(
			List<ExternalSystemsUser> externalSystemsUsers) {
		this.externalSystemsUsers = externalSystemsUsers;
	}
	/**
	 * 属性transactionMessages的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSysInfo")
	public List<TransactionMessage> getTransactionMessages() {
		return this.transactionMessages;
	}
	/**
	 * 属性transactionMessages的setter方法
	 */
	public void setTransactionMessages(
			List<TransactionMessage> transactionMessages) {
		this.transactionMessages = transactionMessages;
	}
	/**
	 * 属性clientUsers的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSysInfo")
	public List<ClientUser> getClientUsers() {
		return this.clientUsers;
	}
	/**
	 * 属性clientUsers的setter方法
	 */
	public void setClientUsers(List<ClientUser> clientUsers) {
		this.clientUsers = clientUsers;
	}

}
