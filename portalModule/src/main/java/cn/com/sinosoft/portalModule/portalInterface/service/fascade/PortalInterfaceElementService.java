package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceElement;

public interface PortalInterfaceElementService {

	/***
	 * ���ݵ�¼����ѯ�ӿ�Ԫ�ؽڵ���Ϣ
	 * @param loginName ��¼��
	 * @return PortalInterfaceElement �ӿ�Ԫ�ؽڵ���Ϣ
	 */
	public abstract PortalInterfaceElement findPortalInterfaceElementByElementName(
			String elementName);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ�Ԫ�ؽڵ���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ�Ԫ�ؽڵ���Ϣ�б��Page����
	 */
	public abstract Page findPortalInterfaceElement(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 *��ӽӿ�Ԫ�ؽڵ���Ϣ
	 *@param portalInterfaceElement �ӿ�Ԫ�ؽڵ���Ϣ
	 *@return
	 */
	public abstract void addPortalInterfaceElement(
			PortalInterfaceElement portalInterfaceElement);

	/***
	 * ���½ӿ�Ԫ�ؽڵ���Ϣ
	 * @param portalInterfaceElement �ӿ�Ԫ�ؽڵ���Ϣ
	 * @return
	 */
	public abstract void updatePortalInterfaceElement(
			PortalInterfaceElement portalInterfaceElement);

	/***
	 * ���ݸ��ݽڵ�����ɾ���ڵ���Ϣ
	 * @param loginName ��¼��
	 * @return
	 */
	public abstract void deletePortalInterfaceElementByElementName(
			String elementName);

}