package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfig;


public interface GeFlowConfigService {
	/**
	 * �������̴����ѯ������Ϣ
	 * @param flowCode
	 * @return
	 */
	public GeFlowConfig findGeFlowConfigByFlowCode(String flowCode);
	
}
