package cn.com.sinosoft.ebusiness.sys.moduleException.dataVerify;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.userException.DataVerifyException;
/**
 * MisÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class MisException extends DataVerifyException {
	private static final String subUserExceptionCode = "010101";

	private MisException(String concreteExceptionCode, String msg,
			Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static MisException newInstanceCode(String concreteExceptionCode) {
		return new MisException(concreteExceptionCode, null, null, null);
	}

	public static MisException newInstanceCode(String concreteExceptionCode,
			Throwable cause) {
		return new MisException(concreteExceptionCode, null, cause, null);
	}

	public static MisException newInstanceMsg(String msg) {
		return new MisException(null, msg, null, null);
	}

	public static MisException newInstanceMsg(String msg, Throwable cause) {
		return new MisException(null, msg, cause, null);
	}

	public static MisException newInstanceMsg(String msg, Throwable cause,
			ExceptionGrade grade) {
		return new MisException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
