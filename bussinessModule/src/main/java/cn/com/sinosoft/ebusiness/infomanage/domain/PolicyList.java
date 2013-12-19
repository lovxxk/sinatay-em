package cn.com.sinosoft.ebusiness.infomanage.domain;

/**
 * �����б�domain��
 * @author chengj
 *
 */
public class PolicyList {
	/**
	 * ������
	 */
	private String policySerialNumber;
	
	/**
	 * Ͷ������
	 */
	private String prNumber;
	
	/**
	 * ��������
	 */
	private String mainRiskName;
	
	/**
	 * ����������
	 */
	private String insuredName;
	
	/**
	 * ���ս��
	 */
	private String amount;
	
	/**
	 * ������Ч��
	 */
	private String inceptionDate;
	
	/**
	 * ����״̬
	 */
	private String policyStatus;
	
	/**
	 * �����ַ���
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
