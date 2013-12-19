package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptAttribute;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptAttribute;

/**
 * 机构关联
 *  
 *
 */
public interface GeDeptAttributeService {
	/**
	 * 保存机构关联信息
	 * @param geDeptAttribute
	 * @return
	 */
	public boolean save(GeDeptAttribute geDeptAttribute);
	
	
	
	/**
	 * 根据主键查询机构关联信息
	 * @param pk
	 * @return
	 */
	public GeDeptAttribute findGeDeptAttributeByPk(String pk);
	
	/**
	 * 查询所有的机构关联信息
	 * @param queryRule
	 * @return
	 */
	public List<GeDeptAttribute> findAllGeDeptAttribute(QueryRule queryRule);
	
	/**
	 * 分页查询机构关联信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllDeptAttributeByDisPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除机构关联信息
	 * @param pk
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * 修改机构关联信息
	 * @param geDeptAttribute
	 * @return
	 */
	public boolean updateGeDeptAttribute(GeDeptAttribute geDeptAttribute);

	/**
	 * 根据deptId 查询车险配置信息
	 * @param deptId
	 * @return
	 */
	public Map<String, String> findAllDeptAttribute(String deptId);
	/**
	 * 根据deptId 查询车险配置信息
	 * @param deptId
	 * @return
	 */
	public List<GeDeptAttribute> findGeDeptAttributeById(String deptId);
	/**
	 * 根据deptId,attrId 查询车险配置信息
	 * @param deptId,attrId
	 * @return
	 */
	public Object findGeDeptAttributeByDeAtId(String deptId,String attrId,Object obj);

}
