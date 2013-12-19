package cn.com.sinosoft.portalModule.portalTransaction.domain;

import java.util.Date;

import javax.persistence.Basic;
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
import org.hibernate.annotations.Type;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;

@Entity
@Table(name = "PORTALTRANSACTIONMESSAGE")
public class PortalTransactionMessage implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5781018205265710345L;
	

	/** ������� */
	private String serialNo;

	/** ����ϵͳ���� */
	private String systemName;
	
	/** ���Ա������� */
	private Integer messageType;
	
	/** ���Խ��ױ��� */
	private String transactionMessage;
	
	/** ����������״̬ */
	private Integer requestProcessStatus;
	
	/** ����������״̬���� */
	private String requestProcessStatusName;
	
	/** ����������״̬���� */
	private String requestProcessStatusDesc;
	
	/** ������Ϣ */
	private PortalTransaction portalTransaction;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ���׹켣
	 */
	public PortalTransactionMessage() {
		
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
	 * ����������״̬��getter����
	 */

	@Column(name = "PROCESSSTATUS")
	public Integer getRequestProcessStatus() {
		return this.requestProcessStatus;
	}

	/**
	 * ����������״̬ö�����getter����
	 */
	@Transient
	public RequestProcessStatus getEnumRequestProcessStatus() {
		if (getRequestProcessStatus() == null) {
			return null;
		}
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByValue(RequestProcessStatus.class, getRequestProcessStatus());
		return requestProcessStatus;
	}

	/**
	 * ����������״̬����ֵ��getter����
	 */
	@Transient
	public String getRequestProcessStatusByCoreValue() {
		if (getRequestProcessStatus() == null) {
			return "";
		}
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByValue(RequestProcessStatus.class, getRequestProcessStatus());
		return requestProcessStatus.getCoreValue();
	}

	/**
	 * ����������״̬�̼�ֵ��getter����
	 */
	@Transient
	public String getRequestProcessStatusByMerchantValue() {
		if (getRequestProcessStatus() == null) {
			return "";
		}
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByValue(RequestProcessStatus.class, getRequestProcessStatus());
		return requestProcessStatus.getMerchantValue();
	}

	/**
	 * ����������״̬��setter����
	 */
	public void setRequestProcessStatus(Integer requestProcessStatus) {
		this.requestProcessStatus = requestProcessStatus;
	}

	/**
	 * ����������״̬��ֵ
	 */
	public void setEnumRequestProcessStatus(RequestProcessStatus requestProcessStatus) {
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * ���Լ������͸�ֵ
	 */
	public void setRequestProcessStatusByCoreValue(String coreValue) {
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByCoreValue(RequestProcessStatus.class, coreValue);
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * �����̼�������״̬��ֵ
	 */
	public void setRequestProcessStatusByMerchantValue(String merchantValue) {
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(RequestProcessStatus.class, merchantValue);
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
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
	 * ���Ա������͵�getter����
	 */
	@Column(name = "MESSAGETYPE")
	public Integer getMessageType() {
		return messageType;
	}

	/**
	 * ���Ա������͵�setter����
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	/**
	 * ���Խ��ױ��ĵ�getter����
	 */
	@Column(name = "TRANSACTIONMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getTransactionMessage() {
		return transactionMessage;
	}

	/**
	 * ���Խ��ױ��ĵ�setter����
	 */
	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	/**
	 * ����������״̬���Ƶ�getter����
	 */
	@Column(name = "PROCESSSTATUSNAME")
	public String getRequestProcessStatusName() {
		return requestProcessStatusName;
	}

	/**
	 * ����������״̬���Ƶ�setter����
	 */
	public void setRequestProcessStatusName(String requestProcessStatusName) {
		this.requestProcessStatusName = requestProcessStatusName;
	}
	
	/**
	 * ����������״̬������getter����
	 */
	@Column(name = "PROCESSSTATUSDESC")
	public String getRequestProcessStatusDesc() {
		return requestProcessStatusDesc;
	}

	/**
	 * ����������״̬������setter����
	 */
	public void setRequestProcessStatusDesc(String requestProcessStatusDesc) {
		this.requestProcessStatusDesc = requestProcessStatusDesc;
	}
	
	/**
	 * ���Խ�����Ϣ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACTIONSERIALNO")
	public PortalTransaction getPortalTransaction() {
		return portalTransaction;
	}

	/**
	 * ���Խ�����Ϣ��setter����
	 */
	public void setPortalTransaction(PortalTransaction portalTransaction) {
		this.portalTransaction = portalTransaction;
//		if (getPortalTransaction() != null && !getPortalTransaction().getPortalTransactionMessages().contains(this)) {
//			getPortalTransaction().getPortalTransactionMessages().add(this);
//		}
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
