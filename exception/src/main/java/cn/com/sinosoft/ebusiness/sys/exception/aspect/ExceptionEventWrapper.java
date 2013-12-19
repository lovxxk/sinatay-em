package cn.com.sinosoft.ebusiness.sys.exception.aspect;

/**
 * Copyright (c) 2005-2012 sinosoft.com.cn
 *
 */

import java.util.Date;
import java.util.UUID;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.UserException;

/**
 * ExceptionEvent的包装类
 * 
 */
public class ExceptionEventWrapper {
	private final ExceptionEvent event;
	private final Throwable throwable;
	private final UserException userException;

	public ExceptionEventWrapper(ExceptionEvent event) {
		this.event = event;
		if (event == null) {
			throwable = null;
		} else {
			throwable = event.getThrowable();
		}
		if (throwable != null && throwable instanceof UserException) {
			userException = (UserException) throwable;
		} else {
			userException = null;
		}
	}

	public String getSerialNo() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public String getExceptionKind() {
		if (userException != null) {
			return userException.getExceptionKind();
		}
		return "00";
	}

	public String getUserExceptionCode() {
		if (userException != null) {
			return userException.getUserExceptionCode();
		}
		return "";
	}

	public String getSubUserExceptionCode() {
		if (userException != null) {
			return userException.getSubUserExceptionCode();
		}
		return "";
	}

	public String getConcreteExceptionCode() {
		if (userException != null) {
			return userException.getConcreteExceptionCode();
		}
		return "";
	}

	public String getExceptionDesc() {
		if (userException != null) {
			return userException.getMsg();
		}
		if (throwable != null && throwable.getStackTrace() != null) {
			if (throwable.getStackTrace().length > 0
					&& throwable.getStackTrace()[0] != null)
				return throwable.getStackTrace()[0].toString();
		}
		return "no exception desc";
	}
	
	public String getIdentifyFlag(){
		if (userException != null) {
			return userException.getIdentifyFlag();
		}
		return "";
	}

	/**
	 * 模仿throwable的printStackTrace
	 * 
	 * @return
	 */
	public String getExceptionReason() {
		StringBuffer sb = new StringBuffer();
		if (this.throwable != null) {
			try {
				sb.append(this.throwable.toString() + "\n");
				StackTraceElement causedTrace[] = this.throwable
						.getStackTrace();
				for (StackTraceElement trace : this.throwable.getStackTrace()) {
					sb.append("\tat " + trace.toString() + "\n");
				}
				Throwable ourCause = this.throwable.getCause();
				while (ourCause != null) {
					StackTraceElement[] trace = ourCause.getStackTrace();
					int m = trace.length - 1, n = causedTrace.length - 1;
					while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
						m--;
						n--;
					}
					int framesInCommon = trace.length - 1 - m;

					sb.append("Caused by: " + ourCause);
					for (int i = 0; i <= m; i++)
						sb.append("\tat " + trace[i]);
					if (framesInCommon != 0)
						sb.append("\t... " + framesInCommon + " more");
					causedTrace = ourCause.getStackTrace();
					ourCause = ourCause.getCause();
				}
				sb.toString();
			} catch (Exception e) {
				sb = new StringBuffer();
				sb.append("分析异常时,产生异常: " + e.getMessage());
			}
		} else
			sb.append("no exception reason");
		return sb.toString();
	}

	public String getCause(Throwable ourCause) {
		return null;
	}

	public Date getExceptionTime() {
		if (event != null) {
			return event.getExceptionTime();
		}
		return new Date();
	}

	public String getExceptionGrade() {
		if (userException != null) {
			return userException.getGrade().toString();
		}
		return ExceptionGrade.SERIOUS.toString();
	}

	public String convertToString() {
		if (this.throwable != null) {
			return "Exception(" + this.getExceptionTime() + "---"
					+ this.getExceptionDesc() + ")";
		}
		return "throwable is null";
	}

}
