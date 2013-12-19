package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeSaleProSaleDepPayment
 */
@Entity
@Table(name = "GE_SALE_PROSALEDEPPAYMENT")
public class GeSaleProSaleDepPayment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性 */
	private GeSaleProProDept geSaleProProDept;

	/** 属性支付方式 */
	private GePayment gePayment;

	/**
	 * 类GeSaleProSaleDepPayment的默认构造方法
	 */
	public GeSaleProSaleDepPayment() {
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
	 * 属性的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEDEPSERIALNO")
	public GeSaleProProDept getGeSaleProProDept() {
		return this.geSaleProProDept;
	}
	/**
	 * 属性的setter方法
	 */
	public void setGeSaleProProDept(GeSaleProProDept geSaleProProDept) {
		this.geSaleProProDept = geSaleProProDept;
	}
	/**
	 * 属性支付方式的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENTCODE")
	public GePayment getGePayment() {
		return this.gePayment;
	}
	/**
	 * 属性支付方式的setter方法
	 */
	public void setGePayment(GePayment gePayment) {
		this.gePayment = gePayment;
	}

}
