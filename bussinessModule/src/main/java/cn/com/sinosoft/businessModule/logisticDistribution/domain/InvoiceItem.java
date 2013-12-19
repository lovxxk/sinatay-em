package cn.com.sinosoft.businessModule.logisticDistribution.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;
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
 * POJO��InvoiceItem
 */
@Entity
@Table(name = "INVOICEITEM")
public class InvoiceItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Է�Ʊ */
	private Invoice invoice;

	/** ������Ŀ�� */
	private String item;

	/** ���Ե��� */
	private BigDecimal unitPrice;

	/** �������� */
	private BigDecimal amount;

	/** ���Խ�� */
	private BigDecimal amountChanged;

	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ��InvoiceItem��Ĭ�Ϲ��췽��
	 */
	public InvoiceItem() {
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
	 * ������Ŀ����getter����
	 */

	@Column(name = "ITEM")
	public String getItem() {
		return this.item;
	}

	/**
	 * ������Ŀ����setter����
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * ���Ե��۵�getter����
	 */

	@Column(name = "UNITPRICE")
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	/**
	 * ���Ե��۵�setter����
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * ����������getter����
	 */

	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * ����������setter����
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * ���Խ���getter����
	 */

	@Column(name = "AMOUNTCHANGED")
	public BigDecimal getAmountChanged() {
		return this.amountChanged;
	}

	/**
	 * ���Խ���setter����
	 */
	public void setAmountChanged(BigDecimal amountChanged) {
		this.amountChanged = amountChanged;
	}
	
	/**
	 * ���Է�Ʊ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVOICESERIALNO")
	public Invoice getInvoice() {
		return this.invoice;
	}

	/**
	 * ���Է�Ʊ��setter����
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	/**
	 * ��ӷ�Ʊʱͬʱ����Ʊ��ϸ��Ϣ��ֵ����Ʊ����
	 */
	public void addInvoice(Invoice invoice) {
		this.invoice = invoice;
		if (!getInvoice().getInvoiceItems().contains(this)) {
			getInvoice().getInvoiceItems().add(this);
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

}
