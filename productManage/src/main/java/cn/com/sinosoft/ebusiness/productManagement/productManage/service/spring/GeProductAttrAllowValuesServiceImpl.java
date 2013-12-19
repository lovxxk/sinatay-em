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
	 * 新建保险期间配置信息
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
	 * 按主键查询保险期间配置信息
	 * @param serialNo
	 * @return
	 */
	public GeProductAttrAllowValues findPAAValuesBySerialNo(String serialNo){
		return super.get(serialNo);
	}
	
	/**
	 * 分页查询保险期间配置信息
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
	 * 查询保险期间配置信息集合
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
	 * 保存保险期间配置信息
	 */
	public void savePAAValues(GeProductAttrAllowValues pAAValues){
		try {
			super.save(pAAValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存保险期间配置信息列表
	 */
	public void savePAAValuesList(List<GeProductAttrAllowValues> pAAValuesList){
		super.saveAll(pAAValuesList);
	}

	
	/**
	 * 删除保险期间配置信息
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValues(GeProductAttrAllowValues PAAValues) throws Exception {
		super.delete(PAAValues);
	}
	
	/**
	 * 删除保险期间配置信息列表
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValuesList(List<GeProductAttrAllowValues> PAAValuesList) throws Exception {
		super.deleteAll(PAAValuesList);
	}

	/**
	 * 保存保险期间配置信息列表（自定义）
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
