package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;

public interface ExternalSysUserInterfaceInfoService {

	public abstract void addExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo);

	public abstract void clearAllCache();

	public abstract void clearCacheByExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo);

	public abstract void deleteExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo);

	public abstract Page findExternalSysUserInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize);

	public void updateExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo);
}
