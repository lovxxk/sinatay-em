package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;

/**
 * 服务网点服务类
 *  
 *
 */
public interface GeStationInfoService {
	
	/**
	 * 保存服务网点信息
	 * @param geStationInfo
	 * @return
	 */
	public boolean save(GeStationInfo geStationInfo);
	
	/**
	 * 根据主键查询服务网点
	 * @param pk
	 * @return
	 */
	public GeStationInfo findGeStationInfoByPk(String pk);
	
	/**
	 * 查询所有的服务网点信息
	 * @param queryRule
	 * @return
	 */
	public List<GeStationInfo> findAllGeStationInfo(QueryRule queryRule);
	
	/**
	 * 分页查询服务网点信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllGeStationInfoByDisPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除服务网点信息
	 * @param pk
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * 修改服务网点信息
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateGeStationInfo(GeStationInfo geStationInfo);
	
	public String getCode();
}
