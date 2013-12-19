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
	 *  ���ݲ�ѯ�����ȡPage����Ĺ��ܿ���������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeFunctionSwitch(QueryRule queryRule, int pageNo, int pageSize)  throws BizConfigCommonException;

	/**
	 * ���ݹ����ѯһ�����ܿ���������Ϣ
	 * @param queryRule
	 * @return
	 */
	public GeFunctionSwitch findGeFunctionSwitch(QueryRule queryRule)  throws BizConfigCommonException ;
	
	/**
	 * ���¹��ܿ���������Ϣ
	 * @param GeFunctionSwitch
	 */
	public boolean updateGeFunctionSwitch(GeFunctionSwitch geFunctionSwitch)  throws BizConfigCommonException ;
	
	/**
	 * ���湦�ܿ���������Ϣ
	 * @param GeFunctionSwitch
	 */
	public boolean save(GeFunctionSwitch geFunctionSwitch)  throws BizConfigCommonException ;

	/**
	 * �h�����ܿ���������Ϣ
	 * @param id
	 */
	public boolean delete(String functiontId)  throws BizConfigCommonException ;
	
	/**
	 * �h��String������key����Ӧ�Ķ���
	 * @param keys
	 */
	public boolean deleteAllGeFunctionSwitch(String keys)  throws BizConfigCommonException ;
	
	/**
	 * �h��list�����е�GeFunctionSwitch����
	 * @param userCode
	 */
	public boolean deleteAllGeFunctionSwitch(List<GeFunctionSwitch> geFunctionSwitchList)  throws BizConfigCommonException ;
	
	/**
	 * �ж�ĳ�������Ƿ�ͨ
	 * @param functiontId
	 * @return
	 */
	public boolean isSwitchOpen(String functiontId);
}
