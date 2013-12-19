package cn.com.sinosoft.portalModule.portalTransaction.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.Date;
import java.util.List;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransaction;
import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransactionMessage;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionService;

public class PortalTransactionServiceSpringImpl extends GenericDaoHibernate<PortalTransaction, String> implements PortalTransactionService {
	
	
	/**
	 * 根据查询对象获取Page对象的交易信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易信息列表的Page对象
	 */
	public Page findPortalTransaction(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取交易信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 增加交易信息
	 * @param portalTransaction 交易信息
	 * @return
	 */
	public void addPortalTransaction(PortalTransaction portalTransaction) {
		super.save(portalTransaction);
	}
	
	
	/***
	 * 删除交易信息
	 * @param 更新交易信息
	 */
	public void deletePortalTransaction(PortalTransaction portalTransaction) {
		super.delete(portalTransaction);
		
	}
	


	public List<PortalTransaction> findPortalTransaction(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public void insertAndUpdatePortalTransaction(String systemName,
			String transCode, String xmlBody, String transRefGUID, String localOrderId,
			String merchantOrderId, MessageType messageType,
			RequestProcessStatus processStatus) {
		boolean isUpdate = false;
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("transSerialNumber", transRefGUID);
		List<PortalTransaction> transactionList =  findPortalTransaction(queryRule);
		PortalTransaction transaction =null;
		if(transactionList.size() == 0){
			transaction= new PortalTransaction();
			transaction.setTransCode(transCode);
			transaction.setTransSerialNumber(transRefGUID);
			transaction.setMerchantTransSerialNumber(transRefGUID);
			transaction.setOrderSerialNumber(localOrderId);
		}else{
			isUpdate = true;
			transaction = transactionList.get(0);
			transaction.setUpdateTime(new Date());
		}
		if(messageType==MessageType.FRONTREQUEST){
			transaction.setRequestTime(new Date());
		}
		if(messageType==MessageType.FRONTRESPONSE){
			transaction.setResponseTime(new Date());
		}
		transaction.setMerchantOrderNumber(merchantOrderId);
		transaction.setRequestProcessStatus(processStatus.getValue());
		transaction.setRequestProcessStatusName(processStatus.getDataCode());
		transaction.setRequestProcessStatusDesc(processStatus.getDataName());
		
		PortalTransactionMessage transMsg = new PortalTransactionMessage();
		transMsg.setSystemName(systemName);
		transMsg.setMessageType(messageType.getValue());
		transMsg.setTransactionMessage(xmlBody);
		transMsg.setRequestProcessStatus(processStatus.getValue());
		transMsg.setRequestProcessStatusName(processStatus.getDataCode());
		transMsg.setRequestProcessStatusDesc(processStatus.getDataName());
		transMsg.setPortalTransaction(transaction);
		transaction.getPortalTransactionMessages().add(transMsg);
		if(isUpdate){
			update(transaction);
		}else{
			save(transaction);
		}
	}
	
	
}
