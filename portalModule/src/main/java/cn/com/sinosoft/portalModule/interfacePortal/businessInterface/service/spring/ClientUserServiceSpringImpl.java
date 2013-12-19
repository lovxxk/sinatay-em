package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ClientUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

public class ClientUserServiceSpringImpl extends GenericDaoHibernate<ClientUser, String> implements ClientUserService, PortalService {
	
	/***
	 * ���ݵ�¼����ѯ�ͻ����û���Ϣ
	 * @param loginName ��¼��
	 * @return �ͻ����û�
	 */
	public ClientUser findClientUserByLoginName(String loginName) {
		return super.findUnique("loginName", loginName);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ŀͻ����û���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ͻ����û���Ϣ�б��Page����
	 */
	public Page findClientUser(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ͻ����û���Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * ���ӿͻ����û�
	 * @param clientUser �ͻ����û�
	 * @return
	 */
	public void addClientUser(ClientUser clientUser) {
		super.save(clientUser);
	}
	
	/***
	 * ���¿ͻ����û���Ϣ
	 * @param ���¿ͻ����û���Ϣ
	 */
	public void updateClientUser(ClientUser clientUser) {
		ClientUser update = super.get(clientUser.getId());
		BeanUtils.copyProperties(clientUser, update, new String[]{"id"});
		super.update(update);
		clearCacheByExternalSysInfo(clientUser);
	}
	
	/***
	 * ɾ���ͻ����û���Ϣ
	 * @param ���¿ͻ����û���Ϣ
	 */
	public void deleteClientUser(ClientUser clientUser) {
		 clearCacheByExternalSysInfo(clientUser);
		super.delete(clientUser);
		
	}
	
	/***
	 * 
	 * ���ݿͻ����û��������
	 * @param �ͻ����û�����
	 * @return
	 * 
	 */
	public void clearCacheByExternalSysInfo(ClientUser clientUser) {
		List<InterfaceInfo> interfaceInfoList = clientUser.getInterfaceInfos();
		for (InterfaceInfo interfaceInfo : interfaceInfoList) {
			if (interfaceInfo != null) {
				interfaceInfoMap.remove(interfaceInfo.getTransCode());
				stubClassMap.remove(interfaceInfo.getTransCode());
				stubSendClassMap.remove(interfaceInfo.getTransCode());
			}
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


	public List<ClientUser> findClientUser(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
	
}
