package cn.com.sinosoft.portalModule.portalTransaction.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;

@Entity
@Table(name = "TRANSACTIONTRACK")
public class TransactionTrack implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5781018205265710345L;
	

	/** ������� */
	private String serialNo;

	/** ���Կͻ�ID */
	private String customerID;

	/** ���Զ����� */
	private String orderSerialNumber;
	
	/** ������Զ��������ַ */
	private String requestURL;
	
	/** ��������ʽ */
	private Integer requestMethod;
	
	/** ���Ա��뼯 */
	private String encoded;

	/** ��������SessionID*/
	private String requestSessionID;
	
	/** ���Է����� */
	private String serviceName;

	/** �����߳�ID */
	private String threadID;
	
	/** ����Session Id */
	private String sessionID;
	
	/** ����cookieֵ */
	private String cookieValue;
	
	/** ��������ʱ�� */
	private Date requestTime;
	
	/** ����Ӧ��ʱ�� */
	private Date responseTime;
	
	/** ����Զ���������� */
	private String remoteHost;
	
	/** ����Զ��������ַ */
	private String remoteAddr;
	
	/** ����Զ�������˿� */
	private Integer remotePort;
	
	/** ���Ա��ػ������� */
	private String localName;
	
	/** ���Ա��ػ�����ַ */
	private String localAddr;
	
	/** ���Ա��ػ����˿� */
	private Integer localPort;
	
	/** ����������״̬ */
	private Integer requestProcessStatus;
	
	/** ����������״̬���� */
	private String requestProcessStatusName;
	
	/** ����������״̬���� */
	private String requestProcessStatusDesc;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ���׹켣
	 */
	public TransactionTrack() {
		
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
	 * �����û��ŵ�getter����
	 */
	@Column(name = "CUSTOMERID")
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	
	/**
	 * ���Զ����ŵ�getter����
	 */

	@Column(name = "ORDERSERIALNUMBER")
	public String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	/**
	 * ���Զ����ŵ�setter����
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}

	/**
	 * ���������ַ��getter����
	 */
	@Column(name = "REQUESTURL")
	public String getRequestURL() {
		return requestURL;
	}
	
	/**
	 * ���������ַ��setter����
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	/**
	 * �������󷽷���getter����
	 */
	@Column(name = "REQUESTMETHOD")
	public Integer getRequestMethod() {
		return requestMethod;
	}

	/**
	 * �������󷽷���setter����
	 */
	public void setRequestMethod(Integer requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	/**
	 * ���Ա��뼯��getter����
	 */
	@Column(name = "ENCODED")
	public String getEncoded() {
		return encoded;
	}

	/**
	 * ���Ա��뼯��setter����
	 */
	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}

	/**
	 * ��������SessionID��getter����
	 */
	@Column(name = "REQUESTSESSIONID")
	public String getRequestSessionID() {
		return requestSessionID;
	}

	/**
	 * ��������SessionID��setter����
	 */
	public void setRequestSessionID(String requestSessionID) {
		this.requestSessionID = requestSessionID;
	}

	/**
	 * ���Է������Ƶ�getter����
	 */
	@Column(name = "SERVICENAME")
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * ���Է������Ƶ�setter����
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * �����߳�ID��getter����
	 */
	@Column(name = "THREADID")
	public String getThreadID() {
		return threadID;
	}

	/**
	 * �����߳�ID��setter����
	 */
	public void setThreadID(String threadID) {
		this.threadID = threadID;
	}

	/**
	 * ����session ID��getter����
	 */
	@Column(name = "SESSIONID")
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * ����session ID��setter����
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	/**
	 * ����cookieֵ��getter����
	 */
	@Column(name = "COOKIEVALUE")
	public String getCookieValue() {
		return cookieValue;
	}
	/**
	 * ����cookieֵ��setter����
	 */
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}

	/**
	 * ��������ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUESTTIME")
	public Date getRequestTime() {
		return requestTime;
	}
	
	/**
	 * ��������ʱ���setter����
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * ����Ӧ��ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESPONSETIME")
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * ����Ӧ��ʱ���setter����
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * ����Զ������������getter����
	 */

	@Column(name = "REMOTEHOST")
	public String getRemoteHost() {
		return this.remoteHost;
	}

	/**
	 * ����Զ������������setter����
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/**
	 * ����Զ������IP��ַ��getter����
	 */

	@Column(name = "REMOTEADDR")
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	/**
	 * ����Զ������IP��ַ��setter����
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * ����Զ�������˿ڵ�getter����
	 */

	@Column(name = "REMOTEPORT")
	public Integer getRemotePort() {
		return this.remotePort;
	}

	/**
	 * ����Զ�������˿ڵ�setter����
	 */
	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}


	/**
	 * ���Ա������Ƶ�getter����
	 */

	@Column(name = "LOCALNAME")
	public String getLocalName() {
		return this.localName;
	}

	/**
	 * ���Ա������Ƶ�setter����
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	/**
	 * ���Ա��ص�ַ��getter����
	 */

	@Column(name = "LOCALADDR")
	public String getLocalAddr() {
		return this.localAddr;
	}

	/**
	 * ���Ա��ص�ַ��setter����
	 */
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	/**
	 * ���Ա��ض˿ڵ�getter����
	 */
	@Column(name = "LOCALPORT")
	public Integer getLocalPort() {
		return this.localPort;
	}

	/**
	 * ���Ա��ض˿ڵ�setter����
	 */
	public void setLocalPort(Integer localPort) {
		this.localPort = localPort;
	}
	
	/**
	 * ����������״̬��getter����
	 */

	@Column(name = "REQUESTPROCESSSTATUS")
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
	 * ����������״̬���Ƶ�getter����
	 */
	@Column(name = "REQUESTPROCESSSTATUSNAME")
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
	@Column(name = "REQUESTPROCESSSTATUSDESC")
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
