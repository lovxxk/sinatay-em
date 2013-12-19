package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductDuty;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductDutyService;

public class GeProductDutyServiceSpringImpl extends
GenericDaoHibernate<GeProductDuty, String> implements GeProductDutyService {
	
	public List<GeProductDuty> findGeProductDutyByQueryRule(QueryRule queryRule) {
		return super.find(queryRule);
	}

	public GeProductDuty findBySerialNo(String pk) {
		// TODO Auto-generated method stub
		QueryRule q = QueryRule.getInstance();
		q.addEqual("serialNo", pk);
		return super.findUnique(q);
	}

	public void addGeProductDuty(GeProductDuty duty) {
		// TODO Auto-generated method stub
		super.save(duty);
	}

	public void deleteGeProductDuty(String pk) {
		// TODO Auto-generated method stub
		GeProductDuty duty = findBySerialNo(pk);
		super.delete(duty);
	}

	public void deleteGeProductDuty(GeProductDuty duty) {
		// TODO Auto-generated method stub
		super.delete(duty);
	}

	public void deleteByProductId(String productId) {
		// TODO Auto-generated method stub
		QueryRule q = QueryRule.getInstance();
		q.addEqual("geProductMain.coreProductCode", productId);
		List<GeProductDuty> list = findGeProductDutyByQueryRule(q);
		for (GeProductDuty geProductDuty : list) {
			deleteGeProductDuty(geProductDuty);
		}
	}

	public void updateGeProductDuty(GeProductDuty duty) {
		// TODO Auto-generated method stub
		super.update(duty);
	}


	
}
