package cn.com.sinosoft.ebusiness.common.party.domain;

/**
 * ����״̬dto
 *  
 *
 */
public class DistributionStatusDto implements java.io.Serializable {
	/**������*/
	private String bookNo;
	/**����״̬*/
	private String state;
	/**�ռ�������*/
	private String addressee;
	/**���͵�ַ*/
	private String postArea;
	/**��ϸ��ַ*/
	private String postAddress;
	/**��������*/
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
