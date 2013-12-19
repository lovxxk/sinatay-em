package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeSaleProProDept
 */
@Entity
@Table(name = "GE_SALE_PRO_PRODEPT")
public class GeSaleProProDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ���Թ���������� */
	private String deptID;

	/** ����geSaleProSaleDepPayments */
	private List<GeSaleProSaleDepPayment> geSaleProSaleDepPayments = new ArrayList<GeSaleProSaleDepPayment>(
			0);

	/**
	 * ��GeSaleProProDept��Ĭ�Ϲ��췽��
	 */
	public GeSaleProProDept() {
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
	 * ����geSaleProduct��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * ����geSaleProduct��setter����
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * ���Թ�����������getter����
	 */

	@Column(name = "DEPTID")
	public String getDeptID() {
		return this.deptID;
	}
	/**
	 * ���Թ�����������setter����
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	/**
	 * ����geSaleProSaleDepPayments��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProProDept")
	public List<GeSaleProSaleDepPayment> getGeSaleProSaleDepPayments() {
		return this.geSaleProSaleDepPayments;
	}
	/**
	 * ����geSaleProSaleDepPayments��setter����
	 */
	public void setGeSaleProSaleDepPayments(
			List<GeSaleProSaleDepPayment> geSaleProSaleDepPayments) {
		this.geSaleProSaleDepPayments = geSaleProSaleDepPayments;
	}

}
