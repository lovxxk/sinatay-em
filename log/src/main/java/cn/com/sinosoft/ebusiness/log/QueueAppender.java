package cn.com.sinosoft.ebusiness.log;


import java.util.concurrent.BlockingQueue;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import cn.com.sinosoft.util.queue.QueuesHolder;

/**
 * 轻量级的Log4j异步Appender.
 * 
 * 将所有消息放入QueueManager所管理的Blocking Queue中.
 * 
 * @see QueuesHolder
 * 
 *  
 */
public class QueueAppender extends org.apache.log4j.WriterAppender {

	protected String queueName;

	protected BlockingQueue<LoggingEvent> queue;

	/**
	 * AppenderSkeleton回调函数, 事件到达时将时间放入Queue.
	 */
	@Override
	public void append(LoggingEvent event) {
		if (queue == null) {
			queue = QueuesHolder.getQueue(queueName);
		}
//		System.out.println(new Date()+"---日志event放入队列  FQNOfLoggerClass---"+event.getFQNOfLoggerClass());
		boolean sucess = queue.offer(event);

		if (sucess) {
			LogLog.debug("put event to queue success:" + new LoggingEventWrapper(event).convertToString());
		} else {
			LogLog.error("Put event to queue fail:" + new LoggingEventWrapper(event).convertToString());
		}
	}

	/**
	 * AppenderSkeleton回调函数,关闭Logger时的清理动作.
	 */
	public void close() {
	}

	/**
	 * AppenderSkeleton回调函数, 设置是否需要定义Layout.
	 */
	public boolean requiresLayout() {
		return false;
	}

	/**
	 * Log4j根据getter/setter从log4j.properties中注入同名参数.
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
