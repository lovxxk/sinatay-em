package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GePageElementConfigService;

public class GePageElementConfigServiceSpringImpl extends
		GenericDaoHibernate<GePageElementConfig, String> implements
		GePageElementConfigService {

	public List<GePageElementConfig> getEntitys(QueryRule q) {
		// TODO Auto-generated method stub
		return super.find(q);
	}
	
	
	public GePageElementConfig findGePageElementConfigByElementCode(String elementCode) {
		return super.get(elementCode);
	}
}
