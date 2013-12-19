package cn.com.sinosoft.businessModule.hospitalManage.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.businessModule.hospitalManage.domain.HospitalManage;
import cn.com.sinosoft.businessModule.hospitalManage.service.facade.HospitalManageService;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HospitalManageServiceSpringImpl extends GenericDaoHibernate<HospitalManage, String> implements HospitalManageService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addHospital(HospitalManage hospitalManage) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("province", hospitalManage.getProvince());
		queryRule.addEqual("city", hospitalManage.getCity());
		queryRule.addEqual("hosName", hospitalManage.getHosName());
		queryRule.addEqual("hosAddr", hospitalManage.getHosAddr());
		List<HospitalManage> tList = this.findHospitalByQueryRule(queryRule);
		   if(tList.size() != 0){
			   return "2";
		   }
		super.save(hospitalManage);
		return "1";
	}

	@Override
	public List<HospitalManage> findHospitalByQueryRule(QueryRule queryRule) {
		StringBuffer hql = new StringBuffer("from " + HospitalManage.class.getName() + " where 1 = 1 ");
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
	public void updateHospital(HospitalManage hospitalManage) {
//		HospitalManage update = super.get(hospitalManage.getSerialNo());
//		List<String> ignorePropertyList = new ArrayList<String>();
//		String[] ignoreProperties = new String[ignorePropertyList.size()];
//		BeanUtils.copyProperties(hospitalManage, update, ignorePropertyList.toArray(ignoreProperties));
		super.update(hospitalManage);
	}

	@Override
	public void deleteHospital(HospitalManage hospitalManage) {
		HospitalManage delete = super.get(hospitalManage.getSerialNo());
		super.delete(delete);
	}
	
	@Override
	public Page getHospitalPage(HospitalManage hospitalManage, int pageNo, int pageSize) {
		QueryRule queryRule = QueryRule.getInstance();
		if(hospitalManage.getProvince()!=null&&!"".equals(hospitalManage.getProvince())){
			queryRule.addEqual("province",hospitalManage.getProvince());
		}
		if(hospitalManage.getHosName()!=null&&!"".equals(hospitalManage.getHosName())){
			queryRule.addLike("hosName", "%"+hospitalManage.getHosName()+"%");
		}
		Page page = super.find(queryRule, pageNo, pageSize);
		
		return page;
	}
	
	@Override
	public List<String> findUniqueProvince(){
		String sql = "select distinct province from hospitalmanage";
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
	public List findArea(String province) {
		List<Object> objectList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select ");
		sql.append("t.PLACECODE,");
		sql.append("t.PLACENAME");
		sql.append(" from ");
		if(province != null && !"".equals(province)){
			sql.append("GE_AREA_ADDRESS t where t.UPPLACENAME ='" + province + "'");
		}else{
			sql.append("GE_AREA_ADDRESS t where t.PLACETYPE = '01'");
		}
		
		return jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
	}
}