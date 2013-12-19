package cn.com.sinosoft.ebusiness.sys.exception.userException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.UserException;

/**
 * “µŒÒ“Ï≥£
 * 
 * 
 */
public abstract class BusinessException extends UserException {
	private final static String userExceptionCode = "0100";

	public BusinessException(String subUserExceptionCode,
			String concreteExceptionCode, String msg, Throwable cause,
			ExceptionGrade grade) {
		super(userExceptionCode, subUserExceptionCode, concreteExceptionCode,
				msg, cause, grade);
	}

	public String getUserExceptionCode() {
		return userExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
