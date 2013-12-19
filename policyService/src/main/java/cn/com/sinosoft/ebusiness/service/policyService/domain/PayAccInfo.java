package cn.com.sinosoft.ebusiness.service.policyService.domain;

public class PayAccInfo {
	// 付费账户类型
	private String accType;
	// 银行类型
	private String bankType;
	// 银行省份
	private String bankProvince;
	// 银行市区
	private String bankCity;
	// 卡类型
	private String cardType;
	// 银行账号
	private String accNo;
	// 账户名
	private String accName;
	// cvv
	private String cvv;
	// 有效期
	private String cvdate;
	// 支行名称
	private String branchName;
	// 手机号
	private String phone;

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCvdate() {
		return cvdate;
	}

	public void setCvdate(String cvdate) {
		this.cvdate = cvdate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
