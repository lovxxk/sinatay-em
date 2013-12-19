package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;

/***
 * �ӿ���Ϣ������
 *  
 * @version 1 created on 2011��8��26������12:49:14
 */
public class PortalInterfaceServiceSpringImpl extends AbstractInterfaceService<PortalInterface, String> implements PortalInterfaceService {

	/***
	 * 
	 * ͨ�����״����ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @return �ӿ���Ϣ
	 * 
	 */
	@Override
	public PortalInterface findPortalInterfaceByTransCode(String transCode) {
		PortalInterface portalInterface = portalInterfaceMap.get(transCode);

		if (portalInterface == null) {
			portalInterface = super.findUnique("transCode", transCode);
			if (portalInterface == null) {
				return null;
			} else {
				portalInterfaceMap.put(transCode, portalInterface);
			}
		}
		return portalInterface;
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ���Ϣ�б��Page����
	 */
	@Override
	public Page findPortalInterface(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ӿ���Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}

	/***
	 * ��ӽӿ���Ϣ
	 * @param portalInterface �ӿ���Ϣ
	 * @return
	 */
	@Override
	public void addPortalInterface(PortalInterface portalInterface) {
			logger.debug("���ӽӿ���Ϣ�����״��룺" + portalInterface.getTransCode());
			super.save(portalInterface);
	}
	
	/***
	 * ���½ӿ���Ϣ
	 * @param portalInterface �ӿ���Ϣ
	 * @return
	 */
	@Override
	public void updatePortalInterface(PortalInterface portalInterface) {
		logger.debug("���½ӿ���Ϣ�����״��룺" + portalInterface.getTransCode());
		try {
			PortalInterface update = super.findUnique("transCode", portalInterface.getTransCode());
			BeanUtils.copyProperties(portalInterface, update, new String[]{"transCode"});
			super.save(update);
			clearPortalInterfaceCache(portalInterface);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/****
	 * ͨ�����״���ɾ���ӿ���Ϣ
	 * @param transCode ���״���
	 * @return
	 */
	@Override
	public void deletePortalInterfaceByTransCode(String transCode) {
		clearPortalInterfaceCacheByTransCode(transCode);
		super.deleteByPK(transCode);
	}
}
