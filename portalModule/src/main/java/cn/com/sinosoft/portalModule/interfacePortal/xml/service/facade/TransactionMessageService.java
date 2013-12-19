package cn.com.sinosoft.portalModule.interfacePortal.xml.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransactionMessage;

public interface TransactionMessageService {

	public abstract void addTransactionMessage(TransactionMessage transactionMessage);

	public abstract void updateTransactionMessage(TransactionMessage transactionMessage);

	public abstract TransactionMessage findTransactionMessageByTransRefGuid(String transRefGuid);

	public abstract Page findTransactionMessage(QueryRule queryRule, int pageNo,
			int pageSize);

}
