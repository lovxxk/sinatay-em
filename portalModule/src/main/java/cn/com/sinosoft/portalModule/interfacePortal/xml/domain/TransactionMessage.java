package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysInfo;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 * POJO类TransactionMessage
 */
@Entity
@Table(name = "GE_PORTAL_TRANSMESSAGE")
public class TransactionMessage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// 异常信息
	private String esbException;
	
	/** 属性esbrequestheader */
	private String esbrequestheader;

	/** 属性esbresponseheader */
	private String esbresponseheader;
	
	/** 属性交易流水号 */
	private String transRefGuid;

	/** 属性externalSysInfo */
	private ExternalSysInfo externalSysInfo;

	/** 属性交易代码 */
	private String transCode;

	/** 属性交易时间 */
	private Date transTime;

	/** 属性请求时间 */
	private Date requestTime;

	/** 属性请求报文 */
	private String requestMessage;

	/** 属性应答时间 */
	private Date responseTime;

	/** 属性应答报文 */
	private String responseMessage;

	/** 属性接收外部系统报文时间 */
	private Date frontRequestTime;

	/** 属性外部系统请求报文 */
	private String frontRequestMessage;

	/** 属性外部系统应答时间 */
	private Date frontResponeTime;

	/** 属性外部系统应答报文 */
	private String frontResponseMessage;

	/**
	 * 类TransactionMessage的默认构造方法
	 */
	public TransactionMessage() {
	}

	/**
	 * 属性交易流水号的getter方法
	 */
	@Id
	@Column(name = "TRANSREFGUID")
	public String getTransRefGuid() {
		return this.transRefGuid;
	}
	/**
	 * 属性交易流水号的setter方法
	 */
	public void setTransRefGuid(String transRefGuid) {
		this.transRefGuid = transRefGuid;
	}
	/**
	 * 属性externalSysInfo的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXTERNALSYSID")
	public ExternalSysInfo getExternalSysInfo() {
		return this.externalSysInfo;
	}
	/**
	 * 属性externalSysInfo的setter方法
	 */
	public void setExternalSysInfo(ExternalSysInfo externalSysInfo) {
		this.externalSysInfo = externalSysInfo;
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
	 * 属性交易时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSTIME")
	public Date getTransTime() {
		return this.transTime;
	}
	/**
	 * 属性交易时间的setter方法
	 */
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	/**
	 * 属性请求时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUESTTIME")
	public Date getRequestTime() {
		return this.requestTime;
	}
	/**
	 * 属性请求时间的setter方法
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	/**
	 * 属性请求报文的getter方法
	 */

	@Column(name = "REQUESTMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getRequestMessage() {
		return this.requestMessage;
	}
	/**
	 * 属性请求报文的setter方法
	 */
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	/**
	 * 属性应答时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESPONSETIME")
	public Date getResponseTime() {
		return this.responseTime;
	}
	/**
	 * 属性应答时间的setter方法
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	/**
	 * 属性应答报文的getter方法
	 */

	@Column(name = "RESPONSEMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getResponseMessage() {
		return this.responseMessage;
	}
	/**
	 * 属性应答报文的setter方法
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	/**
	 * 属性接收外部系统报文时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FRONTREQUESTTIME")
	public Date getFrontRequestTime() {
		return this.frontRequestTime;
	}
	/**
	 * 属性接收外部系统报文时间的setter方法
	 */
	public void setFrontRequestTime(Date frontRequestTime) {
		this.frontRequestTime = frontRequestTime;
	}
	/**
	 * 属性外部系统请求报文的getter方法
	 */

	@Column(name = "FRONTREQUESTMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getFrontRequestMessage() {
		return this.frontRequestMessage;
	}
	/**
	 * 属性外部系统请求报文的setter方法
	 */
	public void setFrontRequestMessage(String frontRequestMessage) {
		this.frontRequestMessage = frontRequestMessage;
	}
	/**
	 * 属性外部系统应答时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FRONTRESPONETIME")
	public Date getFrontResponeTime() {
		return this.frontResponeTime;
	}
	/**
	 * 属性外部系统应答时间的setter方法
	 */
	public void setFrontResponeTime(Date frontResponeTime) {
		this.frontResponeTime = frontResponeTime;
	}
	/**
	 * 属性外部系统应答报文的getter方法
	 */

	@Column(name = "FRONTRESPONSEMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getFrontResponseMessage() {
		return this.frontResponseMessage;
	}
	/**
	 * 属性外部系统应答报文的setter方法
	 */
	public void setFrontResponseMessage(String frontResponseMessage) {
		this.frontResponseMessage = frontResponseMessage;
	}

	/**
	 * 属性esbrequestheader的getter方法
	 */

//	@Column(name = "ESBREQUESTHEADER")
//	@Basic(fetch = FetchType.LAZY)
//	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getEsbrequestheader() {
		return this.esbrequestheader;
	}

	/**
	 * 属性esbrequestheader的setter方法
	 */
	public void setEsbrequestheader(String esbrequestheader) {
		this.esbrequestheader = esbrequestheader;
	}

	/**
	 * 属性esbresponseheader的getter方法
	 */

//	@Column(name = "ESBRESPONSEHEADER")
//	@Basic(fetch = FetchType.LAZY)
//	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getEsbresponseheader() {
		return this.esbresponseheader;
	}

	/**
	 * 属性esbresponseheader的setter方法
	 */
	public void setEsbresponseheader(String esbresponseheader) {
		this.esbresponseheader = esbresponseheader;
	}

	
//	@Column(name = "ESBEXCEPTION")
//	@Basic(fetch = FetchType.LAZY)
//	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getEsbException() {
		return esbException;
	}

	public void setEsbException(String esbException) {
		this.esbException = esbException;
	}
	
	
}
