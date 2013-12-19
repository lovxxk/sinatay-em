package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeMonitorAppException;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.ExceptionService;

public class ExceptionServiceSpringImpl extends GenericDaoHibernate<GeMonitorAppException, String> implements ExceptionService {

	/**
	 * 查看异常信息
	 */
	public Page findException(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
	/**
	 * 根据id查询异常详细信息
	 */
	public GeMonitorAppException getExceptionDetil(String id){
		
		return super.get(GeMonitorAppException.class, id);
	}
}
