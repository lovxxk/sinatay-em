/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeFunctionSwitch;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 *
 */
public interface GeFunctionSwitchService {

	/**
	 *  根据查询对象获取Page对象的功能开关配置信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeFunctionSwitch(QueryRule queryRule, int pageNo, int pageSize)  throws BizConfigCommonException;

	/**
	 * 根据规则查询一条功能开关配置信息
	 * @param queryRule
	 * @return
	 */
	public GeFunctionSwitch findGeFunctionSwitch(QueryRule queryRule)  throws BizConfigCommonException ;
	
	/**
	 * 更新功能开关配置信息
	 * @param GeFunctionSwitch
	 */
	public boolean updateGeFunctionSwitch(GeFunctionSwitch geFunctionSwitch)  throws BizConfigCommonException ;
	
	/**
	 * 保存功能开关配置信息
	 * @param GeFunctionSwitch
	 */
	public boolean save(GeFunctionSwitch geFunctionSwitch)  throws BizConfigCommonException ;

	/**
	 * 刪除功能开关配置信息
	 * @param id
	 */
	public boolean delete(String functiontId)  throws BizConfigCommonException ;
	
	/**
	 * 刪除String对象中key所对应的对象
	 * @param keys
	 */
	public boolean deleteAllGeFunctionSwitch(String keys)  throws BizConfigCommonException ;
	
	/**
	 * 刪除list集合中的GeFunctionSwitch对象
	 * @param userCode
	 */
	public boolean deleteAllGeFunctionSwitch(List<GeFunctionSwitch> geFunctionSwitchList)  throws BizConfigCommonException ;
	
	/**
	 * 判断某个开关是否开通
	 * @param functiontId
	 * @return
	 */
	public boolean isSwitchOpen(String functiontId);
}
