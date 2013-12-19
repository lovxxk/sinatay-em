/**
 * 
 */
package cn.com.sinosoft.ebusiness.dhf.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.dhf.domain.GeFunctionSwitchPortal;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 *
 */
public interface GeFunctionSwitchPortalService {

	/**
	 *  根据查询对象获取Page对象的功能开关配置信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeFunctionSwitchPortal(QueryRule queryRule, int pageNo, int pageSize)  throws BizConfigCommonException;

	/**
	 * 根据规则查询一条功能开关配置信息
	 * @param queryRule
	 * @return
	 */
	public GeFunctionSwitchPortal findGeFunctionSwitchPortal(QueryRule queryRule)  throws BizConfigCommonException ;
	
	/**
	 * 更新功能开关配置信息
	 * @param GeFunctionSwitchPortal
	 */
	public boolean updateGeFunctionSwitchPortal(GeFunctionSwitchPortal GeFunctionSwitchPortal)  throws BizConfigCommonException ;
	
	/**
	 * 保存功能开关配置信息
	 * @param GeFunctionSwitchPortal
	 */
	public boolean save(GeFunctionSwitchPortal GeFunctionSwitchPortal)  throws BizConfigCommonException ;

	/**
	 * 刪除功能开关配置信息
	 * @param id
	 */
	public boolean delete(String functiontId)  throws BizConfigCommonException ;
	
	/**
	 * 刪除String对象中key所对应的对象
	 * @param keys
	 */
	public boolean deleteAllGeFunctionSwitchPortal(String keys)  throws BizConfigCommonException ;
	
	/**
	 * 刪除list集合中的GeFunctionSwitchPortal对象
	 * @param userCode
	 */
	public boolean deleteAllGeFunctionSwitchPortal(List<GeFunctionSwitchPortal> GeFunctionSwitchPortalList)  throws BizConfigCommonException ;
	
	/**
	 * 判断某个开关是否开通
	 * @param functiontId
	 * @param portalChannel 
	 * @return
	 */
	public boolean isSwitchOpen(String functiontId, String portalChannel);
}
