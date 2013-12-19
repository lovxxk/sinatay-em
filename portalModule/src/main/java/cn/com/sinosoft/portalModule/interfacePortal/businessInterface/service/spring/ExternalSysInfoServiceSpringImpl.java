package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSysInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSystemsUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

/****
 * 外部系统信息对象服务类
 * 
 *  
 * @version 1 created on 2011年8月26日下午12:00:10
 */
public class ExternalSysInfoServiceSpringImpl extends GenericDaoHibernate<ExternalSysInfo, String> implements ExternalSysInfoService, PortalService {

	/***
	 * 
	 * 通过外部系统ID查询外部系统信息
	 * @param externalSysID  外部系统Id
	 * @return ExternalSysInfo 外部系统信息对象
	 * 
	 */
	public ExternalSysInfo findExternalSysInfoByExternalSysID(String externalSysID) {
		return  super.get(externalSysID);
	}
	
	/**
	 * 根据查询对象获取Page对象的外部系统信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含外部系统信息列表的Page对象
	 */
	public Page findExternalSysInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取外部系统信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 
	 * 添加外部系统信息
	 * @param externalSysInfo 外部系统信息对象
	 * @return
	 * 
	 */
	public void addExternalSysInfo(ExternalSysInfo externalSysInfo) {
		super.save(externalSysInfo);
	}
	
	/***
	 * 
	 * 更新外部系统信息
	 * @param externalSysInfo 外部系统信息对象
	 * @return
	 * 
	 */
	public void updateExternalSysInfo(ExternalSysInfo externalSysInfo) {
		ExternalSysInfo update = super.get(externalSysInfo.getExternalSysId());
		BeanUtils.copyProperties(externalSysInfo, update, new String[]{"externalSysId"});
		super.update(update);
		clearCacheByExternalSysInfo(externalSysInfo);
	}
	
	/***
	 * 
	 * 通过外部系统ID删除外部系统信息对象
	 * @param externalSysId 外部系统Id
	 * @return
	 * 
	 */
	public void deleteExternalSysInfo(String externalSysId) {
		ExternalSysInfo externalSysInfo = super.get(externalSysId);
		if (externalSysInfo != null) {
			clearCacheByExternalSysInfo(externalSysInfo);
			super.deleteByPK(externalSysId);
		}
		
	}
	
	/***
	 * 
	 * 根据外部系统对象清除缓存
	 * @param 外部系统信息对象
	 * @return
	 * 
	 */
	public void clearCacheByExternalSysInfo(ExternalSysInfo externalSysInfo) {
		List<ClientUser> clientUserList = externalSysInfo.getClientUsers();

		for (ClientUser clientUser : clientUserList) {
			List<InterfaceInfo> interfaceInfoList = clientUser.getInterfaceInfos();
			for (InterfaceInfo interfaceInfo : interfaceInfoList) {
				if (interfaceInfo != null) {
					interfaceInfoMap.remove(interfaceInfo.getTransCode());
					stubClassMap.remove(interfaceInfo.getTransCode());
					stubSendClassMap.remove(interfaceInfo.getTransCode());
				}
			}
		}

		List<ExternalSystemsUser> externalSystemsUserList = externalSysInfo.getExternalSystemsUsers();
		for (ExternalSystemsUser externalSystemsUser : externalSystemsUserList) {
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
