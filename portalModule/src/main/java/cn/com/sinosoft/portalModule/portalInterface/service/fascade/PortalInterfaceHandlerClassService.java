package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceHandlerClass;

public interface PortalInterfaceHandlerClassService {

	/***
	 * ����������ѯ�ӿڴ�����
	 * @param className ����
	 * @return PortalInterfaceHandlerClass �ӿڴ�����
	 */
	public abstract PortalInterfaceHandlerClass findPortalInterfaceHandlerClassByClassName(
			String className);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿڴ������б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿڴ������б��Page����
	 */
	public abstract Page findPortalInterfaceHandlerClass(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 *��ӽӿڴ�����
	 *@param PortalInterfaceHandlerClass �ӿڴ�����
	 *@return
	 */
	public abstract void addPortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass);

	/***
	 * ���½ӿڴ�����
	 * @param PortalInterfaceHandlerClass �ӿڴ�����
	 * @return
	 */
	public abstract void updatePortalInterfaceHandlerClass(
			PortalInterfaceHandlerClass portalInterfaceHandlerClass);

	/***
	 * ���ݸ��ݽڵ�����ɾ���ڵ���Ϣ
	 * @param className ����
	 * @return
	 */
	public abstract void deletePortalInterfaceHandlerClassByElementName(
			String className);

}