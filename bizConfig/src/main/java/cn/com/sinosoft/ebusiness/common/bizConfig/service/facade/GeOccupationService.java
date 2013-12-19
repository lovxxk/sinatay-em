/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeOccupation;

/**
 * ְҵ����
 * @since 2011-09-08
 */
public interface GeOccupationService {

	/**
	 * ��ѯְҵ����
	 * @param queryRule	��ѯ����
	 * @return Page
	 */
	public List<GeOccupation> getGeOccupations(QueryRule queryRule);
	/**
	 * ��ѯְҵ
	 * @param occupationCode
	 * @return
	 */
	public GeOccupation getGeOccupationsByCode(String occupationCode);
	
}
