package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 * POJO��geMonitorAppException
 */
@Entity
@Table(name = "GE_MONITOR_APPEXCEPTION")
public class GeMonitorAppException implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����Ͷ�������� */
	private String appName;

	/** �����쳣������� */
	private String exceptionKind;

	/** �����û��쳣���� */
	private String userExceptionCode;

	/** �������û��쳣���� */
	private String subUserExceptionCode;

	/** ���Ծ����쳣���� */
	private String concreteExceptionCode;

	/** �����쳣���� */
	private String exceptionDesc;

	/** �����쳣��ջ */
	private String exceptionReason;

	/** �����쳣ʱ�� */
	private Date exceptionTime;

	/** �����쳣���� */
	private String exceptionGrade;
	
	private String  identifyFlag;

	/**
	 * ��geMonitorAppException��Ĭ�Ϲ��췽��
	 */
	public GeMonitorAppException() {
	}
	
	@Column(name = "IDENTIFYFLAG")
	public String getIdentifyFlag() {
		return identifyFlag;
	}

	public void setIdentifyFlag(String identifyFlag) {
		this.identifyFlag = identifyFlag;
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO")
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
	 * ����Ͷ����������getter����
	 */

	@Column(name = "APPNAME")
	public String getAppName() {
		return this.appName;
	}

	/**
	 * ����Ͷ����������setter����
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * �����쳣��������getter����
	 */

	@Column(name = "EXCEPTIONKIND")
	public String getExceptionKind() {
		return this.exceptionKind;
	}

	/**
	 * �����쳣��������setter����
	 */
	public void setExceptionKind(String exceptionKind) {
		this.exceptionKind = exceptionKind;
	}

	/**
	 * �����û��쳣�����getter����
	 */

	@Column(name = "USEREXCEPTIONCODE")
	public String getUserExceptionCode() {
		return this.userExceptionCode;
	}

	/**
	 * �����û��쳣�����setter����
	 */
	public void setUserExceptionCode(String userExceptionCode) {
		this.userExceptionCode = userExceptionCode;
	}

	/**
	 * �������û��쳣�����getter����
	 */

	@Column(name = "SUBUSEREXCEPTIONCODE")
	public String getSubUserExceptionCode() {
		return this.subUserExceptionCode;
	}

	/**
	 * �������û��쳣�����setter����
	 */
	public void setSubUserExceptionCode(String subUserExceptionCode) {
		this.subUserExceptionCode = subUserExceptionCode;
	}

	/**
	 * ���Ծ����쳣�����getter����
	 */

	@Column(name = "CONCRETEEXCEPTIONCODE")
	public String getConcreteExceptionCode() {
		return this.concreteExceptionCode;
	}

	/**
	 * ���Ծ����쳣�����setter����
	 */
	public void setConcreteExceptionCode(String concreteExceptionCode) {
		this.concreteExceptionCode = concreteExceptionCode;
	}

	/**
	 * �����쳣������getter����
	 */

	@Column(name = "EXCEPTIONDESC")
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	/**
	 * �����쳣������setter����
	 */
	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	/**
	 * �����쳣��ջ��getter����
	 */

	@Column(name = "EXCEPTIONREASON")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getExceptionReason() {
		return this.exceptionReason;
	}

	/**
	 * �����쳣��ջ��setter����
	 */
	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	/**
	 * �����쳣ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXCEPTIONTIME")
	public Date getExceptionTime() {
		return this.exceptionTime;
	}

	/**
	 * �����쳣ʱ���setter����
	 */
	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	/**
	 * �����쳣�����getter����
	 */

	@Column(name = "EXCEPTIONGRADE")
	public String getExceptionGrade() {
		return this.exceptionGrade;
	}

	/**
	 * �����쳣�����setter����
	 */
	public void setExceptionGrade(String exceptionGrade) {
		this.exceptionGrade = exceptionGrade;
	}
 
}
