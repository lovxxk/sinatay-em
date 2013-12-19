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
	 * 根据登录名查询接口账号信息
	 * @param loginName 登录名
	 * @return 客户端用户
	 */
	@Override
	public PortalInterfaceAccount findPortalInterfaceAccountByLoginName(String loginName) {
		return super.findUnique("loginName", loginName);
	}
	
	/**
	 * 根据查询对象获取Page对象的接口账号信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口账号信息列表的Page对象
	 */
	@Override
	public Page findPortalInterfaceAccount(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取接口账号信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/***
	 * 增加客户端用户
	 * @param portalInterfaceAccount 客户端用户
	 * @return
	 */
	@Override
	public void addPortalInterfaceAccount(PortalInterfaceAccount portalInterfaceAccount) {
		super.save(portalInterfaceAccount);
	}
	
	/***
	 * 更新接口账号信息
	 * @param 更新接口账号信息
	 */
	@Override
	public void updatePortalInterfaceAccount(PortalInterfaceAccount portalInterfaceAccount) {
		PortalInterfaceAccount update = super.get(portalInterfaceAccount.getSerialNo());
		BeanUtils.copyProperties(portalInterfaceAccount, update, new String[]{"serialNo"});
		super.update(update);
		clearPortalInterfaceCache(portalInterfaceAccount.getPortalInterface());
	}
	
	/***
	 * 删除接口账号信息
	 * @param 更新接口账号信息
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
