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
 * �ⲿϵͳ��Ϣ���������
 * 
 *  
 * @version 1 created on 2011��8��26������12:00:10
 */
public class ExternalSysInfoServiceSpringImpl extends GenericDaoHibernate<ExternalSysInfo, String> implements ExternalSysInfoService, PortalService {

	/***
	 * 
	 * ͨ���ⲿϵͳID��ѯ�ⲿϵͳ��Ϣ
	 * @param externalSysID  �ⲿϵͳId
	 * @return ExternalSysInfo �ⲿϵͳ��Ϣ����
	 * 
	 */
	public ExternalSysInfo findExternalSysInfoByExternalSysID(String externalSysID) {
		return  super.get(externalSysID);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ��Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ⲿϵͳ��Ϣ�б��Page����
	 */
	public Page findExternalSysInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ⲿϵͳ��Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 
	 * ����ⲿϵͳ��Ϣ
	 * @param externalSysInfo �ⲿϵͳ��Ϣ����
	 * @return
	 * 
	 */
	public void addExternalSysInfo(ExternalSysInfo externalSysInfo) {
		super.save(externalSysInfo);
	}
	
	/***
	 * 
	 * �����ⲿϵͳ��Ϣ
	 * @param externalSysInfo �ⲿϵͳ��Ϣ����
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
	 * ͨ���ⲿϵͳIDɾ���ⲿϵͳ��Ϣ����
	 * @param externalSysId �ⲿϵͳId
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
	 * �����ⲿϵͳ�����������
	 * @param �ⲿϵͳ��Ϣ����
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
	 * ������л���
	 *  
	 */
	public void clearAllCache() {
		interfaceInfoMap.clear();
		stubClassMap.clear();
		stubSendClassMap.clear();
	}
}
