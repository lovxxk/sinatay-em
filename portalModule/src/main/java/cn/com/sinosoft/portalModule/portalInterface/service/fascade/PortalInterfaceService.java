package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;

public interface PortalInterfaceService {

	/***
	 * 
	 * ͨ�����״����ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @return �ӿ���Ϣ
	 * 
	 */
	public abstract PortalInterface findPortalInterfaceByTransCode(
			String transCode);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ���Ϣ�б��Page����
	 */
	public abstract Page findPortalInterface(QueryRule queryRule, int pageNo,
			int pageSize);

	/***
	 * ��ӽӿ���Ϣ
	 * @param PortalInterface �ӿ���Ϣ
	 * @return
	 */
	public abstract void addPortalInterface(PortalInterface portalInterface);

	/***
	 * ���½ӿ���Ϣ
	 * @param PortalInterface �ӿ���Ϣ
	 * @return
	 */
	public abstract void updatePortalInterface(PortalInterface portalInterface);

	/****
	 * ͨ�����״���ɾ���ӿ���Ϣ
	 * @param transCode ���״���
	 * @return
	 */
	public abstract void deletePortalInterfaceByTransCode(String transCode);

}