package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductRisk;

public interface GeProductRiskService {

	public abstract List<GeProductRisk> findGeProductRiskByCoreProductCode(String coreProductCode);

	public abstract String findGeProductRiskTree(String coreProductCode);
	
	public List findDutyRiskTree(String coreProductCode,String busAreaId);
	
	public GeProductRisk findByRiskCode(String code);
	
	public void addGeProductRisk(GeProductRisk risk);
	
	public void deleteGeProductRisk(String code);
	
	public void deleteGeProductRisk(GeProductRisk risk);
	
	public abstract List<GeProductRisk> findByQueryRule(QueryRule qu);
	
	public void deleteByProductId(String productId);
	
	public void updateGeProductRisk(GeProductRisk risk);
	
	public GeProductRisk findByserialNo(String serialNo);
}
