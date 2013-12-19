package cn.com.sinosoft.ebusiness.service.user.personal.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import cn.com.sinosoft.businessModule.enums.dictionary.BooleanStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.DrinkerStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.Gender;
import cn.com.sinosoft.businessModule.enums.dictionary.IdType;
import cn.com.sinosoft.businessModule.enums.dictionary.MainInsuredFlag;
import cn.com.sinosoft.businessModule.enums.dictionary.MaritalStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.RelatedToRoleCode;
import cn.com.sinosoft.businessModule.enums.dictionary.SmokerStatus;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO��Insured
 */
@Entity
@Table(name = "TOPINSURED")
public class TopInsured implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	/** ������Ͷ���˹�ϵ */
	private Integer relatedToApplicant;

	/** ���������������˹�ϵ */
	private Integer relatedToInsured;
	
	/** �����������˱�־ */
	private Integer mainInsuredFlag;
	
	/** ���Ա���˳�� */
	private Integer insuredOrder;

	/** ������ռ����ٷֱ� */
	private BigDecimal insuredAmountPercent;
	
	/** ����Ͷ���˸�֪��Ϣ */
	private String indicatorMessage;

	/** ����ͬҵ���� */
	private String sameIndustryInsuredAmount;
	
	/** ������� */
	private String serialNo;
	
	/** ���Խ�ɫ��� */
	private String roleSerialNo;
	
	/** ������ */
	private String firstName;
	
	/** ������ */
	private String lastName;
	
	/** �������� */
	private String fullName;

	/** ����Ӣ������ */
	private String fullENName;
	
	/** ����֤������ */
	private Integer idType;

	/** ����֤������ */
	private String idNumber;

	/** ����֤����Ч���� */
	private Date idExpDate;

	/** �����Ա� */
	private Integer gender;

	/** ���Գ������� */
	private Date birthDate;

	/** ���Գ������� */
	private Integer ageInDay;

	/** �������� */
	private Integer age;
	
	/** ���Գ������� */
	private Integer birthWeight;

	/** ������� */
	private Integer height;

	/** �������� */
	private Integer weight;

	/** �����ֻ� */
	private String mobilePhoneNumber;

	/** ���Թ̶��绰 */
	private String phoneNumber;

	/** ���԰칫�绰 */
	private String officePhoneNumber;

	/** ���Ե�������  */
	private String email;

	/** ������������ */
	private String postalCode;

	/** ����ʡ�� */
	private String province;
	
	/** ���Գ��� */
	private String city;
	
	/** ���Լ�ͥ��ַ */
	private String homeAddress;
	
	/** ���԰칫��ַ */
	private String officeAddress;
	
	/** ����ͨѶ��ַ */
	private String addressLines;
	
	/** ���Թ��� */
	private Integer citizenShip;

	/** ����ְҵ��� */
	private String occupationClass;
	
	/** ����ְҵ����*/
	private String occupationCode;
	
	/** ����ְҵ���� */
	private String occupationDescription;

	/** ���Ի���״�� */
	private Integer maritalStatus;
	
	/** ���������� */
	private String annualIncome;

	/** ���Թ�����λ���� */
	private String employerFullName;

	/** ������������/�� */
	private Integer drinkerAmountByYear;

	/** ��������״�� */
	private Integer drinkerStatus;

	/** ������������ */
	private Integer drinkerYears;

	/** ������������/�� */
	private Integer smokerAmountByYear;

	/** ��������״�� */
	private Integer smokerStatus;

	/** ������������ */
	private Integer smokerYears;

	/** �����Ƿ�����ֻ����ŷ��� */
	private Integer acceptSMS;

	/** �����û��� */
	private String customerID;

	/** ���Ծɿͻ���־ */
	private Integer customFlag;
	
	/** ���� ֤���Ƿ�������Ч 1 �� 0 �� ���� */
	private Integer iDValidFlag;

	/** ���� ֤������ */
	private Date iDStartDate;

	/** ���� ֤��ֹ�� */
	private Date iDEndDate;

	/** ���� ���� */
	private String household;

	/** ���� �� */
	private String county;

	/** ���� �ʼ��ʱ� */
	private String homeZipCode;

	/** ���� ��˾���� */
	private String grpName;
	
	/** ���� �������� */
	private String citizenShipName;
	
	/** ���� ���� */
	private String fax;
	
	/** ���� �������� */
	private Integer licenseType;
	
	/** ���� ����������ʾ�� */
	private String licenseTypeView;
	
	/**�ǳ�*/
	private String alias;
	
	/**����*/
	private String hobby;
	
	/**Ѫ��*/
	private String bloodType;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/** �����ͻ� */
	private GeUserPersonal userPersonal;
	
	/** Ĭ�ϱ����� */
	private boolean defaultInsured;

	
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
	 * ���Խ�ɫ��ŵ�getter����
	 */
	@Column(name = "ROLESERIALNO")	
	public String getRoleSerialNo() {
		return roleSerialNo;
	}
	
	/**
	 * ���Խ�ɫ��ŵ�setter����
	 */
	public void setRoleSerialNo(String roleSerialNo) {
		this.roleSerialNo = roleSerialNo;
	}

	/**
	 * ����������getter����
	 */
	@Column(name = "FULLNAME")
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * ����������setter����
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * �����յ�getter����
	 */
	@Column(name = "FIRSTNAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * �����յ�setter����
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * �����յ�getter����
	 */
	@Column(name = "LASTNAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * �����յ�setter����
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * ����Ӣ��������getter����
	 */

	@Column(name = "FULLENNAME")
	public String getFullENName() {
		return this.fullENName;
	}
	
	/**
	 * ����Ӣ��������setter����
	 */
	public void setFullENName(String fullENName) {
		this.fullENName = fullENName;
	}

	/**
	 * ����֤�����͵�getter����
	 */

	@Column(name = "IDTYPE")
	public Integer getIdType() {
		return this.idType;
	}
	
	/**
	 * ����֤������ö�����getter����
	 */
	@Transient
	public IdType getEnumIdType() {
		if (getIdType() == null) {
			return null;
		}
		IdType  idType = (IdType) EnumDataUtils.getEnumDictionaryByValue(IdType.class, getIdType());
		return idType;
	}
	
	/**
	 * ����֤�����ͺ���ֵ��getter����
	 */
	@Transient
	public String getIdTypeByCoreValue() {
		if (getIdType() == null) {
			return "";
		}
		IdType  idType = (IdType) EnumDataUtils.getEnumDictionaryByValue(IdType.class, getIdType());
		return idType.getCoreValue();
	}
	
	/**
	 * ����֤�������̼�ֵ��getter����
	 */
	@Transient
	public String getIdTypeByMerchantValue() {
		if (getIdType() == null) {
			return "";
		}
		IdType  idType = (IdType) EnumDataUtils.getEnumDictionaryByValue(IdType.class, getIdType());
		return idType.getMerchantValue();
	}
	
	
	/**
	 * ����֤�����͵�setter����
	 */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	
	/**
	 * ����֤�����͸�ֵ
	 */
	public void setEnumIdType(IdType  idType) {
		if (idType != null) {
			this.idType = idType.getValue();
		}
	}
	
	/**
	 * ���Ժ���֤�����͸�ֵ
	 */
	public void setIdTypeByCoreValue(String coreValue) {
		IdType  idType = (IdType) EnumDataUtils.getEnumDictionaryByCoreValue(IdType.class, coreValue);
		if (idType != null) {
			this.idType = idType.getValue();
		}
		
	}
	
	/**
	 * �����̼�֤�����͸�ֵ
	 */
	public void setIdTypeByMerchantValue(String merchantValue) {
		IdType  idType = (IdType) EnumDataUtils.getEnumDictionaryByMerchantValue(IdType.class, merchantValue);
		if (idType != null) {
			this.idType = idType.getValue();
		}
	}
	
	/**
	 * ����֤�������getter����
	 */

	@Column(name = "IDNUMBER")
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * ����֤�������setter����
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * ����֤����Ч���ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "IDEXPDATE")
	public Date getIdExpDate() {
		return this.idExpDate;
	}

	/**
	 * ����֤����Ч���ڵ�setter����
	 */
	public void setIdExpDate(Date idExpDate) {
		this.idExpDate = idExpDate;
	}

	/**
	 * �����Ա��getter����
	 */

	@Column(name = "GENDER")
	public Integer getGender() {
		return this.gender;
	}
	/**
	 * �����Ա�ö�����getter����
	 */
	@Transient
	public Gender getEnumGender() {
		if (getGender() == null) {
			return null;
		}
		Gender  gender = (Gender) EnumDataUtils.getEnumDictionaryByValue(Gender.class, getGender());
		return gender;
	}
	
	/**
	 * �����Ա����ֵ��getter����
	 */
	@Transient
	public String getGenderByCoreValue() {
		if (getGender() == null) {
			return "";
		}
		Gender  gender = (Gender) EnumDataUtils.getEnumDictionaryByValue(Gender.class, getGender());
		return gender.getCoreValue();
	}
	
	/**
	 * �����Ա��̼�ֵ��getter����
	 */
	@Transient
	public String getGenderByMerchantValue() {
		if (getGender() == null) {
			return "";
		}
		Gender  gender = (Gender) EnumDataUtils.getEnumDictionaryByValue(Gender.class, getGender());
		return gender.getMerchantValue();
	}
	
	/**
	 * �����Ա��setter����
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	/**
	 * �����Ա�ֵ
	 */
	public void setEnumGender(Gender  gender) {
		if (gender != null) {
			this.gender = gender.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ա�ֵ
	 */
	public void setGenderByCoreValue(String coreValue) {
		Gender  gender = (Gender) EnumDataUtils.getEnumDictionaryByCoreValue(Gender.class, coreValue);
		if (gender != null) {
			this.gender = gender.getValue();
		}
	}
	
	/**
	 * �����̼��Ա�ֵ
	 */
	public void setGenderByMerchantValue(String merchantValue) {
		Gender  gender = (Gender) EnumDataUtils.getEnumDictionaryByMerchantValue(Gender.class, merchantValue);
		if (gender != null) {
			this.gender = gender.getValue();
		}
	}
	
	/**
	 * ���Գ������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE")
	public Date getBirthDate() {
		return this.birthDate;
	}

	/**
	 * ���Գ������ڵ�setter����
	 */
	public void setBirthDate(Date birthdate) {
		this.birthDate = birthdate;
	}

	/**
	 * ���Գ���������getter����
	 */

	@Column(name = "AGEINDAY")
	public Integer getAgeInDay() {
		return this.ageInDay;
	}

	/**
	 * ���Գ���������setter����
	 */
	public void setAgeInDay(Integer ageInDay) {
		this.ageInDay = ageInDay;
	}

	/**
	 * ���������getter����
	 */

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	/**
	 * ���������setter����
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * ���Գ������ص�getter����
	 */

	@Column(name = "BIRTHWEIGHT")
	public Integer getBirthWeight() {
		return this.birthWeight;
	}

	/**
	 * ���Գ������ص�setter����
	 */
	public void setBirthWeight(Integer birthWeight) {
		this.birthWeight = birthWeight;
	}

	/**
	 * ������ߵ�getter����
	 */

	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return this.height;
	}

	/**
	 * ������ߵ�setter����
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * �������ص�getter����
	 */

	@Column(name = "WEIGHT")
	public Integer getWeight() {
		return this.weight;
	}

	/**
	 * �������ص�setter����
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * �����ֻ���getter����
	 */

	@Column(name = "MOBILEPHONENUMBER")
	public String getMobilePhoneNumber() {
		return this.mobilePhoneNumber;
	}

	/**
	 * �����ֻ���setter����
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * ���Թ̶��绰��getter����
	 */

	@Column(name = "PHONENUMBER")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * ���Թ̶��绰��setter����
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * ���԰칫�绰��getter����
	 */

	@Column(name = "OFFICEPHONENUMBER")
	public String getOfficePhoneNumber() {
		return this.officePhoneNumber;
	}

	/**
	 * ���԰칫�绰��setter����
	 */
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	/**
	 * ���Ե������� ��getter����
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * ���Ե������� ��setter����
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * �������������getter����
	 */

	@Column(name = "POSTALCODE")
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * �������������setter����
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
	/**
	 * ����ʡ�ݵ�getter����
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}

	/**
	 * ����ʡ�ݵ�setter����
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * ���Գ��е�getter����
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * ���Գ��е�setter����
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	/**
	 * ���Լ�ͥ��ַ��getter����
	 */
	@Column(name = "HOMEADDRESS")
	public String getHomeAddress() {
		return homeAddress;
	}
	
	/**
	 * ���Լ�ͥ��ַ��setter����
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	/**
	 * ���԰칫��ַ��getter����
	 */
	@Column(name = "OFFICEADDRESS")
	public String getOfficeAddress() {
		return officeAddress;
	}
	
	/**
	 * ���԰칫��ַ��setter����
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	
	/**
	 * ����ͨѶ��ַ��getter����
	 */

	@Column(name = "ADDRESSLINES")
	public String getAddressLines() {
		return this.addressLines;
	}

	/**
	 * ����ͨѶ��ַ��setter����
	 */
	public void setAddressLines(String addressLines) {
		this.addressLines = addressLines;
	}

	/**
	 * ���Թ�����getter����
	 */

	@Column(name = "CITIZENSHIP")
	public Integer getCitizenShip() {
		return this.citizenShip;
	}

	/**
	 * ���Թ�����setter����
	 */
	public void setCitizenShip(Integer citizenShip) {
		this.citizenShip = citizenShip;
	}

	/**
	 * ����ְҵ����getter����
	 */

	@Column(name = "OCCUPATIONCLASS")
	public String getOccupationClass() {
		return this.occupationClass;
	}

	/**
	 * ����ְҵ����setter����
	 */
	public void setOccupationClass(String occupationClass) {
		this.occupationClass = occupationClass;
	}
	
	/**
	 * ����ְҵ�����getter����
	 */
	@Column(name = "OCCUPATIONCODE")
	public String getOccupationCode() {
		return occupationCode;
	}
	
	/**
	 * ����ְҵ�����setter����
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * ����ְҵ������getter����
	 */

	@Column(name = "OCCUPATIONDESCRIPTION")
	public String getOccupationDescription() {
		return this.occupationDescription;
	}

	/**
	 * ����ְҵ������setter����
	 */
	public void setOccupationDescription(String occupationDescription) {
		this.occupationDescription = occupationDescription;
	}
	
	/**
	 * ���Ի���״����getter����
	 */

	@Column(name = "MARITALSTATUS")
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	
	/**
	 * ���Ի���״��ö�����getter����
	 */
	@Transient
	public MaritalStatus getEnumMaritalStatus() {
		if (getMaritalStatus() == null) {
			return null;
		}
		MaritalStatus  maritalStatus = (MaritalStatus) EnumDataUtils.getEnumDictionaryByValue(MaritalStatus.class, getMaritalStatus());
		return maritalStatus;
	}
	
	/**
	 * ���Ի���״������ֵ��getter����
	 */
	@Transient
	public String getMaritalStatusByCoreValue() {
		if (getMaritalStatus() == null) {
			return "";
		}
		MaritalStatus  maritalStatus = (MaritalStatus) EnumDataUtils.getEnumDictionaryByValue(MaritalStatus.class, getMaritalStatus());
		return maritalStatus.getCoreValue();
	}
	
	/**
	 * ���Ի���״���̼�ֵ��getter����
	 */
	@Transient
	public String getMaritalStatusByMerchantValue() {
		if (getMaritalStatus() == null) {
			return "";
		}
		MaritalStatus  maritalStatus = (MaritalStatus) EnumDataUtils.getEnumDictionaryByValue(MaritalStatus.class, getMaritalStatus());
		return maritalStatus.getMerchantValue();
	}
	
	/**
	 * ���Ի���״����ֵ
	 */
	public void setEnumMaritalStatus(BooleanStatus  maritalStatus) {
		this.maritalStatus = maritalStatus.getValue();
	}
	
	/**
	 * ���Ի���״����ֵ
	 */
	public void setMaritalStatusByCoreValue(String coreValue) {
		MaritalStatus  maritalStatus = (MaritalStatus) EnumDataUtils.getEnumDictionaryByCoreValue(MaritalStatus.class, coreValue);
		this.maritalStatus = maritalStatus.getValue();
	}
	
	/**
	 * �����̼һ���״����ֵ
	 */
	public void setMaritalStatusByMerchantValue(String merchantValue) {
		MaritalStatus  maritalStatus = (MaritalStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(MaritalStatus.class, merchantValue);
		this.maritalStatus = maritalStatus.getValue();
	}
	
	
	/**
	 * ���Ի���״��setter����
	 */

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	/**
	 * �����������getter����
	 */

	@Column(name = "ANNUALINCOME")
	public String getAnnualIncome() {
		return this.annualIncome;
	}

	/**
	 * �����������setter����
	 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * ���Թ�����λ���Ƶ�getter����
	 */

	@Column(name = "EMPLOYERFULLNAME")
	public String getEmployerFullName() {
		return this.employerFullName;
	}

	/**
	 * ���Թ�����λ���Ƶ�setter����
	 */
	public void setEmployerFullName(String employerFullName) {
		this.employerFullName = employerFullName;
	}

	/**
	 * ������������/���getter����
	 */

	@Column(name = "DRINKERAMOUNTBYYEAR")
	public Integer getDrinkerAmountByYear() {
		return this.drinkerAmountByYear;
	}

	/**
	 * ������������/���setter����
	 */
	public void setDrinkerAmountByYear(Integer drinkerAmountByYear) {
		this.drinkerAmountByYear = drinkerAmountByYear;
	}

	/**
	 * ��������״����getter����
	 */

	@Column(name = "DRINKERSTATUS")
	public Integer getDrinkerStatus() {
		return this.drinkerStatus;
	}
	
	/**
	 * ��������״��ö�����getter����
	 */
	@Transient
	public DrinkerStatus getEnumDrinkerStatus() {
		if (getDrinkerStatus() == null) {
			return null;
		}
		DrinkerStatus  drinkerStatus = (DrinkerStatus) EnumDataUtils.getEnumDictionaryByValue(DrinkerStatus.class, getDrinkerStatus());
		return drinkerStatus;
	}
	
	/**
	 * ��������״������ֵ��getter����
	 */
	@Transient
	public String getDrinkerStatusByCoreValue() {
		if (getDrinkerStatus() == null) {
			return "";
		}
		DrinkerStatus  drinkerStatus = (DrinkerStatus) EnumDataUtils.getEnumDictionaryByValue(DrinkerStatus.class, getDrinkerStatus());
		return drinkerStatus.getCoreValue();
	}
	
	/**
	 * ��������״���̼�ֵ��getter����
	 */
	@Transient
	public String getDrinkerStatusByMerchantValue() {
		if (getDrinkerStatus() == null) {
			return "";
		}
		DrinkerStatus  drinkerStatus = (DrinkerStatus) EnumDataUtils.getEnumDictionaryByValue(DrinkerStatus.class, getDrinkerStatus());
		return drinkerStatus.getMerchantValue();
	}
	
	
	/**
	 * ��������״����setter����
	 */
	public void setDrinkerStatus(Integer drinkerStatus) {
		this.drinkerStatus = drinkerStatus;
	}
	
	/**
	 * ��������״����ֵ
	 */
	public void setEnumDrinkerStatus(DrinkerStatus  drinkerStatus) {
		this.drinkerStatus = drinkerStatus.getValue();
	}
	
	/**
	 * ���Ժ�������״����ֵ
	 */
	public void setDrinkerStatusByCoreValue(String coreValue) {
		DrinkerStatus  drinkerStatus = (DrinkerStatus) EnumDataUtils.getEnumDictionaryByCoreValue(DrinkerStatus.class, coreValue);
		this.drinkerStatus = drinkerStatus.getValue();
	}
	
	/**
	 * �����̼�����״����ֵ
	 */
	public void setDrinkerStatusByMerchantValue(String merchantValue) {
		DrinkerStatus  drinkerStatus = (DrinkerStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(DrinkerStatus.class, merchantValue);
		this.drinkerStatus = drinkerStatus.getValue();
	}
	
	/**
	 * �����������޵�getter����
	 */

	@Column(name = "DRINKERYEARS")
	public Integer getDrinkerYears() {
		return this.drinkerYears;
	}

	/**
	 * �����������޵�setter����
	 */
	public void setDrinkerYears(Integer drinkerYears) {
		this.drinkerYears = drinkerYears;
	}

	/**
	 * ������������/���getter����
	 */

	@Column(name = "SMOKERAMOUNTBYYEAR")
	public Integer getSmokerAmountByYear() {
		return this.smokerAmountByYear;
	}

	/**
	 * ������������/���setter����
	 */
	public void setSmokerAmountByYear(Integer smokerAmountByYear) {
		this.smokerAmountByYear = smokerAmountByYear;
	}

	/**
	 * ��������״����getter����
	 */
	@Column(name = "SMOKERSTATUS")
	public Integer getSmokerStatus() {
		return this.smokerStatus;
	}
	
	/**
	 * ��������״��ö�����getter����
	 */
	@Transient
	public SmokerStatus getEnumSmokerStatus() {
		if (getSmokerStatus() == null) {
			return null;
		}
		SmokerStatus  smokerStatus = (SmokerStatus) EnumDataUtils.getEnumDictionaryByValue(SmokerStatus.class, getSmokerStatus());
		return smokerStatus;
	}
	
	/**
	 * ��������״������ֵ��getter����
	 */
	@Transient
	public String getSmokerStatusByCoreValue() {
		if (getSmokerStatus() == null) {
			return "";
		}
		SmokerStatus  smokerStatus = (SmokerStatus) EnumDataUtils.getEnumDictionaryByValue(SmokerStatus.class, getSmokerStatus());
		return smokerStatus.getCoreValue();
	}
	
	/**
	 * ��������״���̼�ֵ��getter����
	 */
	@Transient
	public String getSmokerStatusByMerchantValue() {
		if (getSmokerStatus() == null) {
			return "";
		}
		SmokerStatus  smokerStatus = (SmokerStatus) EnumDataUtils.getEnumDictionaryByValue(SmokerStatus.class, getSmokerStatus());
		return smokerStatus.getMerchantValue();
	}
	/**
	 * ��������״����setter����
	 */
	public void setSmokerStatus(Integer smokerStatus) {
		this.smokerStatus = smokerStatus;
	}
	
	
	/**
	 * ��������״����ֵ
	 */
	public void setEnumSmokerStatus(SmokerStatus  smokerStatus) {
		this.smokerStatus = smokerStatus.getValue();
	}
	
	/**
	 * ���Ժ�������״����ֵ
	 */
	public void setSmokerStatusByCoreValue(String coreValue) {
		SmokerStatus  smokerStatus = (SmokerStatus) EnumDataUtils.getEnumDictionaryByCoreValue(SmokerStatus.class, coreValue);
		this.smokerStatus = smokerStatus.getValue();
	}
	
	/**
	 * �����̼�����״����ֵ
	 */
	public void setSmokerStatusByMerchantValue(String merchantValue) {
		SmokerStatus  smokerStatus = (SmokerStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(SmokerStatus.class, merchantValue);
		this.smokerStatus = smokerStatus.getValue();
	}
	
	/**
	 * �����������޵�getter����
	 */

	@Column(name = "SMOKERYEARS")
	public Integer getSmokerYears() {
		return this.smokerYears;
	}
	
	/**
	 * �����������޵�setter����
	 */
	public void setSmokerYears(Integer smokerYears) {
		this.smokerYears = smokerYears;
	}

	/**
	 * �����Ƿ�����ֻ����ŷ����getter����
	 */

	@Column(name = "ACCEPTSMS")
	public Integer getAcceptSMS() {
		return this.acceptSMS;
	}
	
	/**
	 * �����Ƿ�����ֻ����ŷ���ö�����getter����
	 */
	@Transient
	public BooleanStatus getEnumAcceptSMS() {
		if (getAcceptSMS() == null) {
			return null;
		}
		BooleanStatus  acceptSMS = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAcceptSMS());
		return acceptSMS;
	}
	
	/**
	 * �����Ƿ�����ֻ����ŷ������ֵ��getter����
	 */
	@Transient
	public String getAcceptSMSByCoreValue() {
		if (getAcceptSMS() == null) {
			return "";
		}
		BooleanStatus  acceptSMS = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAcceptSMS());
		return acceptSMS.getCoreValue();
	}
	
	/**
	 * �����Ƿ�����ֻ����ŷ����̼�ֵ��getter����
	 */
	@Transient
	public String getAcceptSMSByMerchantValue() {
		if (getAcceptSMS() == null) {
			return "";
		}
		BooleanStatus  acceptSMS = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAcceptSMS());
		return acceptSMS.getMerchantValue();
	}
	
	
	/**
	 * �����Ƿ�����ֻ����ŷ����setter����
	 */
	public void setAcceptSMS(Integer acceptSMS) {
		this.acceptSMS = acceptSMS;
	}

	/**
	 * �����Ƿ�����ֻ����ŷ���ֵ
	 */
	public void setEnumAcceptSMS(BooleanStatus  acceptSMS) {
		this.acceptSMS = acceptSMS.getValue();
	}
	
	/**
	 * ���Ժ����Ƿ�����ֻ����ŷ���ֵ
	 */
	public void setAcceptSMSByCoreValue(String coreValue) {
		BooleanStatus  acceptSMS = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		this.acceptSMS = acceptSMS.getValue();
	}
	
	/**
	 * �����̼��Ƿ�����ֻ����ŷ���ֵ
	 */
	public void setAcceptSMSByMerchantValue(String merchantValue) {
		BooleanStatus  acceptSMS = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		this.acceptSMS = acceptSMS.getValue();
	}
	
	/**
	 * �����û��ŵ�getter����
	 */

	@Column(name = "CUSTOMERID")
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * ���Ծɿͻ���־��getter����
	 */

	@Column(name = "CUSTOMFLAG")
	public Integer getCustomFlag() {
		return this.customFlag;
	}
	
	/**
	 * ���Ծɿͻ���־ö�����getter����
	 */
	@Transient
	public BooleanStatus getEnumCustomFlag() {
		if (getCustomFlag() == null) {
			return null;
		}
		BooleanStatus  customFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCustomFlag());
		return customFlag;
	}
	
	/**
	 * ���Ժ��ľɿͻ���־����ֵ��getter����
	 */
	@Transient
	public String getCustomFlagByCoreValue() {
		if (getCustomFlag() == null) {
			return "";
		}
		BooleanStatus  customFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCustomFlag());
		return customFlag.getCoreValue();
	}
	
	/**
	 * �����̼Ҿɿͻ���־�̼�ֵ��getter����
	 */
	@Transient
	public String getCustomFlagByMerchantValue() {
		if (getCustomFlag() == null) {
			return "";
		}
		BooleanStatus  customFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCustomFlag());
		return customFlag.getMerchantValue();
	}
	
	
	/**
	 * ���Ծɿͻ���־��setter����
	 */
	public void setCustomFlag(Integer customFlag) {
		this.customFlag = customFlag;
	}
	
	/**
	 * ���Ծɿͻ���־��ֵ
	 */
	public void setEnumCustomFlag(BooleanStatus  customFlag) {
		this.customFlag = customFlag.getValue();
	}
	
	/**
	 * ���Ժ��ľɿͻ���־��ֵ
	 */
	public void setCustomFlagByCoreValue(String coreValue) {
		BooleanStatus  customFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		this.customFlag = customFlag.getValue();
	}
	
	/**
	 * �����̼Ҿɿͻ���־��ֵ
	 */
	public void setCustomFlagByMerchantValue(String merchantValue) {
		BooleanStatus  customFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		this.customFlag = customFlag.getValue();
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * ���� ֤���Ƿ�������Ч ��getter����
	 */
	@Column(name = "IDVALIDFLAG")
	public Integer getiDValidFlag() {
		return iDValidFlag;
	}

	/**
	 * ���� ֤���Ƿ�������Ч ��setter����
	 */
	public void setiDValidFlag(Integer iDValidFlag) {
		this.iDValidFlag = iDValidFlag;
	}

	/**
	 * ���� ֤������ ��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "IDSTARTDATE")
	public Date getiDStartDate() {
		return iDStartDate;
	}

	/**
	 * ���� ֤������ ��setter����
	 */
	public void setiDStartDate(Date iDStartDate) {
		this.iDStartDate = iDStartDate;
	}

	/**
	 * ���� ֤��ֹ�� ��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "IDENDDATE")
	public Date getiDEndDate() {
		return iDEndDate;
	}

	/**
	 * ���� ֤��ֹ�� ��setter����
	 */
	public void setiDEndDate(Date iDEndDate) {
		this.iDEndDate = iDEndDate;
	}

	/**
	 * ���� ���� ��getter����
	 */
	@Column(name = "HOUSEHOLD")
	public String getHousehold() {
		return household;
	}

	/**
	 * ���� ���� ��setter����
	 */
	public void setHousehold(String household) {
		this.household = household;
	}

	/**
	 * ���� �� ��getter����
	 */
	@Column(name = "COUNTY")
	public String getCounty() {
		return county;
	}

	/**
	 * ���� �� ��setter����
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * ���� �ʼ��ʱ� ��getter����
	 */
	@Column(name = "HOMEZIPCODE")
	public String getHomeZipCode() {
		return homeZipCode;
	}

	/**
	 * ���� �ʼ��ʱ� ��setter����
	 */
	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	/**
	 * ���� ��˾���� ��getter����
	 */
	@Column(name = "GRPNAME")
	public String getGrpName() {
		return grpName;
	}

	/**
	 * ���� ��˾���� ��setter����
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * ���� �������� ��getter����
	 * @return the citizenShipName
	 */
	@Column(name = "CITIZENSHIPNAME")
	public String getCitizenShipName() {
		return citizenShipName;
	}

	/**
	 * ���� �������� ��setter����
	 * @param citizenShipName the citizenShipName to set
	 */
	public void setCitizenShipName(String citizenShipName) {
		this.citizenShipName = citizenShipName;
	}

	/**
	 * ���� ���� ��getter����
	 * @return the fax
	 */
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	/**
	 * ���� ���� ��setter����
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * ����  �������� ��getter����
	 * @return the licenseType
	 */
	@Column(name = "LICENSETYPE")
	public Integer getLicenseType() {
		return licenseType;
	}

	/**
	 * ���� �������� ��setter����
	 * @param licenseType the licenseType to set
	 */
	public void setLicenseType(Integer licenseType) {
		this.licenseType = licenseType;
	}

	/**
	 * ���� ����������ʾ�� ��getter����
	 * @return the licenseTypeView
	 */
	@Column(name = "LICENSETYPEVIEW")
	public String getLicenseTypeView() {
		return licenseTypeView;
	}

	/**
	 * ���� ����������ʾ�� ��setter����
	 * @param licenseTypeView the licenseTypeView to set
	 */
	public void setLicenseTypeView(String licenseTypeView) {
		this.licenseTypeView = licenseTypeView;
	}

	/**
	 * ���� �ǳ� ��getter����
	 * @return the alias
	 */
	@Column(name = "ALIAS")
	public String getAlias() {
		return alias;
	}

	/**
	 * ���� �ǳ� ��setter����
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * ���� ���� ��getter����
	 * @return the hobby
	 */
	@Column(name = "HOBBY")
	public String getHobby() {
		return hobby;
	}

	/**
	 * ���� ���� ��setter����
	 * @param hobby the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	/**
	 * ���� Ѫ�� ��getter����
	 * @return the bloodType
	 */
	@Column(name = "BLOODTYPE")
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * ���� Ѫ�� ��setter����
	 * @param bloodType the bloodType to set
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	/**
	 * ��Insured��Ĭ�Ϲ��췽��
	 */
	public TopInsured() {
	}

	/**
	 * ������Ͷ���˹�ϵ��getter����
	 */

	@Column(name = "RELATEDTOAPPLICANT")
	public Integer getRelatedToApplicant() {
		return this.relatedToApplicant;
	}


	/**
	 * ������Ͷ���˹�ϵö�����getter����
	 */
	@Transient
	public RelatedToRoleCode getEnumRelatedToApplicant() {
		if (getRelatedToApplicant() == null) {
			return null;
		}
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToApplicant());
		return relatedToApplicant;
	}
	
	/**
	 * ������Ͷ���˹�ϵ����ֵ��getter����
	 */
	@Transient
	public String getRelatedToApplicantByCoreValue() {
		if (getRelatedToApplicant() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToApplicant());
		return relatedToApplicant.getCoreValue();
	}
	
	/**
	 * ������Ͷ���˹�ϵ�̼�ֵ��getter����
	 */
	@Transient
	public String getRelatedToApplicantByMerchantValue() {
		if (getRelatedToApplicant() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToApplicant());
		return relatedToApplicant.getMerchantValue();
	}
	
	/**
	 * ������Ͷ���˹�ϵ��setter����
	 */
	public void setRelatedToApplicant(Integer relatedToApplicant) {
		this.relatedToApplicant = relatedToApplicant;
	}
	
	/**
	 * ������Ͷ���˹�ϵ��ֵ
	 */
	public void setEnumRelatedToApplicant(RelatedToRoleCode  relatedToApplicant) {
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	/**
	 * ���Ժ�����Ͷ���˹�ϵ��ֵ
	 */
	public void setRelatedToApplicantByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	/**
	 * �����̼���Ͷ���˹�ϵ��ֵ
	 */
	public void setRelatedToApplicantByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToApplicant = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToApplicant != null) {
			this.relatedToApplicant = relatedToApplicant.getValue();
		}
	}
	
	
	/**
	 * ���������������˹�ϵ��getter����
	 */

	@Column(name = "RELATEDTOINSURED")
	public Integer getRelatedToInsured() {
		return this.relatedToInsured;
	}
	
	/**
	 * ���������������˹�ϵö�����getter����
	 */
	@Transient
	public RelatedToRoleCode getEnumRelatedToInsured() {
		if (getRelatedToInsured() == null) {
			return null;
		}
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToInsured());
		return relatedToInsured;
	}
	
	/**
	 * ���������������˹�ϵ����ֵ��getter����
	 */
	@Transient
	public String getRelatedToInsuredByCoreValue() {
		if (getRelatedToInsured() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToInsured());
		return relatedToInsured.getCoreValue();
	}
	
	/**
	 * ���������������˹�ϵ�̼�ֵ��getter����
	 */
	@Transient
	public String getRelatedToInsuredByMerchantValue() {
		if (getRelatedToInsured() == null) {
			return "";
		}
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByValue(RelatedToRoleCode.class, getRelatedToInsured());
		return relatedToInsured.getMerchantValue();
	}
	
	/**
	 * ���������������˹�ϵ��setter����
	 */
	public void setRelatedToInsured(Integer relatedToInsured) {
		this.relatedToInsured = relatedToInsured;
	}
	
	/**
	 * �����뱻�����˹�ϵ��ֵ
	 */
	public void setEnumRelatedToInsured(RelatedToRoleCode  relatedToInsured) {
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * ���Ժ����뱻�����˹�ϵ��ֵ
	 */
	public void setRelatedToInsuredByCoreValue(String coreValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByCoreValue(RelatedToRoleCode.class, coreValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * �����̼��뱻�����˹�ϵ��ֵ
	 */
	public void setRelatedToInsuredByMerchantValue(String merchantValue) {
		RelatedToRoleCode  relatedToInsured = (RelatedToRoleCode) EnumDataUtils.getEnumDictionaryByMerchantValue(RelatedToRoleCode.class, merchantValue);
		if (relatedToInsured != null) {
			this.relatedToInsured = relatedToInsured.getValue();
		}
	}
	
	/**
	 * ���Ա����˱�־��getter����
	 */
	@Column(name = "MAININSUREDFLAG")
	public Integer getMainInsuredFlag() {
		return mainInsuredFlag;
	}
	
	/**
	 * ���Ա����˱�־ö�����getter����
	 */
	@Transient
	public MainInsuredFlag getEnumMainInsuredFlag() {
		if (getMainInsuredFlag() == null) {
			return null;
		}
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByValue(MainInsuredFlag.class, getMainInsuredFlag());
		return mainInsuredFlag;
	}
	
	/**
	 * ���Ա����˱�־����ֵ��getter����
	 */
	@Transient
	public String getMainInsuredFlagByCoreValue() {
		if (getMainInsuredFlag() == null) {
			return "";
		}
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByValue(MainInsuredFlag.class, getMainInsuredFlag());
		return mainInsuredFlag.getCoreValue();
	}
	
	/**
	 * ���Ա����˱�־�̼�ֵ��getter����
	 */
	@Transient
	public String getMainInsuredFlagByMerchantValue() {
		if (getMainInsuredFlag() == null) {
			return "";
		}
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByValue(MainInsuredFlag.class, getMainInsuredFlag());
		return mainInsuredFlag.getMerchantValue();
	}
	
	
	/**
	 * ���Ա����˱�־��setter����
	 */
	public void setMainInsuredFlag(Integer mainInsuredFlag) {
		this.mainInsuredFlag = mainInsuredFlag;
	}
	
	/**
	 * ���Ա����˱�־��ֵ
	 */
	public void setEnumMainInsuredFlag(MainInsuredFlag  mainInsuredFlag) {
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * ���Ժ��ı����˱�־��ֵ
	 */
	public void setMainInsuredFlagByCoreValue(String coreValue) {
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByCoreValue(MainInsuredFlag.class, coreValue);
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * �����̼ұ����˱�־��ֵ
	 */
	public void setMainInsuredFlagByMerchantValue(String merchantValue) {
		MainInsuredFlag  mainInsuredFlag = (MainInsuredFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(MainInsuredFlag.class, merchantValue);
		if (mainInsuredFlag != null) {
			this.mainInsuredFlag = mainInsuredFlag.getValue();
		}
	}
	
	/**
	 * ���Ա���˳���getter����
	 */

	@Column(name = "ROLEORDER")
	public Integer getInsuredOrder() {
		return this.insuredOrder;
	}

	/**
	 * ���Ա���˳���setter����
	 */
	public void setInsuredOrder(Integer insuredOrder) {
		this.insuredOrder = insuredOrder;
	}

	/**
	 * ������ռ����ٷֱȵ�getter����
	 */

	@Column(name = "INSUREDAMOUNTPERCENT")
	public BigDecimal getInsuredAmountPercent() {
		return this.insuredAmountPercent;
	}

	/**
	 * ������ռ����ٷֱȵ�setter����
	 */
	public void setInsuredAmountPercent(BigDecimal insuredAmountPercent) {
		this.insuredAmountPercent = insuredAmountPercent;
	}

	/**
	 * ����Ͷ���˸�֪��Ϣ��getter����
	 */

	@Column(name = "INDICATORMESSAGE")
	public String getIndicatorMessage() {
		return this.indicatorMessage;
	}

	/**
	 * ����Ͷ���˸�֪��Ϣ��setter����
	 */
	public void setIndicatorMessage(String indicatorMessage) {
		this.indicatorMessage = indicatorMessage;
	}

	/**
	 * ����ͬҵ�����getter����
	 */

	@Column(name = "SAMEINDUSTRYINSUREDAMOUNT")
	public String getSameIndustryInsuredAmount() {
		return this.sameIndustryInsuredAmount;
	}

	/**
	 * ����ͬҵ�����setter����
	 */
	public void setSameIndustryInsuredAmount(String sameIndustryInsuredAmount) {
		this.sameIndustryInsuredAmount = sameIndustryInsuredAmount;
	}

	@ManyToOne(targetEntity = GeUserPersonal.class, fetch = FetchType.LAZY)
	@JoinColumn(name="USERPERSONAL_ID", nullable=true, insertable = true, updatable = true) 
	public GeUserPersonal getUserPersonal() {
		return userPersonal;
	}

	public void setUserPersonal(GeUserPersonal userPersonal) {
		this.userPersonal = userPersonal;
	}

	@Type(type="yes_no")
	public boolean isDefaultInsured() {
		return defaultInsured;
	}

	public void setDefaultInsured(boolean defaultInsured) {
		this.defaultInsured = defaultInsured;
	}

	
}
