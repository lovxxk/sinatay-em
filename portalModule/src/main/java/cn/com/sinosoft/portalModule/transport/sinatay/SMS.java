package cn.com.sinosoft.portalModule.transport.sinatay;

public class SMS implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String phoneNo;
	
	private String msgComment;
	
	private String sender;
	
	private String planTime;
	
	private String busiSerialNo;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMsgComment() {
		return msgComment;
	}

	public void setMsgComment(String msgComment) {
		this.msgComment = msgComment;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getBusiSerialNo() {
		return busiSerialNo;
	}

	public void setBusiSerialNo(String busiSerialNo) {
		this.busiSerialNo = busiSerialNo;
	}
}
