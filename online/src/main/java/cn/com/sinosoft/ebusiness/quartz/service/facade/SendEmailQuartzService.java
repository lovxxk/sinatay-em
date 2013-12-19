package cn.com.sinosoft.ebusiness.quartz.service.facade;

public interface SendEmailQuartzService {
	
	/**
	 * 定时扫核保成功未支付的订单信息，给投保人发送订单支付邮件
	 */
	public abstract void sendOrderPayEmail();
	
	/**
	 * 定时扫待完善的订单信息，给投保人发送完善订单邮件
	 */
	public abstract void sendOrderCompleteEmail();
}
