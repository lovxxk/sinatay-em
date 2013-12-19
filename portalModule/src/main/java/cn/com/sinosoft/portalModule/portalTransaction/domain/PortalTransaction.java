package cn.com.sinosoft.portalModule.portalTransaction.domain;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
@Entity
@Table(name = "PORTALTRANSACTION")
public class PortalTransaction implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5781018205265710345L;
	

	/** 属性序号 */
	private String serialNo;

	/** 属性交易代码 */
	private String transCode;
	
	/** 属性批量处理ID */
	private String batchID;

	/** 属性交易标示 */
	private String transIdentify;
	
	/** 属性交易流水号 */
	private String transSerialNumber;
	
	/** 属性商家交易流水号 */
	private String merchantTransSerialNumber;
	
	/** 属性订单号 */
	private String orderSerialNumber;
	
	/** 属性合作机构订单号（淘宝订单号） */
	private String merchantOrderNumber;
	
	/** 属性请服务器地址 */
	private String requestURL;
	
	/** 属性回调地址 */
	private String callbackURL;
	
	/** 属性请求时间 */
	private Date requestTime;
	
	/** 属性应答时间 */
	private Date responseTime;
	
	/** 属性请求处理状态 */
	private Integer requestProcessStatus;
	
	/** 属性请求处理状态名称 */
	private String requestProcessStatusName;
	
	/** 属性请求处理状态描述 */
	private String requestProcessStatusDesc;
	
	/** 属性交易报文 */
	private List<PortalTransactionMessage> portalTransactionMessages = new ArrayList<PortalTransactionMessage>(0);
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 交易信息
	 */
	public PortalTransaction() {
		
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
	 * 属性交易代码的getter方法
	 */
	@Column(name = "TRANSCODE")
	public String getTransCode() {
		return this.transCode;
	}
	
	/**
	 * 属性交易代码的setter方法
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	/**
	 * 属性批量处理ID的getter方法
	 */
	@Column(name = "BATCHID")
	public String getBatchID() {
		return batchID;
	}
	
	/**
	 * 属性批量处理ID的setter方法
	 */
	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}
	
	/**
	 * 属性交易标识码的getter方法
	 */
	@Column(name = "TRANSIDENTIFY")
	public String getTransIdentify() {
		return transIdentify;
	}

	/**
	 * 属性交易标识码的getter方法
	 */
	public void setTransIdentify(String transIdentify) {
		this.transIdentify = transIdentify;
	}

	/**
	 * 属性交易流水号的getter方法
	 */
	@Column(name = "TRANSSERIALNUMBER")
	public String getTransSerialNumber() {
		return this.transSerialNumber;
	}

	/**
	 * 属性交易流水号的setter方法
	 */
	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}
	
	/**
	 * 属性商家交易流水号的getter方法
	 */
	@Column(name = "MERCHANTTRANSSERIALNUMBER")
	public String getMerchantTransSerialNumber() {
		return merchantTransSerialNumber;
	}
	
	/**
	 * 属性商家交易流水号的setter方法
	 */
	public void setMerchantTransSerialNumber(String merchantTransSerialNumber) {
		this.merchantTransSerialNumber = merchantTransSerialNumber;
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
	 * 属性合作机构订单号的getter方法
	 */
	@Column(name = "MERCHANTORDERNUMBER")
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * 属性合作机构订单号的setter方法
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
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
	 * 属性回调地址的getter方法
	 */
	@Column(name = "CALLBACKURL")
	public String getCallbackURL() {
		return callbackURL;
	}
	
	/**
	 * 属性回调地址的setter方法
	 */
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
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
	 * 属性交易报文的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalTransaction")
	public List<PortalTransactionMessage> getPortalTransactionMessages() {
		return portalTransactionMessages;
	}
	
	/**
	 * 属性交易报文的setter方法
	 */
	public void setPortalTransactionMessages(
			List<PortalTransactionMessage> portalTransactionMessages) {
		this.portalTransactionMessages = portalTransactionMessages;
	}

	/**
	 * 属性添加所有交易报文
	 */
	public void addPortalTransactionMessages(List<PortalTransactionMessage> portalTransactionMessages) {
		
		for (PortalTransactionMessage portalTransactionMessage:portalTransactionMessages) {
			if (!getPortalTransactionMessages().contains(portalTransactionMessage)) {
				getPortalTransactionMessages().add(portalTransactionMessage);
			}
		}
		
		for (PortalTransactionMessage portalTransactionMessage:getPortalTransactionMessages()) {
			if (portalTransactionMessage.getTransactionMessage() == null) {
				portalTransactionMessage.setPortalTransaction(this);
			}
			
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
