package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.Serializable;

/**
 * ServiceInfo节点DTO
 *
 */
public class ServiceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 服务信息id */
	private String id;
	
	/** 服务类型 增加在线回访任务 */
	private String type;
	
	/** 投保人姓名 */
	private String appName;

	/** 保费 */
	private String prem;

	/** 被保人姓名 */
	private String insName;
	
	/** 被保人证件号码 */
	private String insIdNumber;
	
	/** 被保人性别 */
	private String insGender;
	
	/** 被保人出生日期 */
	private String insBirthDate;
	
	/** 服务机构 */
	private String serviceCom;
	
	/** 投保单号码 */
	private String proposalContNo;
	
	/** 投保人证件号码 */
	private String appIdNumber;
	
	/** 投保人出生日期 */
	private String appBirthDate;
	
	/** 投保人性别 */
	private String appGender;
	
	/** 投保人地址 */
	private String appAddress;
	
	/** 险种名称（主险） */
	private String mainRiskName;
	
	/** 投保人邮编 */
	private String appZipCode;
	
	/** 附加险 */
	private String subRisk;
	
	/** 投保人手机号码 */
	private String appMobilePhoneNumber;
	
	/** 保额 */
	private String insuredAmount;
	
	/** 缴费频率 */
	private String payIntv;
	
	/** 缴费年期 */
	private String payEndYear;
	
	/** 保险期间 */
	private String years;
	
	/** 注册用户姓名 */
	private String userName;
	
	/** 注册用户性别 */
	private String userGender;
	
	/** 注册用户邮箱 */
	private String userEmail;
	
	/** 注册用户证件号码 */
	private String userIdNumber;
	
	/** 注册用户手机号码 */
	private String userMobilePhoneNumber;
	
	/** 推荐人姓名 */
	private String recommendName;
	
	/** 推荐人手机号码 */
	private String recommendMobilePhoneNumber;
	
	/** 被保人职业 */
	private String insJobCode;
	
	/** 投保人职业 */
	private String appJobCode;

	/**
	 * 属性 id 的 getter 方法
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 属性 id 的 setter 方法
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 属性 type 的 getter 方法
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 属性 type 的 setter 方法
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 属性 appName 的 getter 方法
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * 属性 appName 的 setter 方法
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * 属性 prem 的 getter 方法
	 * @return the prem
	 */
	public String getPrem() {
		return prem;
	}

	/**
	 * 属性 prem 的 setter 方法
	 * @param prem the prem to set
	 */
	public void setPrem(String prem) {
		this.prem = prem;
	}

	/**
	 * 属性 insName 的 getter 方法
	 * @return the insName
	 */
	public String getInsName() {
		return insName;
	}

	/**
	 * 属性 insName 的 setter 方法
	 * @param insName the insName to set
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}

	/**
	 * 属性 insIdNumber 的 getter 方法
	 * @return the insIdNumber
	 */
	public String getInsIdNumber() {
		return insIdNumber;
	}

	/**
	 * 属性 insIdNumber 的 setter 方法
	 * @param insIdNumber the insIdNumber to set
	 */
	public void setInsIdNumber(String insIdNumber) {
		this.insIdNumber = insIdNumber;
	}

	/**
	 * 属性 insGender 的 getter 方法
	 * @return the insGender
	 */
	public String getInsGender() {
		return insGender;
	}

	/**
	 * 属性 insGender 的 setter 方法
	 * @param insGender the insGender to set
	 */
	public void setInsGender(String insGender) {
		this.insGender = insGender;
	}

	/**
	 * 属性 insBirthDate 的 getter 方法
	 * @return the insBirthDate
	 */
	public String getInsBirthDate() {
		return insBirthDate;
	}

	/**
	 * 属性 insBirthDate 的 setter 方法
	 * @param insBirthDate the insBirthDate to set
	 */
	public void setInsBirthDate(String insBirthDate) {
		this.insBirthDate = insBirthDate;
	}

	/**
	 * 属性 serviceCom 的 getter 方法
	 * @return the serviceCom
	 */
	public String getServiceCom() {
		return serviceCom;
	}

	/**
	 * 属性 serviceCom 的 setter 方法
	 * @param serviceCom the serviceCom to set
	 */
	public void setServiceCom(String serviceCom) {
		this.serviceCom = serviceCom;
	}

	/**
	 * 属性 proposalContNo 的 getter 方法
	 * @return the proposalContNo
	 */
	public String getProposalContNo() {
		return proposalContNo;
	}

	/**
	 * 属性 proposalContNo 的 setter 方法
	 * @param proposalContNo the proposalContNo to set
	 */
	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	/**
	 * 属性 appIdNumber 的 getter 方法
	 * @return the appIdNumber
	 */
	public String getAppIdNumber() {
		return appIdNumber;
	}

	/**
	 * 属性 appIdNumber 的 setter 方法
	 * @param appIdNumber the appIdNumber to set
	 */
	public void setAppIdNumber(String appIdNumber) {
		this.appIdNumber = appIdNumber;
	}

	/**
	 * 属性 appBirthDate 的 getter 方法
	 * @return the appBirthDate
	 */
	public String getAppBirthDate() {
		return appBirthDate;
	}

	/**
	 * 属性 appBirthDate 的 setter 方法
	 * @param appBirthDate the appBirthDate to set
	 */
	public void setAppBirthDate(String appBirthDate) {
		this.appBirthDate = appBirthDate;
	}

	/**
	 * 属性 appGender 的 getter 方法
	 * @return the appGender
	 */
	public String getAppGender() {
		return appGender;
	}

	/**
	 * 属性 appGender 的 setter 方法
	 * @param appGender the appGender to set
	 */
	public void setAppGender(String appGender) {
		this.appGender = appGender;
	}

	/**
	 * 属性 appAddress 的 getter 方法
	 * @return the appAddress
	 */
	public String getAppAddress() {
		return appAddress;
	}

	/**
	 * 属性 appAddress 的 setter 方法
	 * @param appAddress the appAddress to set
	 */
	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	}

	/**
	 * 属性 mainRiskName 的 getter 方法
	 * @return the mainRiskName
	 */
	public String getMainRiskName() {
		return mainRiskName;
	}

	/**
	 * 属性 mainRiskName 的 setter 方法
	 * @param mainRiskName the mainRiskName to set
	 */
	public void setMainRiskName(String mainRiskName) {
		this.mainRiskName = mainRiskName;
	}

	/**
	 * 属性 appZipCode 的 getter 方法
	 * @return the appZipCode
	 */
	public String getAppZipCode() {
		return appZipCode;
	}

	/**
	 * 属性 appZipCode 的 setter 方法
	 * @param appZipCode the appZipCode to set
	 */
	public void setAppZipCode(String appZipCode) {
		this.appZipCode = appZipCode;
	}

	/**
	 * 属性 subRisk 的 getter 方法
	 * @return the subRisk
	 */
	public String getSubRisk() {
		return subRisk;
	}

	/**
	 * 属性 subRisk 的 setter 方法
	 * @param subRisk the subRisk to set
	 */
	public void setSubRisk(String subRisk) {
		this.subRisk = subRisk;
	}

	/**
	 * 属性 appMobilePhoneNumber 的 getter 方法
	 * @return the appMobilePhoneNumber
	 */
	public String getAppMobilePhoneNumber() {
		return appMobilePhoneNumber;
	}

	/**
	 * 属性 appMobilePhoneNumber 的 setter 方法
	 * @param appMobilePhoneNumber the appMobilePhoneNumber to set
	 */
	public void setAppMobilePhoneNumber(String appMobilePhoneNumber) {
		this.appMobilePhoneNumber = appMobilePhoneNumber;
	}

	/**
	 * 属性 insuredAmount 的 getter 方法
	 * @return the insuredAmount
	 */
	public String getInsuredAmount() {
		return insuredAmount;
	}

	/**
	 * 属性 insuredAmount 的 setter 方法
	 * @param insuredAmount the insuredAmount to set
	 */
	public void setInsuredAmount(String insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	/**
	 * 属性 payIntv 的 getter 方法
	 * @return the payIntv
	 */
	public String getPayIntv() {
		return payIntv;
	}

	/**
	 * 属性 payIntv 的 setter 方法
	 * @param payIntv the payIntv to set
	 */
	public void setPayIntv(String payIntv) {
		this.payIntv = payIntv;
	}

	/**
	 * 属性 payEndYear 的 getter 方法
	 * @return the payEndYear
	 */
	public String getPayEndYear() {
		return payEndYear;
	}

	/**
	 * 属性 payEndYear 的 setter 方法
	 * @param payEndYear the payEndYear to set
	 */
	public void setPayEndYear(String payEndYear) {
		this.payEndYear = payEndYear;
	}

	/**
	 * 属性 years 的 getter 方法
	 * @return the years
	 */
	public String getYears() {
		return years;
	}

	/**
	 * 属性 years 的 setter 方法
	 * @param years the years to set
	 */
	public void setYears(String years) {
		this.years = years;
	}

	/**
	 * 属性 userName 的 getter 方法
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 属性 userName 的 setter 方法
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 属性 userGender 的 getter 方法
	 * @return the userGender
	 */
	public String getUserGender() {
		return userGender;
	}

	/**
	 * 属性 userGender 的 setter 方法
	 * @param userGender the userGender to set
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/**
	 * 属性 userEmail 的 getter 方法
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 属性 userEmail 的 setter 方法
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * 属性 userIdNumber 的 getter 方法
	 * @return the userIdNumber
	 */
	public String getUserIdNumber() {
		return userIdNumber;
	}

	/**
	 * 属性 userIdNumber 的 setter 方法
	 * @param userIdNumber the userIdNumber to set
	 */
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}

	/**
	 * 属性 userMobilePhoneNumber 的 getter 方法
	 * @return the userMobilePhoneNumber
	 */
	public String getUserMobilePhoneNumber() {
		return userMobilePhoneNumber;
	}

	/**
	 * 属性 userMobilePhoneNumber 的 setter 方法
	 * @param userMobilePhoneNumber the userMobilePhoneNumber to set
	 */
	public void setUserMobilePhoneNumber(String userMobilePhoneNumber) {
		this.userMobilePhoneNumber = userMobilePhoneNumber;
	}

	/**
	 * 属性 recommendName 的 getter 方法
	 * @return the recommendName
	 */
	public String getRecommendName() {
		return recommendName;
	}

	/**
	 * 属性 recommendName 的 setter 方法
	 * @param recommendName the recommendName to set
	 */
	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	/**
	 * 属性 recommendMobilePhoneNumber 的 getter 方法
	 * @return the recommendMobilePhoneNumber
	 */
	public String getRecommendMobilePhoneNumber() {
		return recommendMobilePhoneNumber;
	}

	/**
	 * 属性 recommendMobilePhoneNumber 的 setter 方法
	 * @param recommendMobilePhoneNumber the recommendMobilePhoneNumber to set
	 */
	public void setRecommendMobilePhoneNumber(String recommendMobilePhoneNumber) {
		this.recommendMobilePhoneNumber = recommendMobilePhoneNumber;
	}

	/**
	 * 属性 insJobCode 的 getter 方法
	 * @return the insJobCode
	 */
	public String getInsJobCode() {
		return insJobCode;
	}

	/**
	 * 属性 insJobCode 的 setter 方法
	 * @param insJobCode the insJobCode to set
	 */
	public void setInsJobCode(String insJobCode) {
		this.insJobCode = insJobCode;
	}

	/**
	 * 属性 appJobCode 的 getter 方法
	 * @return the appJobCode
	 */
	public String getAppJobCode() {
		return appJobCode;
	}

	/**
	 * 属性 appJobCode 的 setter 方法
	 * @param appJobCode the appJobCode to set
	 */
	public void setAppJobCode(String appJobCode) {
		this.appJobCode = appJobCode;
	}
	
}
