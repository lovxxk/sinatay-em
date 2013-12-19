package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;


import java.util.List;

public interface GeSaleProWebFlowPageElementService {
	
	/**
	 * 查询产品的经过排序的元素代码
	 * @param productCode 产品代码
	 * @param flowCode 流程代码
	 * @param pageCode 页面代码
	 * @return 元素代码 
	 */
	List<String> getElementCodesByOrder(String productCode,String flowCode,String pageCode);
}
