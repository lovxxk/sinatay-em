package cn.com.sinosoft.businessModule.order.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.businessModule.order.domain.AbnormalOrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.AbnormalOrderFormService;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AbnormalOrderFormServiceSpringImpl extends GenericDaoHibernate<AbnormalOrderForm, String> implements AbnormalOrderFormService {

	/**
	 * 添加异常订单
	 * @param order
	 */
	public void addAbnormalOrderForm(AbnormalOrderForm AbnormalOrderForm) {
		super.save(AbnormalOrderForm);
	}
	
	/***
	 * 根据属性查询异常订单
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbnormalOrderForm findAbnormalOrderFormByQueryMap(Map propertyMap) {
		return super.findUnique(propertyMap);
	}
	
	/**
	 * 根据 主键查询异常订单信息
	 * @param serialNo
	 * @return
	 */
	public AbnormalOrderForm findAbnormalOrderFormBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * 根据异常订单号查询异常订单信息
	 * @param AbnormalOrderFormSerialNumber
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbnormalOrderForm findAbnormalOrderFormByOrderSerialNumber(String orderSerialNumber) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("orderSerialNumber", orderSerialNumber);
		queryRule.addDescOrder("updateTime");
		List objectList = new ArrayList();
		objectList.add(orderSerialNumber);
		List<AbnormalOrderForm> AbnormalOrderFormList = super.find(queryRule);
		 if (AbnormalOrderFormList.isEmpty()) {
			 return null;
		 } if (AbnormalOrderFormList.size() == 1) {
			 return AbnormalOrderFormList.get(0);
		 } else {
			 deleteAbnormalOrderForm(AbnormalOrderFormList.subList(1, AbnormalOrderFormList.size()));
			 return AbnormalOrderFormList.get(0);
		 }
	}
	
	
	/***
	 * 分页查询异常订单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page findAbnormalOrderFormByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("from " + AbnormalOrderForm.class.getName() + " where 1 = 1 ");
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
		
		return super.findByHqlNoLimit(hql.toString(), pageNo, pageSize, objectList.toArray());
	}
	
	/***
	 * 根据条件查询异常订单
	 * @param queryRule
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<AbnormalOrderForm> findAllAbnormalOrderFormByQueryRule(QueryRule queryRule){
		StringBuffer hql = new StringBuffer("from " + AbnormalOrderForm.class.getName() + " where 1 = 1 ");
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
	
	/**
	 * 更新异常订单
	 * @param AbnormalOrderForm
	 */
	public void updateAbnormalOrderForm(AbnormalOrderForm AbnormalOrderForm) {
		AbnormalOrderForm update = super.get(AbnormalOrderForm.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(AbnormalOrderForm, update, ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}

	/**
	 * 删除异常订单
	 * @param AbnormalOrderFormList
	 * @return
	 */
	public boolean deleteAbnormalOrderForm(List<AbnormalOrderForm> AbnormalOrderFormList) {
		try{
			super.deleteAll(AbnormalOrderFormList);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 根据订单号删除异常订单
	 */
	public boolean deleteAbnormalOrderForm(String orderSerialNumber){
		try{
			super.delete(super.findUnique("orderSerialNumber", orderSerialNumber));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
