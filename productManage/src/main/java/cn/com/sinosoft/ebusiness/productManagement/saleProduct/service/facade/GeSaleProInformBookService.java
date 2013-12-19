package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInformbook;

public interface GeSaleProInformBookService {

	/**
	 * ���ݲ�ѯ�����ѯͶ����֪
	 * @param queryRule ��Ʒ����
	 * @return
	 */
	List<GeSaleProInformbook> findInformBooks(QueryRule queryRule);
	
	/**
	 * ���ݲ�Ʒ�����ѯ���������Ͷ����֪��Ϣ
	 * @param productCode
	 * @return
	 */
	List<GeSaleProInformbook> findInformBooks(String productCode);
}
