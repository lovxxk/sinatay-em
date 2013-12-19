package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceElement;

public interface PortalInterfaceElementService {

	/***
	 * 根据登录名查询接口元素节点信息
	 * @param loginName 登录名
	 * @return PortalInterfaceElement 接口元素节点信息
	 */
	public abstract PortalInterfaceElement findPortalInterfaceElementByElementName(
			String elementName);

	/**
	 * 根据查询对象获取Page对象的接口元素节点信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口元素节点信息列表的Page对象
	 */
	public abstract Page findPortalInterfaceElement(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 *添加接口元素节点信息
	 *@param portalInterfaceElement 接口元素节点信息
	 *@return
	 */
	public abstract void addPortalInterfaceElement(
			PortalInterfaceElement portalInterfaceElement);

	/***
	 * 更新接口元素节点信息
	 * @param portalInterfaceElement 接口元素节点信息
	 * @return
	 */
	public abstract void updatePortalInterfaceElement(
			PortalInterfaceElement portalInterfaceElement);

	/***
	 * 根据根据节点名称删除节点信息
	 * @param loginName 登录名
	 * @return
	 */
	public abstract void deletePortalInterfaceElementByElementName(
			String elementName);

}