package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;

/**
 * 角色管理服务接口
 *  
 *
 */
public interface GeRoleService {

	/**
	 * 根据主键判断角色是否存在
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * 保存角色信息
	 * @param obj
	 * @return
	 */
	public boolean save(GeRole geRole);
	
	/**
	 * 查询角色信息
	 * @param queryRule
	 * @return
	 */
	public GeRole findGeRole(QueryRule queryRule);
	
	/**
	 * 修改角色
	 * @param obj
	 * @param authoritys
	 * @return
	 */
	public boolean updates(GeRole geRole);
	
	/**
	 * 按主键删除角色信息
	 * @param pk 可以为多个（以逗号分隔）
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * 分页查询角色信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page find(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * 按角色id查询关联组id
	 * @param roleId
	 * @return
	 */
	public List<GeGroup> findRoleGroupID(String roleId,String authorityid);
	
	public Page findAuthorityRroup(String groupRoleAuthDeptId,String roleId,int pageNo,int pageSize);
	
	public List<GeRole> findGeRoles(QueryRule queryRule);
}
