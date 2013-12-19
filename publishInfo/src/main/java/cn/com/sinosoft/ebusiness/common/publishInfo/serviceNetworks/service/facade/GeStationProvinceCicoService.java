package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade;

import java.util.List;

import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationProvinceCico;

/**
 * 服务网点所属机构服务接口
 *  
 *
 */
public interface GeStationProvinceCicoService {
	
	/**
	 * 查询服务网点树
	 * @return
	 */
	public abstract String getServiceNetworkXMLTree(String id);
	/**
	 * 查找省市
	 */
	public GeStationProvinceCico findgeGeStationProvinceCico(QueryRule queryRule);
	/**
	 * 查找一级
	 */
	public List<GeStationProvinceCico> findProvinceList(QueryRule queryRule);
}
