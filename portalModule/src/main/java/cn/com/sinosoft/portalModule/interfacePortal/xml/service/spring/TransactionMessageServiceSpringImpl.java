package cn.com.sinosoft.portalModule.interfacePortal.xml.service.spring;

import java.util.ArrayList;
import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import org.apache.axis2.AxisFault;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransactionMessage;
import cn.com.sinosoft.portalModule.interfacePortal.xml.service.facade.TransactionMessageService;

/***
 * 交易报文对象
 *  
 * @version 1 created on 2011年8月26日下午05:51:43
 */
@Service(value="transactionManager")
@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor={AxisFault.class, portalModuleException.class})
public class TransactionMessageServiceSpringImpl extends GenericDaoHibernate<TransactionMessage, String> implements TransactionMessageService {
	
	/***
	 * 通过交易流水号查询交易报文
	 * @param transRefGuid 交易流水号
	 * @return 交易对象
	 */
	public TransactionMessage findTransactionMessageByTransRefGuid(String transRefGuid){
		return super.findUnique("transRefGuid", transRefGuid);
	}
	
	/**
	 * 根据查询对象获取Page对象的交易报文对象列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易报文对象列表的Page对象
	 */
	public Page findTransactionMessage(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取交易报文对象列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 增加交易报文
	 * @param transactionMessage 交易报文对象
	 * @return
	 */
	public void addTransactionMessage(final TransactionMessage transactionMessage) {
		super.save(transactionMessage);
	}
	
	/***
	 * 更新交易报文
	 * @param transactionMessage 交易报文对象
	 * @return
	 */
	public void updateTransactionMessage(TransactionMessage transactionMessage) {
		logger.debug("更新报文记录，交易代码：" + transactionMessage.getTransRefGuid());
		TransactionMessage update = super.findUnique("transRefGuid", transactionMessage.getTransRefGuid());
		List<String> ignoreProperties = new ArrayList<String>();
		ignoreProperties.add("transRefGuid");
		ignoreProperties.add("externalSysInfo");
		ignoreProperties.add("transCode");
		ignoreProperties.add("transTime");
		ignoreProperties.add("requestTime");
		ignoreProperties.add("requestMessage");
		ignoreProperties.add("frontRequestTime");
		ignoreProperties.add("frontRequestMessage");
		if (update.getResponseTime() != null) {
			ignoreProperties.add("responseTime");
			ignoreProperties.add("responseMessage");
		}
		if (update.getFrontResponeTime() != null) {
			ignoreProperties.add("frontResponeTime");
			ignoreProperties.add("frontResponseMessage");
		}
		BeanUtils.copyProperties(transactionMessage, update, ignoreProperties.toArray(new String[ignoreProperties.size()]));
		super.update(update);
	}
	
}
