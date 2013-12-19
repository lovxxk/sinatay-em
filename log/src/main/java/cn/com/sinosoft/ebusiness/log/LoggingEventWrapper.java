package cn.com.sinosoft.ebusiness.log;

/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: LoggingEventWrapper.java 1099 2010-05-29 14:33:47Z calvinxiu $
 */

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log4j LoggingEvent的包装类, 提供默认的toString函数及更直观的属性访问方法.
 * 
 *  
 */
public class LoggingEventWrapper {
	public static final PatternLayout DEFAULT_PATTERN_LAYOUT = new PatternLayout(
			"%d [%t]  %-5p %c - %m");

	private final LoggingEvent event;
	private final String logType;
	private final String message;
	private final String className;
	private final String methodName;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public LoggingEventWrapper(LoggingEvent event) {
		this.event = event;
		this.message = (String) event.getMessage();
		this.className = event.getLocationInformation().getClassName();
		this.methodName = event.getLocationInformation().getMethodName();
		if (message.contains("@MethodTrace")
				) {
			this.logType = "1";
		} else {
			this.logType = "0";
		}
	}

	/**
	 * 使用默认的layoutPattern转换事件到日志字符串.
	 */
	public String convertToString() {
		return DEFAULT_PATTERN_LAYOUT.format(event);
	}

	/**
	 * 根据参数中的layoutPattern转换事件到日志字符串.
	 */
	public String convertToString(String layoutPattern) {
		return new PatternLayout(layoutPattern).format(event);
	}

	public String getSerialNo() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public long getTimeStamp() {
		return event.getTimeStamp();
	}

	public String getTraceId() {
		String traceId = "noid";
		if ("1".equals(logType) && message.contains("@traceId=")) {
			traceId = message.split("Id=")[1].split("]")[0];
		}
		return traceId;
	}

	public Date getDate() {
		return new Date(event.getTimeStamp());
	}

	public String getThreadName() {
		return event.getThreadName();
	}

	public String getLoggerName() {
		return event.getLoggerName();
	}

	public String getLevel() {
		return event.getLevel().toString();
	}

	public String getMessage() {
		return this.message;
	}

	public String getLogType() {
		return this.logType;
	}

	/**
	 * 影响性能,慎用.
	 */
	public String getClassName() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				String traceClassName = message.split("--")[3].split("\\(")[0]
						.split("\\.")[0];
				return traceClassName;
			} catch (Exception e) {
				logger.warn("err eventwrpper get classname");
			}
		}
		return this.className;
	}

	/**
	 * 影响性能,慎用.
	 */
	public String getMethodName() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				String traceMethodName = message.split("--")[3].split("\\(")[0]
						.split("\\.")[1];
				return traceMethodName;
			} catch (Exception e) {
				logger.warn("err eventwrpper get methodname");
			}
		}
		return this.methodName;
	}

	public String getInputParam() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				String inputParam = message.split("--")[5];
				return inputParam;
			} catch (Exception e) {
				logger.warn("err eventwrpper get inputparam");
			}
		}
		return null;
	}

	public String getOutParam() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				String outParam = message.split("--")[7];
				return outParam;
			} catch (Exception e) {
				logger.warn("err eventwrpper get outparam");
			}
		}
		return null;
	}

	public Date getBeginTime() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				Timestamp beginTime = Timestamp.valueOf(message.split("--")[1]);
				return beginTime;
			} catch (Exception e) {
				logger.warn("err eventwrpper get begintime");
			}
		}
		return null;
	}

	public Date getEndTime() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				Timestamp endTime = Timestamp.valueOf(message.split("--")[1]);
				return endTime;
			} catch (Exception e) {
				logger.warn("err eventwrpper get endtime");
			}
		}
		return null;
	}

	public int getConsumeTime() {
		if ("1".equals(getLogType())) {
			try {
				String message = getMessage();
				int consumeTime = new Integer(message.split("--")[11].trim())
						.intValue();
				return consumeTime;
			} catch (Exception e) {
				logger.warn("err eventwrpper get consumetime");
			}
		}
		return 0;
	}

	public String getThrowableInformation() {
		StringBuffer throwableInformation = new StringBuffer(1000);
		String[] throwableStrRep = event.getThrowableStrRep();
		if (throwableStrRep == null)
			return StringUtils.EMPTY;
		for (int i = 0, n = throwableStrRep.length; i < n; i++) {
			throwableInformation.append(throwableStrRep[i]);
			throwableInformation.append("\r\n");
		}
		return throwableInformation.toString();
	}

}
