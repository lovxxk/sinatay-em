package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LCCont -> LCAppnt -> Risk�ڵ�DTO
 *
 */
public class Risk implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ���ִ��� */
	private String riskCode;
	
	/** �������ִ��� */
	private String mainRiskCode;
	
	/** �������� ���� */
	private String riskType;
	
	/*�����˻����� */
	private String riskAccType;
	
	private String riskDutyType;
	
	 /*�������ֺ���*/
	private String polNo;
	
	 /*����״̬*/
	private String state;
	
	/** �������� ���� */
	private String riskName;
	
	/** ���� */
	private BigDecimal amnt;
	
	/** ���� ���� */
	private BigDecimal rate;
	
	/** ��Ч���� ���� */
	private Date cvaliDate;
	
	/** ���� */
	private String rank;
	
	/** ���շ� */
	private BigDecimal prem;
	
	/** Ͷ������ */
	private Integer mult;
	
	/** �ɷѼ�� CD18 */
	private String payIntv;
	
	/** �ۿ��� ���� */
	private String costIntv;
	
	/** �ۿ�ʱ�� CD01 ���� */
	private String costDate;
	
	/** �����ڼ� */
	private String years;
	
	/** �ر�Լ�� */
	private String specContent;
	
	/** �ɷ����������־ CD19 */
	private String payEndYearFlag;
	
	/** �ɷ��������� */
	private String payEndYear;
	
	/** ��ȡ�������ڱ�־ CD19 */
	private String getYearFlag;
	
	/** ��ȡ���� */
	private String getYear;
	
	/** �������������־ CD19 */
	private String insuYearFlag;
	
	/** ������������ */
	private String insuYear;
	
	/** ��ȡ��ʽ ���� */
	private String getIntv;
	
	/** ��ȡ���б��� ���� */
	private String getBankCode;
	
	/** ��ȡ�����˻� ���� */
	private String getBankAccNo;
	
	/** ��ȡ���л��� ���� */
	private String getAccName;
	
	/** ��ȡ����ʡ���� ���� */
	private String getAccProvince;
	
	/** ��ȡ�����б��� ���� */
	private String getAccCity;
	
	/** ��ȡ���п������� ���� */
	private String getAccType;
	
	/** �潻��־ CD20 */
	private String autoPayFlag;
	
	/** ���������� CD21 */
	private String bonusPayMode;
	
	/** ������־ CD22 */
	private String subFlag;
	
	/** ������ȡ��ʽ CD23 */
	private String bonusGetMode;
	
	/** �Զ�������־ CD24 */
	private String autoRNewFlag;
	
	/** ������֮��־ ���� */
	private String healthFlag;
	
	/** ������ȡ����ȡ��ʽ ���� */
	private String fullBonusGetMode;
	
	/** ��ʼ������ ���� */
	private BigDecimal firstRate;
	
	/** ��֤���� ���� */
	private BigDecimal sureRate;
	
	/** �˻��� */
	private Integer accountCount;
	
	/** �˻� */
	private List<Account> accounts = new ArrayList<Account>(0);
	
	/** �������� */
	private Integer lcBnfCount;
	
	/** ������ */
	private List<LCBnf> lcBnfs = new ArrayList<LCBnf>(0);
	
	
	/**
	 * ���� riskCode �� getter ����
	 * @return the riskCode
	 */
	public String getRiskCode() {
		return riskCode;
	}

	/**
	 * ���� riskCode �� setter ����
	 * @param riskCode the riskCode to set
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * ���� mainRiskCode �� getter ����
	 * @return the mainRiskCode
	 */
	public String getMainRiskCode() {
		return mainRiskCode;
	}

	/**
	 * ���� mainRiskCode �� setter ����
	 * @param mainRiskCode the mainRiskCode to set
	 */
	public void setMainRiskCode(String mainRiskCode) {
		this.mainRiskCode = mainRiskCode;
	}

	/**
	 * ���� riskType �� getter ����
	 * @return the riskType
	 */
	public String getRiskType() {
		return riskType;
	}

	/**
	 * ���� riskType �� setter ����
	 * @param riskType the riskType to set
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskAccType() {
		return riskAccType;
	}

	public void setRiskAccType(String riskAccType) {
		this.riskAccType = riskAccType;
	}

	public String getRiskDutyType() {
		return riskDutyType;
	}

	public void setRiskDutyType(String riskDutyType) {
		this.riskDutyType = riskDutyType;
	}

	public String getPolNo() {
		return polNo;
	}

	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * ���� riskName �� getter ����
	 * @return the riskName
	 */
	public String getRiskName() {
		return riskName;
	}

	/**
	 * ���� riskName �� setter ����
	 * @param riskName the riskName to set
	 */
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	/**
	 * ���� amnt �� getter ����
	 * @return the amnt
	 */
	public BigDecimal getAmnt() {
		return amnt;
	}

	/**
	 * ���� amnt �� setter ����
	 * @param amnt the amnt to set
	 */
	public void setAmnt(BigDecimal amnt) {
		this.amnt = amnt;
	}

	/**
	 * ���� rate �� getter ����
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * ���� rate �� setter ����
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * ���� cvaliDate �� getter ����
	 * @return the cvaliDate
	 */
	public Date getCvaliDate() {
		return cvaliDate;
	}

	/**
	 * ���� cvaliDate �� setter ����
	 * @param cvaliDate the cvaliDate to set
	 */
	public void setCvaliDate(Date cvaliDate) {
		this.cvaliDate = cvaliDate;
	}

	/**
	 * ���� rank �� getter ����
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * ���� rank �� setter ����
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * ���� prem �� getter ����
	 * @return the prem
	 */
	public BigDecimal getPrem() {
		return prem;
	}

	/**
	 * ���� prem �� setter ����
	 * @param prem the prem to set
	 */
	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	/**
	 * ���� mult �� getter ����
	 * @return the mult
	 */
	public Integer getMult() {
		return mult;
	}

	/**
	 * ���� mult �� setter ����
	 * @param mult the mult to set
	 */
	public void setMult(Integer mult) {
		this.mult = mult;
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
	 * ���� costIntv �� getter ����
	 * @return the costIntv
	 */
	public String getCostIntv() {
		return costIntv;
	}

	/**
	 * ���� costIntv �� setter ����
	 * @param costIntv the costIntv to set
	 */
	public void setCostIntv(String costIntv) {
		this.costIntv = costIntv;
	}

	/**
	 * ���� costDate �� getter ����
	 * @return the costDate
	 */
	public String getCostDate() {
		return costDate;
	}

	/**
	 * ���� costDate �� setter ����
	 * @param costDate the costDate to set
	 */
	public void setCostDate(String costDate) {
		this.costDate = costDate;
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
	 * ���� specContent �� getter ����
	 * @return the specContent
	 */
	public String getSpecContent() {
		return specContent;
	}

	/**
	 * ���� specContent �� setter ����
	 * @param specContent the specContent to set
	 */
	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	/**
	 * ���� payEndYearFlag �� getter ����
	 * @return the payEndYearFlag
	 */
	public String getPayEndYearFlag() {
		return payEndYearFlag;
	}

	/**
	 * ���� payEndYearFlag �� setter ����
	 * @param payEndYearFlag the payEndYearFlag to set
	 */
	public void setPayEndYearFlag(String payEndYearFlag) {
		this.payEndYearFlag = payEndYearFlag;
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
	 * ���� getYearFlag �� getter ����
	 * @return the getYearFlag
	 */
	public String getGetYearFlag() {
		return getYearFlag;
	}

	/**
	 * ���� getYearFlag �� setter ����
	 * @param getYearFlag the getYearFlag to set
	 */
	public void setGetYearFlag(String getYearFlag) {
		this.getYearFlag = getYearFlag;
	}

	/**
	 * ���� getYear �� getter ����
	 * @return the getYear
	 */
	public String getGetYear() {
		return getYear;
	}

	/**
	 * ���� getYear �� setter ����
	 * @param getYear the getYear to set
	 */
	public void setGetYear(String getYear) {
		this.getYear = getYear;
	}

	/**
	 * ���� insuYearFlag �� getter ����
	 * @return the insuYearFlag
	 */
	public String getInsuYearFlag() {
		return insuYearFlag;
	}

	/**
	 * ���� insuYearFlag �� setter ����
	 * @param insuYearFlag the insuYearFlag to set
	 */
	public void setInsuYearFlag(String insuYearFlag) {
		this.insuYearFlag = insuYearFlag;
	}

	/**
	 * ���� insuYear �� getter ����
	 * @return the insuYear
	 */
	public String getInsuYear() {
		return insuYear;
	}

	/**
	 * ���� insuYear �� setter ����
	 * @param insuYear the insuYear to set
	 */
	public void setInsuYear(String insuYear) {
		this.insuYear = insuYear;
	}

	/**
	 * ���� getIntv �� getter ����
	 * @return the getIntv
	 */
	public String getGetIntv() {
		return getIntv;
	}

	/**
	 * ���� getIntv �� setter ����
	 * @param getIntv the getIntv to set
	 */
	public void setGetIntv(String getIntv) {
		this.getIntv = getIntv;
	}

	/**
	 * ���� getBankCode �� getter ����
	 * @return the getBankCode
	 */
	public String getGetBankCode() {
		return getBankCode;
	}

	/**
	 * ���� getBankCode �� setter ����
	 * @param getBankCode the getBankCode to set
	 */
	public void setGetBankCode(String getBankCode) {
		this.getBankCode = getBankCode;
	}

	/**
	 * ���� getBankAccNo �� getter ����
	 * @return the getBankAccNo
	 */
	public String getGetBankAccNo() {
		return getBankAccNo;
	}

	/**
	 * ���� getBankAccNo �� setter ����
	 * @param getBankAccNo the getBankAccNo to set
	 */
	public void setGetBankAccNo(String getBankAccNo) {
		this.getBankAccNo = getBankAccNo;
	}

	/**
	 * ���� getAccName �� getter ����
	 * @return the getAccName
	 */
	public String getGetAccName() {
		return getAccName;
	}

	/**
	 * ���� getAccName �� setter ����
	 * @param getAccName the getAccName to set
	 */
	public void setGetAccName(String getAccName) {
		this.getAccName = getAccName;
	}

	/**
	 * ���� getAccProvince �� getter ����
	 * @return the getAccProvince
	 */
	public String getGetAccProvince() {
		return getAccProvince;
	}

	/**
	 * ���� getAccProvince �� setter ����
	 * @param getAccProvince the getAccProvince to set
	 */
	public void setGetAccProvince(String getAccProvince) {
		this.getAccProvince = getAccProvince;
	}

	/**
	 * ���� getAccCity �� getter ����
	 * @return the getAccCity
	 */
	public String getGetAccCity() {
		return getAccCity;
	}

	/**
	 * ���� getAccCity �� setter ����
	 * @param getAccCity the getAccCity to set
	 */
	public void setGetAccCity(String getAccCity) {
		this.getAccCity = getAccCity;
	}

	/**
	 * ���� getAccType �� getter ����
	 * @return the getAccType
	 */
	public String getGetAccType() {
		return getAccType;
	}

	/**
	 * ���� getAccType �� setter ����
	 * @param getAccType the getAccType to set
	 */
	public void setGetAccType(String getAccType) {
		this.getAccType = getAccType;
	}

	/**
	 * ���� autoPayFlag �� getter ����
	 * @return the autoPayFlag
	 */
	public String getAutoPayFlag() {
		return autoPayFlag;
	}

	/**
	 * ���� autoPayFlag �� setter ����
	 * @param autoPayFlag the autoPayFlag to set
	 */
	public void setAutoPayFlag(String autoPayFlag) {
		this.autoPayFlag = autoPayFlag;
	}

	/**
	 * ���� bonusPayMode �� getter ����
	 * @return the bonusPayMode
	 */
	public String getBonusPayMode() {
		return bonusPayMode;
	}

	/**
	 * ���� bonusPayMode �� setter ����
	 * @param bonusPayMode the bonusPayMode to set
	 */
	public void setBonusPayMode(String bonusPayMode) {
		this.bonusPayMode = bonusPayMode;
	}

	/**
	 * ���� subFlag �� getter ����
	 * @return the subFlag
	 */
	public String getSubFlag() {
		return subFlag;
	}

	/**
	 * ���� subFlag �� setter ����
	 * @param subFlag the subFlag to set
	 */
	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
	}

	/**
	 * ���� bonusGetMode �� getter ����
	 * @return the bonusGetMode
	 */
	public String getBonusGetMode() {
		return bonusGetMode;
	}

	/**
	 * ���� bonusGetMode �� setter ����
	 * @param bonusGetMode the bonusGetMode to set
	 */
	public void setBonusGetMode(String bonusGetMode) {
		this.bonusGetMode = bonusGetMode;
	}

	/**
	 * ���� autoRNewFlag �� getter ����
	 * @return the autoRNewFlag
	 */
	public String getAutoRNewFlag() {
		return autoRNewFlag;
	}

	/**
	 * ���� autoRNewFlag �� setter ����
	 * @param autoRNewFlag the autoRNewFlag to set
	 */
	public void setAutoRNewFlag(String autoRNewFlag) {
		this.autoRNewFlag = autoRNewFlag;
	}

	/**
	 * ���� healthFlag �� getter ����
	 * @return the healthFlag
	 */
	public String getHealthFlag() {
		return healthFlag;
	}

	/**
	 * ���� healthFlag �� setter ����
	 * @param healthFlag the healthFlag to set
	 */
	public void setHealthFlag(String healthFlag) {
		this.healthFlag = healthFlag;
	}

	/**
	 * ���� fullBonusGetMode �� getter ����
	 * @return the fullBonusGetMode
	 */
	public String getFullBonusGetMode() {
		return fullBonusGetMode;
	}

	/**
	 * ���� fullBonusGetMode �� setter ����
	 * @param fullBonusGetMode the fullBonusGetMode to set
	 */
	public void setFullBonusGetMode(String fullBonusGetMode) {
		this.fullBonusGetMode = fullBonusGetMode;
	}

	/**
	 * ���� firstRate �� getter ����
	 * @return the firstRate
	 */
	public BigDecimal getFirstRate() {
		return firstRate;
	}

	/**
	 * ���� firstRate �� setter ����
	 * @param firstRate the firstRate to set
	 */
	public void setFirstRate(BigDecimal firstRate) {
		this.firstRate = firstRate;
	}

	/**
	 * ���� sureRate �� getter ����
	 * @return the sureRate
	 */
	public BigDecimal getSureRate() {
		return sureRate;
	}

	/**
	 * ���� sureRate �� setter ����
	 * @param sureRate the sureRate to set
	 */
	public void setSureRate(BigDecimal sureRate) {
		this.sureRate = sureRate;
	}

	/**
	 * ���� accountCount �� getter ����
	 * @return the accountCount
	 */
	public Integer getAccountCount() {
		return accountCount;
	}

	/**
	 * ���� accountCount �� setter ����
	 * @param accountCount the accountCount to set
	 */
	public void setAccountCount(Integer accountCount) {
		this.accountCount = accountCount;
	}

	/**
	 * ���� accounts �� getter ����
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * ���� accounts �� setter ����
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * ���� lcBnfCount �� getter ����
	 * @return the lcBnfCount
	 */
	public Integer getLcBnfCount() {
		return lcBnfCount;
	}

	/**
	 * ���� lcBnfCount �� setter ����
	 * @param lcBnfCount the lcBnfCount to set
	 */
	public void setLcBnfCount(Integer lcBnfCount) {
		this.lcBnfCount = lcBnfCount;
	}

	/**
	 * ���� lcBnfs �� getter ����
	 * @return the lcBnfs
	 */
	public List<LCBnf> getLcBnfs() {
		return lcBnfs;
	}

	/**
	 * ���� lcBnfs �� setter ����
	 * @param lcBnfs the lcBnfs to set
	 */
	public void setLcBnfs(List<LCBnf> lcBnfs) {
		this.lcBnfs = lcBnfs;
	}

}
