package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptGroupRole;

/**
 * �û����ɫȨ�޽ӿ�
 * 
 *  
 * 
 */
public interface GeDeptGroupRoleService {
	/**
	 * ��ѯ�û����ɫ������Ϣ
	 * 
	 * @param queryRule
	 * @return
	 */
	public List<GeDeptGroupRole> findGeDeptGroupRoles(QueryRule queryRule);

	/**
	 * ������ID�ͽ�ɫID��ѯ����ID ע�����ؽ���Զ��ŷָ�
	 * 
	 * @param groupid
	 * @param roleId
	 * @return
	 */
	public String findGeDeptGroupRoleStr(String groupid, String roleId);

	/**
	 * �����û���-��ɫ-������Ϣ
	 * 
	 * @param groupId
	 *            ��ID
	 * @param roleId
	 *            ��ɫID
	 * @param delDepts
	 *            Ҫɾ���Ļ���ID
	 * @param depts
	 *            �������ID
	 * @return
	 */
	boolean insertGeGroupRoleDept(String groupId, String roleId, String depts);

	/**
	 * �����û���-��ɫ-������Ϣ
	 * 
	 * @param groupId
	 *            ��ID
	 * @param roleIds
	 *            �����ɫID
	 * @param delDepts
	 *            Ҫɾ���Ļ���ID
	 * @param depts
	 *            �������ID
	 * @return
	 */
	boolean insertGeGroupRoleDepts(String groupId, String roleIds, String depts);
	/**
	 * ɾ���û���-��ɫ���еĻ�����Ϣ
	 * 
	 * @param groupId
	 *            ��ID
	 * @param roleId
	 *            ��ɫID
	 * @return
	 */
	boolean deleteGeGroupGeRoleDept(String groupId, String roleId);

	/**
	 * ɾ���û���-��ɫ��depts��Ӧ�Ļ�����Ϣ
	 * 
	 * @param groupId
	 * @param roleId
	 * @param depts
	 * @return
	 */
	public boolean deleteGeGroupGeRoleDept(String groupId, String roleId,
			String depts);

	/**
	 * ɾ���û���-��ɫ��depts��Ӧ�Ļ�����Ϣ
	 * 
	 * @param groupId
	 * @param roleIds
	 * @param depts
	 * @return
	 */
	public boolean deleteGeGroupGeRoleDepts(String groupId, String roleIds,
			String depts);
	
	/**
	 * ��ѯ ��ɫ---Ȩ�ޣ���ɫ---����������PermissionFactoryȨ�޹����ࣩ
	 * 
	 * @param operatorId
	 * @return
	 */
	public Map<String, Map<String, Set<String>>> findRoleAuthorityDept(
			String operatorId);

	/**
	 * ��ѯ ��ɫ---Ȩ�ޣ���ɫ---����������PermissionFactoryȨ�޹����ࣩ
	 * 
	 * @param groupid
	 * @param roleid
	 * @return
	 */
	List<String> findGeGroupRoleDept(String groupid, String roleid);
	
	/**
	 * ��������Ȩ�ޱ���
	 * @return
	 */
	public List findAllAuthorityIds();

	/**
	 * �����û�ӵ�еĹ���Ȩ��
	 * 
	 * @param operatorId
	 * @return
	 */
	public List findAuthoritysByOId(String operatorId);

	/**
	 * ������ID��ѯ����ID ע�����ؽ���Զ��ŷָ�
	 * @param groupid
	 * @return
	 */
	String findGeDeptGroupRoleStr(String groupid);
	/**
	 * ������ID��ѯ����ID ע�����ؽ���Զ��ŷָ� ���¡�
	 * @param groupid
	 * @return
	 */
	String findDeptIds(String groupid);
	
	/**
	 * ��ѯ�û���ӵ�е����л���Ȩ��
	 * ע������ֵ���ŷָ�
	 * @param groupid
	 * @return
	 */
	String findGroupDeptAuth(String groupid);
	
	/**
	 * �����û���Ȩ��������Ϣ����ɫ�ͻ�����
	 * @param groupId
	 * @param roleId
	 * @param depts
	 * @param operatorDeptAuths
	 * @return
	 */
	boolean  saveGeGroupRoleDept(String groupId, String roleId,String depts,String operatorDeptAuths);
	/**
	 * ��ѯ�û������Ȩ�ޱ�־
	 * @param groupId
	 * @return
	 */
	HashMap<String,String> findGroupDeptAuthFlag(String groupId);
	
	public List getAllChildDept(String parentId,List allChildDepts);
	
	/**
	 * ��ȡ���⸸�ڵ�
	 * @param groupDeptAuths
	 * @return
	 */
	public String getAllVirtualParent(String groupDeptAuths);
}
