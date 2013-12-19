package cn.com.sinosoft.ebusiness.service.user.personal.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * POJO类GeUserPersonal
 */
@Entity
@Table(name = "GE_USER_PERSONAL")
public class GeUserPersonal implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性用户号 */
	private String userID;

	/** 属性用户等级 */
	private String userLevel;

	/** 属性用户帐号 */
	private String userAccount;

	/** 属性姓名 */
	private String userName;

	/** 属性鹤卡号 */
	private String piCardNo;

	/** 属性登录密码(不可逆) */
	private String pwd;

	/** 属性密码(可逆) */
	private String pwd2;

	/** 属性电子邮箱 */
	private String email;

	/** 属性移动电话 */
	private String mobilePhone;

	/** 属性证件类型 */
	private String identifyType;
	
	/**属性证件有效期*/
	private Date identifyEffectiveDate;

	/** 属性证件号码 */
	private String identifyNumber;

	/** 属性性别 (0-男，1-女)*/
	private String sex;

	/** 属性出生日期 */
	private Date birthday;

	/** 属性婚姻状况(1-未婚；2-已婚；3-离婚；4-丧偶；9-未说明的婚姻状况) */
	private String marriageStatus;

	/** 属性所属地区代码 */
	private String areaCode;

	/** 属性所属行业 */
	private String industry;

	/** 属性收入状况 */
	private String income;

	/** 属性家庭电话 */
	private String homePhone;

	/** 属性办公电话 */
	private String officePhone;

	/** 属性省份 */
	private String provinces;

	/** 属性城市 */
	private String city;

	/** 属性区 */
	private String area;

	/** 属性联系地址 */
	private String contactAddress;

	/** 属性邮政编码 */
	private String zipCode;

	/** 属性用户来源 (1-集团；2-寿险；3-财险；4-养老险；9-其他)*/
	private String userSource;

	/** 属性健康状况(1-优秀；2-良好；3-差) */
	private String health;

	/** 属性客户状态(0-无效；1-有效；2-未开通) */
	private String status;

	/** 属性激活码 */
	private String activeCode;

	/** 属性证书识别码 */
	private String ukey;

	/** 属性审核状态(0-未验真,1-邮箱验真,2-手机验真) */
	private String checkStatus;

	/** 属性积分 */
	private BigDecimal integral = new BigDecimal("0");

	/** 属性创建日期 */
	private Date makeDate = new Date();
	
	/** 属性寿险用户等级（T：没有保单的，也即简单注册用户；E：网上购买的，也即网销成交客户；  C：有正常保单的成交客户（非网上购买的），也即注册用户） */
	private String userRank;

	/** 属性标识位 */
	private String flag;
	
	/** 属性RA认证状态（0:未认证,1:已认证） */
	private String raInd;

	/** 属性登录时间 */
	private Date loginTime;
	
	/** 属性登录次数 */
	private long loginNum = 0L;
	
	/** 属性邮箱验真状态(0-未验真,1-已验真) */
	private String checkMail;
	
	/** 属性手机验真状态(0-未验真,1-已验真) */
	private String checkMobile;
	
	/** 操作结果集 */
	@Transient
	private List results;
	
	/** 当前数据是否有效*/
	private boolean active;
	
	/**昵称*/
	private String alias;
	
	/**爱好*/
	private String hobby;
	
	/**血型*/
	private String bloodType;
	
	/**QQ，支付宝，新浪微博返回的用户唯一Id*/
	private String uniqueId;
	
	private Set<TopInsured> topInsureds = new HashSet<TopInsured>(0);
	
	/**用户上次登录时间*/
	private Date lastLoginTime;
	
	/**用户此次登录时间*/
	private Date currentLogintime;
	
	/**密码强度标识 --高/中/低*/
	private String passwordGrade;
	
	/**数据同步标示--Y同步，N不同步*/
	private String syncFlag = "Y";
	
	/**来源*/
	private String source;
	
	/**用户密码输入错误次数，超过三次之后，锁定帐号*/
	public Integer loginFailedCount;
	
	/**当前帐号是否已被锁定*/
	public boolean lockUserAccount = false;
	
	/**帐号锁定时间*/
	public Date lockTime;
	
	/**
	 * 类GeUserPersonal的默认构造方法
	 */
	public GeUserPersonal() {
	}

	/**
	 * 属性用户号的getter方法
	 */
	@Id
	@Column(name = "USERID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
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
	 * 属性用户等级的getter方法
	 */

	@Column(name = "USERLEVEL")
	public String getUserLevel() {
		return this.userLevel;
	}

	/**
	 * 属性用户等级的setter方法
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * 属性用户帐号的getter方法
	 */

	@Column(name = "USERACCOUNT")
	public String getUserAccount() {
		return this.userAccount;
	}

	/**
	 * 属性用户帐号的setter方法
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * 属性姓名的getter方法
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 属性姓名的setter方法
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 属性鹤卡号的getter方法
	 */

	@Column(name = "PICARDNO")
	public String getPiCardNo() {
		return this.piCardNo;
	}

	/**
	 * 属性鹤卡号的setter方法
	 */
	public void setPiCardNo(String piCardNo) {
		this.piCardNo = piCardNo;
	}

	/**
	 * 属性登录密码(不可逆)的getter方法
	 */

	@Column(name = "PWD")
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * 属性登录密码(不可逆)的setter方法
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 属性密码(可逆)的getter方法
	 */

	@Column(name = "PWD2")
	public String getPwd2() {
		return this.pwd2;
	}

	/**
	 * 属性密码(可逆)的setter方法
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
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
	 * 属性移动电话的getter方法
	 */
	@Column(name = "MOBILEPHONE")
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * 属性移动电话的setter方法
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
	 * 属性性别 (0-男，1-女)的getter方法
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * 属性性别 (0-男，1-女)的setter方法
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
	 * 属性婚姻状况(1-未婚；2-已婚；3-离婚；4-丧偶；9-未说明的婚姻状况)的getter方法
	 */

	@Column(name = "MARRIAGESTATUS")
	public String getMarriageStatus() {
		return this.marriageStatus;
	}

	/**
	 * 属性婚姻状况(1-未婚；2-已婚；3-离婚；4-丧偶；9-未说明的婚姻状况)的setter方法
	 */
	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	/**
	 * 属性区域代码的getter方法
	 */

	@Column(name = "AREACODE")
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 属性区域代码的setter方法
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * 属性收入状况的getter方法
	 */

	@Column(name = "INCOME")
	public String getIncome() {
		return this.income;
	}

	/**
	 * 属性收入状况的setter方法
	 */
	public void setIncome(String income) {
		this.income = income;
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
	 * 属性省份的getter方法
	 */

	@Column(name = "PROVINCES")
	public String getProvinces() {
		return this.provinces;
	}

	/**
	 * 属性省份的setter方法
	 */
	public void setProvinces(String provinces) {
		this.provinces = provinces;
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
	 * 属性区的getter方法
	 */

	@Column(name = "AREA")
	public String getArea() {
		return this.area;
	}

	/**
	 * 属性区的setter方法
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * 属性用户来源 (1-集团；2-寿险；3-财险；4-养老险；9-其他)的getter方法
	 */

	@Column(name = "USERSOURCE")
	public String getUserSource() {
		return this.userSource;
	}

	/**
	 * 属性用户来源 (1-集团；2-寿险；3-财险；4-养老险；9-其他)的setter方法
	 */
	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	/**
	 * 属性健康状况(1-优秀；2-良好；3-差)的getter方法
	 */

	@Column(name = "HEALTH")
	public String getHealth() {
		return this.health;
	}

	/**
	 * 属性健康状况(1-优秀；2-良好；3-差)的setter方法
	 */
	public void setHealth(String health) {
		this.health = health;
	}

	/**
	 * 属性客户状态(0-无效；1-有效；2-未开通)的getter方法
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * 属性客户状态(0-无效；1-有效；2-未开通)的setter方法
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 属性激活码的getter方法
	 */

	@Column(name = "ACTIVECODE")
	public String getActiveCode() {
		return this.activeCode;
	}

	/**
	 * 属性激活码的setter方法
	 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
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
	 * 属性审核状态(0-未验真,1-邮箱验真,2-手机验真)的getter方法
	 */

	@Column(name = "CHECKSTATUS")
	public String getCheckStatus() {
		return this.checkStatus;
	}

	/**
	 * 属性审核状态(0-未验真,1-邮箱验真,2-手机验真)的setter方法
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * 属性积分的getter方法
	 */

	@Column(name = "INTEGRAL")
	public BigDecimal getIntegral() {
		return this.integral;
	}

	/**
	 * 属性积分的setter方法
	 */
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	/**
	 * 属性出厂日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MAKEDATE")
	public Date getMakeDate() {
		return this.makeDate;
	}

	/**
	 * 属性出厂日期的setter方法
	 */
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	/**
	 * 属性寿险用户等级的getter方法
	 */

	@Column(name = "USERRANK")
	public String getUserRank() {
		return this.userRank;
	}

	/**
	 * 属性寿险用户等级的setter方法
	 */
	public void setUserRank(String userRank) {
		this.userRank = userRank;
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

	/**
	 * 属性RA认证状态（0:未认证,1:已认证）的setter方法
	 */
	@Column(name = "RAIND")
	public String getRaInd() {
		return raInd;
	}

	public void setRaInd(String raInd) {
		this.raInd = raInd;
	}
	/**
	 * 属性登录时间的getter方法
	 */
	@Column(name = "LOGINTIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 属性登录时间的setter方法
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 属性登录次数的getter方法
	 */
	@Column(name = "LOGINNUM")
	public long getLoginNum() {
		return loginNum;
	}
	/**
	 * 属性登录次数的setter方法
	 */
	public void setLoginNum(long loginNum) {
		this.loginNum = loginNum;
	}

	/**
	 * 属性邮箱验真状态(0-未验真,1-已验真)的getter方法
	 */
	@Column(name = "CHECKMAIL")
	public String getCheckMail() {
		return checkMail;
	}
	/**
	 * 属性邮箱验真状态(0-未验真,1-已验真)的setter方法
	 */
	public void setCheckMail(String checkMail) {
		this.checkMail = checkMail;
	}
	
	/**
	 * 属性手机验真状态(0-未验真,1-已验真)的getter方法
	 */
	@Column(name = "CHECKMOBILE")
	public String getCheckMobile() {
		return checkMobile;
	}
	/**
	 * 属性手机验真状态(0-未验真,1-已验真)的setter方法
	 */
	public void setCheckMobile(String checkMobile) {
		this.checkMobile = checkMobile;
	}

	/**
	 * 临时属性操作结果集的getter方法
	 */
	@Transient
	public List getResults() {
		return results;
	}

	/**
	 * 临时属性操作结果集的setter方法
	 */
	public void setResults(List results) {
		this.results = results;
	}

	@Type(type="yes_no")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "identifyEffectiveDate")
	public Date getIdentifyEffectiveDate() {
		return identifyEffectiveDate;
	}

	public void setIdentifyEffectiveDate(Date identifyEffectiveDate) {
		this.identifyEffectiveDate = identifyEffectiveDate;
	}

	@Column(name = "alias")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "hobby")
	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "bloodType")
	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "uniqueId")
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userPersonal")
	public Set<TopInsured> getTopInsureds() {
		return topInsureds;
	}

	public void setTopInsureds(Set<TopInsured> topInsureds) {
		this.topInsureds = topInsureds;
	}
	
	public void addTopInsured(TopInsured topInsured) {
		this.topInsureds.add(topInsured);
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCurrentLogintime() {
		return currentLogintime;
	}

	public void setCurrentLogintime(Date currentLogintime) {
		this.currentLogintime = currentLogintime;
	}

	@Column(name = "passwordGrade")
	public String getPasswordGrade() {
		return passwordGrade;
	}

	public void setPasswordGrade(String passwordGrade) {
		this.passwordGrade = passwordGrade;
	}
	
	@Column(name = "SYNCFLAG")
	public String getSyncFlag() {
		return syncFlag;
	}

	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
	
	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(length = 4)
	public Integer getLoginFailedCount() {
		return loginFailedCount;
	}

	public void setLoginFailedCount(Integer loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}

	@Type(type="yes_no")	
	public boolean isLockUserAccount() {
		return lockUserAccount;
	}

	public void setLockUserAccount(boolean lockUserAccount) {
		this.lockUserAccount = lockUserAccount;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
}
