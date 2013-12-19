package cn.com.sinosoft.ebusiness.sys.moduleException.permissionException;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.userException.PermissionException;
/**
 * 权限模块异常
 * 
 *  
 * 
 */
public class PermissionSysException extends PermissionException {
	private static final String subUserExceptionCode = "010200";

	private PermissionSysException(String concreteExceptionCode,
			String msg, Throwable cause, ExceptionGrade grade) {
		super(subUserExceptionCode, concreteExceptionCode, msg, cause, grade);
	}

	public static PermissionSysException newInstanceCode(
			String concreteExceptionCode) {
		return new PermissionSysException(concreteExceptionCode,
				null, null, null);
	}

	public static PermissionSysException newInstanceCode(
			String concreteExceptionCode, Throwable cause) {
		return new PermissionSysException(concreteExceptionCode,
				null, cause, null);
	}

	public static PermissionSysException newInstanceMsg(String msg) {
		return new PermissionSysException(null, msg, null, null);
	}

	public static PermissionSysException newInstanceMsg(String msg,
			Throwable cause) {
		return new PermissionSysException(null, msg, cause, null);
	}

	public static PermissionSysException newInstanceMsg(String msg,
			Throwable cause, ExceptionGrade grade) {
		return new PermissionSysException(null, msg, cause, grade);
	}

	public String getSubUserExceptionCode() {
		return subUserExceptionCode;
	}

	private static final long serialVersionUID = 1L;
}
