package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePageElementConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeWebFlowPageElement;

public interface GeWebFlowPageElementService {
	void update(GeWebFlowPageElement el);
	
	void addGeWebFlowPageElement(GeWebFlowPageElement geWebFlowPageElement);
	
	void delete(GeWebFlowPageElement geWebFlowPageElement);
	
	GeWebFlowPageElement findGeWebFlowPageElementBySerialNo(String serialNo);
	
	List<GeWebFlowPageElement> findGeWebFlowPageElementByQueryRule(QueryRule queryRule);
	
	/**
	 * 查询产品的经过排序的元素代码
	 * @param productCode 产品代码
	 * @param flowCode 流程代码
	 * @param pageCode 页面代码
	 * @return 元素代码 
	 */
	List<String> getElementCodesByOrder(String productCode,String flowCode,String pageCode);

	public abstract List<GeWebFlowPageElement> findGeWebFlowPageCoreProductCode(String coreProductCode, String serialNo);

	public abstract List<GePageElementConfig> findPageElementConfigByCoreProductCode(String coreProductCode);

	public abstract void addAllGeWebFlowPageElement(List<GeWebFlowPageElement> geWebFlowPageElementList, String coreProductCode);

	public abstract void updateGeWebFlowPageElementStatus(String coreProductCode,String elementCode);
	
	public abstract void updateGeWebFlowPageElementStatus(String status,String coreProductCode,String elementCode);

	public abstract void updateGeWebFlowPageElementStatus_all(String status,String coreProductCode);
}
