package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactor;

@SuppressWarnings("rawtypes")
public interface PortalInterfaceRuleFactorService {
	
	/***
	 * 添加接口交互规则信息
	 * 
	 * @param PortalInterfaceRuleFactor
	 *  接口交互规则信息和接口关系对象
	 * @return
	 */
	public abstract void addPortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);

	/**
	 * 根据查询对象获取Page对象的接口交互规则信息和接口关系列表
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口交互系统信息和接口关系列表的Page对象
	 */
	public abstract Page findPortalInterfaceRuleFactor(QueryRule queryRule, int pageNo, int pageSize);

	public abstract PortalInterfaceRuleFactor findPortalInterfaceRuleFactorBySerialNo(String serialNo);
	
	public abstract List<PortalInterfaceRuleFactor> findPortalInterfaceRuleFactorByQueryRule(QueryRule queryRule);
	
	public abstract PortalInterfaceRuleFactor findPortalInterfaceRuleFactorByFactorCode(String factorCode);
	
	public abstract PortalInterfaceRuleFactor findPortalInterfaceRuleFactorByQueryMap(Map propertyMap);
	
	public abstract void deletePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);

	/***
	 * 更新接口交互规则信息和接口关系信息
	 * 
	 * @param 更新接口交互规则信息和接口关系信息
	 */
	public abstract void updatePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);
}
