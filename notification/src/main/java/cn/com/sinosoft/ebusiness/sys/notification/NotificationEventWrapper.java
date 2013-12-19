/**
 * Copyright (c) 2005-2012 sinosoft.com.cn
 *
 */
package cn.com.sinosoft.ebusiness.sys.notification;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NotificationEvent的包装类
 * 
 *  
 */
public class NotificationEventWrapper {
	private final NotificationEvent event;

	public NotificationEventWrapper(NotificationEvent event) {
		this.event = event;

	}

	// public String getTelParam(String param_tel) {
	// StringBuffer telParam = null;
	// if (null != event) {
	// Iterator<String> it_tel = event.getTels_iterator();
	// if (null != it_tel) {
	// telParam = new StringBuffer();
	// for (int i = 0; it_tel.hasNext(); i++) {
	// String tel = (String) it_tel.next();
	// telParam.append(param_tel + "[" + i + "]=" + encode(tel)
	// + "&");
	// }
	// return telParam.toString();
	// }
	// }
	// return "";
	// }
	public String getIsSendSmsParam(String param_tel) {
		if (null != event) {
			return param_tel + "=" + event.getIsSendSms() + "&";
		}
		return "";
	}

	public String getIsSendEmailParam(String param_email) {
		if (null != event) {
			return param_email + "=" + event.getIsSendEmail() + "&";
		}
		return "";
	}

	// public String getEmailParam(String param_email) {
	// StringBuffer emailParam = null;
	// if (null != event) {
	// Iterator<String> it_email = event.getEmails_iterator();
	// if (null != it_email) {
	// emailParam = new StringBuffer();
	// for (int i = 0; it_email.hasNext(); i++) {
	// String email = (String) it_email.next();
	// emailParam.append(param_email + "[" + i + "]="
	// + encode(email) + "&");
	// }
	// return emailParam.toString();
	// }
	// }
	// return "";
	// }

	public String getTitleParam(String param_title) {
		if (null != event && !"".equals(event.getTitle())) {
			String title = event.getTitle();
			return param_title + "=" + encode(title) + "&";
		}
		return param_title + "=" + encode("no title") + "&";
	}

	public String getContentParam(String param_content) {
		if (null != event && !"".equals(event.getContent())) {
			String content = event.getContent();
			return param_content + "=" + encode(content) + "&";
		}
		return param_content + "=" + encode("no content") + "&";
	}

	public String getNotificationModule() {
		if (null != event && null != event.getNotificationModule())
			return event.getNotificationModule().toString();
		return "";
	}

	public String convertToString() {
		if (null != event) {
			return "notification [title:" + event.getTitle() + ",content:"
					+ event.getContent() + "]";
		}
		return "notification [event =null]";
	}

	public String encode(String param) {
		String paramEncode = param;
		try {
			paramEncode = URLEncoder.encode(param, "GBK");
		} catch (UnsupportedEncodingException e) {
			Logger logger = LoggerFactory
					.getLogger(NotificationEventWrapper.class);
			logger.error(e.getMessage() + "   param is:" + param, e);
		}
		return paramEncode;
	}
}
