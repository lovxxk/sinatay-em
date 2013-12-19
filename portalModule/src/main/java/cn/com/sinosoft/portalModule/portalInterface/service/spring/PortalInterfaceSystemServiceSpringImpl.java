package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceSystemService;

public class PortalInterfaceSystemServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceSystem, String> implements PortalInterfaceSystemService {
	
	/***
	 * 添加接口交互系统信息
	 * @param portalInterfaceSystem 接口交互系统信息和接口关系对象
	 * @return
	 */
	@Override
	public void addPortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		super.save(portalInterfaceSystem);
	}
	
	/**
	 * 根据查询对象获取Page对象的接口交互系统信息和接口关系列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口交互系统信息和接口关系列表的Page对象
	 */
	@Override
	public Page findPortalInterfaceSystem(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取接口交互系统信息和接口关系列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	@Override
	public void deletePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		if (portalInterfaceSystem != null) {
			clearPortalInterfaceCache(portalInterfaceSystem.getPortalInterface());
			super.delete(portalInterfaceSystem);
		}
	}
	
	/***
	 * 更新接口交互系统信息和接口关系信息
	 * @param 更新接口交互系统信息和接口关系信息
	 */
	@Override
	public void updatePortalInterfaceSystem(PortalInterfaceSystem portalInterfaceSystem) {
		PortalInterfaceSystem update = super.get(portalInterfaceSystem.getSerialNo());
		BeanUtils.copyProperties(portalInterfaceSystem, update, new String[]{"serialNo"});
		super.update(update);
		clearPortalInterfaceCache(portalInterfaceSystem.getPortalInterface());
	}
	
}
