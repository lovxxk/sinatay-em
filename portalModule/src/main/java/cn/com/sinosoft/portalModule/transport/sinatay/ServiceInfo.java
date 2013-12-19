package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.Serializable;

/**
 * ServiceInfo�ڵ�DTO
 *
 */
public class ServiceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ������Ϣid */
	private String id;
	
	/** �������� �������߻ط����� */
	private String type;
	
	/** Ͷ�������� */
	private String appName;

	/** ���� */
	private String prem;

	/** ���������� */
	private String insName;
	
	/** ������֤������ */
	private String insIdNumber;
	
	/** �������Ա� */
	private String insGender;
	
	/** �����˳������� */
	private String insBirthDate;
	
	/** ������� */
	private String serviceCom;
	
	/** Ͷ�������� */
	private String proposalContNo;
	
	/** Ͷ����֤������ */
	private String appIdNumber;
	
	/** Ͷ���˳������� */
	private String appBirthDate;
	
	/** Ͷ�����Ա� */
	private String appGender;
	
	/** Ͷ���˵�ַ */
	private String appAddress;
	
	/** �������ƣ����գ� */
	private String mainRiskName;
	
	/** Ͷ�����ʱ� */
	private String appZipCode;
	
	/** ������ */
	private String subRisk;
	
	/** Ͷ�����ֻ����� */
	private String appMobilePhoneNumber;
	
	/** ���� */
	private String insuredAmount;
	
	/** �ɷ�Ƶ�� */
	private String payIntv;
	
	/** �ɷ����� */
	private String payEndYear;
	
	/** �����ڼ� */
	private String years;
	
	/** ע���û����� */
	private String userName;
	
	/** ע���û��Ա� */
	private String userGender;
	
	/** ע���û����� */
	private String userEmail;
	
	/** ע���û�֤������ */
	private String userIdNumber;
	
	/** ע���û��ֻ����� */
	private String userMobilePhoneNumber;
	
	/** �Ƽ������� */
	private String recommendName;
	
	/** �Ƽ����ֻ����� */
	private String recommendMobilePhoneNumber;
	
	/** ������ְҵ */
	private String insJobCode;
	
	/** Ͷ����ְҵ */
	private String appJobCode;

	/**
	 * ���� id �� getter ����
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * ���� id �� setter ����
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ���� type �� getter ����
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * ���� type �� setter ����
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * ���� appName �� getter ����
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * ���� appName �� setter ����
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * ���� prem �� getter ����
	 * @return the prem
	 */
	public String getPrem() {
		return prem;
	}

	/**
	 * ���� prem �� setter ����
	 * @param prem the prem to set
	 */
	public void setPrem(String prem) {
		this.prem = prem;
	}

	/**
	 * ���� insName �� getter ����
	 * @return the insName
	 */
	public String getInsName() {
		return insName;
	}

	/**
	 * ���� insName �� setter ����
	 * @param insName the insName to set
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}

	/**
	 * ���� insIdNumber �� getter ����
	 * @return the insIdNumber
	 */
	public String getInsIdNumber() {
		return insIdNumber;
	}

	/**
	 * ���� insIdNumber �� setter ����
	 * @param insIdNumber the insIdNumber to set
	 */
	public void setInsIdNumber(String insIdNumber) {
		this.insIdNumber = insIdNumber;
	}

	/**
	 * ���� insGender �� getter ����
	 * @return the insGender
	 */
	public String getInsGender() {
		return insGender;
	}

	/**
	 * ���� insGender �� setter ����
	 * @param insGender the insGender to set
	 */
	public void setInsGender(String insGender) {
		this.insGender = insGender;
	}

	/**
	 * ���� insBirthDate �� getter ����
	 * @return the insBirthDate
	 */
	public String getInsBirthDate() {
		return insBirthDate;
	}

	/**
	 * ���� insBirthDate �� setter ����
	 * @param insBirthDate the insBirthDate to set
	 */
	public void setInsBirthDate(String insBirthDate) {
		this.insBirthDate = insBirthDate;
	}

	/**
	 * ���� serviceCom �� getter ����
	 * @return the serviceCom
	 */
	public String getServiceCom() {
		return serviceCom;
	}

	/**
	 * ���� serviceCom �� setter ����
	 * @param serviceCom the serviceCom to set
	 */
	public void setServiceCom(String serviceCom) {
		this.serviceCom = serviceCom;
	}

	/**
	 * ���� proposalContNo �� getter ����
	 * @return the proposalContNo
	 */
	public String getProposalContNo() {
		return proposalContNo;
	}

	/**
	 * ���� proposalContNo �� setter ����
	 * @param proposalContNo the proposalContNo to set
	 */
	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	/**
	 * ���� appIdNumber �� getter ����
	 * @return the appIdNumber
	 */
	public String getAppIdNumber() {
		return appIdNumber;
	}

	/**
	 * ���� appIdNumber �� setter ����
	 * @param appIdNumber the appIdNumber to set
	 */
	public void setAppIdNumber(String appIdNumber) {
		this.appIdNumber = appIdNumber;
	}

	/**
	 * ���� appBirthDate �� getter ����
	 * @return the appBirthDate
	 */
	public String getAppBirthDate() {
		return appBirthDate;
	}

	/**
	 * ���� appBirthDate �� setter ����
	 * @param appBirthDate the appBirthDate to set
	 */
	public void setAppBirthDate(String appBirthDate) {
		this.appBirthDate = appBirthDate;
	}

	/**
	 * ���� appGender �� getter ����
	 * @return the appGender
	 */
	public String getAppGender() {
		return appGender;
	}

	/**
	 * ���� appGender �� setter ����
	 * @param appGender the appGender to set
	 */
	public void setAppGender(String appGender) {
		this.appGender = appGender;
	}

	/**
	 * ���� appAddress �� getter ����
	 * @return the appAddress
	 */
	public String getAppAddress() {
		return appAddress;
	}

	/**
	 * ���� appAddress �� setter ����
	 * @param appAddress the appAddress to set
	 */
	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	}

	/**
	 * ���� mainRiskName �� getter ����
	 * @return the mainRiskName
	 */
	public String getMainRiskName() {
		return mainRiskName;
	}

	/**
	 * ���� mainRiskName �� setter ����
	 * @param mainRiskName the mainRiskName to set
	 */
	public void setMainRiskName(String mainRiskName) {
		this.mainRiskName = mainRiskName;
	}

	/**
	 * ���� appZipCode �� getter ����
	 * @return the appZipCode
	 */
	public String getAppZipCode() {
		return appZipCode;
	}

	/**
	 * ���� appZipCode �� setter ����
	 * @param appZipCode the appZipCode to set
	 */
	public void setAppZipCode(String appZipCode) {
		this.appZipCode = appZipCode;
	}

	/**
	 * ���� subRisk �� getter ����
	 * @return the subRisk
	 */
	public String getSubRisk() {
		return subRisk;
	}

	/**
	 * ���� subRisk �� setter ����
	 * @param subRisk the subRisk to set
	 */
	public void setSubRisk(String subRisk) {
		this.subRisk = subRisk;
	}

	/**
	 * ���� appMobilePhoneNumber �� getter ����
	 * @return the appMobilePhoneNumber
	 */
	public String getAppMobilePhoneNumber() {
		return appMobilePhoneNumber;
	}

	/**
	 * ���� appMobilePhoneNumber �� setter ����
	 * @param appMobilePhoneNumber the appMobilePhoneNumber to set
	 */
	public void setAppMobilePhoneNumber(String appMobilePhoneNumber) {
		this.appMobilePhoneNumber = appMobilePhoneNumber;
	}

	/**
	 * ���� insuredAmount �� getter ����
	 * @return the insuredAmount
	 */
	public String getInsuredAmount() {
		return insuredAmount;
	}

	/**
	 * ���� insuredAmount �� setter ����
	 * @param insuredAmount the insuredAmount to set
	 */
	public void setInsuredAmount(String insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	/**
	 * ���� payIntv �� getter ����
	 * @return the payIntv
	 */
	public String getPayIntv() {
		return payIntv;
	}

	/**
	 * ���� payIntv �� setter ����
	 * @param payIntv the payIntv to set
	 */
	public void setPayIntv(String payIntv) {
		this.payIntv = payIntv;
	}

	/**
	 * ���� payEndYear �� getter ����
	 * @return the payEndYear
	 */
	public String getPayEndYear() {
		return payEndYear;
	}

	/**
	 * ���� payEndYear �� setter ����
	 * @param payEndYear the payEndYear to set
	 */
	public void setPayEndYear(String payEndYear) {
		this.payEndYear = payEndYear;
	}

	/**
	 * ���� years �� getter ����
	 * @return the years
	 */
	public String getYears() {
		return years;
	}

	/**
	 * ���� years �� setter ����
	 * @param years the years to set
	 */
	public void setYears(String years) {
		this.years = years;
	}

	/**
	 * ���� userName �� getter ����
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ���� userName �� setter ����
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ���� userGender �� getter ����
	 * @return the userGender
	 */
	public String getUserGender() {
		return userGender;
	}

	/**
	 * ���� userGender �� setter ����
	 * @param userGender the userGender to set
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/**
	 * ���� userEmail �� getter ����
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * ���� userEmail �� setter ����
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * ���� userIdNumber �� getter ����
	 * @return the userIdNumber
	 */
	public String getUserIdNumber() {
		return userIdNumber;
	}

	/**
	 * ���� userIdNumber �� setter ����
	 * @param userIdNumber the userIdNumber to set
	 */
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}

	/**
	 * ���� userMobilePhoneNumber �� getter ����
	 * @return the userMobilePhoneNumber
	 */
	public String getUserMobilePhoneNumber() {
		return userMobilePhoneNumber;
	}

	/**
	 * ���� userMobilePhoneNumber �� setter ����
	 * @param userMobilePhoneNumber the userMobilePhoneNumber to set
	 */
	public void setUserMobilePhoneNumber(String userMobilePhoneNumber) {
		this.userMobilePhoneNumber = userMobilePhoneNumber;
	}

	/**
	 * ���� recommendName �� getter ����
	 * @return the recommendName
	 */
	public String getRecommendName() {
		return recommendName;
	}

	/**
	 * ���� recommendName �� setter ����
	 * @param recommendName the recommendName to set
	 */
	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	/**
	 * ���� recommendMobilePhoneNumber �� getter ����
	 * @return the recommendMobilePhoneNumber
	 */
	public String getRecommendMobilePhoneNumber() {
		return recommendMobilePhoneNumber;
	}

	/**
	 * ���� recommendMobilePhoneNumber �� setter ����
	 * @param recommendMobilePhoneNumber the recommendMobilePhoneNumber to set
	 */
	public void setRecommendMobilePhoneNumber(String recommendMobilePhoneNumber) {
		this.recommendMobilePhoneNumber = recommendMobilePhoneNumber;
	}

	/**
	 * ���� insJobCode �� getter ����
	 * @return the insJobCode
	 */
	public String getInsJobCode() {
		return insJobCode;
	}

	/**
	 * ���� insJobCode �� setter ����
	 * @param insJobCode the insJobCode to set
	 */
	public void setInsJobCode(String insJobCode) {
		this.insJobCode = insJobCode;
	}

	/**
	 * ���� appJobCode �� getter ����
	 * @return the appJobCode
	 */
	public String getAppJobCode() {
		return appJobCode;
	}

	/**
	 * ���� appJobCode �� setter ����
	 * @param appJobCode the appJobCode to set
	 */
	public void setAppJobCode(String appJobCode) {
		this.appJobCode = appJobCode;
	}
	
}
