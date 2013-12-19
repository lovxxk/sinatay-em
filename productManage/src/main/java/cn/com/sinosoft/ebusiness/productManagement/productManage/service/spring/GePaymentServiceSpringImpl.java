package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GePaymentService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.ProductManageException;

public class GePaymentServiceSpringImpl extends
		GenericDaoHibernate<GePayment, String> implements GePaymentService {

	public void createPayment(GePayment gePayment) {
		try {
			super.save(gePayment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GePayment findPaymentById(String paymentId) {
		return super.get(paymentId);
	}

	public Page findPayment(QueryRule queryRule, int pageNo, int pageSize) {
		try {
			return super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updatePayment(GePayment gePayment) {
		try {
			GePayment update = super.findUnique("paymentId", gePayment.getPaymentId());
			List<String> ignorePropertyList = new ArrayList<String>();
			ignorePropertyList.add("createTime");
			if (update.getLogoImg() != null && gePayment.getLogoImg() == null) {
				ignorePropertyList.add("logoImg");
			}
			String[] ignoreProperties = new String[ignorePropertyList.size()];
			
			BeanUtils.copyProperties(gePayment, update, ignorePropertyList.toArray(ignoreProperties));
			super.update(update);
		}catch(BeansException e){
			e.printStackTrace();
		}
	}
	
	public List<GePayment> findPayments(QueryRule queryRule){
		try {
			return super.find(queryRule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw ProductManageException.newInstanceMsg("查询支付方式出现异常", e);
		}
	}

	public int findCountByPaymentCode(String paymentCode) {
		QueryRule q = QueryRule.getInstance();
		q.addEqual("paymentCode", paymentCode);
		List list = super.find(q);
		if(list!=null)
			return list.size();
		return 0;
	}

	public void updatePayMent(GePayment gePayment) {
		// TODO Auto-generated method stub
		super.update(gePayment);
	}

	public boolean excuteSql(final String sql) {
		getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Connection CurConn = session.connection();
				PreparedStatement ps = CurConn.prepareStatement(sql);
				ps.execute();
				ps.close();
				session.flush();
				return true;
			}
			} );
		return false;
	}
	
	
	public void clearByPayMentId(String paymentId) {
		 final String sql = "delete ge_payment_city c where c.paymentid='"+paymentId+"'";
		 excuteSql(sql);
	}
	
	public List<GePayment> findPaymentsByDeptId(String deptId) {
		String hqlString = "from GePayment m where m.paymentId in (select g.paymentId from GePayment g , GePaymentCity t where g.paymentId = t.id.paymentId and t.id.cityId = ?  group by g.paymentId) order by m.paymentCode";
		return super.findByHql(hqlString, deptId);
	}
	public boolean delPaymentByPaymentId(String paymentId){
		try {
			super.deleteByPK(paymentId);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public List<GePayment> findPaymentsByDeptIds(List<String> deptIds) {
		String hqlString = "from GePayment m where m.paymentId in (select g.paymentId from GePayment g,GePaymentCity t where g.paymentId = t.id.paymentId and t.id.cityId in (";
		String ids = "";
		for (int i = 0; i < deptIds.size(); i++) {
			ids += "','" + deptIds.get(i);
		}
		hqlString += ids.substring(2) + "') group by g.paymentId) order by m.paymentCode";
		return super.findByHql(hqlString, null);
	}

	public List<String> getPayMentIdsByCity(String city) {
		String sql = "select distinct paymentid from ge_payment_city c where "+city;
		
		List<String> result = new ArrayList<String>();
		List list = super.findBySql(sql, null);
		if(list!=null && list.size()>0){
			for (Object object : list) {
				result.add((String)object);
			}
		}
		return result;
	}

}
