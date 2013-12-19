package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.Date;

/**
 * 投保人、被保人、受益人公共属性DTO
 *
 */
public class PartyRolePolicyDTO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 姓名 */
	private String fullName;
	
	/** 性别 CD13 */
	private String gender;
	
	/** 出生日期 CD01 */
	private Date birthDate;
	
	/** 证件类型 CD14 */
	private String idType;
	
	/** 证件号码 */
	private String idNumber;
	
	/** 证件是否终身有效 1 是 0 否 不填 */
	private String idValidFlag;
	
	/** 证件起期 CD01 不填 */
	private String idStartDate;
	
	/** 证件止期 CD01 */
	private String idEndDate;
	
	/** 住宅电话 */
	private String phoneNumber;
	
	/** 办公电话 */
	private String officePhoneNumber;
	
	/** 移动电话 */
	private String mobilePhoneNumber;
	
	/** 户籍 */
	private String household;
	
	/** 婚姻状况 CD15 不填 */
	private String marriage;
	
	/** 地址省编码 */
	private String province;
	
	/** 地址市编码 */
	private String city;
	
	/** 地址县编码 */
	private String county;
	
	/** 邮寄地址 不填 */
	private String homeAddress;
	
	/** 邮寄邮编 不填 */
	private String homeZipCode;
	
	/** 通讯地址 */
	private String mailAddress;
	
	/** 通讯邮编 */
	private String mailZipCode;
	
	/** 公司名称 */
	private String grpName;
	
	/** 电子邮箱 */
	private String email;
	
	/** 职业类型 不填 */
	private String jobType;
	
	/** 职业代码 CD10 */
	private String jobCode;
	
	/** 职业代码 CD10 */
	private String jobName;
	
	/** 国籍 CD16 */
	private String nationality;
	
	/** 年收入(万元) */
	private String inCome;
	
	/**传真*/
	private String fax;

	/**
	 * 属性 fullName 的 getter 方法
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 属性 fullName 的 setter 方法
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 属性 gender 的 getter 方法
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 属性 gender 的 setter 方法
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 属性 birthDate 的 getter 方法
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * 属性 birthDate 的 setter 方法
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * 属性 idType 的 getter 方法
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * 属性 idType 的 setter 方法
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * 属性 idNumber 的 getter 方法
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * 属性 idNumber 的 setter 方法
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * 属性 idValidFlag 的 getter 方法
	 * @return the idValidFlag
	 */
	public String getIdValidFlag() {
		return idValidFlag;
	}

	/**
	 * 属性 idValidFlag 的 setter 方法
	 * @param idValidFlag the idValidFlag to set
	 */
	public void setIdValidFlag(String idValidFlag) {
		this.idValidFlag = idValidFlag;
	}

	/**
	 * 属性 idStartDate 的 getter 方法
	 * @return the idStartDate
	 */
	public String getIdStartDate() {
		return idStartDate;
	}

	/**
	 * 属性 idStartDate 的 setter 方法
	 * @param idStartDate the idStartDate to set
	 */
	public void setIdStartDate(String idStartDate) {
		this.idStartDate = idStartDate;
	}

	/**
	 * 属性 idEndDate 的 getter 方法
	 * @return the idEndDate
	 */
	public String getIdEndDate() {
		return idEndDate;
	}

	/**
	 * 属性 idEndDate 的 setter 方法
	 * @param idEndDate the idEndDate to set
	 */
	public void setIdEndDate(String idEndDate) {
		this.idEndDate = idEndDate;
	}

	/**
	 * 属性 phoneNumber 的 getter 方法
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 属性 phoneNumber 的 setter 方法
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 属性 officePhoneNumber 的 getter 方法
	 * @return the officePhoneNumber
	 */
	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	/**
	 * 属性 officePhoneNumber 的 setter 方法
	 * @param officePhoneNumber the officePhoneNumber to set
	 */
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	/**
	 * 属性 mobilePhoneNumber 的 getter 方法
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * 属性 mobilePhoneNumber 的 setter 方法
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * 属性 household 的 getter 方法
	 * @return the household
	 */
	public String getHousehold() {
		return household;
	}

	/**
	 * 属性 household 的 setter 方法
	 * @param household the household to set
	 */
	public void setHousehold(String household) {
		this.household = household;
	}

	/**
	 * 属性 marriage 的 getter 方法
	 * @return the marriage
	 */
	public String getMarriage() {
		return marriage;
	}

	/**
	 * 属性 marriage 的 setter 方法
	 * @param marriage the marriage to set
	 */
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	/**
	 * 属性 province 的 getter 方法
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 属性 province 的 setter 方法
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 属性 city 的 getter 方法
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 属性 city 的 setter 方法
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 属性 county 的 getter 方法
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * 属性 county 的 setter 方法
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * 属性 homeAddress 的 getter 方法
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * 属性 homeAddress 的 setter 方法
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * 属性 homeZipCode 的 getter 方法
	 * @return the homeZipCode
	 */
	public String getHomeZipCode() {
		return homeZipCode;
	}

	/**
	 * 属性 homeZipCode 的 setter 方法
	 * @param homeZipCode the homeZipCode to set
	 */
	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	/**
	 * 属性 mailAddress 的 getter 方法
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * 属性 mailAddress 的 setter 方法
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * 属性 mailZipCode 的 getter 方法
	 * @return the mailZipCode
	 */
	public String getMailZipCode() {
		return mailZipCode;
	}

	/**
	 * 属性 mailZipCode 的 setter 方法
	 * @param mailZipCode the mailZipCode to set
	 */
	public void setMailZipCode(String mailZipCode) {
		this.mailZipCode = mailZipCode;
	}

	/**
	 * 属性 grpName 的 getter 方法
	 * @return the grpName
	 */
	public String getGrpName() {
		return grpName;
	}

	/**
	 * 属性 grpName 的 setter 方法
	 * @param grpName the grpName to set
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * 属性 email 的 getter 方法
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 属性 email 的 setter 方法
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 属性 jobType 的 getter 方法
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * 属性 jobType 的 setter 方法
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * 属性 jobCode 的 getter 方法
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 属性 jobCode 的 setter 方法
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * 属性 nationality 的 getter 方法
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * 属性 nationality 的 setter 方法
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * 属性 inCome 的 getter 方法
	 * @return the inCome
	 */
	public String getInCome() {
		return inCome;
	}

	/**
	 * 属性 inCome 的 setter 方法
	 * @param inCome the inCome to set
	 */
	public void setInCome(String inCome) {
		this.inCome = inCome;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
}
