package cn.com.sinosoft.ebusiness.sale.service.facade;

import java.util.List;
import java.util.Map;

public interface SmsSendService {
	
	/**
	 * 发送手机短信
	 * @param isUseTemplate 是否使用系统内置短信模板
	 * @param businessType 业务类型
	 * @param id 标示id（1、2、3、4、5...）
	 * @param phoneNo 手机号
	 * @param msgComment 内容
	 * @param sender 发送者(9005)
	 * @param planTime
	 * @return
	 */
	public Map<String, String> smsSend(boolean isUseTemplate, String businessType, List<String> params, String id, String phoneNo, String msgComment, String sender, String planTime);
}
