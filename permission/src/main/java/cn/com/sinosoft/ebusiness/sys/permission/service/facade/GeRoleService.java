package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeRole;

/**
 * ��ɫ�������ӿ�
 *  
 *
 */
public interface GeRoleService {

	/**
	 * ���������жϽ�ɫ�Ƿ����
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * �����ɫ��Ϣ
	 * @param obj
	 * @return
	 */
	public boolean save(GeRole geRole);
	
	/**
	 * ��ѯ��ɫ��Ϣ
	 * @param queryRule
	 * @return
	 */
	public GeRole findGeRole(QueryRule queryRule);
	
	/**
	 * �޸Ľ�ɫ
	 * @param obj
	 * @param authoritys
	 * @return
	 */
	public boolean updates(GeRole geRole);
	
	/**
	 * ������ɾ����ɫ��Ϣ
	 * @param pk ����Ϊ������Զ��ŷָ���
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * ��ҳ��ѯ��ɫ��Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page find(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * ����ɫid��ѯ������id
	 * @param roleId
	 * @return
	 */
	public List<GeGroup> findRoleGroupID(String roleId,String authorityid);
	
	public Page findAuthorityRroup(String groupRoleAuthDeptId,String roleId,int pageNo,int pageSize);
	
	public List<GeRole> findGeRoles(QueryRule queryRule);
}
