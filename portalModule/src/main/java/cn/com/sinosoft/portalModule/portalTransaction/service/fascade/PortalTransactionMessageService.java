package cn.com.sinosoft.portalModule.portalTransaction.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransactionMessage;

public interface PortalTransactionMessageService {

	/**
	 * 根据查询对象获取Page对象的交易报文信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易报文信息列表的Page对象
	 */
	public abstract Page findPortalTransactionMessage(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * 增加交易报文信息
	 * @param portalTransactionMessage 交易报文信息
	 * @return
	 */
	public abstract void addPortalTransactionMessage(
			PortalTransactionMessage portalTransactionMessage);

	/***
	 * 删除交易报文信息
	 * @param 更新交易报文信息
	 */
	public abstract void deletePortalTransactionMessage(
			PortalTransactionMessage portalTransactionMessage);

	public abstract List<PortalTransactionMessage> findPortalTransactionMessage(
			QueryRule queryRule);

}