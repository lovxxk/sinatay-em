package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

public class MailConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mailHost;
	private String mailAddress;
	private String mailAddresssName;
	private String userPassword;
	
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getMailAddresssName() {
		return mailAddresssName;
	}
	public void setMailAddresssName(String mailAddresssName) {
		this.mailAddresssName = mailAddresssName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
