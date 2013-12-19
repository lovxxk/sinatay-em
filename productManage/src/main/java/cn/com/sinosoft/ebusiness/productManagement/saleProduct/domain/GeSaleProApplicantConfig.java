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
 * POJO类GeSaleProApplicantConfig
 */
@Entity
@Table(name = "GE_SALE_PRO_APPLICANTCONFIG")
public class GeSaleProApplicantConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	// 投保人年龄是否启用(0 启用 1禁用)
	private String appAgeFlag;
	
	// 投保人年龄开始
	private String appAgeStart;
	// 投保人年龄结束
	private String appAgeEnd;
	
	// 投保人年龄开始单位
	private String appAgeStartAttr;
	
	// 投保人年龄结束单位
	private String appAgeEndAttr;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性投保人姓名 */
	private String appName;

	/** 属性投保人性别 */
	private String appSex;

	/** 属性投保人出生日期 */
	private String appBirthday;

	/** 属性投保人证件类型 */
	private String appIdType;

	/** 属性投保人证件号码 */
	private String appIdNo;

	/** 属性投保人联系地址 */
	private String appAddress;

	/** 属性投保人邮政编码 */
	private String appZipCode;

	/** 属性投保人电子邮箱  */
	private String appEmail;

	/** 属性投保人公司电话 */
	private String appComPhone;

	/** 属性投保人家庭电话  */
	private String appHomePhone;

	/** 属性投保人移动电话 */
	private String appMobilePhone;

	/** 属性投保人性别配置 */
	private String appSexConfig;

	/** 属性投保人证件类型配置 */
	private String appIdTypeConfig;
	
	/** 属性投保人职业类别 */
	private String appOccupation;

	/** 属性投保人职业类别配置项 */
	private String appOccupationTypeConfig;

	/**
	 * 类GeSaleProApplicantConfig的默认构造方法
	 */
	public GeSaleProApplicantConfig() {
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
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
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
	 * 属性投保人姓名的getter方法
	 */

	@Column(name = "APPNAME")
	public String getAppName() {
		return this.appName;
	}
	/**
	 * 属性投保人姓名的setter方法
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * 属性投保人性别的getter方法
	 */

	@Column(name = "APPSEX")
	public String getAppSex() {
		return this.appSex;
	}
	/**
	 * 属性投保人性别的setter方法
	 */
	public void setAppSex(String appSex) {
		this.appSex = appSex;
	}
	/**
	 * 属性投保人出生日期的getter方法
	 */

	@Column(name = "APPBIRTHDAY")
	public String getAppBirthday() {
		return this.appBirthday;
	}
	/**
	 * 属性投保人出生日期的setter方法
	 */
	public void setAppBirthday(String appBirthday) {
		this.appBirthday = appBirthday;
	}
	/**
	 * 属性投保人证件类型的getter方法
	 */

	@Column(name = "APPIDTYPE")
	public String getAppIdType() {
		return this.appIdType;
	}
	/**
	 * 属性投保人证件类型的setter方法
	 */
	public void setAppIdType(String appIdType) {
		this.appIdType = appIdType;
	}
	/**
	 * 属性投保人证件号码的getter方法
	 */

	@Column(name = "APPIDNO")
	public String getAppIdNo() {
		return this.appIdNo;
	}
	/**
	 * 属性投保人证件号码的setter方法
	 */
	public void setAppIdNo(String appIdNo) {
		this.appIdNo = appIdNo;
	}
	/**
	 * 属性投保人联系地址的getter方法
	 */

	@Column(name = "APPADDRESS")
	public String getAppAddress() {
		return this.appAddress;
	}
	/**
	 * 属性投保人联系地址的setter方法
	 */
	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	}
	/**
	 * 属性投保人邮政编码的getter方法
	 */

	@Column(name = "APPZIPCODE")
	public String getAppZipCode() {
		return this.appZipCode;
	}
	/**
	 * 属性投保人邮政编码的setter方法
	 */
	public void setAppZipCode(String appZipCode) {
		this.appZipCode = appZipCode;
	}
	/**
	 * 属性投保人电子邮箱 的getter方法
	 */

	@Column(name = "APPEMAIL")
	public String getAppEmail() {
		return this.appEmail;
	}
	/**
	 * 属性投保人电子邮箱 的setter方法
	 */
	public void setAppEmail(String appEmail) {
		this.appEmail = appEmail;
	}
	/**
	 * 属性投保人公司电话的getter方法
	 */

	@Column(name = "APPCOMPHONE")
	public String getAppComPhone() {
		return this.appComPhone;
	}
	/**
	 * 属性投保人公司电话的setter方法
	 */
	public void setAppComPhone(String appComPhone) {
		this.appComPhone = appComPhone;
	}
	/**
	 * 属性投保人家庭电话 的getter方法
	 */

	@Column(name = "APPHOMEPHONE")
	public String getAppHomePhone() {
		return this.appHomePhone;
	}
	/**
	 * 属性投保人家庭电话 的setter方法
	 */
	public void setAppHomePhone(String appHomePhone) {
		this.appHomePhone = appHomePhone;
	}
	/**
	 * 属性投保人移动电话的getter方法
	 */

	@Column(name = "APPMOBILEPHONE")
	public String getAppMobilePhone() {
		return this.appMobilePhone;
	}
	/**
	 * 属性投保人移动电话的setter方法
	 */
	public void setAppMobilePhone(String appMobilePhone) {
		this.appMobilePhone = appMobilePhone;
	}
	/**
	 * 属性投保人性别配置的getter方法
	 */

	@Column(name = "APPSEXCONFIG")
	public String getAppSexConfig() {
		return this.appSexConfig;
	}
	/**
	 * 属性投保人性别配置的setter方法
	 */
	public void setAppSexConfig(String appSexConfig) {
		this.appSexConfig = appSexConfig;
	}
	/**
	 * 属性投保人证件类型配置的getter方法
	 */

	@Column(name = "APPIDTYPECONFIG")
	public String getAppIdTypeConfig() {
		return this.appIdTypeConfig;
	}
	/**
	 * 属性投保人证件类型配置的setter方法
	 */
	public void setAppIdTypeConfig(String appIdTypeConfig) {
		this.appIdTypeConfig = appIdTypeConfig;
	}

	@Column(name = "APPAGESTART")
	public String getAppAgeStart() {
		return appAgeStart;
	}

	public void setAppAgeStart(String appAgeStart) {
		this.appAgeStart = appAgeStart;
	}

	
	@Column(name = "APPAGEEND")
	public String getAppAgeEnd() {
		return appAgeEnd;
	}

	public void setAppAgeEnd(String appAgeEnd) {
		this.appAgeEnd = appAgeEnd;
	}

	@Column(name = "APPAGESTARTATTR")
	public String getAppAgeStartAttr() {
		return appAgeStartAttr;
	}

	public void setAppAgeStartAttr(String appAgeStartAttr) {
		this.appAgeStartAttr = appAgeStartAttr;
	}

	@Column(name = "APPAGEENDATTR")
	public String getAppAgeEndAttr() {
		return appAgeEndAttr;
	}

	public void setAppAgeEndAttr(String appAgeEndAttr) {
		this.appAgeEndAttr = appAgeEndAttr;
	}

	@Column(name = "APPAGEFLAG")
	public String getAppAgeFlag() {
		return appAgeFlag;
	}

	public void setAppAgeFlag(String appAgeFlag) {
		this.appAgeFlag = appAgeFlag;
	}

	/**
	 * 属性投保人职业类别的getter方法
	 */
	@Column(name = "APPOCCUPATION")
	public String getAppOccupation() {
		return this.appOccupation;
	}
	/**
	 * 属性投保人职业类别的setter方法
	 */
	public void setAppOccupation(String appOccupation) {
		this.appOccupation = appOccupation;
	}
	
	/**
	 * 属性投保人职业类别配置项的getter方法
	 */
	@Column(name = "APPOCCUPATIONTYPECONFIG")
	public String getAppOccupationTypeConfig() {
		return this.appOccupationTypeConfig;
	}
	/**
	 * 属性投保人职业类别配置项的setter方法
	 */
	public void setAppOccupationTypeConfig(String appOccupationTypeConfig) {
		this.appOccupationTypeConfig = appOccupationTypeConfig;
	}
}
