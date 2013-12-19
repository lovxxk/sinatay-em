package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptInfo;

/**
 * 机构属性信息
 */
public interface GeDeptInfoService {
	/**
	 * 保存机构属性信息
	 * @param geDeptInfo
	 * @return
	 */
	public boolean save(GeDeptInfo geDeptInfo);
	
	/**
	 * 根据主键查询机构属性信息
	 * @param pk
	 * @return
	 */
	public GeDeptInfo findGeDeptInfoByPk(String pk);
	
	/**
	 * 查询所有的机构属性信息
	 * @param queryRule
	 * @return
	 */
	public List<GeDeptInfo> findAllGeDeptInfo(QueryRule queryRule);
	
	/**
	 * 分页查询机构属性信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllGeDeptInfoByDisPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除机构属性信息
	 * @param pk
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * 修改机构属性信息
	 * @param geDeptInfo
	 * @return
	 */
	public boolean updateGeDeptInfo(GeDeptInfo geDeptInfo);
	
	/**
	 * 判断主键是否存在
	 */
	public boolean isExitId(String id);
}
