package cn.com.sinosoft.businessModule.bankAccount.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.bankAccount.domain.FundsTransfer;
import cn.com.sinosoft.businessModule.enums.dictionary.FundsTransferStatus;

public interface FundsTransferService {

	/***
	 * ����ʽ𻮲�
	 * @param frundsTransfer
	 */
	public abstract void addFundsTransfer(FundsTransfer frundsTransfer);

	/***
	 * ��ҳ��ѯ�ʽ𻮲���Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findFundsTransferByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * �������Բ�ѯ�ʽ𻮲���Ϣ
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public abstract FundsTransfer findFundsTransferByQueryMap(Map propertyMap);
	
	/***
	 * ����������ѯ�ʽ𻮲���Ϣ
	 * @param serialNo �߼��������
	 * @return
	 */
	public abstract FundsTransfer findFundsTransferBySerialNo(String serialNo);
	
	/**
	 * �����ʽ𻮲������Ų�ѯ�ʽ𻮲���Ϣ
	 * @param fundsTransferOrderNumber
	 * @return
	 */
	public abstract FundsTransfer findFundsTransferByfundsTransferOrderNumber(String fundsTransferOrderNumber);
	
	/**
	 * �����ʽ𻮲������Ÿ�����Ϣ
	 * @param fundsTransfer �ʽ𻮲���Ϣ
	 * @param copyProperties ��Ҫ����������
	 */
	public abstract void updateFundsTransferByFundsTransferOrderNumber(
			FundsTransfer fundsTransfer, List<String> copyProperties);
	
	/**
	 * ��������������Ĵ����ʽ𻮲�
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateAllRequestCoreHandlerTransfer(final List fundsTransferAll,
			final FundsTransferStatus fundsTransferStatus);

}