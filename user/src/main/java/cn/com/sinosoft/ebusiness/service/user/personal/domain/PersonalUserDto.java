package cn.com.sinosoft.ebusiness.service.user.personal.domain;

import java.util.Date;

public class PersonalUserDto {
 	
	/** ���Ը��˿ͻ�ID */
	private String userID;

	/** �����û��ȼ� */
	private String userLevel;

	/** �����û��ʺ� */
	private String userAccount;

	/** �����û��������� */
	private String userName;

	/** ���Ժ׿��� */
	private String piCardNo;

	/** ��������(������) */
	private String pwd;

	/** �������� */
	private String email;

	/** �����ֻ��� */
	private String mobilePhone;

	/** ����֤������ */
	private String identifyType;

	/** ����֤������ */
	private String identifyNumber;

	/** �����Ա� 1-�У�2-Ů */
	private String sex;

	/** ���Գ������� */
	private Date birthday;

	/** ���Ի���״��  1-δ�飻2-�ѻ飻3-��飻4-ɥż��9-δ˵���Ļ���״�� */
	private String marriageStatus;

	/** �������ڵ��� */
	private String areaCode;

	/** ����������ҵ */
	private String industry;

	/** ��������״�� */
	private String income;

	/** ���Լ�ͥ�绰 */
	private String homePhone;

	/** ���԰칫�绰 */
	private String officePhone;

	/** ������ϵ��ַ */
	private String contactAddress;

	/** �����ʱ� */
	private String zipCode;

	/** �����û���Դ(1-���ţ�2-���գ�3-���գ�4-�����գ�9-����) */
	private String userSource;

	/** ���Խ���״��(1-���㣻2-���ã�3-��) */
	private String health;

	/** �����û�״̬(0 ��Ч 1 ��Ч 2δ��ͨ) */
	private String status;

	/** ����֤��ʶ���� */
	private String ukey;
	
	/** ���������û��ȼ���T��û�б����ģ�Ҳ����ע���û���E�����Ϲ���ģ�Ҳ�������ɽ��ͻ���  C�������������ĳɽ��ͻ��������Ϲ���ģ���Ҳ��ע���û��� */
	private String userRank;
	
	/** ���Դ������� */
	private Date makeDate;
	
	
	/** ���Ե�¼ʱ�� */
	private Date loginTime;
	
	/** ���Ե�¼���� */
	private long loginNum;
	
	
	/**  �����  */
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
