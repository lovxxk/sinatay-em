package cn.com.sinosoft.ebusiness.sale.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类InsuranceApplicant
 */
@Entity
@Table(name = "QUOTE_PARTYROLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "ROLEKIND" ,discriminatorType = DiscriminatorType.STRING)
public class QuoteRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	/** 属性角色编号 */
	private String roleSerialNo;
	
	/** 属性姓 */
	private String firstName;
	
	/** 属性名 */
	private String lastName;
	
	/** 属性姓名 */
	private String fullName;

	/** 属性英文姓名 */
	private String fullENName;
	
	/** 属性证件类型 */
	private Integer idType;

	/** 属性证件号码 */
	private String idNumber;

	/** 属性证件有效日期 */
	private Date idExpDate;

	/** 属性性别 */
	private Integer gender;

	/** 属性出生日期 */
	private Date birthDate;

	/** 属性出生天数 */
	private Integer ageInDay;

	/** 属性年龄 */
	private Integer age;
	
	/** 属性出生体重 */
	private Integer birthWeight;

	/** 属性身高 */
	private Integer height;

	/** 属性体重 */
	private Integer weight;

	/** 属性手机 */
	private String mobilePhoneNumber;

	/** 属性固定电话 */
	private String phoneNumber;

	/** 属性办公电话 */
	private String officePhoneNumber;

	/** 属性电子邮箱  */
	private String email;

	/** 属性邮政编码 */
	private String postalCode;

	/** 属性省份 */
	private String province;
	
	/** 属性城市 */
	private String city;
	
	/** 属性家庭地址 */
	private String homeAddress;
	
	/** 属性办公地址 */
	private String officeAddress;
	
	/** 属性通讯地址 */
	private String addressLines;
	
	/** 属性国籍 */
	private Integer citizenShip;

	/** 属性职业类别 */
	private String occupationClass;
	
	/** 属性职业代码*/
	private String occupationCode;
	
	/** 属性职业描述 */
	private String occupationDescription;

	/** 属性婚姻状况 */
	private Integer maritalStatus;
	
	/** 属性年收入 */
	private String annualIncome;

	/** 属性工作单位名称 */
	private String employerFullName;

	/** 属性饮酒数量/年 */
	private Integer drinkerAmountByYear;

	/** 属性饮酒状况 */
	private Integer drinkerStatus;

	/** 属性饮酒年限 */
	private Integer drinkerYears;

	/** 属性吸烟数量/年 */
	private Integer smokerAmountByYear;

	/** 属性吸烟状况 */
	private Integer smokerStatus;

	/** 属性吸烟年限 */
	private Integer smokerYears;

	/** 属性是否接受手机短信服务 */
	private Integer acceptSMS;

	/** 属性用户号 */
	private String customerID;

	/** 属性旧客户标志 */
	private Integer customFlag;
	
	/** 属性 证件是否终身有效 1 是 0 否 不填 */
	private Integer iDValidFlag;

	/** 属性 证件起期 */
	private Date iDStartDate;

	/** 属性 证件止期 */
	private Date iDEndDate;

	/** 属性 户籍 */
	private String household;

	/** 属性 县 */
	private String county;

	/** 属性 邮寄邮编 */
	private String homeZipCode;

	/** 属性 公司名称 */
	private String grpName;
	
	/** 属性 国籍名称 */
	private String citizenShipName;
	
	/** 属性 传真 */
	private String fax;
	
	/** 属性 驾照类型 */
	private Integer licenseType;
	
	/** 属性 驾照类型显示用 */
	private String licenseTypeView;
	
	/**昵称*/
	private String alias;
	
	/**爱好*/
	private String hobby;
	
	/**血型*/
	private String bloodType;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/** 属性省份 */
	private String provinceName;
	
	/** 属性城市 */
	private String cityName;
	
	/** 属性 县 */
	private String countyName;

	/**
	 * 类InsuranceApplicant的默认构造方法
	 */
	public QuoteRole() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@JsonIgnore
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
	 * 属性角色编号的getter方法
	 */
	@Column(name = "ROLESERIALNO")	
	public String getRoleSerialNo() {
		return roleSerialNo;
	}
	
	/**
	 * 属性角色编号的setter方法
	 */
	public void setRoleSerialNo(String roleSerialNo) {
		this.roleSerialNo = roleSerialNo;
	}

	/**
	 * 属性姓名的getter方法
	 */
	@Column(name = "FULLNAME")
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * 属性姓名的setter方法
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 属性姓的getter方法
	 */
	@Column(name = "FIRSTNAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 属性姓的setter方法
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 属性姓的getter方法
	 */
	@Column(name = "LASTNAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * 属性姓的setter方法
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 属性英文姓名的getter方法
	 */

	@Column(name = "FULLENNAME")
	public String getFullENName() {
		return this.fullENName;
	}
	
	/**
	 * 属性英文姓名的setter方法
	 */
	public void setFullENName(String fullENName) {
		this.fullENName = fullENName;
	}

	/**
	 * 属性证件类型的getter方法
	 */

	@Column(name = "IDTYPE")
	public Integer getIdType() {
		return this.idType;
	}
	
	
	
	/**
	 * 属性证件类型的setter方法
	 */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	
	
	/**
	 * 属性证件号码的getter方法
	 */

	@Column(name = "IDNUMBER")
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * 属性证件号码的setter方法
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * 属性证件有效日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "IDEXPDATE")
	public Date getIdExpDate() {
		return this.idExpDate;
	}

	/**
	 * 属性证件有效日期的setter方法
	 */
	public void setIdExpDate(Date idExpDate) {
		this.idExpDate = idExpDate;
	}

	/**
	 * 属性性别的getter方法
	 */

	@Column(name = "GENDER")
	public Integer getGender() {
		return this.gender;
	}
	
	/**
	 * 属性性别的setter方法
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	
	/**
	 * 属性出生日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE")
	public Date getBirthDate() {
		return this.birthDate;
	}

	/**
	 * 属性出生日期的setter方法
	 */
	public void setBirthDate(Date birthdate) {
		this.birthDate = birthdate;
	}

	/**
	 * 属性出生天数的getter方法
	 */

	@Column(name = "AGEINDAY")
	public Integer getAgeInDay() {
		return this.ageInDay;
	}

	/**
	 * 属性出生天数的setter方法
	 */
	public void setAgeInDay(Integer ageInDay) {
		this.ageInDay = ageInDay;
	}

	/**
	 * 属性年龄的getter方法
	 */

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	/**
	 * 属性年龄的setter方法
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * 属性出生体重的getter方法
	 */

	@Column(name = "BIRTHWEIGHT")
	public Integer getBirthWeight() {
		return this.birthWeight;
	}

	/**
	 * 属性出生体重的setter方法
	 */
	public void setBirthWeight(Integer birthWeight) {
		this.birthWeight = birthWeight;
	}

	/**
	 * 属性身高的getter方法
	 */

	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return this.height;
	}

	/**
	 * 属性身高的setter方法
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * 属性体重的getter方法
	 */

	@Column(name = "WEIGHT")
	public Integer getWeight() {
		return this.weight;
	}

	/**
	 * 属性体重的setter方法
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 属性手机的getter方法
	 */

	@Column(name = "MOBILEPHONENUMBER")
	public String getMobilePhoneNumber() {
		return this.mobilePhoneNumber;
	}

	/**
	 * 属性手机的setter方法
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * 属性固定电话的getter方法
	 */

	@Column(name = "PHONENUMBER")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 属性固定电话的setter方法
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 属性办公电话的getter方法
	 */

	@Column(name = "OFFICEPHONENUMBER")
	public String getOfficePhoneNumber() {
		return this.officePhoneNumber;
	}

	/**
	 * 属性办公电话的setter方法
	 */
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	/**
	 * 属性电子邮箱 的getter方法
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * 属性电子邮箱 的setter方法
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 属性邮政编码的getter方法
	 */

	@Column(name = "POSTALCODE")
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * 属性邮政编码的setter方法
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
	/**
	 * 属性省份的getter方法
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}

	/**
	 * 属性省份的setter方法
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 属性城市的getter方法
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * 属性城市的setter方法
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	/**
	 * 属性家庭地址的getter方法
	 */
	@Column(name = "HOMEADDRESS")
	public String getHomeAddress() {
		return homeAddress;
	}
	
	/**
	 * 属性家庭地址的setter方法
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	/**
	 * 属性办公地址的getter方法
	 */
	@Column(name = "OFFICEADDRESS")
	public String getOfficeAddress() {
		return officeAddress;
	}
	
	/**
	 * 属性办公地址的setter方法
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	
	/**
	 * 属性通讯地址的getter方法
	 */

	@Column(name = "ADDRESSLINES")
	public String getAddressLines() {
		return this.addressLines;
	}

	/**
	 * 属性通讯地址的setter方法
	 */
	public void setAddressLines(String addressLines) {
		this.addressLines = addressLines;
	}

	/**
	 * 属性国籍的getter方法
	 */

	@Column(name = "CITIZENSHIP")
	public Integer getCitizenShip() {
		return this.citizenShip;
	}

	/**
	 * 属性国籍的setter方法
	 */
	public void setCitizenShip(Integer citizenShip) {
		this.citizenShip = citizenShip;
	}

	/**
	 * 属性职业类别的getter方法
	 */

	@Column(name = "OCCUPATIONCLASS")
	public String getOccupationClass() {
		return this.occupationClass;
	}

	/**
	 * 属性职业类别的setter方法
	 */
	public void setOccupationClass(String occupationClass) {
		this.occupationClass = occupationClass;
	}
	
	/**
	 * 属性职业代码的getter方法
	 */
	@Column(name = "OCCUPATIONCODE")
	public String getOccupationCode() {
		return occupationCode;
	}
	
	/**
	 * 属性职业代码的setter方法
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * 属性职业描述的getter方法
	 */

	@Column(name = "OCCUPATIONDESCRIPTION")
	public String getOccupationDescription() {
		return this.occupationDescription;
	}

	/**
	 * 属性职业描述的setter方法
	 */
	public void setOccupationDescription(String occupationDescription) {
		this.occupationDescription = occupationDescription;
	}
	
	/**
	 * 属性婚姻状况的getter方法
	 */

	@Column(name = "MARITALSTATUS")
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	
	/**
	 * 属性婚姻状况setter方法
	 */

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	/**
	 * 属性年收入的getter方法
	 */

	@Column(name = "ANNUALINCOME")
	public String getAnnualIncome() {
		return this.annualIncome;
	}

	/**
	 * 属性年收入的setter方法
	 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * 属性工作单位名称的getter方法
	 */

	@Column(name = "EMPLOYERFULLNAME")
	public String getEmployerFullName() {
		return this.employerFullName;
	}

	/**
	 * 属性工作单位名称的setter方法
	 */
	public void setEmployerFullName(String employerFullName) {
		this.employerFullName = employerFullName;
	}

	/**
	 * 属性饮酒数量/年的getter方法
	 */

	@Column(name = "DRINKERAMOUNTBYYEAR")
	public Integer getDrinkerAmountByYear() {
		return this.drinkerAmountByYear;
	}

	/**
	 * 属性饮酒数量/年的setter方法
	 */
	public void setDrinkerAmountByYear(Integer drinkerAmountByYear) {
		this.drinkerAmountByYear = drinkerAmountByYear;
	}

	/**
	 * 属性饮酒状况的getter方法
	 */

	@Column(name = "DRINKERSTATUS")
	public Integer getDrinkerStatus() {
		return this.drinkerStatus;
	}
	
	/**
	 * 属性饮酒状况的setter方法
	 */
	public void setDrinkerStatus(Integer drinkerStatus) {
		this.drinkerStatus = drinkerStatus;
	}
	
	/**
	 * 属性饮酒年限的getter方法
	 */

	@Column(name = "DRINKERYEARS")
	public Integer getDrinkerYears() {
		return this.drinkerYears;
	}

	/**
	 * 属性饮酒年限的setter方法
	 */
	public void setDrinkerYears(Integer drinkerYears) {
		this.drinkerYears = drinkerYears;
	}

	/**
	 * 属性吸烟数量/年的getter方法
	 */

	@Column(name = "SMOKERAMOUNTBYYEAR")
	public Integer getSmokerAmountByYear() {
		return this.smokerAmountByYear;
	}

	/**
	 * 属性吸烟数量/年的setter方法
	 */
	public void setSmokerAmountByYear(Integer smokerAmountByYear) {
		this.smokerAmountByYear = smokerAmountByYear;
	}

	/**
	 * 属性吸烟状况的getter方法
	 */
	@Column(name = "SMOKERSTATUS")
	public Integer getSmokerStatus() {
		return this.smokerStatus;
	}
	
	/**
	 * 属性吸烟状况的setter方法
	 */
	public void setSmokerStatus(Integer smokerStatus) {
		this.smokerStatus = smokerStatus;
	}
	
	/**
	 * 属性吸烟年限的getter方法
	 */

	@Column(name = "SMOKERYEARS")
	public Integer getSmokerYears() {
		return this.smokerYears;
	}
	
	/**
	 * 属性吸烟年限的setter方法
	 */
	public void setSmokerYears(Integer smokerYears) {
		this.smokerYears = smokerYears;
	}

	/**
	 * 属性是否接受手机短信服务的getter方法
	 */

	@Column(name = "ACCEPTSMS")
	public Integer getAcceptSMS() {
		return this.acceptSMS;
	}
	
	/**
	 * 属性是否接受手机短信服务的setter方法
	 */
	public void setAcceptSMS(Integer acceptSMS) {
		this.acceptSMS = acceptSMS;
	}
	
	/**
	 * 属性用户号的getter方法
	 */

	@Column(name = "CUSTOMERID")
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * 属性旧客户标志的getter方法
	 */

	@Column(name = "CUSTOMFLAG")
	public Integer getCustomFlag() {
		return this.customFlag;
	}
	
	/**
	 * 属性旧客户标志的setter方法
	 */
	public void setCustomFlag(Integer customFlag) {
		this.customFlag = customFlag;
	}

	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JsonIgnore
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	@JsonIgnore
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性 证件是否终身有效 的getter方法
	 */
	@Column(name = "IDVALIDFLAG")
	public Integer getiDValidFlag() {
		return iDValidFlag;
	}

	/**
	 * 属性 证件是否终身有效 的setter方法
	 */
	public void setiDValidFlag(Integer iDValidFlag) {
		this.iDValidFlag = iDValidFlag;
	}

	/**
	 * 属性 证件起期 的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "IDSTARTDATE")
	public Date getiDStartDate() {
		return iDStartDate;
	}

	/**
	 * 属性 证件起期 的setter方法
	 */
	public void setiDStartDate(Date iDStartDate) {
		this.iDStartDate = iDStartDate;
	}

	/**
	 * 属性 证件止期 的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "IDENDDATE")
	public Date getiDEndDate() {
		return iDEndDate;
	}

	/**
	 * 属性 证件止期 的setter方法
	 */
	public void setiDEndDate(Date iDEndDate) {
		this.iDEndDate = iDEndDate;
	}

	/**
	 * 属性 户籍 的getter方法
	 */
	@Column(name = "HOUSEHOLD")
	public String getHousehold() {
		return household;
	}

	/**
	 * 属性 户籍 的setter方法
	 */
	public void setHousehold(String household) {
		this.household = household;
	}

	/**
	 * 属性 县 的getter方法
	 */
	@Column(name = "COUNTY")
	public String getCounty() {
		return county;
	}

	/**
	 * 属性 县 的setter方法
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * 属性 邮寄邮编 的getter方法
	 */
	@Column(name = "HOMEZIPCODE")
	public String getHomeZipCode() {
		return homeZipCode;
	}

	/**
	 * 属性 邮寄邮编 的setter方法
	 */
	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	/**
	 * 属性 公司名称 的getter方法
	 */
	@Column(name = "GRPNAME")
	public String getGrpName() {
		return grpName;
	}

	/**
	 * 属性 公司名称 的setter方法
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * 属性 国籍名称 的getter方法
	 * @return the citizenShipName
	 */
	@Column(name = "CITIZENSHIPNAME")
	public String getCitizenShipName() {
		return citizenShipName;
	}

	/**
	 * 属性 国籍名称 的setter方法
	 * @param citizenShipName the citizenShipName to set
	 */
	public void setCitizenShipName(String citizenShipName) {
		this.citizenShipName = citizenShipName;
	}

	/**
	 * 属性 传真 的getter方法
	 * @return the fax
	 */
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	/**
	 * 属性 传真 的setter方法
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 属性  驾照类型 的getter方法
	 * @return the licenseType
	 */
	@Column(name = "LICENSETYPE")
	public Integer getLicenseType() {
		return licenseType;
	}

	/**
	 * 属性 驾照类型 的setter方法
	 * @param licenseType the licenseType to set
	 */
	public void setLicenseType(Integer licenseType) {
		this.licenseType = licenseType;
	}

	/**
	 * 属性 驾照类型显示用 的getter方法
	 * @return the licenseTypeView
	 */
	@Column(name = "LICENSETYPEVIEW")
	public String getLicenseTypeView() {
		return licenseTypeView;
	}

	/**
	 * 属性 驾照类型显示用 的setter方法
	 * @param licenseTypeView the licenseTypeView to set
	 */
	public void setLicenseTypeView(String licenseTypeView) {
		this.licenseTypeView = licenseTypeView;
	}

	/**
	 * 属性 昵称 的getter方法
	 * @return the alias
	 */
	@Column(name = "ALIAS")
	public String getAlias() {
		return alias;
	}

	/**
	 * 属性 昵称 的setter方法
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 属性 爱好 的getter方法
	 * @return the hobby
	 */
	@Column(name = "HOBBY")
	public String getHobby() {
		return hobby;
	}

	/**
	 * 属性 爱好 的setter方法
	 * @param hobby the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	/**
	 * 属性 血型 的getter方法
	 * @return the bloodType
	 */
	@Column(name = "BLOODTYPE")
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * 属性 血型 的setter方法
	 * @param bloodType the bloodType to set
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "PROVINCENAME")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "CITYNAME")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "COUNTYNAME")
	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

}
