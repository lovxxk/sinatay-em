package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactor;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;

public class PortalInterfaceRuleFactorServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceRuleFactor, String> implements PortalInterfaceRuleFactorService {

	@Override
	public void addPortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor) {
		super.save(portalInterfaceRuleFactor);
	}

	@Override
	public Page findPortalInterfaceRuleFactor(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public void deletePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor) {
		if (portalInterfaceRuleFactor != null) {
			super.delete(portalInterfaceRuleFactor);
		}
	}

	@Override
	public void updatePortalInterfaceRuleFactor(PortalInterfaceRuleFactor portalInterfaceRuleFactor) {
		PortalInterfaceRuleFactor update = super.get(portalInterfaceRuleFactor.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		
		cn.com.sinosoft.util.spring.BeanUtils.copyProperties(portalInterfaceRuleFactor, update, ignorePropertyList.toArray(ignoreProperties));
		super.update(update);
	}

	@Override
	public PortalInterfaceRuleFactor findPortalInterfaceRuleFactorBySerialNo(String serialNo) {
		return super.get(serialNo);
	}

	@Override
	public List<PortalInterfaceRuleFactor> findPortalInterfaceRuleFactorByQueryRule(QueryRule queryRule) {
		StringBuffer hql = new StringBuffer("from " + PortalInterfaceRuleFactor.class.getName() + " where 1 = 1 ");
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

	@Override
	public PortalInterfaceRuleFactor findPortalInterfaceRuleFactorByFactorCode(String factorCode) {
		return super.findUnique("factorCode", factorCode);
	}

	@Override
	public PortalInterfaceRuleFactor findPortalInterfaceRuleFactorByQueryMap(Map propertyMap) {
		return super.findUnique(propertyMap);
	}

}
