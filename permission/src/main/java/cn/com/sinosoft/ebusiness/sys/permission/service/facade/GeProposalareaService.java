package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;

public interface GeProposalareaService {

	
	
	/**
	 * ��ȡ���⸸�ڵ�
	 * @param groupDeptAuths
	 * @return
	 */
	public String getAllVirtualProParent(String groupDeptAuths);
	
	/**
	 * ��ȡ��������
	 * @param geDepartment
	 * @return
	 */
	public String getProSelfName(GeDepartment geDepartment);
	
	/**
	 * ��ѯ����������Լ�deptment����id
	 * @return
	 */
	public Map<String,List> findPropoAreaDept();
	
	/**
	 * Ȩ��deptid
	 * @param query
	 * @return
	 */
	public String findDeptString(String areaCode);
	
}
