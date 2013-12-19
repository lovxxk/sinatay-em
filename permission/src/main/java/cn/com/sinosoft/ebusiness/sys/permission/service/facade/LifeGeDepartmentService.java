package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartmentDto;

public interface LifeGeDepartmentService {
	/**
	 * 根据机构代码（父机构编码）查询出机构名称、 机构编码、是否为末级机构（Y/N)
	 * @param deptId	机构代码
	 * @return
	 */
	public GeDepartmentDto[] getLifeDeptInfo(String deptId);
}
