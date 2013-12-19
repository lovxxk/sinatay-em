package cn.com.sinosoft.util.queue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.Assert;

/**
 * ���ö�ʱ������ȡQueue����Ϣ���Ե�Consumer.
 */
public abstract class PeriodConsumer extends QueueConsumer {

	protected int batchSize = 0;
	protected int period = 0;

	/**
	* ������ʱ��ȡ��Ϣ�Ķ��д�С.
	*/
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/**
	 * ������ʱ��ȡ��ʱ����,��λΪ����.
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	@PostConstruct
	public void checkSetting() {
		Assert.isTrue(batchSize > 0);
		Assert.isTrue(period > 0);
	}

	/**
	 * �߳�ִ�к���,����������ȡ��Ϣ������processMessageList()����.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				List list = new ArrayList(batchSize);
				queue.drainTo(list, batchSize);
				processMessageList(list);
				if (!Thread.currentThread().isInterrupted()) {
					Thread.sleep(period);
				}
			}
		} catch (InterruptedException e) {
			// Ignore.
		} finally {
			//�˳��߳�ǰ����������.
			clean();
		}
	}

	/**
	 * ������Ϣ������.
	 */
	protected abstract void processMessageList(List<?> messageList);

	/**
	 * �˳�������.
	 */
	protected abstract void clean();
}
