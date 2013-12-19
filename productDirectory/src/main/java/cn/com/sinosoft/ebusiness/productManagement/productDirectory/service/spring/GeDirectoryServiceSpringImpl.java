
package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;

/***
 * 
 *  
 * @version  1 created on 2011-9-19 上午11:07:32
 */
public class GeDirectoryServiceSpringImpl extends GenericDaoHibernate<GeDirectory, String> implements GeDirectoryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/***
	 * 增加产品目录
	 * @param geDirectory
	 */
	@LocusTrace(setDesc="增加产品 ")
	public void addGeDirectory(GeDirectory geDirectory) {
		super.save(geDirectory);
	}
	
	@LocusTrace(setDesc="更新产品上架状态")
	public void updateProductShelf(GeDirectory geDirectory) {
		try {
			super.save(geDirectory);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
	@LocusTrace(setDesc="删除产品")
	public void deleteGeDirectory(String eId) {
		try {
			super.deleteByPK(eId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@LocusTrace(setDesc="根据属性attrId及其他查询条件查询该属性关联的产品")
	public Page findGeDirectoryByAttrIDAndQueryDate(String attrID,Date queryDate, int pageNo, int pageSize) {
		try {
			StringBuffer hql = new StringBuffer("select geDirectory from GeDirectoryAttributeRelate relate inner join relate.geDirectory geDirectory  where relate.geDirectoryAttributeInfo.attrID = ? and geDirectory.isProductShelf = ? and geDirectory.publishDate <= ? and geDirectory.stopDate >=?  order by geDirectory.productRecommend desc, geDirectory.createDate desc ");
			return super.findByHqlNoLimit(hql.toString(), pageNo, pageSize, new Object[]{attrID, "01", queryDate, queryDate});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@LocusTrace(setDesc="根据属性attrId及其他查询条件查询该属性关联的产品")
	public Page findGeDirectoryByAttrIDAndQueryDate(String attrID, Map paramsMap, Date queryDate, int pageNo, int pageSize) {
		try {
			StringBuffer hqlString = new StringBuffer("select geDirectory from GeDirectoryAttributeRelate relate inner join relate.geDirectory geDirectory  where relate.geDirectoryAttributeInfo.attrID = ? ");
			Iterator paramIterator = paramsMap.keySet().iterator();
			List paramValueList = new ArrayList();
			paramValueList.add(attrID);
			while (paramIterator.hasNext()) {
				String paramString = (String) paramIterator.next();
				hqlString.append("and geDirectory." + paramString + " = ? ");
				paramValueList.add((String) paramsMap.get(paramString));
			}
			hqlString.append("and geDirectory.publishDate <= ? and geDirectory.stopDate >=? ");
			hqlString.append("order by geDirectory.productRecommend desc, geDirectory.createDate desc ");
			paramValueList.add(queryDate);
			paramValueList.add(queryDate);
			return super.findByHqlNoLimit(hqlString.toString(),  pageNo, pageSize,paramValueList.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@LocusTrace(setDesc="根据属性attrId及其他查询条件查询该属性关联的产品")
	public List<GeDirectory> findGeDirectoryByEids(String[] eids) {
		try {
			List<String> eidList = new ArrayList<String>();
			eidList = Arrays.asList(eids);
			//设置查询步长
			int step = 1000;
			if (eidList.size() < 1000)
				step = eidList.size();
			//根据产品目录EID查询产品目录基本信息
			QueryRule geDirectoryQueryRule = QueryRule.getInstance();
			List<GeDirectory> geDirectoryList = new ArrayList<GeDirectory>();
			for (int i =0; i < eidList.size();) {
				List queryList = eidList.subList(i, step);
				geDirectoryQueryRule.addIn("eid", queryList);
				geDirectoryQueryRule.addEqual("isProductShelf", "01");
				geDirectoryList.addAll(super.find(GeDirectory.class, geDirectoryQueryRule));
				i += step;
			}
			return geDirectoryList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询“产品目录基本信息表”所有数据
	 */
	@LocusTrace(setDesc="查询产品")
	public Page searchGeDirectory(QueryRule queryRule, int pageNo, int pageSize) {
		try {
			return super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询要修改的产品目录基本信息
	 * @param eId
	 * @return
	 * @throws Exception
	 */
	@LocusTrace(setDesc="根据Eid查询产品")
	public GeDirectory findGeDirectoryByEId(String eId) {
		try {
			return	super.findUnique("eid", eId);
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新产品基本信息
	 */
	@LocusTrace(setDesc="更新产品基本信息")
	public void updateGeDirectory(GeDirectory geDirectory) {
		try {
			GeDirectory update = super.findUnique("eid", geDirectory.getEid());
			List<String> ignorePropertyList = new ArrayList<String>();
			ignorePropertyList.add("eid");
			ignorePropertyList.add("createDate");
			ignorePropertyList.add("isProductShelf");
			if (update.getSmallPictureOne() != null && geDirectory.getSmallPictureOne() == null) {
				ignorePropertyList.add("smallPictureOne");
			}
			if (update.getSmallPictureTwo() != null && geDirectory.getSmallPictureTwo() == null) {
				ignorePropertyList.add("smallPictureTwo");
			}
			if (update.getMiddlePictureOne() != null && geDirectory.getMiddlePictureOne() == null) {
				ignorePropertyList.add("middlePictureOne");
			} 
			if (update.getMiddlePictureTwo() != null && geDirectory.getMiddlePictureTwo() == null) {
				ignorePropertyList.add("middlePictureTwo");
			} 
			if (update.getBigPictureOne() != null && geDirectory.getBigPictureOne() == null) {
				ignorePropertyList.add("bigPictureOne");
			} 
			if (update.getBigPictureTwo() != null && geDirectory.getBigPictureTwo() == null) {
				ignorePropertyList.add("bigPictureTwo");
			} 
			String[] ignoreProperties = new String[ignorePropertyList.size()];
			BeanUtils.copyProperties(geDirectory, update, ignorePropertyList.toArray(ignoreProperties));
			super.update(update);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 查询为推荐的产品 
	 * @param eid 推荐产品EID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LocusTrace(setDesc="查询为推荐的产品 ")
	public List<GeDirectory> findNoRecommendProduct(String eid) {
		if (eid != null) {
			String hql = "from GeDirectory d where d.eid not in(select recommendProduct.geDirectoryByrecommendProduct.eid from GeProductCorrelation  recommendProduct where recommendProduct.geDirectoryByproduct.eid = ?) and d.eid != ?";
			return super.findByHql(hql, new Object[]{eid,eid});
		}
		return null;
		
	}
	
	/**
	 * 查询未推荐的产品
	 * @param eid 电子商务产品ID
	 * @param businessArea 业务领域，以逗号分隔
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LocusTrace(setDesc="查询未推荐的产品")
	public List<GeDirectory> findNoRecommendProduct(String eid,String businessArea){
		if (StringUtils.isNotBlank(eid) && StringUtils.isNotBlank(businessArea)) {
			String hql = "from GeDirectory d where d.eid not in(select recommendProduct.geDirectoryByrecommendProduct.eid from GeProductCorrelation  recommendProduct where recommendProduct.geDirectoryByproduct.eid = ?) and d.eid != ? and d.businessArea in ('"+businessArea.replaceAll(",", "','")+"') order by d.eid asc";
			return super.findByHql(hql, new Object[]{eid,eid});
		}
		return null;
	}
	
	/**
	 * 查询未推荐的产品
	 * @param eid 电子商务产品ID
	 * @param noRecommendProductEId 未推荐的电子商务产品ID,用于模糊查询
	 * @param businessArea 业务领域，以逗号分隔
	 * @param noRecommendProductCoreProductCode 产品代码/险种代码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LocusTrace(setDesc="查询未推荐的产品")
	public List<GeDirectory> findNoRecommendProduct(String eid,String noRecommendProductEId,String businessArea,String noRecommendProductCoreProductCode){
		if (StringUtils.isNotBlank(eid) && StringUtils.isNotBlank(businessArea)) {
			String hql = "from GeDirectory d where d.eid not in(select recommendProduct.geDirectoryByrecommendProduct.eid from GeProductCorrelation  recommendProduct where recommendProduct.geDirectoryByproduct.eid = ?) and d.eid != ? and d.businessArea in ('"+businessArea.replaceAll(",", "','")+"') ";
			List<Object> list = new ArrayList<Object>();
			list.add(eid);
			list.add(eid);
			if (StringUtils.isNotBlank(noRecommendProductEId)) {
				hql += " and d.eid like ? ";
				list.add("%"+noRecommendProductEId+"%");
			}
			if (StringUtils.isNotBlank(noRecommendProductCoreProductCode)) {
				hql += " and d.coreProductCode like ? ";
				list.add("%"+noRecommendProductCoreProductCode+"%");
			}
			hql += " order by d.eid asc";
			return super.findByHql(hql, list.toArray(new String[list.size()]));
		}
		return null;
	}
	
	/***
	 * 按
	 * ①是否网销
	 * ②业务类型
	 * 查询产品表的前两条记录
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@LocusTrace(setDesc="查询产品表的前两条记录")
	public List<GeDirectory> findTopNumberDirectoryByParams(Map paramsMap,int topNumber){
		Date queryDate = new Date();
		StringBuffer sqlString = new StringBuffer("from GeDirectory t where 1 = 1 ");
		Iterator paramIterator = paramsMap.keySet().iterator();
		List paramValueList = new ArrayList();
		while (paramIterator.hasNext()) {
			String paramString = (String) paramIterator.next();
			sqlString.append("and t." + paramString + " = ? ");
			paramValueList.add((String) paramsMap.get(paramString));
		}
		sqlString.append("and t.publishDate <= ? ");
		sqlString.append("and t.stopDate >= ? ");
		sqlString.append("order by t.productRecommend desc, t.createDate desc ");
		paramValueList.add(queryDate);
		paramValueList.add(queryDate);
		List<GeDirectory> geDirectoryList = super.findTopByHql(sqlString.toString(), topNumber, paramValueList.toArray());
		return geDirectoryList;
	}
	
	/***
	 * 查询产品表推荐度最高的前4条记录
	 */
	@LocusTrace(setDesc="查询产品表推荐度最高的前4条记录")
	public List<GeDirectory> findGeDirectoryTopNRecommendDesc(int topNum,String productSection){
		if(StringUtils.isNotBlank(productSection)){
			return super.findTopByHql("from GeDirectory t where t.isProductShelf = ? and t.productSection = ? order by t.productRecommend desc, t.createDate desc ", topNum, new String[]{"01", productSection});
		}else{
			return super.findTopByHql("from GeDirectory t where t.isProductShelf = ? order by t.productRecommend desc, t.createDate desc ", topNum, new String[]{"01"});
		}
	}
	
	/***
	 * 查询产品表推荐度最高的前5条网销记录
	 */
	@LocusTrace(setDesc="查询产品表推荐度最高的前5条网销记录")
	public List<GeDirectory> findGeDirectoryTopNNetsale(int topNum){
			return super.findTopByHql("from GeDirectory t where t.isProductShelf = ? and t.isNetSale = ? order by t.productRecommend desc, t.createDate desc ", topNum, new String[]{"01", "01"});
	}
	
	/***
	 * 根据条件查询产品目录
	 * @param queryRule
	 * @return
	 */
	@LocusTrace(setDesc="根据条件查询产品目录")
	public List<GeDirectory> findGeDirectoryByQueryRule(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public List<Map<String, Object>> findGeDirectoryBySaleTop() {
		List<Object> objectList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select ");
		sql.append("gd.eid,");
		sql.append("gd.coreproductcode,");
		sql.append("gd.productname,");
		sql.append("a.nub");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("ge_directory gd");
		sql.append(" inner join ");
		sql.append("(select orf.productcode ,count(*) as nub ");
		sql.append(" from ");
		sql.append(" orderform orf ");
		sql.append(" where orf.paystatus=1 ");
		sql.append(" group by orf.productcode ) a ");
		sql.append(" on gd.coreproductcode=a.productcode ");
		sql.append(" order by a.nub desc ");
		
		return jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
	}
	
}
