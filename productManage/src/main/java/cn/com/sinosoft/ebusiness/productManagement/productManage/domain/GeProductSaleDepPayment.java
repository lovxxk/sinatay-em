package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeProductSaleDepPayment
 */
@Entity
@Table(name = "GE_PRODUCTSALEDEPPAYMENT")
public class GeProductSaleDepPayment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ������� */
	private GeProductProDept geProductProDept;

	/** ����֧����ʽ */
	private GePayment gePayment;

	/**
	 * ��GeProductSaleDepPayment��Ĭ�Ϲ��췽��
	 */
	public GeProductSaleDepPayment() {
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
	 * ���Բ�Ʒ���������getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEDEPSERIALNO")
	public GeProductProDept getGeProductProDept() {
		return this.geProductProDept;
	}
	/**
	 * ���Բ�Ʒ���������setter����
	 */
	public void setGeProductProDept(GeProductProDept geProductProDept) {
		this.geProductProDept = geProductProDept;
	}
	/**
	 * ����֧����ʽ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENTCODE")
	public GePayment getGePayment() {
		return this.gePayment;
	}
	/**
	 * ����֧����ʽ��setter����
	 */
	public void setGePayment(GePayment gePayment) {
		this.gePayment = gePayment;
	}

}
