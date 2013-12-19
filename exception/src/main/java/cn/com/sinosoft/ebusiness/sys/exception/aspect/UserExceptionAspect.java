package cn.com.sinosoft.ebusiness.sys.exception.aspect;

import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.sys.exception.ExceptionConfig;
import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGrade;
import cn.com.sinosoft.ebusiness.sys.exception.ExceptionGradeHandle;
import cn.com.sinosoft.ebusiness.sys.exception.UserException;
import cn.com.sinosoft.ebusiness.sys.notification.NotificationEvent;
import cn.com.sinosoft.ebusiness.sys.notification.NotificationModule;
import cn.com.sinosoft.ebusiness.sys.notification.service.facade.NotificationService;

/**
 * 异常拦截,写数据库和发通知
 * 
 * 
 */
@Aspect
public class UserExceptionAspect {
	private static Logger logger = LoggerFactory
			.getLogger(UserExceptionAspect.class);

	@Autowired
	private ExceptionQueueAppender exceptionQueueAppender;

	@Autowired
	private NotificationService notificationService;

	/**
	 * 异常拦截,拦截后抛出
	 * 
	 * @param ue
	 */
	@AfterThrowing(pointcut = "execution (* cn.com.sinosoft.ebusiness..service..spring.*SpringImpl.*(..)))", throwing = "ue")
	public void exceptionCatch(UserException ue) {
		System.out.println("--------catch---- begin------");
		// 放入异常入库队列dbException
		ExceptionEvent ee = new ExceptionEvent(ue, new Date());
		exceptionQueueAppender.append(ee);
		// 放入异常通知队列notification
		exceptionNotification(ee, ue);
		System.out.println("--------catch-----end-----");
	}

	/**
	 * 异常通知
	 * 
	 * @param ee
	 * @param ue
	 */
	public void exceptionNotification(ExceptionEvent ee, UserException ue) {
		logger.info("拦截用户异常,发送通知");
		if (ee != null && ue != null) {
			NotificationEvent event = null;
			String title = ue.getMsg();
			String content = "在" + new Date() + "产生异常:";
			if (null == ue.getMessage()) {
				content = content + title;
			} else {
				content = content + title + "--" + ue.getMessage();
			}
			logger.info("拦截用户异常,异常级别是" + ue.getGrade().toString());
			ExceptionGradeHandle exceptionGradeHandle = ExceptionConfig
					.getGradeHandle(ue.getGrade().toString());

			if (exceptionGradeHandle != null
					&& exceptionGradeHandle.getExceptionGrade() != ExceptionGrade.UNSERIOUS
							.toString()) {
				event = new NotificationEvent(
						exceptionGradeHandle.getIsSendSms(),
						exceptionGradeHandle.getIsSendEmail(), title, content,
						NotificationModule.EXCEPTION);
				notificationService.notification(event);
			}else{
				logger.info("异常不严重,不发送通知");
			}
			// if (ue.getGrade() == ExceptionGrade.SERIOUS) {
			// event = new NotificationEvent(null,
			// ExceptionConfig.getEmailsIterator(), title, content,
			// NotificationModule.EXCEPTION);
			// notificationService.notification(event);
			// logger.info("异常级别是" + ExceptionGrade.SERIOUS.toString()
			// + "放入异常通知队列notification,发送邮件");
			// } else if (ue.getGrade() == ExceptionGrade.MORESERIOUS) {
			// event = new NotificationEvent(
			// ExceptionConfig.getTelsIterator(), null, title,
			// content, NotificationModule.EXCEPTION);
			// notificationService.notification(event);
			// logger.info("异常级别是" + ExceptionGrade.MORESERIOUS.toString()
			// + "放入异常通知队列notification,发送短信");
			// } else if (ue.getGrade() == ExceptionGrade.MOSTSERIOUS) {
			// event = new NotificationEvent(
			// ExceptionConfig.getTelsIterator(),
			// ExceptionConfig.getEmailsIterator(), title, content,
			// NotificationModule.EXCEPTION);
			// notificationService.notification(event);
			// logger.info("异常级别是" + ExceptionGrade.MOSTSERIOUS.toString()
			// + "放入异常通知队列notification,发送短信和邮件");
			// }
		} else {
			logger.info("拦截的UserException为null");
		}
	}
}
