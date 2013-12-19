package cn.com.sinosoft.portalModule.portalInterface.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��AppSystem
 */
@Entity
@Table(name = "PORTAL_INTERFACE_ACCOUNT")
public class PortalInterfaceAccount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Ե�¼�� */
	private String loginName;

	/** ���Ե�¼���� */
	private String password;

	/** �����˻�״̬ */
	private Integer status;

	/** ����IP��ַ */
	private String ipAddress;

	/** ���Զ˿�*/
	private String port;
	
	/** ���Ա�ע */
	private String remark;

	/** ���Բ���Ա */
	private String operatorID;
	
	/** ���Խӿڽ���ϵͳ */
	private PortalInterfaceSystem portalInterfaceSystem;
	
	/** ���Խӿ� */
	private PortalInterface portalInterface;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��PortalInterfaceSystem��Ĭ�Ϲ��췽��
	 */
	public PortalInterfaceAccount() {
		
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}
	
	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ���Ե�¼����getter����
	 */

	@Column(name = "LOGINNAME")
	public String getLoginName() {
		return this.loginName;
	}
	
	/**
	 * ���Ե�¼����setter����
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/**
	 * ���Ե�¼�����getter����
	 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * ���Ե�¼�����setter����
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * ���Խӿ�״̬��getter����
	 */

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * ���Խӿ�״̬ö�����getter����
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
	 * ���Խӿ�״̬����ֵ��getter����
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
	 * ���Խӿ�״̬�̼�ֵ��getter����
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
	 * ���Խӿ�״̬��setter����
	 */
	public void setStatus(Integer Status) {
		this.status = Status;
	}

	/**
	 * ���Խӿ�״̬��ֵ
	 */
	public void setEnumStatus(UserStatus status) {
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * ���Ժ��Ľӿ�״̬��ֵ
	 */
	public void setStatusByCoreValue(String coreValue) {
		UserStatus status = (UserStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(UserStatus.class, coreValue);
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * �����̼ҽӿ�״̬��ֵ
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
	 * �����û�IP��ַ��getter����
	 */

	@Column(name = "IPADDRESS")
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * �����û�IP��ַ��setter����
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	/**
	 * ���Զ˿ڵ�getter����
	 */

	@Column(name = "PORT")
	public String getPort() {
		return this.port;
	}

	/**
	 * ���Զ˿ڵ�setter����
	 */
	public void setPort(String port) {
		this.port = port;
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
	 * ���Բ���Ա��getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ���Ա��setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	
	/**
	 * ���Խӿڽ���ϵͳ��getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSTEMSERIALNO")
	public PortalInterfaceSystem getPortalInterfaceSystem() {
		return portalInterfaceSystem;
	}
	
	/**
	 * ���Խӿڽ���ϵͳ��setter����
	 */
	public void setPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		this.portalInterfaceSystem = portalInterfaceSystem;
		if (getPortalInterfaceSystem() != null && !getPortalInterfaceSystem().getPortalInterfaceAccounts().contains(this)) {
			getPortalInterfaceSystem().getPortalInterfaceAccounts().add(this);
		}
	}

	/**
	 * ���Խӿڵ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTERFACESERIALNO")
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}
	
	/**
	 * ���Խӿڵ�getter����
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
		if (getPortalInterface() != null && !getPortalInterface().getPortalInterfaceAccounts().contains(this)) {
			getPortalInterface().getPortalInterfaceAccounts().add(this);
		}
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
