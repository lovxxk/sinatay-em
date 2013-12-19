package cn.com.sinosoft.ebusiness.common.party.domain;

/**
 * 物流状态dto
 *  
 *
 */
public class DistributionStatusDto implements java.io.Serializable {
	/**订单号*/
	private String bookNo;
	/**配送状态*/
	private String state;
	/**收件人姓名*/
	private String addressee;
	/**寄送地址*/
	private String postArea;
	/**详细地址*/
	private String postAddress;
	/**邮政编码*/
	private String postCode;
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
}
