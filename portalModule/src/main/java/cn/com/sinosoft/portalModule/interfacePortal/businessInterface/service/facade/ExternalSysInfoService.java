package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysInfo;

public interface ExternalSysInfoService {

	public abstract ExternalSysInfo findExternalSysInfoByExternalSysID(String externalSysID);

	public abstract void addExternalSysInfo(ExternalSysInfo externalSysInfo);

	public abstract void updateExternalSysInfo(ExternalSysInfo externalSysInfo);

	public abstract void deleteExternalSysInfo(String externalSysId);

	public abstract void clearAllCache();

	public abstract Page findExternalSysInfo(QueryRule queryRule, int pageNo, int pageSize);

}
