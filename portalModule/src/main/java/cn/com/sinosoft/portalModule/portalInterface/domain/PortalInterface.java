package cn.com.sinosoft.portalModule.portalInterface.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.BooleanStatus;
import cn.com.sinosoft.portalModule.enumUtil.EncapsulationType;
import cn.com.sinosoft.portalModule.enumUtil.EncryptionType;
import cn.com.sinosoft.portalModule.enumUtil.InterfaceStatus;
import cn.com.sinosoft.portalModule.enumUtil.InterfaceType;
import cn.com.sinosoft.portalModule.enumUtil.RecordMessageType;
import cn.com.sinosoft.portalModule.enumUtil.ReturnType;

/**
 * POJO类AppSystem
 */
@Entity
@Table(name = "PORTAL_INTERFACE", uniqueConstraints = @UniqueConstraint(columnNames = "SYSTEMSERIALNO"))
public class PortalInterface implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性交易代码 */
	private String transCode;

	/** 属性交易名称 */
	private String transName;

	/** 属性数据传输类型 */
	private String transPortType;

	/** 属性接口类型 */
	private Integer interfaceType;

	/** 属性接口状态 */
	private Integer status = InterfaceStatus.ENABLED.getValue();

	/** 属性是发送方ID */
	private String senderId;

	/** 属性是接收方ID */
	private String receiverId;

	/** 属性请服务器地址 */
	private String requestURL;

	/** 属性请求编码集 */
	private String requestEcoding = "GBK";

	/** 属性回调地址 */
	private String callbackURL;

	/** 属性前缀 */
	private String prefix;

	/** 属性命名空间 */
	private String namespaceURI;

	/** 属性本地部分方法名 */
	private String localPart;

	/** 属性行动 */
	private String action;

	/** 属性设置从URL连接读数据 */
	private Integer doInput;

	/** 属性设置将数据写入URL连接 */
	private Integer doOutput;

	/** 属性连接超时时间  默认1分钟 */
	private Integer connectTimeOut = 1 * 60 * 1000;

	/** 属性读超时 默认1分钟 */
	private Integer readTimeOut = 1 * 60 * 1000;

	/** 属性报文封装类型 */
	private Integer encapsulationType;

	/** 属性报文返回类型 */
	private Integer returnType;

	/** 属性报文编码集 */
	private String messageEncoding = "GBK";

	/** 属性记录报文类型 */
	private Integer recordMessageType;

	/** 属性加密类型 */
	private Integer encryptionType;

	/** 属性报文处理类型 */
	private String handleMessageType;

	/** 属性操作员 */
	private String operatorID;

	/** 属性接口交互系统 */
	private PortalInterfaceSystem portalInterfaceSystem;

	/** 属性接口处理类 */
	private PortalInterfaceHandlerClass portalInterfaceHandlerClass;

	/** 属性接口账号信息 */
	private List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters = new ArrayList<PortalInterfaceHandlerMethodParameter>(0);
	
	/** 属性接口账号信息 */
	private List<PortalInterfaceAccount> portalInterfaceAccounts = new ArrayList<PortalInterfaceAccount>(
			0);

	/** 属性接口账号信息 */
	private List<PortalInterfaceElement> portalInterfaceElements = new ArrayList<PortalInterfaceElement>(
			0);

	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 类PortalInterfaceSystem的默认构造方法
	 */
	public PortalInterface() {

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
	 * 属性交易名称的getter方法
	 */
	@Column(name = "TRANSNAME")
	public String getTransName() {
		return this.transName;
	}

	/**
	 * 属性交易名称的setter方法
	 */
	public void setTransName(String transName) {
		this.transName = transName;
	}

	/**
	 * 属性数据传输类型的getter方法
	 */
	@Column(name = "TRANSPORTTYPE")
	public String getTransPortType() {
		return transPortType;
	}

	/**
	 * 属性数据传输类型的setter方法
	 */
	public void setTransPortType(String transPortType) {
		this.transPortType = transPortType;
	}

	/**
	 * 属性接口类型的getter方法
	 */

	@Column(name = "INTERFACETYPE")
	public Integer getInterfaceType() {
		return this.interfaceType;
	}

	/**
	 * 属性接口类型枚举类的getter方法
	 */
	@Transient
	public InterfaceType getEnumInterfaceType() {
		if (getInterfaceType() == null) {
			return null;
		}
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceType.class,
						getInterfaceType());
		return interfaceType;
	}

	/**
	 * 属性接口类型核心值的getter方法
	 */
	@Transient
	public String getInterfaceTypeByCoreValue() {
		if (getInterfaceType() == null) {
			return "";
		}
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceType.class,
						getInterfaceType());
		return interfaceType.getCoreValue();
	}

	/**
	 * 属性接口类型商家值的getter方法
	 */
	@Transient
	public String getInterfaceTypeByMerchantValue() {
		if (getInterfaceType() == null) {
			return "";
		}
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceType.class,
						getInterfaceType());
		return interfaceType.getMerchantValue();
	}

	/**
	 * 属性接口类型的setter方法
	 */
	public void setInterfaceType(Integer InterfaceType) {
		this.interfaceType = InterfaceType;
	}

	/**
	 * 属性接口类型赋值
	 */
	public void setEnumInterfaceType(InterfaceType interfaceType) {
		if (interfaceType != null) {
			this.interfaceType = interfaceType.getValue();
		}
	}

	/**
	 * 属性核心接口类型赋值
	 */
	public void setInterfaceTypeByCoreValue(String coreValue) {
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByCoreValue(InterfaceType.class, coreValue);
		if (interfaceType != null) {
			this.interfaceType = interfaceType.getValue();
		}
	}

	/**
	 * 属性商家接口类型赋值
	 */
	public void setInterfaceTypeByMerchantValue(String merchantValue) {
		InterfaceType interfaceType = (InterfaceType) EnumDataUtils
				.getEnumDictionaryByMerchantValue(InterfaceType.class,
						merchantValue);
		if (interfaceType != null) {
			this.interfaceType = interfaceType.getValue();
		}
	}

	/**
	 * 属性接口状态的getter方法
	 */

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 属性接口状态枚举类的getter方法
	 */
	@Transient
	public InterfaceStatus getEnumStatus() {
		if (getStatus() == null) {
			return null;
		}
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceStatus.class, getStatus());
		return status;
	}

	/**
	 * 属性接口状态核心值的getter方法
	 */
	@Transient
	public String getStatusByCoreValue() {
		if (getStatus() == null) {
			return "";
		}
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceStatus.class, getStatus());
		return status.getCoreValue();
	}

	/**
	 * 属性接口状态商家值的getter方法
	 */
	@Transient
	public String getStatusByMerchantValue() {
		if (getStatus() == null) {
			return "";
		}
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByValue(InterfaceStatus.class, getStatus());
		return status.getMerchantValue();
	}

	/**
	 * 属性接口状态的setter方法
	 */
	public void setStatus(Integer Status) {
		this.status = Status;
	}

	/**
	 * 属性接口状态赋值
	 */
	public void setEnumStatus(InterfaceStatus status) {
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * 属性核心接口状态赋值
	 */
	public void setStatusByCoreValue(String coreValue) {
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(InterfaceStatus.class, coreValue);
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * 属性商家接口状态赋值
	 */
	public void setStatusByMerchantValue(String merchantValue) {
		InterfaceStatus status = (InterfaceStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(InterfaceStatus.class,
						merchantValue);
		if (status != null) {
			this.status = status.getValue();
		}
	}

	/**
	 * 属性发送方ID的getter方法
	 */
	@Column(name = "SENDERID")
	public String getSenderId() {
		return senderId;
	}

	/**
	 * 属性发送方ID的setter方法
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * 属性接收方ID的getter方法
	 */
	@Column(name = "RECEIVERID")
	public String getReceiverId() {
		return receiverId;
	}

	/**
	 * 属性接收方ID的setter方法
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
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
	 * 属性请求编码集的getter方法
	 */
	@Column(name = "REQUESTECODING")
	public String getRequestEcoding() {
		return requestEcoding;
	}

	/**
	 * 属性请求编码集的setter方法
	 */
	public void setRequestEcoding(String requestEcoding) {
		this.requestEcoding = requestEcoding;
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
	 * 属性前缀的getter方法
	 */
	@Column(name = "PREFIX")
	public String getPrefix() {
		return prefix;
	}

	/**
	 * 属性前缀的setter方法
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * 属性命名空间的getter方法
	 */
	@Column(name = "NAMESPACEURI")
	public String getNamespaceURI() {
		return namespaceURI;
	}

	/**
	 * 属性命名空间的setter方法
	 */
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	/**
	 * 属性本地部分方法名的getter方法
	 */
	@Column(name = "LOCALPART")
	public String getLocalPart() {
		return localPart;
	}

	/**
	 * 属性本地部分方法名的setter方法
	 */
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	/**
	 * 属性行动的getter方法
	 */
	@Column(name = "ACTION")
	public String getAction() {
		return action;
	}

	/**
	 * 属性行动的setter方法
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 属性设置从URL连接读数据的getter方法
	 */

	@Column(name = "DOINPUT")
	public Integer getDoInput() {
		return this.doInput;
	}

	/**
	 * 属性设置从URL连接读数据枚举类的getter方法
	 */
	@Transient
	public BooleanStatus getEnumDoInput() {
		if (getDoInput() == null) {
			return null;
		}
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getDoInput());
		return doInput;
	}

	/**
	 * 属性设置从URL连接读数据核心值的getter方法
	 */
	@Transient
	public String getDoInputByCoreValue() {
		if (getDoInput() == null) {
			return "";
		}
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getDoInput());
		return doInput.getCoreValue();
	}

	/**
	 * 属性设置从URL连接读数据商家值的getter方法
	 */
	@Transient
	public String getDoInputByMerchantValue() {
		if (getDoInput() == null) {
			return "";
		}
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getDoInput());
		return doInput.getMerchantValue();
	}

	/**
	 * 属性设置从URL连接读数据的setter方法
	 */
	public void setDoInput(Integer DoInput) {
		this.doInput = DoInput;
	}

	/**
	 * 属性设置从URL连接读数据赋值
	 */
	public void setEnumDoInput(BooleanStatus doInput) {
		if (doInput != null) {
			this.doInput = doInput.getValue();
		}
	}

	/**
	 * 属性核心设置从URL连接读数据赋值
	 */
	public void setDoInputByCoreValue(String coreValue) {
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (doInput != null) {
			this.doInput = doInput.getValue();
		}
	}

	/**
	 * 属性商家设置从URL连接读数据赋值
	 */
	public void setDoInputByMerchantValue(String merchantValue) {
		BooleanStatus doInput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(BooleanStatus.class,
						merchantValue);
		if (doInput != null) {
			this.doInput = doInput.getValue();
		}
	}

	/**
	 * 属性设置将数据写入URL连接的getter方法
	 */

	@Column(name = "DOOUTPUT")
	public Integer getdoOutput() {
		return this.doOutput;
	}

	/**
	 * 属性设置将数据写入URL连接枚举类的getter方法
	 */
	@Transient
	public BooleanStatus getEnumdoOutput() {
		if (getdoOutput() == null) {
			return null;
		}
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getdoOutput());
		return doOutput;
	}

	/**
	 * 属性设置将数据写入URL连接核心值的getter方法
	 */
	@Transient
	public String getdoOutputByCoreValue() {
		if (getdoOutput() == null) {
			return "";
		}
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getdoOutput());
		return doOutput.getCoreValue();
	}

	/**
	 * 属性设置将数据写入URL连接商家值的getter方法
	 */
	@Transient
	public String getdoOutputByMerchantValue() {
		if (getdoOutput() == null) {
			return "";
		}
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByValue(BooleanStatus.class, getdoOutput());
		return doOutput.getMerchantValue();
	}

	/**
	 * 属性设置将数据写入URL连接的setter方法
	 */
	public void setdoOutput(Integer doOutput) {
		this.doOutput = doOutput;
	}

	/**
	 * 属性设置将数据写入URL连接赋值
	 */
	public void setEnumdoOutput(BooleanStatus doOutput) {
		if (doOutput != null) {
			this.doOutput = doOutput.getValue();
		}
	}

	/**
	 * 属性核心设置将数据写入URL连接赋值
	 */
	public void setdoOutputByCoreValue(String coreValue) {
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (doOutput != null) {
			this.doOutput = doOutput.getValue();
		}
	}

	/**
	 * 属性商家设置将数据写入URL连接赋值
	 */
	public void setdoOutputByMerchantValue(String merchantValue) {
		BooleanStatus doOutput = (BooleanStatus) EnumDataUtils
				.getEnumDictionaryByMerchantValue(BooleanStatus.class,
						merchantValue);
		if (doOutput != null) {
			this.doOutput = doOutput.getValue();
		}
	}

	/**
	 * 属性连接超时时间getter方法
	 */
	@Column(name = "CONNECTTIMEOUT")
	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}

	/**
	 * 属性连接超时时间setter方法
	 */
	public void setConnectTimeOut(Integer connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	/**
	 * 属性读超时getter方法
	 */
	@Column(name = "READTIMEOUT")
	public Integer getReadTimeOut() {
		return readTimeOut;
	}

	/**
	 * 属性读超时setter方法
	 */
	public void setReadTimeOut(Integer readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	/**
	 * 属性封装类型的getter方法
	 */

	@Column(name = "ENCAPSULATIONTYPE")
	public Integer getEncapsulationType() {
		return this.encapsulationType;
	}

	/**
	 * 属性封装类型枚举类的getter方法
	 */
	@Transient
	public EncapsulationType getEnumEncapsulationType() {
		if (getEncapsulationType() == null) {
			return null;
		}
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByValue(EncapsulationType.class,
						getEncapsulationType());
		return encapsulationType;
	}

	/**
	 * 属性封装类型核心值的getter方法
	 */
	@Transient
	public String getEncapsulationTypeByCoreValue() {
		if (getEncapsulationType() == null) {
			return "";
		}
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByValue(EncapsulationType.class,
						getEncapsulationType());
		return encapsulationType.getCoreValue();
	}

	/**
	 * 属性封装类型商家值的getter方法
	 */
	@Transient
	public String getEncapsulationTypeByMerchantValue() {
		if (getEncapsulationType() == null) {
			return "";
		}
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByValue(EncapsulationType.class,
						getEncapsulationType());
		return encapsulationType.getMerchantValue();
	}

	/**
	 * 属性封装类型的setter方法
	 */
	public void setEncapsulationType(Integer encapsulationType) {
		this.encapsulationType = encapsulationType;
	}

	/**
	 * 属性封装类型赋值
	 */
	public void setEnumEncapsulationType(EncapsulationType encapsulationType) {
		if (encapsulationType != null) {
			this.encapsulationType = encapsulationType.getValue();
		}
	}

	/**
	 * 属性核心封装类型赋值
	 */
	public void setEncapsulationTypeByCoreValue(String coreValue) {
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByCoreValue(EncapsulationType.class,
						coreValue);
		if (encapsulationType != null) {
			this.encapsulationType = encapsulationType.getValue();
		}
	}

	/**
	 * 属性商家封装类型赋值
	 */
	public void setEncapsulationTypeByMerchantValue(String merchantValue) {
		EncapsulationType encapsulationType = (EncapsulationType) EnumDataUtils
				.getEnumDictionaryByMerchantValue(EncapsulationType.class,
						merchantValue);
		if (encapsulationType != null) {
			this.encapsulationType = encapsulationType.getValue();
		}
	}

	/**
	 * 属性返回类型的getter方法
	 */

	@Column(name = "RETURNTYPE")
	public Integer getReturnType() {
		return this.returnType;
	}

	/**
	 * 属性返回类型枚举类的getter方法
	 */
	@Transient
	public ReturnType getEnumReturnType() {
		if (getReturnType() == null) {
			return null;
		}
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByValue(ReturnType.class, getReturnType());
		return returnType;
	}

	/**
	 * 属性返回类型核心值的getter方法
	 */
	@Transient
	public String getReturnTypeByCoreValue() {
		if (getReturnType() == null) {
			return "";
		}
		ReturnType ReturnType = (ReturnType) EnumDataUtils.getEnumDictionaryByValue(ReturnType.class, getReturnType());
		return ReturnType.getCoreValue();
	}

	/**
	 * 属性返回类型商家值的getter方法
	 */
	@Transient
	public String getReturnTypeByMerchantValue() {
		if (getReturnType() == null) {
			return "";
		}
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByValue(ReturnType.class, getReturnType());
		return returnType.getMerchantValue();
	}

	/**
	 * 属性返回类型的setter方法
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	/**
	 * 属性返回类型赋值
	 */
	public void setEnumReturnType(ReturnType returnType) {
		if (returnType != null) {
			this.returnType = returnType.getValue();
		}
	}

	/**
	 * 属性核心返回类型赋值
	 */
	public void setReturnTypeByCoreValue(String coreValue) {
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByCoreValue(ReturnType.class, coreValue);
		if (returnType != null) {
			this.returnType = returnType.getValue();
		}
	}

	/**
	 * 属性商家返回类型赋值
	 */
	public void setReturnTypeByMerchantValue(String merchantValue) {
		ReturnType returnType = (ReturnType) EnumDataUtils.getEnumDictionaryByMerchantValue(ReturnType.class, merchantValue);
		if (returnType != null) {
			this.returnType = returnType.getValue();
		}
	}

	/**
	 * 属性报文编码集getter方法
	 */
	@Column(name = "MESSAGEENCODING")
	public String getMessageEncoding() {
		return messageEncoding;
	}

	/**
	 * 属性报文编码集setter方法
	 */
	public void setMessageEncoding(String messageEncoding) {
		this.messageEncoding = messageEncoding;
	}

	/**
	 * 属性记录报文类型的getter方法
	 */

	@Column(name = "RECORDMESSAGETYPE")
	public Integer getRecordMessageType() {
		return this.recordMessageType;
	}

	/**
	 * 属性记录报文类型枚举类的getter方法
	 */
	@Transient
	public RecordMessageType getEnumRecordMessageType() {
		if (getRecordMessageType() == null) {
			return null;
		}
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByValue(RecordMessageType.class, getRecordMessageType());
		return recordMessageType;
	}

	/**
	 * 属性记录报文类型核心值的getter方法
	 */
	@Transient
	public String getRecordMessageTypeByCoreValue() {
		if (getRecordMessageType() == null) {
			return "";
		}
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByValue(RecordMessageType.class, getRecordMessageType());
		return recordMessageType.getCoreValue();
	}

	/**
	 * 属性记录报文类型商家值的getter方法
	 */
	@Transient
	public String getRecordMessageTypeByMerchantValue() {
		if (getRecordMessageType() == null) {
			return "";
		}
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByValue(RecordMessageType.class, getRecordMessageType());
		return recordMessageType.getMerchantValue();
	}

	/**
	 * 属性记录报文类型的setter方法
	 */
	public void setRecordMessageType(Integer recordMessageType) {
		this.recordMessageType = recordMessageType;
	}

	/**
	 * 属性记录报文类型赋值
	 */
	public void setEnumRecordMessageType(RecordMessageType recordMessageType) {
		if (recordMessageType != null) {
			this.recordMessageType = recordMessageType.getValue();
		}
	}

	/**
	 * 属性核心记录报文类型赋值
	 */
	public void setRecordMessageTypeByCoreValue(String coreValue) {
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByCoreValue(RecordMessageType.class, coreValue);
		if (recordMessageType != null) {
			this.recordMessageType = recordMessageType.getValue();
		}
	}

	/**
	 * 属性商家记录报文类型赋值
	 */
	public void setRecordMessageTypeByMerchantValue(String merchantValue) {
		RecordMessageType recordMessageType = (RecordMessageType) EnumDataUtils.getEnumDictionaryByMerchantValue(RecordMessageType.class, merchantValue);
		if (recordMessageType != null) {
			this.recordMessageType = recordMessageType.getValue();
		}
	}

	/**
	 * 属性记录报文类型的getter方法
	 */

	@Column(name = "ENCRYPTIONTYPE")
	public Integer getEncryptionType() {
		return this.encryptionType;
	}

	/**
	 * 属性记录报文类型枚举类的getter方法
	 */
	@Transient
	public EncryptionType getEnumEncryptionType() {
		if (getEncryptionType() == null) {
			return null;
		}
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByValue(EncryptionType.class, getEncryptionType());
		return encryptionType;
	}

	/**
	 * 属性记录报文类型核心值的getter方法
	 */
	@Transient
	public String getEncryptionTypeByCoreValue() {
		if (getEncryptionType() == null) {
			return "";
		}
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByValue(EncryptionType.class, getEncryptionType());
		return encryptionType.getCoreValue();
	}

	/**
	 * 属性记录报文类型商家值的getter方法
	 */
	@Transient
	public String getEncryptionTypeByMerchantValue() {
		if (getEncryptionType() == null) {
			return "";
		}
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByValue(EncryptionType.class, getEncryptionType());
		return encryptionType.getMerchantValue();
	}

	/**
	 * 属性记录报文类型的setter方法
	 */
	public void setEncryptionType(Integer encryptionType) {
		this.encryptionType = encryptionType;
	}

	/**
	 * 属性记录报文类型赋值
	 */
	public void setEnumEncryptionType(EncryptionType encryptionType) {
		if (encryptionType != null) {
			this.encryptionType = encryptionType.getValue();
		}
	}

	/**
	 * 属性加密类型赋值
	 */
	public void setEncryptionTypeByCoreValue(String coreValue) {
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByCoreValue(EncryptionType.class, coreValue);
		if (encryptionType != null) {
			this.encryptionType = encryptionType.getValue();
		}
	}

	/**
	 * 属性商家记录报文类型赋值
	 */
	public void setEncryptionTypeByMerchantValue(String merchantValue) {
		EncryptionType encryptionType = (EncryptionType) EnumDataUtils.getEnumDictionaryByMerchantValue(EncryptionType.class, merchantValue);
		if (encryptionType != null) {
			this.encryptionType = encryptionType.getValue();
		}
	}

	/**
	 * 属性报文处理类型getter方法
	 */
	@Column(name = "HANDLEMESSAGETYPE")
	public String getHandleMessageType() {
		return handleMessageType;
	}

	/**
	 * 属性报文处理类型setter方法
	 */
	public void setHandleMessageType(String handleMessageType) {
		this.handleMessageType = handleMessageType;
	}

	/**
	 * 属性操作员的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作员的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * 属性接口交互系统的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSTEMSERIALNO", unique = true, nullable = true)
	public PortalInterfaceSystem getPortalInterfaceSystem() {
		return portalInterfaceSystem;
	}

	/**
	 * 属性接口交互系统的setter方法
	 */
	public void setPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		this.portalInterfaceSystem = portalInterfaceSystem;
		if (getPortalInterfaceSystem() != null && getPortalInterfaceSystem().getPortalInterface() == null) {
			getPortalInterfaceSystem().setPortalInterface(this);
		}
	}

	/**
	 * 属性接口处理类的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portalInterface")
	public PortalInterfaceHandlerClass getPortalInterfaceHandlerClass() {
		return portalInterfaceHandlerClass;
	}

	/**
	 * 属性接口处理类的setter方法
	 */
	public void setPortalInterfaceHandlerClass(PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		this.portalInterfaceHandlerClass = portalInterfaceHandlerClass;
		if (getPortalInterfaceHandlerClass() != null && getPortalInterfaceHandlerClass().getPortalInterface() == null) {
			getPortalInterfaceHandlerClass().setPortalInterface(this);
		}
	}
	
	/**
	 * 属性接口处理方法参数的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterfaceHandlerClass")
	public List<PortalInterfaceHandlerMethodParameter> getPortalInterfaceHandlerMethodParameters() {
		return portalInterfaceHandlerMethodParameters;
	}
	
	/**
	 * 属性接口处理方法参数的setter方法
	 */
	public void setPortalInterfaceHandlerMethodParameters(
			List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) {
		this.portalInterfaceHandlerMethodParameters = portalInterfaceHandlerMethodParameters;
	}
	
	/**
	 * 属性添加所有接口处理方法参数
	 */
	public void addPortalInterfaceHandlerMethodParameters(List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) {
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:portalInterfaceHandlerMethodParameters) {
			if (!getPortalInterfaceHandlerMethodParameters().contains(portalInterfaceHandlerMethodParameter)) {
				getPortalInterfaceHandlerMethodParameters().add(portalInterfaceHandlerMethodParameter);
			}
		}
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:getPortalInterfaceHandlerMethodParameters()) {
			if (portalInterfaceHandlerMethodParameter.getPortalInterface() == null) {
				portalInterfaceHandlerMethodParameter.setPortalInterface(this);
			}
			
		}
	}
	
	/**
	 * 属性接口账号信息的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterface")
	@Fetch(FetchMode.SUBSELECT)
	public List<PortalInterfaceAccount> getPortalInterfaceAccounts() {
		return portalInterfaceAccounts;
	}

	/**
	 * 属性接口账号信息的setter方法
	 */
	public void setPortalInterfaceAccounts(
			List<PortalInterfaceAccount> portalInterfaceAccounts) {
		this.portalInterfaceAccounts = portalInterfaceAccounts;
	}
	
	/**
	 * 属性添加所有接口账号信息
	 */
	public void addPortalInterfaceAccounts(List<PortalInterfaceAccount> portalInterfaceAccounts) {
		
		for (PortalInterfaceAccount portalInterfaceAccount:portalInterfaceAccounts) {
			if (!getPortalInterfaceAccounts().contains(portalInterfaceAccount)) {
				getPortalInterfaceAccounts().add(portalInterfaceAccount);
			}
		}
		
		for (PortalInterfaceAccount portalInterfaceAccount:getPortalInterfaceAccounts()) {
			if (portalInterfaceAccount.getPortalInterface() == null) {
				portalInterfaceAccount.setPortalInterface(this);
			}
			
		}
	}
	
	/**
	 * 属性元素节点的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalInterface")
	@Fetch(FetchMode.SUBSELECT)
	public List<PortalInterfaceElement> getPortalInterfaceElements() {
		return portalInterfaceElements;
	}

	/**
	 * 属性元素节点的setter方法
	 */
	public void setPortalInterfaceElements(
			List<PortalInterfaceElement> portalInterfaceElements) {
		this.portalInterfaceElements = portalInterfaceElements;
	}
	
	/**
	 * 属性添加所有元素节点
	 */
	public void addPortalInterfaceElements(List<PortalInterfaceElement> portalInterfaceElements) {
		
		for (PortalInterfaceElement portalInterfaceElement:portalInterfaceElements) {
			if (!getPortalInterfaceElements().contains(portalInterfaceElement)) {
				getPortalInterfaceElements().add(portalInterfaceElement);
			}
		}
		
		for (PortalInterfaceElement portalInterfaceElement:getPortalInterfaceElements()) {
			if (portalInterfaceElement.getPortalInterface() == null) {
				portalInterfaceElement.setPortalInterface(this);
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
