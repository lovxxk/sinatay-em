package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade.LifeGeStationInfoService;

public class LifeGeStationInfoSpringImpl extends GenericDaoHibernate<GeStationInfo, String>
		implements LifeGeStationInfoService {
	
	/**
	 * 寿险服务网点查询
	 * @param DEPTID
	 * @return
	 */
	@Override
	public GeStationInfo[] getStationInfoByDept(String DeptId) {
		// TODO Auto-generated method stub
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("deptid", DeptId);
			int size = super.find(queryRule).size();
			GeStationInfo[] geStationInfoArray = new GeStationInfo[size];
			for(int i=0;i<size;i++){
				GeStationInfo geStationInfo = super.find(queryRule).get(i);
				geStationInfoArray[i] = geStationInfo;
			}
			return geStationInfoArray;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
