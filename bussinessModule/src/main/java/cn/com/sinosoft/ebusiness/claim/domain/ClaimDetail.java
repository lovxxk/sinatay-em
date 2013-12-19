package cn.com.sinosoft.ebusiness.claim.domain;

import java.util.List;

public class ClaimDetail {
	//�ⰸ��
	private String claimNumber;
	//����������
	private String insuredName;
	//����ʱ��
	private String accDate;
	//����״̬
	private String claimState;
	//���յص�
	private String accPlace;
	//������
	private String claimMoney;
	//����ԭ��
	private String accReason;
	//����ʱ��
	private String applyDate;
	//�᰸ʱ��
	private String closeDate;
	//����ԭ�򣨱�ע��
	private String refuseReason;
	//�������
	private String claimResult;
	//֧����ϸ�б�
	private List<ClaimPayDetail> listClaimPayDetail;
	
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
	public String getClaimMoney() {
		return claimMoney;
	}
	public void setClaimMoney(String claimMoney) {
		this.claimMoney = claimMoney;
	}
	public String getAccReason() {
		return accReason;
	}
	public void setAccReason(String accReason) {
		this.accReason = accReason;
	}
	public String getClaimState() {
		return claimState;
	}
	public void setClaimState(String claimState) {
		this.claimState = claimState;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getClaimResult() {
		return claimResult;
	}
	public void setClaimResult(String claimResult) {
		this.claimResult = claimResult;
	}
	public List<ClaimPayDetail> getListClaimPayDetail() {
		return listClaimPayDetail;
	}
	public void setListClaimPayDetail(List<ClaimPayDetail> listClaimPayDetail) {
		this.listClaimPayDetail = listClaimPayDetail;
	}
	public String getAccPlace() {
		return accPlace;
	}
	public void setAccPlace(String accPlace) {
		this.accPlace = accPlace;
	}
}
