package cn.com.sinosoft.ebusiness.claim.domain;

public class ClaimProcessList {
	//�ⰸ��
	private String claimNumber;
	//����������
	private String insuredName;
	//����ʱ��
	private String accDate;
	//�ⰸ״̬
	private String claimStatus;
	
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	
}
