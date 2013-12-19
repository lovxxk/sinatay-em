package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceAccount;

public interface PortalInterfaceAccountService {

	/***
	 * ���ݵ�¼����ѯ�ӿ��˺���Ϣ
	 * @param loginName ��¼��
	 * @return �ͻ����û�
	 */
	public abstract PortalInterfaceAccount findPortalInterfaceAccountByLoginName(
			String loginName);

	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ��˺���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ��˺���Ϣ�б��Page����
	 */
	public abstract Page findPortalInterfaceAccount(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * ���ӿͻ����û�
	 * @param portalInterfaceAccount �ͻ����û�
	 * @return
	 */
	public abstract void addPortalInterfaceAccount(
			PortalInterfaceAccount portalInterfaceAccount);

	/***
	 * ���½ӿ��˺���Ϣ
	 * @param ���½ӿ��˺���Ϣ
	 */
	public abstract void updatePortalInterfaceAccount(
			PortalInterfaceAccount portalInterfaceAccount);

	/***
	 * ɾ���ӿ��˺���Ϣ
	 * @param ���½ӿ��˺���Ϣ
	 */
	public abstract void deletePortalInterfaceAccount(
			PortalInterfaceAccount portalInterfaceAccount);

	public abstract List<PortalInterfaceAccount> findPortalInterfaceAccount(
			QueryRule queryRule);

}