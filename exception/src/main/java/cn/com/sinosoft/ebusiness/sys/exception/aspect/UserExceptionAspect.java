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
 * �쳣����,д���ݿ�ͷ�֪ͨ
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
	 * �쳣����,���غ��׳�
	 * 
	 * @param ue
	 */
	@AfterThrowing(pointcut = "execution (* cn.com.sinosoft.ebusiness..service..spring.*SpringImpl.*(..)))", throwing = "ue")
	public void exceptionCatch(UserException ue) {
		System.out.println("--------catch---- begin------");
		// �����쳣������dbException
		ExceptionEvent ee = new ExceptionEvent(ue, new Date());
		exceptionQueueAppender.append(ee);
		// �����쳣֪ͨ����notification
		exceptionNotification(ee, ue);
		System.out.println("--------catch-----end-----");
	}

	/**
	 * �쳣֪ͨ
	 * 
	 * @param ee
	 * @param ue
	 */
	public void exceptionNotification(ExceptionEvent ee, UserException ue) {
		logger.info("�����û��쳣,����֪ͨ");
		if (ee != null && ue != null) {
			NotificationEvent event = null;
			String title = ue.getMsg();
			String content = "��" + new Date() + "�����쳣:";
			if (null == ue.getMessage()) {
				content = content + title;
			} else {
				content = content + title + "--" + ue.getMessage();
			}
			logger.info("�����û��쳣,�쳣������" + ue.getGrade().toString());
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
				logger.info("�쳣������,������֪ͨ");
			}
			// if (ue.getGrade() == ExceptionGrade.SERIOUS) {
			// event = new NotificationEvent(null,
			// ExceptionConfig.getEmailsIterator(), title, content,
			// NotificationModule.EXCEPTION);
			// notificationService.notification(event);
			// logger.info("�쳣������" + ExceptionGrade.SERIOUS.toString()
			// + "�����쳣֪ͨ����notification,�����ʼ�");
			// } else if (ue.getGrade() == ExceptionGrade.MORESERIOUS) {
			// event = new NotificationEvent(
			// ExceptionConfig.getTelsIterator(), null, title,
			// content, NotificationModule.EXCEPTION);
			// notificationService.notification(event);
			// logger.info("�쳣������" + ExceptionGrade.MORESERIOUS.toString()
			// + "�����쳣֪ͨ����notification,���Ͷ���");
			// } else if (ue.getGrade() == ExceptionGrade.MOSTSERIOUS) {
			// event = new NotificationEvent(
			// ExceptionConfig.getTelsIterator(),
			// ExceptionConfig.getEmailsIterator(), title, content,
			// NotificationModule.EXCEPTION);
			// notificationService.notification(event);
			// logger.info("�쳣������" + ExceptionGrade.MOSTSERIOUS.toString()
			// + "�����쳣֪ͨ����notification,���Ͷ��ź��ʼ�");
			// }
		} else {
			logger.info("���ص�UserExceptionΪnull");
		}
	}
}
