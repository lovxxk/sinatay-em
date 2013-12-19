/**
 * Copyright (c) 2005-2012 sinosoft.com.cn
 *
 */
package cn.com.sinosoft.ebusiness.sys.notification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.com.sinosoft.util.queue.BlockingConsumer;
import cn.com.sinosoft.util.string.StringUtils;

/**
 * ��Queue�е�notification event���͵�monitor������������.
 * 
 * @see BlockingConsumer
 * 
 *  
 */
public class NotificationDealer extends BlockingConsumer {

	protected String appName;
	protected String url;
	protected String param_tel;
	protected String param_email;
	protected String param_title;
	protected String param_content;
	protected String validind;

	/**
	 * ��Ϣ������
	 */
	@Override
	protected void processMessage(Object message) {
		NotificationEvent event = (NotificationEvent) message;
		// ��monitor����http����
		String param = getParam(event);
		logger.debug("url=" + url + param);
		if(StringUtils.isNotBlank(validind) && validind.equals("1")){
			sendNotificationRequest(param, event);
		}
	}

	/**
	 * ��monitor����notification����
	 */
	public void sendNotificationRequest(String param, NotificationEvent event) {
		String responseMessage = "";
		HttpURLConnection httpConnection = null;
		DataOutputStream out = null;
		BufferedReader reader = null;
		try {
			URL urlPost = new URL(url);
			httpConnection = (HttpURLConnection) urlPost.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			// ��������̫�󣬲�����get��ʽ
			httpConnection.setRequestMethod("POST");
			// ��ʹ�û���
			httpConnection.setUseCaches(false);
			// URLConnection.setInstanceFollowRedirects�ǳ�Ա�������������ڵ�ǰ����
			httpConnection.setInstanceFollowRedirects(true);
			// ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
			// ��˼��������urlencoded�������form����
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
			// Ҫע�����connection.getOutputStream�������Ľ���connect��
			// ʵ����ֻ�ǽ�����һ�����������tcp���ӣ���û��ʵ�ʷ���http����
			httpConnection.connect();
			out = new DataOutputStream(httpConnection.getOutputStream());
			// The URL-encoded contend
			// ���ģ�����������ʵ��get��URL��'?'��Ĳ����ַ���һ��
			String content = param;
			// DataOutputStream.writeBytes���ַ����е�16λ��unicode�ַ���8λ���ַ���ʽд��������
			out.writeBytes(content);
			// flush and close
			out.flush();
			reader = new BufferedReader(new InputStreamReader(
					httpConnection.getInputStream()));
			StringBuffer resposne = new StringBuffer();
			while ((responseMessage = reader.readLine()) != null) {
				resposne.append(responseMessage);
			}
			// reader.close();
			// httpConnection.disconnect();
			// monitor�����ı�����success,����Ϊ����ʧ��
			if (!"success".equals(resposne.toString())) {
				logger.error("error send to monitor  notification, ignore it: "
						+ new NotificationEventWrapper(event).convertToString()
						+ "\n��" + url + "\n����" + content + "\n����" + resposne);
			} else {
				logger.debug("success send to monitor  notification");
			}
		} catch (IOException e) {
			handleIOException(e, url + param, event);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
				if (null != reader) {
					reader.close();
				}
				if (null != httpConnection) {
					httpConnection.disconnect();
				}
			} catch (Exception e2) {
				logger.error("http connection close error:", e2);
			}
		}

	}

	/**
	 * �˳�������
	 */
	@Override
	protected void clean() {
		logger.debug("cleaned task {}", this);
	}

	/**
	 * ��monitor��������Ĳ�������
	 */
	protected String getParam(NotificationEvent event) {
		StringBuffer ParamAppend = new StringBuffer();
		NotificationEventWrapper wrapper = new NotificationEventWrapper(event);
		ParamAppend
				.append(wrapper.getIsSendSmsParam(param_tel))
				.append(wrapper.getIsSendEmailParam(param_email))
				.append(wrapper.getTitleParam(param_title))
				.append(wrapper.getContentParam(param_content))
				.append("appName=" + appName)
				.append("&notificationModule="
						+ wrapper.getNotificationModule());
		return ParamAppend.toString();
	}

	/**
	 * �ɱ��������ص����ݷ��ʴ�������,�罫������¼��־û����ļ�.
	 */
	protected void handleIOException(IOException e, String url,
			NotificationEvent event) {
		if (e instanceof IOException) {
			logger.error("http connection error", e);
		} else {
			logger.error("other error", e);
		}
		logger.error("event send to monitor error notification, ignore it: "
				+ new NotificationEventWrapper(event).convertToString(), e);

	}

	public String getAppName() {
		return appName;
	}

	public String getParam_tel() {
		return param_tel;
	}

	public void setParam_tel(String param_tel) {
		this.param_tel = param_tel;
	}

	public String getParam_email() {
		return param_email;
	}

	public void setParam_email(String param_email) {
		this.param_email = param_email;
	}

	public String getParam_title() {
		return param_title;
	}

	public void setParam_title(String param_title) {
		this.param_title = param_title;
	}

	public String getParam_content() {
		return param_content;
	}

	public void setParam_content(String param_content) {
		this.param_content = param_content;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getValidind() {
		return validind;
	}

	public void setValidind(String validind) {
		this.validind = validind;
	}
	
}
