package cn.com.sinosoft.ebusiness.common.party.domain;
/**
 * 仅用于业务上的显示，不用于持久化
 *  
 *
 */
public class GeThirdParterGoods {
	private String proposalNo;
	private String itemName;
	private String itemAccount;
	//set and get method
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemAccount() {
		return itemAccount;
	}
	public void setItemAccount(String itemAccount) {
		this.itemAccount = itemAccount;
	}
}
