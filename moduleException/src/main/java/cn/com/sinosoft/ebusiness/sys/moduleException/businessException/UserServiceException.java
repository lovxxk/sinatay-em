package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;

import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;
/**
 * service-UserÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class UserServiceException extends BusinessException {
	private static final String subUserExceptionCode = "010000";

	private UserServiceException(String concreteExceptionCode,
			String msg, Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static UserServiceException newInstanceCode(
			String concreteExceptionCode) {
		return new UserServiceException(concreteExceptionCode, null,
				null, null);
	}

	public static UserServiceException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new UserServiceException(concreteExceptionCode, null,
				cause, null);
	}

	public static UserServiceException newInstanceMsg(String msg) {
		return new UserServiceException(null, msg, null, null);
	}

	public static UserServiceException newInstanceMsg(String msg,
			Throwable cause) {
		return new UserServiceException(null, msg, cause, null);
	}

	public static UserServiceException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new UserServiceException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
