package cn.com.sinosoft.ebusiness.service.user.common.domain;

// default package
// Generated 2013-8-15 15:51:15 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "GE_CUSTOMER")
public class Customer implements java.io.Serializable {

	private String customerId;
	private String customerName;
	private Integer gender;
	private Date birthday;
	private Integer identifyType;
	private String identfyNumber;
	private String zipcode;
	private String companyPhone;
	private String homePhone;
	private String mobile;
	private String email;
	private String address;
	private String occupation;
	private String occupaTionType;
	private String remark;
	private String flag;
	private String occupationm;
	private String occupationTypem;
	private String occupations;
	private String occupationTypes;
	private String password;
	private String userId;
	private String token;
	private String sign;
	private String isSuccess;
	private String signType;
	private String notifyId;
	private String serialno;
	private String realName;
	private Set geMemberProposals = new HashSet(0);
	private Set geUserPersonals = new HashSet(0);
	private Set geActiveCardMembers = new HashSet(0);
	
//	public LoginUserInfo loginUserInfo;

	public Customer() {
	}

	public Customer(String customerId) {
		this.customerId = customerId;
	}

	public Customer(String customerId, String customerName, Integer gender,
			Date birthday, Integer identifyType, String identfyNumber,
			String zipcode, String companyPhone, String homePhone,
			String mobile, String email, String address, String occupation,
			String occupaTionType, String remark, String flag,
			String occupationm, String occupationTypem, String occupations,
			String occupationTypes, String password, String userId,
			String token, String sign, String isSuccess, String signType,
			String notifyId, String serialno, String realName,
			Set geMemberProposals, Set geUserPersonals, Set geActiveCardMembers) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.birthday = birthday;
		this.identifyType = identifyType;
		this.identfyNumber = identfyNumber;
		this.zipcode = zipcode;
		this.companyPhone = companyPhone;
		this.homePhone = homePhone;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.occupation = occupation;
		this.occupaTionType = occupaTionType;
		this.remark = remark;
		this.flag = flag;
		this.occupationm = occupationm;
		this.occupationTypem = occupationTypem;
		this.occupations = occupations;
		this.occupationTypes = occupationTypes;
		this.password = password;
		this.userId = userId;
		this.token = token;
		this.sign = sign;
		this.isSuccess = isSuccess;
		this.signType = signType;
		this.notifyId = notifyId;
		this.serialno = serialno;
		this.realName = realName;
		this.geMemberProposals = geMemberProposals;
		this.geUserPersonals = geUserPersonals;
		this.geActiveCardMembers = geActiveCardMembers;
	}

	@Id
	@Column(name = "CUSTOMERID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CUSTOMERNAME", length = 120)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "GENDER")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "IDENTIFYTYPE")
	public Integer getIdentifyType() {
		return this.identifyType;
	}

	public void setIdentifyType(Integer identifyType) {
		this.identifyType = identifyType;
	}

	@Column(name = "IDENTIFYNUMBER", length = 100)
	public String getIdentfyNumber() {
		return this.identfyNumber;
	}

	public void setIdentfyNumber(String identfyNumber) {
		this.identfyNumber = identfyNumber;
	}

	@Column(name = "ZIPCODE", length = 6)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "COMPANYPHONE", length = 30)
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	@Column(name = "HOMEPHONE", length = 30)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "MOBILE", length = 15)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "OCCUPATION", length = 150)
	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name = "OCCUPATIONTYPE", length = 10)
	public String getOccupaTionType() {
		return this.occupaTionType;
	}

	public void setOccupaTionType(String occupaTionType) {
		this.occupaTionType = occupaTionType;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "FLAG", length = 10)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "OCCUPATIONM", length = 150)
	public String getOccupationm() {
		return this.occupationm;
	}

	public void setOccupationm(String occupationm) {
		this.occupationm = occupationm;
	}

	@Column(name = "OCCUPATIONTYPEM", length = 10)
	public String getOccupationTypem() {
		return this.occupationTypem;
	}

	public void setOccupationTypem(String occupationTypem) {
		this.occupationTypem = occupationTypem;
	}

	@Column(name = "OCCUPATIONS", length = 150)
	public String getOccupations() {
		return this.occupations;
	}

	public void setOccupations(String occupations) {
		this.occupations = occupations;
	}

	@Column(name = "OCCUPATIONTYPES", length = 10)
	public String getOccupationTypes() {
		return this.occupationTypes;
	}

	public void setOccupationTypes(String occupationTypes) {
		this.occupationTypes = occupationTypes;
	}

	@Column(name = "PASSWORD", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "TOKEN", length = 32)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "SIGN", length = 16)
	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "IS_SUCCESS", length = 4)
	public String getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Column(name = "SIGN_TYPE", length = 16)
	public String getSignType() {
		return this.signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	@Column(name = "NOTIFY_ID", length = 16)
	public String getNotifyId() {
		return this.notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	@Column(name = "SERIALNO", length = 32)
	public String getSerialno() {
		return this.serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	@Column(name = "REAL_NAME", length = 16)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

//	public LoginUserInfo getLoginUserInfo() {
//		return loginUserInfo;
//	}
//
//	public void setLoginUserInfo(LoginUserInfo loginUserInfo) {
//		this.loginUserInfo = loginUserInfo;
//	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//	public Set getGeMemberProposals() {
//		return this.geMemberProposals;
//	}
//
//	public void setGeMemberProposals(Set geMemberProposals) {
//		this.geMemberProposals = geMemberProposals;
//	}
//
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "GE_CUSTOMER_USER", joinColumns = { @JoinColumn(name = "CUSTOMERID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USERID", nullable = false, updatable = false) })
//	public Set getGeUserPersonals() {
//		return this.geUserPersonals;
//	}
//
//	public void setGeUserPersonals(Set geUserPersonals) {
//		this.geUserPersonals = geUserPersonals;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//	public Set getGeActiveCardMembers() {
//		return this.geActiveCardMembers;
//	}
//
//	public void setGeActiveCardMembers(Set geActiveCardMembers) {
//		this.geActiveCardMembers = geActiveCardMembers;
//	}

	
}
