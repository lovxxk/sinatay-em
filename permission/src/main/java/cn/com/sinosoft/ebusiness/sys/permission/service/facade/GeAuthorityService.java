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
 * ��̨����ԱȨ�޷���ӿ�
 *  
 *
 */
public interface GeAuthorityService {
	
	/**
	 * ��ȡ�˵�
	 * @return
	 */
	public Map<String,List<Map<String,String>>> getMenu();

	/**
	 * ����������ѯ����Ȩ��
	 * @param queryRule
	 * @return
	 */
	public List<GeAuthority> findAllAuthoritys(QueryRule queryRule);
	
	/**
	 * ����Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeAuthority geAuthority);
	
	/**
	 * �޸�Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeAuthority geAuthority);
	
	/**
	 * ������ɾ��Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeAuthority geAuthority);
	
	/**
	 * ��������ѯȨ����Ϣ
	 * @param pk
	 * @return
	 */
	public GeAuthority findGeAuthorityByPK(String pk);
	
	/**
	 * ��ѯ��Ȩ�޸���
	 * @param pid
	 * @return
	 */
	public List getChildList(String pid);
	
	/**
	 * �ж��Ƿ�����ӽڵ�
	 * @param pid
	 * @return
	 */
	public boolean isHasChild(String pid);
	
	/**
	 * �жϲ˵��Ƿ�����Ӳ˵�
	 * @param pid
	 * @return
	 */
	public boolean isHasChildMenu(String pid);
	
	/**
	 * ��ѯ��ɫӵ�е�Ȩ��
	 * @param roleID
	 */
	public List findRoleAuthoritys(String roleID);
	
	/**
	 * У��Ȩ���Ƿ񱻽�ɫʹ��
	 * @param pk
	 * @return
	 */
	public boolean existRoleAuthority(String pk);
	/**
	 * �鿴����Ȩ�޶��Ӧ�Ľ�ɫ
	 */
	public List<Map<String,String>> findAuthorityRole(String authoryid,String groupRoleAuthDeptId);
	/**
	 * ͨ���û��Ĺ���Ȩ�޲�������ӵ�е�ϵͳ
	 */
	public List findSystemType(Set<String> strings);
	/**
	 * ��������ӵ�е�ϵͳ
	 */
	public List findByGeCodeType(List<GeAuthority> rolsAuthoritys);
	/**
	 * ��ҳ��ѯ
	 */
	public Page findAuthorityRole(String authoryid,String groupRoleAuthDeptId,int pageNo,int pageSize);
}
