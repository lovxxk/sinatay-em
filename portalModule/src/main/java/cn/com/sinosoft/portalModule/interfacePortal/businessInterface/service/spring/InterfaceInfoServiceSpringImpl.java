package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.InterfaceInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

/***
 * 接口信息服务类
 *  
 * @version 1 created on 2011年8月26日下午12:49:14
 */
public class InterfaceInfoServiceSpringImpl extends GenericDaoHibernate<InterfaceInfo, String> implements InterfaceInfoService,PortalService {

	/***
	 * 
	 * 通过交易代码获取接口信息
	 * @param transCode 交易代码
	 * @return 接口信息
	 * 
	 */
	public InterfaceInfo findInterfaceInfoByTransCode(String transCode) {

		InterfaceInfo interfaceInfo = interfaceInfoMap.get(transCode);

		if (interfaceInfo == null) {
			interfaceInfo = super.findUnique("transCode", transCode);
			if (interfaceInfo == null) {
				System.out.println("交易代码：" + transCode + "所对应的接口信息不存在！");
				return null;
			} else {
				interfaceInfoMap.put(transCode, interfaceInfo);
			}

		}
		return interfaceInfo;
	}
	
	/**
	 * 根据查询对象获取Page对象的接口信息列表
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口信息列表的Page对象
	 */
	public Page findInterfaceInfo(QueryRule queryRule, int pageNo, int pageSize) {
		logger.debug("获取接口信息列表");
		return super.find(queryRule, pageNo, pageSize);
	}

	/***
	 * 添加接口信息
	 * @param interfaceInfo 接口信息
	 * @return
	 */
	public void addInterfaceInfo(InterfaceInfo interfaceInfo) {
			logger.debug("增加接口信息，交易代码：" + interfaceInfo.getTransCode());
			super.save(interfaceInfo);
	}
	
	/***
	 * 更新接口信息
	 * @param interfaceInfo 接口信息
	 * @return
	 */
	public void updateInterfaceInfo(InterfaceInfo interfaceInfo) {
		logger.debug("更新接口信息，交易代码：" + interfaceInfo.getTransCode());
		try {
			InterfaceInfo update = super.findUnique("transCode", interfaceInfo.getTransCode());
			BeanUtils.copyProperties(interfaceInfo, update, new String[]{"transCode"});
			super.save(update);
			clearCacheByInterfaceInfo(interfaceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/****
	 * 通过交易代码删除接口信息
	 * @param transCode 交易代码
	 * @return
	 */
	public void deleteInterfaceInfoByTransCode(String transCode) {
		interfaceInfoMap.remove(transCode);
		stubClassMap.remove(transCode);
		stubSendClassMap.remove(transCode);
		super.deleteByPK(transCode);
	}
	
	/***
	 * 根据接口对象清除缓存 
	 * @param interfaceInfo
	 * @return
	 */
	public void clearCacheByInterfaceInfo(InterfaceInfo interfaceInfo) {
		if (interfaceInfo != null) {
			interfaceInfoMap.remove(interfaceInfo.getTransCode());
			stubClassMap.remove(interfaceInfo.getTransCode());
			stubSendClassMap.remove(interfaceInfo.getTransCode());
		}
	}
	
	/***
	 * 清除所有缓存
	 */
	public void clearAllCache() {
		interfaceInfoMap.clear();
		stubClassMap.clear();
		stubSendClassMap.clear();
	}
}
