package cn.com.sinosoft.ebusiness.sys.permission.service.facade;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAuthority;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;

/**
 * 后台管理员权限服务接口
 *  
 *
 */
public interface GeAuthorityService {
	
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
	public List<GeAuthority> findAllAuthoritys(QueryRule queryRule);
	
	/**
	 * 保存权限信息
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeAuthority geAuthority);
	
	/**
	 * 修改权限信息
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeAuthority geAuthority);
	
	/**
	 * 按主键删除权限信息
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeAuthority geAuthority);
	
	/**
	 * 按主键查询权限信息
	 * @param pk
	 * @return
	 */
	public GeAuthority findGeAuthorityByPK(String pk);
	
	/**
	 * 查询子权限个数
	 * @param pid
	 * @return
	 */
	public List getChildList(String pid);
	
	/**
	 * 判断是否存在子节点
	 * @param pid
	 * @return
	 */
	public boolean isHasChild(String pid);
	
	/**
	 * 判断菜单是否存在子菜单
	 * @param pid
	 * @return
	 */
	public boolean isHasChildMenu(String pid);
	
	/**
	 * 查询角色拥有的权限
	 * @param roleID
	 */
	public List findRoleAuthoritys(String roleID);
	
	/**
	 * 校验权限是否被角色使用
	 * @param pk
	 * @return
	 */
	public boolean existRoleAuthority(String pk);
	/**
	 * 查看功能权限多对应的角色
	 */
	public List<Map<String,String>> findAuthorityRole(String authoryid,String groupRoleAuthDeptId);
	/**
	 * 通过用户的功能权限查找其所拥有的系统
	 */
	public List findSystemType(Set<String> strings);
	/**
	 * 查找其所拥有的系统
	 */
	public List findByGeCodeType(List<GeAuthority> rolsAuthoritys);
	/**
	 * 分页查询
	 */
	public Page findAuthorityRole(String authoryid,String groupRoleAuthDeptId,int pageNo,int pageSize);
}
