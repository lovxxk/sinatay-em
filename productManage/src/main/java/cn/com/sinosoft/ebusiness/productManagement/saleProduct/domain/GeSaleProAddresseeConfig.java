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
 * POJO��GeSaleProAddresseeConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_ADDRESSEE_CONFIG")
public class GeSaleProAddresseeConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** �����ռ������� */
	private String consigneeName;

	/** ������ϵ�绰 */
	private String telephone;

	/** ���Թ̶��绰 */
	private String fixedPhone;

	/** ����ʡ */
	private String province;

	/** ������ */
	private String city;

	/** ������/�� */
	private String county;

	/** �����ռ���ַ */
	private String consigneeAddress;

	/** ������������ */
	private String zipCode;

	/** ���Ե������� */
	private String email;

	/** ����������Ϣ���� */
	private String consigneeConfig;

	/** ���Ա�ע */
	private String remark;

	/**
	 * ��GeSaleProAddresseeConfig��Ĭ�Ϲ��췽��
	 */
	public GeSaleProAddresseeConfig() {
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
	 * �����ռ���������getter����
	 */

	@Column(name = "CONSIGNEENAME")
	public String getConsigneeName() {
		return this.consigneeName;
	}
	/**
	 * �����ռ���������setter����
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	/**
	 * ������ϵ�绰��getter����
	 */

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}
	/**
	 * ������ϵ�绰��setter����
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * ���Թ̶��绰��getter����
	 */

	@Column(name = "FIXEDPHONE")
	public String getFixedPhone() {
		return this.fixedPhone;
	}
	/**
	 * ���Թ̶��绰��setter����
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	/**
	 * ����ʡ��getter����
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}
	/**
	 * ����ʡ��setter����
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * �����е�getter����
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}
	/**
	 * �����е�setter����
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * ������/�ص�getter����
	 */

	@Column(name = "COUNTY")
	public String getCounty() {
		return this.county;
	}
	/**
	 * ������/�ص�setter����
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * �����ռ���ַ��getter����
	 */

	@Column(name = "CONSIGNEEADDRESS")
	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}
	/**
	 * �����ռ���ַ��setter����
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 * �������������getter����
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}
	/**
	 * �������������setter����
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * ���Ե��������getter����
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	/**
	 * ���Ե��������setter����
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * ����������Ϣ���õ�getter����
	 */

	@Column(name = "CONSIGNEECONFIG")
	public String getConsigneeConfig() {
		return this.consigneeConfig;
	}
	/**
	 * ����������Ϣ���õ�setter����
	 */
	public void setConsigneeConfig(String consigneeConfig) {
		this.consigneeConfig = consigneeConfig;
	}
	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	/**
	 * ���Ա�ע��setter����
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
