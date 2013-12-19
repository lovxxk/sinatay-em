package cn.com.sinosoft.businessModule.bankAccount.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��PaymentAccount
 */
@Entity
@Table(name = "PAYMENTACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class PaymentAccount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Խ��Ѷ����� */
	private String payOrderSerialNumber;
	
	/** ���Խɷ��˺� */
	private String accountNumber;

	/** ���Խɷ�ȷ���˺� */
	private String accountNumberConfirm;
	
	/** �����ʺų�����Id */
	private String acctHolderId;
	
	/** �����ʺų��������� */
	private String acctHolderName;
	
	/** �����ʺų��������֤���� */
	private String acctIdNumber;
	
	/** �����ʺų������ֻ��� */
	private String acctMobilePhone;
	
	/** ���Կ�/���� */
	private Integer cardOrBook;

	/** ���������ʺű��� */
	private String currencyTypeCode;

	/** ���������ʺ����� */
	private Integer bankAcctType;

	/** �������д��� */
	private String bankCode;

	/** ������������ */
	private String bankName;

	/** ���Է���/֧�д��� */
	private String bankBranchCode;
	
	/** ���Է���/֧������ */
	private String bankBranchName;
	
	/** �������п���Ч��*/
	private String cardEffectiveDate;
	
	/** �������ÿ�У����*/
	private String bankCardCVTwo;
	
	/** ����֧����� */
	private BigDecimal payAmount;
	
	/** ����֧��ʱ��*/
	private Date payTime;
	
	/** ���Զ��˵��� */
	private String checkPayNumber;
	
	/** ���Զ�������*/
	private Date accountTime;
	
	/** ���� ����ʡ���� */
	private String accProvince;
	
	/** ���� �����б��� */
	private String accCity;
	
	/** ���� ���������˻� */
	private String secBankCode;
	
	/** ���� �������б��� */
	private String secBankAccNo;
	
	/** ���� �����˻����� */
	private String secAccName;
	
	/** ���� ��������ʡ���� */
	private String secAccProvince;
	
	/** ���� ���������б��� */
	private String secAccCity;
	
	/** ���� �������п������� */
	private Integer secAccType;
	
	/** ���Բ���Ա */
	private String operatorID;
	
	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**���֧�����ʺ�*/
	private String buyerId;
	
	/**�ӿ�����**/
	private String exterface; 
	
	/**֧��������״̬   ��T����ʾ�ɹ�*/
	private String isSuccess;
	
	/**֪ͨУ��ID*/
	private String notifyId;
	
	/**֪ͨʱ��*/
	private String notifyTime;
	
	/**֪ͨ����*/
	private String notifyType;
	
	/**�����ţ�Ψһ��*/
	private String outTradeNo;
	
	/**֧�����ͣ�����ʱ�����֧�����ͣ�ԭֵ����*/
	private String paymentType;  
	
	/**����֧�����˻���*/
	private String sellerId; 
	
	/**��Ʒ����*/
	private String subject;
	
	/**֧�������׺�*/
	private String tradeNo;
	
	/**����״̬*/
	private String tradeStatus ;
	
	/**֧����ǩ��*/
	private String sign;
	
	/**ǩ����ʽ DSA/RSA/MD%5*/
	private String signType;
	
	/**body ��Ʒ����*/
	private String body;
	
	/**
	 * ��PaymentAccount��Ĭ�Ϲ��췽��
	 */
	public PaymentAccount() {
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
	 * ���ԽɷѶ����ŵ�getter����
	 */
	@Column(name = "PAYORDERSERIALNUMBER")
	public String getPayOrderSerialNumber() {
		return payOrderSerialNumber;
	}

	/**
	 * ���ԽɷѶ����ŵ�setter����
	 */
	public void setPayOrderSerialNumber(String payOrderSerialNumber) {
		this.payOrderSerialNumber = payOrderSerialNumber;
	}

	/**
	 * ���Զ��˵��ŵ�getter����
	 */
	@Column(name = "CHECKPAYNUMBER")
	public String getCheckPayNumber() {
		return checkPayNumber;
	}

	/**
	 * ���Զ��˵��ŵ�setter����
	 */
	public void setCheckPayNumber(String checkPayNumber) {
		this.checkPayNumber = checkPayNumber;
	}


	/**
	 * ���Խɷ��˺ŵ�getter����
	 */
	@Column(name = "ACCOUNTNUMBER")
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * ���Խɷ��˺ŵ�setter����
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * ���Խɷ�ȷ���˺ŵ�getter����
	 */

	@Column(name = "ACCOUNTNUMBERCONFIRM")
	public String getAccountNumberConfirm() {
		return this.accountNumberConfirm;
	}

	/**
	 * ���Խɷ�ȷ���˺ŵ�setter����
	 */
	public void setAccountNumberConfirm(String accountNumberConfirm) {
		this.accountNumberConfirm = accountNumberConfirm;
	}
	
	/**
	 * �����ʺų�����Id��getter����
	 */
	@Column(name = "ACCTHOLDERID")
	public String getAcctHolderId() {
		return acctHolderId;
	}
	
	/**
	 * �����ʺų�����Id��setter����
	 */
	public void setAcctHolderId(String acctHolderId) {
		this.acctHolderId = acctHolderId;
	}

	/**
	 * �����ʺų�����������getter����
	 */

	@Column(name = "ACCTHOLDERNAME")
	public String getAcctHolderName() {
		return this.acctHolderName;
	}

	/**
	 * �����ʺų�����������setter����
	 */
	public void setAcctHolderName(String acctHolderName) {
		this.acctHolderName = acctHolderName;
	}
	
	/**
	 * �ʺų��������֤�����getter����
	 * @return 
	 */
	@Column(name = "ACCTIDNUMBER")
	public String getAcctIdNumber() {
		return acctIdNumber;
	}
	
	/**
	 * �ʺų��������֤�����setter����
	 * @return 
	 */
	public void setAcctIdNumber(String acctIdNumber) {
		this.acctIdNumber = acctIdNumber;
	}

	/**
	 * �ʺų������ֻ��ŵ�getter����
	 * @return 
	 */
	@Column(name = "ACCTMOBILEPHONE")
	public String getAcctMobilePhone() {
		return acctMobilePhone;
	}
	
	/**
	 * �ʺų������ֻ��ŵ�setter����
	 * @return
	 */
	public void setAcctMobilePhone(String acctMobilePhone) {
		this.acctMobilePhone = acctMobilePhone;
	}

	/**
	 * ���Կ�/���۵�getter����
	 */

	@Column(name = "CARDORBOOK")
	public Integer getCardOrBook() {
		return this.cardOrBook;
	}

	/**
	 * ���Կ�/���۵�setter����
	 */
	public void setCardOrBook(Integer cardOrBook) {
		this.cardOrBook = cardOrBook;
	}

	/**
	 * ���������ʺű��ֵ�getter����
	 */

	@Column(name = "CURRENCYTYPECODE")
	public String getCurrencyTypeCode() {
		return this.currencyTypeCode;
	}

	/**
	 * ���������ʺű��ֵ�setter����
	 */
	public void setCurrencyTypeCode(String currencyTypeCode) {
		this.currencyTypeCode = currencyTypeCode;
	}

	/**
	 * ���������ʺ����͵�getter����
	 */

	@Column(name = "BANKACCTTYPE")
	public Integer getBankAcctType() {
		return this.bankAcctType;
	}
	
	/**
	 * ���������ʺ�����ö�����getter����
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
	 * ���������ʺ����ͺ���ֵ��getter����
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
	 * ���������ʺ������̼�ֵ��getter����
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
	 * ���������ʺ����͵�setter����
	 */
	public void setBankAcctType(Integer bankAcctType) {
		this.bankAcctType = bankAcctType;
	}
	
	/**
	 * ���������ʺ����͸�ֵ
	 */
	public void setEnumBankAcctType(BankAccountType  bankAccountType) {
		if (bankAcctType != null) {
			this.bankAcctType = bankAccountType.getValue();
		}
	}
	
	/**
	 * ���Ժ��������ʺ����͸�ֵ
	 */
	public void setBankAcctTypeByCoreValue(String coreValue) {
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByCoreValue(BankAccountType.class, coreValue);
		if (bankAcctType != null) {
			this.bankAcctType = bankAccountType.getValue();
		}
	}
	
	/**
	 * �����̼������ʺ����͸�ֵ
	 */
	public void setBankAcctTypeByMerchantValue(String merchantValue) {
		BankAccountType  bankAccountType = (BankAccountType) EnumDataUtils.getEnumDictionaryByMerchantValue(BankAccountType.class, merchantValue);
		if (bankAcctType != null) {
			this.bankAcctType = bankAccountType.getValue();
		}
	}
	
	
	/**
	 * �������д����getter����
	 */

	@Column(name = "BANKCODE")
	public String getBankCode() {
		return this.bankCode;
	}

	/**
	 * �������д����setter����
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * �����������Ƶ�getter����
	 */

	@Column(name = "BANKNAME")
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * �����������Ƶ�setter����
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * ���Է���/֧�д����getter����
	 */

	@Column(name = "BANKBRANCHCODE")
	public String getBankBranchCode() {
		return this.bankBranchCode;
	}

	/**
	 * ���Է���/֧�д����setter����
	 */
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	/**
	 * ���Է���/֧�����Ƶ�getter����
	 */

	@Column(name = "BANKBRANCHNAME")
	public String getBankBranchName() {
		return this.bankBranchName;
	}

	/**
	 * ���Է���/֧�����Ƶ�setter����
	 */
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	
	/**
	 * ���Կ���Ч�ڵ�get����
	 * @return
	 */
	@Column(name = "CARDEFFECTIVEDATE")
	public String getCardEffectiveDate() {
		return cardEffectiveDate;
	}

	/**
	 * ���Կ���Ч�ڵ�set����
	 * @return
	 */
	public void setCardEffectiveDate(String cardEffectiveDate) {
		this.cardEffectiveDate = cardEffectiveDate;
	}
	
	/**
	 * �������ÿ�У�����get����
	 * @return
	 */
	@Column(name = "BANKCARDCVTWO")
	public String getBankCardCVTwo() {
		return bankCardCVTwo;
	}
	
	/**
	 * �������ÿ�У�����set����
	 * @return
	 */
	public void setBankCardCVTwo(String bankCardCVTwo) {
		this.bankCardCVTwo = bankCardCVTwo;
	}
	
	/**
	 * ����֧������getter����
	 */
	@Column(name = "PAYAMOUNT")
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	/**
	 * ����֧������setter����
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	/**
	 * ����֧��ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PAYTIME")
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * ����֧��ʱ���setter����
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * ���Զ������ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCOUNTTIME")
	public Date getAccountTime() {
		return accountTime;
	}

	/**
	 * ���Զ������ڵ�setter����
	 */
	public void setAccountTime(Date accountTime) {
		this.accountTime = accountTime;
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
	 * ���Ա�����getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
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
		if (getInsurancePolicy() != null && getInsurancePolicy().getPaymentAccount() == null) {
			getInsurancePolicy().setPaymentAccount(this);
		}
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

	
	/**
	 * ���� ����ʡ���� ��getter����
	 */
	@Column(name = "ACCPROVINCE")
	public String getAccProvince() {
		return accProvince;
	}

	/**
	 * ���� ����ʡ���� ��setter����
	 */
	public void setAccProvince(String accProvince) {
		this.accProvince = accProvince;
	}

	/**
	 * ���� �����б��� ��getter����
	 */
	@Column(name = "ACCCITY")
	public String getAccCity() {
		return accCity;
	}

	/**
	 * ���� �����б��� ��setter����
	 */
	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	/**
	 * ���� ���������˻� ��getter����
	 */
	@Column(name = "SECBANKCODE")
	public String getSecBankCode() {
		return secBankCode;
	}

	/**
	 * ���� ���������˻� ��setter����
	 */
	public void setSecBankCode(String secBankCode) {
		this.secBankCode = secBankCode;
	}

	/**
	 * ���� �������б��� ��getter����
	 */
	@Column(name = "SECBANKACCNO")
	public String getSecBankAccNo() {
		return secBankAccNo;
	}

	/**
	 * ���� �������б��� ��setter����
	 */
	public void setSecBankAccNo(String secBankAccNo) {
		this.secBankAccNo = secBankAccNo;
	}

	/**
	 * ���� �����˻����� ��getter����
	 */
	@Column(name = "SECACCNAME")
	public String getSecAccName() {
		return secAccName;
	}

	/**
	 * ���� �����˻����� ��setter����
	 */
	public void setSecAccName(String secAccName) {
		this.secAccName = secAccName;
	}

	/**
	 * ���� ��������ʡ���� ��getter����
	 */
	@Column(name = "SECACCPROVINCE")
	public String getSecAccProvince() {
		return secAccProvince;
	}

	/**
	 * ���� ��������ʡ���� ��setter����
	 */
	public void setSecAccProvince(String secAccProvince) {
		this.secAccProvince = secAccProvince;
	}

	/**
	 * ���� ���������б��� ��getter����
	 */
	@Column(name = "SECACCCITY")
	public String getSecAccCity() {
		return secAccCity;
	}

	/**
	 * ���� ���������б��� ��setter����
	 */
	public void setSecAccCity(String secAccCity) {
		this.secAccCity = secAccCity;
	}

	/**
	 * ���� �������п������� ��getter����
	 */
	@Column(name = "SECACCTYPE")
	public Integer getSecAccType() {
		return secAccType;
	}

	/**
	 * ���� �������п������� ��setter����
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
