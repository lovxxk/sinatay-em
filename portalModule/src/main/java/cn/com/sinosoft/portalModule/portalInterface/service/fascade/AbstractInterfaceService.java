package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.dao.GenericDaoHibernate;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;

public abstract class AbstractInterfaceService<T extends Serializable, PK extends Serializable> extends GenericDaoHibernate <T,PK> implements PortalService {
	
	/**
	 * 根据接口信息清除缓存
	 * @param portalInterfaceSystem
	 */
	public void clearPortalInterfaceCache(PortalInterface interfaceInfo){
		
		if (interfaceInfo != null) {
			portalInterfaceMap.remove(interfaceInfo.getTransCode());
			stubClassMap.remove(interfaceInfo.getTransCode());
			stubSendClassMap.remove(interfaceInfo.getTransCode());
		}
		
	}


	/**
	 * 根据接口信息清除缓存
	 * @param portalInterfaceSystem
	 */
	public void clearPortalInterfaceCacheByTransCode(String transCode){
		
		if (StringUtils.isNotBlank(transCode)) {
			portalInterfaceMap.remove(transCode);
			stubClassMap.remove(transCode);
			stubSendClassMap.remove(transCode);
		}
		
	}
	
	/***
	 * 清除所有缓存
	 */
	public void clearAllCache() {
		portalInterfaceMap.clear();
		stubClassMap.clear();
		stubSendClassMap.clear();
	}
}
