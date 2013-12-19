package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
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
 * POJO��GeProductProDept
 */
@Entity
@Table(name = "GE_PRODUCT_PRODEPT")
public class GeProductProDept implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Թ���������� */
	private String deptID;

	/** ����geProductSaleDepPayments */
	private List<GeProductSaleDepPayment> geProductSaleDepPayments = new ArrayList<GeProductSaleDepPayment>(
			0);

	/**
	 * ��GeProductProDept��Ĭ�Ϲ��췽��
	 */
	public GeProductProDept() {
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
	 * ���Բ�Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * ���Բ�Ʒ��setter����
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
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
	 * ����geProductSaleDepPayments��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductProDept")
	public List<GeProductSaleDepPayment> getGeProductSaleDepPayments() {
		return this.geProductSaleDepPayments;
	}
	/**
	 * ����geProductSaleDepPayments��setter����
	 */
	public void setGeProductSaleDepPayments(
			List<GeProductSaleDepPayment> geProductSaleDepPayments) {
		this.geProductSaleDepPayments = geProductSaleDepPayments;
	}

}
