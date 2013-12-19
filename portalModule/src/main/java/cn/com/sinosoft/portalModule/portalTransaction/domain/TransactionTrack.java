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
	

	/** 属性序号 */
	private String serialNo;

	/** 属性客户ID */
	private String customerID;

	/** 属性订单号 */
	private String orderSerialNumber;
	
	/** 属性请远程主机地址 */
	private String requestURL;
	
	/** 属性请求方式 */
	private Integer requestMethod;
	
	/** 属性编码集 */
	private String encoded;

	/** 属性请求SessionID*/
	private String requestSessionID;
	
	/** 属性服务名 */
	private String serviceName;

	/** 属性线程ID */
	private String threadID;
	
	/** 属性Session Id */
	private String sessionID;
	
	/** 属性cookie值 */
	private String cookieValue;
	
	/** 属性请求时间 */
	private Date requestTime;
	
	/** 属性应答时间 */
	private Date responseTime;
	
	/** 属性远程主机名称 */
	private String remoteHost;
	
	/** 属性远程主机地址 */
	private String remoteAddr;
	
	/** 属性远程主机端口 */
	private Integer remotePort;
	
	/** 属性本地机器名称 */
	private String localName;
	
	/** 属性本地机器地址 */
	private String localAddr;
	
	/** 属性本地机器端口 */
	private Integer localPort;
	
	/** 属性请求处理状态 */
	private Integer requestProcessStatus;
	
	/** 属性请求处理状态名称 */
	private String requestProcessStatusName;
	
	/** 属性请求处理状态描述 */
	private String requestProcessStatusDesc;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 交易轨迹
	 */
	public TransactionTrack() {
		
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
	 * 属性用户号的getter方法
	 */
	@Column(name = "CUSTOMERID")
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	
	/**
	 * 属性订单号的getter方法
	 */

	@Column(name = "ORDERSERIALNUMBER")
	public String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	/**
	 * 属性订单号的setter方法
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}

	/**
	 * 属性请求地址的getter方法
	 */
	@Column(name = "REQUESTURL")
	public String getRequestURL() {
		return requestURL;
	}
	
	/**
	 * 属性请求地址的setter方法
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	/**
	 * 属性请求方法的getter方法
	 */
	@Column(name = "REQUESTMETHOD")
	public Integer getRequestMethod() {
		return requestMethod;
	}

	/**
	 * 属性请求方法的setter方法
	 */
	public void setRequestMethod(Integer requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	/**
	 * 属性编码集的getter方法
	 */
	@Column(name = "ENCODED")
	public String getEncoded() {
		return encoded;
	}

	/**
	 * 属性编码集的setter方法
	 */
	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}

	/**
	 * 属性请求SessionID的getter方法
	 */
	@Column(name = "REQUESTSESSIONID")
	public String getRequestSessionID() {
		return requestSessionID;
	}

	/**
	 * 属性请求SessionID的setter方法
	 */
	public void setRequestSessionID(String requestSessionID) {
		this.requestSessionID = requestSessionID;
	}

	/**
	 * 属性服务名称的getter方法
	 */
	@Column(name = "SERVICENAME")
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * 属性服务名称的setter方法
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * 属性线程ID的getter方法
	 */
	@Column(name = "THREADID")
	public String getThreadID() {
		return threadID;
	}

	/**
	 * 属性线程ID的setter方法
	 */
	public void setThreadID(String threadID) {
		this.threadID = threadID;
	}

	/**
	 * 属性session ID的getter方法
	 */
	@Column(name = "SESSIONID")
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * 属性session ID的setter方法
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	/**
	 * 属性cookie值的getter方法
	 */
	@Column(name = "COOKIEVALUE")
	public String getCookieValue() {
		return cookieValue;
	}
	/**
	 * 属性cookie值的setter方法
	 */
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}

	/**
	 * 属性请求时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUESTTIME")
	public Date getRequestTime() {
		return requestTime;
	}
	
	/**
	 * 属性请求时间的setter方法
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * 属性应答时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESPONSETIME")
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * 属性应答时间的setter方法
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * 属性远程主机主机的getter方法
	 */

	@Column(name = "REMOTEHOST")
	public String getRemoteHost() {
		return this.remoteHost;
	}

	/**
	 * 属性远程主机主机的setter方法
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/**
	 * 属性远程主机IP地址的getter方法
	 */

	@Column(name = "REMOTEADDR")
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	/**
	 * 属性远程主机IP地址的setter方法
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * 属性远程主机端口的getter方法
	 */

	@Column(name = "REMOTEPORT")
	public Integer getRemotePort() {
		return this.remotePort;
	}

	/**
	 * 属性远程主机端口的setter方法
	 */
	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}


	/**
	 * 属性本地名称的getter方法
	 */

	@Column(name = "LOCALNAME")
	public String getLocalName() {
		return this.localName;
	}

	/**
	 * 属性本地名称的setter方法
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	/**
	 * 属性本地地址的getter方法
	 */

	@Column(name = "LOCALADDR")
	public String getLocalAddr() {
		return this.localAddr;
	}

	/**
	 * 属性本地地址的setter方法
	 */
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	/**
	 * 属性本地端口的getter方法
	 */
	@Column(name = "LOCALPORT")
	public Integer getLocalPort() {
		return this.localPort;
	}

	/**
	 * 属性本地端口的setter方法
	 */
	public void setLocalPort(Integer localPort) {
		this.localPort = localPort;
	}
	
	/**
	 * 属性请求处理状态的getter方法
	 */

	@Column(name = "REQUESTPROCESSSTATUS")
	public Integer getRequestProcessStatus() {
		return this.requestProcessStatus;
	}

	/**
	 * 属性请求处理状态枚举类的getter方法
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
	 * 属性请求处理状态核心值的getter方法
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
	 * 属性请求处理状态商家值的getter方法
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
	 * 属性请求处理状态的setter方法
	 */
	public void setRequestProcessStatus(Integer requestProcessStatus) {
		this.requestProcessStatus = requestProcessStatus;
	}

	/**
	 * 属性请求处理状态赋值
	 */
	public void setEnumRequestProcessStatus(RequestProcessStatus requestProcessStatus) {
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * 属性加密类型赋值
	 */
	public void setRequestProcessStatusByCoreValue(String coreValue) {
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByCoreValue(RequestProcessStatus.class, coreValue);
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * 属性商家请求处理状态赋值
	 */
	public void setRequestProcessStatusByMerchantValue(String merchantValue) {
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(RequestProcessStatus.class, merchantValue);
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * 属性请求处理状态名称的getter方法
	 */
	@Column(name = "REQUESTPROCESSSTATUSNAME")
	public String getRequestProcessStatusName() {
		return requestProcessStatusName;
	}

	/**
	 * 属性请求处理状态名称的setter方法
	 */
	public void setRequestProcessStatusName(String requestProcessStatusName) {
		this.requestProcessStatusName = requestProcessStatusName;
	}
	
	/**
	 * 属性请求处理状态描述的getter方法
	 */
	@Column(name = "REQUESTPROCESSSTATUSDESC")
	public String getRequestProcessStatusDesc() {
		return requestProcessStatusDesc;
	}

	/**
	 * 属性请求处理状态描述的setter方法
	 */
	public void setRequestProcessStatusDesc(String requestProcessStatusDesc) {
		this.requestProcessStatusDesc = requestProcessStatusDesc;
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
