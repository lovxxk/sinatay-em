package cn.com.sinosoft.ebusiness.claim.domain;

import java.util.List;

public class ClaimDetail {
	//赔案号
	private String claimNumber;
	//被保人姓名
	private String insuredName;
	//出险时间
	private String accDate;
	//理赔状态
	private String claimState;
	//出险地点
	private String accPlace;
	//理赔金额
	private String claimMoney;
	//出险原因
	private String accReason;
	//申请时间
	private String applyDate;
	//结案时间
	private String closeDate;
	//拒赔原因（备注）
	private String refuseReason;
	//理赔结论
	private String claimResult;
	//支付明细列表
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
