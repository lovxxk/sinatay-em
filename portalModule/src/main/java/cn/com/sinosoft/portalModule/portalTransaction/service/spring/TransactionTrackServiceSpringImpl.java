package cn.com.sinosoft.portalModule.portalTransaction.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.TransactionTrack;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.TransactionTrackService;

public class TransactionTrackServiceSpringImpl extends GenericDaoHibernate<TransactionTrack, String> implements TransactionTrackService {
	
	
	/**
	 * 根据查询对象获取Page对象的交易轨迹信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易轨迹信息列表的Page对象
	 */
	public Page findTransactionTrack(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取交易轨迹信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 增加交易轨迹信息
	 * @param transactionTrack 交易轨迹信息
	 * @return
	 */
	public void addTransactionTrack(TransactionTrack transactionTrack) {
		super.save(transactionTrack);
	}
	
	
	/***
	 * 删除交易轨迹信息
	 * @param 更新交易轨迹信息
	 */
	public void deleteTransactionTrack(TransactionTrack transactionTrack) {
		super.delete(transactionTrack);
		
	}
	


	public List<TransactionTrack> findTransactionTrack(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
}
