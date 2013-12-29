package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.portalModule.enumUtil.SaveMessageType;
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
	
	/**
	 * ����functionFlag��source���ҵ�ǰ�ӿڹ�������
	 * @param functionFlag
	 * @param source
	 * @return ������������������
	 */
	public abstract List<String> findPortalInterfaceRuleFactorProcessClass(String transCode, String systemCode);
	
	/**
	 * ����functionFlag��source���ҵ�ǰ�ӿڹ���У����
	 * @param functionFlag
	 * @param source
	 * @return У����������������
	 */
	public abstract List<String> findPortalInterfaceRuleFactorVerificationProcessClass(String transCode, String systemCode);
	
	/**
	 * ����functionFlag��source���ҵ�ǰ�ӿڹ����Ĵ洢����
	 * @param transCode
	 * @param systemCode
	 * @return ���Ĵ洢����ö�ٶ���1-���ݿ�;2-�ļ�;3-���ݿ�and�ļ���
	 */
	public abstract SaveMessageType findPortalInterfaceRuleFactorSaveMessageType(String transCode, String systemCode);

	/***
	 * ���½ӿڽ���������Ϣ�ͽӿڹ�ϵ��Ϣ
	 * 
	 * @param ���½ӿڽ���������Ϣ�ͽӿڹ�ϵ��Ϣ
	 */
	public abstract void updatePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor);
}
