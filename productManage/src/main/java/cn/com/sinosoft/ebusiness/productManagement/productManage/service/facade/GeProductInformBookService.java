package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductInformBook;

public interface GeProductInformBookService {

	GeProductInformBook findById(String id);
	/**
	 * ���ݲ�ѯ�����ѯͶ����֪
	 * @param queryRule ��Ʒ����
	 * @return
	 */
	List<GeProductInformBook> findInformBooks(QueryRule queryRule);
	
	/**
	 * ���ݲ�Ʒ�����ѯ���������Ͷ����֪��Ϣ
	 * @param productCode
	 * @return
	 */
	List<GeProductInformBook> findInformBooks(String productCode);
}
