package cn.com.sinosoft.ebusiness.service.user.personal.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeUserPersonal
 */
@Entity
@Table(name = "GE_USER_PERSONAL")
public class GeUserPersonal implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** �����û��� */
	private String userID;

	/** �����û��ȼ� */
	private String userLevel;

	/** �����û��ʺ� */
	private String userAccount;

	/** �������� */
	private String userName;

	/** ���Ժ׿��� */
	private String piCardNo;

	/** ���Ե�¼����(������) */
	private String pwd;

	/** ��������(����) */
	private String pwd2;

	/** ���Ե������� */
	private String email;

	/** �����ƶ��绰 */
	private String mobilePhone;

	/** ����֤������ */
	private String identifyType;
	
	/**����֤����Ч��*/
	private Date identifyEffectiveDate;

	/** ����֤������ */
	private String identifyNumber;

	/** �����Ա� (0-�У�1-Ů)*/
	private String sex;

	/** ���Գ������� */
	private Date birthday;

	/** ���Ի���״��(1-δ�飻2-�ѻ飻3-��飻4-ɥż��9-δ˵���Ļ���״��) */
	private String marriageStatus;

	/** ���������������� */
	private String areaCode;

	/** ����������ҵ */
	private String industry;

	/** ��������״�� */
	private String income;

	/** ���Լ�ͥ�绰 */
	private String homePhone;

	/** ���԰칫�绰 */
	private String officePhone;

	/** ����ʡ�� */
	private String provinces;

	/** ���Գ��� */
	private String city;

	/** ������ */
	private String area;

	/** ������ϵ��ַ */
	private String contactAddress;

	/** ������������ */
	private String zipCode;

	/** �����û���Դ (1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)*/
	private String userSource;

	/** ���Խ���״��(1-���㣻2-���ã�3-��) */
	private String health;

	/** ���Կͻ�״̬(0-��Ч��1-��Ч��2-δ��ͨ) */
	private String status;

	/** ���Լ����� */
	private String activeCode;

	/** ����֤��ʶ���� */
	private String ukey;

	/** �������״̬(0-δ����,1-��������,2-�ֻ�����) */
	private String checkStatus;

	/** ���Ի��� */
	private BigDecimal integral = new BigDecimal("0");

	/** ���Դ������� */
	private Date makeDate = new Date();
	
	/** ���������û��ȼ���T��û�б����ģ�Ҳ����ע���û���E�����Ϲ���ģ�Ҳ�������ɽ��ͻ���  C�������������ĳɽ��ͻ��������Ϲ���ģ���Ҳ��ע���û��� */
	private String userRank;

	/** ���Ա�ʶλ */
	private String flag;
	
	/** ����RA��֤״̬��0:δ��֤,1:����֤�� */
	private String raInd;

	/** ���Ե�¼ʱ�� */
	private Date loginTime;
	
	/** ���Ե�¼���� */
	private long loginNum = 0L;
	
	/** ������������״̬(0-δ����,1-������) */
	private String checkMail;
	
	/** �����ֻ�����״̬(0-δ����,1-������) */
	private String checkMobile;
	
	/** ��������� */
	@Transient
	private List results;
	
	/** ��ǰ�����Ƿ���Ч*/
	private boolean active;
	
	/**�ǳ�*/
	private String alias;
	
	/**����*/
	private String hobby;
	
	/**Ѫ��*/
	private String bloodType;
	
	/**QQ��֧����������΢�����ص��û�ΨһId*/
	private String uniqueId;
	
	private Set<TopInsured> topInsureds = new HashSet<TopInsured>(0);
	
	/**�û��ϴε�¼ʱ��*/
	private Date lastLoginTime;
	
	/**�û��˴ε�¼ʱ��*/
	private Date currentLogintime;
	
	/**����ǿ�ȱ�ʶ --��/��/��*/
	private String passwordGrade;
	
	/**����ͬ����ʾ--Yͬ����N��ͬ��*/
	private String syncFlag = "Y";
	
	/**��Դ*/
	private String source;
	
	/**�û�������������������������֮�������ʺ�*/
	public Integer loginFailedCount;
	
	/**��ǰ�ʺ��Ƿ��ѱ�����*/
	public boolean lockUserAccount = false;
	
	/**�ʺ�����ʱ��*/
	public Date lockTime;
	
	/**
	 * ��GeUserPersonal��Ĭ�Ϲ��췽��
	 */
	public GeUserPersonal() {
	}

	/**
	 * �����û��ŵ�getter����
	 */
	@Id
	@Column(name = "USERID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
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
	 * �����û��ȼ���getter����
	 */

	@Column(name = "USERLEVEL")
	public String getUserLevel() {
		return this.userLevel;
	}

	/**
	 * �����û��ȼ���setter����
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * �����û��ʺŵ�getter����
	 */

	@Column(name = "USERACCOUNT")
	public String getUserAccount() {
		return this.userAccount;
	}

	/**
	 * �����û��ʺŵ�setter����
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * ����������getter����
	 */

	@Column(name = "USERNAME")
	public String getUserName() {
		return this.userName;
	}

	/**
	 * ����������setter����
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ���Ժ׿��ŵ�getter����
	 */

	@Column(name = "PICARDNO")
	public String getPiCardNo() {
		return this.piCardNo;
	}

	/**
	 * ���Ժ׿��ŵ�setter����
	 */
	public void setPiCardNo(String piCardNo) {
		this.piCardNo = piCardNo;
	}

	/**
	 * ���Ե�¼����(������)��getter����
	 */

	@Column(name = "PWD")
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * ���Ե�¼����(������)��setter����
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * ��������(����)��getter����
	 */

	@Column(name = "PWD2")
	public String getPwd2() {
		return this.pwd2;
	}

	/**
	 * ��������(����)��setter����
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
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
	 * �����ƶ��绰��getter����
	 */
	@Column(name = "MOBILEPHONE")
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * �����ƶ��绰��setter����
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
	 * �����Ա� (0-�У�1-Ů)��getter����
	 */

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/**
	 * �����Ա� (0-�У�1-Ů)��setter����
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
	 * ���Ի���״��(1-δ�飻2-�ѻ飻3-��飻4-ɥż��9-δ˵���Ļ���״��)��getter����
	 */

	@Column(name = "MARRIAGESTATUS")
	public String getMarriageStatus() {
		return this.marriageStatus;
	}

	/**
	 * ���Ի���״��(1-δ�飻2-�ѻ飻3-��飻4-ɥż��9-δ˵���Ļ���״��)��setter����
	 */
	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	/**
	 * ������������getter����
	 */

	@Column(name = "AREACODE")
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * ������������setter����
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * ��������״����getter����
	 */

	@Column(name = "INCOME")
	public String getIncome() {
		return this.income;
	}

	/**
	 * ��������״����setter����
	 */
	public void setIncome(String income) {
		this.income = income;
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
	 * ����ʡ�ݵ�getter����
	 */

	@Column(name = "PROVINCES")
	public String getProvinces() {
		return this.provinces;
	}

	/**
	 * ����ʡ�ݵ�setter����
	 */
	public void setProvinces(String provinces) {
		this.provinces = provinces;
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
	 * ��������getter����
	 */

	@Column(name = "AREA")
	public String getArea() {
		return this.area;
	}

	/**
	 * ��������setter����
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * �����û���Դ (1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)��getter����
	 */

	@Column(name = "USERSOURCE")
	public String getUserSource() {
		return this.userSource;
	}

	/**
	 * �����û���Դ (1-���ţ�2-���գ�3-���գ�4-�����գ�9-����)��setter����
	 */
	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	/**
	 * ���Խ���״��(1-���㣻2-���ã�3-��)��getter����
	 */

	@Column(name = "HEALTH")
	public String getHealth() {
		return this.health;
	}

	/**
	 * ���Խ���״��(1-���㣻2-���ã�3-��)��setter����
	 */
	public void setHealth(String health) {
		this.health = health;
	}

	/**
	 * ���Կͻ�״̬(0-��Ч��1-��Ч��2-δ��ͨ)��getter����
	 */

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	/**
	 * ���Կͻ�״̬(0-��Ч��1-��Ч��2-δ��ͨ)��setter����
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ���Լ������getter����
	 */

	@Column(name = "ACTIVECODE")
	public String getActiveCode() {
		return this.activeCode;
	}

	/**
	 * ���Լ������setter����
	 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
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
	 * �������״̬(0-δ����,1-��������,2-�ֻ�����)��getter����
	 */

	@Column(name = "CHECKSTATUS")
	public String getCheckStatus() {
		return this.checkStatus;
	}

	/**
	 * �������״̬(0-δ����,1-��������,2-�ֻ�����)��setter����
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * ���Ի��ֵ�getter����
	 */

	@Column(name = "INTEGRAL")
	public BigDecimal getIntegral() {
		return this.integral;
	}

	/**
	 * ���Ի��ֵ�setter����
	 */
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	/**
	 * ���Գ������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MAKEDATE")
	public Date getMakeDate() {
		return this.makeDate;
	}

	/**
	 * ���Գ������ڵ�setter����
	 */
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	/**
	 * ���������û��ȼ���getter����
	 */

	@Column(name = "USERRANK")
	public String getUserRank() {
		return this.userRank;
	}

	/**
	 * ���������û��ȼ���setter����
	 */
	public void setUserRank(String userRank) {
		this.userRank = userRank;
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

	/**
	 * ����RA��֤״̬��0:δ��֤,1:����֤����setter����
	 */
	@Column(name = "RAIND")
	public String getRaInd() {
		return raInd;
	}

	public void setRaInd(String raInd) {
		this.raInd = raInd;
	}
	/**
	 * ���Ե�¼ʱ���getter����
	 */
	@Column(name = "LOGINTIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * ���Ե�¼ʱ���setter����
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * ���Ե�¼������getter����
	 */
	@Column(name = "LOGINNUM")
	public long getLoginNum() {
		return loginNum;
	}
	/**
	 * ���Ե�¼������setter����
	 */
	public void setLoginNum(long loginNum) {
		this.loginNum = loginNum;
	}

	/**
	 * ������������״̬(0-δ����,1-������)��getter����
	 */
	@Column(name = "CHECKMAIL")
	public String getCheckMail() {
		return checkMail;
	}
	/**
	 * ������������״̬(0-δ����,1-������)��setter����
	 */
	public void setCheckMail(String checkMail) {
		this.checkMail = checkMail;
	}
	
	/**
	 * �����ֻ�����״̬(0-δ����,1-������)��getter����
	 */
	@Column(name = "CHECKMOBILE")
	public String getCheckMobile() {
		return checkMobile;
	}
	/**
	 * �����ֻ�����״̬(0-δ����,1-������)��setter����
	 */
	public void setCheckMobile(String checkMobile) {
		this.checkMobile = checkMobile;
	}

	/**
	 * ��ʱ���Բ����������getter����
	 */
	@Transient
	public List getResults() {
		return results;
	}

	/**
	 * ��ʱ���Բ����������setter����
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
