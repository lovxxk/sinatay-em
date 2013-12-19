package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeRiskConfig;

public interface GeRiskConfigService {

	/**
	 * 添加险种
	 * @param geRiskConfig
	 */
	public abstract void addGeRiskConfig(GeRiskConfig geRiskConfig);

	/**
	 * 根据条件查询险种
	 * @param queryRule
	 * @return
	 */
	public abstract List<GeRiskConfig> findGeRiskConfigByQueryRule(
			QueryRule queryRule);

	/**
	 * 分页形式查询险种
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findGeRiskConfig(QueryRule queryRule, int pageNo,
			int pageSize);
	
	/**
	 * 根据险种代码查询险种
	 * @param riskCode
	 * @return
	 */
	public abstract GeRiskConfig findGeRiskConfigByRiskCode(String riskCode);
	
	/**
	 * 更新险种
	 * @param geRiskConfig
	 */
	public abstract void updateGeRiskConfig(GeRiskConfig geRiskConfig);

}
