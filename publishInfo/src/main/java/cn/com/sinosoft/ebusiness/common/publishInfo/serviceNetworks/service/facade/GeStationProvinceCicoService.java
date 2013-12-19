package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade;

import java.util.List;

import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationProvinceCico;

/**
 * ��������������������ӿ�
 *  
 *
 */
public interface GeStationProvinceCicoService {
	
	/**
	 * ��ѯ����������
	 * @return
	 */
	public abstract String getServiceNetworkXMLTree(String id);
	/**
	 * ����ʡ��
	 */
	public GeStationProvinceCico findgeGeStationProvinceCico(QueryRule queryRule);
	/**
	 * ����һ��
	 */
	public List<GeStationProvinceCico> findProvinceList(QueryRule queryRule);
}
