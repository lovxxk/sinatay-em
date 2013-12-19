package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInformbook;

public interface GeSaleProInformBookService {

	/**
	 * 根据查询规则查询投保告知
	 * @param queryRule 产品代码
	 * @return
	 */
	List<GeSaleProInformbook> findInformBooks(QueryRule queryRule);
	
	/**
	 * 根据产品代码查询经过排序的投保告知信息
	 * @param productCode
	 * @return
	 */
	List<GeSaleProInformbook> findInformBooks(String productCode);
}
