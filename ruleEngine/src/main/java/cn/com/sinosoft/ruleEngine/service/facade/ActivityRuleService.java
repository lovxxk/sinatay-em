package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.ActivityInputBOM;
import cn.com.sinosoft.ruleEngine.domain.ActivityResultBOM;


public interface ActivityRuleService {
	/**
	 *  ��������ֵ������ص�����
	 * @param integralInfoList
	 * @return
	 */
	public List<ActivityResultBOM> getActivityInfo(List<ActivityInputBOM> activityInfoList);

}
