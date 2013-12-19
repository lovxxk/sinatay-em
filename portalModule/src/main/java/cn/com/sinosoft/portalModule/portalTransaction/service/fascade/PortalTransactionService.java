package cn.com.sinosoft.portalModule.portalTransaction.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransaction;

public interface PortalTransactionService {

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľ�����Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return ����������Ϣ�б��Page����
	 */
	public abstract Page findPortalTransaction(QueryRule queryRule, int pageNo,
			int pageSize);

	/***
	 * ���ӽ�����Ϣ
	 * @param portalTransaction ������Ϣ
	 * @return
	 */
	public abstract void addPortalTransaction(
			PortalTransaction portalTransaction);

	/***
	 * ɾ��������Ϣ
	 * @param ���½�����Ϣ
	 */
	public abstract void deletePortalTransaction(
			PortalTransaction portalTransaction);

	public abstract List<PortalTransaction> findPortalTransaction(
			QueryRule queryRule);

	public  void insertAndUpdatePortalTransaction(String systemName,
			String transCode, String xmlBody, String transRefGUID, String localOrderId,
			String merchantOrderId, MessageType messageType, RequestProcessStatus processStatus);

}