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
 * �û������ӿ�
 *  
 *
 */
public interface GeGroupService {
	
	/**
	 * ���������ж϶����Ƿ����
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * �����û���
	 * @param geGroup
	 * @return
	 */
	public boolean save(GeGroup geGroup);
	
	/**
	 * ����������ѯ�����û���
	 * @param queryRule
	 * @return
	 */
	public GeGroup findGeGroup(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ�û���
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeGroup(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * ���Ҷ����
	 * @param queryRule
	 * @return
	 */
	public List<GeGroup> findGeGroups(QueryRule queryRule);
	
	/**
	 * �����û���
	 * @param geGroup
	 * @return
	 */
	public boolean updates(GeGroup geGroup);
	
	/**
	 * �h��String������key����Ӧ�Ķ���
	 * @param keys
	 * @return
	 */
	public int delete(String keys);

	/**
	 * ��ѯ���������û���Ϣ
	 * ע��������Ȩ��
	 * @param groupid ��id
	 * @return
	 */
	public List<Map<String,String>> findGeGroupOperator(String groupid,String groupUserAuthDeptId);
	
	/**
	 * ��ѯ����deptid��������groupid���û�
	 * @param groupid ��ID
	 * @param deptid ����ID
	 * @return
	 */
	public List<Map<String,String>> findGeGroupDeptOperator(String groupid, String deptid);

	/**
	 * ����û����û���
	 * @param groupid
	 * @param selectCity
	 * @param updateOperator
	 * @return
	 */
	public boolean updateGeGroupGeOperators(String groupid, String selectCity, String updateOperator)  throws Exception;
	
	/**
	 * �������еĽ�ɫ��Ϣ�Լ������û���Ľ�ɫ
	 * ע����ѯ�����status����Ƿ��Ѿ������û��飨1-�Ѿ����룬0-δ���룩
	 * @param id
	 * @return
	 */
	public List<Map<String,String>> findAllRolesAndChecked(String id,String groupRoleAuthDeptId);
	
	/**
	 * ���ݴ��������������еĽ�ɫ��Ϣ�Լ������û���Ľ�ɫ
	 * ע����ѯ�����status����Ƿ��Ѿ������û��飨1-�Ѿ����룬0-δ���룩
	 * @param id
	 * @return
	 */
	public List<Map<String,String>> findRolesAndChecked(String id,String odeptid);
	
	/**
	 * �ж��Ƿ�Ϊ�û������û�
	 * @param userID
	 * @param groupID
	 * @return
	 */
	public boolean isExistUser(String userID, String groupID);
	/**
	 * �޸���
	 */
	public boolean updatesGroup(GeGroup obj);
	/**
	 * ��ҳ��ѯ
	 */
	public Page findGroupRoles(String id,String groupRoleAuthDeptId,int pageNo,int pageSize);
	
	/**
	 * ��ѯ�û���Ȩ�޷�Χ�ڵ����н�ɫ
	 * @param groupId
	 * @param groupRoleAuthDeptId
	 * @return
	 */
	public List<GeRole> findAllGroupRoles(String groupId,String groupRoleAuthDeptId);
	
	public Page findGeGroupOperator(String groupid,String groupUserAuthDeptId,int pageNo,int pageSize);
	
	/**
	 * ���û���ɾ���û�
	 * @param operatorId
	 * @param groupId
	 * @return
	 */
	public boolean deleteUserFromGroup(String operatorId,String groupId);
	
	
	/**
	 * �Ѹոձ�����û����뵽ѡ�����û�����
	 * @param operatorId
	 * @param groupsId
	 * @return
	 */
	public boolean saveUserToGroups(String createUserId,String groupsId);
	
	/**
	 * �޸��û�ѡ�����û���
	 * @param operatorId
	 * @param groupsId
	 * @return
	 */
	public boolean updateUserToGroups(String createUserId,String groupsId,String allGroupsIds);
	
	
}
