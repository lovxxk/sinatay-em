package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;

public interface PortalInterfaceService {

	/***
	 * 
	 * 通过交易代码获取接口信息
	 * @param transCode 交易代码
	 * @return 接口信息
	 * 
	 */
	public abstract PortalInterface findPortalInterfaceByTransCode(
			String transCode);

	/**
	 * 根据查询对象获取Page对象的接口信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口信息列表的Page对象
	 */
	public abstract Page findPortalInterface(QueryRule queryRule, int pageNo,
			int pageSize);

	/***
	 * 添加接口信息
	 * @param PortalInterface 接口信息
	 * @return
	 */
	public abstract void addPortalInterface(PortalInterface portalInterface);

	/***
	 * 更新接口信息
	 * @param PortalInterface 接口信息
	 * @return
	 */
	public abstract void updatePortalInterface(PortalInterface portalInterface);

	/****
	 * 通过交易代码删除接口信息
	 * @param transCode 交易代码
	 * @return
	 */
	public abstract void deletePortalInterfaceByTransCode(String transCode);

}