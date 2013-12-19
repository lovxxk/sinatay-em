package cn.com.sinosoft.portalModule.portalTransaction.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.portalModule.portalTransaction.domain.PortalTransactionMessage;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionMessageService;

public class PortalTransactionMessageServiceSpringImpl extends GenericDaoHibernate<PortalTransactionMessage, String> implements PortalTransactionMessageService {
	
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľ��ױ�����Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �������ױ�����Ϣ�б��Page����
	 */
	public Page findPortalTransactionMessage(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ���ױ�����Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * ���ӽ��ױ�����Ϣ
	 * @param portalTransactionMessage ���ױ�����Ϣ
	 * @return
	 */
	public void addPortalTransactionMessage(PortalTransactionMessage portalTransactionMessage) {
		super.save(portalTransactionMessage);
	}
	
	
	/***
	 * ɾ�����ױ�����Ϣ
	 * @param ���½��ױ�����Ϣ
	 */
	public void deletePortalTransactionMessage(PortalTransactionMessage portalTransactionMessage) {
		super.delete(portalTransactionMessage);
		
	}
	


	public List<PortalTransactionMessage> findPortalTransactionMessage(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	
}
