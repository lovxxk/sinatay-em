package cn.com.sinosoft.ebusiness.sys.permission.service.spring;

import org.springframework.security.core.AuthenticationException;

/**
 * µÇÂ¼spring securityÒì³£
 *  
 *
 */
public class LoginUserException extends AuthenticationException{
	private static final long serialVersionUID = 213146456576767L;

	public LoginUserException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
    public LoginUserException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public LoginUserException(String msg, Object extraInformation) {
        super(msg,extraInformation);
    }
}
