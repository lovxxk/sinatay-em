package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.InterfaceInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

/***
 * �ӿ���Ϣ������
 *  
 * @version 1 created on 2011��8��26������12:49:14
 */
public class InterfaceInfoServiceSpringImpl extends GenericDaoHibernate<InterfaceInfo, String> implements InterfaceInfoService,PortalService {

	/***
	 * 
	 * ͨ�����״����ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @return �ӿ���Ϣ
	 * 
	 */
	public InterfaceInfo findInterfaceInfoByTransCode(String transCode) {

		InterfaceInfo interfaceInfo = interfaceInfoMap.get(transCode);

		if (interfaceInfo == null) {
			interfaceInfo = super.findUnique("transCode", transCode);
			if (interfaceInfo == null) {
				System.out.println("���״��룺" + transCode + "����Ӧ�Ľӿ���Ϣ�����ڣ�");
				return null;
			} else {
				interfaceInfoMap.put(transCode, interfaceInfo);
			}

		}
		return interfaceInfo;
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ���Ϣ�б��Page����
	 */
	public Page findInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ӿ���Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}

	/***
	 * ��ӽӿ���Ϣ
	 * @param interfaceInfo �ӿ���Ϣ
	 * @return
	 */
	public void addInterfaceInfo(InterfaceInfo interfaceInfo) {
			logger.debug("���ӽӿ���Ϣ�����״��룺" + interfaceInfo.getTransCode());
			super.save(interfaceInfo);
	}
	
	/***
	 * ���½ӿ���Ϣ
	 * @param interfaceInfo �ӿ���Ϣ
	 * @return
	 */
	public void updateInterfaceInfo(InterfaceInfo interfaceInfo) {
		logger.debug("���½ӿ���Ϣ�����״��룺" + interfaceInfo.getTransCode());
		try {
			InterfaceInfo update = super.findUnique("transCode", interfaceInfo.getTransCode());
			BeanUtils.copyProperties(interfaceInfo, update, new String[]{"transCode"});
			super.save(update);
			clearCacheByInterfaceInfo(interfaceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/****
	 * ͨ�����״���ɾ���ӿ���Ϣ
	 * @param transCode ���״���
	 * @return
	 */
	public void deleteInterfaceInfoByTransCode(String transCode) {
		interfaceInfoMap.remove(transCode);
		stubClassMap.remove(transCode);
		stubSendClassMap.remove(transCode);
		super.deleteByPK(transCode);
	}
	
	/***
	 * ���ݽӿڶ���������� 
	 * @param interfaceInfo
	 * @return
	 */
	public void clearCacheByInterfaceInfo(InterfaceInfo interfaceInfo) {
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
