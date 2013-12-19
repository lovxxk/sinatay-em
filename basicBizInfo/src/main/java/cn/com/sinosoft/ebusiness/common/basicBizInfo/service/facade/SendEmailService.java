package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade;

import java.util.Map;

public interface SendEmailService {
	
	/**
	 * ע�ἤ���ʼ�
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public boolean sendActivateRegistEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * ���������ʼ�
	 * @param map
	 * @return
	 */
	public boolean sendResetPasswordEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * �б��ʼ�
	 * @param map
	 * @return
	 */
	public boolean sendInsureSuccessEmail(String title,Map<String,String> map);
	
	/**
	 * ֧���ʼ�
	 * @param map
	 * @return
	 */
	public boolean sendOrderPayEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * ���ƶ����ʼ�
	 * @param map
	 * @return
	 */
	public boolean sendOrderCompleteEmail(String title,Map<String,String> map) throws Exception;
	
	/**
	 * ���ϵ����ʼ�
	 * @param map
	 * @return
	 */
	public boolean sendRenewalEmail(String title,Map<String,String> map) throws Exception;
}
