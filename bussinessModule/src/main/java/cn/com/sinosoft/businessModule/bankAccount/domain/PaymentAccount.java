package cn.com.sinosoft.businessModule.bankAccount.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.enums.dictionary.BankAccountType;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类PaymentAccount
 */
@Entity
@Table(name = "PAYMENTACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class PaymentAccount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性交费订单号 */
	private String payOrderSerialNumber;
	
	/** 属性缴费账号 */
	private String accountNumber;

	/** 属性缴费确认账号 */
	private String accountNumberConfirm;
	
	/** 属性帐号持有人Id */
	private String acctHolderId;
	
	/** 属性帐号持有人姓名 */
	private String acctHolderName;
	
	/** 属性帐号持有人身份证号码 */
	private String acctIdNumber;
	
	/** 属性帐号持有人手机号 */
	private String acctMobilePhone;
	
	/** 属性卡/存折 */
	private Integer cardOrBook;

	/** 属性银行帐号币种 */
	private String currencyTypeCode;

	/** 属性银行帐号类型 */
	private Integer bankAcctType;

	/** 属性银行代码 */
	private String bankCode;

	/** 属性银行名称 */
	private String bankName;

	/** 属性分行/支行代码 */
	private String bankBranchCode;
	
	/** 属性分行/支行名称 */
	private String bankBranchName;
	
	/** 属性银行卡有效期*/
	private String cardEffectiveDate;
	
	/** 属性信用卡校验码*/
	private String bankCardCVTwo;
	
	/** 属性支付金额 */
	private BigDecimal payAmount;
	
	/** 属性支付时间*/
	private Date payTime;
	
	/** 属性对账单号 */
	private String checkPayNumber;
	
	/** 属性对账日期*/
	private Date accountTime;
	
	/** 属性 银行省编码 */
	private String accProvince;
	
	/** 属性 银行市编码 */
	private String accCity;
	
	/** 属性 续期银行账户 */
	private String secBankCode;
	
	/** 属性 续期银行编码 */
	private String secBankAccNo;
	
	/** 属性 续期账户姓名 */
	private String secAccName;
	
	/** 属性 续期银行省编码 */
	private String secAccProvince;
	
	/** 属性 续期银行市编码 */
	private String secAccCity;
	
	/** 属性 续期银行卡折类型 */
	private Integer secAccType;
	
	/** 属性操作员 */
	private String operatorID;
	
	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**买家支付宝帐号*/
	private String buyerId;
	
	/**接口名称**/
	private String exterface; 
	
	/**支付宝返回状态   ‘T’表示成功*/
	private String isSuccess;
	
	/**通知校验ID*/
	private String notifyId;
	
	/**通知时间*/
	private String notifyTime;
	
	/**通知类型*/
	private String notifyType;
	
	/**订单号（唯一）*/
	private String outTradeNo;
	
	/**支付类型，请求时传入的支付类型，原值返回*/
	private String paymentType;  
	
	/**卖家支付宝账户号*/
	private String sellerId; 
	
	/**商品名称*/
	private String subject;
	
	/**支付宝交易号*/
	private String tradeNo;
	
	/**交易状态*/
	private String tradeStatus ;
	
	/**支付宝签名*/
	private String sign;
	
	/**签名方式 DSA/RSA/MD%5*/
	private String signType;
	
	/**body 商品描述*/
	private String body;
	
	/**
	 * 类PaymentAccount的默认构造方法
	 */
	public PaymentAccount() {
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
	 * 属性缴费订单号的getter方法
	 */
	@Column(name = "PAYORDERSERIALNUMBER")
	public String getPayOrderSerialNumber() {
		return payOrderSerialNumber;
	}

	/**
	 * 属性缴费订单号的setter方法
	 */
	public void setPayOrderSerialNumber(String payOrderSerialNumber) {
		this.payOrderSerialNumber = payOrderSerialNumber;
	}

	/**
	 * 属性对账单号的getter方法
	 */
	@Column(name = "CHECKPAYNUMBER")
	public String getCheckPayNumber() {
		return checkPayNumber;
	}

	/**
	 * 属性对账单号的setter方法
	 */
	public void setCheckPayNumber(String checkPayNumber) {
		this.checkPayNumber = checkPayNumber;
	}


	/**
	 * 属性缴费账号的getter方法
	 */
	@Column(name = "ACCOUNTNUMBER")
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * 属性缴费账号的setter方法
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * 属性缴费确认账号的getter方法
	 */

	@Column(name = "ACCOUNTNUMBERCONFIRM")
	public String getAccountNumberConfirm() {
		return this.accountNumberConfirm;
	}

	/**
	 * 属性缴费确认账号的setter方法
	 */
	public void setAccountNumberConfirm(String accountNumberConfirm) {
		this.accountNumberConfirm = accountNumberConfirm;
	}
	
	/**
	 * 属性帐号持有人Id的getter方法
	 */
	@Column(name = "ACCTHOLDERID")
	public String getAcctHolderId() {
		return acctHolderId;
	}
	
	/**
	 * 属性帐号持有人Id的setter方法
	 */
	public void setAcctHolderId(String acctHolderId) {
		this.acctHolderId = acctHolderId;
	}

	/**
	 * 属性帐号持有人姓名的getter方法
	 */

	@Column(name = "ACCTHOLDERNAME")
	public String getAcctHolderName() {
		return this.acctHolderName;
	}

	/**
	 * 属性帐号持有人姓名的setter方法
	 */
	public void setAcctHolderName(String acctHolderName) {
		this.acctHolderName = acctHolderName;
	}
	
	/**
	 * 帐号持有人身份证号码的getter方法
	 * @return 
	 */
	@Column(name = "ACCTIDNUMBER")
	public String getAcctIdNumber() {
		return acctIdNumber;
	}
	
	/**
	 * 帐号持有人身份证号码的setter方法
	 * @return 
	 */
	public void setAcctIdNumber(String acctIdNumber) {
		this.acctIdNumber = acctIdNumber;
	}

	/**
	 * 帐号持有人手机号的getter方法
	 * @return 
	 */
	@Column(name = "ACCTMOBILEPHONE")
	public String getAcctMobilePhone() {
		return acctMobilePhone;
	}
	
	/**
	 * 帐号持有人手机号的setter方法
	 * @return
	 */
	public void setAcctMobilePhone(String acctMobilePhone) {
		this.acctMobilePhone = acctMobilePhone;
	}

	/**
	 * 属性卡/存折的getter方法
	 */

	@Column(name = "CARDORBOOK")
	public Integer getCardOrBook() {
		return this.cardOrBook;
	}

	/**
	 * 属性卡/存折的setter方法
	 */
	public void setCardOrBook(Integer cardOrBook) {
		this.cardOrBook = cardOrBook;
	}

	/**
	 * 属性银行帐号币种的getter方法
	 */

	@Column(name = "CURRENCYTYPECODE")
	public String getCurrencyTypeCode() {
		return this.currencyTypeCode;
	}

	/**
	 * 属性银行帐号币种的setter方法
	 */
	public void setCurrencyTypeCode(String currencyTypeCode) {
		this.currencyTypeCode = currencyTypeCode;
	}

	/**
	 * 属性银行帐号类型的getter方法
	 */

	@Column(name = "BANKACCTTYPE")
	public Integer getBankAcctType() {
		return this.bankAcctType;
	}
	
	/**
	 * 属性银行帐号类型枚举类的getter方法
	 */
	@Transient
	public BankAccountType getEnumBankAcctType() {
		if (getBankAcctType() == null) {
			return null;
		}
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByValue(BankAccountType.class, getBankAcctType());
		return bankAccountType;
	}
	
	/**
	 * 属性银行帐号类型核心值的getter方法
	 */
	@Transient
	public String getBankAcctTypeByCoreValue() {
		if (getBankAcctType() == null) {
			return "";
		}
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByValue(BankAccountType.class, getBankAcctType());
		return bankAccountType.getCoreValue();
	}
	
	/**
	 * 属性银行帐号类型商家值的getter方法
	 */
	@Transient
	public String getBankAcctTypeByMerchantValue() {
		if (getBankAcctType() == null) {
			return "";
		}
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByValue(BankAccountType.class, getBankAcctType());
		return bankAccountType.getMerchantValue();
	}
	
	
	/**
	 * 属性银行帐号类型的setter方法
	 */
	public void setBankAcctType(Integer bankAcctType) {
		this.bankAcctType = bankAcctType;
	}
	
	/**
	 * 属性银行帐号类型赋值
	 */
	public void setEnumBankAcctType(BankAccountType  bankAccountType) {
		if (bankAcctType != null) {
			this.bankAcctType = bankAccountType.getValue();
		}
	}
	
	/**
	 * 属性核心银行帐号类型赋值
	 */
	public void setBankAcctTypeByCoreValue(String coreValue) {
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByCoreValue(BankAccountType.class, coreValue);
		if (bankAcctType != null) {
			this.bankAcctType = bankAccountType.getValue();
		}
	}
	
	/**
	 * 属性商家银行帐号类型赋值
	 */
	public void setBankAcctTypeByMerchantValue(String merchantValue) {
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByMerchantValue(BankAccountType.class, merchantValue);
		if (bankAcctType != null) {
			this.bankAcctType = bankAccountType.getValue();
		}
	}
	
	
	/**
	 * 属性银行代码的getter方法
	 */

	@Column(name = "BANKCODE")
	public String getBankCode() {
		return this.bankCode;
	}

	/**
	 * 属性银行代码的setter方法
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * 属性银行名称的getter方法
	 */

	@Column(name = "BANKNAME")
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * 属性银行名称的setter方法
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * 属性分行/支行代码的getter方法
	 */

	@Column(name = "BANKBRANCHCODE")
	public String getBankBranchCode() {
		return this.bankBranchCode;
	}

	/**
	 * 属性分行/支行代码的setter方法
	 */
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	/**
	 * 属性分行/支行名称的getter方法
	 */

	@Column(name = "BANKBRANCHNAME")
	public String getBankBranchName() {
		return this.bankBranchName;
	}

	/**
	 * 属性分行/支行名称的setter方法
	 */
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	
	/**
	 * 属性卡有效期的get方法
	 * @return
	 */
	@Column(name = "CARDEFFECTIVEDATE")
	public String getCardEffectiveDate() {
		return cardEffectiveDate;
	}

	/**
	 * 属性卡有效期的set方法
	 * @return
	 */
	public void setCardEffectiveDate(String cardEffectiveDate) {
		this.cardEffectiveDate = cardEffectiveDate;
	}
	
	/**
	 * 属性信用卡校验码的get方法
	 * @return
	 */
	@Column(name = "BANKCARDCVTWO")
	public String getBankCardCVTwo() {
		return bankCardCVTwo;
	}
	
	/**
	 * 属性信用卡校验码的set方法
	 * @return
	 */
	public void setBankCardCVTwo(String bankCardCVTwo) {
		this.bankCardCVTwo = bankCardCVTwo;
	}
	
	/**
	 * 属性支付金额的getter方法
	 */
	@Column(name = "PAYAMOUNT")
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	/**
	 * 属性支付金额的setter方法
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * 属性支付时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PAYTIME")
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * 属性支付时间的setter方法
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * 属性对账日期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCOUNTTIME")
	public Date getAccountTime() {
		return accountTime;
	}

	/**
	 * 属性对账日期的setter方法
	 */
	public void setAccountTime(Date accountTime) {
		this.accountTime = accountTime;
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
	 * 属性保单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
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
	 * 属性添加保单时同时将付款信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getPaymentAccount() == null) {
			getInsurancePolicy().setPaymentAccount(this);
		}
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

	
	/**
	 * 属性 银行省编码 的getter方法
	 */
	@Column(name = "ACCPROVINCE")
	public String getAccProvince() {
		return accProvince;
	}

	/**
	 * 属性 银行省编码 的setter方法
	 */
	public void setAccProvince(String accProvince) {
		this.accProvince = accProvince;
	}

	/**
	 * 属性 银行市编码 的getter方法
	 */
	@Column(name = "ACCCITY")
	public String getAccCity() {
		return accCity;
	}

	/**
	 * 属性 银行市编码 的setter方法
	 */
	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	/**
	 * 属性 续期银行账户 的getter方法
	 */
	@Column(name = "SECBANKCODE")
	public String getSecBankCode() {
		return secBankCode;
	}

	/**
	 * 属性 续期银行账户 的setter方法
	 */
	public void setSecBankCode(String secBankCode) {
		this.secBankCode = secBankCode;
	}

	/**
	 * 属性 续期银行编码 的getter方法
	 */
	@Column(name = "SECBANKACCNO")
	public String getSecBankAccNo() {
		return secBankAccNo;
	}

	/**
	 * 属性 续期银行编码 的setter方法
	 */
	public void setSecBankAccNo(String secBankAccNo) {
		this.secBankAccNo = secBankAccNo;
	}

	/**
	 * 属性 续期账户姓名 的getter方法
	 */
	@Column(name = "SECACCNAME")
	public String getSecAccName() {
		return secAccName;
	}

	/**
	 * 属性 续期账户姓名 的setter方法
	 */
	public void setSecAccName(String secAccName) {
		this.secAccName = secAccName;
	}

	/**
	 * 属性 续期银行省编码 的getter方法
	 */
	@Column(name = "SECACCPROVINCE")
	public String getSecAccProvince() {
		return secAccProvince;
	}

	/**
	 * 属性 续期银行省编码 的setter方法
	 */
	public void setSecAccProvince(String secAccProvince) {
		this.secAccProvince = secAccProvince;
	}

	/**
	 * 属性 续期银行市编码 的getter方法
	 */
	@Column(name = "SECACCCITY")
	public String getSecAccCity() {
		return secAccCity;
	}

	/**
	 * 属性 续期银行市编码 的setter方法
	 */
	public void setSecAccCity(String secAccCity) {
		this.secAccCity = secAccCity;
	}

	/**
	 * 属性 续期银行卡折类型 的getter方法
	 */
	@Column(name = "SECACCTYPE")
	public Integer getSecAccType() {
		return secAccType;
	}

	/**
	 * 属性 续期银行卡折类型 的setter方法
	 */
	public void setSecAccType(Integer secAccType) {
		this.secAccType = secAccType;
	}

	@Column(name = "buyerId")
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "exterface")
	public String getExterface() {
		return exterface;
	}

	public void setExterface(String exterface) {
		this.exterface = exterface;
	}

	@Column(name = "isSuccess")
	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Column(name = "notifyId")
	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	@Column(name = "notifyTime")
	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Column(name = "notifyType")
	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	@Column(name = "outTradeNo")
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	@Column(name = "paymentType")
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "sellerId")
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "tradeNo")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "tradeStatus")
	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	@Column(name = "sign")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "signType")
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	@Column(name = "body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
