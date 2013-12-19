package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import java.util.List;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProDutyAttrAllowVal;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeDutyAttrAllowValueService;

public class GeDutyAttrAllowValueServiceImpl extends
		GenericDaoHibernate<GeProDutyAttrAllowVal, String> implements GeDutyAttrAllowValueService {

	public List<GeProDutyAttrAllowVal> findByQuery(QueryRule Query) {
		// TODO Auto-generated method stub
		return super.find(Query);
	}

	public void deleteByDutyId(String dutyId) {
		// TODO Auto-generated method stub
		QueryRule q  = QueryRule.getInstance();
		q.addEqual("geProductDuty.serialNo", dutyId);
		List<GeProDutyAttrAllowVal> list = find(q);
		for (GeProDutyAttrAllowVal geProDutyAttrAllowVal : list) {
			delete(geProDutyAttrAllowVal);
		}
	}

	public void add(GeProDutyAttrAllowVal allow) {
		// TODO Auto-generated method stub
		super.save(allow);
	}

	public void update(GeProDutyAttrAllowVal allow) {
		// TODO Auto-generated method stub
		super.update(allow);
	}

	public GeProDutyAttrAllowVal findByPk(String pk) {
		// TODO Auto-generated method stub
		QueryRule q  = QueryRule.getInstance();
		q.addEqual("serialNo", pk);
		
		List<GeProDutyAttrAllowVal> list = find(q);
		if(list.size()>0)
			return list.get(0);
		else 
			return null;
	}

	public void delete(GeProDutyAttrAllowVal allow) {
		// TODO Auto-generated method stub
		super.delete(allow);
	}



}
