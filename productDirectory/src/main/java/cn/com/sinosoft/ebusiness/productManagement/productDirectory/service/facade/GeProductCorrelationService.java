package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeProductCorrelation;

public interface GeProductCorrelationService {

	/***
	 * 
	 * 添加针对该产品推荐的产品
	 * @param geProductCorrelation
	 * 
	 */
	public abstract void addGeProductCorrelation(
			GeProductCorrelation geProductCorrelation);

	/***
	 * 
	 * 添加针对该产品推荐的所有产品
	 * @param geProductCorrelationList
	 * 
	 */
	public abstract void addAllGeProductCorrelation(
			List<GeProductCorrelation> geProductCorrelationList);

	/***
	 * 
	 * 根据eId删除针对该eId对应产品推荐的产品
	 * @param eId
	 * 
	 */
	public abstract void deleteGeProductCorrelationByProductEId(String eId);

	/***
	 * 根据eId查询该eId对应产品推荐的产品 
	 * @param eId
	 * @return
	 */
	public abstract List<GeProductCorrelation> findGeProductCorrelationByProductEId(
			String eId);
	
	public List<GeDirectory> findTopNCorrelationProductByEid(int topN,String eid);

	public abstract List<GeDirectory> findTopNCorrelationProductByEid(List eid,
			Map paramsMap, int topN);

}
