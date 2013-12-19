package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartment;

public interface GeProposalareaService {

	
	
	/**
	 * 获取虚拟父节点
	 * @param groupDeptAuths
	 * @return
	 */
	public String getAllVirtualProParent(String groupDeptAuths);
	
	/**
	 * 获取本级名称
	 * @param geDepartment
	 * @return
	 */
	public String getProSelfName(GeDepartment geDepartment);
	
	/**
	 * 查询销售区域表以及deptment表交集id
	 * @return
	 */
	public Map<String,List> findPropoAreaDept();
	
	/**
	 * 权限deptid
	 * @param query
	 * @return
	 */
	public String findDeptString(String areaCode);
	
}
