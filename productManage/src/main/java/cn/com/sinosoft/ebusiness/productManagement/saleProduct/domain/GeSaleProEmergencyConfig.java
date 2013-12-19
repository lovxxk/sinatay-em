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
 * POJO��GeSaleProEmergencyConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_EMERGENCY_CONFIG")
public class GeSaleProEmergencyConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ���Խ�����ϵ������ */
	private String emerName;

	/** ���Խ�����ϵ���ֻ� */
	private String mobile;

	/** ���Խ�����ϵ��Email */
	private String emerEmail;

	/**
	 * ��GeSaleProEmergencyConfig��Ĭ�Ϲ��췽��
	 */
	public GeSaleProEmergencyConfig() {
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
	@JoinColumn(name = "COREPRODUCTCODE")
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
	 * ���Խ�����ϵ��������getter����
	 */

	@Column(name = "EMERNAME")
	public String getEmerName() {
		return this.emerName;
	}
	/**
	 * ���Խ�����ϵ��������setter����
	 */
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	/**
	 * ���Խ�����ϵ���ֻ���getter����
	 */

	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * ���Խ�����ϵ���ֻ���setter����
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * ���Խ�����ϵ��Email��getter����
	 */

	@Column(name = "EMEREMAIL")
	public String getEmerEmail() {
		return this.emerEmail;
	}
	/**
	 * ���Խ�����ϵ��Email��setter����
	 */
	public void setEmerEmail(String emerEmail) {
		this.emerEmail = emerEmail;
	}

}
