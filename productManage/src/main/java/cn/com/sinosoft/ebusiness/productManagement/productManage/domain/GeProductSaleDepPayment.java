package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeProductSaleDepPayment
 */
@Entity
@Table(name = "GE_PRODUCTSALEDEPPAYMENT")
public class GeProductSaleDepPayment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品管理机构 */
	private GeProductProDept geProductProDept;

	/** 属性支付方式 */
	private GePayment gePayment;

	/**
	 * 类GeProductSaleDepPayment的默认构造方法
	 */
	public GeProductSaleDepPayment() {
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
	 * 属性产品管理机构的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEDEPSERIALNO")
	public GeProductProDept getGeProductProDept() {
		return this.geProductProDept;
	}
	/**
	 * 属性产品管理机构的setter方法
	 */
	public void setGeProductProDept(GeProductProDept geProductProDept) {
		this.geProductProDept = geProductProDept;
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
