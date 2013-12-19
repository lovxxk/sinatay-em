package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;

public interface GeDepartmentService {

	/**
	 * ���������Ϣ
	 * @param obj
	 * @return
	 */
	public boolean save(GeDepartment geDepartment);
	
	/**
	 * �޸Ļ�����Ϣ
	 * @param obj
	 * @return
	 */
	public boolean updates(GeDepartment geDepartment);
	
	/**
	 * ������ɾ��������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delete(GeDepartment geDepartment);
	
	/**
	 * ��������ѯ������Ϣ
	 * @param pk
	 * @return
	 */
	public GeDepartment findGeDepartmentByPK(String pk);
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page find(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * ���������ж϶����Ƿ����
	 * 
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	
	/**
	 * ��ѯ���л���
	 * @param queryRule
	 * @return
	 */
	public List findAllDepartments(QueryRule queryRule);
	
	/***
	 * �жϽڵ��Ƿ����ӽڵ�
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id);
	
	/**
	 * �жϻ���deptId�Ƿ�����û�
	 * @param deptId
	 * @return
	 */
	public boolean hasOperator(String deptId);
	
	/**
	 * �жϻ���deptId�Ƿ񱻻���Ȩ��ʹ��
	 * @param deptId
	 * @return
	 */
	public boolean hasDeptGroupRole(String deptId);
	
	/**
	 * ��ȡ�ӻ�����id����
	 * @param pid
	 * @return
	 */
	public List getChildList(String pid);
	
	/**
	 * ���ݻ��������ѯ�������������и�����
	 * @param deptid
	 * @return
	 */
	public List<GeDepartment> findDepartmentByDepId(List list,String deptid);
	
	/**
	 * ��ѯҵ�������»�����Ϣ����ѡ�����ʹ��
	 * @Title: findDepartment
	 * @Description: TODO
	 * @param @param businessArea	ҵ������
	 * @param @param deptLevel		�ȼ�
	 * @param @param parentId	��ID
	 * @param @return
	 * @return List<GeDepartment>
	 * @throws
	 */
	public List<GeDepartment> findDepartment(String businessArea, String deptLevel, String parentId);
	
	/**
	 * ���ݻ��������ѯ��ͬ�������л���(3�����»�����������)
	 * @param deptId 3�����£������������ı���
	 * @return
	 */
	public List<GeDepartment> findSiblingDepartments(String deptId);
	
	/**
	 * ���ݻ��������ѯ���ϼ�����
	 * @param deptId ��������
	 * @return
	 */
	public GeDepartment findGeDepartmentByChildId(String deptId);
	
	/**
	 * ��ѯ������Ϣ
	 * @return <�������룬��������>
	 */
	public Map<String, String> findAllDepartMent();
	
	public void updateDeptName();
	public List<String[]> getAllChildAuthDept(String parentId,String auth,boolean isReturnAll);
	
	/**
	 * ��ȡ���и��ڵ�
	 * @param auth
	 * @return
	 */
	public String findParentIds(String auth);
	
	/**
	 * ��ȡ��������
	 * @param geDepartment
	 * @return
	 */
	public String getSelfName(GeDepartment geDepartment);
}
