package cn.com.sinosoft.ebusiness.sale.service.facade;

import java.util.List;
import java.util.Map;

public interface SmsSendService {
	
	/**
	 * �����ֻ�����
	 * @param isUseTemplate �Ƿ�ʹ��ϵͳ���ö���ģ��
	 * @param businessType ҵ������
	 * @param id ��ʾid��1��2��3��4��5...��
	 * @param phoneNo �ֻ���
	 * @param msgComment ����
	 * @param sender ������(9005)
	 * @param planTime
	 * @return
	 */
	public Map<String, String> smsSend(boolean isUseTemplate, String businessType, List<String> params, String id, String phoneNo, String msgComment, String sender, String planTime);
}
