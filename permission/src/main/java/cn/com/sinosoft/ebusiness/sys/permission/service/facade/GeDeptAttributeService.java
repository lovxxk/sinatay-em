package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptAttribute;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDeptAttribute;

/**
 * ��������
 *  
 *
 */
public interface GeDeptAttributeService {
	/**
	 * �������������Ϣ
	 * @param geDeptAttribute
	 * @return
	 */
	public boolean save(GeDeptAttribute geDeptAttribute);
	
	
	
	/**
	 * ����������ѯ����������Ϣ
	 * @param pk
	 * @return
	 */
	public GeDeptAttribute findGeDeptAttributeByPk(String pk);
	
	/**
	 * ��ѯ���еĻ���������Ϣ
	 * @param queryRule
	 * @return
	 */
	public List<GeDeptAttribute> findAllGeDeptAttribute(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ����������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllDeptAttributeByDisPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ������������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * �޸Ļ���������Ϣ
	 * @param geDeptAttribute
	 * @return
	 */
	public boolean updateGeDeptAttribute(GeDeptAttribute geDeptAttribute);

	/**
	 * ����deptId ��ѯ����������Ϣ
	 * @param deptId
	 * @return
	 */
	public Map<String, String> findAllDeptAttribute(String deptId);
	/**
	 * ����deptId ��ѯ����������Ϣ
	 * @param deptId
	 * @return
	 */
	public List<GeDeptAttribute> findGeDeptAttributeById(String deptId);
	/**
	 * ����deptId,attrId ��ѯ����������Ϣ
	 * @param deptId,attrId
	 * @return
	 */
	public Object findGeDeptAttributeByDeAtId(String deptId,String attrId,Object obj);

}
