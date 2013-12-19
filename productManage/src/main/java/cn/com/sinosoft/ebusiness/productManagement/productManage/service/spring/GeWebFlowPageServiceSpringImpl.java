package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPage;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeWebFlowPageService;

public class GeWebFlowPageServiceSpringImpl extends GenericDaoHibernate<GeWebFlowPage, String> implements GeWebFlowPageService {

	public List<GeWebFlowPage> getEneitys(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/***
	 * 根据产品代码查询于该产品关联的流程配置
	 * @param coreProductCode 产品代码
	 */
	@SuppressWarnings("unchecked")
	public List<GeWebFlowPage> findGeWebFlowPageCoreProductCode(String coreProductCode) {
		StringBuffer hql = new StringBuffer("from GeWebFlowPage webFlowPage where 1 = 1 ");
		hql.append("and webFlowPage.geProductMain.coreProductCode = ? ");
		hql.append("and webFlowPage.isShow = ? ");
		hql.append("order by webFlowPage.seqIndex");
		List<GeWebFlowPage> webFlowPageList = super.findByHql(hql.toString(), new Object[]{coreProductCode, "1"});
		return webFlowPageList;
	}
	
	/***
	 * 根据产品代码查询该产品下所有页面元素的配置交集
	 * @param coreProductCode
	 * @return
	 */
	public List<GePageElementConfig> findGePageElementConfigBycoreProductCode(String coreProductCode) {
		StringBuffer hql = new StringBuffer("select pec.elementCode, pec.elementName, pec.handleURL, wfpe.status, pec.defaultSeqIndex from GeWebFlowPage wfp ");
		hql.append("inner join wfp.geWebFlowPageElements wfpe ");
		hql.append("inner join wfpe.gePageElementConfig pec ");
		hql.append("where 1 = 1 ");
		hql.append("and wfp.geProductMain.coreProductCode = ? ");
		hql.append("group by pec.elementCode, pec.elementName, pec.handleURL, wfpe.status, pec.defaultSeqIndex ");
		hql.append("order by pec.defaultSeqIndex");
		List<GePageElementConfig> pageElementConfig = super.findByHql(hql.toString(), new Object[]{coreProductCode});
		return pageElementConfig;
	}
}

