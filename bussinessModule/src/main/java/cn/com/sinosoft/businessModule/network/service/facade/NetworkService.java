package cn.com.sinosoft.businessModule.network.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.network.domain.Network;

public interface NetworkService {
	/**
	 * @ProjectName:
	 * @Package:     
	 * @ClassName:   
	 * @Description: 
	 * @Author:      jack_xiao
	 * @CreateDate:  2013-9-24
	 * @UpdateUser: 
	 * @UpdateDate: 
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	
	/***
	 * ����������ѯ����������Ϣ
	 * @param serialNo �߼��������
	 * @return
	 */
	public abstract Network findNetworkBySerialNo(
			String serialNo);

	/***
	 * ��ҳ��ѯ����������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page getNetworkPage(
			Network Network, int pageNo, int pageSize);

	/**
	 * ����Ͷ����
	 * @param Network
	 */
	public abstract void updateNetwork(
			Network Network);

	public abstract List<Network> findNetworkByQueryRule(
			QueryRule queryRule);



	@SuppressWarnings("rawtypes")
	public abstract Network findNetworkByQueryMap(Map propertyMap);
	
	
	public abstract void deleteNetwork(Network network);
	
	@SuppressWarnings("rawtypes")
	public abstract List findArea(String province);
	public abstract String findPlaceName(String placeCode);
	public abstract List<String> findUniqueProvince();
	public abstract List<String> findUniqueCity(String province);
	public abstract String addServiceNetwork(Network network);

}
