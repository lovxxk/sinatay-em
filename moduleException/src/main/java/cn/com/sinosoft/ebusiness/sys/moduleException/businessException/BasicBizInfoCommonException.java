package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;

/**
 * common-basicBizInfoÄ£¿éÒì³£
 * 
 */
public class BasicBizInfoCommonException extends BusinessException {
	private static final String subUserExceptionCode = "010013";

	private BasicBizInfoCommonException(String concreteExceptionCode,
			String msg, Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static BasicBizInfoCommonException newInstanceCode(
			String concreteExceptionCode) {
		return new BasicBizInfoCommonException(concreteExceptionCode, null,
				null, null);
	}

	public static BasicBizInfoCommonException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new BasicBizInfoCommonException(concreteExceptionCode, null,
				cause, null);
	}

	public static BasicBizInfoCommonException newInstanceMsg(String msg) {
		return new BasicBizInfoCommonException(null, msg, null, null);
	}

	public static BasicBizInfoCommonException newInstanceMsg(String msg,
			Throwable cause) {
		return new BasicBizInfoCommonException(null, msg, cause, null);
	}

	public static BasicBizInfoCommonException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new BasicBizInfoCommonException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
