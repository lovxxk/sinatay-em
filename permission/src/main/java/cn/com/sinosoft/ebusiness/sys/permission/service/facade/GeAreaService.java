package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeAreaAuthority;


public interface GeAreaService {
	
	/**
	 * ��ȡ�����˵�
	 */
	public List<GeAreaService> getGeAeraByGlevel();
	
	/**
	 * ��ѯ����������Ϣ
	 * @param pgid
	 * @param glevel
	 * @return
	 */
	public Object[] findGeAreas(String pgid,String glevel);
	
	/**
	 * ����������ѯ����������Ϣ
	 */
	/**
	 * ��ѯ���л���
	 * @param queryRule
	 * @return
	 */
	public List findGeAreas(QueryRule queryRule);
	
	/**
	 * ��ѯ������
	 */
	public List getChildList(String pgid);
	/**
	 * ��ѯ��������µ�����
	 */
	public List getBussAreas(String buss);
	/**
	 * ����������Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean save(GeAreaAuthority geAreaAuthority);
	
	/**
	 * �޸�������Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean updates(GeAreaAuthority geAreaAuthority);
	
	/**
	 * ������ɾ��������Ϣ
	 * @param geAuthority
	 * @return
	 */
	public boolean delete(GeAreaAuthority geAreaAuthority);
	
	/**
	 * ��������ѯ������Ϣ
	 * @param pk
	 * @return
	 */
	public GeAreaAuthority findGeAuthorityByPK(String pk);
	/**
	 * ���������ж϶����Ƿ����
	 * 
	 * @param pk
	 * @return
	 */
	public boolean exists(String pk);
	/**
	 * �鿴���������Ƿ񱻻�������
	 */
	public boolean isUsedDep(String gid);
	
	/**
	 * �����¼����������ѯ�ϼ�����
	 * @param childId
	 * @return
	 */
	public GeAreaAuthority findGeAuthorityByChildId(String childId);
}
