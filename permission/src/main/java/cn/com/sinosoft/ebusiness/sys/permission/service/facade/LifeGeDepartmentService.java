package cn.com.sinosoft.ebusiness.sys.permission.service.facade;

import cn.com.sinosoft.ebusiness.sys.permission.domain.GeDepartmentDto;

public interface LifeGeDepartmentService {
	/**
	 * ���ݻ������루���������룩��ѯ���������ơ� �������롢�Ƿ�Ϊĩ��������Y/N)
	 * @param deptId	��������
	 * @return
	 */
	public GeDepartmentDto[] getLifeDeptInfo(String deptId);
}
