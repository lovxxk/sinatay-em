package cn.com.sinosoft.portalModule.portalTransaction.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.TransactionTrack;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.TransactionTrackService;

public class TransactionTrackServiceSpringImpl extends GenericDaoHibernate<TransactionTrack, String> implements TransactionTrackService {
	
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľ��׹켣��Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �������׹켣��Ϣ�б��Page����
	 */
	public Page findTransactionTrack(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ���׹켣��Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * ���ӽ��׹켣��Ϣ
	 * @param transactionTrack ���׹켣��Ϣ
	 * @return
	 */
	public void addTransactionTrack(TransactionTrack transactionTrack) {
		super.save(transactionTrack);
	}
	
	
	/***
	 * ɾ�����׹켣��Ϣ
	 * @param ���½��׹켣��Ϣ
	 */
	public void deleteTransactionTrack(TransactionTrack transactionTrack) {
		super.delete(transactionTrack);
		
	}
	


	public List<TransactionTrack> findTransactionTrack(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
}
