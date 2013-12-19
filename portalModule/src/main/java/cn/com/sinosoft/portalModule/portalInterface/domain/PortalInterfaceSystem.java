package cn.com.sinosoft.portalModule.portalInterface.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��AppSystem
 */
@Entity
@Table(name = "PORTAL_INTERFACE_SYSTEM")
public class PortalInterfaceSystem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����ϵͳ���� */
	private String systemCode;

	/** ����ϵͳ���� */
	private String systemName;

	/** ����ϵͳ��� */
	private String systemSimpleName;

	/** ����ϵͳ���� */
	private Integer systemType;

	/** ����ϵͳ���� */
	private String systemDesc;

	/** ����ϵͳ��� */
	private String systemSummary;

	/** ���Բ���Ա */
	private String operatorID;

	/** ���Խӿ��˺���Ϣ */
	private List<PortalInterfaceAccount> portalInterfaceAccounts = new ArrayList<PortalInterfaceAccount>(0);
	
	/** ���Խӿ��˺���Ϣ */
	private PortalInterface portalInterface;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��PortalInterfaceSystem��Ĭ�Ϲ��췽��
	 */
	public PortalInterfaceSystem() {
		
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
	 * ����ϵͳ�����getter����
	 */

	@Column(name = "SYSTEMCODE")
	public String getSystemCode() {
		return this.systemCode;
	}

	/**
	 * ����ϵͳ�����setter����
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * ����ϵͳ���Ƶ�getter����
	 */

	@Column(name = "SYSTEMNAME")
	public String getSystemName() {
		return this.systemName;
	}

	/**
	 * ����ϵͳ���Ƶ�setter����
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * ����ϵͳ��Ƶ�getter����
	 */

	@Column(name = "SYSTEMSIMPLENAME")
	public String getSystemSimpleName() {
		return this.systemSimpleName;
	}

	/**
	 * ����ϵͳ��Ƶ�setter����
	 */
	public void setSystemSimpleName(String systemSimpleName) {
		this.systemSimpleName = systemSimpleName;
	}

	/**
	 * ����ϵͳ���͵�getter����
	 */

	@Column(name = "SYSTEMTYPE")
	public Integer getSystemType() {
		return this.systemType;
	}

	/**
	 * ����ϵͳ����ö�����getter����
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
	 * ����ϵͳ���ͺ���ֵ��getter����
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
	 * ����ϵͳ�����̼�ֵ��getter����
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
	 * ����ϵͳ���͵�setter����
	 */
	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	/**
	 * ����ϵͳ���͸�ֵ
	 */
	public void setEnumSystemType(SystemType systemType) {
		if (systemType != null) {
			this.systemType = systemType.getValue();
		}
	}

	/**
	 * ���Լ������͸�ֵ
	 */
	public void setSystemTypeByCoreValue(String coreValue) {
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByCoreValue(SystemType.class, coreValue);
		if (systemType != null) {
			this.systemType = systemType.getValue();
		}
	}

	/**
	 * �����̼�ϵͳ���͸�ֵ
	 */
	public void setSystemTypeByMerchantValue(String merchantValue) {
		SystemType systemType = (SystemType) EnumDataUtils.getEnumDictionaryByMerchantValue(SystemType.class, merchantValue);
		if (systemType != null) {
			this.systemType = systemType.getValue();
		}
	}

	/**
	 * ����ϵͳ������getter����
	 */

	@Column(name = "SYSTEMDESC")
	public String getSystemDesc() {
		return this.systemDesc;
	}

	/**
	 * ����ϵͳ������setter����
	 */
	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	/**
	 * ����ϵͳ����getter����
	 */

	@Column(name = "SYSTEMSUMMARY")
	public String getSystemSummary() {
		return this.systemSummary;
	}

	/**
	 * ����ϵͳ����setter����
	 */
	public void setSystemSummary(String systemSummary) {
		this.systemSummary = systemSummary;
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
	 * ���Խӿ��˺���Ϣ��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceSystem")
	public List<PortalInterfaceAccount> getPortalInterfaceAccounts() {
		return portalInterfaceAccounts;
	}
	
	/**
	 * ���Խӿ��˺���Ϣ��setter����
	 */
	public void setPortalInterfaceAccounts(
			List<PortalInterfaceAccount> portalInterfaceAccounts) {
		this.portalInterfaceAccounts = portalInterfaceAccounts;
	}
	/**
	 * ����������нӿ��˺���Ϣ
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
	 * ���Խӿڵ�getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterfaceSystem")
	public PortalInterface getPortalInterface() {
		return portalInterface;
	}
	
	/**
	 * ���Խӿڵ�getter����
	 */
	public void setPortalInterface(PortalInterface portalInterface) {
		this.portalInterface = portalInterface;
		if (getPortalInterface() != null && getPortalInterface().getPortalInterfaceSystem() == null) {
			getPortalInterface().setPortalInterfaceSystem(this);
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
