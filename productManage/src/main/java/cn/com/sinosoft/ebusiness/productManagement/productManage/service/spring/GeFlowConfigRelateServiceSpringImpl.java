package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeFlowConfigRelate;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeFlowConfigRelateService;

public class GeFlowConfigRelateServiceSpringImpl extends
		GenericDaoHibernate<GeFlowConfigRelate, String> implements
		GeFlowConfigRelateService {
	
	/***
	 * �������̴��룬��ѯ�����̹�����ҳ��
	 * @param flowCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GeFlowConfigRelate> findGePageConfigByFlowCode(String flowCode) {
		StringBuffer hql = new StringBuffer("from GeFlowConfigRelate flowConfigRelate where 1 = 1 ");
		hql.append("and flowConfigRelate.geFlowConfig.pageCode = ? ");
		hql.append("order by flowConfigRelate.defaultSeqIndex");
		List<GeFlowConfigRelate> flowConfigRelate = super.findByHql(hql.toString(), new Object[]{flowCode});
		return flowConfigRelate;
	}
}
