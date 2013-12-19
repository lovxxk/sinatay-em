package cn.com.sinosoft.businessModule.bankAccount.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.bankAccount.domain.FundsTransfer;
import cn.com.sinosoft.businessModule.enums.dictionary.FundsTransferStatus;

public interface FundsTransferService {

	/***
	 * 添加资金划拨
	 * @param frundsTransfer
	 */
	public abstract void addFundsTransfer(FundsTransfer frundsTransfer);

	/***
	 * 分页查询资金划拨信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findFundsTransferByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * 根据属性查询资金划拨信息
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public abstract FundsTransfer findFundsTransferByQueryMap(Map propertyMap);
	
	/***
	 * 根据主键查询资金划拨信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public abstract FundsTransfer findFundsTransferBySerialNo(String serialNo);
	
	/**
	 * 根据资金划拨订单号查询资金划拨信息
	 * @param fundsTransferOrderNumber
	 * @return
	 */
	public abstract FundsTransfer findFundsTransferByfundsTransferOrderNumber(String fundsTransferOrderNumber);
	
	/**
	 * 根据资金划拨订单号更新信息
	 * @param fundsTransfer 资金划拨信息
	 * @param copyProperties 需要拷贝的属性
	 */
	public abstract void updateFundsTransferByFundsTransferOrderNumber(
			FundsTransfer fundsTransfer, List<String> copyProperties);
	
	/**
	 * 批量更新请求核心处理资金划拨
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateAllRequestCoreHandlerTransfer(final List fundsTransferAll,
			final FundsTransferStatus fundsTransferStatus);

}