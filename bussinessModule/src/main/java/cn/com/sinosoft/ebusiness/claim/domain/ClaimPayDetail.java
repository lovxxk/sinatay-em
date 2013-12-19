package cn.com.sinosoft.ebusiness.claim.domain;

public class ClaimPayDetail {
	//支付方式
	private String payType;
	//支付时间
	private String payDate;
	//领款人姓名
	private String receiverName;
	//领款人证件类型
	private String receiverIDType;
	//领款人证件号码
	private String receiverID;
	//领款金额
	private String receiveMoney;
	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverIDType() {
		return receiverIDType;
	}
	public void setReceiverIDType(String receiverIDType) {
		this.receiverIDType = receiverIDType;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getReceiveMoney() {
		return receiveMoney;
	}
	public void setReceiveMoney(String receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	
}
