package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlow;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeWebFlowService;

public class GeWebFlowServiceSpringImpl extends
		GenericDaoHibernate<GeWebFlow, String> implements GeWebFlowService {

	public void createWebFlow(GeWebFlow geWebFlow) {
		try{
			super.save(geWebFlow);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
