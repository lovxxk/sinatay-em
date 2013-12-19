package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSystemsUser;

public interface ExternalSystemsUserService {

	public abstract ExternalSystemsUser findExternalSystemsUserByLoginName(String loginName);

	public abstract void addExternalSystemsUser(ExternalSystemsUser externalSystemsUser);

	public abstract void updateExternalSystemsUser(ExternalSystemsUser externalSystemsUser);

	public abstract void clearCacheByExternalSystemsUser(ExternalSystemsUser externalSystemsUser);

	public abstract void deleteExternalSystemsUserByLoginName(String loginName);

	public abstract void clearAllCache();

	public abstract Page findExternalSysUserInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize);

}
