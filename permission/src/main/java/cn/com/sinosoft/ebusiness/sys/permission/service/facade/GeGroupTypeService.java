/**
 * 
 */
package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGroup;
import cn.com.sinosoft.ebusiness.sys.permission.domain.GeGrouptype;

/**
 * �û������ͷ���ӿ�
 *  
 *
 */
public interface GeGroupTypeService {

	/**
	 * ���������ж϶����Ƿ����
	 * @param pk
	 * @return
	 */
	public boolean existsType(String pk);

	/**
	 * �����û�������
	 * @param geGroup
	 * @return
	 */
	public boolean saveType(GeGrouptype geGrouptype);
	
	/**
	 * ��ҳ��ѯ�û�������
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeGroupType(QueryRule queryRule, int pageNo, int pageSize);
	
	/**
	 * ����������ѯ�����û�������
	 * @param queryRule
	 * @return
	 */
	public GeGrouptype findGeGroupType(QueryRule queryRule);
	
	/**
	 * �޸�������
	 */
	public boolean updateType(GeGrouptype obj);
	/**
	 * ���ݻ�����Ų�ѯ�û�������
	 * @param deptid
	 * @return
	 */
	public List<GeGrouptype> findGeGroupTypes(QueryRule queryRule);
	
	/**
	 * �h��String������key����Ӧ�Ķ���
	 * @param keys
	 * @return
	 */
	public int delete(String keys);
}
