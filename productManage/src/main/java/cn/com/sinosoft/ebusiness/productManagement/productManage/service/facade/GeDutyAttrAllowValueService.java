package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProDutyAttrAllowVal;

public interface GeDutyAttrAllowValueService {
	public List<GeProDutyAttrAllowVal> findByQuery(QueryRule Query);
	
	public void deleteByDutyId(String dutyId);
	
	public void add(GeProDutyAttrAllowVal allow);
	
	public void update(GeProDutyAttrAllowVal allow);
	
	public GeProDutyAttrAllowVal findByPk(String pk);
	
	public void delete(GeProDutyAttrAllowVal allow);
	
}
