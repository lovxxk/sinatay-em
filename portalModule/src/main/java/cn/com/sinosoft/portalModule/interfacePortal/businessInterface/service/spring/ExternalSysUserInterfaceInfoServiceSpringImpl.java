package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import org.springframework.beans.BeanUtils;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSysUserInterfaceInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

public class ExternalSysUserInterfaceInfoServiceSpringImpl extends GenericDaoHibernate<ExternalSysUserInterfaceInfo, String> implements ExternalSysUserInterfaceInfoService, PortalService {
	
	/***
	 * 添加外部系统用户和接口之间的关系
	 * @param externalSysUserInterfaceInfo 外部系统用户和接口关系对象
	 * @return
	 */
	public void addExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		super.save(externalSysUserInterfaceInfo);
	}
	
	/**
	 * 根据查询对象获取Page对象的外部系统用户和接口关系列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含外部系统用户和接口关系列表的Page对象
	 */
	public Page findExternalSysUserInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取外部系统用户和接口关系列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	public void deleteExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		if (externalSysUserInterfaceInfo != null) {
			clearCacheByExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
			super.delete(externalSysUserInterfaceInfo);
		}
	}
	
	/***
	 * 更新外部系统用户和接口关系信息
	 * @param 更新外部系统用户和接口关系信息
	 */
	public void updateExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		ExternalSysUserInterfaceInfo update = super.get(externalSysUserInterfaceInfo.getId());
		BeanUtils.copyProperties(externalSysUserInterfaceInfo, update, new String[]{"id"});
		super.update(update);
		clearCacheByExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
	}
	
	/***
	 * 根据外部系统用户和接口关系对象清除缓存
	 * @param externalSysUserInterfaceInfo
	 * @return
	 */
	public void clearCacheByExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo){
		
		InterfaceInfo interfaceInfo = externalSysUserInterfaceInfo.getInterfaceInfo();
		
		if (interfaceInfo != null) {
			interfaceInfoMap.remove(interfaceInfo.getTransCode());
			stubClassMap.remove(interfaceInfo.getTransCode());
			stubSendClassMap.remove(interfaceInfo.getTransCode());
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
}
