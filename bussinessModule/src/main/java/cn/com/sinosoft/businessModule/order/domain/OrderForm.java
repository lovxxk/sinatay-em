package cn.com.sinosoft.businessModule.order.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

/**
 * POJO��Order
 */
@Entity
@Table(name = "ORDERFORM", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class OrderForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;

	/** ���Զ����� */
	private String orderSerialNumber;
	
	/** ���Ժ������������ţ��Ա������ţ� */
	private String merchantOrderNumber;
	
	/** ���Խ�����ˮ�� */
	private String transSerialNumber;
	
	/** ������Ʒ���� */
	private String productCode;

	/** ������Ʒ���� */
	private String productName;

	/** ������Ʒ���� */
	private Integer productNumber;

	/** ������Ʒ���� */
	private String productDesc;

	/** ����֧������ϵ��ʽ���� (1�����ʼ� 2�ֻ�) */
	private Integer payerContactType;

	/** ����֧������ϵ��ʽ */
	private String payerContact;

	/** ����֧����IP */
	private String payerIP;

	/** ���Ը��˿ͻ� */
	private String personalUserSerialNo;

	/** �����û��� */
	private String customerID;

	/** ���Զ������� */
	private Integer orderType;

	/** ���Զ���״̬ */
	private Integer orderStatus;

	/** ���Զ���״̬���� */
	private String orderStatusName;

	/** ���Զ���״̬���� */
	private String orderStatusDesc;

	/** ���Զ������ */
	private BigDecimal orderAmount;

	/** ����֧����ʽ */
	private Integer payMethod;

	/** ����֧��ʱ�� */
	private Date payTime;
	
	/** ����֧����ʽ	 */
	private Integer paymentMethod;
	
	/** ����֧��״̬ */
	private Integer payStatus;

	/** ����֧��״̬���� */
	private String payStatusName;

	/** ����֧��״̬���� */
	private String payStatusDesc;

	/** �����ύʱ�� */
	private Date submitTime;

	/** �������ض����� */
	private String gateWayOrderNo;

	/** �������ж����� */
	private String bankOrderNo;

	/** �����˵���ʾ */
	private String refundmentFlag;
	
	/** ���Բ���Ա */
	private String operatorID;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/** �������� */
	private String orderDesc;
	
	/**��������*/
	private String bankType;
	
	/**֧���ɹ���ʧ����Ϣ*/
	private String paymentMessage;
	
	/**���Ѵ���*/
	private Integer remindCount = 0;
	
	/**
	 * ��Order��Ĭ�Ϲ��췽��
	 */
	public OrderForm() {
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
	 * ���Ա�����getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * ���Ա�����setter����
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	
	/**
	 * ������ӱ���ʱͬʱ��������Ϣ��ֵ����������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getOrderForm() == null) {
			getInsurancePolicy().setOrderForm(this);
		}
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
	 * ���Ժ������������ŵ�getter����
	 */
	@Column(name = "merchantOrderNumber")
	@JSON(serialize=false) 
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * ���Ժ������������ŵ�setter����
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}

	/**
	 * ���Խ�����ˮ�ŵ�getter����
	 */
	@Column(name = "TRANSSERIALNUMBER")
	@JSON(serialize=false)  
	public String getTransSerialNumber() {
		return this.transSerialNumber;
	}

	/**
	 * ���Խ�����ˮ�ŵ�setter����
	 */
	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}
	

	/**
	 * ���Բ�Ʒ�����getter����
	 */

	@Column(name = "PRODUCTCODE")
	@JSON(serialize=false)  
	public String getProductCode() {
		return this.productCode;
	}

	/**
	 * ���Բ�Ʒ�����setter����
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * ���Բ�Ʒ���Ƶ�getter����
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}

	/**
	 * ���Բ�Ʒ���Ƶ�setter����
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * ���Բ�Ʒ������getter����
	 */

	@Column(name = "PRODUCTNUMBER")
	public Integer getProductNumber() {
		return this.productNumber;
	}

	/**
	 * ���Բ�Ʒ������setter����
	 */
	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	/**
	 * ���Բ�Ʒ������getter����
	 */

	@Column(name = "PRODUCTDESC")
	@JSON(serialize=false)  
	public String getProductDesc() {
		return this.productDesc;
	}

	/**
	 * ���Բ�Ʒ������setter����
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * ����֧������ϵ��ʽ���� (1�����ʼ� 2�ֻ�)��getter����
	 */

	@Column(name = "PAYERCONTACTTYPE")
	@JSON(serialize=false)  
	public Integer getPayerContactType() {
		return this.payerContactType;
	}

	/**
	 * ����֧������ϵ��ʽ���� (1�����ʼ� 2�ֻ�)��setter����
	 */
	public void setPayerContactType(Integer payerContactType) {
		this.payerContactType = payerContactType;
	}

	/**
	 * ����֧������ϵ��ʽ��getter����
	 */

	@Column(name = "PAYERCONTACT")
	@JSON(serialize=false)  
	public String getPayerContact() {
		return this.payerContact;
	}

	/**
	 * ����֧������ϵ��ʽ��setter����
	 */
	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}

	/**
	 * ����֧����IP��getter����
	 */

	@Column(name = "PAYERIP")
	@JSON(serialize=false)  
	public String getPayerIP() {
		return this.payerIP;
	}

	/**
	 * ����֧����IP��setter����
	 */
	public void setPayerIP(String payerIP) {
		this.payerIP = payerIP;
	}

	/**
	 * ���Ը��˿ͻ���getter����
	 */

	@Column(name = "PERSONALUSERSERIALNO")
	@JSON(serialize=false)  
	public String getPersonalUserSerialNo() {
		return this.personalUserSerialNo;
	}

	/**
	 * ���Ը��˿ͻ���setter����
	 */
	public void setPersonalUserSerialNo(String personalUserSerialNo) {
		this.personalUserSerialNo = personalUserSerialNo;
	}

	/**
	 * �����û��ŵ�getter����
	 */

	@Column(name = "CUSTOMERID")
	@JSON(serialize=false)  
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * ���Զ������͵�getter����
	 */

	@Column(name = "ORDERTYPE")
	@JSON(serialize=false)  
	public Integer getOrderType() {
		return this.orderType;
	}

	/**
	 * ���Զ������͵�setter����
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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
	 * ���Զ���״̬���Ƶ�getter����
	 */

	@Column(name = "ORDERSTATUSNAME")
	@JSON(serialize=false)  
	public String getOrderStatusName() {
		return this.orderStatusName;
	}

	/**
	 * ���Զ���״̬���Ƶ�setter����
	 */
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	/**
	 * ���Զ���״̬������getter����
	 */

	@Column(name = "ORDERSTATUSDESC")
	@JSON(serialize=false)  
	public String getOrderStatusDesc() {
		return this.orderStatusDesc;
	}

	/**
	 * ���Զ���״̬������setter����
	 */
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	/**
	 * ���Զ�������getter����
	 */

	@Column(name = "ORDERAMOUNT")
	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	/**
	 * ���Զ�������setter����
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * ����֧����ʽ��getter����
	 */

	@Column(name = "PAYMETHOD")
	@JSON(serialize=false)  
	public Integer getPayMethod() {
		return this.payMethod;
	}

	/**
	 * ����֧����ʽ��setter����
	 */
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * ����֧��ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYTIME")
	public Date getPayTime() {
		return this.payTime;
	}

	/**
	 * ����֧��ʱ���setter����
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	/**
	 * ����֧����ʽ��getter����
	 */
	@Column(name = "PAYMENTMETHOD")
	@JSON(serialize=false)  
	public Integer getPaymentMethod() {
		return paymentMethod;
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
	@JSON(serialize=false)  
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
	 * ����֧��״̬���Ƶ�getter����
	 */

	@Column(name = "PAYSTATUSNAME")
	@JSON(serialize=false)  
	public String getPayStatusName() {
		return this.payStatusName;
	}

	/**
	 * ����֧��״̬���Ƶ�setter����
	 */
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}

	/**
	 * ����֧��״̬������getter����
	 */

	@Column(name = "PAYSTATUSDESC")
	@JSON(serialize=false)  
	public String getPayStatusDesc() {
		return this.payStatusDesc;
	}

	/**
	 * ����֧��״̬������setter����
	 */
	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	/**
	 * �����ύʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTIME")
	@JSON(serialize=false)  
	public Date getSubmitTime() {
		return this.submitTime;
	}

	/**
	 * �����ύʱ���setter����
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * �������ض����ŵ�getter����
	 */

	@Column(name = "GATEWAYORDERNO")
	@JSON(serialize=false)  
	public String getGateWayOrderNo() {
		return this.gateWayOrderNo;
	}

	/**
	 * �������ض����ŵ�setter����
	 */
	public void setGateWayOrderNo(String gateWayOrderNo) {
		this.gateWayOrderNo = gateWayOrderNo;
	}

	/**
	 * �������ж����ŵ�getter����
	 */

	@Column(name = "BANKORDERNO")
	@JSON(serialize=false)  
	public String getBankOrderNo() {
		return this.bankOrderNo;
	}

	/**
	 * �������ж����ŵ�setter����
	 */
	public void setBankOrderNo(String bankOrderNo) {
		this.bankOrderNo = bankOrderNo;
	}

	/**
	 * �����˵���ʾ��getter����
	 */

	@Column(name = "REFUNDMENTFLAG")
	@JSON(serialize=false)  
	public String getRefundmentFlag() {
		return this.refundmentFlag;
	}

	/**
	 * �����˵���ʾ��setter����
	 */
	public void setRefundmentFlag(String refundmentFlag) {
		this.refundmentFlag = refundmentFlag;
	}
	
	/**
	 * ���Բ���Ա��getter����
	 */

	@Column(name = "OPERATORID")
	@JSON(serialize=false)  
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
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JSON(serialize=false)  
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

	@Column(name = "orderDesc")
	@JSON(serialize=false)  
	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	@Column(name = "bankType")
	@JSON(serialize=false)  
	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	@JSON(serialize=false)  
	public String getPaymentMessage() {
		return paymentMessage;
	}

	public void setPaymentMessage(String paymentMessage) {
		this.paymentMessage = paymentMessage;
	}
	
	@JSON(serialize=false)  
	@Column(name = "REMINDCOUNT")
	public Integer getRemindCount() {
		return remindCount;
	}

	public void setRemindCount(Integer remindCount) {
		this.remindCount = remindCount;
	}
	
}
