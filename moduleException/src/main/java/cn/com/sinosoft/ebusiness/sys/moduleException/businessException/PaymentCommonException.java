package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;
/**
 * Common PaymentÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class PaymentCommonException extends BusinessException {
	private static final String subUserExceptionCode = "010016";

	private PaymentCommonException(String concreteExceptionCode,
			String msg, Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static PaymentCommonException newInstanceCode(
			String concreteExceptionCode) {
		return new PaymentCommonException(concreteExceptionCode,
				null, null, null);
	}

	public static PaymentCommonException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new PaymentCommonException(concreteExceptionCode,
				null, cause, null);
	}

	public static PaymentCommonException newInstanceMsg(String msg) {
		return new PaymentCommonException(null, msg, null, null);
	}

	public static PaymentCommonException newInstanceMsg(String msg,
			Throwable cause) {
		return new PaymentCommonException(null, msg, cause, null);
	}

	public static PaymentCommonException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new PaymentCommonException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
