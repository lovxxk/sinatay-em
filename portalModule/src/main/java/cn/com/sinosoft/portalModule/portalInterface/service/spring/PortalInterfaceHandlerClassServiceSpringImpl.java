package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceHandlerClass;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceHandlerClassService;

/***
 * 接口处理类
 *  
 * @version 1 created on 2011年8月26日下午03:55:04
 */
public class PortalInterfaceHandlerClassServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceHandlerClass, String> implements PortalInterfaceHandlerClassService {

	/***
	 * 根据类名查询接口处理类
	 * @param className 类名
	 * @return PortalInterfaceHandlerClass 接口处理类
	 */
	@Override
	public PortalInterfaceHandlerClass findPortalInterfaceHandlerClassByClassName(String className) {
		return  super.findUnique("className", className);
	}
	
	/**
	 * 根据查询对象获取Page对象的接口处理类列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口处理类列表的Page对象
	 */
	@Override
	public Page findPortalInterfaceHandlerClass(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取接口处理类列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 *添加接口处理类
	 *@param PortalInterfaceHandlerClass 接口处理类
	 *@return
	 */
	@Override
	public void addPortalInterfaceHandlerClass(PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		super.save(portalInterfaceHandlerClass);
	}
	
	/***
	 * 更新接口处理类
	 * @param PortalInterfaceHandlerClass 接口处理类
	 * @return
	 */
	@Override
	public void updatePortalInterfaceHandlerClass(PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		PortalInterfaceHandlerClass update = super.get(portalInterfaceHandlerClass.getSerialNo());
		BeanUtils.copyProperties(portalInterfaceHandlerClass, update, new String[]{"serialNo"});
		super.update(update);
		clearPortalInterfaceCache(portalInterfaceHandlerClass.getPortalInterface());
	}
	
	/***
	 * 根据根据节点名称删除节点信息
	 * @param className 类名
	 * @return
	 */
	@Override
	public void deletePortalInterfaceHandlerClassByElementName(String className){
		PortalInterfaceHandlerClass deletePortalInterfaceHandlerClass = this.findPortalInterfaceHandlerClassByClassName(className);
		if (deletePortalInterfaceHandlerClass != null) {
			clearPortalInterfaceCache(deletePortalInterfaceHandlerClass.getPortalInterface());
			super.delete(deletePortalInterfaceHandlerClass);
		}
		
	}
	
	
}
