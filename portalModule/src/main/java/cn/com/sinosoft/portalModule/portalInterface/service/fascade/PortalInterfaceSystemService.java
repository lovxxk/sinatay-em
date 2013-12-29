package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;

public interface PortalInterfaceSystemService {

	/***
	 * ��ӽӿڽ���ϵͳ��Ϣ
	 * 
	 * @param portalInterfaceSystem
	 *            �ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ����
	 * @return
	 */
	public abstract void addPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б�
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б��Page����
	 */
	public abstract Page findPortalInterfaceSystem(QueryRule queryRule, int pageNo, int pageSize);

	public abstract PortalInterfaceSystem findPortalInterfaceSystemBySerialNo(String serialNo);

	public abstract List<PortalInterfaceSystem> findPortalInterfaceSystemByQueryRule(QueryRule queryRule);

	public abstract void deletePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem);

	/***
	 * ���½ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ��Ϣ
	 * 
	 * @param ���½ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ��Ϣ
	 */
	public abstract void updatePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem);
	
	public abstract PortalInterfaceSystem findPortalInterfaceSystemBySystemCode(String systemCode);

}