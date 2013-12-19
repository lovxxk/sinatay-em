package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSystemsUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSystemsUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

/***
 * �ⲿϵͳ�û���Ϣ
 *  
 * @version 1 created on 2011��8��26������03:55:04
 */
public class ExternalSystemsUserServiceSpringImpl extends GenericDaoHibernate<ExternalSystemsUser, String> implements ExternalSystemsUserService, PortalService {

	/***
	 * ���ݵ�¼����ѯ�ⲿϵͳ�û���Ϣ
	 * @param loginName ��¼��
	 * @return ExternalSystemsUser �ⲿϵͳ�û���Ϣ
	 */
	public ExternalSystemsUser findExternalSystemsUserByLoginName(String loginName) {
		return  super.findUnique("loginName", loginName);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage������ⲿϵͳ�û���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ⲿϵͳ�û���Ϣ�б��Page����
	 */
	public Page findExternalSysUserInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ⲿϵͳ�û���Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 *����ⲿϵͳ�û�
	 *@param externalSystemsUser �ⲿϵͳ�û�
	 *@return
	 */
	public void addExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		super.save(externalSystemsUser);
	}
	
	/***
	 * �����ⲿϵͳ�û�
	 * @param externalSystemsUser �ⲿϵͳ�û�
	 * @return
	 */
	public void updateExternalSystemsUser(ExternalSystemsUser externalSystemsUser) {
		ExternalSystemsUser update = super.get(externalSystemsUser.getId());
		BeanUtils.copyProperties(externalSystemsUser, update, new String[]{"id"});
		super.update(update);
		clearCacheByExternalSystemsUser(update);
	}
	
	/***
	 * ���ݵ�¼��ɾ���û���Ϣ
	 * @param loginName ��¼��
	 * @return
	 */
	public void deleteExternalSystemsUserByLoginName(String loginName){
		ExternalSystemsUser deleteExternalSystemsUser = this.findExternalSystemsUserByLoginName(loginName);
		if (deleteExternalSystemsUser != null) {
			clearCacheByExternalSystemsUser(deleteExternalSystemsUser);
			super.delete(deleteExternalSystemsUser);
		}
		
	}
	
	/***
	 * �����ⲿϵͳ�û�ɾ������
	 * @param externalSystemsUser
	 * @return
	 */
	public void clearCacheByExternalSystemsUser(ExternalSystemsUser externalSystemsUser){
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
