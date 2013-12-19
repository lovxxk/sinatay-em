package cn.com.sinosoft.portalModule.interfacePortal.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��ExternalSysInfo
 */
@Entity
@Table(name = "GE_PORTAL_EXSYSINFO")
public class ExternalSysInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����ⲿϵͳID */
	private String externalSysId;

	/** �����ⲿϵͳ���� */
	private String externalSysName;

	/** �����ⲿϵͳ���� */
	private String externalSysType;

	/** ���Ա�ע */
	private String remark;

	/** ����externalSystemsUsers */
	private List<ExternalSystemsUser> externalSystemsUsers = new ArrayList<ExternalSystemsUser>(
			0);

	/** ����transactionMessages */
	private List<TransactionMessage> transactionMessages = new ArrayList<TransactionMessage>(
			0);

	/** ����clientUsers */
	private List<ClientUser> clientUsers = new ArrayList<ClientUser>(0);

	/**
	 * ��ExternalSysInfo��Ĭ�Ϲ��췽��
	 */
	public ExternalSysInfo() {
	}

	/**
	 * �����ⲿϵͳID��getter����
	 */
	@Id
	@Column(name = "EXTERNALSYSID")
	public String getExternalSysId() {
		return this.externalSysId;
	}
	/**
	 * �����ⲿϵͳID��setter����
	 */
	public void setExternalSysId(String externalSysId) {
		this.externalSysId = externalSysId;
	}
	/**
	 * �����ⲿϵͳ���Ƶ�getter����
	 */

	@Column(name = "EXTERNALSYSNAME")
	public String getExternalSysName() {
		return this.externalSysName;
	}
	/**
	 * �����ⲿϵͳ���Ƶ�setter����
	 */
	public void setExternalSysName(String externalSysName) {
		this.externalSysName = externalSysName;
	}
	/**
	 * �����ⲿϵͳ���͵�getter����
	 */

	@Column(name = "EXTERNALSYSTYPE")
	public String getExternalSysType() {
		return this.externalSysType;
	}
	/**
	 * �����ⲿϵͳ���͵�setter����
	 */
	public void setExternalSysType(String externalSysType) {
		this.externalSysType = externalSysType;
	}
	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	/**
	 * ���Ա�ע��setter����
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * ����externalSystemsUsers��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSysInfo")
	public List<ExternalSystemsUser> getExternalSystemsUsers() {
		return this.externalSystemsUsers;
	}
	/**
	 * ����externalSystemsUsers��setter����
	 */
	public void setExternalSystemsUsers(
			List<ExternalSystemsUser> externalSystemsUsers) {
		this.externalSystemsUsers = externalSystemsUsers;
	}
	/**
	 * ����transactionMessages��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSysInfo")
	public List<TransactionMessage> getTransactionMessages() {
		return this.transactionMessages;
	}
	/**
	 * ����transactionMessages��setter����
	 */
	public void setTransactionMessages(
			List<TransactionMessage> transactionMessages) {
		this.transactionMessages = transactionMessages;
	}
	/**
	 * ����clientUsers��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "externalSysInfo")
	public List<ClientUser> getClientUsers() {
		return this.clientUsers;
	}
	/**
	 * ����clientUsers��setter����
	 */
	public void setClientUsers(List<ClientUser> clientUsers) {
		this.clientUsers = clientUsers;
	}

}
