package cn.com.sinosoft.util.queue;


/**
 * ���ü�ʱ������ȡQueue����Ϣ���Ե�Consumer.
 */
public abstract class BlockingConsumer extends QueueConsumer {

	/**
	 * �߳�ִ�к���,������ȡ��Ϣ������processMessage()���д���.
	 */
	public void run() {
		//ѭ��������ȡ��Ϣֱ���̱߳��ж�.
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Object message = queue.take();
				processMessage(message);
			}
		} catch (InterruptedException e) {
			// Ignore.
		} finally {
			//�˳��߳�ǰ����������.
			clean();
		}
	}

	/**
	 * ��Ϣ������.
	 */
	protected abstract void processMessage(Object message);

	/**
	 * �˳�������.
	 */
	protected abstract void clean();
}
