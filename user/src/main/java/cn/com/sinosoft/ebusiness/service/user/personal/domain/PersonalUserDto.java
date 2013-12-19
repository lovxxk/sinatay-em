package cn.com.sinosoft.ebusiness.service.user.personal.domain;

import java.util.Date;

public class PersonalUserDto {
 	
	/** 属性个人客户ID */
	private String userID;

	/** 属性用户等级 */
	private String userLevel;

	/** 属性用户帐号 */
	private String userAccount;

	/** 属性用户中文姓名 */
	private String userName;

	/** 属性鹤卡号 */
	private String piCardNo;

	/** 属性密码(不可逆) */
	private String pwd;

	/** 属性邮箱 */
	private String email;

	/** 属性手机号 */
	private String mobilePhone;

	/** 属性证件类型 */
	private String identifyType;

	/** 属性证件号码 */
	private String identifyNumber;

	/** 属性性别 1-男；2-女 */
	private String sex;

	/** 属性出生日期 */
	private Date birthday;

	/** 属性婚姻状况  1-未婚；2-已婚；3-离婚；4-丧偶；9-未说明的婚姻状况 */
	private String marriageStatus;

	/** 属性所在地区 */
	private String areaCode;

	/** 属性所在行业 */
	private String industry;

	/** 属性收入状况 */
	private String income;

	/** 属性家庭电话 */
	private String homePhone;

	/** 属性办公电话 */
	private String officePhone;

	/** 属性联系地址 */
	private String contactAddress;

	/** 属性邮编 */
	private String zipCode;

	/** 属性用户来源(1-集团；2-寿险；3-财险；4-养老险；9-其他) */
	private String userSource;

	/** 属性健康状况(1-优秀；2-良好；3-差) */
	private String health;

	/** 属性用户状态(0 无效 1 有效 2未开通) */
	private String status;

	/** 属性证书识别码 */
	private String ukey;
	
	/** 属性寿险用户等级（T：没有保单的，也即简单注册用户；E：网上购买的，也即网销成交客户；  C：有正常保单的成交客户（非网上购买的），也即注册用户） */
	private String userRank;
	
	/** 属性创建日期 */
	private Date makeDate;
	
	
	/** 属性登录时间 */
	private Date loginTime;
	
	/** 属性登录次数 */
	private long loginNum;
	
	
	/**  结果集  */
	private GeResultsDto geResults;
	
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public GeResultsDto getGeResults() {
		return geResults;
	}

	public void setGeResults(GeResultsDto geResults) {
		this.geResults = geResults;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPiCardNo() {
		return piCardNo;
	}

	public void setPiCardNo(String piCardNo) {
		this.piCardNo = piCardNo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUserRank() {
		return userRank;
	}

	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public long getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(long loginNum) {
		this.loginNum = loginNum;
	}
	
}
