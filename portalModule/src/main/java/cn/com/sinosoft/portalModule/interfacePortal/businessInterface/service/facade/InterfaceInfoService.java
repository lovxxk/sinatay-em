package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

public interface InterfaceInfoService {

	public abstract InterfaceInfo findInterfaceInfoByTransCode(String transCode);
	
	public abstract void addInterfaceInfo(InterfaceInfo interfaceInfo);

	public abstract void updateInterfaceInfo(InterfaceInfo interfaceInfo);

	public abstract void clearAllCache();

	public abstract void clearCacheByInterfaceInfo(InterfaceInfo interfaceInfo);

	public abstract void deleteInterfaceInfoByTransCode(String transCode);

	public abstract Page findInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize);

}
