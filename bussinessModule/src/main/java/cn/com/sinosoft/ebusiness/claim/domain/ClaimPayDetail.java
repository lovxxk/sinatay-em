package cn.com.sinosoft.ebusiness.claim.domain;

public class ClaimPayDetail {
	//֧����ʽ
	private String payType;
	//֧��ʱ��
	private String payDate;
	//���������
	private String receiverName;
	//�����֤������
	private String receiverIDType;
	//�����֤������
	private String receiverID;
	//�����
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
