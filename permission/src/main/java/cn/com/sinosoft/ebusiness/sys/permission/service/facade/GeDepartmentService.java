package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;

public interface GeDepartmentService {

	/**
	 * 保存机构信息
	 * @param obj
	 * @return
	 */
	public boolean save(GeDepartment geDepartment);
	
	/**
	 * 修改机构信息
	 * @param obj
	 * @return
	 */
	public boolean updates(GeDepartment geDepartment);
	
	/**
	 * 按主键删除机构信息
	 * @param pk
	 * @return
	 */
	public boolean delete(GeDepartment geDepartment);
	
	/**
	 * 按主键查询机构信息
	 * @param pk
	 * @return
	 */
	public GeDepartment findGeDepartmentByPK(String pk);
	
	/**
	 * 分页查询机构信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page find(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * 根据主键判断对象是否存在
	 * 
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * 查询所有机构
	 * @param queryRule
	 * @return
	 */
	public List findAllDepartments(QueryRule queryRule);
	
	/***
	 * 判断节点是否有子节点
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id);
	
	/**
	 * 判断机构deptId是否存在用户
	 * @param deptId
	 * @return
	 */
	public boolean hasOperator(String deptId);
	
	/**
	 * 判断机构deptId是否被机构权限使用
	 * @param deptId
	 * @return
	 */
	public boolean hasDeptGroupRole(String deptId);
	
	/**
	 * 获取子机构的id集合
	 * @param pid
	 * @return
	 */
	public List getChildList(String pid);
	
	/**
	 * 根据机构编码查询出本机构及所有父机构
	 * @param deptid
	 * @return
	 */
	public List<GeDepartment> findDepartmentByDepId(List list,String deptid);
	
	/**
	 * 查询业务领域下机构信息仅供选择机构使用
	 * @Title: findDepartment
	 * @Description: TODO
	 * @param @param businessArea	业务领域
	 * @param @param deptLevel		等级
	 * @param @param parentId	父ID
	 * @param @return
	 * @return List<GeDepartment>
	 * @throws
	 */
	public List<GeDepartment> findDepartment(String businessArea, String deptLevel, String parentId);
	
	/**
	 * 根据机构编码查询出同级的所有机构(3级以下机构（包含）)
	 * @param deptId 3级以下（包含）机构的编码
	 * @return
	 */
	public List<GeDepartment> findSiblingDepartments(String deptId);
	
	/**
	 * 根据机构编码查询出上级机构
	 * @param deptId 机构编码
	 * @return
	 */
	public GeDepartment findGeDepartmentByChildId(String deptId);
	
	/**
	 * 查询机构信息
	 * @return <机构编码，机构名称>
	 */
	public Map<String, String> findAllDepartMent();
	
	public void updateDeptName();
	public List<String[]> getAllChildAuthDept(String parentId,String auth,boolean isReturnAll);
	
	/**
	 * 获取所有父节点
	 * @param auth
	 * @return
	 */
	public String findParentIds(String auth);
	
	/**
	 * 获取本级名称
	 * @param geDepartment
	 * @return
	 */
	public String getSelfName(GeDepartment geDepartment);
}
