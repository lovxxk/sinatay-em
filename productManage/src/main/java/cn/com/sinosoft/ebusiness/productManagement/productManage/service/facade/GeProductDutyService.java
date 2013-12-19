package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductDuty;

public interface GeProductDutyService {

	public abstract List<GeProductDuty> findGeProductDutyByQueryRule(
			QueryRule queryRule);
	
	public GeProductDuty findBySerialNo(String pk);
	
	public void addGeProductDuty(GeProductDuty duty);
	
	public void deleteGeProductDuty(String pk);
	
	public void deleteGeProductDuty(GeProductDuty duty);

	public void deleteByProductId(String productId);
	
	public void updateGeProductDuty(GeProductDuty duty);
}
