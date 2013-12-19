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
	 *  ���ݲ�ѯ�����ȡPage����Ĺ��ܿ���������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findGeFunctionSwitchPortal(QueryRule queryRule, int pageNo, int pageSize)  throws BizConfigCommonException;

	/**
	 * ���ݹ����ѯһ�����ܿ���������Ϣ
	 * @param queryRule
	 * @return
	 */
	public GeFunctionSwitchPortal findGeFunctionSwitchPortal(QueryRule queryRule)  throws BizConfigCommonException ;
	
	/**
	 * ���¹��ܿ���������Ϣ
	 * @param GeFunctionSwitchPortal
	 */
	public boolean updateGeFunctionSwitchPortal(GeFunctionSwitchPortal GeFunctionSwitchPortal)  throws BizConfigCommonException ;
	
	/**
	 * ���湦�ܿ���������Ϣ
	 * @param GeFunctionSwitchPortal
	 */
	public boolean save(GeFunctionSwitchPortal GeFunctionSwitchPortal)  throws BizConfigCommonException ;

	/**
	 * �h�����ܿ���������Ϣ
	 * @param id
	 */
	public boolean delete(String functiontId)  throws BizConfigCommonException ;
	
	/**
	 * �h��String������key����Ӧ�Ķ���
	 * @param keys
	 */
	public boolean deleteAllGeFunctionSwitchPortal(String keys)  throws BizConfigCommonException ;
	
	/**
	 * �h��list�����е�GeFunctionSwitchPortal����
	 * @param userCode
	 */
	public boolean deleteAllGeFunctionSwitchPortal(List<GeFunctionSwitchPortal> GeFunctionSwitchPortalList)  throws BizConfigCommonException ;
	
	/**
	 * �ж�ĳ�������Ƿ�ͨ
	 * @param functiontId
	 * @param portalChannel 
	 * @return
	 */
	public boolean isSwitchOpen(String functiontId, String portalChannel);
}
