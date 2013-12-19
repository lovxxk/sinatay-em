package cn.com.sinosoft.portalModule.portalTransaction.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.TransactionTrack;

public interface TransactionTrackService {

	/**
	 * 根据查询对象获取Page对象的交易轨迹信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易轨迹信息列表的Page对象
	 */
	public abstract Page findTransactionTrack(QueryRule queryRule, int pageNo,
			int pageSize);

	/***
	 * 增加交易轨迹信息
	 * @param TransactionTrack 交易轨迹信息
	 * @return
	 */
	public abstract void addTransactionTrack(TransactionTrack TransactionTrack);

	/***
	 * 删除交易轨迹信息
	 * @param 更新交易轨迹信息
	 */
	public abstract void deleteTransactionTrack(
			TransactionTrack TransactionTrack);

	public abstract List<TransactionTrack> findTransactionTrack(
			QueryRule queryRule);

}