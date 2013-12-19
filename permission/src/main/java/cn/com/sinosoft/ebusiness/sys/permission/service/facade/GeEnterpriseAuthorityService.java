package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeEnterpriseAuthority;

/**
 * ǰ̨��ҵ�û�Ȩ�޽ӿ�
 *  
 *
 */
public interface GeEnterpriseAuthorityService {
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
	public List<GeEnterpriseAuthority> findAllAuthoritys(QueryRule queryRule);
	
	/**
	 * ����Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeEnterpriseAuthority geEnterpriseAuthority);
	
	/**
	 * �޸�Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeEnterpriseAuthority geEnterpriseAuthority);
	
	/**
	 * ������ɾ��Ȩ����Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeEnterpriseAuthority geEnterpriseAuthority);
	
	/**
	 * ��������ѯȨ����Ϣ
	 * @param pk
	 * @return
	 */
	public GeEnterpriseAuthority findGeEnterpriseAuthorityByPK(String pk);
	

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
	public List findLevelAuthoritys(String enperpriseLevel);
	
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
	public boolean saveLevelAuthoritys(String enterpriseLevel,String authoritys);
	
	/**
	 * �����û��ȼ���ѯȨ����Ϣ
	 * @param level
	 * @return
	 */
	public List getEnterpriseAuthoritysByLevel(String level);
	/**
	 * ��ѯȨ���Ƿ�ʹ��
	 * @param authorityid
	 * @return
	 */
	public boolean findLevelByAuthorityid(String authorityid);
	
}
