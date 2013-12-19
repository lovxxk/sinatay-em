package cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.common.publishInfo.serviceNetworks.domain.GeStationInfo;

/**
 * �������������
 *  
 *
 */
public interface GeStationInfoService {
	
	/**
	 * �������������Ϣ
	 * @param geStationInfo
	 * @return
	 */
	public boolean save(GeStationInfo geStationInfo);
	
	/**
	 * ����������ѯ��������
	 * @param pk
	 * @return
	 */
	public GeStationInfo findGeStationInfoByPk(String pk);
	
	/**
	 * ��ѯ���еķ���������Ϣ
	 * @param queryRule
	 * @return
	 */
	public List<GeStationInfo> findAllGeStationInfo(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ����������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllGeStationInfoByDisPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ������������Ϣ
	 * @param pk
	 * @return
	 */
	public boolean delete(String pk);
	
	/**
	 * �޸ķ���������Ϣ
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateGeStationInfo(GeStationInfo geStationInfo);
	
	public String getCode();
}
