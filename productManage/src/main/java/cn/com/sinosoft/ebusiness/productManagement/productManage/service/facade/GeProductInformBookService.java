package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInformBook;

public interface GeProductInformBookService {

	GeProductInformBook findById(String id);
	/**
	 * 根据查询规则查询投保告知
	 * @param queryRule 产品代码
	 * @return
	 */
	List<GeProductInformBook> findInformBooks(QueryRule queryRule);
	
	/**
	 * 根据产品代码查询经过排序的投保告知信息
	 * @param productCode
	 * @return
	 */
	List<GeProductInformBook> findInformBooks(String productCode);
}
