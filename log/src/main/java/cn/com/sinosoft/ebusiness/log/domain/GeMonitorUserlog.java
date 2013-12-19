package cn.com.sinosoft.ebusiness.log.domain;

//default package
//���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
* POJO��GeMonitorUserlog
*/
@Entity
@Table(name = "GE_MONITOR_USERLOG")
public class GeMonitorUserlog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ���Ժ�������� */
	private String id;

	/** ����geMonitorUserlog */
	private GeMonitorUserlog geMonitorUserlog;

	/** �����û��� */
	private String userID;

	/** ���Բ���ϵͳIp */
	private String ip;

	/** �����û����� */
	private String userType;

	/** ����operationtype */
	private String operationtype;

	/** ���Գ������� */
	private Date makeDate;

	/** ����cpuTimes */
	private Short cpuTimes;

	/** ����operation */
	private String operation;

	/** ����result */
	private String result;

	/** ����errormessage */
	private String errormessage;

	/** ������� */
	private Short serialNo;

	/** ����geMonitorUserlogs */
	private List<GeMonitorUserlog> geMonitorUserlogs = new ArrayList<GeMonitorUserlog>(
			0);

	/**
	 * ��GeMonitorUserlog��Ĭ�Ϲ��췽��
	 */
	public GeMonitorUserlog() {
	}

	/**
	 * ���Ժ�������ŵ�getter����
	 */
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getId() {
		return this.id;
	}

	/**
	 * ���Ժ�������ŵ�setter����
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ����geMonitorUserlog��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTID")
	public GeMonitorUserlog getGeMonitorUserlog() {
		return this.geMonitorUserlog;
	}

	/**
	 * ����geMonitorUserlog��setter����
	 */
	public void setGeMonitorUserlog(GeMonitorUserlog geMonitorUserlog) {
		this.geMonitorUserlog = geMonitorUserlog;
	}

	/**
	 * �����û��ŵ�getter����
	 */

	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * ���Բ���ϵͳIp��getter����
	 */

	@Column(name = "IP")
	public String getIp() {
		return this.ip;
	}

	/**
	 * ���Բ���ϵͳIp��setter����
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * �����û����͵�getter����
	 */

	@Column(name = "USERTYPE")
	public String getUserType() {
		return this.userType;
	}

	/**
	 * �����û����͵�setter����
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * ����operationtype��getter����
	 */

	@Column(name = "OPERATIONTYPE")
	public String getOperationtype() {
		return this.operationtype;
	}

	/**
	 * ����operationtype��setter����
	 */
	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}

	/**
	 * ���Գ������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MAKEDATE")
	public Date getMakeDate() {
		return this.makeDate;
	}

	/**
	 * ���Գ������ڵ�setter����
	 */
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	/**
	 * ����cpuTimes��getter����
	 */

	@Column(name = "CPU_TIMES")
	public Short getCpuTimes() {
		return this.cpuTimes;
	}

	/**
	 * ����cpuTimes��setter����
	 */
	public void setCpuTimes(Short cpuTimes) {
		this.cpuTimes = cpuTimes;
	}

	/**
	 * ����operation��getter����
	 */

	@Column(name = "OPERATION")
	public String getOperation() {
		return this.operation;
	}

	/**
	 * ����operation��setter����
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * ����result��getter����
	 */

	@Column(name = "RESULT")
	public String getResult() {
		return this.result;
	}

	/**
	 * ����result��setter����
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * ����errormessage��getter����
	 */

	@Column(name = "ERRORMESSAGE")
	public String getErrormessage() {
		return this.errormessage;
	}

	/**
	 * ����errormessage��setter����
	 */
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	/**
	 * ������ŵ�getter����
	 */

	@Column(name = "SERIALNO")
	public Short getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * ����geMonitorUserlogs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geMonitorUserlog")
	public List<GeMonitorUserlog> getGeMonitorUserlogs() {
		return this.geMonitorUserlogs;
	}

	/**
	 * ����geMonitorUserlogs��setter����
	 */
	public void setGeMonitorUserlogs(List<GeMonitorUserlog> geMonitorUserlogs) {
		this.geMonitorUserlogs = geMonitorUserlogs;
	}

}
