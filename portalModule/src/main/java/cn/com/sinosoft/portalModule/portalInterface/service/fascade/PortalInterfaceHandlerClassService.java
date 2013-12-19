package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceHandlerClass;

public interface PortalInterfaceHandlerClassService {

	/***
	 * 根据类名查询接口处理类
	 * @param className 类名
	 * @return PortalInterfaceHandlerClass 接口处理类
	 */
	public abstract PortalInterfaceHandlerClass findPortalInterfaceHandlerClassByClassName(
			String className);

	/**
	 * 根据查询对象获取Page对象的接口处理类列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口处理类列表的Page对象
	 */
	public abstract Page findPortalInterfaceHandlerClass(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 *添加接口处理类
	 *@param PortalInterfaceHandlerClass 接口处理类
	 *@return
	 */
	public abstract void addPortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass);

	/***
	 * 更新接口处理类
	 * @param PortalInterfaceHandlerClass 接口处理类
	 * @return
	 */
	public abstract void updatePortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass);

	/***
	 * 根据根据节点名称删除节点信息
	 * @param className 类名
	 * @return
	 */
	public abstract void deletePortalInterfaceHandlerClassByElementName(
			String className);

}