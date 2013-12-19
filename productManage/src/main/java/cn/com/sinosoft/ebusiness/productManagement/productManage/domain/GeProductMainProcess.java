package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeProductMainProcess
 */
@Entity
@Table(name = "GE_PRODUCTMAIN_PROCESS")
public class GeProductMainProcess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Դ������ */
	private String handleDept;

	/** ���Բ���Ա��� */
	private String operatorID;

	/** ���Դ���ʱ�� */
	private Date handleDate;

	/** ���Դ���״̬ */
	private String handleStatus;

	/** ���Դ������� */
	private String newHandleDept;

	/** ���Դ������ */
	private String options;

	/** ���Ա�ע */
	private String remark;

	/** ���Ա�־λ */
	private String flag;

	/**
	 * ��GeProductMainProcess��Ĭ�Ϲ��췽��
	 */
	public GeProductMainProcess() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
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
	 * ���Բ�Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * ���Բ�Ʒ��setter����
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * ���Դ��������getter����
	 */

	@Column(name = "HANDLEDEPT")
	public String getHandleDept() {
		return this.handleDept;
	}
	/**
	 * ���Դ��������setter����
	 */
	public void setHandleDept(String handleDept) {
		this.handleDept = handleDept;
	}
	/**
	 * ���Բ���Ա��ŵ�getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * ���Բ���Ա��ŵ�setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HANDLEDATE")
	public Date getHandleDate() {
		return this.handleDate;
	}
	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	/**
	 * ���Դ���״̬��getter����
	 */

	@Column(name = "HANDLESTATUS")
	public String getHandleStatus() {
		return this.handleStatus;
	}
	/**
	 * ���Դ���״̬��setter����
	 */
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	/**
	 * ���Դ���������getter����
	 */

	@Column(name = "NEWHANDLEDEPT")
	public String getNewHandleDept() {
		return this.newHandleDept;
	}
	/**
	 * ���Դ���������setter����
	 */
	public void setNewHandleDept(String newHandleDept) {
		this.newHandleDept = newHandleDept;
	}
	/**
	 * ���Դ��������getter����
	 */

	@Column(name = "OPTIONS")
	public String getOptions() {
		return this.options;
	}
	/**
	 * ���Դ��������setter����
	 */
	public void setOptions(String options) {
		this.options = options;
	}
	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	/**
	 * ���Ա�ע��setter����
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * ���Ա�־λ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}
	/**
	 * ���Ա�־λ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
