package cn.com.sinosoft.portalModule.portalInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceAccount;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.AbstractInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceAccountService;

public class PortalInterfaceAccountServiceSpringImpl extends AbstractInterfaceService<PortalInterfaceAccount, String> implements PortalInterfaceAccountService {
	
	/***
	 * ���ݵ�¼����ѯ�ӿ��˺���Ϣ
	 * @param loginName ��¼��
	 * @return �ͻ����û�
	 */
	@Override
	public PortalInterfaceAccount findPortalInterfaceAccountByLoginName(String loginName) {
		return super.findUnique("loginName", loginName);
	}
	
	/**
	 * ���ݲ�ѯ�����ȡPage����Ľӿ��˺���Ϣ�б�
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return �����ӿ��˺���Ϣ�б��Page����
	 */
	@Override
	public Page findPortalInterfaceAccount(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("��ȡ�ӿ��˺���Ϣ�б�");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * ���ӿͻ����û�
	 * @param portalInterfaceAccount �ͻ����û�
	 * @return
	 */
	@Override
	public void addPortalInterfaceAccount(PortalInterfaceAccount portalInterfaceAccount) {
		super.save(portalInterfaceAccount);
	}
	
	/***
	 * ���½ӿ��˺���Ϣ
	 * @param ���½ӿ��˺���Ϣ
	 */
	@Override
	public void updatePortalInterfaceAccount(PortalInterfaceAccount portalInterfaceAccount) {
		PortalInterfaceAccount update = super.get(portalInterfaceAccount.getSerialNo());
		BeanUtils.copyProperties(portalInterfaceAccount, update, new String[]{"serialNo"});
		super.update(update);
		clearPortalInterfaceCache(portalInterfaceAccount.getPortalInterface());
	}
	
	/***
	 * ɾ���ӿ��˺���Ϣ
	 * @param ���½ӿ��˺���Ϣ
	 */
	@Override
	public void deletePortalInterfaceAccount(PortalInterfaceAccount portalInterfaceAccount) {
		clearPortalInterfaceCache(portalInterfaceAccount.getPortalInterface());
		super.delete(portalInterfaceAccount);
		
	}
	
	@Override
	public List<PortalInterfaceAccount> findPortalInterfaceAccount(QueryRule queryRule) {
		// TODO Auto-generated method stub
		return super.find(queryRule);
	}
	
}
