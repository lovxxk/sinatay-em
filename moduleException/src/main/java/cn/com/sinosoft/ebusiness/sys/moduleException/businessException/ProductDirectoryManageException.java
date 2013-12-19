package cn.com.sinosoft.ebusiness.sys.moduleException.businessException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;

import cn.com.sinosoft.ebusiness.sys.exception.userException.BusinessException;
/**
 * manage-productDirectoryÄ£¿éÒì³£
 * 
 *  
 * 
 */
public class ProductDirectoryManageException extends BusinessException {
	private static final String subUserExceptionCode = "010007";

	private ProductDirectoryManageException(String concreteExceptionCode, String msg,
			Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static ProductDirectoryManageException newInstanceCode(
			String concreteExceptionCode) {
		return new ProductDirectoryManageException(concreteExceptionCode, null, null,
				null);
	}

	public static ProductDirectoryManageException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new ProductDirectoryManageException(concreteExceptionCode, null, cause,
				null);
	}

	public static ProductDirectoryManageException newInstanceMsg(String msg) {
		return new ProductDirectoryManageException(null, msg, null, null);
	}

	public static ProductDirectoryManageException newInstanceMsg(String msg,
			Throwable cause) {
		return new ProductDirectoryManageException(null, msg, cause, null);
	}

	public static ProductDirectoryManageException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new ProductDirectoryManageException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
