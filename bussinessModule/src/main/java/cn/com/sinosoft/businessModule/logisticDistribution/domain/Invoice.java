package cn.com.sinosoft.businessModule.logisticDistribution.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

/**
 * POJO类Invoice
 */
@Entity
@Table(name = "INVOICE", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class Invoice implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性保单 */
	private InsurancePolicy insurancePolicy;

	/** 属性发票类型 */
	private Integer invoiceType;

	/** 属性发票抬头 */
	private String invoiceTitle;

	/** 属性发票内容 */
	private String invoinceContent;

	/** 属性单位名称 */
	private String companyName;

	/** 属性注册地址 */
	private String registeredAddress;

	/** 属性注册电话 */
	private String registeredPhone;

	/** 属性开户银行 */
	private String openingBank;

	/** 属性税号 */
	private String taxRegistryNo;

	/** 属性发票代码 */
	private String invoiceCode;

	/** 属性发票号 */
	private String invoiceNo;

	/** 属性打印机号码 */
	private String printNo;

	/** 属性打印机编号 */
	private String printerMachineNo;

	/** 属性税控码 */
	private String taxControlCode;

	/** 属性货币符号 */
	private String currencySymbol;

	/** 属性小写合计 */
	private BigDecimal totalInFigures;

	/** 属性大写合计 */
	private String totalInLetters;

	/** 属性收款单位 */
	private String payee;

	/** 属性付款单位(个人) */
	private String payer;

	/** 属性银行帐户 */
	private String bankAccount;

	/** 属性收款员 */
	private Date cashier;

	/** 属性收款单位印章 */
	private String payeeSeal;

	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/** 属性invoiceItems */
	private List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>(0);

	/**
	 * 类Invoice的默认构造方法
	 */
	public Invoice() {
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
	 * 属性发票类型的getter方法
	 */

	@Column(name = "INVOICETYPE")
	public Integer getInvoiceType() {
		return this.invoiceType;
	}

	/**
	 * 属性发票类型的setter方法
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * 属性发票抬头的getter方法
	 */

	@Column(name = "INVOICETITLE")
	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	/**
	 * 属性发票抬头的setter方法
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	/**
	 * 属性发票内容的getter方法
	 */

	@Column(name = "INVOINCECONTENT")
	public String getInvoinceContent() {
		return this.invoinceContent;
	}

	/**
	 * 属性发票内容的setter方法
	 */
	public void setInvoinceContent(String invoinceContent) {
		this.invoinceContent = invoinceContent;
	}

	/**
	 * 属性单位名称的getter方法
	 */

	@Column(name = "COMPANYNAME")
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * 属性单位名称的setter方法
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 属性注册地址的getter方法
	 */

	@Column(name = "REGISTEREDADDRESS")
	public String getRegisteredAddress() {
		return this.registeredAddress;
	}

	/**
	 * 属性注册地址的setter方法
	 */
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	/**
	 * 属性注册电话的getter方法
	 */

	@Column(name = "REGISTEREDPHONE")
	public String getRegisteredPhone() {
		return this.registeredPhone;
	}

	/**
	 * 属性注册电话的setter方法
	 */
	public void setRegisteredPhone(String registeredPhone) {
		this.registeredPhone = registeredPhone;
	}

	/**
	 * 属性开户银行的getter方法
	 */

	@Column(name = "OPENINGBANK")
	public String getOpeningBank() {
		return this.openingBank;
	}

	/**
	 * 属性开户银行的setter方法
	 */
	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	/**
	 * 属性税号的getter方法
	 */

	@Column(name = "TAXREGISTRYNO")
	public String getTaxRegistryNo() {
		return this.taxRegistryNo;
	}

	/**
	 * 属性税号的setter方法
	 */
	public void setTaxRegistryNo(String taxRegistryNo) {
		this.taxRegistryNo = taxRegistryNo;
	}

	/**
	 * 属性发票代码的getter方法
	 */

	@Column(name = "INVOICECODE")
	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	/**
	 * 属性发票代码的setter方法
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	/**
	 * 属性发票号的getter方法
	 */

	@Column(name = "INVOICENO")
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	/**
	 * 属性发票号的setter方法
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * 属性打印机号码的getter方法
	 */

	@Column(name = "PRINTNO")
	public String getPrintNo() {
		return this.printNo;
	}

	/**
	 * 属性打印机号码的setter方法
	 */
	public void setPrintNo(String printNo) {
		this.printNo = printNo;
	}

	/**
	 * 属性打印机编号的getter方法
	 */

	@Column(name = "PRINTERMACHINENO")
	public String getPrinterMachineNo() {
		return this.printerMachineNo;
	}

	/**
	 * 属性打印机编号的setter方法
	 */
	public void setPrinterMachineNo(String printerMachineNo) {
		this.printerMachineNo = printerMachineNo;
	}

	/**
	 * 属性税控码的getter方法
	 */

	@Column(name = "TAXCONTROLCODE")
	public String getTaxControlCode() {
		return this.taxControlCode;
	}

	/**
	 * 属性税控码的setter方法
	 */
	public void setTaxControlCode(String taxControlCode) {
		this.taxControlCode = taxControlCode;
	}

	/**
	 * 属性货币符号的getter方法
	 */

	@Column(name = "CURRENCYSYMBOL")
	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	/**
	 * 属性货币符号的setter方法
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	/**
	 * 属性小写合计的getter方法
	 */

	@Column(name = "TOTALINFIGURES")
	public BigDecimal getTotalInFigures() {
		return this.totalInFigures;
	}

	/**
	 * 属性小写合计的setter方法
	 */
	public void setTotalInFigures(BigDecimal totalInFigures) {
		this.totalInFigures = totalInFigures;
	}

	/**
	 * 属性大写合计的getter方法
	 */

	@Column(name = "TOTALINLETTERS")
	public String getTotalInLetters() {
		return this.totalInLetters;
	}

	/**
	 * 属性大写合计的setter方法
	 */
	public void setTotalInLetters(String totalInLetters) {
		this.totalInLetters = totalInLetters;
	}

	/**
	 * 属性收款单位的getter方法
	 */

	@Column(name = "PAYEE")
	public String getPayee() {
		return this.payee;
	}

	/**
	 * 属性收款单位的setter方法
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * 属性付款单位(个人)的getter方法
	 */

	@Column(name = "PAYER")
	public String getPayer() {
		return this.payer;
	}

	/**
	 * 属性付款单位(个人)的setter方法
	 */
	public void setPayer(String payer) {
		this.payer = payer;
	}

	/**
	 * 属性银行帐户的getter方法
	 */

	@Column(name = "BANKACCOUNT")
	public String getBankAccount() {
		return this.bankAccount;
	}

	/**
	 * 属性银行帐户的setter方法
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * 属性收款员的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CASHIER")
	public Date getCashier() {
		return this.cashier;
	}

	/**
	 * 属性收款员的setter方法
	 */
	public void setCashier(Date cashier) {
		this.cashier = cashier;
	}

	/**
	 * 属性收款单位印章的getter方法
	 */

	@Column(name = "PAYEESEAL")
	public String getPayeeSeal() {
		return this.payeeSeal;
	}

	/**
	 * 属性收款单位印章的setter方法
	 */
	public void setPayeeSeal(String payeeSeal) {
		this.payeeSeal = payeeSeal;
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
	 * 属性添加保单时同时将发票信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getInvoice() == null) {
			getInsurancePolicy().setInvoice(this);
		}
	}
	
	/**
	 * 属性invoiceItems的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
	public List<InvoiceItem> getInvoiceItems() {
		return this.invoiceItems;
	}
	
	/**
	 * 属性invoiceItems的setter方法
	 */
	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	/**
	 * 属性添加所有invoiceItems的setter方法
	 */
	public void addAllInvoiceItems(List<InvoiceItem> invoiceItems) {
		for (InvoiceItem invoiceItem:invoiceItems) {
			if (!getInvoiceItems().contains(invoiceItem)) {
				getInvoiceItems().add(invoiceItem);
			}
		}
		
		for (InvoiceItem invoiceItem:getInvoiceItems()) {
			if (invoiceItem.getInvoice() == null) {
				invoiceItem.setInvoice(this);
			}
			
		}
		this.invoiceItems = invoiceItems;
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
