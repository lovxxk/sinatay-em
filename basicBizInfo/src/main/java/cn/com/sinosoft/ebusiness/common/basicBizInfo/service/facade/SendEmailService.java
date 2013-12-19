package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade;

import java.util.Map;

public interface SendEmailService {
	
	/**
	 * 注册激活邮件
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public boolean sendActivateRegistEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * 密码重置邮件
	 * @param map
	 * @return
	 */
	public boolean sendResetPasswordEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * 承保邮件
	 * @param map
	 * @return
	 */
	public boolean sendInsureSuccessEmail(String title,Map<String,String> map);
	
	/**
	 * 支付邮件
	 * @param map
	 * @return
	 */
	public boolean sendOrderPayEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * 完善订单邮件
	 * @param map
	 * @return
	 */
	public boolean sendOrderCompleteEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * 保障到期邮件
	 * @param map
	 * @return
	 */
	public boolean sendRenewalEmail(String title,Map<String,String> map) throws Exception;
}
