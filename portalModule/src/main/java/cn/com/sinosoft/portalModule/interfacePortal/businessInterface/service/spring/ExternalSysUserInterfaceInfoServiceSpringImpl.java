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
	 * ����ⲿϵͳ�û��ͽӿ�֮��Ĺ�ϵ
	 * @param externalSysUserInterfaceInfo �ⲿϵͳ�û��ͽӿڹ�ϵ����
	 * @return
	 */
	public void addExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		super.save(externalSysUserInterfaceInfo);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ�û��ͽӿڹ�ϵ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ⲿϵͳ�û��ͽӿڹ�ϵ�б��Page����
	 */
	public Page findExternalSysUserInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ⲿϵͳ�û��ͽӿڹ�ϵ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	public void deleteExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		if (externalSysUserInterfaceInfo != null) {
			clearCacheByExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
			super.delete(externalSysUserInterfaceInfo);
		}
	}
	
	/***
	 * �����ⲿϵͳ�û��ͽӿڹ�ϵ��Ϣ
	 * @param �����ⲿϵͳ�û��ͽӿڹ�ϵ��Ϣ
	 */
	public void updateExternalSysUserInterfaceInfo(ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo) {
		ExternalSysUserInterfaceInfo update = super.get(externalSysUserInterfaceInfo.getId());
		BeanUtils.copyProperties(externalSysUserInterfaceInfo, update, new String[]{"id"});
		super.update(update);
		clearCacheByExternalSysUserInterfaceInfo(externalSysUserInterfaceInfo);
	}
	
	/***
	 * �����ⲿϵͳ�û��ͽӿڹ�ϵ�����������
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
	 * ������л���
	 */
	public void clearAllCache() {
		interfaceInfoMap.clear();
		stubClassMap.clear();
		stubSendClassMap.clear();
	}
}
