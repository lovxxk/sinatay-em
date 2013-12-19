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
	

	/** 属性序号 */
	private String serialNo;

	/** 属性系统名称 */
	private String systemName;
	
	/** 属性报文类型 */
	private Integer messageType;
	
	/** 属性交易报文 */
	private String transactionMessage;
	
	/** 属性请求处理状态 */
	private Integer requestProcessStatus;
	
	/** 属性请求处理状态名称 */
	private String requestProcessStatusName;
	
	/** 属性请求处理状态描述 */
	private String requestProcessStatusDesc;
	
	/** 交易信息 */
	private PortalTransaction portalTransaction;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 交易轨迹
	 */
	public PortalTransactionMessage() {
		
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
	 * 属性请求处理状态的getter方法
	 */

	@Column(name = "PROCESSSTATUS")
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
	 * 属性报文类型的getter方法
	 */
	@Column(name = "MESSAGETYPE")
	public Integer getMessageType() {
		return messageType;
	}

	/**
	 * 属性报文类型的setter方法
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	/**
	 * 属性交易报文的getter方法
	 */
	@Column(name = "TRANSACTIONMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getTransactionMessage() {
		return transactionMessage;
	}

	/**
	 * 属性交易报文的setter方法
	 */
	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	/**
	 * 属性请求处理状态名称的getter方法
	 */
	@Column(name = "PROCESSSTATUSNAME")
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
	@Column(name = "PROCESSSTATUSDESC")
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
	 * 属性交易信息的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRANSACTIONSERIALNO")
	public PortalTransaction getPortalTransaction() {
		return portalTransaction;
	}

	/**
	 * 属性交易信息的setter方法
	 */
	public void setPortalTransaction(PortalTransaction portalTransaction) {
		this.portalTransaction = portalTransaction;
//		if (getPortalTransaction() != null && !getPortalTransaction().getPortalTransactionMessages().contains(this)) {
//			getPortalTransaction().getPortalTransactionMessages().add(this);
//		}
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
