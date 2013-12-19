package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
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
 * POJO类GeProductProDept
 */
@Entity
@Table(name = "GE_PRODUCT_PRODEPT")
public class GeProductProDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性管理机构代码 */
	private String deptID;

	/** 属性geProductSaleDepPayments */
	private List<GeProductSaleDepPayment> geProductSaleDepPayments = new ArrayList<GeProductSaleDepPayment>(
			0);

	/**
	 * 类GeProductProDept的默认构造方法
	 */
	public GeProductProDept() {
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
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
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
	 * 属性geProductSaleDepPayments的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductProDept")
	public List<GeProductSaleDepPayment> getGeProductSaleDepPayments() {
		return this.geProductSaleDepPayments;
	}
	/**
	 * 属性geProductSaleDepPayments的setter方法
	 */
	public void setGeProductSaleDepPayments(
			List<GeProductSaleDepPayment> geProductSaleDepPayments) {
		this.geProductSaleDepPayments = geProductSaleDepPayments;
	}

}
