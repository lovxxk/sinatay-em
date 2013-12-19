package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeUserAuthority;

/**
 * ǰ̨�����û�Ȩ�޽ӿ�
 *  
 *
 */
public interface GeUserAuthorityService {
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
	public List<GeUserAuthority> findAllAuthoritys(QueryRule queryRule);
	
	/**
	 * ����Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeUserAuthority geUserAuthority);
	
	/**
	 * �޸�Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeUserAuthority geUserAuthority);
	
	/**
	 * ������ɾ��Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeUserAuthority geUserAuthority);
	
	/**
	 * ��������ѯȨ����Ϣ
	 * @param pk
	 * @return
	 */
	public GeUserAuthority findGeUserAuthorityByPK(String pk);
	

	/**
	 * �ж��Ƿ�����ӽڵ�
	 * @param pid
	 * @return
	 */
	public boolean isHasChild(String pid);
	
	/**
	 * ��ѯ��Ȩ�޸���
	 * @param pid
	 * @return
	 */
	public List getChildList(String pid);
	
	/**
	 * ��ѯ�û��ȼ�ӵ�е�Ȩ��
	 * @param roleID
	 */
	public List findLevelAuthoritys(String userLevel);
	
	/**
	 * У��Ȩ���Ƿ񱻽�ɫʹ��
	 * @param pk
	 * @return
	 */
	public boolean existRoleAuthority(String pk);
	
	/**
	 * ���������û��ȼ�Ȩ��
	 * @param userLevel
	 * @param authoritys
	 * @return
	 */
	public boolean saveLevelAuthoritys(String userLevel,String authoritys);
	
	/**
	 * �����û��ȼ���ѯȨ����Ϣ
	 * @param level
	 * @return
	 */
	public List getUserAuthoritysByLevel(String level);
	
}
