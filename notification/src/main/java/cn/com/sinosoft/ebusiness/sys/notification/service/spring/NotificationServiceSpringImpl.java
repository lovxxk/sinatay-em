package cn.com.sinosoft.ebusiness.sys.notification.service.spring;

import cn.com.sinosoft.ebusiness.sys.notification.NotificationEvent;
import cn.com.sinosoft.ebusiness.sys.notification.NotificationQueueAppender;
import cn.com.sinosoft.ebusiness.sys.notification.service.facade.NotificationService;

/**
 * 通知实现类
 * 
 *  
 * 
 */
public class NotificationServiceSpringImpl implements NotificationService {

	private NotificationQueueAppender notificationQueueAppender;

	public void notification(NotificationEvent event) {
		notificationQueueAppender.append(event);
	}

	public NotificationQueueAppender getNotificationQueueAppender() {
		return notificationQueueAppender;
	}

	public void setNotificationQueueAppender(
			NotificationQueueAppender notificationQueueAppender) {
		this.notificationQueueAppender = notificationQueueAppender;
	}

}
