package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceAccount;

public interface PortalInterfaceAccountService {

	/***
	 * 根据登录名查询接口账号信息
	 * @param loginName 登录名
	 * @return 客户端用户
	 */
	public abstract PortalInterfaceAccount findPortalInterfaceAccountByLoginName(
			String loginName);

	/**
	 * 根据查询对象获取Page对象的接口账号信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口账号信息列表的Page对象
	 */
	public abstract Page findPortalInterfaceAccount(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * 增加客户端用户
	 * @param portalInterfaceAccount 客户端用户
	 * @return
	 */
	public abstract void addPortalInterfaceAccount(
			PortalInterfaceAccount portalInterfaceAccount);

	/***
	 * 更新接口账号信息
	 * @param 更新接口账号信息
	 */
	public abstract void updatePortalInterfaceAccount(
			PortalInterfaceAccount portalInterfaceAccount);

	/***
	 * 删除接口账号信息
	 * @param 更新接口账号信息
	 */
	public abstract void deletePortalInterfaceAccount(
			PortalInterfaceAccount portalInterfaceAccount);

	public abstract List<PortalInterfaceAccount> findPortalInterfaceAccount(
			QueryRule queryRule);

}