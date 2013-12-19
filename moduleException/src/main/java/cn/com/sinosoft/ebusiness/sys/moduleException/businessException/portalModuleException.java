package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;

import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;

/**
 * service-UserÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class portalModuleException extends BusinessException {
	private static final String subUserExceptionCode = "010020";

	private portalModuleException(String concreteExceptionCode, String msg,
			Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static portalModuleException newInstanceCode(
			String concreteExceptionCode) {
		return new portalModuleException(concreteExceptionCode, null, null,
				null);
	}

	public static portalModuleException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new portalModuleException(concreteExceptionCode, null, cause,
				null);
	}

	public static portalModuleException newInstanceMsg(String msg) {
		return new portalModuleException(null, msg, null, null);
	}

	public static portalModuleException newInstanceMsg(String msg,
			Throwable cause) {
		return new portalModuleException(null, msg, cause, null);
	}

	public static portalModuleException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new portalModuleException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
