package cn.com.sinosoft.ebusiness.quartz.service.facade;

public interface SendEmailQuartzService {
	
	/**
	 * ��ʱɨ�˱��ɹ�δ֧���Ķ�����Ϣ����Ͷ���˷��Ͷ���֧���ʼ�
	 */
	public abstract void sendOrderPayEmail();
	
	/**
	 * ��ʱɨ�����ƵĶ�����Ϣ����Ͷ���˷������ƶ����ʼ�
	 */
	public abstract void sendOrderCompleteEmail();
}
