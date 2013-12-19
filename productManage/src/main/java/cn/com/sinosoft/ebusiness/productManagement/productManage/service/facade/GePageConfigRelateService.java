package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageConfigRelate;


public interface GePageConfigRelateService {

	public abstract List<GePageConfigRelate> findGePageConfigByPageCode(String pageCode);
	
	List<GePageConfigRelate> getEneitys(QueryRule q);
	
	void update(GePageConfigRelate g);
}
