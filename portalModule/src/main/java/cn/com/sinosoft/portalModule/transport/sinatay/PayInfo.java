package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;

public class PayInfo {
	private String payType;
	private String proposalContNo;
	private String orderId;
	private BigDecimal prem;
	private String accName;
	private String accBankCode;
	private String accProvince;
	private String accCity;
	private String accType;
	private String creditValid;
	private String creditCvv2;
	private String bankAccNo;
	private String appntIDType;
	private String appntIDNo;
	private String appntMobile;
	private String callbackMode;
	private String callbackUrl;

	public String getCallbackMode() {
		return callbackMode;
	}

	public void setCallbackMode(String callbackMode) {
		this.callbackMode = callbackMode;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getProposalContNo() {
		return proposalContNo;
	}

	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPrem() {
		return prem;
	}

	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccBankCode() {
		return accBankCode;
	}

	public void setAccBankCode(String accBankCode) {
		this.accBankCode = accBankCode;
	}

	public String getAccProvince() {
		return accProvince;
	}

	public void setAccProvince(String accProvince) {
		this.accProvince = accProvince;
	}

	public String getAccCity() {
		return accCity;
	}

	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getCreditValid() {
		return creditValid;
	}

	public void setCreditValid(String creditValid) {
		this.creditValid = creditValid;
	}

	public String getCreditCvv2() {
		return creditCvv2;
	}

	public void setCreditCvv2(String creditCvv2) {
		this.creditCvv2 = creditCvv2;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getAppntIDType() {
		return appntIDType;
	}

	public void setAppntIDType(String appntIDType) {
		this.appntIDType = appntIDType;
	}

	public String getAppntIDNo() {
		return appntIDNo;
	}

	public void setAppntIDNo(String appntIDNo) {
		this.appntIDNo = appntIDNo;
	}

	public String getAppntMobile() {
		return appntMobile;
	}

	public void setAppntMobile(String appntMobile) {
		this.appntMobile = appntMobile;
	}
}
