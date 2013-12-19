package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;

public interface GeDirectoryService {

	public abstract Page searchGeDirectory(QueryRule queryRule, int pageNo, int pageSize);
	
	public abstract GeDirectory findGeDirectoryByEId(String eId) throws Exception;
	
	public abstract void deleteGeDirectory(String eId);
	
	public List<GeDirectory> findNoRecommendProduct(String eid,String businessArea) ;
	
	public List<GeDirectory> findNoRecommendProduct(String eid,String noRecommendProductEId,String businessArea,String noRecommendProductCoreProductCode) ;

	public abstract void addGeDirectory(GeDirectory geDirectory);

	public abstract void updateProductShelf(GeDirectory geDirectory);

	public void updateGeDirectory(GeDirectory geDirectory);

	public abstract Page findGeDirectoryByAttrIDAndQueryDate(String attrID, Date queryDate, int pageNo, int pageSize);

	public abstract List<GeDirectory> findGeDirectoryByEids(String[] eids);
	
	public List<GeDirectory> findGeDirectoryTopNRecommendDesc(int topNum,String productSection);
	
	public List<GeDirectory> findGeDirectoryTopNNetsale(int topNum);

	public abstract List<GeDirectory> findNoRecommendProduct(String eid);

	public abstract List<GeDirectory> findGeDirectoryByQueryRule(QueryRule queryRule);

	public abstract List<GeDirectory> findTopNumberDirectoryByParams(Map paramsMap, int topNumber);

	public abstract Page findGeDirectoryByAttrIDAndQueryDate(String attrID, Map paramsMap, Date queryDate, int pageNo, int pageSize);
	
	public List<Map<String, Object>> findGeDirectoryBySaleTop();
}
