package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfacePayment;

@SuppressWarnings("rawtypes")
public interface PortalInterfacePaymentService {

	/***
	 * ��ӽӿڽ���֧����Ϣ
	 * 
	 * @param PortalInterfacePayment
	 *  �ӿڽ���֧����Ϣ�ͽӿڹ�ϵ����
	 * @return
	 */
	public abstract void addPortalInterfacePayment(PortalInterfacePayment portalInterfacePayment);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڽ���֧����Ϣ�ͽӿڹ�ϵ�б�
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڽ���ϵͳ��Ϣ�ͽӿڹ�ϵ�б��Page����
	 */
	public abstract Page findPortalInterfacePayment(QueryRule queryRule, int pageNo, int pageSize);

	public abstract PortalInterfacePayment findPortalInterfacePaymentBySerialNo(String serialNo);
	
	public abstract List<PortalInterfacePayment> findPortalInterfacePaymentByQueryRule(QueryRule queryRule);

	public abstract void deletePortalInterfacePayment(PortalInterfacePayment portalInterfacePayment);

	/***
	 * ���½ӿڽ���֧����Ϣ�ͽӿڹ�ϵ��Ϣ
	 * 
	 * @param ���½ӿڽ���֧����Ϣ�ͽӿڹ�ϵ��Ϣ
	 */
	public abstract void updatePortalInterfacePayment(PortalInterfacePayment portalInterfacePayment);

}
