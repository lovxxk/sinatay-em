package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade;

import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;

public interface LifeGeStationInfoService {
	
	/**
	 * ���շ��������ѯ
	 * @param DEPTID
	 * @return
	 */


	public GeStationInfo[] getStationInfoByDept(String DeptId);
}
