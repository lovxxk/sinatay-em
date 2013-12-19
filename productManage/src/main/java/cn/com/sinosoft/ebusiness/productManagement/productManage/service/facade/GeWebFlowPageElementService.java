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
	 * ��ѯ��Ʒ�ľ��������Ԫ�ش���
	 * @param productCode ��Ʒ����
	 * @param flowCode ���̴���
	 * @param pageCode ҳ�����
	 * @return Ԫ�ش��� 
	 */
	List<String> getElementCodesByOrder(String productCode,String flowCode,String pageCode);

	public abstract List<GeWebFlowPageElement> findGeWebFlowPageCoreProductCode(String coreProductCode, String serialNo);

	public abstract List<GePageElementConfig> findPageElementConfigByCoreProductCode(String coreProductCode);

	public abstract void addAllGeWebFlowPageElement(List<GeWebFlowPageElement> geWebFlowPageElementList, String coreProductCode);

	public abstract void updateGeWebFlowPageElementStatus(String coreProductCode,String elementCode);
	
	public abstract void updateGeWebFlowPageElementStatus(String status,String coreProductCode,String elementCode);

	public abstract void updateGeWebFlowPageElementStatus_all(String status,String coreProductCode);
}
