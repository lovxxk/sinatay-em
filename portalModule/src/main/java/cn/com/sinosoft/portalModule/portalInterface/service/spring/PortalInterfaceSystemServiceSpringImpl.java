package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceSystemService;

public class PortalInterfaceSystemServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceSystem, String> implements PortalInterfaceSystemService {
	
	/***
	 * ��ӽӿڽ���ϵͳ��Ϣ
	 * @param portalInterfaceSystem �ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ����
	 * @return
	 */
	@Override
	public void addPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		super.save(portalInterfaceSystem);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б��Page����
	 */
	@Override
	public Page findPortalInterfaceSystem(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	@Override
	public void deletePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		if (portalInterfaceSystem != null) {
			clearPortalInterfaceCache(portalInterfaceSystem.getPortalInterface());
			super.delete(portalInterfaceSystem);
		}
	}
	
	/***
	 * ���½ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ��Ϣ
	 * @param ���½ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ��Ϣ
	 */
	@Override
	public void updatePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		PortalInterfaceSystem update = super.get(portalInterfaceSystem.getSerialNo());
		BeanUtils.copyProperties(portalInterfaceSystem, update, new String[]{"serialNo"});
		super.update(update);
		clearPortalInterfaceCache(portalInterfaceSystem.getPortalInterface());
	}
	
}
