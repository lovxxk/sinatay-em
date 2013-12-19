package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageConfigRelate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GePageConfigRelateService;

public class GePageConfigRelateServiceSpringImpl extends
		GenericDaoHibernate<GePageConfigRelate, String> implements
		GePageConfigRelateService {
	
	/***
	 * 根据页面代码，查询该页面关联的元素配置
	 * @param pageCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GePageConfigRelate> findGePageConfigByPageCode(String pageCode) {
		StringBuffer hql = new StringBuffer("from GePageConfigRelate pageConfigRelate where 1 = 1 ");
		hql.append("and pageConfigRelate.gePageConfig.pageCode = ? ");
		hql.append("order by pageConfigRelate.defaultSeqIndex");
		List<GePageConfigRelate> pageConfigRelate = super.findByHql(hql.toString(), new Object[]{pageCode});
		return pageConfigRelate;
	}
	
	public List<GePageConfigRelate> getEneitys(QueryRule q) {
		// TODO Auto-generated method stub
		return super.find(q);
	}
}
