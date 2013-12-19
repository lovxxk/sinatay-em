package cn.com.sinosoft.businessModule.EPolicy.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.businessModule.EPolicy.domain.EPolicy;
import cn.com.sinosoft.businessModule.EPolicy.service.facade.EPolicyService;

public class EPolicyServiceSpringImpl extends GenericDaoHibernate<EPolicy, String> implements EPolicyService {

	/**
	 * ��ӵ��ӱ���
	 * @param ePolicy
	 */
	public void addEPolicy(EPolicy ePolicy) {
		super.save(ePolicy);
	}

	/**
	 * ���ĵ��ӱ���
	 * @param ePolicy
	 */
	public void updateEPolicy(EPolicy ePolicy) {
		EPolicy update = super.get(ePolicy.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(ePolicy, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}

	/**
	 * ����QueryRule��ѯ��ѯ���ӱ���
	 * @param queryRule
	 */
	public List<EPolicy> findEPolicyByQueryRule(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/**
	 * ��������SerialNo ��ѯ���ӱ�����Ϣ
	 * @param serialNo
	 * @return
	 */
	public EPolicy findEPolicyBySerialNo(String serialNo) {
		return super.findUnique("serialNo", serialNo);
	}
	
	/**
	 * ���ݱ����Ų�ѯ���ӱ�����Ϣ
	 * @param policyNo
	 * @return
	 */
	public EPolicy findEPolicyByPolicyNo(String policyNo) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("policyNo", policyNo);
		queryRule.addDescOrder("createTime");
		List<EPolicy> ePolicyList = super.find(queryRule);
		if (ePolicyList.size() > 0 ) {
			return ePolicyList.get(0);
		} 
		return null;
	}
	
	/**
	 * ���ݶ����Ų�ѯ���ӱ�����Ϣ
	 * @param orderNo
	 * @return
	 */
	public EPolicy findEPolicyByOrderNo(String orderNo) {
		return super.findUnique("orderNo", orderNo);
	}
	
	/**
	 * ��ѯ����ĵ��ӱ��� 
	 * @param orderNo
	 * @return
	 */
	public EPolicy findLatelyEPolicyByOrderNo(String orderNo) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("orderNo", orderNo);
		queryRule.addDescOrder("createTime");
		List<EPolicy> ePolicyList = super.find(queryRule);
		if (ePolicyList.size() > 0 ) {
			return ePolicyList.get(0);
		} 
		
		return null;
		
	}
	
	/**
	 * ɾ�����ӱ���
	 * @param queryRule
	 */
	public void deleteQueryRuleBySerialNo(String serialNo) {
		super.deleteByPK(serialNo);
	}
}
