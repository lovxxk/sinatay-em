package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.Date;

/**
 * Ͷ���ˡ������ˡ������˹�������DTO
 *
 */
public class PartyRolePolicyDTO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** ���� */
	private String fullName;
	
	/** �Ա� CD13 */
	private String gender;
	
	/** �������� CD01 */
	private Date birthDate;
	
	/** ֤������ CD14 */
	private String idType;
	
	/** ֤������ */
	private String idNumber;
	
	/** ֤���Ƿ�������Ч 1 �� 0 �� ���� */
	private String idValidFlag;
	
	/** ֤������ CD01 ���� */
	private String idStartDate;
	
	/** ֤��ֹ�� CD01 */
	private String idEndDate;
	
	/** סլ�绰 */
	private String phoneNumber;
	
	/** �칫�绰 */
	private String officePhoneNumber;
	
	/** �ƶ��绰 */
	private String mobilePhoneNumber;
	
	/** ���� */
	private String household;
	
	/** ����״�� CD15 ���� */
	private String marriage;
	
	/** ��ַʡ���� */
	private String province;
	
	/** ��ַ�б��� */
	private String city;
	
	/** ��ַ�ر��� */
	private String county;
	
	/** �ʼĵ�ַ ���� */
	private String homeAddress;
	
	/** �ʼ��ʱ� ���� */
	private String homeZipCode;
	
	/** ͨѶ��ַ */
	private String mailAddress;
	
	/** ͨѶ�ʱ� */
	private String mailZipCode;
	
	/** ��˾���� */
	private String grpName;
	
	/** �������� */
	private String email;
	
	/** ְҵ���� ���� */
	private String jobType;
	
	/** ְҵ���� CD10 */
	private String jobCode;
	
	/** ְҵ���� CD10 */
	private String jobName;
	
	/** ���� CD16 */
	private String nationality;
	
	/** ������(��Ԫ) */
	private String inCome;
	
	/**����*/
	private String fax;

	/**
	 * ���� fullName �� getter ����
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * ���� fullName �� setter ����
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * ���� gender �� getter ����
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * ���� gender �� setter ����
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * ���� birthDate �� getter ����
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * ���� birthDate �� setter ����
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * ���� idType �� getter ����
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * ���� idType �� setter ����
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * ���� idNumber �� getter ����
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * ���� idNumber �� setter ����
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * ���� idValidFlag �� getter ����
	 * @return the idValidFlag
	 */
	public String getIdValidFlag() {
		return idValidFlag;
	}

	/**
	 * ���� idValidFlag �� setter ����
	 * @param idValidFlag the idValidFlag to set
	 */
	public void setIdValidFlag(String idValidFlag) {
		this.idValidFlag = idValidFlag;
	}

	/**
	 * ���� idStartDate �� getter ����
	 * @return the idStartDate
	 */
	public String getIdStartDate() {
		return idStartDate;
	}

	/**
	 * ���� idStartDate �� setter ����
	 * @param idStartDate the idStartDate to set
	 */
	public void setIdStartDate(String idStartDate) {
		this.idStartDate = idStartDate;
	}

	/**
	 * ���� idEndDate �� getter ����
	 * @return the idEndDate
	 */
	public String getIdEndDate() {
		return idEndDate;
	}

	/**
	 * ���� idEndDate �� setter ����
	 * @param idEndDate the idEndDate to set
	 */
	public void setIdEndDate(String idEndDate) {
		this.idEndDate = idEndDate;
	}

	/**
	 * ���� phoneNumber �� getter ����
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * ���� phoneNumber �� setter ����
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * ���� officePhoneNumber �� getter ����
	 * @return the officePhoneNumber
	 */
	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	/**
	 * ���� officePhoneNumber �� setter ����
	 * @param officePhoneNumber the officePhoneNumber to set
	 */
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	/**
	 * ���� mobilePhoneNumber �� getter ����
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * ���� mobilePhoneNumber �� setter ����
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * ���� household �� getter ����
	 * @return the household
	 */
	public String getHousehold() {
		return household;
	}

	/**
	 * ���� household �� setter ����
	 * @param household the household to set
	 */
	public void setHousehold(String household) {
		this.household = household;
	}

	/**
	 * ���� marriage �� getter ����
	 * @return the marriage
	 */
	public String getMarriage() {
		return marriage;
	}

	/**
	 * ���� marriage �� setter ����
	 * @param marriage the marriage to set
	 */
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	/**
	 * ���� province �� getter ����
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * ���� province �� setter ����
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * ���� city �� getter ����
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * ���� city �� setter ����
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * ���� county �� getter ����
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * ���� county �� setter ����
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * ���� homeAddress �� getter ����
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * ���� homeAddress �� setter ����
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * ���� homeZipCode �� getter ����
	 * @return the homeZipCode
	 */
	public String getHomeZipCode() {
		return homeZipCode;
	}

	/**
	 * ���� homeZipCode �� setter ����
	 * @param homeZipCode the homeZipCode to set
	 */
	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	/**
	 * ���� mailAddress �� getter ����
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * ���� mailAddress �� setter ����
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * ���� mailZipCode �� getter ����
	 * @return the mailZipCode
	 */
	public String getMailZipCode() {
		return mailZipCode;
	}

	/**
	 * ���� mailZipCode �� setter ����
	 * @param mailZipCode the mailZipCode to set
	 */
	public void setMailZipCode(String mailZipCode) {
		this.mailZipCode = mailZipCode;
	}

	/**
	 * ���� grpName �� getter ����
	 * @return the grpName
	 */
	public String getGrpName() {
		return grpName;
	}

	/**
	 * ���� grpName �� setter ����
	 * @param grpName the grpName to set
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * ���� email �� getter ����
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * ���� email �� setter ����
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ���� jobType �� getter ����
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * ���� jobType �� setter ����
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * ���� jobCode �� getter ����
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
	 * ���� jobCode �� setter ����
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * ���� nationality �� getter ����
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * ���� nationality �� setter ����
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * ���� inCome �� getter ����
	 * @return the inCome
	 */
	public String getInCome() {
		return inCome;
	}

	/**
	 * ���� inCome �� setter ����
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
