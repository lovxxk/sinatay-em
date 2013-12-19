package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;

public interface ClientUserService {

	public abstract ClientUser findClientUserByLoginName(String loginName);

	public abstract void addClientUser(ClientUser clientUser);

	public abstract void updateClientUser(ClientUser clientUser);

	public abstract void clearCacheByExternalSysInfo(ClientUser clientUser);

	public abstract void deleteClientUser(ClientUser clientUser);

	public abstract void clearAllCache();

	public abstract Page findClientUser(QueryRule queryRule, int pageNo, int pageSize);
	
	List<ClientUser> findClientUser(QueryRule queryRule);

}
