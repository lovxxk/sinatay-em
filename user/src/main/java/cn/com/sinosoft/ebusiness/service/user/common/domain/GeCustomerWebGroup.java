package cn.com.sinosoft.ebusiness.service.user.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * POJO��GeCustomerWebGroup
 */
@Entity
@Table(name = "GE_CUSTOMER_WEBGROUP")
public class GeCustomerWebGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����û��� */
	private String userID;

	/** ������֯��������*/
	private String organizeCode;

	/** ���Ե������� */
	private String email;

	/** �����ֻ� */
	private String mobile;

	/** ���Ե�¼���� */
	private String pwd;

	/** ����pwd2 */
	private String pwd2;

	/** �����û��� */
	private String userName;

	/** �������ڲ��� */
	private String department;
	
	/** �������� */
	private String name;

	/** �����Ա� */
	private String sex;

	/** ���Գ������� */
	private Date birthday;

	/** ����֤������ */
	private String identifyType;

	/** ����֤������ */
	private String identifyNumber;

	/** ���Լ�ͥ�绰 */
	private String homePhone;

	/** ���԰칫�绰 */
	private String officePhone;

	/** ������ϵ��ַ */
	private String contactAddress;

	/** ������������ */
	private String zipCode;

	/** ����֤��ʶ���� */
	private String ukey;

	/** ����״̬ */
	private String status;

	/** ������ҵ��ģ */
	private String enterpriseSize;

	/** ��������ʡ */
	private String provinceCode;

	/** ���������� */
	private String cityCode;

	/** �������״̬ */
	private String checkStatus;

	/** ������ҵ���� */
	private String enterpriseName;

	/** ������ҵע���ַ */
	private String registAdress;

	/** ������ҵ�������� */
	private String enterpriseType;

	/** ����������ҵ */
	private String industry;

	/** ���Է��˴��� */
	private String legalRepresentative;

	/** ����ע���ʽ� */
	private BigDecimal registeredCapital;

	/** �������ܵ�λ */
	private String headUnit;

	/** ���Ծ�Ӫ��Χ */
	private String businessScope;

	/** ���Ե���Χ */
	private String areaScope;
	
	/** ������֤��*/
	private String authCode;
	
	/** ������֤����Чֹ��*/
	private Date authCodeEndDate;

	/** ���Ա�ʶλ */
	private String flag;
	
	//ҵ����ʹ�õ��ֶ�,�ǳ־û�start
	private String newPassword;
	private String emailPictureUrl;
	
	//ע��ʱ��
	private Date registerTime;
    
	//ҵ����ʹ�õ��ֶ�,�ǳ־û�end
	/**
	 * ��GeCustomerWebGroup��Ĭ�Ϲ��췽��
	 */
	public GeCustomerWebGroup() {
	}

	/**
	 * �����û��ŵ�getter����
	 */
	@Id
	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * ������֯���������getter����
	 */
	@Column(name = "ORGANIZECODE")
	public String getOrganizeCode() {
		return organizeCode;
	}

	/**
	 * ������֯���������setter����
	 */
	public void setOrganizeCode(String organizeCode) {
		this.organizeCode = organizeCode;
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
	 * �����ֻ���getter����
	 */

	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * �����ֻ���setter����
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * ���Ե�¼�����getter����
	 */

	@Column(name = "PWD")
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * ���Ե�¼�����setter����
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * ����pwd2��getter����
	 */

	@Column(name = "PWD2")
	public String getPwd2() {
		return this.pwd2;
	}

	/**
	 * ����pwd2��setter����
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	/**
	 * �����û�����getter����
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * �����û�����setter����
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ����������getter����
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	
	/**
	 * ����������setter����
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * �������ڲ��ŵ�getter����
	 */

	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return this.department;
	}

	/**
	 * �������ڲ��ŵ�setter����
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * �����Ա��getter����
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * �����Ա��setter����
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * ���Գ������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * ���Գ������ڵ�setter����
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * ����֤�����͵�getter����
	 */

	@Column(name = "IDENTIFYTYPE")
	public String getIdentifyType() {
		return this.identifyType;
	}

	/**
	 * ����֤�����͵�setter����
	 */
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	/**
	 * ����֤�������getter����
	 */

	@Column(name = "IDENTIFYNUMBER")
	public String getIdentifyNumber() {
		return this.identifyNumber;
	}

	/**
	 * ����֤�������setter����
	 */
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	/**
	 * ���Լ�ͥ�绰��getter����
	 */

	@Column(name = "HOMEPHONE")
	public String getHomePhone() {
		return this.homePhone;
	}

	/**
	 * ���Լ�ͥ�绰��setter����
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * ���԰칫�绰��getter����
	 */

	@Column(name = "OFFICEPHONE")
	public String getOfficePhone() {
		return this.officePhone;
	}

	/**
	 * ���԰칫�绰��setter����
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * ������ϵ��ַ��getter����
	 */

	@Column(name = "CONTACTADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	/**
	 * ������ϵ��ַ��setter����
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
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
	 * ����֤��ʶ�����getter����
	 */

	@Column(name = "UKEY")
	public String getUkey() {
		return this.ukey;
	}

	/**
	 * ����֤��ʶ�����setter����
	 */
	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	/**
	 * ����״̬��getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * ����״̬��setter����
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ������ҵ��ģ��getter����
	 */

	@Column(name = "ENTERPRISESIZE")
	public String getEnterpriseSize() {
		return this.enterpriseSize;
	}

	/**
	 * ������ҵ��ģ��setter����
	 */
	public void setEnterpriseSize(String enterpriseSize) {
		this.enterpriseSize = enterpriseSize;
	}

	/**
	 * ��������ʡ��getter����
	 */

	@Column(name = "PROVINCECODE")
	public String getProvinceCode() {
		return this.provinceCode;
	}

	/**
	 * ��������ʡ��setter����
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * ���������е�getter����
	 */

	@Column(name = "CITYCODE")
	public String getCityCode() {
		return this.cityCode;
	}

	/**
	 * ���������е�setter����
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * �������״̬��getter����
	 */

	@Column(name = "CHECKSTATUS")
	public String getCheckStatus() {
		return this.checkStatus;
	}

	/**
	 * �������״̬��setter����
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * ������ҵ���Ƶ�getter����
	 */

	@Column(name = "ENTERPRISENAME")
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * ������ҵ���Ƶ�setter����
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
	 * ������ҵע���ַ��getter����
	 */

	@Column(name = "REGISTADRESS")
	public String getRegistAdress() {
		return this.registAdress;
	}

	/**
	 * ������ҵע���ַ��setter����
	 */
	public void setRegistAdress(String registAdress) {
		this.registAdress = registAdress;
	}

	/**
	 * ������ҵ�������͵�getter����
	 */

	@Column(name = "ENTERPRISETYPE")
	public String getEnterpriseType() {
		return this.enterpriseType;
	}

	/**
	 * ������ҵ�������͵�setter����
	 */
	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	/**
	 * ����������ҵ��getter����
	 */

	@Column(name = "INDUSTRY")
	public String getIndustry() {
		return this.industry;
	}

	/**
	 * ����������ҵ��setter����
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * ���Է��˴����getter����
	 */

	@Column(name = "LEGALREPRESENTATIVE")
	public String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	/**
	 * ���Է��˴����setter����
	 */
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	/**
	 * ����ע���ʽ��getter����
	 */

	@Column(name = "REGISTEREDCAPITAL")
	public BigDecimal getRegisteredCapital() {
		return this.registeredCapital;
	}

	/**
	 * ����ע���ʽ��setter����
	 */
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	/**
	 * �������ܵ�λ��getter����
	 */

	@Column(name = "HEADUNIT")
	public String getHeadUnit() {
		return this.headUnit;
	}

	/**
	 * �������ܵ�λ��setter����
	 */
	public void setHeadUnit(String headUnit) {
		this.headUnit = headUnit;
	}

	/**
	 * ���Ծ�Ӫ��Χ��getter����
	 */

	@Column(name = "BUSINESSSCOPE")
	public String getBusinessScope() {
		return this.businessScope;
	}

	/**
	 * ���Ծ�Ӫ��Χ��setter����
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	/**
	 * ���Ե���Χ��getter����
	 */

	@Column(name = "AREASCOPE")
	public String getAreaScope() {
		return this.areaScope;
	}

	/**
	 * ���Ե���Χ��setter����
	 */
	public void setAreaScope(String areaScope) {
		this.areaScope = areaScope;
	}
	
	/**
	 * ������֤���getter����
	 */
	@Column(name="AUTHCODE")
	public String getAuthCode() {
		return authCode;
	}
	
	/**
	 * ������֤���setter����
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	/**
	 * ������֤����Чֹ�ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AUTHCODEENDDATE")
	public Date getAuthCodeEndDate() {
		return authCodeEndDate;
	}
	
	/**
	 * ������֤����Чֹ�ڵ�setter����
	 */
	public void setAuthCodeEndDate(Date authCodeEndDate) {
		this.authCodeEndDate = authCodeEndDate;
	}
	
	/**
	 * ���Ա�ʶλ��getter����
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ���Ա�ʶλ��setter����
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	//ҵ����ʹ�õ��ֶ�,�ǳ־û�
	@Transient
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Transient
	public String getEmailPictureUrl() {
		return emailPictureUrl;
	}
	public void setEmailPictureUrl(String emailPictureUrl) {
		this.emailPictureUrl = emailPictureUrl;
	}
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTERTIME")
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	

}

