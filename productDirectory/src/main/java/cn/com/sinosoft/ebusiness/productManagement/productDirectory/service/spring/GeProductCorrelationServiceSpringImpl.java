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
 * ��Ʒ�Ƽ�
 *  
 * @version  1 created on 2011-10-11 ����04:42:16
 * 
 */
public class GeProductCorrelationServiceSpringImpl extends GenericDaoHibernate<GeProductCorrelation, String> implements GeProductCorrelationService{
	
	/***
	 * 
	 * �����Ըò�Ʒ�Ƽ��Ĳ�Ʒ
	 * @param geProductCorrelation
	 * 
	 */
	@LocusTrace(setDesc="�����Ըò�Ʒ�Ƽ��Ĳ�Ʒ")
	public void addGeProductCorrelation(GeProductCorrelation geProductCorrelation) {
		super.save(geProductCorrelation);
	}
	
	/***
	 * 
	 * �����Ըò�Ʒ�Ƽ������в�Ʒ
	 * @param geProductCorrelationList
	 * 
	 */
	@LocusTrace(setDesc="�����Ըò�Ʒ�Ƽ������в�Ʒ")
	public void addAllGeProductCorrelation(List<GeProductCorrelation> geProductCorrelationList) {
		super.saveAll(geProductCorrelationList);
	}
	
	/***
	 * 
	 * ����eIdɾ����Ը�eId��Ӧ��Ʒ�Ƽ��Ĳ�Ʒ
	 * @param eId
	 * 
	 */
	@LocusTrace(setDesc="����eIdɾ����Ը�eId��Ӧ��Ʒ�Ƽ��Ĳ�Ʒ")
	public void deleteGeProductCorrelationByProductEId(String eId) {
		super.deleteAll(findGeProductCorrelationByProductEId(eId));
	}
	
	/***
	 * ����eId��ѯ��eId��Ӧ��Ʒ�Ƽ��Ĳ�Ʒ 
	 * @param eId
	 * @return
	 */
	@LocusTrace(setDesc="����eId��ѯ��eId��Ӧ��Ʒ�Ƽ��Ĳ�Ʒ ")
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
	 * ���ݲ�ƷID��ѯ�ò�Ʒ�Ƽ��Ĳ�Ʒ�����ݲ�Ʒ�Ƽ��ȣ���Ʒ�ȶȣ���Ʒ����ʱ������
	 */
	@LocusTrace(setDesc="���ݲ�ƷID��ѯ�ò�Ʒ�Ƽ��Ĳ�Ʒ�����ݲ�Ʒ�Ƽ��ȣ���Ʒ�ȶȣ���Ʒ����ʱ������ ")
	public List<GeDirectory> findTopNCorrelationProductByEid(int topN,String eid) {
		//��ѯ�Ƽ��Ĳ�Ʒ
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
	 * ���ݲ�ƷID��ѯ�ò�Ʒ�Ƽ��Ĳ�Ʒ�����ݲ�Ʒ�Ƽ��ȣ���Ʒ�ȶȣ���Ʒ����ʱ������
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@LocusTrace(setDesc="���ݲ�ƷID��ѯ�ò�Ʒ�Ƽ��Ĳ�Ʒ�����ݲ�Ʒ�Ƽ��ȣ���Ʒ�ȶȣ���Ʒ����ʱ������ ")
	public List<GeDirectory> findTopNCorrelationProductByEid(List eids, Map paramsMap, int topN) {
		//��ѯ�Ƽ��Ĳ�Ʒ
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
