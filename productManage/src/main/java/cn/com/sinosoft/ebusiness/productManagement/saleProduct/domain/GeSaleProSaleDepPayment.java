package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeSaleProSaleDepPayment
 */
@Entity
@Table(name = "GE_SALE_PROSALEDEPPAYMENT")
public class GeSaleProSaleDepPayment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���� */
	private GeSaleProProDept geSaleProProDept;

	/** ����֧����ʽ */
	private GePayment gePayment;

	/**
	 * ��GeSaleProSaleDepPayment��Ĭ�Ϲ��췽��
	 */
	public GeSaleProSaleDepPayment() {
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
	 * ���Ե�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEDEPSERIALNO")
	public GeSaleProProDept getGeSaleProProDept() {
		return this.geSaleProProDept;
	}
	/**
	 * ���Ե�setter����
	 */
	public void setGeSaleProProDept(GeSaleProProDept geSaleProProDept) {
		this.geSaleProProDept = geSaleProProDept;
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
