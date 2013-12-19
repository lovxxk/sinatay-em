package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceElement;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceElementService;

/***
 * �ӿ�Ԫ�ؽڵ���Ϣ
 *  
 * @version 1 created on 2011��8��26������03:55:04
 */
public class PortalInterfaceElementServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceElement, String> implements PortalInterfaceElementService {

	/***
	 * ���ݵ�¼����ѯ�ӿ�Ԫ�ؽڵ���Ϣ
	 * @param loginName ��¼��
	 * @return PortalInterfaceElement �ӿ�Ԫ�ؽڵ���Ϣ
	 */
	@Override
	public PortalInterfaceElement findPortalInterfaceElementByElementName(String elementName) {
		return  super.findUnique("elementName", elementName);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ�Ԫ�ؽڵ���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ�Ԫ�ؽڵ���Ϣ�б��Page����
	 */
	@Override
	public Page findPortalInterfaceElement(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ӿ�Ԫ�ؽڵ���Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 *��ӽӿ�Ԫ�ؽڵ���Ϣ
	 *@param portalInterfaceElement �ӿ�Ԫ�ؽڵ���Ϣ
	 *@return
	 */
	@Override
	public void addPortalInterfaceElement(PortalInterfaceElement portalInterfaceElement) {
		super.save(portalInterfaceElement);
	}
	
	/***
	 * ���½ӿ�Ԫ�ؽڵ���Ϣ
	 * @param portalInterfaceElement �ӿ�Ԫ�ؽڵ���Ϣ
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
	 * ���ݸ��ݽڵ�����ɾ���ڵ���Ϣ
	 * @param loginName ��¼��
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
