package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.FactorType;
import cn.com.sinosoft.portalModule.enumUtil.FactorValueType;
import cn.com.sinosoft.portalModule.enumUtil.SaveMessageType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactor;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactorValue;
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

	/**
	 * 根据functionFlag、systemCode查找当前接口规则处理类
	 * @param functionFlag
	 * @param systemCode
	 * @return 处理类完整类名集合
	 */
	@Override
	public List<String> findPortalInterfaceRuleFactorProcessClass(String transCode, String systemCode) {
		return findPortalInterfaceRuleFactorValues(transCode, systemCode, FactorType.SYSTEM_INTERFACE.getValue());
	}

	/**
	 * 根据functionFlag、systemCode查找当前接口规则校验器
	 * @param functionFlag
	 * @param systemCode
	 * @return 校验器完整类名集合
	 */
	@Override
	public List<String> findPortalInterfaceRuleFactorVerificationProcessClass(String transCode, String systemCode) {
		return findPortalInterfaceRuleFactorValues(transCode, systemCode, FactorType.INTERFACE_VERIFICATION.getValue());
	}
	
	public List<String> findPortalInterfaceRuleFactorValues(String transCode, String systemCode, int factorType) {
		List<String> lists = new ArrayList<String>(0);
		if(factorType == FactorType.SYSTEM_INTERFACE.getValue()){
			Map<String, Object> propertyMap = new HashMap<String, Object>();
			propertyMap.put("status", "1");
			propertyMap.put("systemCode", systemCode);
			propertyMap.put("transCode", transCode);
			propertyMap.put("factorType", factorType);
			PortalInterfaceRuleFactor pifrf = this.findPortalInterfaceRuleFactorByQueryMap(propertyMap);
			if(pifrf != null){
				List<PortalInterfaceRuleFactor> pifrfs = new ArrayList<PortalInterfaceRuleFactor>(0);
				pifrfs.add(pifrf);
				lists = load(pifrfs, FactorValueType.INTERFACE_PROCESS.getValue());
			}
		}else if(factorType == FactorType.INTERFACE_VERIFICATION.getValue()){
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("status", "1");
			queryRule.addEqual("systemCode", systemCode);
			queryRule.addEqual("transCode", transCode);
			queryRule.addEqual("factorType", factorType);
			List<PortalInterfaceRuleFactor> pifrfs = findPortalInterfaceRuleFactorByQueryRule(queryRule);
			lists = load(pifrfs, FactorValueType.INTERFACE_VERIFICATION_PROCESS.getValue());
		}
		return lists;
	}
	
	public List<String> load(List<PortalInterfaceRuleFactor> pifrfs, int factorValueType){
		List<String> lists = new ArrayList<String>(0);
		if(pifrfs != null && !pifrfs.isEmpty()){
			for(PortalInterfaceRuleFactor pifrf : pifrfs){
				List<PortalInterfaceRuleFactorValue> pifrfvs = pifrf.getPortalInterfaceRuleFactorValues();
				if(pifrfvs != null && !pifrfvs.isEmpty()){
					for(PortalInterfaceRuleFactorValue pifrfv : pifrfvs){
						if(factorValueType == pifrfv.getFactorValueType()){
							lists.add(pifrfv.getCvalue());
						}
					}
				}
			}
		}
		return lists;
	}

	/**
	 * 根据functionFlag、source查找当前接口规则报文存储类型
	 * @param transCode
	 * @param systemCode
	 * @return 报文存储类型枚举对象（1-数据库;2-文件;3-数据库and文件）
	 */
	@Override
	public SaveMessageType findPortalInterfaceRuleFactorSaveMessageType(String transCode, String systemCode) {
		Map<String, Object> propertyMap = new HashMap<String, Object>();
		propertyMap.put("status", "1");
		propertyMap.put("factorType", FactorType.SYSTEM_INTERFACE.getValue());
		propertyMap.put("transCode", transCode);
		propertyMap.put("systemCode", systemCode);
		PortalInterfaceRuleFactor pifrf = findPortalInterfaceRuleFactorByQueryMap(propertyMap);
		if(pifrf != null){
			return (SaveMessageType) EnumDataUtils.getEnumDictionaryByValue(SaveMessageType.class, pifrf.getSaveMessageType());
		}else{
			return SaveMessageType.DATABASE;
		}
	}

}
