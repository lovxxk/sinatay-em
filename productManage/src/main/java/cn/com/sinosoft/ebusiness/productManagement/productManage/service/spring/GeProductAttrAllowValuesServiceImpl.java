package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductAttrAllowValues;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeProductAttrAllowValuesService;

public class GeProductAttrAllowValuesServiceImpl  extends
GenericDaoHibernate<GeProductAttrAllowValues, String> implements GeProductAttrAllowValuesService {
	
	/**
	 * �½������ڼ�������Ϣ
	 * @param geProductAttrAllowValues
	 */
	public void createPAAValues(GeProductAttrAllowValues geProductAttrAllowValues){
		try {
			super.save(geProductAttrAllowValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������ѯ�����ڼ�������Ϣ
	 * @param serialNo
	 * @return
	 */
	public GeProductAttrAllowValues findPAAValuesBySerialNo(String serialNo){
		return super.get(serialNo);
	}
	
	/**
	 * ��ҳ��ѯ�����ڼ�������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPAAValues(QueryRule queryRule, int pageNo,
			int pageSize){
		try {
			return super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ѯ�����ڼ�������Ϣ����
	 * @param queryRule
	 * @return
	 */
	public List<GeProductAttrAllowValues> findPAAValuesList(QueryRule queryRule){
		try {
			return super.find(queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���汣���ڼ�������Ϣ
	 */
	public void savePAAValues(GeProductAttrAllowValues pAAValues){
		try {
			super.save(pAAValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���汣���ڼ�������Ϣ�б�
	 */
	public void savePAAValuesList(List<GeProductAttrAllowValues> pAAValuesList){
		super.saveAll(pAAValuesList);
	}

	
	/**
	 * ɾ�������ڼ�������Ϣ
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValues(GeProductAttrAllowValues PAAValues) throws Exception {
		super.delete(PAAValues);
	}
	
	/**
	 * ɾ�������ڼ�������Ϣ�б�
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValuesList(List<GeProductAttrAllowValues> PAAValuesList) throws Exception {
		super.deleteAll(PAAValuesList);
	}

	/**
	 * ���汣���ڼ�������Ϣ�б��Զ��壩
	 * @param PAAValuesList
	 */
	public void savePAAValuesListAll(List<GeProductAttrAllowValues> PAAValuesList) {
		try{
			for(GeProductAttrAllowValues PAAValues : PAAValuesList) {
				super.save(PAAValues);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
