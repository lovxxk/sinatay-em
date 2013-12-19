package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;
/**
 * SaleÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class SaleException extends BusinessException {
	private static final String subUserExceptionCode = "010018";

	private SaleException(String concreteExceptionCode,
			String msg, Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static SaleException newInstanceCode(
			String concreteExceptionCode) {
		return new SaleException(concreteExceptionCode,
				null, null, null);
	}

	public static SaleException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new SaleException(concreteExceptionCode,
				null, cause, null);
	}

	public static SaleException newInstanceMsg(String msg) {
		return new SaleException(null, msg, null, null);
	}

	public static SaleException newInstanceMsg(String msg,
			Throwable cause) {
		return new SaleException(null, msg, cause, null);
	}

	public static SaleException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new SaleException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
