package cn.com.sinosoft.portalModule.portalTransaction.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransaction;

public interface PortalTransactionService {

	/**
	 * 根据查询对象获取Page对象的交易信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易信息列表的Page对象
	 */
	public abstract Page findPortalTransaction(QueryRule queryRule, int pageNo,
			int pageSize);

	/***
	 * 增加交易信息
	 * @param portalTransaction 交易信息
	 * @return
	 */
	public abstract void addPortalTransaction(
			PortalTransaction portalTransaction);

	/***
	 * 删除交易信息
	 * @param 更新交易信息
	 */
	public abstract void deletePortalTransaction(
			PortalTransaction portalTransaction);

	public abstract List<PortalTransaction> findPortalTransaction(
			QueryRule queryRule);

	public  void insertAndUpdatePortalTransaction(String systemName,
			String transCode, String xmlBody, String transRefGUID, String localOrderId,
			String merchantOrderId, MessageType messageType, RequestProcessStatus processStatus);

}