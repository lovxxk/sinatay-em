package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuredLiability;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade.InsuredLiabilityService;

public class InsuredLiabilityServiceSpringImpl  extends GenericDaoHibernate<InsuredLiability, String> implements InsuredLiabilityService{
	 
	/***
	 *  ��ӱ������˱���/����
	 * @param insuredLiability
	 */
	public void addInsuredLiability(InsuredLiability insuredLiability) {
		super.save(insuredLiability);
	}
	
	/**
	 * ������б������˱���/����
	 * @param insuredLiabilityList
	 */
	public void addAllInsuredLiability(List<InsuredLiability> insuredLiabilityList) {
		super.saveAll(insuredLiabilityList);
	}
	

	/***
	 * �������Բ�ѯ�������˱���/����
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InsuredLiability findInsuredLiabilityByQueryMap(Map propertyMap) {
		return super.findUnique(propertyMap);
	}
	
	/**
	 * ���� ������ѯ�������˱���/������Ϣ
	 * @param serialNo
	 * @return
	 */
	public InsuredLiability findInsuredLiabilityBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * ��ҳ��ѯ�������˱���/������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page findInsuredLiabilityByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("from InsuredLiability insuredLiability where 1 = 1 ");
		List<Rule> ruleList = queryRule.getRuleList();
		List objectList = new ArrayList();
		for (int i = 0; i < ruleList.size(); i++) {
			Rule rule = ruleList.get(i);
			switch (rule.getType()) {
			
				case 1 :
					hql.append("and " + rule.getPropertyName() + " like ? ");
					objectList.add(rule.getValues()[0]);
					break;
				case 4 :
					hql.append("and " + rule.getPropertyName() + " = ? ");
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
			}
		}
		List<org.hibernate.criterion.Order> orderList = super.getOrderFromQueryRule(queryRule);
		if (orderList.size() >= 0 ) {
			hql.append("InsuredLiability by ");
			for (int i = 0; i < orderList.size(); i++ ) {
				org.hibernate.criterion.Order InsuredLiability = orderList.get(i);
				if (i  == 0) {
					hql.append(InsuredLiability.toString());
				} else {
					hql.append(", " + InsuredLiability.toString());
				}
				
			}	
		}
		
		
		return super.findByHql(hql.toString(), pageNo, pageSize, objectList.toArray());
	}
	
	
	
	/***
	 * ����������ѯ�������˱���/����
	 * @param queryRule
	 * @return
	 */
	public List<InsuredLiability> findAllInsuredLiabilityByQueryRule(QueryRule queryRule){
		return super.find(queryRule);
	}
	
	/**
	 * ���±������˱���/����
	 * @param insuredLiability
	 */
	public void updateInsuredLiability(InsuredLiability insuredLiability) {
		InsuredLiability update = super.get(insuredLiability.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(insuredLiability, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}

	/**
	 * ɾ���������˱���/����
	 * @param insuredLiabilityList
	 * @return
	 */
	public boolean deleteAllInsuredLiability(List<InsuredLiability> insuredLiabilityList) {
		try{
			super.deleteAll(insuredLiabilityList);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
