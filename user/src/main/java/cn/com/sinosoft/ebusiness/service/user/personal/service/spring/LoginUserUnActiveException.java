package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import org.springframework.security.core.AuthenticationException;

/**
 * µÇÂ¼spring securityÒì³£
 *  
 *
 */
public class LoginUserUnActiveException extends AuthenticationException{
	private static final long serialVersionUID = 213146456576767L;

	public LoginUserUnActiveException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
    public LoginUserUnActiveException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public LoginUserUnActiveException(String msg, Object extraInformation) {
        super(msg,extraInformation);
    }
}
