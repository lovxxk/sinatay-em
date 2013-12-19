package cn.com.sinosoft.businessModule.order.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.order.service.facade.OrderFormService;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OrderFormServiceSpringImpl extends GenericDaoHibernate<OrderForm, String> implements OrderFormService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger log = LoggerFactory
			.getLogger(OrderFormServiceSpringImpl.class);
	/**
	 * 添加订单
	 * @param orderForm
	 */
	public String addOrderForm(OrderForm orderForm) {
		super.save(orderForm);
		return orderForm.getSerialNo();
	}

	/***
	 * 根据属性查询订单
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OrderForm findOrderFormByQueryMap(Map propertyMap) {
		return super.findUnique(propertyMap);
	}
	
	/**
	 * 根据 主键查询订单信息
	 * @param serialNo
	 * @return
	 */
	public OrderForm findOrderFormBySerialNo(String serialNo) {
		return super.get(serialNo);
	}
	
	/***
	 * 根据订单号查询订单信息
	 * @param orderSerialNumber
	 * @return
	 */
	public OrderForm findOrderFormByOrderFormSerialNumber(String orderSerialNumber) {
		return super.findUnique("orderSerialNumber", orderSerialNumber);
	}
	
	
	/***
	 * 分页查询订单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page findOrderFormByQueryRule(QueryRule queryRule, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("from " + OrderForm.class.getName() + " where 1 = 1 ");
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
		List<org.hibernate.criterion.Order> OrderFormList = super.getOrderFromQueryRule(queryRule);
		if (OrderFormList.size() > 0 ) {
			hql.append("order by ");
			for (int i = 0; i < OrderFormList.size(); i++ ) {
				org.hibernate.criterion.Order OrderForm = OrderFormList.get(i);
				if (i  == 0) {
					hql.append(OrderForm.toString());
				} else {
					hql.append(", " + OrderForm.toString());
				}
				
			}	
		}
		
		return super.findByHqlNoLimit(hql.toString(), pageNo, pageSize, objectList.toArray());
	}
	
	/***
	 * 根据条件查询前几条数据
	 * @param propertyMap
	 * @param topNumber
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<OrderForm> findTopOrderFormByHQL(Map propertyMap, int topNumber) {
		StringBuffer hql = new StringBuffer("from OrderForm where 1 = 1 ");
		Set propertyNameSet = propertyMap.keySet();
		List propertyValueList = new ArrayList();
		if (!propertyNameSet.isEmpty()) {
			Iterator propertyIterator = propertyNameSet.iterator();
			while (propertyIterator.hasNext()) {
				String propertyName = (String) propertyIterator.next();
				hql.append("and " + propertyName + " = ? ");
				propertyValueList.add(propertyMap.get(propertyName));
			}
		}
		hql.append("order by createTime");
		
		List<OrderForm> orderFormList = super.findTopByHql(hql.toString(),topNumber, propertyValueList.toArray());
		
		return orderFormList;
	}
	
	/**
	 * 查询当前用户的订单数
	 * @param personalUserSerialNo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public long findCurrentUserOrderFormNumber(String personalUserSerialNo) {
		StringBuffer hql = new StringBuffer("select count(serialNo) from OrderForm where 1 = 1 ");
		hql.append("and personalUserSerialNo = ? ");
	 	List queryResultList = super.findByHql(hql.toString(), new Object[]{personalUserSerialNo});
	 	if (queryResultList.size() > 0) {
	 		return (Long)queryResultList.get(0);
	 	}
		return 0;
	}
	
	/***
	 * 根据条件查询订单
	 * @param queryRule
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<OrderForm> findAllOrderFormByPropertyMap(QueryRule queryRule){
		StringBuffer hql = new StringBuffer("from " + OrderForm.class.getName() + " where 1 = 1 ");
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
		List<org.hibernate.criterion.Order> orderFormList = super.getOrderFromQueryRule(queryRule);
		if (orderFormList.size() > 0 ) {
			hql.append("order by ");
			for (int i = 0; i < orderFormList.size(); i++ ) {
				org.hibernate.criterion.Order OrderForm = orderFormList.get(i);
				if (i  == 0) {
					hql.append(OrderForm.toString());
				} else {
					hql.append(", " + OrderForm.toString());
				}
				
			}	
		}
		
		return super.findByHql(hql.toString(), objectList.toArray());
	}
	
	/***
	 * 根据条件查询订单
	 * @param queryRule
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderForm> findAllOrderFormJoinPolicyBasicInfoByPersonalUser(String personalUserSerialNo){
		String hql = "from OrderForm o " +
				"join fetch o.insurancePolicy p " +
				"join fetch p.insurancePolicyBasicInfo " +
				"where o.personalUserSerialNo = ? "+
				"OrderForm by o.createTime desc";
		List<OrderForm> list = super.findByHql(hql, new Object[]{personalUserSerialNo});
		return list;
	}
	
	/***
	 * 根据条件查询最老订单
	 * @param queryRule
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderForm> findOldOrderFormPolicyBasicInfoByPersonalUser(String personalUserSerialNo){
		String hql = "from OrderForm o " +
				"join fetch o.insurancePolicy p " +
				"join fetch p.insurancePolicyBasicInfo " +
				"where o.personalUserSerialNo = '" + personalUserSerialNo +"' and (o.orderStatus=101 or o.orderStatus=72) "+
				"OrderForm by o.createTime desc";
		List<OrderForm> list = getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		return list;
	}
	
	/***
	 * 根据客户号查询所有保单数据
	 */
	public List<OrderForm> getOrderFormsByUserId(String personalUserSerialNo, String status) {
		StringBuffer sql = new StringBuffer("from " + OrderForm.class.getName() + " where personalUserSerialNo = '" + personalUserSerialNo + "' ");
		
		if (StringUtils.isNotEmpty(status)) {
			try {
				sql.append(" and orderStatus  = " + status);
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		sql.append(" order by updateTime desc");
		
		return findByHql(sql.toString());
	}
	
	/***
	 * 根据客户号查询所有保单数据
	 */
	public Page getOrderFormsByUserId(String personalUserSerialNo, Integer status, String orderSerialNo, int pageNo, int pageSize) {
		Page page = null;
		try {
			StringBuffer sql = new StringBuffer("from " + OrderForm.class.getName() + " where personalUserSerialNo = ? ");
			List<Object> queryValues = new ArrayList<Object>();
			queryValues.add(personalUserSerialNo);
			if(StringUtils.isNotBlank(orderSerialNo)){
				sql.append(" and serialNo  = ? ");
				queryValues.add(orderSerialNo);
			}
			if(status != null){
				sql.append(" and orderStatus  = ? ");
				queryValues.add(status);
			}
			sql.append(" order by transSerialNumber desc");
			page = super.findByHql(sql.toString(), pageNo, pageSize, queryValues.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 更新订单
	 * @param orderForm
	 */
	public void updateOrderForm(OrderForm orderForm) {
		super.update(orderForm);
//		OrderForm update = super.get(orderForm.getSerialNo());
//		List<String> ignorePropertyList = new ArrayList<String>();
//		ignorePropertyList.add("createTime");
	/*	//如果订单号和投保单号一样
		//如果要查询到的订单状态是已支付，并且要修改的订单状态也是已支付，那么就返回空，不修改订单状态
		if( update.getInsurancePolicy().getInsurancePolicyBasicInfo().getPolicySerialNumber() == OrderForm.getInsurancePolicy().getInsurancePolicyBasicInfo().getPolicySerialNumber())
		{
	      //如果要修改的订单号是已支付
		  if(OrderFormStatus.PAID.getValue().equals(update.getOrderStatus())){
			  //如果查询到的订单号不是已支付
			  if(!OrderFormStatus.PAID.getValue().equals(OrderForm.getOrderStatus())){
				  return ;
			  }
		  }
		  //如果查询到的订单号是承保成功，那么就不修改
		  if(OrderFormStatus.INSURED_SUCCESS.getValue().equals(OrderForm.getOrderStatus())){
			      return ;
		  }   
		  //如果查询到的订单号是电子保单，那么就不修改
		  if(OrderFormStatus.GENERATED_EPOLICY.getValue().equals(OrderForm.getOrderStatus())){
			      return ;
		  }
		}
		else{
	    //如果查询到的订单状态不是电子保单而且要修改的订单状态是承保成功或者电子保单，那么就将其放到ignorePropertyList中。
		if (!OrderFormStatus.GENERATED_EPOLICY.getValue().equals(OrderForm.getOrderStatus())) {
			if (OrderFormStatus.GENERATED_EPOLICY.getValue().equals(update.getOrderStatus()) || OrderFormStatus.INSURED_SUCCESS.getValue().equals(update.getOrderStatus())) {
				//将这些属性加到ignorePropertyList中
				ignorePropertyList.add("OrderFormType");
				ignorePropertyList.add("OrderFormStatus");
				ignorePropertyList.add("OrderFormStatusName");
				ignorePropertyList.add("OrderFormStatusDesc");
				ignorePropertyList.add("payTime");
				ignorePropertyList.add("payStatus");
				ignorePropertyList.add("payStatusName");
				ignorePropertyList.add("payStatusDesc");
			}
		}
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		//只更新ignorePropertyList中的数据
		BeanUtils.copyProperties(OrderForm, update, ignorePropertyList.toArray(ignoreProperties));
		//手动更新UpdateTime
		update.setUpdateTime(new Date());
		if (!(update.getOrderStatus() >= 70 && OrderForm.getOrderStatus() < 70)) {
			super.update(update);
		}
		}*/
	}

	/**
	 * 删除订单
	 * @param orderForms
	 * @return
	 */
	public boolean deleteAllOrderForm(List<OrderForm> orderForms) {
		try{
			super.deleteAll(orderForms);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 根据订单号删除订单
	 * @param orderSerialNumber
	 * @return
	 */
	public boolean deleteByOrderFormSerialNumber(String orderSerialNumber){
		try{
			super.delete(super.findUnique("orderSerialNumber", orderSerialNumber));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 查询相应状态对应的订单信息
	 */
	@Override
	public Integer getOrderFomrsByState(OrderStatus status, String userId) {
		QueryRule query = QueryRule.getInstance();

		query.addEqual("orderStatus", status.getValue());
		query.addEqual("personalUserSerialNo", userId);
		
		List list = this.find(query);
		 
		return list.isEmpty() ? 0 : list.size();
	}

	/**
	 * 查询为提交的试算单
	 */
	@Override
	public Integer getQuoteMainByState(String userId) {
		String sql = "select count(*) from orderform where orderStatus = 10 and personalUserSerialNo = '" + userId + "'";

		List list = this.findBySql(sql);
		
		return list.isEmpty() ? 0 : Integer.valueOf(list.get(0) + "");
	}

	@Override
	public List<OrderForm> getTwoOrderForms(String userId) {
		Query query = getSession().createQuery("from " + OrderForm.class.getName() + " where personalUserSerialNo = ? order by updateTime desc");
//		Query query = getSession().createQuery("from " + OrderForm.class.getName() + " order by updateTime desc");
		query.setMaxResults(2);
		query.setString(0, userId);
		
		return query.list();
	}

	@Override
	public List<OrderForm> getSaleRecords() {
		Query query = getSession().createQuery("from " + OrderForm.class.getName() + " order by updateTime desc");
		
		return query.list();
	}

	@Override
	public OrderForm getOrderFormBySerialNo(String serialNo) {
		try {
			return findUnique("serialNo", serialNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderForm getOrderFormForKey(String serialNo) {
		String hql = "from " + OrderForm.class.getName() + " where serialNo = ?";
		
		List<OrderForm> list = findByHql(hql, serialNo);
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	public boolean updateInsurancePolicyEffectDate(OrderForm orderForm, String effect_start) {
		try {
			if (effect_start != null) {
				String sql = "update INSURANCEPOLICY set inceptionDate = to_date('" + effect_start + "','yyyy-MM-dd') where serialNo = '" + orderForm.getInsurancePolicy().getSerialNo() + "'";
				int i = super.getSession().createSQLQuery(sql).executeUpdate();
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 批量跟新提醒次数
	 * @param orderFormAll
	 */
	public void updateAllRemindCount(final List orderFormAll){
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ");
		sql.append("ORDERFORM ");
		sql.append("set REMINDCOUNT = ? ");
//		sql.append(", ");
//		sql.append("UPDATETIME = ? ");
		sql.append("WHERE ");
		sql.append("SERIALNO = ? ");
		jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				OrderForm order = (OrderForm) orderFormAll.get(i);
//				System.out.println(order.getSerialNo());
				ps.setInt(1, (order.getRemindCount()==null?0:order.getRemindCount())+1);
//				Timestamp generationTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//				ps.setTimestamp(2, generationTime);
				ps.setString(2, order.getSerialNo());
			}

			@Override
			public int getBatchSize() {
				return orderFormAll.size();
			}
			
		});
	}

}