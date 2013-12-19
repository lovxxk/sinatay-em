package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProduct;

public interface GeSaleProductService {
	List<GeSaleProduct> findByQuery(QueryRule query);
	
	GeSaleProduct findByCode(String code);
	
	void deleteByCode(String code);
	/**
	 * ²éÑ¯×´Ì¬
	 * @param productCode
	 * @return
	 */
	public boolean getStatusBycode(String productCode);
}
