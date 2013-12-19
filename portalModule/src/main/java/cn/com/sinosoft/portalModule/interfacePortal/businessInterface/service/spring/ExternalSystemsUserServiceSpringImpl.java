package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSystemsUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSystemsUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

/***
 * 外部系统用户信息
 *  
 * @version 1 created on 2011年8月26日下午03:55:04
 */
public class ExternalSystemsUserServiceSpringImpl extends GenericDaoHibernate<ExternalSystemsUser, String> implements ExternalSystemsUserService, PortalService {

	/***
	 * 根据登录名查询外部系统用户信息
	 * @param loginName 登录名
	 * @return ExternalSystemsUser 外部系统用户信息
	 */
	public ExternalSystemsUser findExternalSystemsUserByLoginName(String loginName) {
		return  super.findUnique("loginName", loginName);
	}
	
	/**
	 * 根据查询对象获取Page对象的外部系统用户信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含外部系统用户信息列表的Page对象
	 */
	public Page findExternalSysUserInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取外部系统用户信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 *添加外部系统用户
	 *@param externalSystemsUser 外部系统用户
	 *@return
	 */
	public void addExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		super.save(externalSystemsUser);
	}
	
	/***
	 * 更新外部系统用户
	 * @param externalSystemsUser 外部系统用户
	 * @return
	 */
	public void updateExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		ExternalSystemsUser update = super.get(externalSystemsUser.getId());
		BeanUtils.copyProperties(externalSystemsUser, update, new String[]{"id"});
		super.update(update);
		clearCacheByExternalSystemsUser(update);
	}
	
	/***
	 * 根据登录名删除用户信息
	 * @param loginName 登录名
	 * @return
	 */
	public void deleteExternalSystemsUserByLoginName(String loginName){
		ExternalSystemsUser deleteExternalSystemsUser = this.findExternalSystemsUserByLoginName(loginName);
		if (deleteExternalSystemsUser != null) {
			clearCacheByExternalSystemsUser(deleteExternalSystemsUser);
			super.delete(deleteExternalSystemsUser);
		}
		
	}
	
	/***
	 * 根据外部系统用户删除缓存
	 * @param externalSystemsUser
	 * @return
	 */
	public void clearCacheByExternalSystemsUser(ExternalSystemsUser externalSystemsUser){
		List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfoList = externalSystemsUser.getExternalSysUserInterfaceInfos();
		for (ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo : externalSysUserInterfaceInfoList) {
			InterfaceInfo interfaceInfo = externalSysUserInterfaceInfo.getInterfaceInfo();
			if (interfaceInfo != null) {
				interfaceInfoMap.remove(interfaceInfo.getTransCode());
				stubClassMap.remove(interfaceInfo.getTransCode());
				stubSendClassMap.remove(interfaceInfo.getTransCode());
			}
			
		}
	}
	
	/***
	 * 
	 * 清除所有缓存
	 *  
	 */
	public void clearAllCache() {
		interfaceInfoMap.clear();
		stubClassMap.clear();
		stubSendClassMap.clear();
	}
	
	
}
