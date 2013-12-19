package cn.com.sinosoft.businessModule.network.service.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.QueryRule.Rule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.businessModule.network.domain.Network;
import cn.com.sinosoft.businessModule.network.service.facade.NetworkService;
import cn.com.sinosoft.util.spring.BeanUtils;

public class NetworkServiceSpringImpl extends
		GenericDaoHibernate<Network, String> implements NetworkService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Cacheable(cacheName = "netWorkCache")
	@Override
	public Network findNetworkBySerialNo(String serialNo) {

		return super.get(serialNo);
	}
	@TriggersRemove(cacheName="netWorkCache",removeAll=true) 
	@Override
	public void updateNetwork(Network network) {
		Network update = super.get(network.getSerialNo());
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.add("createTime");
		String[] ignoreProperties = new String[ignorePropertyList.size()];
		BeanUtils.copyProperties(network, update,
				ignorePropertyList.toArray(ignoreProperties));
		update.setUpdateTime(new Date());
		super.update(update);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Cacheable(cacheName = "netWorkCache")
	public List<Network> findNetworkByQueryRule(QueryRule queryRule) {

		StringBuffer hql = new StringBuffer("from " + Network.class.getName()
				+ " where 1 = 1 ");
		List<Rule> ruleList = queryRule.getRuleList();

		List objectList = new ArrayList();
		for (int i = 0; i < ruleList.size(); i++) {
			Rule rule = ruleList.get(i);
			switch (rule.getType()) {

			case 1:
				hql.append("and " + rule.getPropertyName() + " like ? ");
				objectList.add(rule.getValues()[0]);
				break;
			case 2:
				Object ruleObj = rule.getValues()[0];
				if (ruleObj instanceof List) {
					List ruleObjList = (List) ruleObj;

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
			case 3:
				if (rule.getValues().length < 2) {
					break;
				}
				hql.append("and " + rule.getPropertyName()
						+ " between ? and ? ");
				objectList.add(rule.getValues()[0]);
				objectList.add(rule.getValues()[1]);
				break;
			case 4:
				hql.append("and " + rule.getPropertyName() + " = ? ");
				objectList.add(rule.getValues()[0]);
				break;
			case 5:
				hql.append("and " + rule.getPropertyName() + " <> ? ");
				objectList.add(rule.getValues()[0]);
				break;
			case 6:
				hql.append("and " + rule.getPropertyName() + " > ? ");
				objectList.add(rule.getValues()[0]);
				break;
			case 7:
				hql.append("and " + rule.getPropertyName() + " >= ? ");
				objectList.add(rule.getValues()[0]);
				break;
			case 8:
				hql.append("and " + rule.getPropertyName() + " < ? ");
				objectList.add(rule.getValues()[0]);
				break;
			case 9:
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
		// 增加排序
		queryRule.addAscOrder("sort");
		List<org.hibernate.criterion.Order> orderList = super
				.getOrderFromQueryRule(queryRule);
		if (orderList.size() > 0) {
			hql.append("order by ");
			for (int i = 0; i < orderList.size(); i++) {
				org.hibernate.criterion.Order order = orderList.get(i);
				if (i == 0) {
					hql.append(order.toString());
				} else {
					hql.append(", " + order.toString());
				}

			}
		}
		return super.findByHql(hql.toString(), objectList.toArray());

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Cacheable(cacheName = "netWorkCache")
	@Override
	public Network findNetworkByQueryMap(Map propertyMap) {
		super.setOptimizeFind(true);
		return super.findUnique(propertyMap);
	}
	
	@TriggersRemove(cacheName="netWorkCache",removeAll=true) 
	@Override
	public void deleteNetwork(Network network) {
		try {
			super.deleteByPK(network.getSerialNo());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Cacheable(cacheName = "netWorkCache")
	@Override
	public Page getNetworkPage(Network network, int pageNo, int pageSize) {
		QueryRule queryRule = QueryRule.getInstance();
		if (network.getProvince() != null && !"".equals(network.getProvince())) {
			queryRule.addEqual("province", network.getProvince());
		}
		if (network.getCity() != null && !"".equals(network.getCity())) {
			queryRule.addEqual("city", network.getCity());
		}
		if (network.getManageName() != null
				&& !"".equals(network.getManageName())) {
			queryRule
					.addLike("manageName", "%" + network.getManageName() + "%");
		}
		// 怎加排序
		queryRule.addAscOrder("manageCom");
		Page page = super.find(queryRule, pageNo, pageSize);

		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findArea(String province) {
		List<Object> objectList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select ");
		sql.append("t.PLACECODE,");
		sql.append("t.PLACENAME");
		sql.append(" from ");
		if (province != null && !"".equals(province)) {
			sql.append("GE_AREA_ADDRESS t where t.UPPLACENAME ='" + province
					+ "'");
		} else {
			sql.append("GE_AREA_ADDRESS t where t.PLACETYPE = '01'");
		}

		return jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
	}
	@Cacheable(cacheName = "netWorkCache")

	@Override
	public List<String> findUniqueProvince() {
		System.out.println("服务网点--->查询省份");
		String sql = "select distinct province from network";
		List<String> rList = new ArrayList<String>();

		if(jdbcTemplate!=null){
			List<Map<String, Object>> tlist = jdbcTemplate.queryForList(sql);
			for(int i = 0; i < tlist.size(); i++){
				rList.add(tlist.get(i).get("province").toString());
			}
		}
		
		return rList;
	}

	@Override
	public String findPlaceName(String placeCode) {
		String sql = "select distinct placename from GE_AREA_ADDRESS where 1=1 ";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String placeName = session.createSQLQuery(sql).list().get(0).toString();
		tx.commit();
		session.close();
		return placeName;
	}
	
	@TriggersRemove(cacheName="netWorkCache",removeAll=true) 
	@Override
	public String addServiceNetwork(Network network) {
		String sql = "select count(1) from network where 1=1 and manageCom = '"
				+ network.getManageCom() + "'";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int count = ((BigDecimal) session.createSQLQuery(sql).list().get(0))
				.intValue();
		if (count > 0) {
			return "2";
		}
		save(network);
		tx.commit();
		session.close();
		return "1";
	}
	@Override
	@Cacheable(cacheName = "netWorkCache")
	public List<String> findUniqueCity(String province) {
		System.out.println("服务网点--->查询市");
		String sql = "select distinct city from network where province='"
				+ province + "'";
		List<String> rList = new ArrayList<String>();
		if(jdbcTemplate!=null){
			List<Map<String, Object>> tlist = jdbcTemplate.queryForList(sql);
			for(int i = 0; i < tlist.size(); i++){
				rList.add(tlist.get(i).get("city").toString());
			}
		}
		return rList;
	}

}
