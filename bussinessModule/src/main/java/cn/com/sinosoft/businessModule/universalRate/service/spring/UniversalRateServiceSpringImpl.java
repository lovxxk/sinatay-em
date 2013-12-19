package cn.com.sinosoft.businessModule.universalRate.service.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.businessModule.universalRate.domain.UniversalRate;
import cn.com.sinosoft.businessModule.universalRate.service.facade.UniversalRateService;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class UniversalRateServiceSpringImpl extends GenericDaoHibernate<UniversalRate, String> implements UniversalRateService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addUniversal(UniversalRate universalRate) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("riskName", universalRate.getRiskName());
		queryRule.addEqual("culDate", universalRate.getCulDate());
		queryRule.addEqual("dateRate", universalRate.getDateRate());
		queryRule.addEqual("yearRate", universalRate.getYearRate());
		List<UniversalRate> tList = this.findUniversalByQueryRule(queryRule);
		   if(tList.size() != 0){
			   return "2";
		   }
		super.save(universalRate);
		return "1";
	}

	@Override
	public List<UniversalRate> findUniversalByQueryRule(QueryRule queryRule) {
		StringBuffer hql = new StringBuffer("from " + UniversalRate.class.getName() + " where 1 = 1 ");
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
	public void updateUniversal(UniversalRate universalRate) {
		super.update(universalRate);
	}

	@Override
	public void deleteUniversal(UniversalRate universalRate) {
		UniversalRate delete = super.get(universalRate.getSerialNo());
		super.delete(delete);
	}
	
	@Override
	public Page getUniversalPage(UniversalRate universalRate, int pageNo, int pageSize) {
		QueryRule queryRule = QueryRule.getInstance();
		if(universalRate.getCulDate()!=null){
			queryRule.addEqual("culDate",universalRate.getCulDate());
		}
		if(universalRate.getRiskName()!=null&&!"".equals(universalRate.getRiskName())){
			queryRule.addLike("riskName", "%"+universalRate.getRiskName()+"%");
		}
		Page page = super.find(queryRule, pageNo, pageSize);
		
		return page;
	}
	
	@Override
	public List<String> findUniqueRiskName(){
		String sql = "select distinct riskname from ge_universal_rate order by riskname";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> tList = session.createSQLQuery(sql).list();
		tx.commit();
		session.close();
		List<String> rList = new ArrayList<String>();
		for(int i = 0; i<tList.size(); i++){
			Object o = tList.get(i);
			rList.add((String)o);
		}
		return rList;
	}
	
	@Override
	public List<UniversalRate> findNewUniversal(){
		String sql = "select serialno,riskname,culdate,daterate,yearrate from Ge_Universal_Rate t where culdate >=  trunc((select max(culDate) from Ge_Universal_Rate), 'month') order by culdate desc,riskname";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> tList = session.createSQLQuery(sql).list();
		tx.commit();
		session.close();
		List<UniversalRate> listUniversal = new ArrayList<UniversalRate>();
		UniversalRate tUniversalRate;
		for(int i = 0; i < tList.size(); i++){
			tUniversalRate = new UniversalRate();
			tUniversalRate.setSerialNo((String)tList.get(i)[0]);
			tUniversalRate.setRiskName((String)tList.get(i)[1]);
			tUniversalRate.setCulDate((java.sql.Date)tList.get(i)[2]);
			tUniversalRate.setDateRate((BigDecimal)tList.get(i)[3]);
			tUniversalRate.setYearRate((BigDecimal)tList.get(i)[4]);
			listUniversal.add(tUniversalRate);
		}
		
		return listUniversal;
	}
	
	@Override
	public UniversalRate findUniqNewUniversal(){
		String sql = "select serialno,riskname,culdate,daterate,yearrate from Ge_Universal_Rate t where riskname = '信泰宝利来两全保险（万能型）A款2013年7月1日后（含当日）生效' order by culDate desc";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> tList = session.createSQLQuery(sql).list();
		tx.commit();
		session.close();
		UniversalRate tUniversalRate = new UniversalRate();
		if(tList != null && !tList.isEmpty()){
			Object o = tList.get(0);
			tUniversalRate.setSerialNo((String)tList.get(0)[0]);
			tUniversalRate.setRiskName((String)tList.get(0)[1]);
			tUniversalRate.setCulDate((java.sql.Date)tList.get(0)[2]);
			tUniversalRate.setDateRate((BigDecimal)tList.get(0)[3]);
			tUniversalRate.setYearRate((BigDecimal)tList.get(0)[4]);
		}
		return tUniversalRate;
	}
}