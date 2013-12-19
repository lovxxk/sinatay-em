package cn.com.sinosoft.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
	
	private String userName = null;
	
	private String password = null;
	
	
	public MailAuthenticator() {
		
	}

	public MailAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
	
}
