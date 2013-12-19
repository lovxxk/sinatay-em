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
 * POJO类GeCustomerWebGroup
 */
@Entity
@Table(name = "GE_CUSTOMER_WEBGROUP")
public class GeCustomerWebGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性用户号 */
	private String userID;

	/** 属性组织机构代码*/
	private String organizeCode;

	/** 属性电子邮箱 */
	private String email;

	/** 属性手机 */
	private String mobile;

	/** 属性登录密码 */
	private String pwd;

	/** 属性pwd2 */
	private String pwd2;

	/** 属性用户名 */
	private String userName;

	/** 属性所在部门 */
	private String department;
	
	/** 属性姓名 */
	private String name;

	/** 属性性别 */
	private String sex;

	/** 属性出生日期 */
	private Date birthday;

	/** 属性证件类型 */
	private String identifyType;

	/** 属性证件号码 */
	private String identifyNumber;

	/** 属性家庭电话 */
	private String homePhone;

	/** 属性办公电话 */
	private String officePhone;

	/** 属性联系地址 */
	private String contactAddress;

	/** 属性邮政编码 */
	private String zipCode;

	/** 属性证书识别码 */
	private String ukey;

	/** 属性状态 */
	private String status;

	/** 属性企业规模 */
	private String enterpriseSize;

	/** 属性所在省 */
	private String provinceCode;

	/** 属性所在市 */
	private String cityCode;

	/** 属性审核状态 */
	private String checkStatus;

	/** 属性企业名称 */
	private String enterpriseName;

	/** 属性企业注册地址 */
	private String registAdress;

	/** 属性企业经济类型 */
	private String enterpriseType;

	/** 属性所属行业 */
	private String industry;

	/** 属性法人代表 */
	private String legalRepresentative;

	/** 属性注册资金 */
	private BigDecimal registeredCapital;

	/** 属性主管单位 */
	private String headUnit;

	/** 属性经营范围 */
	private String businessScope;

	/** 属性地域范围 */
	private String areaScope;
	
	/** 属性验证码*/
	private String authCode;
	
	/** 属性验证码有效止期*/
	private Date authCodeEndDate;

	/** 属性标识位 */
	private String flag;
	
	//业务上使用的字段,非持久化start
	private String newPassword;
	private String emailPictureUrl;
	
	//注册时间
	private Date registerTime;
    
	//业务上使用的字段,非持久化end
	/**
	 * 类GeCustomerWebGroup的默认构造方法
	 */
	public GeCustomerWebGroup() {
	}

	/**
	 * 属性用户号的getter方法
	 */
	@Id
	@Column(name = "USERID")
	public String getUserID() {
		return this.userID;
	}

	/**
	 * 属性用户号的setter方法
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * 属性组织机构代码的getter方法
	 */
	@Column(name = "ORGANIZECODE")
	public String getOrganizeCode() {
		return organizeCode;
	}

	/**
	 * 属性组织机构代码的setter方法
	 */
	public void setOrganizeCode(String organizeCode) {
		this.organizeCode = organizeCode;
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
	 * 属性手机的getter方法
	 */

	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 属性手机的setter方法
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 属性登录密码的getter方法
	 */

	@Column(name = "PWD")
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * 属性登录密码的setter方法
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 属性pwd2的getter方法
	 */

	@Column(name = "PWD2")
	public String getPwd2() {
		return this.pwd2;
	}

	/**
	 * 属性pwd2的setter方法
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	/**
	 * 属性用户名的getter方法
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 属性用户名的setter方法
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 属性姓名的getter方法
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	
	/**
	 * 属性姓名的setter方法
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 属性所在部门的getter方法
	 */

	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return this.department;
	}

	/**
	 * 属性所在部门的setter方法
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 属性性别的getter方法
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * 属性性别的setter方法
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 属性出生日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * 属性出生日期的setter方法
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 属性证件类型的getter方法
	 */

	@Column(name = "IDENTIFYTYPE")
	public String getIdentifyType() {
		return this.identifyType;
	}

	/**
	 * 属性证件类型的setter方法
	 */
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	/**
	 * 属性证件号码的getter方法
	 */

	@Column(name = "IDENTIFYNUMBER")
	public String getIdentifyNumber() {
		return this.identifyNumber;
	}

	/**
	 * 属性证件号码的setter方法
	 */
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	/**
	 * 属性家庭电话的getter方法
	 */

	@Column(name = "HOMEPHONE")
	public String getHomePhone() {
		return this.homePhone;
	}

	/**
	 * 属性家庭电话的setter方法
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * 属性办公电话的getter方法
	 */

	@Column(name = "OFFICEPHONE")
	public String getOfficePhone() {
		return this.officePhone;
	}

	/**
	 * 属性办公电话的setter方法
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	/**
	 * 属性联系地址的getter方法
	 */

	@Column(name = "CONTACTADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	/**
	 * 属性联系地址的setter方法
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
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
	 * 属性证书识别码的getter方法
	 */

	@Column(name = "UKEY")
	public String getUkey() {
		return this.ukey;
	}

	/**
	 * 属性证书识别码的setter方法
	 */
	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	/**
	 * 属性状态的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * 属性状态的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 属性企业规模的getter方法
	 */

	@Column(name = "ENTERPRISESIZE")
	public String getEnterpriseSize() {
		return this.enterpriseSize;
	}

	/**
	 * 属性企业规模的setter方法
	 */
	public void setEnterpriseSize(String enterpriseSize) {
		this.enterpriseSize = enterpriseSize;
	}

	/**
	 * 属性所在省的getter方法
	 */

	@Column(name = "PROVINCECODE")
	public String getProvinceCode() {
		return this.provinceCode;
	}

	/**
	 * 属性所在省的setter方法
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * 属性所在市的getter方法
	 */

	@Column(name = "CITYCODE")
	public String getCityCode() {
		return this.cityCode;
	}

	/**
	 * 属性所在市的setter方法
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 属性审核状态的getter方法
	 */

	@Column(name = "CHECKSTATUS")
	public String getCheckStatus() {
		return this.checkStatus;
	}

	/**
	 * 属性审核状态的setter方法
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * 属性企业名称的getter方法
	 */

	@Column(name = "ENTERPRISENAME")
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * 属性企业名称的setter方法
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
	 * 属性企业注册地址的getter方法
	 */

	@Column(name = "REGISTADRESS")
	public String getRegistAdress() {
		return this.registAdress;
	}

	/**
	 * 属性企业注册地址的setter方法
	 */
	public void setRegistAdress(String registAdress) {
		this.registAdress = registAdress;
	}

	/**
	 * 属性企业经济类型的getter方法
	 */

	@Column(name = "ENTERPRISETYPE")
	public String getEnterpriseType() {
		return this.enterpriseType;
	}

	/**
	 * 属性企业经济类型的setter方法
	 */
	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	/**
	 * 属性所属行业的getter方法
	 */

	@Column(name = "INDUSTRY")
	public String getIndustry() {
		return this.industry;
	}

	/**
	 * 属性所属行业的setter方法
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * 属性法人代表的getter方法
	 */

	@Column(name = "LEGALREPRESENTATIVE")
	public String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	/**
	 * 属性法人代表的setter方法
	 */
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	/**
	 * 属性注册资金的getter方法
	 */

	@Column(name = "REGISTEREDCAPITAL")
	public BigDecimal getRegisteredCapital() {
		return this.registeredCapital;
	}

	/**
	 * 属性注册资金的setter方法
	 */
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	/**
	 * 属性主管单位的getter方法
	 */

	@Column(name = "HEADUNIT")
	public String getHeadUnit() {
		return this.headUnit;
	}

	/**
	 * 属性主管单位的setter方法
	 */
	public void setHeadUnit(String headUnit) {
		this.headUnit = headUnit;
	}

	/**
	 * 属性经营范围的getter方法
	 */

	@Column(name = "BUSINESSSCOPE")
	public String getBusinessScope() {
		return this.businessScope;
	}

	/**
	 * 属性经营范围的setter方法
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	/**
	 * 属性地域范围的getter方法
	 */

	@Column(name = "AREASCOPE")
	public String getAreaScope() {
		return this.areaScope;
	}

	/**
	 * 属性地域范围的setter方法
	 */
	public void setAreaScope(String areaScope) {
		this.areaScope = areaScope;
	}
	
	/**
	 * 属性验证码的getter方法
	 */
	@Column(name="AUTHCODE")
	public String getAuthCode() {
		return authCode;
	}
	
	/**
	 * 属性验证码的setter方法
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	/**
	 * 属性验证码有效止期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AUTHCODEENDDATE")
	public Date getAuthCodeEndDate() {
		return authCodeEndDate;
	}
	
	/**
	 * 属性验证码有效止期的setter方法
	 */
	public void setAuthCodeEndDate(Date authCodeEndDate) {
		this.authCodeEndDate = authCodeEndDate;
	}
	
	/**
	 * 属性标识位的getter方法
	 */

	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性标识位的setter方法
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	//业务上使用的字段,非持久化
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

