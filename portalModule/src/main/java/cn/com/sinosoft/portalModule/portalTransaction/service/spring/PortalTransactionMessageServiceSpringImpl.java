package cn.com.sinosoft.portalModule.portalTransaction.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransactionMessage;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionMessageService;

public class PortalTransactionMessageServiceSpringImpl extends GenericDaoHibernate<PortalTransactionMessage, String> implements PortalTransactionMessageService {
	
	
	/**
	 * 根据查询对象获取Page对象的交易报文信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含交易报文信息列表的Page对象
	 */
	public Page findPortalTransactionMessage(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取交易报文信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 增加交易报文信息
	 * @param portalTransactionMessage 交易报文信息
	 * @return
	 */
	public void addPortalTransactionMessage(PortalTransactionMessage portalTransactionMessage) {
		super.save(portalTransactionMessage);
	}
	
	
	/***
	 * 删除交易报文信息
	 * @param 更新交易报文信息
	 */
	public void deletePortalTransactionMessage(PortalTransactionMessage portalTransactionMessage) {
		super.delete(portalTransactionMessage);
		
	}
	


	public List<PortalTransactionMessage> findPortalTransactionMessage(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	
}
