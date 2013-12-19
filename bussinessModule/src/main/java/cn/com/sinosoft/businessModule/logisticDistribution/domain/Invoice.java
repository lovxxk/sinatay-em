package cn.com.sinosoft.businessModule.logisticDistribution.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��Invoice
 */
@Entity
@Table(name = "INVOICE", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class Invoice implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;

	/** ���Է�Ʊ���� */
	private Integer invoiceType;

	/** ���Է�Ʊ̧ͷ */
	private String invoiceTitle;

	/** ���Է�Ʊ���� */
	private String invoinceContent;

	/** ���Ե�λ���� */
	private String companyName;

	/** ����ע���ַ */
	private String registeredAddress;

	/** ����ע��绰 */
	private String registeredPhone;

	/** ���Կ������� */
	private String openingBank;

	/** ����˰�� */
	private String taxRegistryNo;

	/** ���Է�Ʊ���� */
	private String invoiceCode;

	/** ���Է�Ʊ�� */
	private String invoiceNo;

	/** ���Դ�ӡ������ */
	private String printNo;

	/** ���Դ�ӡ����� */
	private String printerMachineNo;

	/** ����˰���� */
	private String taxControlCode;

	/** ���Ի��ҷ��� */
	private String currencySymbol;

	/** ����Сд�ϼ� */
	private BigDecimal totalInFigures;

	/** ���Դ�д�ϼ� */
	private String totalInLetters;

	/** �����տλ */
	private String payee;

	/** ���Ը��λ(����) */
	private String payer;

	/** ���������ʻ� */
	private String bankAccount;

	/** �����տ�Ա */
	private Date cashier;

	/** �����տλӡ�� */
	private String payeeSeal;

	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/** ����invoiceItems */
	private List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>(0);

	/**
	 * ��Invoice��Ĭ�Ϲ��췽��
	 */
	public Invoice() {
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
	 * ���Է�Ʊ���͵�getter����
	 */

	@Column(name = "INVOICETYPE")
	public Integer getInvoiceType() {
		return this.invoiceType;
	}

	/**
	 * ���Է�Ʊ���͵�setter����
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * ���Է�Ʊ̧ͷ��getter����
	 */

	@Column(name = "INVOICETITLE")
	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	/**
	 * ���Է�Ʊ̧ͷ��setter����
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	/**
	 * ���Է�Ʊ���ݵ�getter����
	 */

	@Column(name = "INVOINCECONTENT")
	public String getInvoinceContent() {
		return this.invoinceContent;
	}

	/**
	 * ���Է�Ʊ���ݵ�setter����
	 */
	public void setInvoinceContent(String invoinceContent) {
		this.invoinceContent = invoinceContent;
	}

	/**
	 * ���Ե�λ���Ƶ�getter����
	 */

	@Column(name = "COMPANYNAME")
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * ���Ե�λ���Ƶ�setter����
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * ����ע���ַ��getter����
	 */

	@Column(name = "REGISTEREDADDRESS")
	public String getRegisteredAddress() {
		return this.registeredAddress;
	}

	/**
	 * ����ע���ַ��setter����
	 */
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	/**
	 * ����ע��绰��getter����
	 */

	@Column(name = "REGISTEREDPHONE")
	public String getRegisteredPhone() {
		return this.registeredPhone;
	}

	/**
	 * ����ע��绰��setter����
	 */
	public void setRegisteredPhone(String registeredPhone) {
		this.registeredPhone = registeredPhone;
	}

	/**
	 * ���Կ������е�getter����
	 */

	@Column(name = "OPENINGBANK")
	public String getOpeningBank() {
		return this.openingBank;
	}

	/**
	 * ���Կ������е�setter����
	 */
	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	/**
	 * ����˰�ŵ�getter����
	 */

	@Column(name = "TAXREGISTRYNO")
	public String getTaxRegistryNo() {
		return this.taxRegistryNo;
	}

	/**
	 * ����˰�ŵ�setter����
	 */
	public void setTaxRegistryNo(String taxRegistryNo) {
		this.taxRegistryNo = taxRegistryNo;
	}

	/**
	 * ���Է�Ʊ�����getter����
	 */

	@Column(name = "INVOICECODE")
	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	/**
	 * ���Է�Ʊ�����setter����
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	/**
	 * ���Է�Ʊ�ŵ�getter����
	 */

	@Column(name = "INVOICENO")
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	/**
	 * ���Է�Ʊ�ŵ�setter����
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * ���Դ�ӡ�������getter����
	 */

	@Column(name = "PRINTNO")
	public String getPrintNo() {
		return this.printNo;
	}

	/**
	 * ���Դ�ӡ�������setter����
	 */
	public void setPrintNo(String printNo) {
		this.printNo = printNo;
	}

	/**
	 * ���Դ�ӡ����ŵ�getter����
	 */

	@Column(name = "PRINTERMACHINENO")
	public String getPrinterMachineNo() {
		return this.printerMachineNo;
	}

	/**
	 * ���Դ�ӡ����ŵ�setter����
	 */
	public void setPrinterMachineNo(String printerMachineNo) {
		this.printerMachineNo = printerMachineNo;
	}

	/**
	 * ����˰�����getter����
	 */

	@Column(name = "TAXCONTROLCODE")
	public String getTaxControlCode() {
		return this.taxControlCode;
	}

	/**
	 * ����˰�����setter����
	 */
	public void setTaxControlCode(String taxControlCode) {
		this.taxControlCode = taxControlCode;
	}

	/**
	 * ���Ի��ҷ��ŵ�getter����
	 */

	@Column(name = "CURRENCYSYMBOL")
	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	/**
	 * ���Ի��ҷ��ŵ�setter����
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	/**
	 * ����Сд�ϼƵ�getter����
	 */

	@Column(name = "TOTALINFIGURES")
	public BigDecimal getTotalInFigures() {
		return this.totalInFigures;
	}

	/**
	 * ����Сд�ϼƵ�setter����
	 */
	public void setTotalInFigures(BigDecimal totalInFigures) {
		this.totalInFigures = totalInFigures;
	}

	/**
	 * ���Դ�д�ϼƵ�getter����
	 */

	@Column(name = "TOTALINLETTERS")
	public String getTotalInLetters() {
		return this.totalInLetters;
	}

	/**
	 * ���Դ�д�ϼƵ�setter����
	 */
	public void setTotalInLetters(String totalInLetters) {
		this.totalInLetters = totalInLetters;
	}

	/**
	 * �����տλ��getter����
	 */

	@Column(name = "PAYEE")
	public String getPayee() {
		return this.payee;
	}

	/**
	 * �����տλ��setter����
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * ���Ը��λ(����)��getter����
	 */

	@Column(name = "PAYER")
	public String getPayer() {
		return this.payer;
	}

	/**
	 * ���Ը��λ(����)��setter����
	 */
	public void setPayer(String payer) {
		this.payer = payer;
	}

	/**
	 * ���������ʻ���getter����
	 */

	@Column(name = "BANKACCOUNT")
	public String getBankAccount() {
		return this.bankAccount;
	}

	/**
	 * ���������ʻ���setter����
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * �����տ�Ա��getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CASHIER")
	public Date getCashier() {
		return this.cashier;
	}

	/**
	 * �����տ�Ա��setter����
	 */
	public void setCashier(Date cashier) {
		this.cashier = cashier;
	}

	/**
	 * �����տλӡ�µ�getter����
	 */

	@Column(name = "PAYEESEAL")
	public String getPayeeSeal() {
		return this.payeeSeal;
	}

	/**
	 * �����տλӡ�µ�setter����
	 */
	public void setPayeeSeal(String payeeSeal) {
		this.payeeSeal = payeeSeal;
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
	 * ������ӱ���ʱͬʱ����Ʊ��Ϣ��ֵ����������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getInvoice() == null) {
			getInsurancePolicy().setInvoice(this);
		}
	}
	
	/**
	 * ����invoiceItems��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
	public List<InvoiceItem> getInvoiceItems() {
		return this.invoiceItems;
	}
	
	/**
	 * ����invoiceItems��setter����
	 */
	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	/**
	 * �����������invoiceItems��setter����
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
