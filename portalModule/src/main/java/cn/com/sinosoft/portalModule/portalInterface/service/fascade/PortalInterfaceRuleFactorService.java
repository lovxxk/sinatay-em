package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.portalModule.enumUtil.SaveMessageType;
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
	
	/**
	 * 根据functionFlag、source查找当前接口规则处理类
	 * @param functionFlag
	 * @param source
	 * @return 处理类完整类名集合
	 */
	public abstract List<String> findPortalInterfaceRuleFactorProcessClass(String transCode, String systemCode);
	
	/**
	 * 根据functionFlag、source查找当前接口规则校验器
	 * @param functionFlag
	 * @param source
	 * @return 校验器完整类名集合
	 */
	public abstract List<String> findPortalInterfaceRuleFactorVerificationProcessClass(String transCode, String systemCode);
	
	/**
	 * 根据functionFlag、source查找当前接口规则报文存储类型
	 * @param transCode
	 * @param systemCode
	 * @return 报文存储类型枚举对象（1-数据库;2-文件;3-数据库and文件）
	 */
	public abstract SaveMessageType findPortalInterfaceRuleFactorSaveMessageType(String transCode, String systemCode);

	/***
	 * 更新接口交互规则信息和接口关系信息
	 * 
	 * @param 更新接口交互规则信息和接口关系信息
	 */
	public abstract void updatePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);
}
