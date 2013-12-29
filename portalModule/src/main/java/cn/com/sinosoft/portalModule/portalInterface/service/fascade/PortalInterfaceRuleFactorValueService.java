package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactorValue;

@SuppressWarnings("rawtypes")
public interface PortalInterfaceRuleFactorValueService {
	
	/***
	 * ��ӽӿڽ���������Ϣ
	 * 
	 * @param PortalInterfaceRuleFactorValue
	 *  �ӿڽ���������Ϣ�ͽӿڹ�ϵ����
	 * @return
	 */
	public abstract void addPortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڽ���������Ϣ�ͽӿڹ�ϵ�б�
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б��Page����
	 */
	public abstract Page findPortalInterfaceRuleFactorValue(QueryRule queryRule, int pageNo, int pageSize);
	
	public abstract PortalInterfaceRuleFactorValue findPortalInterfaceRuleFactorValueBySerialNo(String serialNo);
	
	public abstract List<PortalInterfaceRuleFactorValue> findPortalInterfaceRuleFactorValueByQueryRule(QueryRule queryRule);

	public abstract void deletePortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue);

	/***
	 * ���½ӿڽ���������Ϣ�ͽӿڹ�ϵ��Ϣ
	 * 
	 * @param ���½ӿڽ���������Ϣ�ͽӿڹ�ϵ��Ϣ
	 */
	public abstract void updatePortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue);
}
