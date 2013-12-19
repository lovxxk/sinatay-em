package cn.com.sinosoft.ebusiness.ali.config;

import java.util.Properties;

import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

/* *
 *������AlipayConfig
 *���ܣ�����������
 *��ϸ�������ʻ��й���Ϣ������·��
 *�汾��3.3
 *���ڣ�2012-08-10
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
	
 *��ʾ����λ�ȡ��ȫУ����ͺ��������ID
 *1.������ǩԼ֧�����˺ŵ�¼֧������վ(www.alipay.com)
 *2.������̼ҷ���(https://b.alipay.com/order/myOrder.htm)
 *3.�������ѯ���������(PID)��������ѯ��ȫУ����(Key)��

 *��ȫУ����鿴ʱ������֧�������ҳ��ʻ�ɫ��������ô�죿
 *���������
 *1�������������ã������������������������
 *2���������������ԣ����µ�¼��ѯ��
 */

public class AlipayConfig {
	
	//�����������������������������������Ļ�����Ϣ������������������������������
	// ���������ID����2088��ͷ��16λ��������ɵ��ַ���
	public static String partner;
	// �̻���˽Կ
	public static String key;
	
	
	public static String mobile_partner;
	
	public static String mobile_key;
	
	public static String mobile_seller_email;
	
	public static String mobile_token_service;
	
	public static String mobile_https_alipay_url;
	
	public static String mobile_notify_url;
	
	public static String mobile_call_back_url;
	
	public static String mobile_pay_service;
	
	public static String mobile_format;
	
	public static String mobile_v;

	//�����������������������������������Ļ�����Ϣ������������������������������
	

	// �����ã�����TXT��־�ļ���·��
	public static String log_path = "D:\\alipay.log";

	// �ַ������ʽ Ŀǰ֧�� gbk �� utf-8
	public static String input_charset = "GBK";
	
	// ǩ����ʽ �����޸�
	public static String sign_type = "MD5";
	
	static {
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		Properties properties = PropertyFileUtils.getProperties();
		
		partner = properties.getProperty("partner");
		key = properties.getProperty("key");
		
		mobile_partner = properties.getProperty("mobile_partner");
		mobile_key = properties.getProperty("mobile_key");
		mobile_seller_email = properties.getProperty("mobile_seller_email");
		mobile_token_service = properties.getProperty("mobile_token_service");
		mobile_https_alipay_url = properties.getProperty("mobile_https_alipay_url");
		mobile_notify_url = properties.getProperty("mobile_notify_url");
		mobile_call_back_url = properties.getProperty("mobile_call_back_url");
		mobile_pay_service = properties.getProperty("mobile_pay_service");
		mobile_format = properties.getProperty("mobile_format");
		mobile_v = properties.getProperty("mobile_v");
	}
	
}
