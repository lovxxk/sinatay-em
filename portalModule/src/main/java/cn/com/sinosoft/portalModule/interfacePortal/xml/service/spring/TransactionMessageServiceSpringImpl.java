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
 * ���ױ��Ķ���
 *  
 * @version 1 created on 2011��8��26������05:51:43
 */
@Service(value="transactionManager")
@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor={AxisFault.class, portalModuleException.class})
public class TransactionMessageServiceSpringImpl extends GenericDaoHibernate<TransactionMessage, String> implements TransactionMessageService {
	
	/***
	 * ͨ��������ˮ�Ų�ѯ���ױ���
	 * @param transRefGuid ������ˮ��
	 * @return ���׶���
	 */
	public TransactionMessage findTransactionMessageByTransRefGuid(String transRefGuid){
		return super.findUnique("transRefGuid", transRefGuid);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľ��ױ��Ķ����б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �������ױ��Ķ����б��Page����
	 */
	public Page findTransactionMessage(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ���ױ��Ķ����б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * ���ӽ��ױ���
	 * @param transactionMessage ���ױ��Ķ���
	 * @return
	 */
	public void addTransactionMessage(final TransactionMessage transactionMessage) {
		super.save(transactionMessage);
	}
	
	/***
	 * ���½��ױ���
	 * @param transactionMessage ���ױ��Ķ���
	 * @return
	 */
	public void updateTransactionMessage(TransactionMessage transactionMessage) {
		logger.debug("���±��ļ�¼�����״��룺" + transactionMessage.getTransRefGuid());
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
