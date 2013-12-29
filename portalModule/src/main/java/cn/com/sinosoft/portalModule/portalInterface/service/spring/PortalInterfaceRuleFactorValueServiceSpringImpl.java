package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactorValue;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorValueService;

public class PortalInterfaceRuleFactorValueServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceRuleFactorValue, String> implements PortalInterfaceRuleFactorValueService {

	@Override
	public void addPortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue) {
		super.save(portalInterfaceRuleFactorValue);
	}

	@Override
	public Page findPortalInterfaceRuleFactorValue(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public void deletePortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue) {
		if (portalInterfaceRuleFactorValue != null) {
			super.delete(portalInterfaceRuleFactorValue);
		}
	}

	@Override
	public void updatePortalInterfaceRuleFactorValue(PortalInterfaceRuleFactorValue portalInterfaceRuleFactorValue) {
		PortalInterfaceRuleFactorValue update = super.get(portalInterfaceRuleFactorValue.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		
		cn.com.sinosoft.util.spring.BeanUtils.copyProperties(portalInterfaceRuleFactorValue, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}

	@Override
	public PortalInterfaceRuleFactorValue findPortalInterfaceRuleFactorValueBySerialNo(String serialNo) {
		return super.get(serialNo);
	}

	@Override
	public List<PortalInterfaceRuleFactorValue> findPortalInterfaceRuleFactorValueByQueryRule(QueryRule queryRule) {
		StringBuffer hql = new StringBuffer("from " + PortalInterfaceRuleFactorValue.class.getName() + " where 1 = 1 ");
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

}
