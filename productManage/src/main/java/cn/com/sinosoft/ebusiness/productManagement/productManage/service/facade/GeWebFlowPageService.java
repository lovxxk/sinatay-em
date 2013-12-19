package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPage;


public interface GeWebFlowPageService {
	public List<GeWebFlowPage> getEneitys(QueryRule queryRule);

	public abstract List<GeWebFlowPage> findGeWebFlowPageCoreProductCode(String coreProductCode);

	public abstract List<GePageElementConfig> findGePageElementConfigBycoreProductCode(String coreProductCode);
	
	
}
