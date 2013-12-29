package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactorValue;

@SuppressWarnings("rawtypes")
public interface PortalInterfaceRuleFactorValueService {
	
	/***
	 * 添加接口交互规则信息
	 * 
	 * @param PortalInterfaceRuleFactorValue
	 *  接口交互规则信息和接口关系对象
	 * @return
	 */
	public abstract void addPortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue);

	/**
	 * 根据查询对象获取Page对象的接口交互规则信息和接口关系列表
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口交互系统信息和接口关系列表的Page对象
	 */
	public abstract Page findPortalInterfaceRuleFactorValue(QueryRule queryRule, int pageNo, int pageSize);
	
	public abstract PortalInterfaceRuleFactorValue findPortalInterfaceRuleFactorValueBySerialNo(String serialNo);
	
	public abstract List<PortalInterfaceRuleFactorValue> findPortalInterfaceRuleFactorValueByQueryRule(QueryRule queryRule);

	public abstract void deletePortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue);

	/***
	 * 更新接口交互规则信息和接口关系信息
	 * 
	 * @param 更新接口交互规则信息和接口关系信息
	 */
	public abstract void updatePortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue);
}
