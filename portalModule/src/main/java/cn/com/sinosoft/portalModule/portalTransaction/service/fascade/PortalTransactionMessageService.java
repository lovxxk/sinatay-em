package cn.com.sinosoft.portalModule.portalTransaction.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransactionMessage;

public interface PortalTransactionMessageService {

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľ��ױ�����Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �������ױ�����Ϣ�б��Page����
	 */
	public abstract Page findPortalTransactionMessage(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * ���ӽ��ױ�����Ϣ
	 * @param portalTransactionMessage ���ױ�����Ϣ
	 * @return
	 */
	public abstract void addPortalTransactionMessage(
			PortalTransactionMessage portalTransactionMessage);

	/***
	 * ɾ�����ױ�����Ϣ
	 * @param ���½��ױ�����Ϣ
	 */
	public abstract void deletePortalTransactionMessage(
			PortalTransactionMessage portalTransactionMessage);

	public abstract List<PortalTransactionMessage> findPortalTransactionMessage(
			QueryRule queryRule);

}