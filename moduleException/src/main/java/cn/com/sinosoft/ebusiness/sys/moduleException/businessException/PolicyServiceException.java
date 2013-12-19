package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;

import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;
/**
 * service-PolicyÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class PolicyServiceException extends BusinessException {
	private static final String subUserExceptionCode = "010006";

	private PolicyServiceException(String concreteExceptionCode, String msg,
			Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static PolicyServiceException newInstanceCode(
			String concreteExceptionCode) {
		return new PolicyServiceException(concreteExceptionCode, null, null,
				null);
	}

	public static PolicyServiceException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new PolicyServiceException(concreteExceptionCode, null, cause,
				null);
	}

	public static PolicyServiceException newInstanceMsg(String msg) {
		return new PolicyServiceException(null, msg, null, null);
	}

	public static PolicyServiceException newInstanceMsg(String msg,
			Throwable cause) {
		return new PolicyServiceException(null, msg, cause, null);
	}

	public static PolicyServiceException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new PolicyServiceException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
