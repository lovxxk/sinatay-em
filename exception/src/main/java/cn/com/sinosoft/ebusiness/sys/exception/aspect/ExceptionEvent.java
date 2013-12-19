package cn.com.sinosoft.ebusiness.sys.exception.aspect;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 异常事件描述类
 * 
 */
public class ExceptionEvent implements Serializable {
	static final long serialVersionUID = 1L;

	public ExceptionEvent(Throwable t, Date exceptionTime) {
		this.throwable = t;
		this.exceptionTime = exceptionTime;
	}

	private Throwable throwable;
	private Date exceptionTime;

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

}
