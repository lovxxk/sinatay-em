package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ClientUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

public class ClientUserServiceSpringImpl extends GenericDaoHibernate<ClientUser, String> implements ClientUserService, PortalService {
	
	/***
	 * 根据登录名查询客户端用户信息
	 * @param loginName 登录名
	 * @return 客户端用户
	 */
	public ClientUser findClientUserByLoginName(String loginName) {
		return super.findUnique("loginName", loginName);
	}
	
	/**
	 * 根据查询对象获取Page对象的客户端用户信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含客户端用户信息列表的Page对象
	 */
	public Page findClientUser(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取客户端用户信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 增加客户端用户
	 * @param clientUser 客户端用户
	 * @return
	 */
	public void addClientUser(ClientUser clientUser) {
		super.save(clientUser);
	}
	
	/***
	 * 更新客户端用户信息
	 * @param 更新客户端用户信息
	 */
	public void updateClientUser(ClientUser clientUser) {
		ClientUser update = super.get(clientUser.getId());
		BeanUtils.copyProperties(clientUser, update, new String[]{"id"});
		super.update(update);
		clearCacheByExternalSysInfo(clientUser);
	}
	
	/***
	 * 删除客户端用户信息
	 * @param 更新客户端用户信息
	 */
	public void deleteClientUser(ClientUser clientUser) {
		 clearCacheByExternalSysInfo(clientUser);
		super.delete(clientUser);
		
	}
	
	/***
	 * 
	 * 根据客户端用户清除缓存
	 * @param 客户端用户对象
	 * @return
	 * 
	 */
	public void clearCacheByExternalSysInfo(ClientUser clientUser) {
		List<InterfaceInfo> interfaceInfoList = clientUser.getInterfaceInfos();
		for (InterfaceInfo interfaceInfo : interfaceInfoList) {
			if (interfaceInfo != null) {
				interfaceInfoMap.remove(interfaceInfo.getTransCode());
				stubClassMap.remove(interfaceInfo.getTransCode());
				stubSendClassMap.remove(interfaceInfo.getTransCode());
			}
		}
	}
	
	/***
	 * 清除所有缓存
	 */
	public void clearAllCache() {
		interfaceInfoMap.clear();
		stubClassMap.clear();
		stubSendClassMap.clear();
	}


	public List<ClientUser> findClientUser(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
	
}
