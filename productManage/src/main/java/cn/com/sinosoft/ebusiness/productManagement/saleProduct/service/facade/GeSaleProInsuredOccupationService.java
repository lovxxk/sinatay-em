/**
 * 
 */
package cn.com.sinosoft.ebusiness.productManagement.saleProduct.service.facade;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain.GeSaleProInsuredOccupation;

/**
 * 职业类型
 *  
 * @since 2011-09-08
 */
public interface GeSaleProInsuredOccupationService {

	
	/**
	 * 查询第一大类职业
	 */
	public List<GeSaleProInsuredOccupation> findGeOccupations(String insuredCode);
	
	/**
	 * 查询第二大类职业
	 */
	public List<GeSaleProInsuredOccupation> findGeOccupationsM(String insuredCode,String occupationCode);
}
