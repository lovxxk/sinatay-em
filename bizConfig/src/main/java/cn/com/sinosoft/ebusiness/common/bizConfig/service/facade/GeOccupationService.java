/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeOccupation;

/**
 * 职业类型
 * @since 2011-09-08
 */
public interface GeOccupationService {

	/**
	 * 查询职业类型
	 * @param queryRule	查询条件
	 * @return Page
	 */
	public List<GeOccupation> getGeOccupations(QueryRule queryRule);
	/**
	 * 查询职业
	 * @param occupationCode
	 * @return
	 */
	public GeOccupation getGeOccupationsByCode(String occupationCode);
	
}
