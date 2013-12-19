/**
 * 
 */
package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGrouptype;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;

/**
 * 用户组服务接口
 *  
 *
 */
public interface GeGroupService {
	
	/**
	 * 根据主键判断对象是否存在
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * 保存用户组
	 * @param geGroup
	 * @return
	 */
	public boolean save(GeGroup geGroup);
	
	/**
	 * 根据条件查询单个用户组
	 * @param queryRule
	 * @return
	 */
	public GeGroup findGeGroup(QueryRule queryRule);
	
	/**
	 * 分页查询用户组
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeGroup(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * 查找多个组
	 * @param queryRule
	 * @return
	 */
	public List<GeGroup> findGeGroups(QueryRule queryRule);
	
	/**
	 * 更新用户组
	 * @param geGroup
	 * @return
	 */
	public boolean updates(GeGroup geGroup);
	
	/**
	 * 刪除String对象中key所对应的对象
	 * @param keys
	 * @return
	 */
	public int delete(String keys);

	/**
	 * 查询组内所有用户信息
	 * 注：带机构权限
	 * @param groupid 组id
	 * @return
	 */
	public List<Map<String,String>> findGeGroupOperator(String groupid,String groupUserAuthDeptId);
	
	/**
	 * 查询机构deptid并加入组groupid的用户
	 * @param groupid 组ID
	 * @param deptid 机构ID
	 * @return
	 */
	public List<Map<String,String>> findGeGroupDeptOperator(String groupid, String deptid);

	/**
	 * 添加用户到用户组
	 * @param groupid
	 * @param selectCity
	 * @param updateOperator
	 * @return
	 */
	public boolean updateGeGroupGeOperators(String groupid, String selectCity, String updateOperator)  throws Exception;
	
	/**
	 * 查找所有的角色信息以及加入用户组的角色
	 * 注：查询结果中status标记是否已经加入用户组（1-已经加入，0-未加入）
	 * @param id
	 * @return
	 */
	public List<Map<String,String>> findAllRolesAndChecked(String id,String groupRoleAuthDeptId);
	
	/**
	 * 根据创建机构查找所有的角色信息以及加入用户组的角色
	 * 注：查询结果中status标记是否已经加入用户组（1-已经加入，0-未加入）
	 * @param id
	 * @return
	 */
	public List<Map<String,String>> findRolesAndChecked(String id,String odeptid);
	
	/**
	 * 判断是否为用户组中用户
	 * @param userID
	 * @param groupID
	 * @return
	 */
	public boolean isExistUser(String userID, String groupID);
	/**
	 * 修改组
	 */
	public boolean updatesGroup(GeGroup obj);
	/**
	 * 分页查询
	 */
	public Page findGroupRoles(String id,String groupRoleAuthDeptId,int pageNo,int pageSize);
	
	/**
	 * 查询用户在权限范围内的所有角色
	 * @param groupId
	 * @param groupRoleAuthDeptId
	 * @return
	 */
	public List<GeRole> findAllGroupRoles(String groupId,String groupRoleAuthDeptId);
	
	public Page findGeGroupOperator(String groupid,String groupUserAuthDeptId,int pageNo,int pageSize);
	
	/**
	 * 从用户组删除用户
	 * @param operatorId
	 * @param groupId
	 * @return
	 */
	public boolean deleteUserFromGroup(String operatorId,String groupId);
	
	
	/**
	 * 把刚刚保存的用户加入到选定的用户组中
	 * @param operatorId
	 * @param groupsId
	 * @return
	 */
	public boolean saveUserToGroups(String createUserId,String groupsId);
	
	/**
	 * 修改用户选定的用户组
	 * @param operatorId
	 * @param groupsId
	 * @return
	 */
	public boolean updateUserToGroups(String createUserId,String groupsId,String allGroupsIds);
	
	
}
