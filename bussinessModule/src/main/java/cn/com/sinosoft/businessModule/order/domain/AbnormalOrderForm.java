package cn.com.sinosoft.businessModule.order.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��AbnormalOrderForm
 */
@Entity
@Table(name = "ABNORMALORDERFORM")
public class AbnormalOrderForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Զ����� */
	private String orderSerialNumber;

	/** ����Ͷ������ */
	private String applicationNumber;

	/** ���Զ���״̬ */
	private Integer orderStatus;
	
	/** ����֧����ʽ */
	private Integer paymentMethod;

	/** ����֧��״̬ */
	private Integer payStatus;
	
	/** �����ֶ��б�״̬ */
	private Integer manualInsuredStatus;
	
	/** �����쳣���� */
	private String exceptionCode;

	/** �����쳣���� */
	private String exceptionDescription;

	/** �����쳣��ջ */
	private String exceptionStack;
	
	/** ���Բ���Ա */
	private String operatorID;
	
	/** �����ʼ�����ʱ�� */
	private Date sendEmailTime;
	
	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ��AbnormalOrder��Ĭ�Ϲ��췽��
	 */
	public AbnormalOrderForm() {
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
	 * ���Զ����ŵ�getter����
	 */

	@Column(name = "ORDERSERIALNUMBER")
	public String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	/**
	 * ���Զ����ŵ�setter����
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}

	/**
	 * ����Ͷ�����ŵ�getter����
	 */

	@Column(name = "APPLICATIONNUMBER")
	public String getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * ����Ͷ�����ŵ�setter����
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * ���Զ���״̬��getter����
	 */

	@Column(name = "ORDERSTATUS")
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * ���Զ���״̬��setter����
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * ����֧����ʽ��getter����
	 */

	@Column(name = "PAYMENTMETHOD")
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * ����֧����ʽ��setter����
	 */
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * ����֧��״̬��getter����
	 */

	@Column(name = "PAYSTATUS")
	public Integer getPayStatus() {
		return this.payStatus;
	}

	/**
	 * ����֧��״̬��setter����
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	 * �����ֶ��б�״̬��getter����
	 */

	@Column(name = "MANUALINSUREDSTATUS")
	public Integer getManualInsuredStatus() {
		return this.manualInsuredStatus;
	}
	
	/**
	 * �����ֶ��б�״̬��setter����
	 */
	public void setManualInsuredStatus(Integer manualInsuredStatus) {
		this.manualInsuredStatus = manualInsuredStatus;
	}

	/**
	 * �����쳣�����getter����
	 */

	@Column(name = "EXCEPTIONCODE")
	public String getExceptionCode() {
		return this.exceptionCode;
	}

	/**
	 * �����쳣�����setter����
	 */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * �����쳣������getter����
	 */

	@Column(name = "EXCEPTIONDESCRIPTION")
	public String getExceptionDescription() {
		return this.exceptionDescription;
	}

	/**
	 * �����쳣������setter����
	 */
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

	/**
	 * �����쳣��ջ��getter����
	 */

	@Column(name = "EXCEPTIONSTACK")
	public String getExceptionStack() {
		return this.exceptionStack;
	}

	/**
	 * �����쳣��ջ��setter����
	 */
	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}
	
	/**
	 * ���Բ���Ա��getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ���Ա��setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	
	/**
	 * �����ʼ�����ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SENDEMAILTIME")
	public Date getSendEmailTime() {
		return sendEmailTime;
	}
	
	/**
	 * �����ʼ�����ʱ���setter����
	 */
	public void setSendEmailTime(Date sendEmailTime) {
		this.sendEmailTime = sendEmailTime;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
