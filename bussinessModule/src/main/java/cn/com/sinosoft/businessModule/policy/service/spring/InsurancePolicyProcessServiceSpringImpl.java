package cn.com.sinosoft.businessModule.policy.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.enums.dictionary.ProcessStatus;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyProcess;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyProcessService;
import cn.com.sinosoft.util.spring.BeanUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class InsurancePolicyProcessServiceSpringImpl extends GenericDaoHibernate<InsurancePolicyProcess, String> implements InsurancePolicyProcessService {
	
	/**
	 * 添加投保单
	 * @param insurancePolicyProcess
	 */
	public String addInsurancePolicyProcess(InsurancePolicyProcess insurancePolicyProcess) {
		super.save(insurancePolicyProcess);
		return insurancePolicyProcess.getSerialNo();
	}

	/***
	 * 根据主键查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public InsurancePolicyProcess findInsurancePolicyProcessBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * 分页查询投保单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findInsurancePolicyProcessByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	
	/**
	 * 更新投保单
	 * @param InsurancePolicyProcess
	 */
	public void updateInsurancePolicyProcess(InsurancePolicyProcess insurancePolicyProcess) {
		InsurancePolicyProcess update = findInsurancePolicyProcessByMerchantOrderNumber(insurancePolicyProcess.getMerchantOrderNumber());
		if(update!=null){
			List<String> ignorePropertyList = new ArrayList<String>();
			ignorePropertyList.add("createTime");
			String[] ignoreProperties = new String[ignorePropertyList.size()];
			BeanUtils.copyProperties(insurancePolicyProcess, update, ignorePropertyList.toArray(ignoreProperties));
			update.setUpdateTime(new Date());
			super.update(update);
		}else{
			super.save(update);
		}
	}
	
	public List<InsurancePolicyProcess> findInsurancePolicyProcessByQueryRule(QueryRule queryRule) {
		StringBuffer hql = new StringBuffer("from " + InsurancePolicyProcess.class.getName() + " where 1 = 1 ");
		List<Rule> ruleList = queryRule.getRuleList();
		List objectList = new ArrayList();
		for (int i = 0; i < ruleList.size(); i++) {
			Rule rule = ruleList.get(i);
			switch (rule.getType()) {
			
				case 1 :
					hql.append("and " + rule.getPropertyName() + " like ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 2 :
					Object ruleObj = rule.getValues()[0];
					if (ruleObj instanceof List) {
						List ruleObjList = (List)ruleObj;
						
						hql.append("and " + rule.getPropertyName() + " in (");
						for (int j = 0; j < ruleObjList.size(); j++) {
							if (j == 0) {
								hql.append("?");
							} else {
								hql.append(", ?");
							}
						}
						hql.append(") ");
						objectList.addAll(ruleObjList);
					}
					
					break;
				case 3 :
					if (rule.getValues().length < 2) {
						break;
					}
					hql.append("and " + rule.getPropertyName() + " between ? and ? ");
					objectList.add(rule.getValues()[0]);
					objectList.add(rule.getValues()[1]);
					break;
				case 4 :
					hql.append("and " + rule.getPropertyName() + " = ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 5 :
					hql.append("and " + rule.getPropertyName() + " <> ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 6 :
					hql.append("and " + rule.getPropertyName() + " > ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 7 :
					hql.append("and " + rule.getPropertyName() + " >= ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 8 :
					hql.append("and " + rule.getPropertyName() + " < ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 9 :
					hql.append("and " + rule.getPropertyName() + " <= ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 11:
					hql.append("and " + rule.getPropertyName() + " is null ");
			        break;
				case 12:
					hql.append("and " + rule.getPropertyName() + " is not null ");
			        break;
			}
		}
		List<org.hibernate.criterion.Order> orderList = super.getOrderFromQueryRule(queryRule);
		if (orderList.size() > 0 ) {
			hql.append("order by ");
			for (int i = 0; i < orderList.size(); i++ ) {
				org.hibernate.criterion.Order order = orderList.get(i);
				if (i  == 0) {
					hql.append(order.toString());
				} else {
					hql.append(", " + order.toString());
				}
				
			}	
		}
		
		return super.findByHql(hql.toString(), objectList.toArray());
	}
	
	public void updateInsurancePolicyProcessByMerchantOrderNumber(InsurancePolicyProcess insurancePolicyProcess) {
		InsurancePolicyProcess update = super.findUnique("merchantOrderNumber", insurancePolicyProcess.getMerchantOrderNumber()==null?"0":insurancePolicyProcess.getMerchantOrderNumber());
		if(update != null){
			updateInsurancePolicyProcess(update);
		}else{
			addInsurancePolicyProcess(insurancePolicyProcess);
		}
		
	}
	
	public String addInsurancePolicyProcessByInsurancePolicy(InsurancePolicy insurancePolicy) {
		InsurancePolicyProcess update = new InsurancePolicyProcess();
		update.setMerchantOrderNumber(insurancePolicy.getQuoteNo());
		update.setPolicySerialNumber(insurancePolicy.getPolicySerialNumber());
		update.setInstitutionCode(insurancePolicy.getInstitutionCode());
		update.setInstitutionName(insurancePolicy.getInstitutionName());
		update.setEnumProcessStatus(ProcessStatus.PERFORMING);
		update.setProcessStartTime(new Date());
		super.save(update);
		return update.getSerialNo();
	}
	
	public InsurancePolicyProcess findInsurancePolicyProcessByQueryMap(Map propertyMap) {
		super.setOptimizeFind(true);
		return super.findUnique(propertyMap);
	}
	
	public InsurancePolicyProcess findInsurancePolicyProcessByMerchantOrderNumber(String merchantOrderNumber){
		InsurancePolicyProcess insurancePolicyProcess = null;
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("merchantOrderNumber", merchantOrderNumber);
		queryRule.addDescOrder("updateTime");
		List<InsurancePolicyProcess> insurancePolicyProcessList = super.find(queryRule);
		if (insurancePolicyProcessList.size() > 0 ) {
			insurancePolicyProcess = insurancePolicyProcessList.get(0);
		} 
		return insurancePolicyProcess;
	}
	
	public void deleteInsurancePolicyProcess(InsurancePolicyProcess insurancePolicyProcess) {
		InsurancePolicyProcess update = super.get(insurancePolicyProcess.getSerialNo());
		super.delete(update);
	}
	
	public void deleteInsurancePolicyProcessByMerchantOrderNumber(InsurancePolicyProcess insurancePolicyProcess){
		InsurancePolicyProcess update = super.findUnique("merchantOrderNumber", insurancePolicyProcess.getMerchantOrderNumber()==null?"0":insurancePolicyProcess.getMerchantOrderNumber());
		if(update != null){
			super.delete(update);
		}
	}
}
