package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.GeMonitorAppException;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
/**
 * 
 *  “Ï≥£≤È—Ø
 */
public interface ExceptionService {

	public Page findException(QueryRule queryRule,int pageNo,int pageSize);
	
	public GeMonitorAppException getExceptionDetil(String id);
	
}
