package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;
import java.util.Map;

public interface GeProductProDeptService {
	/**
	 * 根据财险核心机构编码查询集团机构编码
	 * @param serviceId
	 * @return
	 */
	public String findDeptIdByServiceId(String serviceId);
	/**
	 * 按产品编号查询旧销售地区
	 * @param coreProductCode
	 * @return 旧销售地区字符串
	 */
	/**
	 * 获取虚拟父节点
	 * @param operatorDeptAuths
	 * @return
	 */
	public String getAllVirtualParent(String operatorDeptAuths);

	public abstract List findProductSaleDept(String coreProductCode);
	
	/**
	 * 查询产品配置的机构
	 * @param productCode
	 * @param deptlevel
	 * @param parentid
	 * @return
	 */
	public List<String[]> findProductSaleDepartmet(String productCode,String deptlevel,String parentid);
	
	/**
	 * 根据核心产品编码查询集团机构编码
	 * @param orgDeptId
	 * @return
	 */
	public String findDeptIdByOrgId(String orgDeptId);
	/**
	 * 根据核心产品编码查询集团的机构名称
	 */
	public String findDeptNameByOrgId(String orgDeptId);
	
	/**
	 * 根据集团机构编码查询核心机构编码
	 * @param orgId
	 * @return
	 */
	public String findOrgIdByDeptId(String orgId);
}
