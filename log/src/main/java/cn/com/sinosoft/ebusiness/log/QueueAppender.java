package cn.com.sinosoft.ebusiness.log;


import java.util.concurrent.BlockingQueue;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import cn.com.sinosoft.util.queue.QueuesHolder;

/**
 * ��������Log4j�첽Appender.
 * 
 * ��������Ϣ����QueueManager�������Blocking Queue��.
 * 
 * @see QueuesHolder
 * 
 *  
 */
public class QueueAppender extends org.apache.log4j.WriterAppender {

	protected String queueName;

	protected BlockingQueue<LoggingEvent> queue;

	/**
	 * AppenderSkeleton�ص�����, �¼�����ʱ��ʱ�����Queue.
	 */
	@Override
	public void append(LoggingEvent event) {
		if (queue == null) {
			queue = QueuesHolder.getQueue(queueName);
		}
//		System.out.println(new Date()+"---��־event�������  FQNOfLoggerClass---"+event.getFQNOfLoggerClass());
		boolean sucess = queue.offer(event);

		if (sucess) {
			LogLog.debug("put event to queue success:" + new LoggingEventWrapper(event).convertToString());
		} else {
			LogLog.error("Put event to queue fail:" + new LoggingEventWrapper(event).convertToString());
		}
	}

	/**
	 * AppenderSkeleton�ص�����,�ر�Loggerʱ��������.
	 */
	public void close() {
	}

	/**
	 * AppenderSkeleton�ص�����, �����Ƿ���Ҫ����Layout.
	 */
	public boolean requiresLayout() {
		return false;
	}

	/**
	 * Log4j����getter/setter��log4j.properties��ע��ͬ������.
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @see #getQueueName()
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
