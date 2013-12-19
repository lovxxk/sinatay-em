package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeFlowConfigService;

public class GeFlowConfigServiceSpringImpl extends
		GenericDaoHibernate<GeFlowConfig, String> implements
		GeFlowConfigService {
	
	/**
	 * 
	 * �������̴����ѯ������Ϣ
	 * @param flowCode
	 * @return
	 * 
	 */
	public GeFlowConfig findGeFlowConfigByFlowCode(String flowCode) {
		try{
			return super.get(flowCode);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
