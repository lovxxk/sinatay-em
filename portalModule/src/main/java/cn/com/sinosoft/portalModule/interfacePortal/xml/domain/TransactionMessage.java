package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��TransactionMessage
 */
@Entity
@Table(name = "GE_PORTAL_TRANSMESSAGE")
public class TransactionMessage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// �쳣��Ϣ
	private String esbException;
	
	/** ����esbrequestheader */
	private String esbrequestheader;

	/** ����esbresponseheader */
	private String esbresponseheader;
	
	/** ���Խ�����ˮ�� */
	private String transRefGuid;

	/** ����externalSysInfo */
	private ExternalSysInfo externalSysInfo;

	/** ���Խ��״��� */
	private String transCode;

	/** ���Խ���ʱ�� */
	private Date transTime;

	/** ��������ʱ�� */
	private Date requestTime;

	/** ���������� */
	private String requestMessage;

	/** ����Ӧ��ʱ�� */
	private Date responseTime;

	/** ����Ӧ���� */
	private String responseMessage;

	/** ���Խ����ⲿϵͳ����ʱ�� */
	private Date frontRequestTime;

	/** �����ⲿϵͳ������ */
	private String frontRequestMessage;

	/** �����ⲿϵͳӦ��ʱ�� */
	private Date frontResponeTime;

	/** �����ⲿϵͳӦ���� */
	private String frontResponseMessage;

	/**
	 * ��TransactionMessage��Ĭ�Ϲ��췽��
	 */
	public TransactionMessage() {
	}

	/**
	 * ���Խ�����ˮ�ŵ�getter����
	 */
	@Id
	@Column(name = "TRANSREFGUID")
	public String getTransRefGuid() {
		return this.transRefGuid;
	}
	/**
	 * ���Խ�����ˮ�ŵ�setter����
	 */
	public void setTransRefGuid(String transRefGuid) {
		this.transRefGuid = transRefGuid;
	}
	/**
	 * ����externalSysInfo��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXTERNALSYSID")
	public ExternalSysInfo getExternalSysInfo() {
		return this.externalSysInfo;
	}
	/**
	 * ����externalSysInfo��setter����
	 */
	public void setExternalSysInfo(ExternalSysInfo externalSysInfo) {
		this.externalSysInfo = externalSysInfo;
	}
	/**
	 * ���Խ��״����getter����
	 */

	@Column(name = "TRANSCODE")
	public String getTransCode() {
		return this.transCode;
	}
	/**
	 * ���Խ��״����setter����
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	/**
	 * ���Խ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSTIME")
	public Date getTransTime() {
		return this.transTime;
	}
	/**
	 * ���Խ���ʱ���setter����
	 */
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	/**
	 * ��������ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUESTTIME")
	public Date getRequestTime() {
		return this.requestTime;
	}
	/**
	 * ��������ʱ���setter����
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	/**
	 * ���������ĵ�getter����
	 */

	@Column(name = "REQUESTMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getRequestMessage() {
		return this.requestMessage;
	}
	/**
	 * ���������ĵ�setter����
	 */
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	/**
	 * ����Ӧ��ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESPONSETIME")
	public Date getResponseTime() {
		return this.responseTime;
	}
	/**
	 * ����Ӧ��ʱ���setter����
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	/**
	 * ����Ӧ���ĵ�getter����
	 */

	@Column(name = "RESPONSEMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getResponseMessage() {
		return this.responseMessage;
	}
	/**
	 * ����Ӧ���ĵ�setter����
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	/**
	 * ���Խ����ⲿϵͳ����ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FRONTREQUESTTIME")
	public Date getFrontRequestTime() {
		return this.frontRequestTime;
	}
	/**
	 * ���Խ����ⲿϵͳ����ʱ���setter����
	 */
	public void setFrontRequestTime(Date frontRequestTime) {
		this.frontRequestTime = frontRequestTime;
	}
	/**
	 * �����ⲿϵͳ�����ĵ�getter����
	 */

	@Column(name = "FRONTREQUESTMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getFrontRequestMessage() {
		return this.frontRequestMessage;
	}
	/**
	 * �����ⲿϵͳ�����ĵ�setter����
	 */
	public void setFrontRequestMessage(String frontRequestMessage) {
		this.frontRequestMessage = frontRequestMessage;
	}
	/**
	 * �����ⲿϵͳӦ��ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FRONTRESPONETIME")
	public Date getFrontResponeTime() {
		return this.frontResponeTime;
	}
	/**
	 * �����ⲿϵͳӦ��ʱ���setter����
	 */
	public void setFrontResponeTime(Date frontResponeTime) {
		this.frontResponeTime = frontResponeTime;
	}
	/**
	 * �����ⲿϵͳӦ���ĵ�getter����
	 */

	@Column(name = "FRONTRESPONSEMESSAGE")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getFrontResponseMessage() {
		return this.frontResponseMessage;
	}
	/**
	 * �����ⲿϵͳӦ���ĵ�setter����
	 */
	public void setFrontResponseMessage(String frontResponseMessage) {
		this.frontResponseMessage = frontResponseMessage;
	}

	/**
	 * ����esbrequestheader��getter����
	 */

//	@Column(name = "ESBREQUESTHEADER")
//	@Basic(fetch = FetchType.LAZY)
//	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getEsbrequestheader() {
		return this.esbrequestheader;
	}

	/**
	 * ����esbrequestheader��setter����
	 */
	public void setEsbrequestheader(String esbrequestheader) {
		this.esbrequestheader = esbrequestheader;
	}

	/**
	 * ����esbresponseheader��getter����
	 */

//	@Column(name = "ESBRESPONSEHEADER")
//	@Basic(fetch = FetchType.LAZY)
//	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getEsbresponseheader() {
		return this.esbresponseheader;
	}

	/**
	 * ����esbresponseheader��setter����
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
