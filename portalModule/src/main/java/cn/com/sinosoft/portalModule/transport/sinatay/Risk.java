package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LCCont -> LCAppnt -> Risk节点DTO
 *
 */
public class Risk implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 险种代码 */
	private String riskCode;
	
	/** 主险险种代码 */
	private String mainRiskCode;
	
	/** 险种类型 不填 */
	private String riskType;
	
	/*险种账户类型 */
	private String riskAccType;
	
	private String riskDutyType;
	
	 /*保单险种号码*/
	private String polNo;
	
	 /*险种状态*/
	private String state;
	
	/** 险种名称 不填 */
	private String riskName;
	
	/** 保额 */
	private BigDecimal amnt;
	
	/** 费率 不填 */
	private BigDecimal rate;
	
	/** 生效日期 不填 */
	private Date cvaliDate;
	
	/** 档次 */
	private String rank;
	
	/** 保险费 */
	private BigDecimal prem;
	
	/** 投保份数 */
	private Integer mult;
	
	/** 缴费间隔 CD18 */
	private String payIntv;
	
	/** 扣款间隔 不填 */
	private String costIntv;
	
	/** 扣款时间 CD01 不填 */
	private String costDate;
	
	/** 保险期间 */
	private String years;
	
	/** 特别约定 */
	private String specContent;
	
	/** 缴费年期年龄标志 CD19 */
	private String payEndYearFlag;
	
	/** 缴费年期年龄 */
	private String payEndYear;
	
	/** 领取年龄年期标志 CD19 */
	private String getYearFlag;
	
	/** 领取年龄 */
	private String getYear;
	
	/** 保险年期年龄标志 CD19 */
	private String insuYearFlag;
	
	/** 保险年期年龄 */
	private String insuYear;
	
	/** 领取方式 不填 */
	private String getIntv;
	
	/** 领取银行编码 不填 */
	private String getBankCode;
	
	/** 领取银行账户 不填 */
	private String getBankAccNo;
	
	/** 领取银行户名 不填 */
	private String getAccName;
	
	/** 领取银行省编码 不填 */
	private String getAccProvince;
	
	/** 领取银行市编码 不填 */
	private String getAccCity;
	
	/** 领取银行卡折类型 不填 */
	private String getAccType;
	
	/** 垫交标志 CD20 */
	private String autoPayFlag;
	
	/** 红利分配标记 CD21 */
	private String bonusPayMode;
	
	/** 减额交清标志 CD22 */
	private String subFlag;
	
	/** 红利领取方式 CD23 */
	private String bonusGetMode;
	
	/** 自动续交标志 CD24 */
	private String autoRNewFlag;
	
	/** 健康告之标志 不填 */
	private String healthFlag;
	
	/** 满期领取金领取方式 不填 */
	private String fullBonusGetMode;
	
	/** 初始费用率 不填 */
	private BigDecimal firstRate;
	
	/** 保证利率 不填 */
	private BigDecimal sureRate;
	
	/** 账户数 */
	private Integer accountCount;
	
	/** 账户 */
	private List<Account> accounts = new ArrayList<Account>(0);
	
	/** 受益人数 */
	private Integer lcBnfCount;
	
	/** 受益人 */
	private List<LCBnf> lcBnfs = new ArrayList<LCBnf>(0);
	
	
	/**
	 * 属性 riskCode 的 getter 方法
	 * @return the riskCode
	 */
	public String getRiskCode() {
		return riskCode;
	}

	/**
	 * 属性 riskCode 的 setter 方法
	 * @param riskCode the riskCode to set
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	/**
	 * 属性 mainRiskCode 的 getter 方法
	 * @return the mainRiskCode
	 */
	public String getMainRiskCode() {
		return mainRiskCode;
	}

	/**
	 * 属性 mainRiskCode 的 setter 方法
	 * @param mainRiskCode the mainRiskCode to set
	 */
	public void setMainRiskCode(String mainRiskCode) {
		this.mainRiskCode = mainRiskCode;
	}

	/**
	 * 属性 riskType 的 getter 方法
	 * @return the riskType
	 */
	public String getRiskType() {
		return riskType;
	}

	/**
	 * 属性 riskType 的 setter 方法
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
	 * 属性 riskName 的 getter 方法
	 * @return the riskName
	 */
	public String getRiskName() {
		return riskName;
	}

	/**
	 * 属性 riskName 的 setter 方法
	 * @param riskName the riskName to set
	 */
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	/**
	 * 属性 amnt 的 getter 方法
	 * @return the amnt
	 */
	public BigDecimal getAmnt() {
		return amnt;
	}

	/**
	 * 属性 amnt 的 setter 方法
	 * @param amnt the amnt to set
	 */
	public void setAmnt(BigDecimal amnt) {
		this.amnt = amnt;
	}

	/**
	 * 属性 rate 的 getter 方法
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * 属性 rate 的 setter 方法
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * 属性 cvaliDate 的 getter 方法
	 * @return the cvaliDate
	 */
	public Date getCvaliDate() {
		return cvaliDate;
	}

	/**
	 * 属性 cvaliDate 的 setter 方法
	 * @param cvaliDate the cvaliDate to set
	 */
	public void setCvaliDate(Date cvaliDate) {
		this.cvaliDate = cvaliDate;
	}

	/**
	 * 属性 rank 的 getter 方法
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * 属性 rank 的 setter 方法
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * 属性 prem 的 getter 方法
	 * @return the prem
	 */
	public BigDecimal getPrem() {
		return prem;
	}

	/**
	 * 属性 prem 的 setter 方法
	 * @param prem the prem to set
	 */
	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	/**
	 * 属性 mult 的 getter 方法
	 * @return the mult
	 */
	public Integer getMult() {
		return mult;
	}

	/**
	 * 属性 mult 的 setter 方法
	 * @param mult the mult to set
	 */
	public void setMult(Integer mult) {
		this.mult = mult;
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
	 * 属性 costIntv 的 getter 方法
	 * @return the costIntv
	 */
	public String getCostIntv() {
		return costIntv;
	}

	/**
	 * 属性 costIntv 的 setter 方法
	 * @param costIntv the costIntv to set
	 */
	public void setCostIntv(String costIntv) {
		this.costIntv = costIntv;
	}

	/**
	 * 属性 costDate 的 getter 方法
	 * @return the costDate
	 */
	public String getCostDate() {
		return costDate;
	}

	/**
	 * 属性 costDate 的 setter 方法
	 * @param costDate the costDate to set
	 */
	public void setCostDate(String costDate) {
		this.costDate = costDate;
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
	 * 属性 specContent 的 getter 方法
	 * @return the specContent
	 */
	public String getSpecContent() {
		return specContent;
	}

	/**
	 * 属性 specContent 的 setter 方法
	 * @param specContent the specContent to set
	 */
	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	/**
	 * 属性 payEndYearFlag 的 getter 方法
	 * @return the payEndYearFlag
	 */
	public String getPayEndYearFlag() {
		return payEndYearFlag;
	}

	/**
	 * 属性 payEndYearFlag 的 setter 方法
	 * @param payEndYearFlag the payEndYearFlag to set
	 */
	public void setPayEndYearFlag(String payEndYearFlag) {
		this.payEndYearFlag = payEndYearFlag;
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
	 * 属性 getYearFlag 的 getter 方法
	 * @return the getYearFlag
	 */
	public String getGetYearFlag() {
		return getYearFlag;
	}

	/**
	 * 属性 getYearFlag 的 setter 方法
	 * @param getYearFlag the getYearFlag to set
	 */
	public void setGetYearFlag(String getYearFlag) {
		this.getYearFlag = getYearFlag;
	}

	/**
	 * 属性 getYear 的 getter 方法
	 * @return the getYear
	 */
	public String getGetYear() {
		return getYear;
	}

	/**
	 * 属性 getYear 的 setter 方法
	 * @param getYear the getYear to set
	 */
	public void setGetYear(String getYear) {
		this.getYear = getYear;
	}

	/**
	 * 属性 insuYearFlag 的 getter 方法
	 * @return the insuYearFlag
	 */
	public String getInsuYearFlag() {
		return insuYearFlag;
	}

	/**
	 * 属性 insuYearFlag 的 setter 方法
	 * @param insuYearFlag the insuYearFlag to set
	 */
	public void setInsuYearFlag(String insuYearFlag) {
		this.insuYearFlag = insuYearFlag;
	}

	/**
	 * 属性 insuYear 的 getter 方法
	 * @return the insuYear
	 */
	public String getInsuYear() {
		return insuYear;
	}

	/**
	 * 属性 insuYear 的 setter 方法
	 * @param insuYear the insuYear to set
	 */
	public void setInsuYear(String insuYear) {
		this.insuYear = insuYear;
	}

	/**
	 * 属性 getIntv 的 getter 方法
	 * @return the getIntv
	 */
	public String getGetIntv() {
		return getIntv;
	}

	/**
	 * 属性 getIntv 的 setter 方法
	 * @param getIntv the getIntv to set
	 */
	public void setGetIntv(String getIntv) {
		this.getIntv = getIntv;
	}

	/**
	 * 属性 getBankCode 的 getter 方法
	 * @return the getBankCode
	 */
	public String getGetBankCode() {
		return getBankCode;
	}

	/**
	 * 属性 getBankCode 的 setter 方法
	 * @param getBankCode the getBankCode to set
	 */
	public void setGetBankCode(String getBankCode) {
		this.getBankCode = getBankCode;
	}

	/**
	 * 属性 getBankAccNo 的 getter 方法
	 * @return the getBankAccNo
	 */
	public String getGetBankAccNo() {
		return getBankAccNo;
	}

	/**
	 * 属性 getBankAccNo 的 setter 方法
	 * @param getBankAccNo the getBankAccNo to set
	 */
	public void setGetBankAccNo(String getBankAccNo) {
		this.getBankAccNo = getBankAccNo;
	}

	/**
	 * 属性 getAccName 的 getter 方法
	 * @return the getAccName
	 */
	public String getGetAccName() {
		return getAccName;
	}

	/**
	 * 属性 getAccName 的 setter 方法
	 * @param getAccName the getAccName to set
	 */
	public void setGetAccName(String getAccName) {
		this.getAccName = getAccName;
	}

	/**
	 * 属性 getAccProvince 的 getter 方法
	 * @return the getAccProvince
	 */
	public String getGetAccProvince() {
		return getAccProvince;
	}

	/**
	 * 属性 getAccProvince 的 setter 方法
	 * @param getAccProvince the getAccProvince to set
	 */
	public void setGetAccProvince(String getAccProvince) {
		this.getAccProvince = getAccProvince;
	}

	/**
	 * 属性 getAccCity 的 getter 方法
	 * @return the getAccCity
	 */
	public String getGetAccCity() {
		return getAccCity;
	}

	/**
	 * 属性 getAccCity 的 setter 方法
	 * @param getAccCity the getAccCity to set
	 */
	public void setGetAccCity(String getAccCity) {
		this.getAccCity = getAccCity;
	}

	/**
	 * 属性 getAccType 的 getter 方法
	 * @return the getAccType
	 */
	public String getGetAccType() {
		return getAccType;
	}

	/**
	 * 属性 getAccType 的 setter 方法
	 * @param getAccType the getAccType to set
	 */
	public void setGetAccType(String getAccType) {
		this.getAccType = getAccType;
	}

	/**
	 * 属性 autoPayFlag 的 getter 方法
	 * @return the autoPayFlag
	 */
	public String getAutoPayFlag() {
		return autoPayFlag;
	}

	/**
	 * 属性 autoPayFlag 的 setter 方法
	 * @param autoPayFlag the autoPayFlag to set
	 */
	public void setAutoPayFlag(String autoPayFlag) {
		this.autoPayFlag = autoPayFlag;
	}

	/**
	 * 属性 bonusPayMode 的 getter 方法
	 * @return the bonusPayMode
	 */
	public String getBonusPayMode() {
		return bonusPayMode;
	}

	/**
	 * 属性 bonusPayMode 的 setter 方法
	 * @param bonusPayMode the bonusPayMode to set
	 */
	public void setBonusPayMode(String bonusPayMode) {
		this.bonusPayMode = bonusPayMode;
	}

	/**
	 * 属性 subFlag 的 getter 方法
	 * @return the subFlag
	 */
	public String getSubFlag() {
		return subFlag;
	}

	/**
	 * 属性 subFlag 的 setter 方法
	 * @param subFlag the subFlag to set
	 */
	public void setSubFlag(String subFlag) {
		this.subFlag = subFlag;
	}

	/**
	 * 属性 bonusGetMode 的 getter 方法
	 * @return the bonusGetMode
	 */
	public String getBonusGetMode() {
		return bonusGetMode;
	}

	/**
	 * 属性 bonusGetMode 的 setter 方法
	 * @param bonusGetMode the bonusGetMode to set
	 */
	public void setBonusGetMode(String bonusGetMode) {
		this.bonusGetMode = bonusGetMode;
	}

	/**
	 * 属性 autoRNewFlag 的 getter 方法
	 * @return the autoRNewFlag
	 */
	public String getAutoRNewFlag() {
		return autoRNewFlag;
	}

	/**
	 * 属性 autoRNewFlag 的 setter 方法
	 * @param autoRNewFlag the autoRNewFlag to set
	 */
	public void setAutoRNewFlag(String autoRNewFlag) {
		this.autoRNewFlag = autoRNewFlag;
	}

	/**
	 * 属性 healthFlag 的 getter 方法
	 * @return the healthFlag
	 */
	public String getHealthFlag() {
		return healthFlag;
	}

	/**
	 * 属性 healthFlag 的 setter 方法
	 * @param healthFlag the healthFlag to set
	 */
	public void setHealthFlag(String healthFlag) {
		this.healthFlag = healthFlag;
	}

	/**
	 * 属性 fullBonusGetMode 的 getter 方法
	 * @return the fullBonusGetMode
	 */
	public String getFullBonusGetMode() {
		return fullBonusGetMode;
	}

	/**
	 * 属性 fullBonusGetMode 的 setter 方法
	 * @param fullBonusGetMode the fullBonusGetMode to set
	 */
	public void setFullBonusGetMode(String fullBonusGetMode) {
		this.fullBonusGetMode = fullBonusGetMode;
	}

	/**
	 * 属性 firstRate 的 getter 方法
	 * @return the firstRate
	 */
	public BigDecimal getFirstRate() {
		return firstRate;
	}

	/**
	 * 属性 firstRate 的 setter 方法
	 * @param firstRate the firstRate to set
	 */
	public void setFirstRate(BigDecimal firstRate) {
		this.firstRate = firstRate;
	}

	/**
	 * 属性 sureRate 的 getter 方法
	 * @return the sureRate
	 */
	public BigDecimal getSureRate() {
		return sureRate;
	}

	/**
	 * 属性 sureRate 的 setter 方法
	 * @param sureRate the sureRate to set
	 */
	public void setSureRate(BigDecimal sureRate) {
		this.sureRate = sureRate;
	}

	/**
	 * 属性 accountCount 的 getter 方法
	 * @return the accountCount
	 */
	public Integer getAccountCount() {
		return accountCount;
	}

	/**
	 * 属性 accountCount 的 setter 方法
	 * @param accountCount the accountCount to set
	 */
	public void setAccountCount(Integer accountCount) {
		this.accountCount = accountCount;
	}

	/**
	 * 属性 accounts 的 getter 方法
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * 属性 accounts 的 setter 方法
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 属性 lcBnfCount 的 getter 方法
	 * @return the lcBnfCount
	 */
	public Integer getLcBnfCount() {
		return lcBnfCount;
	}

	/**
	 * 属性 lcBnfCount 的 setter 方法
	 * @param lcBnfCount the lcBnfCount to set
	 */
	public void setLcBnfCount(Integer lcBnfCount) {
		this.lcBnfCount = lcBnfCount;
	}

	/**
	 * 属性 lcBnfs 的 getter 方法
	 * @return the lcBnfs
	 */
	public List<LCBnf> getLcBnfs() {
		return lcBnfs;
	}

	/**
	 * 属性 lcBnfs 的 setter 方法
	 * @param lcBnfs the lcBnfs to set
	 */
	public void setLcBnfs(List<LCBnf> lcBnfs) {
		this.lcBnfs = lcBnfs;
	}

}
