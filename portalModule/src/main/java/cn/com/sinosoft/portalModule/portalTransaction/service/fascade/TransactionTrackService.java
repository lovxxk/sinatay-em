package cn.com.sinosoft.portalModule.portalTransaction.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.TransactionTrack;

public interface TransactionTrackService {

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľ��׹켣��Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �������׹켣��Ϣ�б��Page����
	 */
	public abstract Page findTransactionTrack(QueryRule queryRule, int pageNo,
			int pageSize);

	/***
	 * ���ӽ��׹켣��Ϣ
	 * @param TransactionTrack ���׹켣��Ϣ
	 * @return
	 */
	public abstract void addTransactionTrack(TransactionTrack TransactionTrack);

	/***
	 * ɾ�����׹켣��Ϣ
	 * @param ���½��׹켣��Ϣ
	 */
	public abstract void deleteTransactionTrack(
			TransactionTrack TransactionTrack);

	public abstract List<TransactionTrack> findTransactionTrack(
			QueryRule queryRule);

}