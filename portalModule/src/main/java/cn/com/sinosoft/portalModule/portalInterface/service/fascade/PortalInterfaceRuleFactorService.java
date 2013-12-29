package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactor;

@SuppressWarnings("rawtypes")
public interface PortalInterfaceRuleFactorService {
	
	/***
	 * ��ӽӿڽ���������Ϣ
	 * 
	 * @param PortalInterfaceRuleFactor
	 *  �ӿڽ���������Ϣ�ͽӿڹ�ϵ����
	 * @return
	 */
	public abstract void addPortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڽ���������Ϣ�ͽӿڹ�ϵ�б�
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б��Page����
	 */
	public abstract Page findPortalInterfaceRuleFactor(QueryRule queryRule, int pageNo, int pageSize);

	public abstract PortalInterfaceRuleFactor findPortalInterfaceRuleFactorBySerialNo(String serialNo);
	
	public abstract List<PortalInterfaceRuleFactor> findPortalInterfaceRuleFactorByQueryRule(QueryRule queryRule);
	
	public abstract PortalInterfaceRuleFactor findPortalInterfaceRuleFactorByFactorCode(String factorCode);
	
	public abstract PortalInterfaceRuleFactor findPortalInterfaceRuleFactorByQueryMap(Map propertyMap);
	
	public abstract void deletePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);

	/***
	 * ���½ӿڽ���������Ϣ�ͽӿڹ�ϵ��Ϣ
	 * 
	 * @param ���½ӿڽ���������Ϣ�ͽӿڹ�ϵ��Ϣ
	 */
	public abstract void updatePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);
}
