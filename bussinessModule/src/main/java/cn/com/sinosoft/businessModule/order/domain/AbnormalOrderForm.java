package cn.com.sinosoft.businessModule.order.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类AbnormalOrderForm
 */
@Entity
@Table(name = "ABNORMALORDERFORM")
public class AbnormalOrderForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性订单号 */
	private String orderSerialNumber;

	/** 属性投保单号 */
	private String applicationNumber;

	/** 属性订单状态 */
	private Integer orderStatus;
	
	/** 属性支付方式 */
	private Integer paymentMethod;

	/** 属性支付状态 */
	private Integer payStatus;
	
	/** 属性手动承保状态 */
	private Integer manualInsuredStatus;
	
	/** 属性异常代码 */
	private String exceptionCode;

	/** 属性异常描述 */
	private String exceptionDescription;

	/** 属性异常堆栈 */
	private String exceptionStack;
	
	/** 属性操作员 */
	private String operatorID;
	
	/** 属性邮件发送时间 */
	private Date sendEmailTime;
	
	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 类AbnormalOrder的默认构造方法
	 */
	public AbnormalOrderForm() {
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
	 * 属性投保单号的getter方法
	 */

	@Column(name = "APPLICATIONNUMBER")
	public String getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * 属性投保单号的setter方法
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * 属性订单状态的getter方法
	 */

	@Column(name = "ORDERSTATUS")
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * 属性订单状态的setter方法
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 属性支付方式的getter方法
	 */

	@Column(name = "PAYMENTMETHOD")
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * 属性支付方式的setter方法
	 */
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 属性支付状态的getter方法
	 */

	@Column(name = "PAYSTATUS")
	public Integer getPayStatus() {
		return this.payStatus;
	}

	/**
	 * 属性支付状态的setter方法
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	 * 属性手动承保状态的getter方法
	 */

	@Column(name = "MANUALINSUREDSTATUS")
	public Integer getManualInsuredStatus() {
		return this.manualInsuredStatus;
	}
	
	/**
	 * 属性手动承保状态的setter方法
	 */
	public void setManualInsuredStatus(Integer manualInsuredStatus) {
		this.manualInsuredStatus = manualInsuredStatus;
	}

	/**
	 * 属性异常代码的getter方法
	 */

	@Column(name = "EXCEPTIONCODE")
	public String getExceptionCode() {
		return this.exceptionCode;
	}

	/**
	 * 属性异常代码的setter方法
	 */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * 属性异常描述的getter方法
	 */

	@Column(name = "EXCEPTIONDESCRIPTION")
	public String getExceptionDescription() {
		return this.exceptionDescription;
	}

	/**
	 * 属性异常描述的setter方法
	 */
	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

	/**
	 * 属性异常堆栈的getter方法
	 */

	@Column(name = "EXCEPTIONSTACK")
	public String getExceptionStack() {
		return this.exceptionStack;
	}

	/**
	 * 属性异常堆栈的setter方法
	 */
	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
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
	 * 属性邮件发送时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SENDEMAILTIME")
	public Date getSendEmailTime() {
		return sendEmailTime;
	}
	
	/**
	 * 属性邮件发送时间的setter方法
	 */
	public void setSendEmailTime(Date sendEmailTime) {
		this.sendEmailTime = sendEmailTime;
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
