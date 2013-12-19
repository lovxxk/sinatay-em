package cn.com.sinosoft.ebusiness.sys.exception.aspect;


import java.util.concurrent.BlockingQueue;

import org.apache.log4j.helpers.LogLog;

import cn.com.sinosoft.util.queue.QueuesHolder;

/**
 * 
 * ��������Ϣ����QueueManager�������Blocking Queue��.
 * 
 * @see QueuesHolder
 * 
 */
public class ExceptionQueueAppender{

	protected  String  queueName;

	protected  BlockingQueue<ExceptionEvent> queue;

	/**
	 * Exception����ExceptionQueue.
	 */
	public void append(ExceptionEvent event) {
		if (queue == null) {
			queue = QueuesHolder.getQueue(queueName);
		}
		boolean sucess = queue.offer(event);

		if (sucess) {
			LogLog.debug("put exception to "+queueName+" success:" + new ExceptionEventWrapper(event).convertToString());
		} else {
			LogLog.error("Put exception to "+queueName+" fail:" + new ExceptionEventWrapper(event).convertToString());
		}
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
