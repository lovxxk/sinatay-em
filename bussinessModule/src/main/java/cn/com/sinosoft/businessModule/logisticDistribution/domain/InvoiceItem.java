package cn.com.sinosoft.businessModule.logisticDistribution.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类InvoiceItem
 */
@Entity
@Table(name = "INVOICEITEM")
public class InvoiceItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性发票 */
	private Invoice invoice;

	/** 属性项目名 */
	private String item;

	/** 属性单价 */
	private BigDecimal unitPrice;

	/** 属性数量 */
	private BigDecimal amount;

	/** 属性金额 */
	private BigDecimal amountChanged;

	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/**
	 * 类InvoiceItem的默认构造方法
	 */
	public InvoiceItem() {
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
	 * 属性项目名的getter方法
	 */

	@Column(name = "ITEM")
	public String getItem() {
		return this.item;
	}

	/**
	 * 属性项目名的setter方法
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * 属性单价的getter方法
	 */

	@Column(name = "UNITPRICE")
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	/**
	 * 属性单价的setter方法
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * 属性数量的getter方法
	 */

	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * 属性数量的setter方法
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 属性金额的getter方法
	 */

	@Column(name = "AMOUNTCHANGED")
	public BigDecimal getAmountChanged() {
		return this.amountChanged;
	}

	/**
	 * 属性金额的setter方法
	 */
	public void setAmountChanged(BigDecimal amountChanged) {
		this.amountChanged = amountChanged;
	}
	
	/**
	 * 属性发票的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVOICESERIALNO")
	public Invoice getInvoice() {
		return this.invoice;
	}

	/**
	 * 属性发票的setter方法
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	/**
	 * 添加发票时同时将发票详细信息赋值给发票对象
	 */
	public void addInvoice(Invoice invoice) {
		this.invoice = invoice;
		if (!getInvoice().getInvoiceItems().contains(this)) {
			getInvoice().getInvoiceItems().add(this);
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

}
