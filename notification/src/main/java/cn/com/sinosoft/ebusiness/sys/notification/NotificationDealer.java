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
 * 将Queue中的notification event发送到monitor的消费者任务.
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
	 * 消息处理函数
	 */
	@Override
	protected void processMessage(Object message) {
		NotificationEvent event = (NotificationEvent) message;
		// 向monitor发送http请求
		String param = getParam(event);
		logger.debug("url=" + url + param);
		if(StringUtils.isNotBlank(validind) && validind.equals("1")){
			sendNotificationRequest(param, event);
		}
	}

	/**
	 * 向monitor发送notification请求
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
			// 参数长度太大，不能用get方式
			httpConnection.setRequestMethod("POST");
			// 不使用缓存
			httpConnection.setUseCaches(false);
			// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			httpConnection.setInstanceFollowRedirects(true);
			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
			// 意思是正文是urlencoded编码过的form参数
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect。
			// 实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
			httpConnection.connect();
			out = new DataOutputStream(httpConnection.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
			String content = param;
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
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
			// monitor返回文本不是success,则认为发送失败
			if (!"success".equals(resposne.toString())) {
				logger.error("error send to monitor  notification, ignore it: "
						+ new NotificationEventWrapper(event).convertToString()
						+ "\n向" + url + "\n发送" + content + "\n返回" + resposne);
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
	 * 退出清理函数
	 */
	@Override
	protected void clean() {
		logger.debug("cleaned task {}", this);
	}

	/**
	 * 向monitor发送请求的参数内容
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
	 * 可被子类重载的数据访问错误处理函数,如将出错的事件持久化到文件.
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
