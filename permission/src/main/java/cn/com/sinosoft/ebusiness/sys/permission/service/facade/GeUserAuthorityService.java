package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeUserAuthority;

/**
 * 前台个人用户权限接口
 *  
 *
 */
public interface GeUserAuthorityService {
	/**
	 * 获取菜单
	 * @return
	 */
	public Map<String,List<Map<String,String>>> getMenu();

	/**
	 * 根据条件查询所有权限
	 * @param queryRule
	 * @return
	 */
	public List<GeUserAuthority> findAllAuthoritys(QueryRule queryRule);
	
	/**
	 * 保存权限信息
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeUserAuthority geUserAuthority);
	
	/**
	 * 修改权限信息
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeUserAuthority geUserAuthority);
	
	/**
	 * 按主键删除权限信息
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeUserAuthority geUserAuthority);
	
	/**
	 * 按主键查询权限信息
	 * @param pk
	 * @return
	 */
	public GeUserAuthority findGeUserAuthorityByPK(String pk);
	

	/**
	 * 判断是否存在子节点
	 * @param pid
	 * @return
	 */
	public boolean isHasChild(String pid);
	
	/**
	 * 查询子权限个数
	 * @param pid
	 * @return
	 */
	public List getChildList(String pid);
	
	/**
	 * 查询用户等级拥有的权限
	 * @param roleID
	 */
	public List findLevelAuthoritys(String userLevel);
	
	/**
	 * 校验权限是否被角色使用
	 * @param pk
	 * @return
	 */
	public boolean existRoleAuthority(String pk);
	
	/**
	 * 保存设置用户等级权限
	 * @param userLevel
	 * @param authoritys
	 * @return
	 */
	public boolean saveLevelAuthoritys(String userLevel,String authoritys);
	
	/**
	 * 根据用户等级查询权限信息
	 * @param level
	 * @return
	 */
	public List getUserAuthoritysByLevel(String level);
	
}
