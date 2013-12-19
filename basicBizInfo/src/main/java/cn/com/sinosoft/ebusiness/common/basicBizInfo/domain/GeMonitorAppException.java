package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类geMonitorAppException
 */
@Entity
@Table(name = "GE_MONITOR_APPEXCEPTION")
public class GeMonitorAppException implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性投保人姓名 */
	private String appName;

	/** 属性异常大类代码 */
	private String exceptionKind;

	/** 属性用户异常代码 */
	private String userExceptionCode;

	/** 属性子用户异常代码 */
	private String subUserExceptionCode;

	/** 属性具体异常代码 */
	private String concreteExceptionCode;

	/** 属性异常描述 */
	private String exceptionDesc;

	/** 属性异常堆栈 */
	private String exceptionReason;

	/** 属性异常时间 */
	private Date exceptionTime;

	/** 属性异常级别 */
	private String exceptionGrade;
	
	private String  identifyFlag;

	/**
	 * 类geMonitorAppException的默认构造方法
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
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO")
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
	 * 属性投保人姓名的getter方法
	 */

	@Column(name = "APPNAME")
	public String getAppName() {
		return this.appName;
	}

	/**
	 * 属性投保人姓名的setter方法
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * 属性异常大类代码的getter方法
	 */

	@Column(name = "EXCEPTIONKIND")
	public String getExceptionKind() {
		return this.exceptionKind;
	}

	/**
	 * 属性异常大类代码的setter方法
	 */
	public void setExceptionKind(String exceptionKind) {
		this.exceptionKind = exceptionKind;
	}

	/**
	 * 属性用户异常代码的getter方法
	 */

	@Column(name = "USEREXCEPTIONCODE")
	public String getUserExceptionCode() {
		return this.userExceptionCode;
	}

	/**
	 * 属性用户异常代码的setter方法
	 */
	public void setUserExceptionCode(String userExceptionCode) {
		this.userExceptionCode = userExceptionCode;
	}

	/**
	 * 属性子用户异常代码的getter方法
	 */

	@Column(name = "SUBUSEREXCEPTIONCODE")
	public String getSubUserExceptionCode() {
		return this.subUserExceptionCode;
	}

	/**
	 * 属性子用户异常代码的setter方法
	 */
	public void setSubUserExceptionCode(String subUserExceptionCode) {
		this.subUserExceptionCode = subUserExceptionCode;
	}

	/**
	 * 属性具体异常代码的getter方法
	 */

	@Column(name = "CONCRETEEXCEPTIONCODE")
	public String getConcreteExceptionCode() {
		return this.concreteExceptionCode;
	}

	/**
	 * 属性具体异常代码的setter方法
	 */
	public void setConcreteExceptionCode(String concreteExceptionCode) {
		this.concreteExceptionCode = concreteExceptionCode;
	}

	/**
	 * 属性异常描述的getter方法
	 */

	@Column(name = "EXCEPTIONDESC")
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	/**
	 * 属性异常描述的setter方法
	 */
	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	/**
	 * 属性异常堆栈的getter方法
	 */

	@Column(name = "EXCEPTIONREASON")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	public String getExceptionReason() {
		return this.exceptionReason;
	}

	/**
	 * 属性异常堆栈的setter方法
	 */
	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	/**
	 * 属性异常时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXCEPTIONTIME")
	public Date getExceptionTime() {
		return this.exceptionTime;
	}

	/**
	 * 属性异常时间的setter方法
	 */
	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	/**
	 * 属性异常级别的getter方法
	 */

	@Column(name = "EXCEPTIONGRADE")
	public String getExceptionGrade() {
		return this.exceptionGrade;
	}

	/**
	 * 属性异常级别的setter方法
	 */
	public void setExceptionGrade(String exceptionGrade) {
		this.exceptionGrade = exceptionGrade;
	}
 
}
