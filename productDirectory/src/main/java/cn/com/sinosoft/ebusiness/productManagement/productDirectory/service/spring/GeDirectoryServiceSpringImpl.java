
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
 * @version  1 created on 2011-9-19 ����11:07:32
 */
public class GeDirectoryServiceSpringImpl extends GenericDaoHibernate<GeDirectory, String> implements GeDirectoryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/***
	 * ���Ӳ�ƷĿ¼
	 * @param geDirectory
	 */
	@LocusTrace(setDesc="���Ӳ�Ʒ ")
	public void addGeDirectory(GeDirectory geDirectory) {
		super.save(geDirectory);
	}
	
	@LocusTrace(setDesc="���²�Ʒ�ϼ�״̬")
	public void updateProductShelf(GeDirectory geDirectory) {
		try {
			super.save(geDirectory);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
	@LocusTrace(setDesc="ɾ����Ʒ")
	public void deleteGeDirectory(String eId) {
		try {
			super.deleteByPK(eId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@LocusTrace(setDesc="��������attrId��������ѯ������ѯ�����Թ����Ĳ�Ʒ")
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
	@LocusTrace(setDesc="��������attrId��������ѯ������ѯ�����Թ����Ĳ�Ʒ")
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
	@LocusTrace(setDesc="��������attrId��������ѯ������ѯ�����Թ����Ĳ�Ʒ")
	public List<GeDirectory> findGeDirectoryByEids(String[] eids) {
		try {
			List<String> eidList = new ArrayList<String>();
			eidList = Arrays.asList(eids);
			//���ò�ѯ����
			int step = 1000;
			if (eidList.size() < 1000)
				step = eidList.size();
			//���ݲ�ƷĿ¼EID��ѯ��ƷĿ¼������Ϣ
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
	 * ��ѯ����ƷĿ¼������Ϣ����������
	 */
	@LocusTrace(setDesc="��ѯ��Ʒ")
	public Page searchGeDirectory(QueryRule queryRule, int pageNo, int pageSize) {
		try {
			return super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯҪ�޸ĵĲ�ƷĿ¼������Ϣ
	 * @param eId
	 * @return
	 * @throws Exception
	 */
	@LocusTrace(setDesc="����Eid��ѯ��Ʒ")
	public GeDirectory findGeDirectoryByEId(String eId) {
		try {
			return	super.findUnique("eid", eId);
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���²�Ʒ������Ϣ
	 */
	@LocusTrace(setDesc="���²�Ʒ������Ϣ")
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
	 * ��ѯΪ�Ƽ��Ĳ�Ʒ 
	 * @param eid �Ƽ���ƷEID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LocusTrace(setDesc="��ѯΪ�Ƽ��Ĳ�Ʒ ")
	public List<GeDirectory> findNoRecommendProduct(String eid) {
		if (eid != null) {
			String hql = "from GeDirectory d where d.eid not in(select recommendProduct.geDirectoryByrecommendProduct.eid from GeProductCorrelation  recommendProduct where recommendProduct.geDirectoryByproduct.eid = ?) and d.eid != ?";
			return super.findByHql(hql, new Object[]{eid,eid});
		}
		return null;
		
	}
	
	/**
	 * ��ѯδ�Ƽ��Ĳ�Ʒ
	 * @param eid ���������ƷID
	 * @param businessArea ҵ�������Զ��ŷָ�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LocusTrace(setDesc="��ѯδ�Ƽ��Ĳ�Ʒ")
	public List<GeDirectory> findNoRecommendProduct(String eid,String businessArea){
		if (StringUtils.isNotBlank(eid) && StringUtils.isNotBlank(businessArea)) {
			String hql = "from GeDirectory d where d.eid not in(select recommendProduct.geDirectoryByrecommendProduct.eid from GeProductCorrelation  recommendProduct where recommendProduct.geDirectoryByproduct.eid = ?) and d.eid != ? and d.businessArea in ('"+businessArea.replaceAll(",", "','")+"') order by d.eid asc";
			return super.findByHql(hql, new Object[]{eid,eid});
		}
		return null;
	}
	
	/**
	 * ��ѯδ�Ƽ��Ĳ�Ʒ
	 * @param eid ���������ƷID
	 * @param noRecommendProductEId δ�Ƽ��ĵ��������ƷID,����ģ����ѯ
	 * @param businessArea ҵ�������Զ��ŷָ�
	 * @param noRecommendProductCoreProductCode ��Ʒ����/���ִ���
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LocusTrace(setDesc="��ѯδ�Ƽ��Ĳ�Ʒ")
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
	 * ��
	 * ���Ƿ�����
	 * ��ҵ������
	 * ��ѯ��Ʒ���ǰ������¼
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@LocusTrace(setDesc="��ѯ��Ʒ���ǰ������¼")
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
	 * ��ѯ��Ʒ���Ƽ�����ߵ�ǰ4����¼
	 */
	@LocusTrace(setDesc="��ѯ��Ʒ���Ƽ�����ߵ�ǰ4����¼")
	public List<GeDirectory> findGeDirectoryTopNRecommendDesc(int topNum,String productSection){
		if(StringUtils.isNotBlank(productSection)){
			return super.findTopByHql("from GeDirectory t where t.isProductShelf = ? and t.productSection = ? order by t.productRecommend desc, t.createDate desc ", topNum, new String[]{"01", productSection});
		}else{
			return super.findTopByHql("from GeDirectory t where t.isProductShelf = ? order by t.productRecommend desc, t.createDate desc ", topNum, new String[]{"01"});
		}
	}
	
	/***
	 * ��ѯ��Ʒ���Ƽ�����ߵ�ǰ5��������¼
	 */
	@LocusTrace(setDesc="��ѯ��Ʒ���Ƽ�����ߵ�ǰ5��������¼")
	public List<GeDirectory> findGeDirectoryTopNNetsale(int topNum){
			return super.findTopByHql("from GeDirectory t where t.isProductShelf = ? and t.isNetSale = ? order by t.productRecommend desc, t.createDate desc ", topNum, new String[]{"01", "01"});
	}
	
	/***
	 * ����������ѯ��ƷĿ¼
	 * @param queryRule
	 * @return
	 */
	@LocusTrace(setDesc="����������ѯ��ƷĿ¼")
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
