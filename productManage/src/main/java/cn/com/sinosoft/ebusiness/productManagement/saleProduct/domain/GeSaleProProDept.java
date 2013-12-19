package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GeSaleProProDept
 */
@Entity
@Table(name = "GE_SALE_PRO_PRODEPT")
public class GeSaleProProDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性管理机构代码 */
	private String deptID;

	/** 属性geSaleProSaleDepPayments */
	private List<GeSaleProSaleDepPayment> geSaleProSaleDepPayments = new ArrayList<GeSaleProSaleDepPayment>(
			0);

	/**
	 * 类GeSaleProProDept的默认构造方法
	 */
	public GeSaleProProDept() {
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
	 * 属性geSaleProduct的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * 属性geSaleProduct的setter方法
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * 属性管理机构代码的getter方法
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}
	/**
	 * 属性管理机构代码的setter方法
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	/**
	 * 属性geSaleProSaleDepPayments的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProProDept")
	public List<GeSaleProSaleDepPayment> getGeSaleProSaleDepPayments() {
		return this.geSaleProSaleDepPayments;
	}
	/**
	 * 属性geSaleProSaleDepPayments的setter方法
	 */
	public void setGeSaleProSaleDepPayments(
			List<GeSaleProSaleDepPayment> geSaleProSaleDepPayments) {
		this.geSaleProSaleDepPayments = geSaleProSaleDepPayments;
	}

}
