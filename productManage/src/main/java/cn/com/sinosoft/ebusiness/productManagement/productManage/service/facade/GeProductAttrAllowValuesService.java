package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductAttrAllowValues;

public interface GeProductAttrAllowValuesService {
	
	/**
	 * 新建保险期间配置信息
	 * @param geProductAttrAllowValues
	 */
	public void createPAAValues(GeProductAttrAllowValues geProductAttrAllowValues);
	
	/**
	 * 分页查询保险期间配置信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPAAValues(QueryRule queryRule, int pageNo,
			int pageSize);

	/**
	 * 按主键查询保险期间配置信息
	 * @param serialNo
	 * @return
	 */
	public GeProductAttrAllowValues findPAAValuesBySerialNo(String serialNo);
	
	/**
	 * 查询保险期间配置信息集合
	 * @param queryRule
	 * @return
	 */
	public List<GeProductAttrAllowValues> findPAAValuesList(QueryRule queryRule);
	
	/**
	 * 保存保险期间配置信息
	 */
	public void savePAAValues(GeProductAttrAllowValues pAAValues);
	
	/**
	 * 保存保险期间配置信息列表
	 */
	public void savePAAValuesList(List<GeProductAttrAllowValues> pAAValuesList);
	
	/**
	 * 删除保险期间配置信息
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValues(GeProductAttrAllowValues PAAValues) throws Exception;

	/**
	 * 删除保险期间配置信息列表
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValuesList(List<GeProductAttrAllowValues> PAAValuesList) throws Exception;
	
	/**
	 * 保存保险期间配置信息列表（自定义）
	 * @param PAAValuesList
	 */
	public void savePAAValuesListAll(List<GeProductAttrAllowValues> PAAValuesList);

}
