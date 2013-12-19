package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceElement;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceElementService;

/***
 * 接口元素节点信息
 *  
 * @version 1 created on 2011年8月26日下午03:55:04
 */
public class PortalInterfaceElementServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceElement, String> implements PortalInterfaceElementService {

	/***
	 * 根据登录名查询接口元素节点信息
	 * @param loginName 登录名
	 * @return PortalInterfaceElement 接口元素节点信息
	 */
	@Override
	public PortalInterfaceElement findPortalInterfaceElementByElementName(String elementName) {
		return  super.findUnique("elementName", elementName);
	}
	
	/**
	 * 根据查询对象获取Page对象的接口元素节点信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口元素节点信息列表的Page对象
	 */
	@Override
	public Page findPortalInterfaceElement(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取接口元素节点信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 *添加接口元素节点信息
	 *@param portalInterfaceElement 接口元素节点信息
	 *@return
	 */
	@Override
	public void addPortalInterfaceElement(PortalInterfaceElement portalInterfaceElement) {
		super.save(portalInterfaceElement);
	}
	
	/***
	 * 更新接口元素节点信息
	 * @param portalInterfaceElement 接口元素节点信息
	 * @return
	 */
	@Override
	public void updatePortalInterfaceElement(PortalInterfaceElement portalInterfaceElement) {
		PortalInterfaceElement update = super.get(portalInterfaceElement.getSerialNo());
		BeanUtils.copyProperties(portalInterfaceElement, update, new String[]{"serialNo"});
		super.update(update);
		clearPortalInterfaceCache(portalInterfaceElement.getPortalInterface());
	}
	
	/***
	 * 根据根据节点名称删除节点信息
	 * @param loginName 登录名
	 * @return
	 */
	@Override
	public void deletePortalInterfaceElementByElementName(String elementName){
		PortalInterfaceElement deletePortalInterfaceElement = this.findPortalInterfaceElementByElementName(elementName);
		if (deletePortalInterfaceElement != null) {
			clearPortalInterfaceCache(deletePortalInterfaceElement.getPortalInterface());
			super.delete(deletePortalInterfaceElement);
		}
		
	}
	
	
}
