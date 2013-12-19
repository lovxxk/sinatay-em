package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeSaleProAddresseeConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_ADDRESSEE_CONFIG")
public class GeSaleProAddresseeConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性收件人姓名 */
	private String consigneeName;

	/** 属性联系电话 */
	private String telephone;

	/** 属性固定电话 */
	private String fixedPhone;

	/** 属性省 */
	private String province;

	/** 属性市 */
	private String city;

	/** 属性区/县 */
	private String county;

	/** 属性收件地址 */
	private String consigneeAddress;

	/** 属性邮政编码 */
	private String zipCode;

	/** 属性电子邮箱 */
	private String email;

	/** 属性配送信息配置 */
	private String consigneeConfig;

	/** 属性备注 */
	private String remark;

	/**
	 * 类GeSaleProAddresseeConfig的默认构造方法
	 */
	public GeSaleProAddresseeConfig() {
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
	@JoinColumn(name = "COREPRODUCTCODE")
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
	 * 属性收件人姓名的getter方法
	 */

	@Column(name = "CONSIGNEENAME")
	public String getConsigneeName() {
		return this.consigneeName;
	}
	/**
	 * 属性收件人姓名的setter方法
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	/**
	 * 属性联系电话的getter方法
	 */

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}
	/**
	 * 属性联系电话的setter方法
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 属性固定电话的getter方法
	 */

	@Column(name = "FIXEDPHONE")
	public String getFixedPhone() {
		return this.fixedPhone;
	}
	/**
	 * 属性固定电话的setter方法
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	/**
	 * 属性省的getter方法
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}
	/**
	 * 属性省的setter方法
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 属性市的getter方法
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}
	/**
	 * 属性市的setter方法
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 属性区/县的getter方法
	 */

	@Column(name = "COUNTY")
	public String getCounty() {
		return this.county;
	}
	/**
	 * 属性区/县的setter方法
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * 属性收件地址的getter方法
	 */

	@Column(name = "CONSIGNEEADDRESS")
	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}
	/**
	 * 属性收件地址的setter方法
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 * 属性邮政编码的getter方法
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}
	/**
	 * 属性邮政编码的setter方法
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * 属性电子邮箱的getter方法
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	/**
	 * 属性电子邮箱的setter方法
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 属性配送信息配置的getter方法
	 */

	@Column(name = "CONSIGNEECONFIG")
	public String getConsigneeConfig() {
		return this.consigneeConfig;
	}
	/**
	 * 属性配送信息配置的setter方法
	 */
	public void setConsigneeConfig(String consigneeConfig) {
		this.consigneeConfig = consigneeConfig;
	}
	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
