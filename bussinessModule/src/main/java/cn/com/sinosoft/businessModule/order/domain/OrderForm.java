package cn.com.sinosoft.businessModule.order.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类Order
 */
@Entity
@Table(name = "ORDERFORM", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class OrderForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性保单 */
	private InsurancePolicy insurancePolicy;

	/** 属性订单号 */
	private String orderSerialNumber;
	
	/** 属性合作机构订单号（淘宝订单号） */
	private String merchantOrderNumber;
	
	/** 属性交易流水号 */
	private String transSerialNumber;
	
	/** 属性商品代码 */
	private String productCode;

	/** 属性商品名称 */
	private String productName;

	/** 属性商品数量 */
	private Integer productNumber;

	/** 属性商品描述 */
	private String productDesc;

	/** 属性支付人联系方式类型 (1代表邮件 2手机) */
	private Integer payerContactType;

	/** 属性支付人联系方式 */
	private String payerContact;

	/** 属性支付人IP */
	private String payerIP;

	/** 属性个人客户 */
	private String personalUserSerialNo;

	/** 属性用户号 */
	private String customerID;

	/** 属性订单类型 */
	private Integer orderType;

	/** 属性订单状态 */
	private Integer orderStatus;

	/** 属性订单状态名称 */
	private String orderStatusName;

	/** 属性订单状态描述 */
	private String orderStatusDesc;

	/** 属性订单金额 */
	private BigDecimal orderAmount;

	/** 属性支付方式 */
	private Integer payMethod;

	/** 属性支付时间 */
	private Date payTime;
	
	/** 属性支付方式	 */
	private Integer paymentMethod;
	
	/** 属性支付状态 */
	private Integer payStatus;

	/** 属性支付状态名称 */
	private String payStatusName;

	/** 属性支付状态描述 */
	private String payStatusDesc;

	/** 属性提交时间 */
	private Date submitTime;

	/** 属性网关订单号 */
	private String gateWayOrderNo;

	/** 属性银行订单号 */
	private String bankOrderNo;

	/** 属性退单标示 */
	private String refundmentFlag;
	
	/** 属性操作员 */
	private String operatorID;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/** 订单描述 */
	private String orderDesc;
	
	/**银行类型*/
	private String bankType;
	
	/**支付成功或失败信息*/
	private String paymentMessage;
	
	/**提醒次数*/
	private Integer remindCount = 0;
	
	/**
	 * 类Order的默认构造方法
	 */
	public OrderForm() {
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
	 * 属性保单的getter方法
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * 属性保单的setter方法
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	
	/**
	 * 属性添加保单时同时将订单信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getOrderForm() == null) {
			getInsurancePolicy().setOrderForm(this);
		}
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
	 * 属性合作机构订单号的getter方法
	 */
	@Column(name = "merchantOrderNumber")
	@JSON(serialize=false) 
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * 属性合作机构订单号的setter方法
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}

	/**
	 * 属性交易流水号的getter方法
	 */
	@Column(name = "TRANSSERIALNUMBER")
	@JSON(serialize=false)  
	public String getTransSerialNumber() {
		return this.transSerialNumber;
	}

	/**
	 * 属性交易流水号的setter方法
	 */
	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}
	

	/**
	 * 属性产品代码的getter方法
	 */

	@Column(name = "PRODUCTCODE")
	@JSON(serialize=false)  
	public String getProductCode() {
		return this.productCode;
	}

	/**
	 * 属性产品代码的setter方法
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 属性产品名称的getter方法
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}

	/**
	 * 属性产品名称的setter方法
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 属性产品数量的getter方法
	 */

	@Column(name = "PRODUCTNUMBER")
	public Integer getProductNumber() {
		return this.productNumber;
	}

	/**
	 * 属性产品数量的setter方法
	 */
	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	/**
	 * 属性产品描述的getter方法
	 */

	@Column(name = "PRODUCTDESC")
	@JSON(serialize=false)  
	public String getProductDesc() {
		return this.productDesc;
	}

	/**
	 * 属性产品描述的setter方法
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * 属性支付人联系方式类型 (1代表邮件 2手机)的getter方法
	 */

	@Column(name = "PAYERCONTACTTYPE")
	@JSON(serialize=false)  
	public Integer getPayerContactType() {
		return this.payerContactType;
	}

	/**
	 * 属性支付人联系方式类型 (1代表邮件 2手机)的setter方法
	 */
	public void setPayerContactType(Integer payerContactType) {
		this.payerContactType = payerContactType;
	}

	/**
	 * 属性支付人联系方式的getter方法
	 */

	@Column(name = "PAYERCONTACT")
	@JSON(serialize=false)  
	public String getPayerContact() {
		return this.payerContact;
	}

	/**
	 * 属性支付人联系方式的setter方法
	 */
	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}

	/**
	 * 属性支付人IP的getter方法
	 */

	@Column(name = "PAYERIP")
	@JSON(serialize=false)  
	public String getPayerIP() {
		return this.payerIP;
	}

	/**
	 * 属性支付人IP的setter方法
	 */
	public void setPayerIP(String payerIP) {
		this.payerIP = payerIP;
	}

	/**
	 * 属性个人客户的getter方法
	 */

	@Column(name = "PERSONALUSERSERIALNO")
	@JSON(serialize=false)  
	public String getPersonalUserSerialNo() {
		return this.personalUserSerialNo;
	}

	/**
	 * 属性个人客户的setter方法
	 */
	public void setPersonalUserSerialNo(String personalUserSerialNo) {
		this.personalUserSerialNo = personalUserSerialNo;
	}

	/**
	 * 属性用户号的getter方法
	 */

	@Column(name = "CUSTOMERID")
	@JSON(serialize=false)  
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * 属性订单类型的getter方法
	 */

	@Column(name = "ORDERTYPE")
	@JSON(serialize=false)  
	public Integer getOrderType() {
		return this.orderType;
	}

	/**
	 * 属性订单类型的setter方法
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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
	 * 属性订单状态名称的getter方法
	 */

	@Column(name = "ORDERSTATUSNAME")
	@JSON(serialize=false)  
	public String getOrderStatusName() {
		return this.orderStatusName;
	}

	/**
	 * 属性订单状态名称的setter方法
	 */
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	/**
	 * 属性订单状态描述的getter方法
	 */

	@Column(name = "ORDERSTATUSDESC")
	@JSON(serialize=false)  
	public String getOrderStatusDesc() {
		return this.orderStatusDesc;
	}

	/**
	 * 属性订单状态描述的setter方法
	 */
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	/**
	 * 属性订单金额的getter方法
	 */

	@Column(name = "ORDERAMOUNT")
	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	/**
	 * 属性订单金额的setter方法
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 属性支付方式的getter方法
	 */

	@Column(name = "PAYMETHOD")
	@JSON(serialize=false)  
	public Integer getPayMethod() {
		return this.payMethod;
	}

	/**
	 * 属性支付方式的setter方法
	 */
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * 属性支付时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYTIME")
	public Date getPayTime() {
		return this.payTime;
	}

	/**
	 * 属性支付时间的setter方法
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	/**
	 * 属性支付方式的getter方法
	 */
	@Column(name = "PAYMENTMETHOD")
	@JSON(serialize=false)  
	public Integer getPaymentMethod() {
		return paymentMethod;
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
	@JSON(serialize=false)  
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
	 * 属性支付状态名称的getter方法
	 */

	@Column(name = "PAYSTATUSNAME")
	@JSON(serialize=false)  
	public String getPayStatusName() {
		return this.payStatusName;
	}

	/**
	 * 属性支付状态名称的setter方法
	 */
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}

	/**
	 * 属性支付状态描述的getter方法
	 */

	@Column(name = "PAYSTATUSDESC")
	@JSON(serialize=false)  
	public String getPayStatusDesc() {
		return this.payStatusDesc;
	}

	/**
	 * 属性支付状态描述的setter方法
	 */
	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	/**
	 * 属性提交时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTIME")
	@JSON(serialize=false)  
	public Date getSubmitTime() {
		return this.submitTime;
	}

	/**
	 * 属性提交时间的setter方法
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * 属性网关订单号的getter方法
	 */

	@Column(name = "GATEWAYORDERNO")
	@JSON(serialize=false)  
	public String getGateWayOrderNo() {
		return this.gateWayOrderNo;
	}

	/**
	 * 属性网关订单号的setter方法
	 */
	public void setGateWayOrderNo(String gateWayOrderNo) {
		this.gateWayOrderNo = gateWayOrderNo;
	}

	/**
	 * 属性银行订单号的getter方法
	 */

	@Column(name = "BANKORDERNO")
	@JSON(serialize=false)  
	public String getBankOrderNo() {
		return this.bankOrderNo;
	}

	/**
	 * 属性银行订单号的setter方法
	 */
	public void setBankOrderNo(String bankOrderNo) {
		this.bankOrderNo = bankOrderNo;
	}

	/**
	 * 属性退单标示的getter方法
	 */

	@Column(name = "REFUNDMENTFLAG")
	@JSON(serialize=false)  
	public String getRefundmentFlag() {
		return this.refundmentFlag;
	}

	/**
	 * 属性退单标示的setter方法
	 */
	public void setRefundmentFlag(String refundmentFlag) {
		this.refundmentFlag = refundmentFlag;
	}
	
	/**
	 * 属性操作员的getter方法
	 */

	@Column(name = "OPERATORID")
	@JSON(serialize=false)  
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
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JSON(serialize=false)  
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
