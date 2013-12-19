package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceHandlerClass;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceHandlerClassService;

/***
 * �ӿڴ�����
 *  
 * @version 1 created on 2011��8��26������03:55:04
 */
public class PortalInterfaceHandlerClassServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceHandlerClass, String> implements PortalInterfaceHandlerClassService {

	/***
	 * ����������ѯ�ӿڴ�����
	 * @param className ����
	 * @return PortalInterfaceHandlerClass �ӿڴ�����
	 */
	@Override
	public PortalInterfaceHandlerClass findPortalInterfaceHandlerClassByClassName(String className) {
		return  super.findUnique("className", className);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڴ������б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڴ������б��Page����
	 */
	@Override
	public Page findPortalInterfaceHandlerClass(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ӿڴ������б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 *��ӽӿڴ�����
	 *@param PortalInterfaceHandlerClass �ӿڴ�����
	 *@return
	 */
	@Override
	public void addPortalInterfaceHandlerClass(PortalInterfaceHandlerClass portalInterfaceHandlerClass) {
		super.save(portalInterfaceHandlerClass);
	}
	
	/***
	 * ���½ӿڴ�����
	 * @param PortalInterfaceHandlerClass �ӿڴ�����
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
	 * ���ݸ��ݽڵ�����ɾ���ڵ���Ϣ
	 * @param className ����
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
