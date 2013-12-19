package cn.com.sinosoft.ebusiness.log.domain;

//default package
//采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
* POJO类GeMonitorUserlog
*/
@Entity
@Table(name = "GE_MONITOR_USERLOG")
public class GeMonitorUserlog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性黑名单编号 */
	private String id;

	/** 属性geMonitorUserlog */
	private GeMonitorUserlog geMonitorUserlog;

	/** 属性用户号 */
	private String userID;

	/** 属性操作系统Ip */
	private String ip;

	/** 属性用户类型 */
	private String userType;

	/** 属性operationtype */
	private String operationtype;

	/** 属性出厂日期 */
	private Date makeDate;

	/** 属性cpuTimes */
	private Short cpuTimes;

	/** 属性operation */
	private String operation;

	/** 属性result */
	private String result;

	/** 属性errormessage */
	private String errormessage;

	/** 属性序号 */
	private Short serialNo;

	/** 属性geMonitorUserlogs */
	private List<GeMonitorUserlog> geMonitorUserlogs = new ArrayList<GeMonitorUserlog>(
			0);

	/**
	 * 类GeMonitorUserlog的默认构造方法
	 */
	public GeMonitorUserlog() {
	}

	/**
	 * 属性黑名单编号的getter方法
	 */
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}

	/**
	 * 属性黑名单编号的setter方法
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 属性geMonitorUserlog的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTID")
	public GeMonitorUserlog getGeMonitorUserlog() {
		return this.geMonitorUserlog;
	}

	/**
	 * 属性geMonitorUserlog的setter方法
	 */
	public void setGeMonitorUserlog(GeMonitorUserlog geMonitorUserlog) {
		this.geMonitorUserlog = geMonitorUserlog;
	}

	/**
	 * 属性用户号的getter方法
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 属性操作系统Ip的getter方法
	 */

	@Column(name = "IP")
	public String getIp() {
		return this.ip;
	}

	/**
	 * 属性操作系统Ip的setter方法
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 属性用户类型的getter方法
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * 属性用户类型的setter方法
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 属性operationtype的getter方法
	 */

	@Column(name = "OPERATIONTYPE")
	public String getOperationtype() {
		return this.operationtype;
	}

	/**
	 * 属性operationtype的setter方法
	 */
	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}

	/**
	 * 属性出厂日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MAKEDATE")
	public Date getMakeDate() {
		return this.makeDate;
	}

	/**
	 * 属性出厂日期的setter方法
	 */
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	/**
	 * 属性cpuTimes的getter方法
	 */

	@Column(name = "CPU_TIMES")
	public Short getCpuTimes() {
		return this.cpuTimes;
	}

	/**
	 * 属性cpuTimes的setter方法
	 */
	public void setCpuTimes(Short cpuTimes) {
		this.cpuTimes = cpuTimes;
	}

	/**
	 * 属性operation的getter方法
	 */

	@Column(name = "OPERATION")
	public String getOperation() {
		return this.operation;
	}

	/**
	 * 属性operation的setter方法
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 属性result的getter方法
	 */

	@Column(name = "RESULT")
	public String getResult() {
		return this.result;
	}

	/**
	 * 属性result的setter方法
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 属性errormessage的getter方法
	 */

	@Column(name = "ERRORMESSAGE")
	public String getErrormessage() {
		return this.errormessage;
	}

	/**
	 * 属性errormessage的setter方法
	 */
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	/**
	 * 属性序号的getter方法
	 */

	@Column(name = "SERIALNO")
	public Short getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性geMonitorUserlogs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geMonitorUserlog")
	public List<GeMonitorUserlog> getGeMonitorUserlogs() {
		return this.geMonitorUserlogs;
	}

	/**
	 * 属性geMonitorUserlogs的setter方法
	 */
	public void setGeMonitorUserlogs(List<GeMonitorUserlog> geMonitorUserlogs) {
		this.geMonitorUserlogs = geMonitorUserlogs;
	}

}
