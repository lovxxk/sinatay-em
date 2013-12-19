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
	 * 添加电子保单
	 * @param ePolicy
	 */
	public void addEPolicy(EPolicy ePolicy) {
		super.save(ePolicy);
	}

	/**
	 * 更改电子保单
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
	 * 根据QueryRule查询查询电子保单
	 * @param queryRule
	 */
	public List<EPolicy> findEPolicyByQueryRule(QueryRule queryRule) {
		return super.find(queryRule);
	}
	
	/**
	 * 根据主键SerialNo 查询电子保单信息
	 * @param serialNo
	 * @return
	 */
	public EPolicy findEPolicyBySerialNo(String serialNo) {
		return super.findUnique("serialNo", serialNo);
	}
	
	/**
	 * 根据保单号查询电子保单信息
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
	 * 根据订单号查询电子保单信息
	 * @param orderNo
	 * @return
	 */
	public EPolicy findEPolicyByOrderNo(String orderNo) {
		return super.findUnique("orderNo", orderNo);
	}
	
	/**
	 * 查询最近的电子保单 
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
	 * 删除电子保单
	 * @param queryRule
	 */
	public void deleteQueryRuleBySerialNo(String serialNo) {
		super.deleteByPK(serialNo);
	}
}
