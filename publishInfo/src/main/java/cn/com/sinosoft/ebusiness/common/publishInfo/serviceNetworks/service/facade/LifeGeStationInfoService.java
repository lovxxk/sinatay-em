package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade;

import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;

public interface LifeGeStationInfoService {
	
	/**
	 * 寿险服务网点查询
	 * @param DEPTID
	 * @return
	 */


	public GeStationInfo[] getStationInfoByDept(String DeptId);
}
