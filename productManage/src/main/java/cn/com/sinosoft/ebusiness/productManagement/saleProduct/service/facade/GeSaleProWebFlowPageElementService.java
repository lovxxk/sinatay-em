package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;


import java.util.List;

public interface GeSaleProWebFlowPageElementService {
	
	/**
	 * ��ѯ��Ʒ�ľ��������Ԫ�ش���
	 * @param productCode ��Ʒ����
	 * @param flowCode ���̴���
	 * @param pageCode ҳ�����
	 * @return Ԫ�ش��� 
	 */
	List<String> getElementCodesByOrder(String productCode,String flowCode,String pageCode);
}
