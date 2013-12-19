package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
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
 * POJO��GeSaleProInformbook
 */
@Entity
@Table(name = "GE_SALE_PRO_INFORMBOOK")
public class GeSaleProInformbook implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ���Ը�֪˳�� */
	private String informOrder;

	/** ���Ը�֪���� */
	private String informContent;

	/** ���Ը�֪ѡ�� */
	private String informOption;

	/**
	 * ��GeSaleProInformbook��Ĭ�Ϲ��췽��
	 */
	public GeSaleProInformbook() {
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
	 * ���Ը�֪˳���getter����
	 */

	@Column(name = "INFORMORDER")
	public String getInformOrder() {
		return this.informOrder;
	}
	/**
	 * ���Ը�֪˳���setter����
	 */
	public void setInformOrder(String informOrder) {
		this.informOrder = informOrder;
	}
	/**
	 * ���Ը�֪���ݵ�getter����
	 */

	@Column(name = "INFORMCONTENT")
	public String getInformContent() {
		return this.informContent;
	}
	/**
	 * ���Ը�֪���ݵ�setter����
	 */
	public void setInformContent(String informContent) {
		this.informContent = informContent;
	}
	/**
	 * ���Ը�֪ѡ���getter����
	 */

	@Column(name = "INFORMOPTION")
	public String getInformOption() {
		return this.informOption;
	}
	/**
	 * ���Ը�֪ѡ���setter����
	 */
	public void setInformOption(String informOption) {
		this.informOption = informOption;
	}

}
