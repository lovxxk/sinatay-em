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
 * POJO��GeProductInformBook
 */
@Entity
@Table(name = "GE_PRODUCT_INFORMBOOK")
public class GeProductInformBook implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Ը�֪˳�� */
	private String informOrder;

	/** ���Ը�֪���� */
	private String informContent;

	/** ���Ը�֪ѡ�� */
	private String informOption;

	/**
	 * ��GeProductInformBook��Ĭ�Ϲ��췽��
	 */
	public GeProductInformBook() {
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
