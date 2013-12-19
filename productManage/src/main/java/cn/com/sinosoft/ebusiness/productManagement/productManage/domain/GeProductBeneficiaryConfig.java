package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
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
 * POJO类GeProductBeneficiaryConfig
 */
@Entity
@Table(name = "GE_PRODUCT_BENEFICIARYCONFIG")
public class GeProductBeneficiaryConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性产品 */
	private GeProductMain geProductMain;

	/** 属性受益人姓名 */
	private String benName;

	/** 属性受益人性别 */
	private String benGender;

	/** 属性受益人出生日期 */
	private String benBirthday;

	/** 属性受益人证件类型 */
	private String benIdType;

	/** 属性受益人证件号码  */
	private String benIdNumber;

	/** 属性受益人联系地址 */
	private String benAddress;

	/** 属性受益人邮政编码 */
	private String benZipCode;

	/** 属性受益人电子邮箱  */
	private String benEmail;

	/** 属性受益人公司电话 */
	private String benComPhone;

	/** 属性受益人家庭电话 */
	private String benHomePhone;

	/** 属性受益人移动电话 */
	private String benMobilePhone;

	/** 属性受益顺序 */
	private String benOrder;

	/** 属性受益比例 */
	private String benRate;

	/** 属性受益类型 */
	private String benType;

	/** 属性受益人与主被保人关系 */
	private String benRelationToPIns;

	/** 属性受益人与主被保人关系配置项 */
	private String benRelationToPInsConfig;

	/** 属性受益人性别配置 */
	private String benGenderConfig;

	/** 属性bendTypeConfig */
	private String bendTypeConfig;

	/**
	 * 类GeProductBeneficiaryConfig的默认构造方法
	 */
	public GeProductBeneficiaryConfig() {
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
	 * 属性产品的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE", nullable = false)
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * 属性产品的setter方法
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * 属性受益人姓名的getter方法
	 */

	@Column(name = "BENNAME")
	public String getBenName() {
		return this.benName;
	}
	/**
	 * 属性受益人姓名的setter方法
	 */
	public void setBenName(String benName) {
		this.benName = benName;
	}
	/**
	 * 属性受益人性别的getter方法
	 */

	@Column(name = "BENGENDER")
	public String getBenGender() {
		return this.benGender;
	}
	/**
	 * 属性受益人性别的setter方法
	 */
	public void setBenGender(String benGender) {
		this.benGender = benGender;
	}
	/**
	 * 属性受益人出生日期的getter方法
	 */

	@Column(name = "BENBIRTHDAY")
	public String getBenBirthday() {
		return this.benBirthday;
	}
	/**
	 * 属性受益人出生日期的setter方法
	 */
	public void setBenBirthday(String benBirthday) {
		this.benBirthday = benBirthday;
	}
	/**
	 * 属性受益人证件类型的getter方法
	 */

	@Column(name = "BENIDTYPE")
	public String getBenIdType() {
		return this.benIdType;
	}
	/**
	 * 属性受益人证件类型的setter方法
	 */
	public void setBenIdType(String benIdType) {
		this.benIdType = benIdType;
	}
	/**
	 * 属性受益人证件号码 的getter方法
	 */

	@Column(name = "BENIDNUMBER")
	public String getBenIdNumber() {
		return this.benIdNumber;
	}
	/**
	 * 属性受益人证件号码 的setter方法
	 */
	public void setBenIdNumber(String benIdNumber) {
		this.benIdNumber = benIdNumber;
	}
	/**
	 * 属性受益人联系地址的getter方法
	 */

	@Column(name = "BENADDRESS")
	public String getBenAddress() {
		return this.benAddress;
	}
	/**
	 * 属性受益人联系地址的setter方法
	 */
	public void setBenAddress(String benAddress) {
		this.benAddress = benAddress;
	}
	/**
	 * 属性受益人邮政编码的getter方法
	 */

	@Column(name = "BENZIPCODE")
	public String getBenZipCode() {
		return this.benZipCode;
	}
	/**
	 * 属性受益人邮政编码的setter方法
	 */
	public void setBenZipCode(String benZipCode) {
		this.benZipCode = benZipCode;
	}
	/**
	 * 属性受益人电子邮箱 的getter方法
	 */

	@Column(name = "BENEMAIL")
	public String getBenEmail() {
		return this.benEmail;
	}
	/**
	 * 属性受益人电子邮箱 的setter方法
	 */
	public void setBenEmail(String benEmail) {
		this.benEmail = benEmail;
	}
	/**
	 * 属性受益人公司电话的getter方法
	 */

	@Column(name = "BENCOMPHONE")
	public String getBenComPhone() {
		return this.benComPhone;
	}
	/**
	 * 属性受益人公司电话的setter方法
	 */
	public void setBenComPhone(String benComPhone) {
		this.benComPhone = benComPhone;
	}
	/**
	 * 属性受益人家庭电话的getter方法
	 */

	@Column(name = "BENHOMEPHONE")
	public String getBenHomePhone() {
		return this.benHomePhone;
	}
	/**
	 * 属性受益人家庭电话的setter方法
	 */
	public void setBenHomePhone(String benHomePhone) {
		this.benHomePhone = benHomePhone;
	}
	/**
	 * 属性受益人移动电话的getter方法
	 */

	@Column(name = "BENMOBILEPHONE")
	public String getBenMobilePhone() {
		return this.benMobilePhone;
	}
	/**
	 * 属性受益人移动电话的setter方法
	 */
	public void setBenMobilePhone(String benMobilePhone) {
		this.benMobilePhone = benMobilePhone;
	}
	/**
	 * 属性受益顺序的getter方法
	 */

	@Column(name = "BENORDER")
	public String getBenOrder() {
		return this.benOrder;
	}
	/**
	 * 属性受益顺序的setter方法
	 */
	public void setBenOrder(String benOrder) {
		this.benOrder = benOrder;
	}
	/**
	 * 属性受益比例的getter方法
	 */

	@Column(name = "BENRATE")
	public String getBenRate() {
		return this.benRate;
	}
	/**
	 * 属性受益比例的setter方法
	 */
	public void setBenRate(String benRate) {
		this.benRate = benRate;
	}
	/**
	 * 属性受益类型的getter方法
	 */

	@Column(name = "BENTYPE")
	public String getBenType() {
		return this.benType;
	}
	/**
	 * 属性受益类型的setter方法
	 */
	public void setBenType(String benType) {
		this.benType = benType;
	}
	/**
	 * 属性受益人与主被保人关系的getter方法
	 */

	@Column(name = "BENRELATIONTOPINS")
	public String getBenRelationToPIns() {
		return this.benRelationToPIns;
	}
	/**
	 * 属性受益人与主被保人关系的setter方法
	 */
	public void setBenRelationToPIns(String benRelationToPIns) {
		this.benRelationToPIns = benRelationToPIns;
	}
	/**
	 * 属性受益人与主被保人关系配置项的getter方法
	 */

	@Column(name = "BENRELATIONTOPINSCONFIG")
	public String getBenRelationToPInsConfig() {
		return this.benRelationToPInsConfig;
	}
	/**
	 * 属性受益人与主被保人关系配置项的setter方法
	 */
	public void setBenRelationToPInsConfig(String benRelationToPInsConfig) {
		this.benRelationToPInsConfig = benRelationToPInsConfig;
	}
	/**
	 * 属性受益人性别配置的getter方法
	 */

	@Column(name = "BENGENDERCONFIG")
	public String getBenGenderConfig() {
		return this.benGenderConfig;
	}
	/**
	 * 属性受益人性别配置的setter方法
	 */
	public void setBenGenderConfig(String benGenderConfig) {
		this.benGenderConfig = benGenderConfig;
	}
	/**
	 * 属性bendTypeConfig的getter方法
	 */

	@Column(name = "BENIDTYPECONFIG")
	public String getBendTypeConfig() {
		return this.bendTypeConfig;
	}
	/**
	 * 属性bendTypeConfig的setter方法
	 */
	public void setBendTypeConfig(String bendTypeConfig) {
		this.bendTypeConfig = bendTypeConfig;
	}

}
