package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;

/***
 * 接口信息服务类
 *  
 * @version 1 created on 2011年8月26日下午12:49:14
 */
public class PortalInterfaceServiceSpringImpl extends AbstractInterfaceService<PortalInterface, String> implements PortalInterfaceService {

	/***
	 * 
	 * 通过交易代码获取接口信息
	 * @param transCode 交易代码
	 * @return 接口信息
	 * 
	 */
	@Override
	public PortalInterface findPortalInterfaceByTransCode(String transCode) {
		PortalInterface portalInterface = portalInterfaceMap.get(transCode);

		if (portalInterface == null) {
			portalInterface = super.findUnique("transCode", transCode);
			if (portalInterface == null) {
				return null;
			} else {
				portalInterfaceMap.put(transCode, portalInterface);
			}
		}
		return portalInterface;
	}
	
	/**
	 * 根据查询对象获取Page对象的接口信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口信息列表的Page对象
	 */
	@Override
	public Page findPortalInterface(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取接口信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}

	/***
	 * 添加接口信息
	 * @param portalInterface 接口信息
	 * @return
	 */
	@Override
	public void addPortalInterface(PortalInterface portalInterface) {
			logger.debug("增加接口信息，交易代码：" + portalInterface.getTransCode());
			super.save(portalInterface);
	}
	
	/***
	 * 更新接口信息
	 * @param portalInterface 接口信息
	 * @return
	 */
	@Override
	public void updatePortalInterface(PortalInterface portalInterface) {
		logger.debug("更新接口信息，交易代码：" + portalInterface.getTransCode());
		try {
			PortalInterface update = super.findUnique("transCode", portalInterface.getTransCode());
			BeanUtils.copyProperties(portalInterface, update, new String[]{"transCode"});
			super.save(update);
			clearPortalInterfaceCache(portalInterface);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/****
	 * 通过交易代码删除接口信息
	 * @param transCode 交易代码
	 * @return
	 */
	@Override
	public void deletePortalInterfaceByTransCode(String transCode) {
		clearPortalInterfaceCacheByTransCode(transCode);
		super.deleteByPK(transCode);
	}
}
