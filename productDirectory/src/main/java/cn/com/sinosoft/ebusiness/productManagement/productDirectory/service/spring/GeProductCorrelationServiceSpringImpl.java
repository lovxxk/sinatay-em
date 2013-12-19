package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeProductCorrelation;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeProductCorrelationService;

/***
 * 
 * 产品推荐
 *  
 * @version  1 created on 2011-10-11 下午04:42:16
 * 
 */
public class GeProductCorrelationServiceSpringImpl extends GenericDaoHibernate<GeProductCorrelation, String> implements GeProductCorrelationService{
	
	/***
	 * 
	 * 添加针对该产品推荐的产品
	 * @param geProductCorrelation
	 * 
	 */
	@LocusTrace(setDesc="添加针对该产品推荐的产品")
	public void addGeProductCorrelation(GeProductCorrelation geProductCorrelation) {
		super.save(geProductCorrelation);
	}
	
	/***
	 * 
	 * 添加针对该产品推荐的所有产品
	 * @param geProductCorrelationList
	 * 
	 */
	@LocusTrace(setDesc="添加针对该产品推荐的所有产品")
	public void addAllGeProductCorrelation(List<GeProductCorrelation> geProductCorrelationList) {
		super.saveAll(geProductCorrelationList);
	}
	
	/***
	 * 
	 * 根据eId删除针对该eId对应产品推荐的产品
	 * @param eId
	 * 
	 */
	@LocusTrace(setDesc="根据eId删除针对该eId对应产品推荐的产品")
	public void deleteGeProductCorrelationByProductEId(String eId) {
		super.deleteAll(findGeProductCorrelationByProductEId(eId));
	}
	
	/***
	 * 根据eId查询该eId对应产品推荐的产品 
	 * @param eId
	 * @return
	 */
	@LocusTrace(setDesc="根据eId查询该eId对应产品推荐的产品 ")
	public List<GeProductCorrelation> findGeProductCorrelationByProductEId(String eId) {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("geDirectoryByproduct.eid", eId);
			return super.find(queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据产品ID查询该产品推荐的产品，根据产品推荐度，产品热度，产品创建时间排序
	 */
	@LocusTrace(setDesc="根据产品ID查询该产品推荐的产品，根据产品推荐度，产品热度，产品创建时间排序 ")
	public List<GeDirectory> findTopNCorrelationProductByEid(int topN,String eid) {
		//查询推荐的产品
		Date queryDate = new Date();
		StringBuffer hqlBuffer = new StringBuffer("select geDirectory from GeProductCorrelation  as gpc inner join gpc.geDirectoryByrecommendProduct as geDirectory where 1 = 1 ");
		hqlBuffer.append("and gpc.geDirectoryByproduct.eid = ? ");
		hqlBuffer.append("and geDirectory.isProductShelf = ? ");
		hqlBuffer.append("and geDirectory.publishDate <= ? ");
		hqlBuffer.append("and geDirectory.stopDate >= ? ");
		hqlBuffer.append("order by gpc.correclation desc, geDirectory.productRecommend desc, geDirectory.createDate desc ");
		List<GeDirectory> geDirectoryList = super.findTopByHql(hqlBuffer.toString(), topN, new Object[]{eid, "01", queryDate, queryDate});
		return geDirectoryList;
	}
	
	/**
	 * 根据产品ID查询该产品推荐的产品，根据产品推荐度，产品热度，产品创建时间排序
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@LocusTrace(setDesc="根据产品ID查询该产品推荐的产品，根据产品推荐度，产品热度，产品创建时间排序 ")
	public List<GeDirectory> findTopNCorrelationProductByEid(List eids, Map paramsMap, int topN) {
		//查询推荐的产品
		Date queryDate = new Date();
		StringBuffer hqlBuffer = new StringBuffer("select geDirectory from GeProductCorrelation  as gpc inner join gpc.geDirectoryByrecommendProduct as geDirectory where 1 = 1 ");
		hqlBuffer.append("and gpc.geDirectoryByproduct.eid in " + eids.toString().replaceAll(",", "','").replace("[", "('").replace("]", "')") + " ");
		Iterator paramIterator = paramsMap.keySet().iterator();
		List paramValueList = new ArrayList();
		while (paramIterator.hasNext()) {
			String paramString = (String) paramIterator.next();
			hqlBuffer.append("and geDirectory." + paramString + " = ? ");
			paramValueList.add((String) paramsMap.get(paramString));
		}
		hqlBuffer.append("and geDirectory.isProductShelf = ? ");
		hqlBuffer.append("and geDirectory.publishDate <= ? ");
		hqlBuffer.append("and geDirectory.stopDate >= ? ");
		hqlBuffer.append("order by gpc.correclation desc, geDirectory.productRecommend desc, geDirectory.createDate desc ");
		paramValueList.add("01");
		paramValueList.add(queryDate);
		paramValueList.add(queryDate);
		List<GeDirectory> geDirectoryList = super.findTopByHql(hqlBuffer.toString(), topN, paramValueList.toArray());
		return geDirectoryList;
	}
	
}
