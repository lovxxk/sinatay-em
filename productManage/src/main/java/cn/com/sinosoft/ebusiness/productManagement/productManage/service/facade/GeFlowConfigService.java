package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfig;


public interface GeFlowConfigService {
	/**
	 * 根据流程代码查询流程信息
	 * @param flowCode
	 * @return
	 */
	public GeFlowConfig findGeFlowConfigByFlowCode(String flowCode);
	
}
