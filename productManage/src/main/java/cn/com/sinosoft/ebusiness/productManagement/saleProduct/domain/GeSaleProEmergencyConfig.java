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
 * POJO类GeSaleProEmergencyConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_EMERGENCY_CONFIG")
public class GeSaleProEmergencyConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性紧急联系人姓名 */
	private String emerName;

	/** 属性紧急联系人手机 */
	private String mobile;

	/** 属性紧急联系人Email */
	private String emerEmail;

	/**
	 * 类GeSaleProEmergencyConfig的默认构造方法
	 */
	public GeSaleProEmergencyConfig() {
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
	 * 属性紧急联系人姓名的getter方法
	 */

	@Column(name = "EMERNAME")
	public String getEmerName() {
		return this.emerName;
	}
	/**
	 * 属性紧急联系人姓名的setter方法
	 */
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	/**
	 * 属性紧急联系人手机的getter方法
	 */

	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 属性紧急联系人手机的setter方法
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 属性紧急联系人Email的getter方法
	 */

	@Column(name = "EMEREMAIL")
	public String getEmerEmail() {
		return this.emerEmail;
	}
	/**
	 * 属性紧急联系人Email的setter方法
	 */
	public void setEmerEmail(String emerEmail) {
		this.emerEmail = emerEmail;
	}

}
