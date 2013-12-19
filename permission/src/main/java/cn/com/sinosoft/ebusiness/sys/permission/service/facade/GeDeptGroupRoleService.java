package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptGroupRole;

/**
 * 用户组角色权限接口
 * 
 *  
 * 
 */
public interface GeDeptGroupRoleService {
	/**
	 * 查询用户组角色机构信息
	 * 
	 * @param queryRule
	 * @return
	 */
	public List<GeDeptGroupRole> findGeDeptGroupRoles(QueryRule queryRule);

	/**
	 * 根据组ID和角色ID查询机构ID 注：返回结果以逗号分隔
	 * 
	 * @param groupid
	 * @param roleId
	 * @return
	 */
	public String findGeDeptGroupRoleStr(String groupid, String roleId);

	/**
	 * 新增用户组-角色-机构信息
	 * 
	 * @param groupId
	 *            组ID
	 * @param roleId
	 *            角色ID
	 * @param delDepts
	 *            要删除的机构ID
	 * @param depts
	 *            多个机构ID
	 * @return
	 */
	boolean insertGeGroupRoleDept(String groupId, String roleId, String depts);

	/**
	 * 新增用户组-角色-机构信息
	 * 
	 * @param groupId
	 *            组ID
	 * @param roleIds
	 *            多个角色ID
	 * @param delDepts
	 *            要删除的机构ID
	 * @param depts
	 *            多个机构ID
	 * @return
	 */
	boolean insertGeGroupRoleDepts(String groupId, String roleIds, String depts);
	/**
	 * 删除用户组-角色所有的机构信息
	 * 
	 * @param groupId
	 *            组ID
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	boolean deleteGeGroupGeRoleDept(String groupId, String roleId);

	/**
	 * 删除用户组-角色与depts对应的机构信息
	 * 
	 * @param groupId
	 * @param roleId
	 * @param depts
	 * @return
	 */
	public boolean deleteGeGroupGeRoleDept(String groupId, String roleId,
			String depts);

	/**
	 * 删除用户组-角色与depts对应的机构信息
	 * 
	 * @param groupId
	 * @param roleIds
	 * @param depts
	 * @return
	 */
	public boolean deleteGeGroupGeRoleDepts(String groupId, String roleIds,
			String depts);
	
	/**
	 * 查询 角色---权限；角色---机构（用于PermissionFactory权限工厂类）
	 * 
	 * @param operatorId
	 * @return
	 */
	public Map<String, Map<String, Set<String>>> findRoleAuthorityDept(
			String operatorId);

	/**
	 * 查询 角色---权限；角色---机构（用于PermissionFactory权限工厂类）
	 * 
	 * @param groupid
	 * @param roleid
	 * @return
	 */
	List<String> findGeGroupRoleDept(String groupid, String roleid);
	
	/**
	 * 查找所有权限编码
	 * @return
	 */
	public List findAllAuthorityIds();

	/**
	 * 查找用户拥有的功能权限
	 * 
	 * @param operatorId
	 * @return
	 */
	public List findAuthoritysByOId(String operatorId);

	/**
	 * 根据组ID查询机构ID 注：返回结果以逗号分隔
	 * @param groupid
	 * @return
	 */
	String findGeDeptGroupRoleStr(String groupid);
	/**
	 * 根据组ID查询机构ID 注：返回结果以逗号分隔 “新”
	 * @param groupid
	 * @return
	 */
	String findDeptIds(String groupid);
	
	/**
	 * 查询用户组拥有的所有机构权限
	 * 注：返回值逗号分隔
	 * @param groupid
	 * @return
	 */
	String findGroupDeptAuth(String groupid);
	
	/**
	 * 保存用户组权限设置信息（角色和机构）
	 * @param groupId
	 * @param roleId
	 * @param depts
	 * @param operatorDeptAuths
	 * @return
	 */
	boolean  saveGeGroupRoleDept(String groupId, String roleId,String depts,String operatorDeptAuths);
	/**
	 * 查询用户组机构权限标志
	 * @param groupId
	 * @return
	 */
	HashMap<String,String> findGroupDeptAuthFlag(String groupId);
	
	public List getAllChildDept(String parentId,List allChildDepts);
	
	/**
	 * 获取虚拟父节点
	 * @param groupDeptAuths
	 * @return
	 */
	public String getAllVirtualParent(String groupDeptAuths);
}
