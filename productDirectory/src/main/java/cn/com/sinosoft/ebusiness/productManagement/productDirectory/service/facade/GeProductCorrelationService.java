package cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeProductCorrelation;

public interface GeProductCorrelationService {

	/***
	 * 
	 * �����Ըò�Ʒ�Ƽ��Ĳ�Ʒ
	 * @param geProductCorrelation
	 * 
	 */
	public abstract void addGeProductCorrelation(
			GeProductCorrelation geProductCorrelation);

	/***
	 * 
	 * �����Ըò�Ʒ�Ƽ������в�Ʒ
	 * @param geProductCorrelationList
	 * 
	 */
	public abstract void addAllGeProductCorrelation(
			List<GeProductCorrelation> geProductCorrelationList);

	/***
	 * 
	 * ����eIdɾ����Ը�eId��Ӧ��Ʒ�Ƽ��Ĳ�Ʒ
	 * @param eId
	 * 
	 */
	public abstract void deleteGeProductCorrelationByProductEId(String eId);

	/***
	 * ����eId��ѯ��eId��Ӧ��Ʒ�Ƽ��Ĳ�Ʒ 
	 * @param eId
	 * @return
	 */
	public abstract List<GeProductCorrelation> findGeProductCorrelationByProductEId(
			String eId);
	
	public List<GeDirectory> findTopNCorrelationProductByEid(int topN,String eid);

	public abstract List<GeDirectory> findTopNCorrelationProductByEid(List eid,
			Map paramsMap, int topN);

}
