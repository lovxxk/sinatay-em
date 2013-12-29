package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;

public interface PortalInterfaceSystemService {

	/***
	 * 添加接口交互系统信息
	 * 
	 * @param portalInterfaceSystem
	 *            接口交互系统信息和接口关系对象
	 * @return
	 */
	public abstract void addPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem);

	/**
	 * 根据查询对象获取Page对象的接口交互系统信息和接口关系列表
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口交互系统信息和接口关系列表的Page对象
	 */
	public abstract Page findPortalInterfaceSystem(QueryRule queryRule, int pageNo, int pageSize);

	public abstract PortalInterfaceSystem findPortalInterfaceSystemBySerialNo(String serialNo);

	public abstract List<PortalInterfaceSystem> findPortalInterfaceSystemByQueryRule(QueryRule queryRule);

	public abstract void deletePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem);

	/***
	 * 更新接口交互系统信息和接口关系信息
	 * 
	 * @param 更新接口交互系统信息和接口关系信息
	 */
	public abstract void updatePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem);
	
	public abstract PortalInterfaceSystem findPortalInterfaceSystemBySystemCode(String systemCode);

}