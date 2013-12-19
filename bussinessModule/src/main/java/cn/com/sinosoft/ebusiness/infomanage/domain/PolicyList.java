package cn.com.sinosoft.ebusiness.infomanage.domain;

/**
 * 保单列表domain类
 * @author chengj
 *
 */
public class PolicyList {
	/**
	 * 保单号
	 */
	private String policySerialNumber;
	
	/**
	 * 投保单号
	 */
	private String prNumber;
	
	/**
	 * 主险名称
	 */
	private String mainRiskName;
	
	/**
	 * 被保人姓名
	 */
	private String insuredName;
	
	/**
	 * 保险金额
	 */
	private String amount;
	
	/**
	 * 保单生效日
	 */
	private String inceptionDate;
	
	/**
	 * 保单状态
	 */
	private String policyStatus;
	
	/**
	 * 下载字符串
	 */
	private String downloadString;
	
	public String getPolicySerialNumber() {
		return policySerialNumber;
	}
	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}
	public String getPrNumber() {
		return prNumber;
	}
	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}
	public String getMainRiskName() {
		return mainRiskName;
	}
	public void setMainRiskName(String mainRiskName) {
		this.mainRiskName = mainRiskName;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getInceptionDate() {
		return inceptionDate;
	}
	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public String getDownloadString() {
		return downloadString;
	}
	public void setDownloadString(String downloadString) {
		this.downloadString = downloadString;
	}
}
