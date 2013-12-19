/**
 * 
 */
package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredOccupation;

/**
 * ְҵ����
 *  
 * @since 2011-09-08
 */
public interface GeSaleProInsuredOccupationService {

	
	/**
	 * ��ѯ��һ����ְҵ
	 */
	public List<GeSaleProInsuredOccupation> findGeOccupations(String insuredCode);
	
	/**
	 * ��ѯ�ڶ�����ְҵ
	 */
	public List<GeSaleProInsuredOccupation> findGeOccupationsM(String insuredCode,String occupationCode);
}
